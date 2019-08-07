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
import java.util.List;

public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 10;

    public Bullet() {
        this(0, 0, ImageMap.get("mb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
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
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTest(List<EnemyPlane> enemyPlaneList, List<Boss> bossList, List<Stones> stonesList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
//        打敌机
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                gameFrame.bulletList.remove(this);
                enemyPlane.setHP(enemyPlane.getHP() - 1);
                gameFrame.score += 1;
                if (enemyPlane.getHP() == 0) {
                    enemyPlaneList.remove(enemyPlane);
                    gameFrame.score += 10;
                }
            }
        }
//        打BOSS
        for (Boss boss : bossList) {
            if (boss.getRectangle().intersects(this.getRectangle())) {
                gameFrame.bulletList.remove(this);
                boss.setHP(boss.getHP() - 1);
                gameFrame.score += 5;
                if (boss.getHP() == 0) {
                    boss.setAlive(false);
                    gameFrame.score += 100;
                }
            }
        }
//        打陨石
        for (Stones stones : stonesList) {
            if(stones.getRectangle().intersects(this.getRectangle())){
                gameFrame.bulletList.remove(this);
            }
        }
    }
}
