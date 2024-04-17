package stachugame.ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        GridBagConstraints c;

        setTitle("Magnaci i Czarodzieje - Legenda Jaboli");
        setSize(800, 600);
        setResizable(false);
        setLayout(new GridBagLayout());


        //Dodawanie widoku konsoli
        JTextArea area = new JTextArea(20, 40);
        area.setBackground(new Color(0));
        area.setForeground(new Color(0xFFFFFF));
        area.setLineWrap(true);
        JScrollPane pane = new JScrollPane(area);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        add(pane, c);

        //Dodanie rysunku mapy.
        //@TODO

        setVisible(true);
    }
}
