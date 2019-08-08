package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    Random r = new Random();

    //    创建背景对象
    private Background background = new Background();

    //    创建飞机对象
    private Plane plane = new Plane();
    private Plane2 plane2 = new Plane2();
    //    创建技能集合
    public final List<Skill> skillList = new CopyOnWriteArrayList<>();
    //    创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    //    创建敌机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    //   创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //    创建BOSS
    public final List<Boss> bossList = new CopyOnWriteArrayList<>();
    //    创建陨石集合
    public final List<Stones> stoneList = new CopyOnWriteArrayList<>();
    //    创建激光集合
    public final List<Light> lightList = new CopyOnWriteArrayList<>();

    public boolean p1GameOver, p2GameOver;
    //    得分
    public static int score = 0;
    //    BOSS动态血量
    int index = 0;

    @Override
    public void paint(Graphics g) {
        if (!Plane.pause) {
            background.draw(g);
            if (!p1GameOver) {
                plane.draw(g);
            }
            if (!p2GameOver) {
                plane2.draw(g);
            }
            if (enemyPlaneList.isEmpty() && bossList.isEmpty() && Boss.alive == true) {

                stoneList.add(new Stones(100, -500 * 2, ImageMap.get("stone"), 1));
                stoneList.add(new Stones(0, -500 * 4, ImageMap.get("stone"), 1));
                stoneList.add(new Stones(350, -500 * 5, ImageMap.get("stone"), 1));

                lightList.add(new Light(r.nextInt(200) + 100, -500 * 2, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(200) + 220, -500 * 3, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(200), -500 * 4, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(200) + 50, -500 * 5, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(200) + 300, -500 * 12, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(200) + 100, -500 * 20, ImageMap.get("light")));

                lightList.add(new Light(r.nextInt(500), -500 * 50, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(500), -500 * 51, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(500), -500 * 52, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(500), -500 * 53, ImageMap.get("light")));
                lightList.add(new Light(r.nextInt(500), -500 * 54, ImageMap.get("light")));
                bossList.add(new Boss(150, -550, ImageMap.get("BOSS02"), 255));
            }
            if (Boss.alive == false) {
                bossList.clear();
                g.setFont(new Font("黑体", 0, 100));
                g.setColor(Color.RED);
                g.drawString("VICTORY", 100, 350);
            }

            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }

            for (Boss boss : bossList) {
                boss.draw(g);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }

            for (Bullet bullet : bulletList) {
                bullet.collisionTest(enemyPlaneList, bossList, stoneList);
            }

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.collisionTest(plane, plane2);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionTest(plane, plane2);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionTest(skillList);
            }

            for (Boss boss : bossList) {
                boss.collisionTest(plane, plane2);
            }

            for (Stones stones : stoneList) {
                stones.draw(g);

            }

            for (Stones stones : stoneList) {
                stones.collisionTest(plane, plane2);
            }

            for (Light light : lightList) {
                light.draw(g);
            }

            for (Light light : lightList) {
                light.collisionTest(plane, plane2);
            }

            for (Skill skill : skillList) {
                skill.draw(g);
            }

        } else {
            if (Boss.alive && !(p1GameOver && p2GameOver)) {
                g.setFont(new Font("黑体", 0, 100));
                g.setColor(Color.RED);
                g.drawString("PAUSE", 140, 350);
            }
        }
