import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mapa extends JPanel {
    
    // 684x584
    final int PANEL_WIDTH = 850, PANEL_HEIGHT = 716;
    final int MAPA_WIDTH = 684, MAPA_HEIGHT = 584;

    final int BASE_X = (PANEL_WIDTH - MAPA_WIDTH) / 2;
    final int BASE_Y = (PANEL_HEIGHT - MAPA_HEIGHT) / 2;

    List<LightBulb> lightBulbs;

    public Mapa(){

        initLightBulbs();

        this.setPreferredSize(new Dimension(MAPA_WIDTH, MAPA_HEIGHT));

    }

    public void initLightBulbs(){

        int id, x, y;
        int width = 18, height = 18;
        int coordinates[] = {105, 52};
        
        this.lightBulbs = new ArrayList<>();

        id = 0;
        for (int i = 0; i < 9; i++) {

            if (i % 2 == 0) {

                for (int j = 0; j < 5; j++) {
            
                    x = BASE_X + coordinates[0];
                    y = BASE_Y + coordinates[1];
        
                    LightBulb bulb = new LightBulb(id, Palette.LIGHT_BULB_DEFAULT, x, y, width, height);
        
                    this.lightBulbs.add(bulb);
        
                    coordinates[0] += 114;

                    id++;
        
                }

            }
            else{

                x = BASE_X + 333;
                y = BASE_Y + coordinates[1];

                LightBulb bulb = new LightBulb(id, Palette.LIGHT_BULB_DEFAULT, x, y, width, height);
        
                this.lightBulbs.add(bulb);

                id++;

            }

            coordinates[0] = 105;
            coordinates[1] += 62;

            
        }

    }

    public List<LightBulb> getLightBulbs(){
        return this.lightBulbs;
    }

    @Override
    public void paintComponent(Graphics g){

        Graphics2D gr = (Graphics2D) g;

        // gr.setColor(Palette.ALABASTER);
        // gr.fillRect(BASE_X, BASE_Y, MAPA_WIDTH, MAPA_HEIGHT);

        gr.drawImage(ImageLibrary.IMG_MAPA.getImage(), 0, 0, null);

        drawLightBulbs(gr);

    }

    public void drawLightBulbs(Graphics g){

        for (LightBulb bulb : this.lightBulbs) {
            
            g.setColor(bulb.getColor());
            g.fillOval(bulb.getX(), bulb.getY(), bulb.getWidth(), bulb.getHeight());
            g.setColor(Palette.DARK_GREY);
            g.drawOval(bulb.getX(), bulb.getY(), bulb.getWidth(), bulb.getHeight());

        }

    }

    public void resetLightBulbs(){

        for (LightBulb lightBulb : this.lightBulbs) {
            lightBulb.setColor(Palette.WHITE);
        }

    }

    public void setBulbColor(int index, Color color) {
        LightBulb bulb = lightBulbs.get(index);

        bulb.setColor(color);
        
        // g.setColor(Color.red);
        // g.fillOval(bulb.getX(), bulb.getY(), bulb.getWidth(), bulb.getHeight());

        repaint();

    }

}
