package school.sptech.loginormyou2up.dto.treino;

public class LocalTreinoCriacaoDto {
    private String nome;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean isAcademia;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean isAcademia() {
        return isAcademia;
    }

    public void setAcademia(boolean academia) {
        isAcademia = academia;
    }
}
