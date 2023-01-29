import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ProductWindow extends JFrame implements PropertyChangeListener{

    final int WINDOW_WIDTH = 555;
	final int WINDOW_HEIGHT = 396;
    
    private String title;
    private Controller controller;
    private Map<String, List<Product>> products;
    
    Container windowPane;

    Form form;
    List<FormElement> formElements;

    AbstractAction accAdd, accSave, accDel, accCancel, accUpdateForm;

    public ProductWindow(String title, Controller controller, Map<String, List<Product>> products){

        super(title);

       

        this.title = title;
        this.controller = controller;
        this.products = products;
        
        this.createActions();
        this.initFormElements();
        

        this.windowPane = createWindowPane();


        this.setContentPane(windowPane);
		// this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // or this.pack() for a dynamic window size
        this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

    }

    private void createActions(){

        accAdd = controller.newAction("Gehitu", "", null, Command.PRODUCT_ADD, KeyEvent.VK_G);
        accSave = controller.newAction("Gorde", "", null, Command.PRODUCT_EDIT, KeyEvent.VK_G);
        accDel = controller.newAction("Ezabatu", "", null, Command.PRODUCT_DELETE, KeyEvent.VK_G);
        accCancel = controller.newAction("Atzera", "", null, Command.CLOSE_PRODUCT_WINDOW, KeyEvent.VK_G);
        
        // This action is handled in the Property change bellow
        // Remember this class becomes a PropertyChangeListener for ProductManager class once its created
        // And will be remove as PropertyChangeListener once is disposed
        accUpdateForm = controller.newAction("", "", null, Command.UPDATE_FORM, KeyEvent.VK_G);

    }

    private void initFormElements(){

        switch (this.title) {

            case "Produktua Gehitu":

                formElements = List.of(
                    new FormElement("Produktuaren izena", new CTextField("Lorem ipsum", "default")),
                    new FormElement("Produktu mota", new CTextField("Edariak", "default")),
                    new FormElement("Kopurua", new CTextField("0", "default")),
                    new FormElement("Ibilbidea", new CTextField("1,2,3,4,5,6,...", "default"))
                );

            break;

            case "Produktua Editatu":

                CComboBox<String, Product> comboBox = new CComboBox<String, Product>(products);
                comboBox.setAction(accUpdateForm);
                // comboBox.setActionCommand(Command.UPDATE_FORM);
                comboBox.addActionListener(controller);
                
                this.formElements = List.of(
                    new FormElement("Aukeratu produktua", comboBox),
                    new FormElement("Produktuaren ID", new CTextField("disabled")),
                    new FormElement("Produktuaren izena", new CTextField("Edariak")),
                    new FormElement("Produktu mota", new CTextField("Edariak")),
                    new FormElement("Kopurua", new CTextField("0")),
                    new FormElement("Ibilbidea", new CTextField("1,2,3,4,5,6,..."))
                );

            break;

            case "Produktua Ezabatu":
                    
                this.formElements = List.of(
                    new FormElement("Produktuaren izena", new JTextField("Lorem ipsum")),
                    new FormElement("Produktu mota", new JTextField("Edariak")),
                    new FormElement("Kopurua", new JTextField("0"))
                );

            break;
        
            default: break;

        }

    }

    private void updateFormElementsData(Product product){

        for (FormElement formElement : formElements) {
            
            String label = formElement.getLabel().getText();

            switch (label) {
                
                case "Produktuaren ID":
                    
                    ((CTextField) formElement.getField()).setText(product.getUID());

                break;

                case "Produktuaren izena":
                    
                    ((CTextField) formElement.getField()).setText(product.getName());

                break;

                case "Produktu mota":
                    
                    ((CTextField) formElement.getField()).setText(product.getType());

                break;


                case "Kopurua":
                        
                    ((CTextField) formElement.getField()).setText(Integer.toString(product.getQuantity()));

                break;

                case "Ibilbidea":

                    List<Integer> path = product.getPath();
                    String pathStr = String.join(",", path.stream().map(Object::toString).collect(Collectors.toList()));
                            
                    ((CTextField) formElement.getField()).setText(pathStr);

                break;

            
                default:
                    break;
            }

        }

    }

    public Form getForm(){
        return this.form;
    }

    private Container createWindowPane() {

        JPanel panel = new JPanel(new BorderLayout(0, 24));
        panel.setBackground(Palette.WHITE);
        panel.setBorder(new EmptyBorder(32, 32, 32, 32));


        JLabel head = new CLabel(this.title, FontLibrary.FONT_SB24, Palette.TEXT_CLR);
        this.form = new Form.FormBuilder(this.formElements).build();
        JPanel buttons = createButtons();

        panel.add(head, BorderLayout.NORTH);
        panel.add(this.form, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;

	}

    private JPanel createButtons(){

        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        panel.setOpaque(false);

        JButton btnCancel = new CButton(accCancel, "Secondary");

        switch (this.title) {
            
            case "Produktua Gehitu":
                
                JButton btnAdd = new CButton(accAdd);

                panel.add(btnAdd);
                panel.add(Box.createRigidArea(new Dimension(8, 0)));
                panel.add(btnCancel);

            break;

            case "Produktua Editatu":
                
                JButton btnEdit = new CButton(accSave);
                JButton btnDel = new CButton(accDel, "Secondary");

                panel.add(btnEdit);
                panel.add(Box.createRigidArea(new Dimension(8, 0)));
                panel.add(btnCancel);
                panel.add(Box.createHorizontalGlue());
                panel.add(btnDel);
                

            break;
        
            default: break;

        }

        return panel;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        switch (property) {
            
            case Command.UPDATE_FORM:
                
                Product product = (Product) evt.getNewValue();

                updateFormElementsData(product);

            break;
        
            default: break;

        }
        
    }

}
