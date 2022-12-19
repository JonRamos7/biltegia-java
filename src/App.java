import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class App extends JFrame implements PropertyChangeListener{

    final int WINDOW_WIDTH = 1000;
	final int WINDOW_HEIGHT = 600;

    final ImageIcon logo_img = new ImageIcon("../lib/images/logo.png");
    
    final ImageIcon plus_icon = new ImageIcon("../lib/icons/fa6-solid_plus.png");
    final ImageIcon edit_icon = new ImageIcon("../lib/icons/fa6-solid_pen-to-square.png");
    final ImageIcon remove_icon = new ImageIcon("../lib/icons/fa6-solid_trash-can.png");
    final ImageIcon chevron_icon = new ImageIcon("../lib/icons/chevron_right.png");

    final static String PROPERTY_1 = "property1";
	final static String PROPERTY_2 = "property2";
	final static String PROPERTY_3 = "property3";

    final static String COMMAND_1 = "command1";
	final static String COMMAND_2 = "command2";

    final static String CMD_Gehitu = "gehitu";
    final static String CMD_Editatu = "editatu";
    final static String CMD_Ezabatu = "ezabatu";

    Model model;
    Controller controller;

    JList<String> menu;
    DefaultListModel<String> jlistMenuModel;
    RendererMenuOption jlistMenuRenderer;

    AbstractAction accOpen, accGehitu, accEditatu, accEzabatu;

    JButton btnGehitu, btnEditatu, btnEzabatu;

    public App(){

        super("BiltegIA");

        jlistMenuRenderer = new RendererMenuOption();
		jlistMenuModel = new DefaultListModel<String>();
        initList();

        this.model = new Model();
        this.controller = new Controller(this, model);

        this.crearAcciones();

        this.setContentPane(createAppPane());
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // or this.pack() for a dynamic window size
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

    }

    private void initList(){

        jlistMenuModel.addElement("Edariak");
        jlistMenuModel.addElement("Patatak");
        jlistMenuModel.addElement("Fruta");
        jlistMenuModel.addElement("Lorem");
        jlistMenuModel.addElement("Ipsum");

    }

    private void crearAcciones() {

		// accOpen = controller.newAction("Open", "Open", "../lib/icons/window_new.png", KeyEvent.VK_O);
        accGehitu = controller.newAction("Produktua Gehitu", "", plus_icon, KeyEvent.VK_G);
        accEditatu = controller.newAction("Produktua Editatu", "", edit_icon, KeyEvent.VK_E);
        accEzabatu = controller.newAction("Produktua Ezabatu", "", remove_icon, KeyEvent.VK_R);

	}

    private Container createAppPane() {

		JSplitPane main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);
        main.setBorder(null);

        JPanel controlPanel = createControlPanel();
        JPanel mapa = crearPanelMapa();
        
        main.add(mapa);
        main.add(controlPanel);

		return main;

	}

    private JPanel crearPanelMapa() {

        int MAPA_WIDTH = 684, MAPA_HEIGHT = 584;

        ImageIcon mapa = new ImageIcon("../lib/images/mapa/Almacen - Principal.png");
        
        JPanel panel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                g.drawImage(mapa.getImage(), 0, 0, MAPA_WIDTH, MAPA_HEIGHT, null);

            }

        };
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.setPreferredSize(new Dimension(MAPA_WIDTH, MAPA_HEIGHT));

        return panel;
    }

    private JPanel createControlPanel() {

        JPanel panel = new JPanel(new BorderLayout(0, 24));
        panel.setBackground(Palette.CPANEL_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));

        JLabel logo = new JLabel(logo_img);
        JScrollPane lista = crearPanelMenu();
        JPanel buttons = crearPanelButtons();


        panel.add(logo, BorderLayout.NORTH);
        panel.add(lista, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

	private JScrollPane crearPanelMenu() {

		JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBorder(null);

		menu = new JList<>();
        menu.setBackground(Palette.CPANEL_BG);
		menu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menu.setModel(jlistMenuModel);
		menu.setCellRenderer(jlistMenuRenderer);
		// zerrenda.addListSelectionListener(kontrolatzailea);
		
		pane.setViewportView(menu);


		return pane;

	}

    private JPanel crearPanelButtons() {

        JPanel panel = new JPanel(new GridLayout(3, 1));
        // panel.setOpaque(true);
        // panel.setBackground(Color.red);
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        btnGehitu = crearButton(accGehitu, plus_icon);
        btnEditatu = crearButton(accEditatu, edit_icon);
        btnEzabatu = crearButton(accEzabatu, remove_icon);

        panel.add(btnGehitu);
        panel.add(btnEditatu);
        panel.add(btnEzabatu);
        

        // JPanel panelGehitu = crearButtonPanel(btnGehitu);
        // panelGehitu.setOpaque(true);
        // panelGehitu.setBackground(Color.red);
        // JPanel panelEditatu = crearButtonPanel(btnEditatu);
        // panelEditatu.setOpaque(true);
        // panelEditatu.setBackground(Color.green);
        // JPanel panelEzabatu = crearButtonPanel(btnEzabatu);
        // panelEzabatu.setOpaque(true);
        // panelEzabatu.setBackground(Color.blue);

        // panel.add(panelGehitu);
        // panel.add(panelEditatu);
        // panel.add(panelEzabatu);

        return panel;
    }

    private JButton crearButton(AbstractAction action, ImageIcon icon){

        JButton button = new JButton("Button");

        button.setIcon(icon);
        button.setIconTextGap(8);

        return button;

    }

    private JPanel crearButtonPanel(JButton button){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, button.getPreferredSize().height));

        panel.add(button, BorderLayout.NORTH);

        return panel;

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
