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

public class Skill extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED;

    public Skill() {
        this(0, 0, ImageMap.get("s01"));
    }

    public Skill(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, (int) getX(), (int) getY(), image.getWidth(null), image.getHeight(null), null);
        outOfBound();
    }

    @Override
    public void move() {
        setY(getY() - speed);

    }

    public void outOfBound() {
        if (getY() < FrameConstant.BG_BORDER - image.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) getX(), (int) getY(), image.getWidth(null), image.getHeight(null));
    }

}
