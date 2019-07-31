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
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int Xspeed = FrameConstant.GAME_SPEED * 3;
    private int Yspeed = FrameConstant.GAME_SPEED * 3;

    private int index = 0;
    private int indexValue = 50;
    public int fireIndex = 98;
    private int fireIndexValue = 0;
    private int bulletStyle = 50;

    Random r = new Random();

    public EnemyPlane() {
        this(0, 0, ImageMap.get("ep01"));
    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);
        move();
        fire();
    }

    public void fire() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(100);
        fireIndexValue++;
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (r1 > fireIndex) {
            if(r2 > bulletStyle){
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep01").getWidth(null) / 2 / 2 - 5,
                        getY() + image.getHeight(null) / 2, ImageMap.get("epb01")));
            }else{
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep01").getWidth(null) / 2 / 2 - 5,
                        getY() + image.getHeight(null) / 2, ImageMap.get("epb02")));
            }

        }
        if(fireIndexValue > 1000){
            fireIndex--;
            fireIndexValue = 0;
        }

    }

    @Override
    public void move() {
        index++;
        if(index < indexValue * 5){
            setX(getX() + Xspeed);
        }else if(index >= indexValue * 5 && index < indexValue * 10){
            setX(getX() - Xspeed);
        }else if(index >= indexValue * 10 && index < indexValue * 15){
            setX(getX() + Xspeed);
        }else if(index >= indexValue * 15 && index < indexValue * 20){
            setX(getX() - Xspeed);
        }else{
            index = 0;
        }
        outOfBound();
    }

    public void outOfBound() {
        if (getX() < 0 || getX() > FrameConstant.GAME_WIDTH - image.getWidth(null)) {
            Xspeed = -Xspeed;
        }
        if (getY() > FrameConstant.GAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2);
    }

    public void collisionTest(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);
            gameFrame.gameOver = true;

        }
    }
}