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

    public static int HP;

    private int Xspeed = FrameConstant.GAME_SPEED * 3;
    private int Yspeed = FrameConstant.GAME_SPEED * 2;
    //    BOSS的级别
    public static int level = 1;

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
    private int bulletStyleI = 180;

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
        this(100, -500, ImageMap.get("BOSS0" + GameFrame.level), 0, 0);
    }

    public Boss(int x, int y, Image image, int HP, int level) {
        super(x, y);
        this.image = image;
        this.HP = HP;
        this.level = level;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, (int) getX(), (int) getY(), image.getWidth(null) / 2, image.getHeight(null) / 2, null);
        if (getY() > 0) {
            fire();
        }

    }

    @Override
    public void move() {
        switch (level) {
            case 1:
                move1();
                break;
            case 2:
                move2();
                break;
            case 3:
                move3();
                break;
            case 4:
                move4();
                break;
            case 5:
                move5();
                break;
            case 6:
                move6();
                break;
            case 7:
                move7();
                break;
            case 8:
                move8();
                break;
            case 9:
                move9();
                break;
            default:
                break;
        }
    }

    public void move1() {
        if (getY() > 100) {
            index++;
//            正常移动
            if (index < indexValue * 10) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 10 && index < indexValue * 20) {
                setX(getX() - Xspeed);
            } else if (index >= indexValue * 20 && index < indexValue * 30) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 30 && index < indexValue * 40) {
                setX(getX() - Xspeed);
            } else {
                index = 0;
            }
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void move2() {
        if (getY() > 150) {
            index++;
//            正常移动
            if (index < indexValue * 8) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 8 && index < indexValue * 16) {
                setX(getX() - Xspeed);
            } else if (index >= indexValue * 16 && index < indexValue * 24) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 24 && index < indexValue * 32) {
                setX(getX() - Xspeed);
            } else {
                index = 0;
            }
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void move3() {
        if (getY() > 100) {
            index++;
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
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void move4() {
        if (getY() > 150) {
            index++;
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
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void move5() {
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
            if (spurt > indexValue * 8 && spurt <= indexValue * 9 || spurt > indexValue * 20 && spurt <= indexValue * 21) {
                setY(getY() + Yspeed * 2);
            } else if (spurt > indexValue * 9 && spurt <= indexValue * 10 || spurt > indexValue * 21 && spurt <= indexValue * 22) {
                setY(getY() - Yspeed * 2);
            } else if (spurt > indexValue * 25) {
                spurt = 0;
            }
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void move6() {
        index++;
//            全屏移动
        if (index < indexValue * 5) {
            setX(getX() + Xspeed * 2);
            setY(getY() + Yspeed * 2);
        } else if (index >= indexValue * 5 && index < indexValue * 10) {
            setX(getX() - Xspeed * 2);
            setY(getY() + Yspeed * 2);
        } else if (index >= indexValue * 10 && index < indexValue * 15) {
            setX(getX() + Xspeed * 2);
            setY(getY() + Yspeed * 2);
        } else if (index >= indexValue * 15 && index < indexValue * 20) {
            setX(getX() - Xspeed * 2);
            setY(getY() + Yspeed * 2);
        } else {
            index = 0;
        }
        outOfBound();
    }

    public void move7() {
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
            if (spurt > indexValue * 8 && spurt <= indexValue * 9 || spurt > indexValue * 20 && spurt <= indexValue * 21) {
                setY(getY() + Yspeed * 2);
            } else if (spurt > indexValue * 9 && spurt <= indexValue * 10 || spurt > indexValue * 21 && spurt <= indexValue * 22) {
                setY(getY() - Yspeed * 2);
            } else if (spurt > indexValue * 25) {
                spurt = 0;
            }
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void move8() {
        if (getY() > 100) {
            index++;
            spurt++;
//            正常移动
            if (index < indexValue * 5) {
                setX(getX() + Xspeed / 2);
            } else if (index >= indexValue * 5 && index < indexValue * 10) {
                setX(getX() - Xspeed / 2);
            } else if (index >= indexValue * 10 && index < indexValue * 15) {
                setX(getX() + Xspeed / 2);
            } else if (index >= indexValue * 15 && index < indexValue * 20) {
                setX(getX() - Xspeed / 2);
            } else {
                index = 0;
            }
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void move9() {
        if (getY() > 150) {
            index++;
            spurt++;
//            正常移动
            if (index < indexValue * 5) {
                setX(getX() + Xspeed * 3 / 2);
            } else if (index >= indexValue * 5 && index < indexValue * 10) {
                setX(getX() - Xspeed * 3 / 2);
            } else if (index >= indexValue * 10 && index < indexValue * 15) {
                setX(getX() + Xspeed * 3 / 2);
            } else if (index >= indexValue * 15 && index < indexValue * 20) {
                setX(getX() - Xspeed * 3 / 2);
            } else {
                index = 0;
            }
//            冲刺
            if (spurt > indexValue * 9 && spurt <= indexValue * 10 || spurt > indexValue * 21 && spurt <= indexValue * 22) {
                setY(getY() + Yspeed * 3 / 2);
            } else if (spurt > indexValue * 10 && spurt <= indexValue * 11 || spurt > indexValue * 22 && spurt <= indexValue * 23) {
                setY(getY() - Yspeed * 3 / 2);
            } else if (spurt > indexValue * 25) {
                spurt = 0;
            }
            outOfBound();
        } else {
            setY(getY() + Yspeed);
        }
    }

    public void fire() {
        switch (level) {
            case 1:
                BOSS1();
                break;
            case 2:
                BOSS2();
                break;
            case 3:
                BOSS3();
                break;
            case 4:
                BOSS4();
                break;
            case 5:
                BOSS5();
                break;
            case 6:
                BOSS6();
                break;
            case 7:
                BOSS7();
                break;
            case 8:
                BOSS8();
                break;
            case 9:
                BOSS9();
                break;
            default:
                break;
        }
        if (fireIndexValue > 4000) {
            fireIndex--;
            fireIndexValue = 0;
        }
    }

    public void BOSS1() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire2();
            }
        }
    }

    public void BOSS2() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire3();
            }
        }
    }

    public void BOSS3() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire5();
            }
        }
    }

    public void BOSS4() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire8();
            }
        }
    }

    public void BOSS5() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire7();
            }
        }
    }

    public void BOSS6() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleB) {
                fire4();
            }
        }
    }

    public void BOSS7() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire4();
            }
        }
    }

    public void BOSS8() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire1();
            }
        }
    }

    public void BOSS9() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleA) {
                fire9();
            } else if (r2 > bulletStyleA && r2 <= bulletStyleB) {
                fire4();
            } else if(r2 > bulletStyleB && r2 <= bulletStyleC){
                fire3();
            }else{
                fire2();
            }
        }
    }

    //    菱形弹幕
    public void fire1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 25,
                (int) getY() + image.getHeight(null) / 2 - 25, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 + 25,
                (int) getY() + image.getHeight(null) / 2 - 25, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb01"), 19));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 25,
                (int) getY() + image.getHeight(null) / 2 - 75, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb01"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 + 25,
                (int) getY() + image.getHeight(null) / 2 - 75, ImageMap.get("epb01"), 19));

    }

    //    普通攻击
    public void fire2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));

    }

    //    三发散弹
    public void fire3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 4));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 5));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 6));

    }

    //    天女散花
    public void fire4() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 1));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 2));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 3));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 4));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 5));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 6));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 7));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 8));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 9));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 10));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 11));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 12));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 13));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 14));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 15));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 16));


    }

    //    连发
    public void fire5() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2 - 20, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2 - 40, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2 - 60, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2 - 80, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2 - 20, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2 - 40, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2 - 60, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2 - 80, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));
    }

    //    西红柿导弹
    public void fire6() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb06"), 18));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb06"), 18));
    }

    //    延时发射
    public void fire7() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 80,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 - 40,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 40,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5 + 80,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
    }

    //    回转子弹
    public void fire8() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb07"), 21));
    }

    //    分裂弹
    public void fire9() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        int r1 = r.nextInt(2);
        int a = 0;
        int b = 0;
        if (r1 == 0) {
            a = 22;
            b = 23;
        } else {
            a = 23;
            b = 22;
        }
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 80, ImageMap.get("epb03"), a));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 16, ImageMap.get("epb03"), a));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 + 48, ImageMap.get("epb03"), a));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 + 80, ImageMap.get("epb03"), b));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 + 16, ImageMap.get("epb03"), b));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 48, ImageMap.get("epb03"), b));
    }


    public void outOfBound() {
        if (getX() < 0 || getX() > FrameConstant.GAME_WIDTH - image.getWidth(null) / 2) {
            Xspeed = -Xspeed;
        }
        if (getY() < -200 || getY() > FrameConstant.GAME_HEIGHT - image.getHeight(null) / 2) {
            Yspeed = -Yspeed;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) getX(), (int) getY(), image.getWidth(null) / 2, image.getHeight(null) / 2);
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
