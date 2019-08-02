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

    private int speed = FrameConstant.GAME_SPEED;
    //    不同子弹的移动方式
    private int type = 0;
//    延时子弹
    private int type17 = 0;
    private int type17value = 10;
//    回转子弹
    private int type21 = 0;
    private int type21value = 10;

    public EnemyBullet() {
    }

    public EnemyBullet(int x, int y, Image image, int typeSpeed) {
        super(x, y);
        this.image = image;
        this.type = typeSpeed;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);
    }

    @Override
    public void move() {
        if (type == 1) {
            setX(getX() + speed * 1);
            setY(getY() - speed * 3);
        }
        if (type == 2) {
            setX(getX() + speed * 2);
            setY(getY() - speed * 2);
        }
        if (type == 3) {
            setX(getX() + speed * 3);
            setY(getY() - speed * 1);
        }
        if (type == 4) {
            setX(getX() + speed * 3);
        }
        if (type == 5) {
            setX(getX() + speed * 3);
            setY(getY() + speed * 1);
        }
        if (type == 6) {
            setX(getX() + speed * 2);
            setY(getY() + speed * 2);
        }
        if (type == 7) {
            setX(getX() + speed * 1);
            setY(getY() + speed * 3);
        }
        if (type == 8) {
            setY(getY() + speed * 3);
        }
        if (type == 9) {
            setX(getX() - speed * 1);
            setY(getY() + speed * 3);
        }
        if (type == 10) {
            setX(getX() - speed * 2);
            setY(getY() + speed * 2);
        }
        if (type == 11) {
            setX(getX() - speed * 3);
            setY(getY() + speed * 1);
        }
        if (type == 12) {
            setX(getX() - speed * 3);
        }
        if (type == 13) {
            setX(getX() - speed * 3);
            setY(getY() - speed * 1);
        }
        if (type == 14) {
            setX(getX() - speed * 2);
            setY(getY() - speed * 2);
        }
        if (type == 15) {
            setX(getX() - speed * 1);
            setY(getY() - speed * 3);
        }
        if (type == 16) {
            setY(getY() - speed * 3);
        }
//        延时发射
        if (type == 17) {
            type17++;
            if (type17 > 0 && type17 <= type17value * 2) {
                setY(getY() - speed * 3);
            } else if (type17 > type17value * 2 && type17 <= type17value * 10) {

            } else {
                setY(getY() + speed * 10);
            }

        }
//        慢速
        if (type == 18) {
            setY(getY() + speed * 2);
        }
//        中速
        if (type == 19) {
            setY(getY() + speed * 6);
        }
//        快速
        if (type == 20) {
            setY(getY() + speed * 10);
        }
//        子弹回转
        if(type == 21){
            type21++;
            if (type21 > 0 && type21 <= type21value * 8) {
                setY(getY() + speed * 5);
            } else if (type21 > type17value * 8 && type21 <= type21value * 11) {

            } else {
                setY(getY() - speed * 10);
            }
        }


        outOfBound();

    }

    public void outOfBound() {
        if (getX() < -100 || getX() > FrameConstant.GAME_WIDTH + 100 || getY() < -100 || getY() > FrameConstant.GAME_HEIGHT + 100) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);
        }
    }

    public void collisionTest(Plane plane, Plane2 plane2) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);
            Plane.hp--;
        }
        if (plane2.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);
            Plane2.hp--;
        }
        if (Plane.hp == 0) {
            gameFrame.p1GameOver = true;
        }
        if (Plane2.hp == 0) {
            gameFrame.p2GameOver = true;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2);
    }
}
