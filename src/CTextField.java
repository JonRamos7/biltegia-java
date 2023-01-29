import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.*;

public class CTextField extends JTextField {
    
    public CTextField(String placeholderText, String variant){

        super(21);

        Border outer = new LineBorder(Palette.DAWN_PINK, 1, true);
		Border inner = new EmptyBorder(8, 8, 8, 8);
		Border border = new CompoundBorder(outer, inner);

        if (placeholderText != null) {
            
            TextPrompt placeholder = new TextPrompt(placeholderText, this);
            placeholder.setFont(FontLibrary.FONT_R16);
            placeholder.setForeground(Palette.SILVER);
            placeholder.changeAlpha(0.75f);

        }

        this.setFont(FontLibrary.FONT_R16);
		this.setBorder(border);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);

        switch (variant) {

            case "default":
            
                this.setForeground(Palette.TEXT_CLR);
                
            break;

            case "disabled":

                this.setText("997849740");
                this.setEnabled(false);
                this.setBackground(Palette.TEXTFIELD_DISABLED_BG);
                this.setForeground(Palette.BANANA_YELLOW);

            break;
        
            default:
                break;
        }


    }

    public CTextField(String variant){

        this(null, variant);

    }

}
