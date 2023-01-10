import java.awt.Font;
import java.io.File;

// CFont a.k.a CustomFont
public class CFont{

    Font font;
    File fontFile;

    public CFont(String filePath, int format, float size){

        fontFile = new File(filePath);

        try {
            this.font = Font.createFont(format, fontFile);
            this.font = this.font.deriveFont(size);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public CFont(String filePath){
        
        this(filePath, Font.TRUETYPE_FONT, 16);

    }

    public Font resize(float size){
        return font.deriveFont(size);
    }

}
