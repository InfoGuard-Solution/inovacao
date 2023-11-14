package entities;

import javax.swing.*;

public class Imagens {

    ClassLoader cl = this.getClass().getClassLoader();
    public Icon AddFechar() {
        return new ImageIcon(cl.getResource("img/x.png"));
    }

    public Icon AddHelp() {

        //Adicionar help
        return new ImageIcon(cl.getResource("img/help.png"));
    }

    public Icon AddLupa() {

        return new ImageIcon(cl.getResource("img/lupa.png"));
    }
}
