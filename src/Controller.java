import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    App app;
    Model model;
    JList<String> menu;

    public Controller(App app, Model model, JList<String> menu){

        this.app = app;
        this.model = model;
        this.menu = menu;

    }

    public AbstractAction newAction(String label, Icon icon, String command, int mnemonic) {

        AbstractAction action = new AbstractAction(label, icon) {
            {
                putValue(AbstractAction.MNEMONIC_KEY, mnemonic);
                // putValue(AbstractAction.SHORT_DESCRIPTION, description);
                putValue(AbstractAction.ACTION_COMMAND_KEY, command);
                // putValue(AbstractAction.LARGE_ICON_KEY, new ImageIcon(iconPath));
            }
        
            public void actionPerformed(ActionEvent e) {

                String command = e.getActionCommand();
                // JButton button = (JButton) e.getSource();

                System.out.println(command);
        
                switch (command) {
        
                    case App.CMD_Gehitu:
        
                        // JMenuItem menuItem = (JMenuItem) e.getSource();
                        // String message = menuItem.getText();
                        // JOptionPane.showMessageDialog (Controller.this.app, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        
        
                    break;
        
                    // case App.CMD_Salir:
        
                    //     Controller.this.app.dispose();
        
                    // break;
        
                    default:
                        break;
        
                }

            }
        }; 
        
        return action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
		JButton button = (JButton) e.getSource();

		switch (command) {

			// case App.COMMAND_1:

            //     model.fun1();

			// break;

			// case App.COMMAND_2:

            //     model.fun2();

			// break;

			default:
				break;

		}
        
    }

	@Override
	public void valueChanged(ListSelectionEvent e) {

		if ( e.getValueIsAdjusting()) return;
		
		String selected = menu.getSelectedValue();

        System.out.println(selected);
		
	}
    
}
