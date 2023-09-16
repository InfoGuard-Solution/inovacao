package entities;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Registros {

    public Integer CpuTemp(){

        Integer temperatura = ThreadLocalRandom.current().nextInt(100);

        if (temperatura > 80) {
            //JOptionPane.showMessageDialog(null, String.format("Temperatura da CPU muito alta %d", temperatura));
        }
        return  temperatura;
    }

    public Integer CpuFrequencia(){

        Integer frequencia = ThreadLocalRandom.current().nextInt(100);

        if (frequencia > 90) {
          //  JOptionPane.showMessageDialog(null, String.format("Frequencia da CPU muito alta %d", frequencia));
        }
        return  frequencia;
    }

    public Integer GpuTemp(){

        Integer temperatura = ThreadLocalRandom.current().nextInt(100);

        if (temperatura > 80) {
          //  JOptionPane.showMessageDialog(null, String.format("Temperatura da GPU muito alta %d", temperatura));
        }
        return  temperatura;
    }

    public Integer GpuFrequencia(){

        Integer frequencia = ThreadLocalRandom.current().nextInt(100);

        if (frequencia > 90) {
          //  JOptionPane.showMessageDialog(null, String.format("Frequencia da GPU muito alta %d", frequencia));
        }
        return  frequencia;
    }
}
