package stachugame.ui;

import stachugame.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {

    MapCanvas mapCanvas;


    public GamePanel() throws IOException {
        setLayout(null);

        setSize(710, 600);
        setPreferredSize(getSize());
        setBorder(BorderFactory.createEtchedBorder());


        //Dodawanie widoku konsoli
        JTextArea area = new JTextArea(20, 50);
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setLineWrap(true);
        area.setFont(Main.monocraft_13);


        JScrollPane outputPane = new JScrollPane(area);
        outputPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension outputPanePreferredSize = outputPane.getPreferredSize();
        outputPane.setBounds(10, 5, outputPanePreferredSize.width, outputPanePreferredSize.height);

        add(outputPane);

        //Dodanie tła mapy.
        JPanel mapPanel = new JPanel();

        mapCanvas = new MapCanvas(ImageIO.read(getClass().getResource("/grid.png")));
        mapPanel.add(mapCanvas);
        Dimension mapPanelPreferredSize = mapPanel.getPreferredSize();
        add(mapPanel);
        mapPanel.setBounds(outputPanePreferredSize.width+20, 0, mapPanelPreferredSize.width, mapPanelPreferredSize.height);

        setVisible(true);

    }
}
