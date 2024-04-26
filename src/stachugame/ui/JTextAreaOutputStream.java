package stachugame.ui;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class JTextAreaOutputStream extends OutputStream {

    private final JTextArea area;
    private String buffer;

    private byte[] bytes = new byte[2];
    private int index = 0;

    public JTextAreaOutputStream(JTextArea area){
        this.area = area;
    }
    @Override
    public void write(int b) throws IOException {
        if(area == null)
            return;
        String toAppend = "";

        if(b < 0){
            bytes[index] = (byte) b;
            index++;
        }else{
            toAppend = String.valueOf(((char) b));
            index = 0;
        }

        if(index == 2){
            toAppend = new String(bytes);
            index = 0;
        }
        String finalToAppend = toAppend;
        if(area.isVisible())
            SwingUtilities.invokeLater(() -> {

                area.append(finalToAppend);
            });
        else
            buffer += finalToAppend;

    }
}
