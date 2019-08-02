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

    Random r = new Random();

    public EnemyPlane() {
        this(0, 0, ImageMap.get("ep01"), 5, 0);
    }

    public EnemyPlane(int x, int y, Image image, int HP, int type) {
        super(x, y);
        this.image = image;
        this.HP = HP;
        this.type = type;
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
        if (type == 1) {

            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
                }

            }

        }
        if (type == 2) {

            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
                }

            }

        }
        if (type == 3) {

            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep01").getWidth(null) / 2 / 2 - 5,
                        getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
            }

        }
        if (type == 4) {

            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > bulletStyleB) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep04").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                } else {
//                    横向双发
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep04").getWidth(null) / 2 / 2 - 5 - ImageMap.get("epb04").getWidth(null),
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 19));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("ep04").getWidth(null) / 2 / 2 - 5 + ImageMap.get("epb04").getWidth(null),
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb04"), 19));
                }

            }


        }
        if (type == 5) {

            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            fireIndexValue++;
            GameFrame gameFrame = DataStore.get("gameFrame");
            if (r1 > fireIndex) {
                if (r2 > 0 && r2 <= bulletStyleA) {
//                    横向三连发
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5 - 30,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5 + 30,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 19));

                } else if (r2 > bulletStyleA && r2 <= bulletStyleB) {
//                    横向双发
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5 - 20,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5 + 20,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb02"), 18));

                } else if (r2 > bulletStyleB && r2 <= bulletStyleC) {
//                    能量球
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5 - 10,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb03"), 19));

                }else if(r2 > bulletStyleC && r2 <= bulletStyleD){
//                    延时散弹
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 24));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 25));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 5,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb01"), 26));

                }
                else {
//                    延时发射
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 3 - 40,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 3 - 20,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 3,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 3 + 20,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + image.getWidth(null) / 2 - ImageMap.get("BOSS01").getWidth(null) / 2 / 2 - 3 + 40,
                            getY() + image.getHeight(null) / 2, ImageMap.get("epb05"), 17));

                }

            }


        }


        if (fireIndexValue > 2000) {
            fireIndex--;
            fireIndexValue = 0;
        }

    }

    @Override
    public void move() {

        //        不同种类的敌机不同的移动方式
        if (type == 1) {

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
        if (type == 2) {

            if (getY() > 100) {
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
        if (type == 3) {


            if (getY() > 100) {
                index++;
                if (index < indexValue * 2) {
                    setX(getX() + Xspeed * 2);
                } else if (index >= indexValue * 2 && index < indexValue * 4) {
                    setY(getY() + Yspeed * 1);
                } else if (index >= indexValue * 4 && index < indexValue * 6) {
                    setX(getX() - Xspeed * 2);
                } else if (index >= indexValue * 6 && index < indexValue * 8) {
                    setY(getY() - Yspeed * 1);
                } else {
                    index = 0;
                }
            } else {
                setY(getY() + Yspeed);
            }

        }
        if (type == 4) {


            if (getY() > 210) {
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
        if (type == 5) {


            if (getY() > 100) {
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
