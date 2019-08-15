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

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int Xspeed = FrameConstant.GAME_SPEED * 3;
    private int Yspeed = FrameConstant.GAME_SPEED * 3;

    //    控制敌机的移动轨迹
    private int index = 0;
    private int indexValue = 50;
    //    控制敌机的攻击
    public int fireIndex = 98;
    private int fireIndexValue = 0;
    //    控制子弹的类型
    private int bulletStyleA = 20;
    private int bulletStyleB = 40;
    private int bulletStyleC = 60;
    private int bulletStyleD = 80;
    //    控制敌机的生命值
    private int HP;
    //    控制敌机的种类
    private int type;
    //    控制敌机的攻击方式
    private int attack;

    Random r = new Random();

    public EnemyPlane() {
        this(0, 0, ImageMap.get("epA1"), 0, 0, 0);
    }

    public EnemyPlane(int x, int y, Image image, int HP, int type, int attack) {
        super(x, y);
        this.image = image;
        this.HP = HP;
        this.type = type;
        this.attack = attack;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
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

    public void fire() {

//        不同种类的敌机不同的攻击方式
//        1.不开火
        if (attack == 1) {
        }
//        2.低速
        if (attack == 2) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex + 1) {
                if (r2 > bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epA1").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epA1").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
                }

            }
        }
//        3.中速
        if (attack == 3) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epA1").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epA1").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
                }

            }
        }
//        4.快速
        if (attack == 4) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex - 1) {
                if (r2 > bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epA1").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epA1").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
                }

            }
        }
//        5.双子球
        if (attack == 5) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
//                    横向双发
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5 - ImageMap.get("epb04").getWidth(null),
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 19));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5 + ImageMap.get("epb04").getWidth(null),
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 19));
                }

            }
        }
//        6.三连发
        if (attack == 6) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > 0 && r2 <= bulletStyleA) {
//                    横向三连发
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5 - 30,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5 + 30,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));

                }
            }
        }
//        7.能量球
        if (attack == 7) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > 0 && r2 <= bulletStyleC) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 19));
                }
            }
        }
//        8.延时发射
        if (attack == 8) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > 0 && r2 <= bulletStyleC) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 3 - 60,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 3 - 30,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 3,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 3 + 30,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epD4").getWidth(null) / 2 / 2 - 3 + 60,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));

                }
            }
        }
