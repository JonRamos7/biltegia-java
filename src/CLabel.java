import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class CLabel extends JLabel {
    
    public CLabel(String text, Font font, Color color){

        super(text);

        this.setOpaque(false);
        this.setFont(font);
        this.setForeground(color);

    }

}
