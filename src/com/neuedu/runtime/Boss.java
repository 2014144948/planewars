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
            if (index < indexValue * 7) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 7 && index < indexValue * 14) {
                setX(getX() - Xspeed);
            } else if (index >= indexValue * 14 && index < indexValue * 21) {
                setX(getX() + Xspeed);
            } else if (index >= indexValue * 21 && index < indexValue * 28) {
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
            if (index < indexValue * 8) {
                setX(getX() + Xspeed / 2);
            } else if (index >= indexValue * 8 && index < indexValue * 16) {
                setX(getX() - Xspeed / 2);
            } else if (index >= indexValue * 16 && index < indexValue * 24) {
                setX(getX() + Xspeed / 2);
            } else if (index >= indexValue * 24 && index < indexValue * 32) {
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

    /**
     * 普通攻击，双发攻击
     */
    public void BOSS1() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleF) {
                fire1_1();
            } else {
                fire1_2();
            }
        }
    }

    /**
     * 三发散弹，六发散弹，七发散弹
     */
    public void BOSS2() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleD) {
                fire2_1();
            } else if (r2 > bulletStyleD && r2 <= bulletStyleG) {
                fire2_2();
            } else {
                fire2_3();
            }
        }
    }

    /**
     * 急速连射，风暴利刃，风暴连斩
     */
    public void BOSS3() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            int index = 0;
            if (r2 > 0 && r2 <= bulletStyleC) {
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            fire3_1();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }.start();

            } else if (r2 > bulletStyleC && r2 <= bulletStyleF) {
                fire3_2();
            } else {
                fire3_3();
            }
        }
    }

    /**
     * 虚空离子球，星光散射，反弹
     */
    private int boss4_4 = 1;

    public void BOSS4() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleC) {
                fire4_1();
            } else if (r2 > bulletStyleC && r2 <= bulletStyleF) {
                fire4_2();
            } else if (r2 > bulletStyleF && r2 <= bulletStyleH) {
                fire4_3();
            } else {
                new Thread() {
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            if (boss4_4 < 17) {
                                fire4_4();
                                boss4_4++;
                            } else {
                                boss4_4 = 1;
                            }
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }.start();

            }
        }
    }

    /**
     * 天女散花，聚能光线，双发光线
     */
    public void BOSS5() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleD) {
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            fire5_1();
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            } else if (r2 > bulletStyleD && r2 <= bulletStyleH) {
                fire5_2();
            } else {
                fire5_3();
            }
        }
    }

    /**
     * 虚空天女散花
     */
    public void BOSS6() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleF) {
                fire6_1();
            }
        }
    }

    /**
     * 西红柿导弹，恶魔召唤，分裂弹，星光爆裂，三相射击
     */
    int boss7_5 = -1;

    public void BOSS7() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleB) {
                fire7_1();
            } else if (r2 > bulletStyleB && r2 <= bulletStyleD) {
                fire7_2();
            } else if (r2 > bulletStyleD && r2 <= bulletStyleF) {
                fire7_3();
            } else if (r2 > bulletStyleF && r2 <= bulletStyleH) {
                fire7_4();
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        boss7_5 = r.nextInt(3);
                        for (int i = 0; i < 30; i++) {
                            fire7_5();
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        }
    }

    /**
     * 菱形弹幕，矩形弹幕，延时发射，星光散射X，星光散射EX
     */
    public void BOSS8() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleB) {
                fire8_1();
            } else if (r2 > bulletStyleB && r2 <= bulletStyleD) {
                fire8_2();
            } else if (r2 > bulletStyleD && r2 <= bulletStyleF) {
                fire8_3();
            } else if (r2 > bulletStyleF && r2 <= bulletStyleH) {
                fire8_4();
            } else {
                fire8_5();
            }
        }
    }

    /**
     * 聚能光线，终极散弹，星光离子球，恶魔之阵
     */
    public void BOSS9() {
        int r1 = r.nextInt(100);
        int r2 = r.nextInt(200);
        fireIndexValue++;
        if (r1 > fireIndex) {
            if (r2 > 0 && r2 <= bulletStyleB) {
                fire9_1();
            } else if (r2 > bulletStyleB && r2 <= bulletStyleD) {
                fire9_2();
            } else if (r2 > bulletStyleD && r2 <= bulletStyleF) {
                fire9_3();
            } else if (r2 > bulletStyleF && r2 <= bulletStyleH) {
                fire9_4();
            } else {
                fire9_5();
            }
        }
    }


    /**
     * 普通攻击
     */
    public void fire1_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
    }

    /**
     * 双发攻击
     */
    public void fire1_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5 - 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5 + 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
    }

    /**
     * 三发散弹
     */
    public void fire2_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 4));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 5));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 6));

    }

    /**
     * 四发散弹
     */
    public void fire2_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 2));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 4));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 6));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 8));

    }

    /**
     * 七发散弹
     */
    public void fire2_3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 2));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 3));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 4));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 5));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 6));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 7));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS02").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 8));

    }

    /**
     * 急速连射
     */
    public void fire3_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS03").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS03").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));
    }

    /**
     * 风暴利刃
     */
    public void fire3_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS03").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb08"), 18));
    }

    /**
     * 风暴连斩
     */
    public void fire3_3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS03").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb08"), 3));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS03").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb08"), 5));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS03").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb08"), 7));
    }

    /**
     * 虚空离子球
     */
    public void fire4_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb07"), 21));
    }

    /**
     * 星光散射
     */
    public void fire4_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        int r1 = r.nextInt(2) + 1;
        for (int i = r1; i < 17; i += 2) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb11"), i));
        }
    }

    /**
     * 反弹
     */
    public void fire4_3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 38));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 39));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 40));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 38));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 39));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 40));
    }

    /**
     * 旋转射击
     */
    public void fire4_4() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS04").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb11"), boss4_4));
    }

    /**
     * 天女散花
     */
    public void fire5_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (int i = 1; i < 17; i++) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS05").getWidth(null) / 2 / 2 - 5,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), i));
        }
    }

    /**
     * 聚能光线
     */
    public void fire5_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS05").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb09"), 20));
    }

    /**
     * 双发光线
     */
    public void fire5_3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS05").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb09"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS05").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb09"), 19));
    }


    /**
     * 虚空天女散花
     */
    public void fire6_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        int r1 = r.nextInt(2) + 1;
        for (int i = r1; i < 17; i += 2) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS06").getWidth(null) / 2 / 2,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb07"), i));
        }
    }


    /**
     * 西红柿导弹
     */
    public void fire7_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 - 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb06"), 18));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 + 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb06"), 18));
    }

    /**
     * 恶魔召唤
     */
    public void fire7_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (int i = 1; i < 10; i++) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 - 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epE1"), i));
        }

    }

    /**
     * 分裂弹
     */
    public void fire7_3() {
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
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 80, ImageMap.get("epb03"), a));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 16, ImageMap.get("epb03"), a));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 + 48, ImageMap.get("epb03"), a));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 + 80, ImageMap.get("epb03"), b));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 + 16, ImageMap.get("epb03"), b));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 48, ImageMap.get("epb03"), b));
    }

    /**
     * 星光爆裂
     */
    public void fire7_4() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (int i = 1; i < 22; i++) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 - 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb12"), i));
        }
    }

    /**
     * 三相射击
     */
    public void fire7_5() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss7_5 == 0) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 - 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 + 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 20));
        } else if (boss7_5 == 1) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 3));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 5));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 7));
        } else {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 - 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 4));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 + 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 4));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 - 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 6));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS07").getWidth(null) / 2 / 2 - 5 + 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 6));
        }
    }

    /**
     * 菱形弹幕
     */
    public void fire8_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb11"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 25,
                (int) getY() + image.getHeight(null) / 2 - 25, ImageMap.get("epb12"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb11"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 + 25,
                (int) getY() + image.getHeight(null) / 2 - 25, ImageMap.get("epb12"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb11"), 19));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 25,
                (int) getY() + image.getHeight(null) / 2 - 75, ImageMap.get("epb12"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb11"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 + 25,
                (int) getY() + image.getHeight(null) / 2 - 75, ImageMap.get("epb12"), 19));

    }

    /**
     * 矩形弹幕
     */
    public void fire8_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb11"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb12"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb11"), 19));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb12"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb12"), 19));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 50,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb11"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb12"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 + 50,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb11"), 19));

    }

    /**
     * 延时发射
     */
    public void fire8_3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 - 160,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 - 120,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 - 80,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 - 40,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 + 40,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 + 80,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 + 120,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 + 160,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb05"), 17));
    }

    /**
     * 星光散射X
     */
    public void fire8_4() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        int r1 = r.nextInt(2) + 1;
        for (int i = r1; i < 17; i += 2) {
            int r2 = r.nextInt(3) + 11;
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 - 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb" + r2), i));
        }
    }

    /**
     * 星光散射EX
     */
    public void fire8_5() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (int i = 27; i < 43; i++) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS08").getWidth(null) / 2 / 2 - 5 - 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb13"), i));
        }
    }

    /**
     * 聚能光线
     */
    public void fire9_1() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 - 200,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb09"), 18));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb09"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb09"), 20));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb09"), 19));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 + 200,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb09"), 18));


    }

    /**
     * 终极散弹
     */
    public void fire9_2() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (int i = 1; i < 17; i++) {
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 - 100,
                    (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb04"), i));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 + 50,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), i));
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 + 100,
                    (int) getY() + image.getHeight(null) / 2 - 50, ImageMap.get("epb04"), i));
        }
    }

    /**
     * 星光离子球
     */
    public void fire9_3() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (int i = 1; i < 10; i++) {
            String value = null;
            if (i % 2 == 0) {
                value = "epb07";
            } else {
                value = "epb13";
            }
            gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5,
                    (int) getY() + image.getHeight(null) / 2, ImageMap.get(value), i));
        }


    }

    /**
     * 恶魔之阵
     */
    public void fire9_4() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb10"), 3));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 - 100,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb10"), 7));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb10"), 3));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5 + 100,
                (int) getY() + image.getHeight(null) / 2 - 100, ImageMap.get("epb10"), 7));

        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb10"), 2));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb10"), 5));
        gameFrame.enemyBulletList.add(new EnemyBullet((int) getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS09").getWidth(null) / 2 / 2 - 5,
                (int) getY() + image.getHeight(null) / 2, ImageMap.get("epb10"), 8));
    }

    /**
     * 召唤守卫
     */
    public void fire9_5() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        int r1 = r.nextInt(500);
        gameFrame.enemyPlaneList.add(new EnemyPlane(r1, -100, ImageMap.get("epE1"), 10, 11, 1));
        gameFrame.enemyPlaneList.add(new EnemyPlane(r1, -100, ImageMap.get("epE1"), 10, 11, 1));
        gameFrame.enemyPlaneList.add(new EnemyPlane(r1, -100, ImageMap.get("epE6"), 10, 9, 3));

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
