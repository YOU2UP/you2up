package school.sptech.loginormyou2up.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notificacao {
    @Id
    private Integer id;

    private String conteudo;

    @ManyToOne
    private Usuario usuario;

    private String descricao;

}
