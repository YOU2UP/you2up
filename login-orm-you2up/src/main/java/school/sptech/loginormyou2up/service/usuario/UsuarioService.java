package school.sptech.loginormyou2up.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.treino.LocalTreinoCriacaoDto;
import school.sptech.loginormyou2up.dto.treino.QuantidadeTreinosPorDiaSemanaDto;
import school.sptech.loginormyou2up.dto.usuario.*;
import school.sptech.loginormyou2up.repository.LocalTreinoUsuarioRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.service.avaliacao.AvaliacaoService;
import school.sptech.loginormyou2up.service.extra.ListaObj;
import school.sptech.loginormyou2up.service.match.MatchService;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LocalTreinoUsuarioRepository localTreinoUsuarioRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private MatchService matchService;

    public UsuarioDtoRespostaCadastro criar(UsuarioDtoCriacao usuarioDtoCriacao) {
        if (usuarioRepository.findByEmail(usuarioDtoCriacao.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        Usuario novoUsuario = UsuarioMapper.convertToUsuario(usuarioDtoCriacao);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());

        novoUsuario.setSenha(senhaCriptografada);

        LocalTreinoUsuario localTreinoCadastrado = localTreinoUsuarioRepository.save(novoUsuario.getLocalTreino());
        novoUsuario.getLocalTreino().setIdLocalTreino(localTreinoCadastrado.getIdLocalTreino());

        return UsuarioMapper.convertToUsuarioDtoRespostaCadastro(usuarioRepository.save(novoUsuario));
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(usuarioLoginDto.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public List<UsuarioDtoResposta> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        //transformando a lista de usuários em uma lista de DTOs
        List<UsuarioDtoResposta> listaRetorno = usuarios.stream().map(UsuarioMapper::convertToDtoResposta).toList();


        //setando a média em todos os usuários
        listaRetorno.forEach(usuarioDtoResposta -> {
            usuarioDtoResposta.setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(usuarioDtoResposta.getId()));
        });

        //adicionando os matches em todos os usuários
        listaRetorno = listaRetorno.stream().map(this::adicionaMatches).toList();

        return listaRetorno;
    }

    public QuantidadeTreinosPorDiaSemanaDto getQuantidadeTreinosPorDiaSemana(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        List<TreinoHasUsuario> treinos = usuarioOpt.get().getTreinos();
        List<LocalDateTime> horarioTreinos = treinos.stream().map(TreinoHasUsuario::getInicioTreino).toList();
        List<DayOfWeek> diasDaSemana = horarioTreinos.stream().map(LocalDateTime::getDayOfWeek).toList();

        QuantidadeTreinosPorDiaSemanaDto dto = new QuantidadeTreinosPorDiaSemanaDto();

        for (DayOfWeek dia : diasDaSemana) {
            switch (dia) {
                case SUNDAY -> dto.incrementaDomingo();
                case MONDAY -> dto.incrementaSegunda();
                case TUESDAY -> dto.incrementaTerca();
                case WEDNESDAY -> dto.incrementaQuarta();
                case THURSDAY -> dto.incrementaQuinta();
                case FRIDAY -> dto.incrementaSexta();
                case SATURDAY -> dto.incrementaSabado();
            }
        }

        return dto;
    }

    public UsuarioDtoResposta getById(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UsuarioDtoResposta dto = UsuarioMapper.convertToDtoResposta(usuarioOpt.get());
        dto.setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(id));

        dto = adicionaMatches(dto);

        return dto;
    }

    public void deleteById(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        usuarioRepository.deleteById(id);

    }


    public UsuarioDtoResposta putById(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            return UsuarioMapper.convertToDtoResposta(usuarioRepository.save(usuario));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ListaObj<UsuarioDtoResposta> menorParaMaior() {
        if (usuarioRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            List<Usuario> lista = usuarioRepository.findAll();
            ListaObj<UsuarioDtoResposta> listaUser = new ListaObj<>(lista.size());

            for (int i = 0; i < lista.size(); i++) {
                listaUser.adicionaNoIndice(UsuarioMapper.convertToDtoResposta(lista.get(i)), i);
            }

            listaUser = bubbleSortNota(listaUser);

            for (int i = 0; i < listaUser.getTamanho(); i++) {
                listaUser.getElemento(i).setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(listaUser.getElemento(i).getId()));
            }

            return listaUser;
        }
    }

    public ListaObj<UsuarioDtoResposta> buscarPorNota(Double nota) {
        if (usuarioRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<Usuario> lista = usuarioRepository.findAll();
        ListaObj<UsuarioDtoResposta> listaUser = new ListaObj<>(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            listaUser.adicionaNoIndice(UsuarioMapper.convertToDtoResposta(lista.get(i)), i);
        }

        listaUser = bubbleSortNota(listaUser);

        ListaObj<UsuarioDtoResposta> encontrados = new ListaObj<>(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            UsuarioDtoResposta encontrado = pesquisaBinariaPorNota(listaUser, nota);
            if (encontrado != null) {
                encontrados.adicionaNoIndice(encontrado, encontrados.getTamanho());
            }

        }

        ListaObj<UsuarioDtoResposta> newEncontrados = new ListaObj<>(encontrados.getTamanho());
        // nova lista para não haver retorno com vários nulls no json

        for (int i = 0; i < encontrados.getTamanho(); i++) {
            newEncontrados.adicionaNoIndice(encontrados.getElemento(i), i);
        }


        if (newEncontrados.getTamanho() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return newEncontrados;
        }

    }

    public UsuarioDtoResposta adicionaMatches(UsuarioDtoResposta usuarioDtoResposta) {
        try {
            usuarioDtoResposta.setMatches(matchService.getByIdUsuario(usuarioDtoResposta.getId()));
        } catch (ResponseStatusException e) {
            usuarioDtoResposta.setMatches(new ArrayList<>());
        }

        return usuarioDtoResposta;
    }

    public ListaObj<UsuarioDtoResposta> adicionaMatches(ListaObj<UsuarioDtoResposta> lista) {
        for (int i = 0; i < lista.getTamanho(); i++) {
            try {
                lista.getElemento(i).setMatches(matchService.getByIdUsuario(lista.getElemento(i).getId()));
            } catch (ResponseStatusException e) {
                lista.getElemento(i).setMatches(new ArrayList<>());
            }
        }

        return lista;
    }

    public ListaObj<UsuarioDtoResposta> bubbleSortNota(ListaObj<UsuarioDtoResposta> lista) {
        ListaObj<UsuarioDtoResposta> userList = lista;
        userList = adicionaMedias(userList);
        userList = adicionaMatches(userList);

        for (int i = 0; i < userList.getTamanho(); i++) {
            for (int j = 1; j < userList.getTamanho(); j++) {
                if (userList.getElemento(j - 1).getNotaMedia() > userList.getElemento(j).getNotaMedia()) {
                    UsuarioDtoResposta aux = userList.getElemento(j);
                    userList.adicionaNoIndice(userList.getElemento(j - 1), j);
                    userList.adicionaNoIndice(aux, j - 1);
                }
            }
        }

        return userList;
    }

    private ListaObj<UsuarioDtoResposta> adicionaMedias(ListaObj<UsuarioDtoResposta> lista) {
        for (int i = 0; i < lista.getTamanho(); i++) {
            lista.getElemento(i).setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(lista.getElemento(i).getId()));
        }

        return lista;
    }

    public UsuarioDtoResposta pesquisaBinariaPorNota(ListaObj<UsuarioDtoResposta> lista, Double nota) {

        int inicio = 0;
        int fim = lista.getTamanho() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (nota.equals(lista.getElemento(meio).getNotaMedia())) {
                UsuarioDtoResposta encontrado = lista.getElemento(meio);
                lista.removePeloIndice(meio);
                return encontrado;


            } else if (nota > lista.getElemento(meio).getNotaMedia()) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return null;
    }

    public void exibeRecursivo(List<UsuarioDtoResposta> userLists, int tamanho){
        if (tamanho == userLists.size() -1) {
            UsuarioDtoResposta user = userLists.get(tamanho);
            System.out.println(user);
        } else {
            UsuarioDtoResposta user = userLists.get(tamanho);
            System.out.println(user);
            System.out.println("=".repeat(50));
            tamanho++;
            exibeRecursivo(userLists, tamanho);
        }
    }

    public ResponseEntity<UsuarioDtoResposta> buscarPorIdRecursivo(Integer atual, long total, Integer id){
        if (atual >= total) {
            return ResponseEntity.status(204).build();
        } else {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(atual + 1);
            if (usuarioOptional.get().getId() == id) {
                UsuarioDtoResposta userDtoResposta = UsuarioMapper.convertToDtoResposta(usuarioOptional.get());
                return ResponseEntity.status(200).body(userDtoResposta);
            } else {
                return buscarPorIdRecursivo(atual + 1, total, id);
            }
        }
    }

    public String postFotoPerfil(int id, String link, String token) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        //adiciona o token ao link pois o link original interpreta
        // o caracter & como um novo parâmetro

        StringBuffer linkTranformacao = new StringBuffer(link + "&token=" + token);

        int contagemBarras = 0;

        for (int i = 0; i < linkTranformacao.length(); i++) {
            if (linkTranformacao.charAt(i) == '/') {
                contagemBarras++;
            }

            //localiza a oitava barra que era pra ser %2F e a substitui pelo caracter necessário
            if (contagemBarras == 8) {
                linkTranformacao.delete(i, i+1);
                linkTranformacao.insert(i, "%2F");
                break;
            }
        }

        String newLink = linkTranformacao.toString();

        Usuario usuario = usuarioOpt.get();
        usuario.setFotoPerfil(newLink);

        usuarioRepository.save(usuario);

        return newLink;
    }



    public static void gravaRegistro(String nomeArq, String registro) {
        BufferedWriter saida = null;

        // Abre o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo!");
            erro.printStackTrace();
        }

        // Grava o registro e finaliza
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar ou fechar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(String nomeArq) {
        int contaRegistroDado = 0;
        List<UsuarioDtoCriacao> lista = new ArrayList<>();

        lista.add(new UsuarioDtoCriacao(1, "João da Silva", "joao@email.com", "senha12345", LocalDate.parse("1990-01-01"), "Usuário 1", "Iniciante", "Academia A"));
        lista.add(new UsuarioDtoCriacao(2, "Maria Souza", "maria@email.com", "123senha", LocalDate.parse("1985-12-03"), "Usuário 2", "Intermediário", "Academia B"));
        lista.add(new UsuarioDtoCriacao(3, "Pedro Almeida", "pedro@email.com", "teste123", LocalDate.parse("1998-09-15"), "Usuário 3", "Avançado", "Academia C"));
        lista.add(new UsuarioDtoCriacao(4, "Ana Pereira", "ana@email.com", "minhasenha", LocalDate.parse("2000-11-30"), "Usuário 4", "Iniciante", "Academia A"));
        lista.add(new UsuarioDtoCriacao(5, "Rafael Oliveira", "rafael@email.com", "123456", LocalDate.parse("1995-04-20"), "Usuário 5", "Iniciante", "Academia D"));
        lista.add(new UsuarioDtoCriacao(6, "Camila Santos", "camila@email.com", "senha123", LocalDate.parse("2003-04-10"), "Usuário 6", "Intermediário", "Academia B"));
        lista.add(new UsuarioDtoCriacao(7, "Lucas Costa", "lucas@email.com", "teste456", LocalDate.parse("1993-01-05"), "Usuário 7", "Avançado", "Academia C"));
        lista.add(new UsuarioDtoCriacao(8, "Juliana Rodrigues", "juliana@email.com", "minhasenha", LocalDate.parse("1997-10-27"), "Usuário 8", "Iniciante", "Academia A"));
        lista.add(new UsuarioDtoCriacao(9, "Gustavo Lima", "gustavo@email.com", "123456789", LocalDate.parse("1992-12-15"), "Usuário 9", "Iniciante", "Academia D"));
        lista.add(new UsuarioDtoCriacao(10, "Vanessa Ferreira", "vanessa@email.com", "senhaabc", LocalDate.parse("1990-05-22"), "Usuário 10", "Intermediário", "Academia B"));

        // Monta o registro de header
        String header = "00USUARIOS";
        header+= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header+= "01";

        // Grava o registro de header
        gravaRegistro(nomeArq, header);

        // Monta e grava o corpo (registros de dados)
        for (UsuarioDtoCriacao a : lista) {
            String corpo = "02";
            corpo += String.format("%2d",a.getId());
            corpo += String.format("%-50.50s",a.getNome());
            corpo += String.format("%-50.50s",a.getEmail());
            corpo += String.format("%-50.50s",a.getSenha());
            corpo += String.format("%-10.10s",a.getDataNascimento());
            corpo += String.format("%-50.50s",a.getDescricao());
            corpo += String.format("%-20.20s",a.getEstagio());
            corpo += String.format("%-50.50s",a.getAcademia());
            gravaRegistro(nomeArq, corpo);
            contaRegistroDado++;

        }

        // Monta o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegistroDado);

        // Grava o registro de trailer
        gravaRegistro(nomeArq, trailer);
    }


    public void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        int id;
        String nome, email, senha, descricao, estagio, academia;
        int contaRegDadosLidos = 0;
        int contaRegDadosGravados = 0;

        // Cria uma listaLida para armazenar os dados lidos dos alunos
        List<UsuarioDtoCriacao> listaLida = new ArrayList<>();

        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
            erro.printStackTrace();
        }

        // Leitura do arquivo
        try {
            registro = entrada.readLine();

            while (registro != null) { // enqto nao chegou ao final do arquivo
                // obtem os 2 primeiros caracteres do registro, que informa
                // qual eh o tipo de registro
                // substring recebe o indice inicial da substring desejada
                // e recebe como 2o argumento o indice final + 1 da substring desejada
                tipoRegistro = registro.substring(0,2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("eh um registro de header");
                    System.out.println("Tipo do arquivo: " + registro.substring(2,6));
                    System.out.println("Ano e semestre: " + registro.substring(6,11));
                    System.out.println("Data e hora de gravacao: " + registro.substring(11,30));
                    System.out.println("Versao do documento de layout: " + registro.substring(30,31));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("eh um registro de trailer");
                    contaRegDadosGravados = Integer.parseInt(registro.substring(2,12));
                    if (contaRegDadosGravados == contaRegDadosLidos) {
                        System.out.println("Quantidade de registros de dados lidos eh compativel com " +
                                "quantidade de registros de dados gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros de dados lidos eh incompativel com " +
                                "quantidade de registros de dados gravados");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("eh um registro de dados");
                    nome = registro.substring(4, 54).trim();
                    email = registro.substring(54, 104).trim();
                    senha = registro.substring(104, 154).trim();
                    String dataNascimentoStr = registro.substring(154, 164).trim();
                    descricao = registro.substring(164, 214).trim();
                    estagio = registro.substring(214, 234).trim();
                    academia = registro.substring(234, 284).trim();
                    System.out.println(dataNascimentoStr);
                    // Ajuste para formatar a data corretamente
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate dataNascimento = null;
                    try {
                        dataNascimento = LocalDate.parse(dataNascimentoStr, dateFormatter);

                    } catch (Exception e) {
                        System.out.println("Erro ao fazer parsing da data de nascimento: " + e.getMessage());



                        // Trate o erro de forma adequada para a sua aplicação.
                    }

                    contaRegDadosLidos++;


                    LocalTreinoCriacaoDto localTreino;
                    localTreino = new LocalTreinoCriacaoDto("You2Up","Rua da Academia",123,"Bairro da Academia","Cidade da Academia","UF",true);

                    // Cria um objeto Aluno com os dados lidos
                    UsuarioDtoCriacao a = new UsuarioDtoCriacao(nome,email,senha,dataNascimento,descricao,estagio,academia);

                    a.setLocalTreino(localTreino);
                    // Para importar no banco de dados, pode-se fazer:
                    criar(a);

                    // No nosso caso, como não estamos conectados a Banco de Dados,
                    // vamos adicionar o aluno a na listaLida
                    listaLida.add(a);

                }
                else {
                    System.out.println("tipo de registro invalido");
                }
                // Le o proximo registro
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        // Printa a lista lida do arquivo
        System.out.println("\nDados lidos do arquivo:");
        for (UsuarioDtoCriacao a : listaLida) {
            System.out.println(a.toString());
        }

        // Nesse ponto, tb seria possivel fazer um repository.saveAll() para salvar a lista
        // toda no banco de dados
    }
}
