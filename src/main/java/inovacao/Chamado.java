package inovacao;

import java.time.LocalDateTime;
import java.util.Date;

public class Chamado {

    private String apelidoPc;
    private LocalDateTime hora;
    private String descricao;
    private String status;

    public Chamado(String apelidoPc, LocalDateTime hora, String descricao) {
        this.apelidoPc = apelidoPc;
        this.hora = hora;
        this.descricao = descricao;
        this.status = "Pendente";
    }

    public String getApelidoPc() {
        return apelidoPc;
    }

    public void setApelidoPc(String apelidoPc) {
        this.apelidoPc = apelidoPc;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
