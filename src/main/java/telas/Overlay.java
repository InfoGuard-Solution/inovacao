package telas;

import entities.DadosLooca;
import entities.Imagens;
import entities.RegistrosOverlay;
import registros.CrudChamado;
import registros.InsertLooca;

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
    private static String apelido;
    private static boolean verificador = true;

    public String getApelido() {
        return apelido;
    }

    public void ExibirValores() {
        RegistrosOverlay registrosOverlay = new RegistrosOverlay();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                data.setText((String.valueOf(registrosOverlay.data())));

                estadoCpu();
                estadoGpu();

                //estado conexao ja valida os dois abaixo
                estadoConexao();
                latencia.setText(String.valueOf(registrosOverlay.redeLatencia()));
                pacote.setText(String.valueOf(registrosOverlay.redePacote()));

            }
        }, 0, 1000);
    }

    // validação para saber o estado de rede
    public void estadoConexao() {
        RegistrosOverlay registrosOverlay = new RegistrosOverlay();

        Integer lat = registrosOverlay.redeLatencia();
        Integer pac = registrosOverlay.redePacote();

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
        RegistrosOverlay registrosOverlay = new RegistrosOverlay();

        // pegando o valor do return e colocando em uma variavel
        Integer temperatura = registrosOverlay.cpuTemp();
        Integer frequencia = registrosOverlay.cpuFrequencia();

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
        RegistrosOverlay registrosOverlay = new RegistrosOverlay();

        Integer temperatura = registrosOverlay.gpuTemp();
        Integer frequencia = registrosOverlay.gpuFrequencia();

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

        // formatação do painelPC
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

        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                TelaChamado navegar = new TelaChamado();
                navegar.setVisible(true);
            }
        });

        fechar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        CrudChamado consultaId = new CrudChamado();

        if (verificador) {
            InsertLooca insert = new InsertLooca();
            DadosLooca dados = new DadosLooca();

            verificador = false;
            apelido = JOptionPane.showInputDialog(null, "Digite o apelido dessa maquina");
            consultaId.pegarIdPc();

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    insert.InsertDados(dados);
                }
            }, 0, 3000);
        }
    }
}

