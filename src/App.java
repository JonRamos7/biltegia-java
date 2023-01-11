import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;

public class App extends JFrame implements PropertyChangeListener{

    final int WINDOW_WIDTH = 1520;
	final int WINDOW_HEIGHT = 860;

    final static CFont FONT_M = new CFont("../lib/fonts/Poppins/Poppins-Medium.ttf");
    final static CFont FONT_SB = new CFont("../lib/fonts/Poppins/Poppins-SemiBold.ttf");
    final static CFont FONT_B = new CFont("../lib/fonts/Poppins/Poppins-Bold.ttf");

	final static Font FONT_M10 = FONT_M.resize(10f);
	final static Font FONT_M14 = FONT_M.resize(14f);
	final static Font FONT_M16 = FONT_M.resize(16f);
	final static Font FONT_M32 = FONT_M.resize(32f);

    final static Font FONT_SB12 = FONT_SB.resize(12f);
	final static Font FONT_SB16 = FONT_SB.resize(16f);
	final static Font FONT_SB24 = FONT_SB.resize(24f);

    final static Font FONT_B16 = FONT_B.resize(16f);

    final ImageIcon IMG_LOGO = new ImageIcon("../lib/images/logo.png");
    final ImageIcon IMG_COCACOLA = new ImageIcon("../lib/images/coca-cola.png");
    
    final ImageIcon ICON_ADD = new ImageIcon("../lib/icons/plus.png");
    final ImageIcon ICON_EDIT2 = new ImageIcon("../lib/icons/edit.png");
    final ImageIcon ICON_DELETE = new ImageIcon("../lib/icons/delete.png");
    final ImageIcon ICON_LINE = new ImageIcon("../lib/icons/line.png");
    final ImageIcon ICON_CONFIG = new ImageIcon("../lib/icons/settings.png");
    final ImageIcon ICON_LOGOUT = new ImageIcon("../lib/icons/logout.png");

    final ImageIcon plus_icon = new ImageIcon("../lib/icons/fa6-solid_plus.png");
    final ImageIcon edit_icon = new ImageIcon("../lib/icons/fa6-solid_pen-to-square.png");
    final ImageIcon remove_icon = new ImageIcon("../lib/icons/fa6-solid_trash-can.png");
    final ImageIcon chevron_icon = new ImageIcon("../lib/icons/chevron_right.png");
    final ImageIcon back_icon = new ImageIcon("../lib/icons/back.png");

    final static String PROPERTY_1 = "property1";
	final static String PROPERTY_2 = "property2";
	final static String PROPERTY_3 = "property3";

    final static String CMD_Gehitu = "gehitu";
    final static String CMD_Editatu = "editatu";
    final static String CMD_Ezabatu = "ezabatu";
    final static String CMD_Edariak = "edariak";
    final static String CMD_Patatak = "patatak";
    
    Container appPane;

    Model model;
    Controller controller;

    JList<String> menu;
    DefaultListModel<String> jlistMenuModel;
    RendererMenuOption jlistMenuRenderer;
    
    JButton btnGehitu, btnEditatu, btnEzabatu;
    AbstractAction accOpen, accGehitu, accEditatu, accEzabatu;

    AbstractAction accAdd, accEdit, accDelete, accSettings, accLogout, accSearch;

