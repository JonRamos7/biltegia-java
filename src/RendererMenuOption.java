import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;

public class RendererMenuOption implements ListCellRenderer<String> {

    final ImageIcon ICON = new ImageIcon("../lib/icons/chevron_right.png");

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {

        JToolBar panel = new JToolBar();
        panel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        panel.setFloatable(false);
        panel.setBackground(Palette.CPANEL_BG);

        JLabel label = new JLabel(value);
        JLabel icon = new JLabel(ICON);

        panel.add(label);
        panel.add(Box.createHorizontalGlue());
        panel.add(icon);

        return panel;

    }
    


}
