import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class RendererListProduct implements ListCellRenderer<Product>{

    final int PRODUCT_IMAGE_WIDTH = 100;
    final int PRODUCT_IMAGE_HEIGHT = 100;


    @Override
    public Component getListCellRendererComponent(JList<? extends Product> list, Product product, int index, boolean isSelected, boolean cellHasFocus) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(true);
        panel.setBackground((isSelected) ? Palette.LIST_ITEM_ACTIVE : Palette.LIST_ITEM_BG);

        JLabel productImage = createItemImage(product.getImagePath()); // new JLabel(product.getImage());
        JPanel productInfo = createItemInfo(product);

        panel.add(productImage);
        panel.add(productInfo);
        
        return panel;

    }

    private JLabel createItemImage(String imagePath){

        JLabel label = new JLabel(new ImageIcon(imagePath));

        label.setPreferredSize(new Dimension(PRODUCT_IMAGE_WIDTH, PRODUCT_IMAGE_HEIGHT));

        return label;

    }

    private JPanel createItemInfo(Product product){

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(300, 62));
        panel.setOpaque(false);


        JLabel productName = new CLabel(product.getName(), FontLibrary.FONT_M14, Palette.TEXT_CLR);
        JPanel productDetails = createItemDetails(product);


        panel.add(productName);
        panel.add(Box.createVerticalGlue());
        panel.add(productDetails);

        return panel;

    }

    private JPanel createItemDetails(Product product){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel quantity = createDetail("Kopurua", Integer.toString(product.getQuantity()) + "/" + Integer.toString(product.getMaxQuantity()));
        JPanel createdby = createDetail("Sortzailea", product.getCreatedby());
        JPanel data = createDetail("Data", product.getDate());

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

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JLabel label = new CLabel(labelText, FontLibrary.FONT_M10, Palette.SILVER);
        JLabel value = new CLabel(valueText, FontLibrary.FONT_SB12, Palette.TEXT_CLR);

        panel.add(label);
        panel.add(value);

        return panel;

    }
    
}
