package game.tile;

import game.container.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tiles[] tile;
    int mapTileNumber[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tiles[10];
        mapTileNumber = new int[gp.MaxScreenCol][gp.MaxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try {

            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass-tile-1.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass-tile-2.png"));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water-tile-1.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map-01.txt");
            BufferedReader  br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.MaxScreenCol && row < gp.MaxScreenRow){

                String line = br.readLine();

                while (col < gp.MaxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.MaxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MaxScreenCol && row < gp.MaxScreenRow) {

            int tileNum = mapTileNumber[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.TileSize, gp.TileSize, null);
            col++;
            x +=  gp.TileSize;

            if(col == gp.MaxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.TileSize;
            }
        }

    }
}
