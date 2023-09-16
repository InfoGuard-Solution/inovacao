package telas;

import entities.Imagens;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class Button extends JFrame {
    private JPanel contentPane;
    private JLabel lupa;

    public Button() {
        setContentPane(contentPane);
        setUndecorated(true);
        setOpacity(0.7f);
        setBounds(5, 5, 60, 60);
        setAlwaysOnTop(true);
        setVisible(true);
        setShape(new RoundRectangle2D.Double(0, 0, 60, 60, 20, 20));

        Imagens img = new Imagens();
        lupa.setIcon(img.AddLupa());
        lupa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();

                Overlay navegar = new Overlay();
                navegar.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        new Button();

    }
}
