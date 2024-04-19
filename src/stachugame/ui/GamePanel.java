package stachugame.ui;

import stachugame.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {

    MapCanvas mapCanvas;

    public GamePanel() throws IOException {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        GridBagConstraints c;

//        setTitle("Magnaci i Czarodzieje - Legenda Jaboli");
        setSize(710, 600);
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
        pane.setBounds(10, 5, panePD.width, panePD.height);

        add(pane);

        //Dodanie tła mapy.
        JPanel p = new JPanel();

        mapCanvas = new MapCanvas(ImageIO.read(getClass().getResource("/grid.png")));
        p.add(mapCanvas);
        Dimension pPD = p.getPreferredSize();
        System.out.println(pPD.height);
        add(p);
        p.setBounds(panePD.width+20, 0, (int) pPD.getWidth(), (int) pPD.getHeight());

        System.out.println(panePD.width + 10+20 + pPD.width+5);

        setVisible(true);

        System.out.println(area.getSize());
    }
}
