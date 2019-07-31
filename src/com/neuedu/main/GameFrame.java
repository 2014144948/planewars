package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;
import com.neuedu.runtime.Bullet;
import com.neuedu.runtime.EnemyBullet;
import com.neuedu.runtime.EnemyPlane;
import com.neuedu.runtime.Plane;
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
    //    创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    //    创建敌机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    //   创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    public boolean gameOver;

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            background.draw(g);
            plane.draw(g);
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
                enemyBullet.collisionTest(plane);
            }

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.collisionTest(plane);
            }
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
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });

        enemyPlaneList.add(new EnemyPlane(10, 200, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(110, 200, ImageMap.get("ep02")));
        enemyPlaneList.add(new EnemyPlane(210, 200, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(310, 200, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(50, 100, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(150, 100, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(250, 100, ImageMap.get("ep02")));
        enemyPlaneList.add(new EnemyPlane(350, 100, ImageMap.get("ep01")));

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


