package game.entity;

import game.container.GamePanel;
import game.container.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/base-10.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/base-11.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/base-2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/base-3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/base-5.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/base-7.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/base-4.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/base-9.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            SpriteCounter++;
            if (SpriteCounter > 16) {
                if (SpriteNumber == 1) {
                    SpriteNumber = 2;
                } else if (SpriteNumber == 2) {
                    SpriteNumber = 1;
                }
                SpriteCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.TileSize, gp.TileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (SpriteNumber == 1) {
                    image = up1;
                }
                if (SpriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (SpriteNumber == 1) {
                    image = down1;
                }
                if (SpriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (SpriteNumber == 1) {
                    image = left1;
                }
                if (SpriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (SpriteNumber == 1) {
                    image = right1;
                }
                if (SpriteNumber == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.TileSize, gp.TileSize, null);
    }

}
