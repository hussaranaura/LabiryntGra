package stachugame.ui;

import stachugame.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {

    public GamePanel() throws IOException {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        GridBagConstraints c;

//        setTitle("Magnaci i Czarodzieje - Legenda Jaboli");
        setSize(800, 600);
        setPreferredSize(getSize());
//        setResizable(false);
//        setLayout(new FlowLayout());
        setBorder(BorderFactory.createEtchedBorder());


        //Dodawanie widoku konsoli
        JTextArea area = new JTextArea(20, 50);
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setLineWrap(true);
        area.setFont(Main.monocraft_13);


        JScrollPane pane = new JScrollPane(area);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension panePD = pane.getPreferredSize();
        pane.setBounds(0, 0, panePD.width, panePD.height);

        add(pane);

        //Dodanie tła mapy.
        JPanel p = new JPanel();
        p.setSize(200, 200);
        p.setMaximumSize(p.getSize());
        p.setMinimumSize(p.getSize());
        p.add(new MapCanvas(ImageIO.read(getClass().getResource("/grid.png"))));
        Dimension pPD = p.getPreferredSize();
        add(p);
        p.setBounds(panePD.width+5, 0, p.getWidth(), p.getHeight());

        setVisible(true);

        System.out.println(area.getSize());
    }
}
