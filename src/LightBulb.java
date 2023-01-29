import java.awt.Color;

public class LightBulb {
    
	private int id;
    private Color color;
    private int x, y, width, height;

    public LightBulb(int id, Color color, int x, int y, int width, int height) {

        this.id = id;
		this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
      
	public void setColor(Color color) {
		this.color = color;
	}

	public int getId(){
		return this.id;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

}
