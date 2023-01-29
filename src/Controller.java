import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener {

    Homepage homepage;
    ProductManager productManager;
    ProductWindow productWindow;

    public Controller(Homepage homepage, ProductManager productManager){

        this.homepage = homepage;
        this.productManager = productManager;

    }

    public AbstractAction newAction(String label, String description, Icon icon, String command, int mnemonic) {

        final Controller controller = this;

        AbstractAction action = new AbstractAction(label, icon) {
            {
                putValue(AbstractAction.MNEMONIC_KEY, mnemonic);
                // putValue(AbstractAction.SHORT_DESCRIPTION, description);
                putValue(AbstractAction.ACTION_COMMAND_KEY, command);
                // putValue(AbstractAction.LARGE_ICON_KEY, new ImageIcon(iconPath));
            }
        
            public void actionPerformed(ActionEvent e) {

                Form form;
                Product product;
                String command = e.getActionCommand();
        
                switch (command) {
        
                    case Command.SHOW_PRODUCT_PATH:
        
                        product = homepage.getSelectedProduct();

                        if (product != null) {
                            productManager.showProductPath(product);
                        }
                        else{
                            JOptionPane.showMessageDialog(productWindow, "Ez duzu produkturik aukeratu", "Errorea", JOptionPane.ERROR_MESSAGE);
                        }
        
                    break;

                    case Command.OPEN_PRODUCT_WINDOW:

                        productWindow = new ProductWindow(description, controller, productManager.getProducts());

                        productWindow.setVisible(true);
                        productManager.addPropertyChangeListener(productWindow);

                    break;

                    case Command.PRODUCT_ADD:

                        form = productWindow.getForm();

                        if (form.isFormValid()) {
                            productManager.addProduct(form.getData());
                            productWindow.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(productWindow, "Kanpo batzuk utzik daude!", "Errorea", JOptionPane.ERROR_MESSAGE);
                        }


                    break;

                    case Command.PRODUCT_EDIT:

                        form = productWindow.getForm();

                        if (form.isFormValid()) {
                            productManager.editProduct(form.getData());
                            productWindow.dispose();  
                        }
                        else{
                            JOptionPane.showMessageDialog(homepage, "Kanpo batzuk utzik daude!", "Errorea", JOptionPane.ERROR_MESSAGE);
                        }

                    break;

                    case Command.UPDATE_FORM:

                        form = productWindow.getForm();
                        Map<String, String> formData = form.getData();

                        String productStr = formData.get("Aukeratu produktua");

                        product = productManager.getProductFromString(productStr);

                        productManager.updateForm(product);

                    break;

                    case Command.CLOSE_PRODUCT_WINDOW:

                        productManager.removePropertyChangeListener(productWindow);
                        productWindow.dispose();
                        productWindow = null;

                    break;
        
                    case Command.LOGOUT:
        
                        Controller.this.homepage.dispose();
        
                    break;
        
                    default: break;
        
                }

            }
        }; 
        
        return action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        
    }

	@Override
	public void valueChanged(ListSelectionEvent e) {

		if ( e.getValueIsAdjusting()) return;
		
        JButton button = homepage.getSelectedNavButton();

        productManager.updateProductsModel(button.getText());

		// Product selected = itemsList.getSelectedValue();

        // System.out.println(selected);
		
	}
    
}
