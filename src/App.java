import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class App extends JFrame implements PropertyChangeListener{

    final int WINDOW_WIDTH = 551;
	final int WINDOW_HEIGHT = 455;

    final static String PROPERTY_1 = "property1";
	final static String PROPERTY_2 = "property2";
	final static String PROPERTY_3 = "property3";

    final static String COMMAND_1 = "command1";
	final static String COMMAND_2 = "command2";

    final static String CMD_Move = "move";
	final static String CMD_Open = "open";
	final static String CMD_Close = "close";
	final static String CMD_Rename = "rename";
	final static String CMD_Edit = "edit";
	final static String CMD_Cut = "cut";
	final static String CMD_Copy = "copy";
	final static String CMD_Paste = "paste";
	final static String CMD_Delete = "delete";
	final static String CMD_Salir = "salir";

    Model model;
    Controller controller;

    AbstractAction accOpen, accClose, accCut, accPaste, accCopy, accDelete, accSalir;

    public App(){

        super("Biltegia");

        this.model = new Model();
        this.controller = new Controller(this, model);

        this.crearAcciones();

        this.setJMenuBar(crearMenuBar());
        this.setContentPane(createAppPane());
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // or this.pack() for a dynamic window size
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

    }

    private void crearAcciones() {

		accOpen = controller.newAction("Open", "Open", "../lib/icons/window_new.png", KeyEvent.VK_O);
		accClose = controller.newAction("Close", "Close", "../lib/icons/kgpg.png", KeyEvent.VK_X);

		accCut = controller.newAction("Cut", "Cut", "../lib/icons/editcut.png", KeyEvent.VK_C);
		accCopy = controller.newAction("Copy", "Copy", "../lib/icons/editcopy.png", KeyEvent.VK_P);
		accPaste = controller.newAction("Paste", "Paste", "../lib/icons/editpaste.png", KeyEvent.VK_S);
		accDelete = controller.newAction("Delete", "Delete", "../lib/icons/editdelete.png", KeyEvent.VK_B);
		accSalir = controller.newAction("Salir", "Salir", "../lib/icons/shutdown.png", KeyEvent.VK_S);

	}

    private JMenuBar crearMenuBar(){

        JMenuBar menuBar = new JMenuBar();

        Object [][] fileMenuItems = {
            {accOpen, false},
            {accClose, true}
        };

        JMenu fileMenu = crearMenu("File", KeyEvent.VK_F, fileMenuItems);

        Object [][] editMenuItems = {
            {accCut, false},
            {accCopy, true},
            {accPaste, false},
            {accDelete, true}
        };

        JMenu editMenu = crearMenu("Edit", KeyEvent.VK_E, editMenuItems);


        Object [][] irtenMenuItems = {
            {accSalir, false}
        };

        JMenu irtenMenu = crearMenu("Irten", KeyEvent.VK_E, irtenMenuItems);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(irtenMenu);
		
		return menuBar;

    }

    private JMenu crearMenu(String menuLabel, int mnemonic, Object[][] items){
        
        JMenu menu = new JMenu(menuLabel);
        menu.setMnemonic(mnemonic);

        for (Object[] item : items) {

            AbstractAction action = (AbstractAction) item[0];
            boolean separator = (boolean) item[1];

            menu.add(action);

            if (separator) {
                menu.addSeparator();
            }
            
        }

        return menu;

    }

    private Container createAppPane() {

		JPanel main = new JPanel();


		return main;

	}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();
		String lift = (String) evt.getNewValue();

        switch (property) {

            case PROPERTY_1:
                
            break;

            case PROPERTY_2:
                
            break;

            case PROPERTY_3:
                
            break;
        
            default: break;

        }
        
    }

    public static void main(String[] args) throws Exception {

        App app = new App();

    }

}