    public App(){

        super("BiltegIA");

        jlistMenuRenderer = new RendererMenuOption();
		jlistMenuModel = new DefaultListModel<String>();
        initList();

        this.menu = new JList<>();
        this.model = new Model();
        this.controller = new Controller(this, model, menu);

        this.crearAcciones();

        appPane = createAppPane();

        this.setContentPane(appPane);
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
        accGehitu = controller.newAction("Produktua Gehitu", plus_icon, CMD_Gehitu, KeyEvent.VK_G);
        accEditatu = controller.newAction("Produktua Editatu", edit_icon, CMD_Editatu, KeyEvent.VK_E);
        accEzabatu = controller.newAction("Produktua Ezabatu", remove_icon, CMD_Ezabatu, KeyEvent.VK_R);

        accAdd = controller.newAction("", ICON_ADD, CMD_Edariak, KeyEvent.VK_G);
        accEdit = controller.newAction("", ICON_EDIT2, CMD_Edariak, KeyEvent.VK_G);
        accDelete = controller.newAction("", ICON_DELETE, CMD_Edariak, KeyEvent.VK_G);
        accSettings = controller.newAction("", ICON_CONFIG, CMD_Edariak, KeyEvent.VK_G);
        accLogout = controller.newAction("", ICON_LOGOUT, CMD_Edariak, KeyEvent.VK_G);
        accSearch = controller.newAction("Bilatu", null, CMD_Edariak, KeyEvent.VK_G);

	}

    private Container createAppPane() {

		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);
        // pane.setResizeWeight(0.7);
        pane.setDividerLocation(1120);
        pane.setEnabled(false);
        pane.setDividerSize(0);
        pane.setBorder(null);

        JPanel main = createMain();
        JPanel sidebar = createSidebar();
        
        pane.add(main);
        pane.add(sidebar);

