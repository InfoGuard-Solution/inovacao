package entities;

import registros.CrudOverlay;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrosOverlay {

    CrudOverlay consultaOverlay = new CrudOverlay();

    public String data() {

        DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime hora = LocalDateTime.now();

        return formatadorDataHora.format(hora);
    }

    public Integer cpuTemp() {

        // primeiro valor da lista vindo do banco
        Integer temperatura = consultaOverlay.dados().get(0);

        return temperatura;
    }

    public Integer cpuFrequencia() {

        // pegamos o segundo valor da lista o qual se remete a frequencia
        Integer frequencia = consultaOverlay.dados().get(1);


        return frequencia;
    }

    public Integer gpuTemp() {

        Integer temperatura = consultaOverlay.dados().get(2);


        return temperatura;
    }

    public Integer gpuFrequencia() {

        Integer frequencia = consultaOverlay.dados().get(3);

        return frequencia;
    }

    public Integer redeLatencia() {

        Integer latencia = consultaOverlay.dados().get(4);

        return latencia;
    }

    public Integer ram() {

        Integer ram = consultaOverlay.dados().get(5);

        return ram;
    }

    public Integer disco() {

        Integer disco = consultaOverlay.dados().get(6);

        return disco;
    }
}
