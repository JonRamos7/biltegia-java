import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class CButtonIcon extends JButton{
    
    public CButtonIcon(AbstractAction action){

        super(action);

        this.setBackground(null);
        this.setBorder(null);
        this.setFocusable(false);

    }

}
