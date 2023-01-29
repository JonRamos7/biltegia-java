import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;

public class Homepage extends JFrame implements PropertyChangeListener{

    final int WINDOW_WIDTH = 1520;
	final int WINDOW_HEIGHT = 860;
    
    Container appPane;

    JSONManager jsonManager;
    JsonNode jsonData;

    Controller controller;
    ProductManager productManager;

    Mapa mapa;

    Map<String, List<Product>> products;

    JList<Product> productsList;
    DefaultListModel<Product> productsListModel;
    RendererListProduct listProductRenderer;

    JList<JButton> navigationList;
    DefaultListModel<JButton> navigationModel;
    RendererNavigationList navButtonRenderer;

    JLabel sidebarTitle;
    JTextField searchInput;

    AbstractAction accEdariak, accPatatak, accGozoak;
    AbstractAction accAdd, accEdit, accDelete, accSettings, accLogout, accSearch;
    AbstractAction accPath;

    public Homepage(){

        super("BiltegIA");

        this.jsonManager = new JSONManager();

        this.mapa = new Mapa();

        this.initProducts();
        
        this.productManager = new ProductManager(products, this);
        this.controller = new Controller(this, productManager);

        this.initActions();
        this.initNavigation();

        this.appPane = createAppPane();

        this.setContentPane(appPane);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // or this.pack() for a dynamic window size
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {

                try {

                    jsonManager.writeToJSON("products", products);
        
                } catch (IOException exception) {
                    
                    System.out.println("Could not load JSON file");
        
                }
            }

        });

    }

    private void initActions(){
        
        // Navigation button actions
        accEdariak = controller.newAction("EDARIAK", "", ImageLibrary.ICON_COFFEE, Command.CHANGE_PRODUCTS_LIST, KeyEvent.VK_G);
        accPatatak = controller.newAction("PATATAK", "", ImageLibrary.ICON_COFFEE, Command.CHANGE_PRODUCTS_LIST, KeyEvent.VK_G);
        accGozoak = controller.newAction("GOZOAK", "", ImageLibrary.ICON_COFFEE, Command.CHANGE_PRODUCTS_LIST, KeyEvent.VK_G);


        // Header button actions
        accAdd = controller.newAction("", "Produktua Gehitu", ImageLibrary.ICON_ADD, Command.OPEN_PRODUCT_WINDOW, KeyEvent.VK_G);
        accEdit = controller.newAction("", "Produktua Editatu", ImageLibrary.ICON_EDIT2, Command.OPEN_PRODUCT_WINDOW, KeyEvent.VK_G);
        accDelete = controller.newAction("", "Produktua Ezabatu", ImageLibrary.ICON_DELETE, Command.OPEN_PRODUCT_WINDOW, KeyEvent.VK_G);
        accSettings = controller.newAction("", "", ImageLibrary.ICON_CONFIG, Command.CONFIG, KeyEvent.VK_G);
        accLogout = controller.newAction("", "", ImageLibrary.ICON_LOGOUT, Command.LOGOUT, KeyEvent.VK_G);


        // Products list button actions
        accSearch = controller.newAction("Bilatu", "", null, Command.PRODUCT_SEARCH, KeyEvent.VK_G);
        accPath = controller.newAction("Erakutsi bidea", "", null, Command.SHOW_PRODUCT_PATH, KeyEvent.VK_G);

	}

    private void initProducts(){

        // Load products data from JSON to the products map
        try {

            products = this.jsonManager.loadJSON("products", new TypeReference<Map<String, List<Product>>>(){});

            // for (Product product : products.get("edariak")) {
            //     System.out.println(product);
            // }

        } catch (IOException e) {
            
            System.out.println("Could not load JSON file");
            
            e.printStackTrace();

        }

        // Initialize products JList, Renderer and Model
        this.productsList = new JList<>();
        this.listProductRenderer = new RendererListProduct();
        this.productsListModel = new DefaultListModel<Product>();

        // Add data to to the JList's model, 
        // by default "edariak" will be the initial list of products the user can see
        this.productsListModel.addAll(products.get("edariak"));

    }

    private void initNavigation(){

        this.navigationList = new JList<>();
        this.navButtonRenderer = new RendererNavigationList();

        this.navigationModel = new DefaultListModel<JButton>();
        this.navigationModel.addElement(new CButton(accEdariak, "Icon&Text"));
        this.navigationModel.addElement(new CButton(accPatatak, "Icon&Text"));
        this.navigationModel.addElement(new CButton(accGozoak, "Icon&Text"));
        this.navigationModel.addElement(new CButton(accEdariak, "Icon&Text"));

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
        panel.setOpaque(true);
        panel.setBackground(Palette.ALABASTER);

        JPanel header = createHeader();
        JPanel mapa = createPanelContent();

        panel.add(header, BorderLayout.NORTH);
        panel.add(mapa, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createHeader(){

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
        panel.setOpaque(true);
        panel.setBackground(Palette.ALABASTER);
        panel.setBorder(BorderFactory.createEmptyBorder(32, 64, 32, 64));

        JLabel logo = new JLabel(ImageLibrary.IMG_LOGO);
        JButton button = new JButton(accAdd);
        button.setBackground(null);
        button.setBorder(null);
        button.setFocusable(false);

        panel.add(logo);

        panel.add(Box.createHorizontalGlue());

        panel.add(new CButton(accAdd, "IconOnly"));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButton(accEdit, "IconOnly"));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButton(accDelete, "IconOnly"));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new JLabel(ImageLibrary.ICON_LINE));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButton(accSettings, "IconOnly"));

        panel.add(Box.createRigidArea(new Dimension(32, 0)));

        panel.add(new CButton(accLogout, "IconOnly"));

        return panel;

    }

    private JPanel createPanelContent() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        // panel.setBackground(Palette.ALABASTER);
        
        // JLabel mapa = new JLabel(IMG_MAPA);
        // mapa.setPreferredSize(new Dimension(MAPA_WIDTH, MAPA_HEIGHT));

        JPanel panelMapa = new JPanel();
        panelMapa.setLayout(new BoxLayout(panelMapa, BoxLayout.X_AXIS));
        panelMapa.setOpaque(true);
        panelMapa.setBackground(Color.red);


        JPanel panelMapaInner = new JPanel();
        panelMapaInner.setLayout(new BoxLayout(panelMapaInner, BoxLayout.X_AXIS));
        panelMapaInner.setOpaque(true);
        panelMapaInner.setBackground(Color.blue);

        panelMapaInner.add(Box.createHorizontalGlue());
        panelMapaInner.add(this.mapa);
        panelMapaInner.add(Box.createVerticalGlue());


        panelMapa.add(Box.createHorizontalGlue());
        panelMapa.add(this.mapa);
        panelMapa.add(Box.createHorizontalGlue());

        // mapa = new Mapa();
        // mapa.setBulbColor(5, Palette.LIGHT_BULB_ACTIVE);

        JPanel navigation = createLeftNavigation();
        // navigation.setOpaque(true);
        // navigation.setBackground(Color.red);

        panel.add(navigation, BorderLayout.WEST);
        panel.add(this.mapa, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createLeftNavigation() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 64, 8, 64));

        navigationList = new JList<>();
        navigationList.setModel(navigationModel);
        navigationList.setSelectedIndex(0);
        navigationList.setCellRenderer(navButtonRenderer);
        navigationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        navigationList.addListSelectionListener(controller);

        navigationList.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = navigationList.locationToIndex(e.getPoint());
                navButtonRenderer.setHoverIndex(index);
                navigationList.repaint();
            }
        });

        navigationList.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                navButtonRenderer.setHoverIndex(-1);
                navigationList.repaint();
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(navigationList);
        panel.add(Box.createVerticalGlue());

        return panel;

    }

    private JPanel createSidebar() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);

        sidebarTitle = new CLabel("Edariak", FontLibrary.FONT_SB24, Palette.TEXT_CLR);
        sidebarTitle.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));
        sidebarTitle.setOpaque(true);
        sidebarTitle.setBackground(Palette.DAWN_PINK);

        JPanel listPanel = createListAndSearch();
        
        panel.add(sidebarTitle, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createListAndSearch() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);
        // panel.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 0));

        JPanel searchPanel = createSearchPanel();
        JScrollPane listPanel = createItemsList();
        JPanel erakutsiBideaPanel = createErakutsiBideaPanel();

        // listPanel.setOpaque(true);
        // listPanel.setBackground(Palette.WHITE);

        JLabel label = new JLabel("aaa");
        label.setBorder(new LineBorder(Palette.DAWN_PINK, 1, false));

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
        panel.add(erakutsiBideaPanel, BorderLayout.SOUTH);


        return panel;
    }

    private JPanel createErakutsiBideaPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);

        Border border = new LineBorder(Palette.DAWN_PINK, 1, false);
		Border padding = new EmptyBorder(16, 16, 16, 16);

        panel.setBorder(new CompoundBorder(border, padding));

        JButton button = new CButton(accPath);
        button.setBorder(BorderFactory.createEmptyBorder(8, 36, 8, 36));

        panel.add(button, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSearchPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Palette.WHITE);

        Border border = new LineBorder(Palette.DAWN_PINK, 1, false);
		Border padding = new EmptyBorder(16, 16, 16, 16);

        panel.setBorder(new CompoundBorder(border, padding));

        searchInput = new CTextField("Bilatu", "default");
        searchInput.setFont(FontLibrary.FONT_SB16);

        JButton button = new CButton(accSearch);
        button.setBorder(BorderFactory.createEmptyBorder(8, 36, 8, 36));


        panel.add(searchInput, BorderLayout.CENTER);
        // panel.add(Box.createRigidArea(new Dimension(8, 0)));
        panel.add(button, BorderLayout.EAST);

        return panel;
    }

    private JScrollPane createItemsList(){

        JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        productsList = new JList<>();
        productsList.setModel(productsListModel);
        productsList.setCellRenderer(listProductRenderer);
        productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.setViewportView(productsList);

		return panel;

    }

    public Product getSelectedProduct(){

        return productsList.getSelectedValue();

    }

    public JButton getSelectedNavButton(){

        return navigationList.getSelectedValue();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();
		// String lift = (String) evt.getNewValue();

        switch (property) {

            case Command.DRAW_PRODUCT_PATH:
                
                int lightBulbID = (int) evt.getNewValue();

                List<LightBulb> lightBulbs = mapa.getLightBulbs();
                LightBulb lightBulb = lightBulbs.stream().filter(e -> e.getId() == lightBulbID).findFirst().orElse(null);

                //System.out.println(lightBulbIndex);

                mapa.setBulbColor(lightBulbs.indexOf(lightBulb), Palette.LIGHT_BULB_ACTIVE);

            break;

            case Command.PRODUCT_PATH_FINISHED:
                
                System.out.println("Path Finished!");

            break;

            case Command.RESET_PRODUCT_PATH:
                
                mapa.resetLightBulbs();

            break;

            case Command.CHANGE_PRODUCTS_LIST:

                // List<Product> list = (List<Product>) evt.getNewValue();
                String productType = (String) evt.getNewValue();

                sidebarTitle.setText(Character.toUpperCase(productType.charAt(0)) + productType.substring(1));

                List<Product> list = products.get(productType);

                productsListModel.removeAllElements();

                for (Product product : list) {
                    productsListModel.addElement(product);
                }

            break;

            case Command.PRODUCT_ADD:

                System.out.println(products.get("edariak"));

                Product newProduct = (Product) evt.getNewValue();

                productsListModel.addElement(newProduct);

            break;
        
            default: break;

        }
        
    }

}
