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

public class Stones extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED;

    public Stones(){
        this(0,0, ImageMap.get("stone"),0);
    }


    public Stones(int x, int y, Image image, int speed) {
        super(x, y);
        this.image = image;
        this.speed = speed;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        outOfBound();

    }

    @Override
    public void move() {
        setY(getY() + speed);

    }

    public void outOfBound() {
        if (getY() > FrameConstant.GAME_HEIGHT + 10) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.stoneList.remove(this);
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(getX(), getY(), image.getWidth(null) / 10 * 9, image.getHeight(null) / 10 * 9);
    }

    public void collisionTest(Plane plane,Plane2 plane2){
//        撞到陨石
        GameFrame gameFrame = DataStore.get("gameFrame");
        if(plane.getRectangle().intersects(this.getRectangle())){
            plane.hp--;
        }
        if(plane2.getRectangle().intersects(this.getRectangle())){
            plane2.hp--;
        }

    }
}
