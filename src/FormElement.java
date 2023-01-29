import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormElement extends JPanel {

    private JLabel label;
    private JComponent field;

    public FormElement(String labelText, JComponent field) {

        this.label = new CLabel(labelText, FontLibrary.FONT_M18, Palette.TEXT_CLR);
        this.field = field;

        this.setLayout(new GridLayout(1, 2));
        this.setOpaque(false);
        this.add(this.label);
        this.add(this.field);

    }

    public JLabel getLabel() {
        return label;
    }

    public JComponent getField() {
        return field;
    }

    public Object getFieldValue(){

        Object value = null;

        if(this.field instanceof JTextField) value = ((JTextField) this.field).getText();
        else if (this.field instanceof JComboBox) value = ((JComboBox) this.field).getSelectedItem().toString();
        
        return value;

    }

}
