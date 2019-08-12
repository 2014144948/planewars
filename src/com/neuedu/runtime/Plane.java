package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Plane extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private Image Himage;

    private boolean up, right, down, left;
    private boolean fire;

    private int speed = FrameConstant.GAME_SPEED * 5;
    private int fireSpeed = FrameConstant.GAME_SPEED * 10;

    private int HgetX = (FrameConstant.GAME_WIDTH - ImageMap.get("my01").getWidth(null) / 2) / 2 + ImageMap.get("my01").getWidth(null) / 2 / 2 - 4 - 100;
    private int HgetY = FrameConstant.GAME_HEIGHT - ImageMap.get("my01").getHeight(null) + ImageMap.get("my01").getHeight(null) / 2 / 2 - 15;

    private int index = 0;

    public static int hp = 20;

    public static boolean pause;
    public static boolean skill01;

    public static int skill01time = 75;

    public Plane() {
        this((FrameConstant.GAME_WIDTH - ImageMap.get("my01").getWidth(null) / 2) / 2 - 100,
                FrameConstant.GAME_HEIGHT - ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"), ImageMap.get("myheart"));
    }

    public Plane(int x, int y, Image image, Image Himage) {
        super(x, y);
        this.image = image;
        this.Himage = Himage;
    }

    public int getHgetX() {
        return HgetX;
    }

    public void setHgetX(int hgetX) {
        HgetX = hgetX;
    }

    public int getHgetY() {
        return HgetY;
    }

    public void setHgetY(int hgetY) {
        HgetY = hgetY;
    }

    @Override
    public void draw(Graphics g) {
        move();
        fire();
        Skill01();
        g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);
        g.drawImage(Himage, HgetX, HgetY,
                ImageMap.get("myheart").getWidth(null),
                ImageMap.get("myheart").getHeight(null),
                null);


        if (fire) {
            index++;
            if (index >= fireSpeed) {
                index = 0;
            }
        } else {
            index = 0;
        }
    }

    public void fire() {
        if (fire && index == 0) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2,
                    getY() - ImageMap.get("mb01").getHeight(null), ImageMap.get("mb01")));

        }
    }


    public static int s01 = 0;

    public void Skill01() {
        if (!fire && skill01) {
            s01++;
            if (s01 > skill01time) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2 + 30,
                        getY() - ImageMap.get("mb01").getHeight(null) + 30, ImageMap.get("mb01")));
                gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2 - 30,
                        getY() - ImageMap.get("mb01").getHeight(null) + 30, ImageMap.get("mb01")));
                gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2 + 20,
                        getY() - ImageMap.get("mb01").getHeight(null) + 20, ImageMap.get("mb01")));
                gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2 - 20,
                        getY() - ImageMap.get("mb01").getHeight(null) + 20, ImageMap.get("mb01")));
                gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2 + 10,
                        getY() - ImageMap.get("mb01").getHeight(null) + 10, ImageMap.get("mb01")));
                gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2 - 10,
                        getY() - ImageMap.get("mb01").getHeight(null) + 10, ImageMap.get("mb01")));
                gameFrame.bulletList.add(new Bullet(getX() + image.getWidth(null) / 2 / 2 - ImageMap.get("mb01").getWidth(null) / 2,
                        getY() - ImageMap.get("mb01").getHeight(null), ImageMap.get("mb01")));
                s01 = 0;
            }

        } else {
            s01 = 0;
        }
    }


    @Override
    public void move() {
        if (up) {
            setY(getY() - speed);
            HgetY = HgetY - speed;
        }
        if (right) {
            setX(getX() + speed);
            HgetX = HgetX + speed;
        }
        if (down) {
            setY(getY() + speed);
            HgetY = HgetY + speed;
        }
        if (left) {
            setX(getX() - speed);
            HgetX = HgetX - speed;
        }
        outOfBound();

    }

    public void outOfBound() {
        if (getX() < 0) {
            setX(0);
            setHgetX(getX() + image.getWidth(null) / 2 / 2 - 4);
        }
        if (getY() < FrameConstant.BG_BORDER) {
            setY(FrameConstant.BG_BORDER);
            setHgetY(FrameConstant.BG_BORDER + image.getHeight(null) / 2 / 2 - 15);
        }
        if (getX() > FrameConstant.GAME_WIDTH - image.getWidth(null) / 2) {
            setX(FrameConstant.GAME_WIDTH - image.getWidth(null) / 2);
            setHgetX(FrameConstant.GAME_WIDTH - image.getWidth(null) / 2 / 2 - 4);
        }
        if (getY() > FrameConstant.GAME_HEIGHT - image.getHeight(null)) {
            setY(FrameConstant.GAME_HEIGHT - image.getHeight(null));
            setHgetY(FrameConstant.GAME_HEIGHT - image.getHeight(null) / 2 / 2 - image.getHeight(null) / 2 - 15);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;

        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;

        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            skill01 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pause = !pause;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            skill01 = false;
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(HgetX, HgetY, ImageMap.get("myheart").getWidth(null) / 2, ImageMap.get("myheart").getHeight(null) / 2);
    }
}