		return pane;

	}

    private JPanel createMain() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel mapa = crearPanelMapa();
        JPanel header = createHeader();

        panel.add(header, BorderLayout.NORTH);
        panel.add(mapa, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSidebar() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);

        JLabel label = new CLabel("Edariak", FONT_SB24, Palette.TEXT_CLR);
        label.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));
        label.setOpaque(true);
        label.setBackground(Palette.DAWN_PINK);

        JPanel listPanel = createListAndSearch();
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createListAndSearch() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);
        // panel.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 0));

        JPanel searchPanel = createSearchPanel();
        JPanel listPanel = createListItem();
        // listPanel.setOpaque(true);
        // listPanel.setBackground(Palette.WHITE);

        JLabel label = new JLabel("aaa");
        label.setBorder(new LineBorder(Palette.DAWN_PINK, 1, false));

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSearchPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);

        Border border = new LineBorder(Palette.DAWN_PINK, 1, false);
		Border padding = new EmptyBorder(16, 16, 16, 16);

        panel.setBorder(new CompoundBorder(border, padding));

        JTextField search = new CTextField("Bilatu", FONT_SB16, 21);
        JButton button = new JButton(accSearch);
        button.setFont(FONT_M16);
        button.setBorder(BorderFactory.createEmptyBorder(0, 36, 0, 36));
        button.setFocusable(false);
        button.setForeground(Palette.WHITE);
        button.setBackground(Palette.BLUISH_PURPLE);


        panel.add(search, BorderLayout.CENTER);
        // panel.add(Box.createRigidArea(new Dimension(8, 0)));
        panel.add(button, BorderLayout.EAST);

        return panel;
    }

    private JPanel createListItem(){

        JPanel panel = new JPanel();
        BoxLayout panelLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(panelLayout);
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);
        // panel.setBackground(Color.decode("#F3EEFF"));

        JLabel image = new JLabel(IMG_COCACOLA);

        JPanel infoPanel = new JPanel();
        BoxLayout infoPanelLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);
        infoPanel.setLayout(infoPanelLayout);
        infoPanel.setMaximumSize(new Dimension(300, 62));
        infoPanel.setOpaque(false);
        infoPanel.setBackground(Palette.WHITE);

        JLabel itemName = new CLabel("Coca Cola", FONT_M14, Palette.TEXT_CLR);
        itemName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel itemDetails = new JPanel();
        BoxLayout itemDetailsLayout = new BoxLayout(itemDetails, BoxLayout.X_AXIS);
        itemDetails.setLayout(itemDetailsLayout);
        itemDetails.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemDetails.setOpaque(false);
        itemDetails.setBackground(Palette.WHITE);


        JPanel quantity = createDetail("Kopurua", "12 / 15");
        JPanel createdby = createDetail("Sortzailea", "Jon Ramos");
        JPanel data = createDetail("Data", "07/12/2023");

        itemDetails.add(quantity);
        itemDetails.add(Box.createRigidArea(new Dimension(32, 0)));
        itemDetails.add(createdby);
        itemDetails.add(Box.createRigidArea(new Dimension(32, 0)));
        itemDetails.add(data);


        infoPanel.add(itemName);
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(itemDetails);


        panel.add(image);
        panel.add(infoPanel);

        return panel;

    }

    private JPanel createDetail(String labelText, String valueText){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBackground(Palette.WHITE);

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JLabel label = new CLabel(labelText, FONT_M10, Palette.SILVER);
        JLabel value = new CLabel(valueText, FONT_SB12, Palette.TEXT_CLR);

        panel.add(label);
        panel.add(value);

        return panel;

    }

    private JPanel createHeader(){

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
        panel.setOpaque(true);
        panel.setBackground(Palette.ALABASTER);
        panel.setBorder(BorderFactory.createEmptyBorder(32, 64, 32, 64));

        JLabel logo = new JLabel(IMG_LOGO);
        JButton button = new JButton(accAdd);
        button.setBackground(null);
        button.setBorder(null);
        button.setFocusable(false);

        panel.add(logo);

        panel.add(Box.createHorizontalGlue());

        panel.add(new CButtonIcon(accAdd));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButtonIcon(accEdit));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButtonIcon(accDelete));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new JLabel(ICON_LINE));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButtonIcon(accSettings));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButtonIcon(accLogout));

        return panel;

    }

    private JPanel crearPanelMapa() {

        int MAPA_WIDTH = 684, MAPA_HEIGHT = 584;

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.setOpaque(true);
        panel.setBackground(Palette.ALABASTER);
        // panel.setBorder(null);
        ImageIcon mapa = new ImageIcon("../lib/images/mapa/Almacen - Principal.png");
        
        JLabel label = new JLabel(){

            @Override
            protected void paintComponent(Graphics g){

                super.paintComponent(g);
                g.drawImage(mapa.getImage(), 0, 0, MAPA_WIDTH, MAPA_HEIGHT, null);

            }

        };
        label.setPreferredSize(new Dimension(MAPA_WIDTH, MAPA_HEIGHT));

        panel.add(label);

        return panel;

    }

    private JPanel createControlPanel() {

        JPanel panel = new JPanel(new BorderLayout(0, 24));
        panel.setBackground(Palette.CPANEL_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));

        JLabel logo = new JLabel(IMG_LOGO);
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

        menu.setBackground(Palette.CPANEL_BG);
		menu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menu.setModel(jlistMenuModel);
		menu.setCellRenderer(jlistMenuRenderer);
        menu.addListSelectionListener(controller);
		// zerrenda.addListSelectionListener(kontrolatzailea);
		
		pane.setViewportView(menu);


		return pane;

	}

    private JPanel crearPanelButtons() {

        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 8));

        btnGehitu = crearButton(accGehitu, Palette.BTN_PRIMARY_BG, Palette.BTN_PRIMARY_FG);
        btnEditatu = crearButton(accEditatu, Palette.BTN_SECONDARY_BG, Palette.BTN_SECONDARY_FG);
        btnEzabatu = crearButton(accEzabatu, Palette.BTN_SECONDARY_BG, Palette.BTN_SECONDARY_FG);

        panel.add(btnGehitu);
        panel.add(btnEditatu);
        panel.add(btnEzabatu);

        return panel;
    }

    private JButton crearButton(AbstractAction action, Color bg, Color fg){

        JButton button = new JButton(action);

        // button.setIcon(icon);
        button.setOpaque(true);
        button.setMargin(new Insets(4, 4, 4, 4));
        // button.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        button.setFont(FONT_M14);
        button.setForeground(fg);
        button.setBackground(bg);
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

    private void foo(){

        // System.out.println(appPane.getSize());
        // System.out.println(btnGehitu.getIcon());

    }

    public static void main(String[] args) throws Exception {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        App app = new App();

        app.foo();

    }

}
