import java.awt.Cursor;
import java.awt.Font; 

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

// CButton a.k.a CustomButton
public class CButton extends JButton {

    final Border PRIMARY_INNER_BORDER = BorderFactory.createEmptyBorder(8, 36, 8, 36);
    final Border SECONDARY_INNER_BORDER = PRIMARY_INNER_BORDER;
    final Border ICONTEXT_INNER_BORDER = BorderFactory.createEmptyBorder(12, 16, 12, 16);
    
    public CButton(AbstractAction action, String variant){

        super(action);

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setOpaque(true);
        this.setFocusable(false);

        Border border = PRIMARY_INNER_BORDER;

        switch (variant) {
            
            case "Primary":
                
                border = PRIMARY_INNER_BORDER;

                this.setFont(FontLibrary.FONT_M16);
                this.setBackground(Palette.BTN_PRIMARY_BG);
                this.setForeground(Palette.BTN_PRIMARY_FG);

            break;

            case "Secondary":

                Border line = BorderFactory.createLineBorder(Palette.BTN_BORDER_CLR, 1, false);
                Border compound = BorderFactory.createCompoundBorder(line, SECONDARY_INNER_BORDER);
        
                border = compound;
                
                this.setFont(FontLibrary.FONT_M16);
                this.setBackground(Palette.BTN_SECONDARY_BG);
                this.setForeground(Palette.BTN_SECONDARY_FG);

            break;

            case "Icon&Text":
                
                border = ICONTEXT_INNER_BORDER;

                this.setFont(FontLibrary.FONT_B16);
                this.setIconTextGap(16);
                this.setBackground(Palette.BTN_SECONDARY_BG);
                this.setForeground(Palette.BTN_SECONDARY_FG);


            break;

            case "IconOnly":

                border = null;
                this.setBackground(null);

            break;
        
            default: break;

        }

        this.setBorder(border);

    }

    public CButton(AbstractAction action){

        this(action, "Primary");

    }

}