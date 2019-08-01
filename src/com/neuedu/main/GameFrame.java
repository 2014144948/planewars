package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;
import com.neuedu.runtime.Boss;
import com.neuedu.runtime.Bullet;
import com.neuedu.runtime.EnemyBullet;
import com.neuedu.runtime.EnemyPlane;
import com.neuedu.runtime.Plane;
import com.neuedu.runtime.Plane2;
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
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    //    创建背景对象
    private Background background = new Background();

    //    创建飞机对象
    private Plane plane = new Plane();
    private Plane2 plane2 = new Plane2();
    //    创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    //    创建敌机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    //   创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //    创建BOSS
    public final List<Boss> bossList = new CopyOnWriteArrayList<>();

    public boolean p1GameOver, p2GameOver;
    //    得分
    public static int score = 0;

    @Override
    public void paint(Graphics g) {
        background.draw(g);
        if (!p1GameOver) {
            plane.draw(g);
        }
        if (!p2GameOver) {
            plane2.draw(g);
        }
        if (enemyPlaneList.isEmpty() && bossList.isEmpty() && Boss.alive == true) {
            bossList.add(new Boss(100, -500, ImageMap.get("BOSS02"), 300));
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
            bullet.collisionTest(enemyPlaneList, bossList);
        }

        for (EnemyPlane enemyPlane : enemyPlaneList) {
            enemyPlane.collisionTest(plane, plane2);
        }

        for (EnemyBullet enemyBullet : enemyBulletList) {
            enemyBullet.collisionTest(plane, plane2);
        }

        for (Boss boss : bossList) {
            boss.collisionTest(plane, plane2);
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
            int a = 15;
            int b = 25;
            int c = 40;
            int d = 60;
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

            enemyPlaneList.add(new EnemyPlane(310, -y * 18, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(150, -y * 18, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(110, -y * 20, ImageMap.get("ep03"), c, 3));
            enemyPlaneList.add(new EnemyPlane(310, -y * 20, ImageMap.get("ep01"), a, 1));
            enemyPlaneList.add(new EnemyPlane(160, -y * 20, ImageMap.get("ep01"), a, 1));

            enemyPlaneList.add(new EnemyPlane(110, -y * 25, ImageMap.get("ep03"), c, 3));
            enemyPlaneList.add(new EnemyPlane(360, -y * 25, ImageMap.get("ep01"), a, 1));

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


