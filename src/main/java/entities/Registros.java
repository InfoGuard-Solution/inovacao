package entities;

import inovacao.Consulta;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Registros {

    Consulta consulta = new Consulta();

    public String data() {


        DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

        LocalDateTime hora = consulta.tempo();

        return formatadorDataHora.format(hora);
    }

    public Integer cpuTemp() {

        // primeiro valor da lista vindo do banco
        Integer temperatura = consulta.dados().get(0);

        return temperatura;
    }

    public Integer cpuFrequencia() {

        // pegamos o segundo valor da lista o qual se remete a frequencia
        Integer frequencia = consulta.dados().get(1);


        return frequencia;
    }

    public Integer gpuTemp() {

        Integer temperatura = consulta.dados().get(2);


        return temperatura;
    }

    public Integer gpuFrequencia() {

        Integer frequencia = consulta.dados().get(3);

        return frequencia;
    }

    public Integer redeLatencia() {

        Integer latencia = consulta.dados().get(4);

        return latencia;
    }

    public Integer redePacote() {

        Integer pacote = consulta.dados().get(5);

        return pacote;
    }
}
