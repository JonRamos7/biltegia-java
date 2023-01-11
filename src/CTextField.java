import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.*;

public class CTextField extends JTextField {
    
    public CTextField(String placeholderText, Font font, int cols){

        super(cols);

        Border outer = new LineBorder(Palette.DAWN_PINK, 1, true);
		Border inner = new EmptyBorder(8, 8, 8, 8);
		Border border = new CompoundBorder(outer, inner);

        TextPrompt placeholder = new TextPrompt(placeholderText, this);
		placeholder.setFont(font);
		placeholder.setForeground(Palette.SILVER);
		placeholder.changeAlpha(0.75f);

        this.setFont(font);
        this.setForeground(Palette.TEXT_CLR);
		this.setBorder(border);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);

    }

}
