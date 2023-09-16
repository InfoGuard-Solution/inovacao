package telas;

import entities.Imagens;
import entities.Registros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

public class Overlay extends JFrame {
    private JPanel contentPane;
    private JPanel opcao;
    private JLabel lupa;
    private JLabel help;
    private JLabel fechar;
    private JPanel cpu_dados;
    private JPanel gpu_dados;
    public JLabel cpuTemp;
    public JLabel cpuFreq;
    public JLabel gpuFreq;
    public JLabel gpuTemp;
    private JLabel data;
    private JLabel estado;
    private JLabel latencia;
    private JLabel pacote;
    private JPanel REDE;


    public void ExibirValores() {
        Registros registros = new Registros();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                data.setText((String.valueOf(registros.data())));

                estadoCpu();
                estadoGpu();

                //estado conexao ja valida os dois abaixo
                estadoConexao();
                latencia.setText(String.valueOf(registros.redeLatencia()));
                pacote.setText(String.valueOf(registros.redePacote()));

            }
        }, 0, 1000);
    }

    // validação para saber o estado de rede
    public void estadoConexao() {
        Registros registros = new Registros();

        Integer lat = registros.redeLatencia();
        Integer pac = registros.redePacote();

        if ((lat <= 20) && (pac <= 2)) {
            estado.setText("Excelente");
            estado.setForeground(Color.GREEN);
        } else if ((lat <= 50) && (pac <= 10)) {
            estado.setText("Mais ou menos");
            estado.setForeground(Color.orange);
        } else {
            estado.setText("Ruim");
            estado.setForeground(Color.RED);
        }

        if (lat <= 20) {
            latencia.setForeground(Color.GREEN);
        } else if ((lat <= 50) && (pac <= 10)) {
            latencia.setForeground(Color.orange);
        } else {
            latencia.setForeground(Color.RED);
        }

        if ((pac <= 2)) {

            pacote.setForeground(Color.GREEN);
        } else if ((pac <= 10)) {

            pacote.setForeground(Color.orange);
        } else {
            pacote.setForeground(Color.RED);
        }
    }

    // validação para estado da cpu
    public void estadoCpu() {
        Registros registros = new Registros();

        // pegando o valor do return e colocando em uma variavel
        Integer temperatura = registros.cpuTemp();
        Integer frequencia = registros.cpuFrequencia();

        // plotar valores na label
        cpuTemp.setText(String.valueOf(temperatura));
        cpuFreq.setText(String.valueOf(frequencia));

        if (temperatura < 40) {

            // Estou importando o jlabel para que possa ser trocado sua cor
            cpuTemp.setForeground(Color.GREEN);

        } else if (temperatura < 80) {

            cpuTemp.setForeground(Color.ORANGE);

        } else {
            cpuTemp.setForeground(Color.RED);
            //  JOptionPane.showMessageDialog(null, String.format("Temperatura da CPU muito alta %d", temperatura));
        }

        if (frequencia < 30) {

            // Estou a importar o jlabel para poder ser trocado a sua cor
            cpuFreq.setForeground(Color.GREEN);

        } else if (frequencia < 70) {

            cpuFreq.setForeground(Color.ORANGE);

        } else {
            cpuFreq.setForeground(Color.red);
        }
    }

    // validação para estado da gpu
    public void estadoGpu() {
        Registros registros = new Registros();

        Integer temperatura = registros.gpuTemp();
        Integer frequencia = registros.gpuFrequencia();

        gpuTemp.setText(String.valueOf(temperatura));
        gpuFreq.setText(String.valueOf(frequencia));
        if (temperatura < 40) {

            // Estou importando o jlabel para que possa ser trocado sua cor
            gpuTemp.setForeground(Color.GREEN);

        } else if (temperatura < 80) {

            gpuTemp.setForeground(Color.ORANGE);

        } else {
            gpuTemp.setForeground(Color.RED);
        }

        if (frequencia < 30) {

            // Estou a importar o jlabel para poder ser trocado a sua cor
            gpuFreq.setForeground(Color.GREEN);

        } else if (frequencia < 70) {

            gpuFreq.setForeground(Color.ORANGE);

        } else {
            gpuFreq.setForeground(Color.red);
        }
    }

    public void Opcao() {
        setBackground(Color.green);
        // adicionar imagens
        Imagens img = new Imagens();
        lupa.setIcon(img.AddLupa());
        fechar.setIcon(img.AddFechar());
        help.setIcon(img.AddHelp());
    }

    // principal
    public Overlay() {
        setContentPane(contentPane);
        //Classes
        Opcao();
        ExibirValores();

        // formatação do painel
        setUndecorated(true);
        setOpacity(0.7f);
        setBounds(5, 5, 240, 280);
        setAlwaysOnTop(true);
        setVisible(true);
        setShape(new RoundRectangle2D.Double(0, 0, 240, 280, 20, 20));

        lupa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                Button navegar = new Button();
                navegar.setVisible(true);
            }
        });
        fechar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }

    public static void main(String[] args) {

        new Overlay();
    }
}