//        画BOSS血量

        if (enemyPlaneList.isEmpty() && !bossList.isEmpty() && Boss.alive == true) {

            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("楷体", 0, 20));
            g.drawString("赤龙X", 80, 60);

            g.setColor(new Color(255, Boss.HP, 0));
            g.drawRect(150, 50, 255, 10);

            if (index < 255) {
                index++;
                g.fillRect(150, 50, index, 10);
            } else {
                g.fillRect(150, 50, Boss.HP, 10);
                index = 260;
            }

        }

        if (Plane2.skill01) {
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("楷体", 0, 20));
            g.drawRect(350, 640, Plane2.skill01time / 4, 10);
            g.fillRect(350, 640, Plane2.s01 / 4, 10);
        }


    }

    public void init() {
        setSize(FrameConstant.GAME_WIDTH, FrameConstant.GAME_HEIGHT);
        setTitle("飞机大战");
        setLocationRelativeTo(null);
        enableInputMethods(false);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
                plane2.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
                plane2.keyReleased(e);
            }
        });


        if (enemyPlaneList.isEmpty()) {
//            不同敌机的生命值
            int a = 30;
            int b = 15;
            int c = 20;
            int d = 50;
            int e = 100;
//            Y坐标的基数
            int y = 500;

            enemyPlaneList.add(new EnemyPlane(100, -y, ImageMap.get("ep02"), b, 2));
            enemyPlaneList.add(new EnemyPlane(200, -y, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(300, -y, ImageMap.get("ep03"), c, 3));

            enemyPlaneList.add(new EnemyPlane(150, -y * 2, ImageMap.get("ep02"), b, 2));
            enemyPlaneList.add(new EnemyPlane(250, -y * 2, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(80, -y * 5, ImageMap.get("ep03"), c, 3));
            enemyPlaneList.add(new EnemyPlane(180, -y * 5, ImageMap.get("ep02"), b, 2));
            enemyPlaneList.add(new EnemyPlane(280, -y * 5, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(380, -y * 5, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(110, -y * 8, ImageMap.get("ep03"), c, 3));
            enemyPlaneList.add(new EnemyPlane(210, -y * 8, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(310, -y * 8, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(100, -y * 10, ImageMap.get("ep03"), c, 3));
            enemyPlaneList.add(new EnemyPlane(150, -y * 10, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(200, -y * 10, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(100, -y * 10, ImageMap.get("ep02"), b, 2));

            enemyPlaneList.add(new EnemyPlane(100, -y * 14, ImageMap.get("ep02"), b, 2));
            enemyPlaneList.add(new EnemyPlane(200, -y * 14, ImageMap.get("ep02"), b, 2));
            enemyPlaneList.add(new EnemyPlane(200, -y * 14, ImageMap.get("ep02"), b, 2));

            enemyPlaneList.add(new EnemyPlane(310, -y * 16, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(150, -y * 16, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(110, -y * 19, ImageMap.get("ep03"), c, 3));
            enemyPlaneList.add(new EnemyPlane(310, -y * 19, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(160, -y * 19, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(110, -y * 23, ImageMap.get("ep03"), c, 3));
            enemyPlaneList.add(new EnemyPlane(360, -y * 23, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(130, -y * 29, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(160, -y * 29, ImageMap.get("ep02"), b, 2));
            enemyPlaneList.add(new EnemyPlane(250, -y * 29, ImageMap.get("ep04"), d, 4));

            enemyPlaneList.add(new EnemyPlane(110, -y * 32, ImageMap.get("ep04"), d, 4));

            enemyPlaneList.add(new EnemyPlane(200, -y * 38, ImageMap.get("BOSS01"), e, 5));
            enemyPlaneList.add(new EnemyPlane(180, -y * 39, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(220, -y * 39, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(160, -y * 40, ImageMap.get("ep03"), b, 3));
            enemyPlaneList.add(new EnemyPlane(240, -y * 40, ImageMap.get("ep03"), b, 3));
            enemyPlaneList.add(new EnemyPlane(150, -y * 41, ImageMap.get("ep04"), d, 4));
            enemyPlaneList.add(new EnemyPlane(250, -y * 41, ImageMap.get("ep04"), d, 4));
            enemyPlaneList.add(new EnemyPlane(300, -y * 50, ImageMap.get("BOSS01"), e, 5));

//            陨石
            stoneList.add(new Stones(200, -y, ImageMap.get("stone"), 1));
            stoneList.add(new Stones(-100, -y * 8, ImageMap.get("stone"), 1));
            stoneList.add(new Stones(400, -y * 8, ImageMap.get("stone"), 1));
            stoneList.add(new Stones(300, -y * 20, ImageMap.get("stone"), 1));
            stoneList.add(new Stones(180, -y * 30, ImageMap.get("stone"), 2));
            stoneList.add(new Stones(300, -y * 20, ImageMap.get("stone"), 1));
//            激光
            lightList.add(new Light(100, -y * 15, ImageMap.get("light")));
            lightList.add(new Light(10, -y * 50, ImageMap.get("light")));
            lightList.add(new Light(400, -y * 50, ImageMap.get("light")));
            lightList.add(new Light(220, -y * 80, ImageMap.get("light")));
            lightList.add(new Light(130, -y * 100, ImageMap.get("light")));
            lightList.add(new Light(355, -y * 103, ImageMap.get("light")));
            lightList.add(new Light(205, -y * 106, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 10, -y * 120, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 300, -y * 125, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 155, -y * 135, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 200, -y * 170, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 150, -y * 180, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 300, -y * 190, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 180, -y * 250, ImageMap.get("light")));
            lightList.add(new Light(r.nextInt(50) + 150, -y * 260, ImageMap.get("light")));

        }

        setVisible(true);


        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    private Image offScreenImage = null;//创建缓冲区

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.GAME_WIDTH, FrameConstant.GAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图

        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }

}


