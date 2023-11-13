package telas;

import entities.DadosLooca;
import entities.Imagens;
import entities.RegistrosOverlay;
import entities.util.HardwareType;
import entities.util.LogLevel;
import entities.util.LogManager;
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
    private JLabel disco;
    private JLabel latencia;
    private JLabel ram;
    private JPanel REDE;
    private static String apelido;
    private static boolean verificador = true;
    private final LogManager logManager = new LogManager();

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
                estadoConexao();
                estadoRam();
                estadoDisco();
            }
        }, 0, 1000);
    }

    // estado conexao
    public void estadoConexao() {
        RegistrosOverlay registrosOverlay = new RegistrosOverlay();

        Integer lat = registrosOverlay.redeLatencia();

        latencia.setText(String.valueOf(lat));

        if (lat <= 20) {
            latencia.setForeground(Color.GREEN);
        } else if ((lat <= 50)) {
            latencia.setForeground(Color.orange);
            logManager.setLog("Latência da rede acima do ideal", LogLevel.AVISO, HardwareType.REDE, lat.toString());
        } else {
            latencia.setForeground(Color.RED);
            logManager.setLog("limite de latência da rede alcançado", LogLevel.PERIGO, HardwareType.REDE, lat.toString());
        }
    }

    // validação para estado da ram
    public void estadoRam() {
        RegistrosOverlay registrosOverlay = new RegistrosOverlay();

        // pegando o valor do return e colocando em uma variavel
        Integer ram = registrosOverlay.ram();

        // plotar valores na label
        this.ram.setText(String.valueOf(ram));

        if (ram < 70) {
            // Estou importando o jlabel para que possa ser trocado sua cor
            this.ram.setForeground(Color.GREEN);

        } else if (ram < 90) {

            this.ram.setForeground(Color.ORANGE);
            logManager.setLog("uso de RAM fora do ideal", LogLevel.AVISO, HardwareType.RAM, ram.toString());

        } else {
            this.ram.setForeground(Color.RED);
            logManager.setLog("uso de RAM muito acima do ideal", LogLevel.PERIGO, HardwareType.RAM, ram.toString());
        }
    }


    // validação para estado do disco
    public void estadoDisco() {
        RegistrosOverlay registrosOverlay = new RegistrosOverlay();

        // pegando o valor do return e colocando em uma variavel
        Integer disco = registrosOverlay.disco();

        // plotar valores na label
        this.disco.setText(String.valueOf(disco));

        if (disco < 50) {
            // Estou importando o jlabel para que possa ser trocado sua cor
            this.disco.setForeground(Color.GREEN);

        } else if (disco < 85) {

            this.disco.setForeground(Color.ORANGE);
            logManager.setLog("uso do DISCO fora do ideal", LogLevel.AVISO, HardwareType.DISCO, disco.toString());

        } else {
            this.disco.setForeground(Color.RED);
            logManager.setLog("uso do DISCO muito acima do ideal", LogLevel.PERIGO, HardwareType.DISCO, disco.toString());
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
            logManager.setLog("Temperatura da CPU anormal", LogLevel.AVISO, HardwareType.CPU, temperatura.toString());

        } else {
            cpuTemp.setForeground(Color.RED);
            logManager.setLog("Temperatura da CPU muito acima do ideal", LogLevel.PERIGO, HardwareType.CPU, temperatura.toString());
        }

        if (frequencia < 30) {

            // Estou a importar o jlabel para poder ser trocado a sua cor
            cpuFreq.setForeground(Color.GREEN);

        } else if (frequencia < 70) {

            cpuFreq.setForeground(Color.ORANGE);
            logManager.setLog("Frequência da CPU anormal", LogLevel.AVISO, HardwareType.CPU, frequencia.toString());
        } else {
            cpuFreq.setForeground(Color.red);
            logManager.setLog("Frequência da CPU muito acima do ideal", LogLevel.PERIGO, HardwareType.CPU, frequencia.toString());
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
            logManager.setLog("Temperatura da GPU muito acima do ideal", LogLevel.PERIGO, HardwareType.GPU, temperatura.toString());


        } else {
            gpuTemp.setForeground(Color.RED);
            logManager.setLog("Temperatura da GPU muito acima do ideal", LogLevel.PERIGO, HardwareType.GPU, temperatura.toString());
        }

        if (frequencia < 30) {

            // Estou a importar o jlabel para poder ser trocado a sua cor
            gpuFreq.setForeground(Color.GREEN);

        } else if (frequencia < 70) {

            gpuFreq.setForeground(Color.ORANGE);
            logManager.setLog("Frequência da CPU anormal", LogLevel.AVISO, HardwareType.CPU, frequencia.toString());

        } else {
            gpuFreq.setForeground(Color.red);
            logManager.setLog("Frequência da GPU muito acima do ideal", LogLevel.PERIGO, HardwareType.GPU, frequencia.toString());
        }
    }

    public void Opcao() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    logManager.salvarLog(getApelido());
                }catch (NullPointerException e){

                }
            }
        }, 0, 6000);

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
                    try {
                        insert.InsertDados(dados);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 0, 6000);
        }
    }
}

