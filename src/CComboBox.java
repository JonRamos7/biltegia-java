import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.*;

public class CComboBox<K, V> extends JComboBox<V> {

    private Map<K, List<V>> items;

    public CComboBox(Map<K, List<V>> items) {

        this.items = items;

        for (K key : this.items.keySet()) {

            List<V> list = this.items.get(key);

            for (V item : list) {
                addItem(item);
            }

        }

        this.setRenderer(new RendererComboBoxItems());

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Border outer = new LineBorder(Palette.DAWN_PINK, 1, true);
		Border inner = new EmptyBorder(8, 8, 8, 8);
		Border border = new CompoundBorder(outer, inner);

		this.setBorder(border);
        this.setFont(FontLibrary.FONT_R16);
        this.setBackground(Palette.WHITE);
        this.setForeground(Palette.TEXT_CLR);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);

    }

}
