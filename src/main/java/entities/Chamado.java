package entities;

import java.time.LocalDateTime;

public class Chamado {

    private String problema;
    private String hora;

    public Chamado(String problema, String hora) {
        this.problema = problema;
        this.hora = hora;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
