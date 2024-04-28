package stachugame;

import stachugame.api.IGame;
import stachugame.ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static Font monocraft_12_3;
    public static void main(String[] args) throws IOException, FontFormatException {

        monocraft_12_3 = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/monocraft.ttf"))
                .deriveFont(12.3f);

        JFrame frame = new JFrame("Magnaci i Czarodzieje - Legenda Jaboli");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new GamePanel();
        frame.setContentPane(content);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);


        IGame.getInstance().init();
    }
}
