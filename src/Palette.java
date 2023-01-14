import java.awt.Color;

enum ColorPalette {
    WHITE("#FFFFFF"),
    TITAN_WHITE("#F3EEFF"),
    ALABASTER("#FBFBFB"),
    PORCELAIN("#F2F2F2"),
    DAWN_PINK("#ECECEC"),
    SILVER("#C3C6C7"),
    CYAN_BLUE("#0085FF"),
    BLUISH_PURPLE("#814EFA"),
    DARK_GREY("#222525"),
    
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
	public static final Color TITAN_WHITE = ColorPalette.TITAN_WHITE.getValue();
	public static final Color ALABASTER = ColorPalette.ALABASTER.getValue();
	public static final Color PORCELAIN = ColorPalette.PORCELAIN.getValue();
	public static final Color DAWN_PINK = ColorPalette.DAWN_PINK.getValue();
	public static final Color SILVER = ColorPalette.SILVER.getValue();
	public static final Color CYAN_BLUE = ColorPalette.CYAN_BLUE.getValue();
	public static final Color BLUISH_PURPLE = ColorPalette.BLUISH_PURPLE.getValue();
    public static final Color DARK_GREY = ColorPalette.DARK_GREY.getValue();


    public static final Color MAIN_BG = ALABASTER;
    public static final Color CPANEL_BG = PORCELAIN;
    public static final Color TEXT_CLR = DARK_GREY;
    public static final Color BTN_PRIMARY_BG = CYAN_BLUE;
    public static final Color BTN_PRIMARY_FG = WHITE;
    public static final Color BTN_SECONDARY_BG = WHITE;
    public static final Color BTN_SECONDARY_FG = DARK_GREY;


    public static final Color BTN_BG = ALABASTER;
    public static final Color BTN_FG = TEXT_CLR;
    public static final Color BTN_BG_SELECTED = BLUISH_PURPLE;
    public static final Color BTN_FG_SELECTED = WHITE;
    public static final Color BTN_BG_HOVER = BTN_BG_SELECTED;
    public static final Color BTN_FG_HOVER = BTN_FG_SELECTED;

    public static final Color LIST_ITEM_BG = WHITE;
    public static final Color LIST_ITEM_ACTIVE = TITAN_WHITE;

}
