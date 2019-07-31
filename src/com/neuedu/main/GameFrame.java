package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;
import com.neuedu.runtime.Bullet;
import com.neuedu.runtime.EnemyBullet;
import com.neuedu.runtime.EnemyPlane;
import com.neuedu.runtime.Plane;
import com.neuedu.runtime.Plane2;
import com.neuedu.util.ImageMap;

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

    public boolean p1GameOver, p2GameOver;

    @Override
    public void paint(Graphics g) {
        background.draw(g);
        if (!p1GameOver) {
            plane.draw(g);
        }
        if (!p2GameOver) {
            plane2.draw(g);
        }

        for (Bullet bullet : bulletList) {
            bullet.draw(g);
        }

        for (EnemyPlane enemyPlane : enemyPlaneList) {
            enemyPlane.draw(g);
        }

        for (EnemyBullet enemyBullet : enemyBulletList) {
            enemyBullet.draw(g);
        }

        for (Bullet bullet : bulletList) {
            bullet.collisionTest(enemyPlaneList);
        }

        for (EnemyBullet enemyBullet : enemyBulletList) {
            enemyBullet.collisionTest(plane, plane2);
        }

        for (EnemyPlane enemyPlane : enemyPlaneList) {
            enemyPlane.collisionTest(plane, plane2);
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
            int a = 10;
            int b = 20;
            int c = 30;
            int d = 50;
            int e = 100;
            enemyPlaneList.add(new EnemyPlane(100, -100, ImageMap.get("ep02"), b));
            enemyPlaneList.add(new EnemyPlane(200, -100, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(300, -100, ImageMap.get("ep03"), c));

            enemyPlaneList.add(new EnemyPlane(150, -1000, ImageMap.get("ep02"), b));
            enemyPlaneList.add(new EnemyPlane(250, -1000, ImageMap.get("ep01"), a));

            enemyPlaneList.add(new EnemyPlane(80, -1500, ImageMap.get("ep03"), c));
            enemyPlaneList.add(new EnemyPlane(180, -1500, ImageMap.get("ep02"), b));
            enemyPlaneList.add(new EnemyPlane(280, -1500, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(380, -1500, ImageMap.get("ep01"), a));

            enemyPlaneList.add(new EnemyPlane(110, -2000, ImageMap.get("ep03"), c));
            enemyPlaneList.add(new EnemyPlane(210, -2000, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(310, -2000, ImageMap.get("ep01"), a));

            enemyPlaneList.add(new EnemyPlane(100, -2200, ImageMap.get("ep03"), c));
            enemyPlaneList.add(new EnemyPlane(150, -2400, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(200, -2600, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(100, -2800, ImageMap.get("ep02"), b));

            enemyPlaneList.add(new EnemyPlane(100, -3200, ImageMap.get("ep02"), b));
            enemyPlaneList.add(new EnemyPlane(200, -3200, ImageMap.get("ep02"), b));
            enemyPlaneList.add(new EnemyPlane(200, -3200, ImageMap.get("ep02"), b));

            enemyPlaneList.add(new EnemyPlane(310, -4000, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(150, -4000, ImageMap.get("ep01"), a));

            enemyPlaneList.add(new EnemyPlane(110, -5000, ImageMap.get("ep03"), c));
            enemyPlaneList.add(new EnemyPlane(310, -5000, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(160, -5000, ImageMap.get("ep01"), a));

            enemyPlaneList.add(new EnemyPlane(110, -6000, ImageMap.get("ep03"), c));
            enemyPlaneList.add(new EnemyPlane(360, -6000, ImageMap.get("ep01"), a));

            enemyPlaneList.add(new EnemyPlane(130, -7000, ImageMap.get("ep01"), a));
            enemyPlaneList.add(new EnemyPlane(160, -7000, ImageMap.get("ep02"), b));
            enemyPlaneList.add(new EnemyPlane(250, -7000, ImageMap.get("ep04"), d));

            enemyPlaneList.add(new EnemyPlane(110, -7800, ImageMap.get("ep04"), d));

            enemyPlaneList.add(new EnemyPlane(200, -9000, ImageMap.get("BOSS01"), e));


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


