import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    
    private final int MAX_QUANTITY = 15;
    private final String IMAGES_PATH = "lib/images/products/";

    public String uid;
    public int quantity;
    public String type;
    public String name;
    public String createdby;
    public String date;
    public String imagePath;
    public List<Integer> path;

    public Product(String name, String type, int quantity, String createdby, String date, String imageFileName, List<Integer> path) {

        this.uid = generateUID();
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.createdby = createdby;
        this.date = date;
        this.imagePath = IMAGES_PATH + imageFileName;
        this.path = path;
        
    }

    @JsonCreator
    public Product(@JsonProperty("uid") String uid, @JsonProperty("name") String name, @JsonProperty("type") String type, @JsonProperty("quantity") int quantity, @JsonProperty("createdby") String createdby, @JsonProperty("date") String date, @JsonProperty("imageFileName") String imageFileName, @JsonProperty("path") List<Integer> path) {

        this.uid = uid;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.createdby = createdby;
        this.date = date;
        this.imagePath = IMAGES_PATH + imageFileName;
        this.path = path;
        
    }

    public static String generateUID() {

        long timestamp = System.currentTimeMillis();
        int randomNumber = (int) (Math.random() * Integer.MAX_VALUE);

        return String.valueOf(timestamp) + String.valueOf(randomNumber);

    }

    public String getUID(){
        return this.uid;
    }

    public List<Integer> getPath(){
        return this.path;
    }

    public void setPath(List<Integer> path){
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxQuantity(){
        return this.MAX_QUANTITY;
    }

    public String getCreatedby() {
        return this.createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // public ImageIcon getImage() {
    //     return this.image;
    // }

    // public void setImage(ImageIcon image) {
    //     this.image = image;
    // }

    @Override
    public String toString() {
        return String.format("%s - %s", this.uid, this.name);
    }


    // public void addPropertyChangeListener(PropertyChangeListener listener) {
    //     support.addPropertyChangeListener(listener);
    // }

    // public void removePropertyChangeListener(PropertyChangeListener listener) {
    //     support.removePropertyChangeListener(listener);
    // }

}