//        9.散射
        if (attack == 9) {
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > 0 && r2 <= bulletStyleA) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 4));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 8));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 12));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 16));
                } else if(r2 > bulletStyleA && r2 <= bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 2));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 6));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 10));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 14));

                } else {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("epE1").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 8));
                }
            }
        }

        if (fireIndexValue > 4000) {
            fireIndex--;
            fireIndexValue = 0;
        }
    }

    @Override
    public void move() {
        //        不同种类的敌机不同的移动方式
//        1.前排移动
        if (type == 1) {

            if (getY() > 250) {
                index++;
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
            } else {
                setY(getY() + Yspeed);
            }

        }
//        2.后排移动
        if (type == 2) {

            if (getY() > 150) {
                index++;
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
            } else {
                setY(getY() + Yspeed);
            }


        }
//        3.中间移动
        if (type == 3) {

            if (getY() > 200) {
                index++;
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
            } else {
                setY(getY() + Yspeed);
            }

        }
//        4.后排慢速移动
        if (type == 4) {

            if (getY() > 100) {
                index++;
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
            } else {
                setY(getY() + Yspeed);
            }

        }
//        5.前排冲刺
        if (type == 5) {
            if (getY() > 50) {
                index++;
                if (index < indexValue * 5) {
                    setX(getX() + Xspeed);
                } else if (index >= indexValue * 5 && index < indexValue * 6) {
                    setY(getY() + Yspeed);
                } else if (index >= indexValue * 6 && index < indexValue * 11) {
                    setX(getX() - Xspeed);
                } else if (index >= indexValue * 11 && index < indexValue * 16) {
                    setY(getY() - Yspeed);
                } else {
                    index = 0;
                }
            } else {
                setY(getY() + Yspeed);
            }

        }
//        6.前排快速冲刺
        if (type == 6) {
            if (getY() > 50) {
                index++;
                if (index < indexValue * 5) {
                    setX(getX() + Xspeed * 2);
                } else if (index >= indexValue * 5 && index < indexValue * 6) {
                    setY(getY() + Yspeed * 2);
                } else if (index >= indexValue * 6 && index < indexValue * 11) {
                    setX(getX() - Xspeed * 2);
                } else if (index >= indexValue * 11 && index < indexValue * 14) {
                    setY(getY() - Yspeed * 2);
                } else {
                    index = 0;
                }
            } else {
                setY(getY() + Yspeed);
            }

        }
//        7.前排慢速冲刺
        if (type == 7) {
            if (getY() > 100) {
                index++;
                if (index < indexValue * 5) {
                    setX(getX() + Xspeed / 2);
                } else if (index >= indexValue * 5 && index < indexValue * 7) {
                    setY(getY() + Yspeed / 2);
                } else if (index >= indexValue * 7 && index < indexValue * 12) {
                    setX(getX() - Xspeed / 2);
                } else if (index >= indexValue * 12 && index < indexValue * 17) {
                    setY(getY() - Yspeed / 2);
                } else {
                    index = 0;
                }
            } else {
                setY(getY() + Yspeed);
            }

        }
//        8.最前排冲刺
        if (type == 8) {
            if (getY() > 150) {
                index++;
                if (index < indexValue * 5) {
                    setX(getX() + Xspeed / 3);
                } else if (index >= indexValue * 5 && index < indexValue * 7) {
                    setY(getY() + Yspeed / 3);
                } else if (index >= indexValue * 7 && index < indexValue * 12) {
                    setX(getX() - Xspeed / 3);
                } else if (index >= indexValue * 12 && index < indexValue * 17) {
                    setY(getY() - Yspeed / 3);
                } else {
                    index = 0;
                }
            } else {
                setY(getY() + Yspeed);
            }

        }
//        9.前排悬停
        if (type == 9) {
            if (getY() > 250) {
            } else {
                setY(getY() + Yspeed);
            }

        }
//        10.后排悬停
        if (type == 10) {
            if (getY() > 100) {
            } else {
                setY(getY() + Yspeed);
            }

        }
//        11.径直前冲
        if (type == 11) {
            if (getY() > -200) {
                setY(getY() + Yspeed);
            } else {
                setY(getY() + Yspeed);
            }
        }
//        12.径直快速前冲
        if (type == 12) {
            if (getY() > -200) {
                setY(getY() + Yspeed * 2);
            } else {
                setY(getY() + Yspeed);
            }
        }
//        13.径直慢速前冲
        if (type == 13) {
            if (getY() > -200) {
                setY(getY() + Yspeed / 2);
            } else {
                setY(getY() + Yspeed);
            }
        }

//        14.最前排移动
        if (type == 14) {
            if (getY() > 350) {
                index++;
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
            } else {
                setY(getY() + Yspeed);
            }

        }
//        15.最前排快速移动
        if (type == 15) {

            if (getY() > 300) {
                index++;
                if (index < indexValue * 5) {
                    setX(getX() + Xspeed * 2);
                } else if (index >= indexValue * 5 && index < indexValue * 10) {
                    setX(getX() - Xspeed * 2);
                } else if (index >= indexValue * 10 && index < indexValue * 15) {
                    setX(getX() + Xspeed * 2);
                } else if (index >= indexValue * 15 && index < indexValue * 20) {
                    setX(getX() - Xspeed * 2);
                } else {
                    index = 0;
                }
            } else {
                setY(getY() + Yspeed);
            }
        }

//        16.最前排慢速移动
        if (type == 16) {
            if (getY() > 300) {
                index++;
                if (index < indexValue * 5) {
                    setX(getX() + Xspeed / 3);
                } else if (index >= indexValue * 5 && index < indexValue * 10) {
                    setX(getX() - Xspeed / 3);
                } else if (index >= indexValue * 10 && index < indexValue * 15) {
                    setX(getX() + Xspeed / 3);
                } else if (index >= indexValue * 15 && index < indexValue * 20) {
                    setX(getX() - Xspeed / 3);
                } else {
                    index = 0;
                }
            } else {
                setY(getY() + Yspeed);
            }

        }


        outOfBound();
    }

    public void outOfBound() {
        if (getX() < 0 || getX() > FrameConstant.GAME_WIDTH - image.getWidth(null) / 2) {
            Xspeed = -Xspeed;
        }
        if (getY() > FrameConstant.GAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
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
            plane.setX(-500);
            plane.setY(-500);
            gameFrame.p1GameOver = true;
        }
        if (Plane2.hp == 0) {
            plane2.setX(-500);
            plane2.setY(-500);
            gameFrame.p2GameOver = true;
        }
    }
}
