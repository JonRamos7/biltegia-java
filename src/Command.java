public class Command {

    public final static String CONFIG = "configuration";
    public final static String LOGOUT = "logout";
    
    // Commands regarding the whole process of drawing the path of a product
    public final static String SHOW_PRODUCT_PATH = "showProductPath";
    public final static String DRAW_PRODUCT_PATH = "drawPath";
	public final static String PRODUCT_PATH_FINISHED = "pathFinished";
	public final static String RESET_PRODUCT_PATH = "resetPath";
    
    
	public final static String UPDATE_FORM = "updateForm";

    // Commands that handle the JList of the products
    public final static String UPDATE_PRODUCTS_LIST = "updateProducts"; // It updates the model data of corresponding JList when a product is added, edited or deleted
    public final static String CHANGE_PRODUCTS_LIST = "changeProductType"; // It changes the JList of the products when user changes the Product type selected

    public final static String p = "aa";

    public final static String PRODUCT_ADD = "addProduct";
    public final static String PRODUCT_EDIT = "editProduct";
    public final static String PRODUCT_DELETE = "delProduct";
    public final static String PRODUCT_SEARCH = "search";


    public final static String OPEN_PRODUCT_WINDOW = "productWindow";
    public final static String CLOSE_PRODUCT_WINDOW = "closeWindow";

}
