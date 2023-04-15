package school.sptech.loginormyou2up.service.dto.treino;

public class TreinoDtoJsonUsuario { // essa classe mostra seu treino sem mostrar a lista de usuarios
    private Integer id;
    private String periodo;
    private String preferencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }
}
