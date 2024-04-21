package stachugame.ui;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class JTextAreaOutputStream extends OutputStream {

    private final JTextArea area;

    public JTextAreaOutputStream(JTextArea area){
        this.area = area;
    }
    @Override
    public void write(int b) throws IOException {
        if(area == null)
            return;
        SwingUtilities.invokeLater(() -> {
            area.append(String.valueOf((char) b));
        });
    }
}
