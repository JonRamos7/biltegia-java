import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class RendererListItem implements ListCellRenderer<ListItem>{

    @Override
    public Component getListCellRendererComponent(JList<? extends ListItem> list, ListItem listItem, int index, boolean isSelected, boolean cellHasFocus) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(true);
        panel.setBackground((isSelected) ? Palette.LIST_ITEM_ACTIVE : Palette.LIST_ITEM_BG);

        JLabel itemImage = new JLabel(listItem.getImage());
        JPanel itemInfo = createItemInfo(listItem);

        panel.add(itemImage);
        panel.add(itemInfo);
        
        return panel;

    }

    private JPanel createItemInfo(ListItem listItem){

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(300, 62));
        panel.setOpaque(false);


        JLabel itemName = new CLabel(listItem.getName(), App.FONT_M14, Palette.TEXT_CLR);
        JPanel itemDetails = createItemDetails(listItem);


        panel.add(itemName);
        panel.add(Box.createVerticalGlue());
        panel.add(itemDetails);

        return panel;

    }

    private JPanel createItemDetails(ListItem listItem){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel quantity = createDetail("Kopurua", listItem.getQuantity());
        JPanel createdby = createDetail("Sortzailea", listItem.getCreatedby());
        JPanel data = createDetail("Data", listItem.getDate());

        panel.add(quantity);
        panel.add(Box.createRigidArea(new Dimension(32, 0)));
        panel.add(createdby);
        panel.add(Box.createRigidArea(new Dimension(32, 0)));
        panel.add(data);

        return panel;

    }

    private JPanel createDetail(String labelText, String valueText){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        // panel.setBackground(Palette.WHITE);

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JLabel label = new CLabel(labelText, App.FONT_M10, Palette.SILVER);
        JLabel value = new CLabel(valueText, App.FONT_SB12, Palette.TEXT_CLR);

        panel.add(label);
        panel.add(value);

        return panel;

    }
    
}
