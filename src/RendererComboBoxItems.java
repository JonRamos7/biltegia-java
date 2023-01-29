import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class RendererComboBoxItems extends DefaultListCellRenderer {
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        this.setBackground((isSelected) ? Palette.LIST_ITEM_ACTIVE : Palette.LIST_ITEM_BG);

        this.setFont(FontLibrary.FONT_R16);
        this.setBorder(null);
        this.setFocusable(false);

        return this;
    }

}
