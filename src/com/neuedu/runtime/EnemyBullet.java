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
import java.util.List;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED;
    private double degree = 0.0625;
    //    不同子弹的移动方式
    private int type = 0;
    //    延时子弹
    private int type17 = 0;
    private int type17value = 10;
    //    回转子弹
    private int type21 = 0;
    private int type21value = 10;
    //    分裂弹L
    private int type22 = 0;
    private int type22value = 10;
    //    分裂弹R
    private int type23 = 0;
    private int type23value = 10;
    //    延时散弹
    private int type24 = 0;
    private int type24value = 10;
    private int type25 = 0;
    private int type25value = 10;
    private int type26 = 0;
    private int type26value = 10;


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
        g.drawImage(image, (int) getX(), (int) getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);
    }

    @Override
    public void move() {
        if (type >= 1 && type <= 16) {
            setX(getX() + speed * 3 * Math.cos(degree * (type - 1) * Math.PI * 2));
            setY(getY() + speed * 3 * Math.sin(degree * (type - 1) * Math.PI * 2));
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
        if (type == 21) {
            type21++;
            if (type21 > 0 && type21 <= type21value * 8) {
                setY(getY() + speed * 5);
            } else if (type21 > type21value * 8 && type21 <= type21value * 11) {

            } else {
                setY(getY() - speed * 10);
            }
        }
//        分裂弹L
        if (type == 22) {
            type22++;
            if (type22 > 0 && type22 <= type22value * 10) {
                setY(getY() + speed * 3);
            } else if (type22 > type22value * 10 && type22 <= type22value * 15) {

            } else {
                setX(getX() - speed * 1);
            }
        }
//        分裂弹R
        if (type == 23) {
            type23++;
            if (type23 > 0 && type23 <= type23value * 10) {
                setY(getY() + speed * 3);
            } else if (type23 > type23value * 10 && type23 <= type23value * 15) {

            } else {
                setX(getX() + speed * 1);
            }
        }
//        延时散弹
        if (type == 24) {
            type24++;
            if (type24 > 0 && type24 <= type24value * 5) {
                setY(getY() + speed * 2);
            } else {
                setY(getY() + speed * 3);
            }
        }
        if (type == 25) {
            type25++;
            if (type25 > 0 && type25 <= type25value * 5) {
                setY(getY() + speed * 2);
            } else {
                setX(getX() - speed * 2);
                setY(getY() + speed * 3);
            }
        }
        if (type == 26) {
            type26++;
            if (type26 > 0 && type26 <= type26value * 5) {
                setY(getY() + speed * 2);
            } else {
                setX(getX() + speed * 2);
                setY(getY() + speed * 3);
            }
        }

        outOfBound();

    }

    public void outOfBound() {
        if (getX() < -200 || getX() > FrameConstant.GAME_WIDTH + 100 || getY() < -200 || getY() > FrameConstant.GAME_HEIGHT + 100) {
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

    public void collisionTest(List<Skill> skillList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (Skill skill : skillList) {
            if (skill.getRectangle().intersects(this.getRectangle())) {
                gameFrame.enemyBulletList.remove(this);
            }
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) getX(), (int) getY(), image.getWidth(null) / 2, image.getHeight(null) / 2);
    }
}
