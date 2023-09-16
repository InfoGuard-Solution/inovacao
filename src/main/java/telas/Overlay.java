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
    private JLabel cpuTemp;
    private JLabel cpuFreq;
    private JLabel gpuFreq;
    private JLabel gpuTemp;


    public void ExibirValores() {
        Registros registros = new Registros();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cpuTemp.setText(String.valueOf(registros.CpuTemp()));
                cpuFreq.setText(String.valueOf(registros.CpuFrequencia()));
                gpuTemp.setText(String.valueOf(registros.GpuTemp()));
                gpuFreq.setText(String.valueOf(registros.GpuFrequencia()));
            }
        }, 0, 1000);
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
        setBounds(5, 5, 250, 300);
        setAlwaysOnTop(true);
        setVisible(true);
        setShape(new RoundRectangle2D.Double(0, 0, 250, 250, 20, 20));

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
