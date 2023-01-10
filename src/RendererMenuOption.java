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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        // panel.setFloatable(false);
        panel.setBackground(Palette.CPANEL_BG);

        JLabel label = new JLabel(value);
        label.setFont(App.FONT_M16);
        label.setForeground(Palette.TEXT_CLR);

        JLabel icon = new JLabel(ICON);

        panel.add(label);
        panel.add(Box.createHorizontalGlue());
        panel.add(icon);

        return panel;

    }
    


}
