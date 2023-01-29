import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class RendererNavigationList implements ListCellRenderer<JButton>{

    private int hoverIndex = -1;
    private final ImageIcon DEFAULT_ICON = new ImageIcon("../lib/icons/coffee.png");
    private final ImageIcon HOVER_ICON = new ImageIcon("../lib/icons/coffee_white.png");
    private final ImageIcon SELECTED_ICON = HOVER_ICON;

    public void setHoverIndex(int index) {
        hoverIndex = index;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends JButton> list, JButton button, int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            button.setBackground(Palette.BTN_BG_SELECTED);
            button.setForeground(Palette.BTN_FG_SELECTED);
            button.setIcon(SELECTED_ICON);
        }
        else if(index == hoverIndex){
            button.setBackground(Palette.BTN_BG_HOVER);
            button.setForeground(Palette.BTN_FG_HOVER);
            button.setIcon(HOVER_ICON);
        }
        else{
            button.setBackground(Palette.BTN_BG);
            button.setForeground(Palette.BTN_FG);
            button.setIcon(DEFAULT_ICON);
        }

        return button;

    }

}
