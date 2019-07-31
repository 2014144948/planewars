package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 7;

    public EnemyBullet() {
    }

    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);
        move();
    }

    @Override
    public void move() {
        setY(getY() + speed);
        outOfBound();

    }

    public void outOfBound() {
        if (getY() > FrameConstant.GAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);
        }
    }

    public void collisionTest(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);
            gameFrame.gameOver = true;

        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2);
    }
}
