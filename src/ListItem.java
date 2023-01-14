import javax.swing.ImageIcon;

public class ListItem {
    
    final int MAX_QUANTITY = 15;

    int quantity;
    String name;
    String createdby;
    String date;
    String imagePath;
    ImageIcon image;


    public ListItem(String name, int quantity, String createdby, String date, ImageIcon image) {
        this.name = name;
        this.quantity = quantity;
        this.createdby = createdby;
        this.date = date;
        this.image = image;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return Integer.toString(this.quantity) + " / " + Integer.toString(MAX_QUANTITY);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public ImageIcon getImage() {
        return this.image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }


}
