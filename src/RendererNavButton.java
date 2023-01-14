import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class RendererNavButton implements ListCellRenderer<JButton>{

    private int hoverIndex = -1;

    public void setHoverIndex(int index) {
        hoverIndex = index;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends JButton> list, JButton button, int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            button.setBackground(Palette.BTN_BG_SELECTED);
            button.setForeground(Palette.BTN_FG_SELECTED);
        }
        else if(index == hoverIndex){
            button.setBackground(Palette.BTN_BG_HOVER);
            button.setForeground(Palette.BTN_FG_HOVER);
        }
        else{
            button.setBackground(Palette.BTN_BG);
            button.setForeground(Palette.BTN_FG);
        }

        return button;

    }

}
