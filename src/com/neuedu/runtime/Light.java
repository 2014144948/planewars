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

public class Light extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed;

    public Light(){
        this(0,0, ImageMap.get("light"),0);
    }


    public Light(int x, int y, Image image,int speed) {
        super(x, y);
        this.image = image;
        this.speed = speed;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, (int) getX(), (int) getY(), image.getWidth(null), image.getHeight(null), null);
        outOfBound();

    }

    @Override
    public void move() {
        setY(getY() + speed);

    }

    public void outOfBound() {
        if (getY() > FrameConstant.GAME_HEIGHT + 100) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.lightList.remove(this);
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle((int) getX(), (int) getY(), image.getWidth(null) / 10 * 8, image.getHeight(null));
    }

    public void collisionTest(Plane plane,Plane2 plane2){
//        撞到激光
        GameFrame gameFrame = DataStore.get("gameFrame");
        if(plane.getRectangle().intersects(this.getRectangle())){
            plane.hp--;
        }
        if(plane2.getRectangle().intersects(this.getRectangle())){
            plane2.hp--;
        }

    }
}
