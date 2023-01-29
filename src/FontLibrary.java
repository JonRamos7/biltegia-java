import java.awt.Font;

public class FontLibrary {
    
    private static final CFont FONT_R = new CFont("../lib/fonts/Poppins/Poppins-Regular.ttf");
    private static final CFont FONT_M = new CFont("../lib/fonts/Poppins/Poppins-Medium.ttf");
    private static final CFont FONT_SB = new CFont("../lib/fonts/Poppins/Poppins-SemiBold.ttf");
    private static final CFont FONT_B = new CFont("../lib/fonts/Poppins/Poppins-Bold.ttf");

    final static Font FONT_R16 = FONT_R.resize(16f);

	final static Font FONT_M10 = FONT_M.resize(10f);
	final static Font FONT_M14 = FONT_M.resize(14f);
	final static Font FONT_M16 = FONT_M.resize(16f);
	final static Font FONT_M18 = FONT_M.resize(18f);
	final static Font FONT_M32 = FONT_M.resize(32f);

    final static Font FONT_SB12 = FONT_SB.resize(12f);
	final static Font FONT_SB16 = FONT_SB.resize(16f);
	final static Font FONT_SB24 = FONT_SB.resize(24f);

    final static Font FONT_B16 = FONT_B.resize(16f);

}
