import java.awt.Color;

enum ColorPalette {
    WHITE("#FFFFFF"),
    PORCELAIN("#F2F2F2"),
    CYAN_BLUE("#0085FF"),
    DARK_GREY("#555555"),
    
    ALUMINIUM("#95AAB5"),
    BLUISH_CYAN("#425059"),
    PALE_GREEN("#A6FD8E");

    Color color;

    ColorPalette(String hex){
        this.color = Color.decode(hex);
    }

	public Color getValue(){
		return this.color;
	}

}

public class Palette{

	public static final Color WHITE = ColorPalette.WHITE.getValue();
	public static final Color PORCELAIN = ColorPalette.PORCELAIN.getValue();
	public static final Color CYAN_BLUE = ColorPalette.CYAN_BLUE.getValue();
    public static final Color DARK_GREY = ColorPalette.DARK_GREY.getValue();


    public static final Color CPANEL_BG = PORCELAIN;
    public static final Color TEXT_CLR = DARK_GREY;
    public static final Color BTN_PRIMARY_BG = CYAN_BLUE;
    public static final Color BTN_PRIMARY_FG = WHITE;
    public static final Color BTN_SECONDARY_BG = WHITE;
    public static final Color BTN_SECONDARY_FG = DARK_GREY;

}