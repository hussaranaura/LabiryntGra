package stachugame.ui;

import stachugame.Main;
import stachugame.api.IGame;
import stachugame.implementation.items.potions.HealthPotion;
import stachugame.implementation.util.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;

/**
 * Główny panel gry
 */
public class GamePanel extends JPanel {
    MapPanel mapPanel;
    HealthPanel healthPanel;

    public GamePanel() throws UnsupportedEncodingException {
        setLayout(null);

        setSize(710, 600);
        setPreferredSize(getSize());
        setBorder(BorderFactory.createEtchedBorder());


        //Dodawanie widoku konsoli
        JTextArea area = new JTextArea(20, 50);
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setFont(Main.monocraft_12_3);

        IGame.getInstance().setOut(new JTextAreaOutputStream(area));


        JScrollPane outputPane = new JScrollPane(area);
        outputPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension outputPanePreferredSize = outputPane.getPreferredSize();
        outputPane.setBounds(10, 5, outputPanePreferredSize.width, outputPanePreferredSize.height);

        add(outputPane);

        //Dodanie tła mapy.
        this.mapPanel = new MapPanel();
        //mapCanvas = new MapCanvas(ImageCache.get("grid"));
        //mapPanel.add(mapCanvas);
        mapPanel.setPreferredSize(new Dimension(200, 200));
        Dimension mapPanelPreferredSize = mapPanel.getPreferredSize();
        add(mapPanel);
        mapPanel.setBounds(outputPanePreferredSize.width+20, 0, mapPanelPreferredSize.width, mapPanelPreferredSize.height);

        //Dodanie tła mapy.
        this.healthPanel = new HealthPanel();
        healthPanel.setPreferredSize(new Dimension(223, 90));
        Dimension healthPanelPreferredSize = healthPanel.getPreferredSize();
        add(healthPanel);
        healthPanel.setBounds(outputPanePreferredSize.width+20, 220, healthPanelPreferredSize.width, healthPanelPreferredSize.height);



        //Dodanie miejsca na wysyłanie poleceń
        JTextField inputField = new JTextField();
        inputField.setBounds(10, 5+outputPanePreferredSize.height, outputPanePreferredSize.width, inputField.getPreferredSize().height);
        inputField.setForeground(Color.WHITE);
        inputField.setBackground(Color.BLACK);
        inputField.setFont(Main.monocraft_12_3);
        add(inputField);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    SwingUtilities.invokeLater(()->{
                        String text = inputField.getText();
                        inputField.setText(null);

                        area.append("> "+text+"\n\n");

                        IGame.getInstance().processCommand(text);

                        GamePanel.this.mapPanel.repaint();
                        GamePanel.this.healthPanel.repaint();

                    });
                }
            }
        });

        setVisible(true);

    }
}
