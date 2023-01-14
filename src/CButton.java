import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;

// CButton a.k.a CustomButton
public class CButton extends JButton {
    
    public CButton(AbstractAction action, Font font){

        super(action);

        this.setFont(font);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        this.setFocusable(false);
        this.setIconTextGap(16);
        this.setBackground(Palette.ALABASTER);
        this.setForeground(Palette.TEXT_CLR);

    }

}