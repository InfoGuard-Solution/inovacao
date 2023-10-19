package telas;

import entities.Imagens;
import registros.CrudChamado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDateTime;

public class TelaChamado extends JFrame {
    private JPanel contentPane;
    private JPanel opcao;
    private JPanel chamado;
    private JLabel lupa;
    private JLabel help;
    private JLabel fechar;
    private JTextField problema;
    private JButton enviar;


    public void Opcao() {
        setBackground(Color.green);
        // adicionar imagens
        Imagens img = new Imagens();
        lupa.setIcon(img.AddLupa());
        fechar.setIcon(img.AddFechar());
        help.setIcon(img.AddHelp());
    }

    // principal
    public TelaChamado() {
        setContentPane(contentPane);

        //Classes
        Opcao();

        // formatação do painel
        setUndecorated(true);
        setOpacity(0.7f);
        setBounds(5, 5, 240, 200);
        setAlwaysOnTop(true);
        setVisible(true);
        setShape(new RoundRectangle2D.Double(0, 0, 240, 200, 20, 20));

        lupa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                telas.Button navegar = new Button();
                navegar.setVisible(true);
            }
        });

        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                Overlay navegar = new Overlay();
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


        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Overlay apelido = new Overlay();
                CrudChamado chamado = new CrudChamado();
                apelido.dispose();

                LocalDateTime hora = LocalDateTime.now();
                String problem = problema.getText();

                if (problem.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Digite o que está acontecendo !!");
                    return;
                }

                chamado.InsertChamado(problem, String.valueOf(hora));
            }
        });
    }

    public static void main(String[] args) {
        new TelaChamado();
    }
}

