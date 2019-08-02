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

public class Boss extends BaseSprite implements Drawable, Moveable {

    private Image image;

    public static boolean alive = true;

    private static int HP;

    private int Xspeed = FrameConstant.GAME_SPEED * 3;
    private int Yspeed = FrameConstant.GAME_SPEED * 2;

    private int index = 0;

    private int indexValue = 50;

    Random r = new Random();

    private int fireIndexValue = 50;
    private int fireIndex = 98;

    //    前冲
    private int spurt = 0;

    private int bulletStyleA = 20;
    private int bulletStyleB = 40;
    private int bulletStyleC = 60;
    private int bulletStyleD = 80;
    private int bulletStyleE = 100;
    private int bulletStyleF = 120;
    private int bulletStyleG = 140;
    private int bulletStyleH = 160;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private int type;

    public Boss() {
        this(100, -500, ImageMap.get("BOSS02"), 0);
    }

    public Boss(int x, int y, Image image, int HP) {
        super(x, y);
        this.image = image;
        this.HP = HP;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);
        if (getY() > 0) {
            fire();
        }

    }

    @Override
    public void move() {

        if (getY() > 100) {
            index++;
            spurt++;
//            正常移动
            if (index < indexValue * 5) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 5 && index < indexValue * 10) {
                setX(getX() - Xspeed);
            } else if (index >= indexValue * 10 && index < indexValue * 15) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 15 && index < indexValue * 20) {
                setX(getX() - Xspeed);
            } else {
                index = 0;
            }
//            冲刺
            if (spurt > indexValue * 8 && spurt <= indexValue * 9) {
                setY(getY() + Yspeed * 2);
            } else if (spurt > indexValue * 9 && spurt <= indexValue * 10) {
                setY(getY() - Yspeed * 2);
            } else if (spurt > indexValue * 10) {
                spurt = 0;
            }

        } else {
            setY(getY() + Yspeed);
        }

        outOfBound();
    }

    public void fire() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (r1 > fireIndex) {
//            BOSS的八种攻击方式
            if (r2 > 0 && r2 <= bulletStyleA) {
                fire1();
            } else if (r2 > bulletStyleA && r2 <= bulletStyleB) {
                fire2();
            } else if (r2 > bulletStyleB && r2 <= bulletStyleC) {
                fire3();
            } else if (r2 > bulletStyleC && r2 <= bulletStyleD) {
                fire4();
            } else if (r2 > bulletStyleD && r2 <= bulletStyleE) {
                fire6();
            } else if (r2 > bulletStyleE && r2 <= bulletStyleF) {
                fire7();
            } else if (r2 > bulletStyleF && r2 <= bulletStyleG) {
                fire5();
            } else if (r2 > bulletStyleG && r2 <= bulletStyleH) {
                fire8();
            } else {
            }
        }

        if (fireIndexValue > 1000) {
            fireIndex--;
            fireIndexValue = 0;
        }
    }

    //    V型弹幕
    public void fire1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 50,
                getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 25,
                getY() + image.getHeight(null) / 2 - 25, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 + 25,
                getY() + image.getHeight(null) / 2 - 25, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 + 50,
                getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb01"), 19));

    }

    //    普通攻击
    public void fire2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));

    }

    //    三发散弹
    public void fire3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 7));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 8));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 9));


    }

    //    天女散花
    public void fire4() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 1));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 2));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 3));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 4));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 5));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 6));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 7));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 8));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 9));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 10));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 11));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 12));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 13));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 14));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 15));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 16));


    }

    //    连发
    public void fire5() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                getY() + image.getHeight(null) / 2 - 20, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                getY() + image.getHeight(null) / 2 - 40, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                getY() + image.getHeight(null) / 2 - 60, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                getY() + image.getHeight(null) / 2 - 80, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));

        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                getY() + image.getHeight(null) / 2 - 20, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                getY() + image.getHeight(null) / 2 - 40, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                getY() + image.getHeight(null) / 2 - 60, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                getY() + image.getHeight(null) / 2 - 80, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));
    }

    //    大型子弹
    public void fire6() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 50,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb06"), 18));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 50,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb06"), 18));
    }

    //    延时发射
    public void fire7() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 80,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 40,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 40,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 80,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
    }

//    回转子弹
    public void fire8(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                getY() + image.getHeight(null) / 2, ImageMap.get("epb07"), 21));
    }


    public void outOfBound() {
        if (getX() < 0 || getX() > FrameConstant.GAME_WIDTH - image.getWidth(null) / 2) {
            Xspeed = -Xspeed;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2);
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

}
