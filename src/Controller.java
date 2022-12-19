import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {

    App app;
    Model model;

    public Controller(App app, Model model){

        this.app = app;
        this.model = model;

    }

    public AbstractAction newAction(String label, String description, String imagePath, int mnemonic) {

        AbstractAction action = new AbstractAction(label) {
            {
                putValue(AbstractAction.MNEMONIC_KEY, mnemonic);
                putValue(AbstractAction.SHORT_DESCRIPTION, description);
                putValue(AbstractAction.LARGE_ICON_KEY, new ImageIcon(imagePath));
            }
        
            public void actionPerformed(ActionEvent e) {

                String command = e.getActionCommand();
                // JButton button = (JButton) e.getSource();
        
                switch (command) {
        
                    case App.CMD_Move:
                    case App.CMD_Open:
                    case App.CMD_Close:
                    case App.CMD_Rename:
                    case App.CMD_Edit:
                    case App.CMD_Cut:
                    case App.CMD_Copy:
                    case App.CMD_Paste:
                    case App.CMD_Delete:
        
                        JMenuItem menuItem = (JMenuItem) e.getSource();
                        String message = menuItem.getText();
                        JOptionPane.showMessageDialog (Controller.this.app, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        
        
                    break;
        
                    case App.CMD_Salir:
        
                        Controller.this.app.dispose();
        
                    break;
        
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

			case App.COMMAND_1:

                model.fun1();

			break;

			case App.COMMAND_2:

                model.fun2();

			break;

			default:
				break;

		}
        
    }
    
}
