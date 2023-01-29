import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.Timer;

public class ProductManager {
    
    Map<String, List<Product>> products;
    List<Integer> curPath;
    Iterator<Integer> iterator;

    Timer timer;

    PropertyChangeSupport support;

    public ProductManager(Map<String, List<Product>> products, PropertyChangeListener listener){

        this.products = products;
        this.curPath = new ArrayList<>();
        this.iterator = null;

        this.support = new PropertyChangeSupport(this);
        this.support.addPropertyChangeListener(listener);

    }

    public Product getProductFromString(String str){

        Product product = null;
        Iterator<Product> productIterator;

        List<Product> productList = products.values().stream().flatMap(List::stream).collect(Collectors.toList());

        productIterator = productList.iterator();
        while (product == null && productIterator.hasNext()) {
            
            Product tmp = productIterator.next();

            if (tmp.toString().equals(str)) {
                product = tmp;
            }

        }

        return product;

    }

    public void addProduct(Map<String, String> data){

        String name = data.get("Produktuaren izena");
        String type = data.get("Produktu mota").toLowerCase();
        int quantity = Integer.parseInt(data.get("Kopurua"));

        String[] path = data.get("Ibilbidea").split(",");

        List<Integer> pathList = Arrays.stream(path).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

        Product product = new Product(name, type, quantity, "Jon Ramos", "01/01/2023", "default.png", pathList);

        products.get(type).add(product);

        support.firePropertyChange(Command.PRODUCT_ADD, null, product);

    }

    public void updateForm(Product product) {

        support.firePropertyChange(Command.UPDATE_FORM, null, product);

    }

    public void editProduct(Map<String, String> data){

        Product product = this.getProductFromString(data.get("Aukeratu produktua"));

        for (String key : data.keySet()) {
            
            switch (key) {
                
                case "Produktuaren izena":
                    
                    product.setName(data.get(key));

                break;

                case "Produktu mota":
                    
                    product.setType(data.get(key));

                break;


                case "Kopurua":
                        
                    product.setQuantity(Integer.parseInt(data.get(key)));

                break;

                case "Ibilbidea":

                    List<Integer> path = Arrays.asList(data.get(key).split(",")).stream().map(Integer::valueOf).collect(Collectors.toList());
                            
                    product.setPath(path);

                break;

            
                default:
                    break;
            }

        }

    }

    public Map<String, List<Product>> getProducts(){
        return this.products;
    }

    public void showProductPath(Product product){

        if (isTimerActive()) return;

        support.firePropertyChange(Command.RESET_PRODUCT_PATH, null, null);

        this.curPath = product.getPath();
        this.iterator = this.curPath.iterator();

        this.timer = new Timer(250, new PATHTimer());
        this.timer.start();

    }

    public void showPath(){

        if (pathHasNext()) {

            int lightBulbID = iterator.next();

            // System.out.println(next.getId());

            support.firePropertyChange(Command.DRAW_PRODUCT_PATH, null, lightBulbID);

        }
        else{

            this.timer.stop();
            this.timer = null;

            this.curPath = new ArrayList<>();
            this.iterator = null;

            support.firePropertyChange(Command.PRODUCT_PATH_FINISHED, null, null);

        }

    }

    public boolean pathHasNext(){
        return iterator.hasNext();
    }

    public void updateProductsModel(String productType){

        List<Product> value = products.get(productType.toLowerCase());

        support.firePropertyChange(Command.CHANGE_PRODUCTS_LIST, null, productType.toLowerCase());

    }

	public boolean isTimerActive(){
		return timer != null;
	}

	private class PATHTimer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            showPath();

        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}
