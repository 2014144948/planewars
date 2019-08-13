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

    //    创建背景集合
    public final List<Background> backgroundList = new CopyOnWriteArrayList<>();

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
    //    当前关卡
    public static int level = 0;
    //    是否过关
    private boolean levelUp;
    //    不同敌机的生命值
    int a = 10;
    int b = 20;
    int c = 30;
    int d = 40;
    int e = 50;
    int f = 60;
    int g = 70;
    int h = 80;
    int i = 90;
    int j = 100;
    //    Y坐标的基数
    int y = 500;
    //    陨石的速度
    int stoneSpeed = 1;
    //    激光的速度
    int lightSpeed = 15;

    @Override
    public void paint(Graphics g) {
        if (!Plane.pause) {
            for (Background background : backgroundList) {
                background.draw(g);
            }
            if (!p1GameOver) {
                plane.draw(g);
            }
            if (!p2GameOver) {
                plane2.draw(g);
            }
            if (enemyPlaneList.isEmpty() && bossList.isEmpty() && Boss.alive == true) {
                bossList.add(new Boss(150, -550, ImageMap.get("BOSS0" + level), 255));

            }
            if (bossList.isEmpty() == false) {
//                BOSS血量显示
                if (Boss.HP < 0) {
                    Boss.HP = 0;
                }
                g.setColor(new Color(255, Boss.HP, 0));
                g.drawRect(180, 50, 255, 10);
                g.fillRect(180, 50, Boss.HP, 10);
            }

            if (Boss.alive == false) {
                bossList.clear();
                stoneList.clear();
                lightList.clear();
                if (level < 9) {
                    backgroundList.clear();
                }
                levelUp = true;
                if (backgroundList.isEmpty() && level < 10) {
                    level++;
                    backgroundList.add(new Background(0, FrameConstant.GAME_HEIGHT - ImageMap.get("bg0" + level).getHeight(null), level, ImageMap.get("bg0" + level)));
                    levelUp = false;
                    Boss.alive = true;
                }
                if (level > 9 && bossList.isEmpty()) {
                    g.setFont(new Font("黑体", 0, 100));
                    g.setColor(Color.RED);
                    g.drawString("VICTORY", 100, 350);
                    enemyPlaneList.add(new EnemyPlane(0, -y * y * y, ImageMap.get("epA1"), a, 1, 1));
                }
            }
            if (enemyPlaneList.isEmpty() && bossList.isEmpty() && stoneList.isEmpty() && lightList.isEmpty()) {
                switch (level) {
                    case 2:
                        level2();
                        break;
                    case 3:
                        level3();
                        break;
                    case 4:
                        level4();
                        break;
                    case 5:
                        level5();
                        break;
                    case 6:
                        level6();
                        break;
                    case 7:
                        level7();
                        break;
                    case 8:
                        level8();
                        break;
                    case 9:
                        level9();
                        break;
                    default:
                        break;
                }

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
            switch (level) {
                case 1:
                    g.drawString("青龙X", 90, 60);
                    break;
                case 2:
                    g.drawString("尖峰X", 90, 60);
                    break;
                case 3:
                    g.drawString("风暴X", 90, 60);
                    break;
                case 4:
                    g.drawString("毁灭者", 90, 60);
                    break;
                case 5:
                    g.drawString("赤龙X", 90, 60);
                    break;
                case 6:
                    g.drawString("虚空幻影", 90, 60);
                    break;
                case 7:
                    g.drawString("恶魔之眼", 90, 60);
                    break;
                case 8:
                    g.drawString("钢铁之臂", 90, 60);
                    break;
                case 9:
                    g.drawString("艾利欧格", 90, 60);
                    break;
            }

        }

//        关卡提示
        g.setColor(new Color(0, 255, 0));
        g.setFont(new Font("楷体", 0, 20));
        g.drawString("level:" + level, 10, 60);

        if (Plane.skill01) {
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("楷体", 0, 20));
            g.drawRect(20, 640, Plane.skill01time * 2, 10);
            g.fillRect(20, 640, Plane.s01 * 2, 10);
        }

        if (Plane2.skill01) {
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("楷体", 0, 20));
            g.drawRect(340, 640, Plane2.skill01time / 3, 10);
            g.fillRect(340, 640, Plane2.s01 / 3, 10);
        }


    }

    public void level2() {
        enemyPlaneList.add(new EnemyPlane(50, -y, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(150, -y, ImageMap.get("epA2"), b, 2, 2));
        enemyPlaneList.add(new EnemyPlane(250, -y, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(60, -y * 2, ImageMap.get("epA3"), c, 3, 6));
        enemyPlaneList.add(new EnemyPlane(160, -y * 2, ImageMap.get("epB1"), a, 5, 2));
        enemyPlaneList.add(new EnemyPlane(260, -y * 3, ImageMap.get("epC1"), a, 9, 1));
        enemyPlaneList.add(new EnemyPlane(100, -y * 4, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(300, -y * 5, ImageMap.get("epB2"), b, 8, 2));
        enemyPlaneList.add(new EnemyPlane(100, -y * 6, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(10, -y * 7, ImageMap.get("epC1"), a, 9, 1));
        enemyPlaneList.add(new EnemyPlane(110, -y * 7, ImageMap.get("epA2"), b, 2, 2));
        enemyPlaneList.add(new EnemyPlane(210, -y * 7, ImageMap.get("epA3"), c, 3, 6));
        enemyPlaneList.add(new EnemyPlane(310, -y * 7, ImageMap.get("epB2"), b, 8, 2));
        enemyPlaneList.add(new EnemyPlane(400, -y * 8, ImageMap.get("epB1"), a, 5, 2));
        enemyPlaneList.add(new EnemyPlane(220, -y * 10, ImageMap.get("epA1"), a, 1, 2));
    }

    public void level3() {
        stoneList.add(new Stones(0, -y * 3, ImageMap.get("stone"), stoneSpeed));

        enemyPlaneList.add(new EnemyPlane(50, -y, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(150, -y, ImageMap.get("epB2"), b, 8, 2));
        enemyPlaneList.add(new EnemyPlane(250, -y, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(60, -y * 3, ImageMap.get("epC1"), c, 9, 1));
        enemyPlaneList.add(new EnemyPlane(160, -y * 3, ImageMap.get("epB1"), a, 5, 2));
        enemyPlaneList.add(new EnemyPlane(260, -y * 4, ImageMap.get("epC1"), b, 9, 1));
        enemyPlaneList.add(new EnemyPlane(100, -y * 5, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(300, -y * 6, ImageMap.get("epB2"), b, 8, 2));
        enemyPlaneList.add(new EnemyPlane(100, -y * 6, ImageMap.get("epA1"), a, 1, 2));
        enemyPlaneList.add(new EnemyPlane(10, -y * 7, ImageMap.get("epC1"), a, 9, 1));
        enemyPlaneList.add(new EnemyPlane(110, -y * 8, ImageMap.get("epC2"), b, 10, 2));
        enemyPlaneList.add(new EnemyPlane(210, -y * 8, ImageMap.get("epC2"), c, 10, 2));
        enemyPlaneList.add(new EnemyPlane(310, -y * 10, ImageMap.get("epB2"), b, 8, 2));
        enemyPlaneList.add(new EnemyPlane(200, -y * 10, ImageMap.get("epD1"), c, 12, 7));
        enemyPlaneList.add(new EnemyPlane(220, -y * 10, ImageMap.get("epA1"), a, 1, 2));
    }

    public void level4() {
        stoneList.add(new Stones(0, -y * 2, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(100, -y * 6, ImageMap.get("stone"), stoneSpeed * 2));
        stoneList.add(new Stones(300, -y * 8, ImageMap.get("stone"), stoneSpeed));

        enemyPlaneList.add(new EnemyPlane(100, -y, ImageMap.get("epA2"), b, 2, 2));
        enemyPlaneList.add(new EnemyPlane(400, -y, ImageMap.get("epC1"), a, 9, 1));
        enemyPlaneList.add(new EnemyPlane(150, -y * 2, ImageMap.get("epC3"), c, 9, 3));
        enemyPlaneList.add(new EnemyPlane(200, -y * 3, ImageMap.get("epB1"), a, 5, 2));
        enemyPlaneList.add(new EnemyPlane(300, -y * 3, ImageMap.get("epA2"), b, 2, 2));
        enemyPlaneList.add(new EnemyPlane(100, -y * 5, ImageMap.get("epA3"), d, 3, 6));
        enemyPlaneList.add(new EnemyPlane(200, -y * 5, ImageMap.get("epB1"), a, 5, 2));
        enemyPlaneList.add(new EnemyPlane(300, -y * 5, ImageMap.get("epB2"), b, 8, 2));
        enemyPlaneList.add(new EnemyPlane(10, -y * 7, ImageMap.get("epC2"), a, 10, 2));
        enemyPlaneList.add(new EnemyPlane(350, -y * 7, ImageMap.get("epA3"), d, 3, 6));
        enemyPlaneList.add(new EnemyPlane(250, -y * 10, ImageMap.get("epC1"), a, 9, 1));
        enemyPlaneList.add(new EnemyPlane(280, -y * 10, ImageMap.get("epC2"), a, 10, 2));
        enemyPlaneList.add(new EnemyPlane(120, -y * 11, ImageMap.get("epC3"), c, 9, 3));
        enemyPlaneList.add(new EnemyPlane(150, -y * 12, ImageMap.get("epB3"), b, 7, 3));
        enemyPlaneList.add(new EnemyPlane(350, -y * 12, ImageMap.get("epA2"), b, 2, 2));
        enemyPlaneList.add(new EnemyPlane(300, -y * 14, ImageMap.get("epA2"), a, 2, 2));
        enemyPlaneList.add(new EnemyPlane(200, -y * 15, ImageMap.get("epB2"), b, 8, 2));
        enemyPlaneList.add(new EnemyPlane(350, -y * 15, ImageMap.get("epB3"), c, 7, 3));
    }

    public void level5() {
        lightList.add(new Light(50, -y * 3, ImageMap.get("light"), lightSpeed - 5));
        lightList.add(new Light(100, -y * 20, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(350, -y * 20, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(200, -y * 50, ImageMap.get("light"), lightSpeed));
        enemyPlaneList.add(new EnemyPlane(100, -y, ImageMap.get("epA4"), f, 4, 8));
        enemyPlaneList.add(new EnemyPlane(200, -y, ImageMap.get("epA3"), b, 3, 6));
        enemyPlaneList.add(new EnemyPlane(300, -y, ImageMap.get("epB3"), c, 7, 3));
        enemyPlaneList.add(new EnemyPlane(50, -y * 5, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(350, -y * 5, ImageMap.get("epD2"), b, 11, 5));
        enemyPlaneList.add(new EnemyPlane(165, -y * 8, ImageMap.get("epA4"), f, 4, 8));
        enemyPlaneList.add(new EnemyPlane(285, -y * 8, ImageMap.get("epA3"), b, 3, 6));
        enemyPlaneList.add(new EnemyPlane(100, -y * 10, ImageMap.get("epB3"), c, 7, 3));
        enemyPlaneList.add(new EnemyPlane(220, -y * 12, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(340, -y * 14, ImageMap.get("epC3"), a, 9, 3));
        enemyPlaneList.add(new EnemyPlane(400, -y * 16, ImageMap.get("epC4"), f, 10, 4));
        enemyPlaneList.add(new EnemyPlane(120, -y * 20, ImageMap.get("epC3"), a, 9, 3));
        enemyPlaneList.add(new EnemyPlane(320, -y * 20, ImageMap.get("epC4"), f, 10, 4));
        enemyPlaneList.add(new EnemyPlane(280, -y * 21, ImageMap.get("epD2"), b, 11, 5));
        enemyPlaneList.add(new EnemyPlane(100, -y * 25, ImageMap.get("epD3"), c, 15, 5));
        enemyPlaneList.add(new EnemyPlane(255, -y * 25, ImageMap.get("epD3"), c, 15, 5));
        enemyPlaneList.add(new EnemyPlane(355, -y * 26, ImageMap.get("epA4"), f, 4, 8));
        enemyPlaneList.add(new EnemyPlane(255, -y * 28, ImageMap.get("epA3"), b, 3, 6));
        enemyPlaneList.add(new EnemyPlane(80, -y * 30, ImageMap.get("epB3"), c, 7, 3));
        enemyPlaneList.add(new EnemyPlane(350, -y * 30, ImageMap.get("epB4"), c, 2, 6));
    }

    public void level6() {
        lightList.add(new Light(100, -y * 5, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(300, -y * 10, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(250, -y * 15, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(300, -y * 16, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(150, -y * 20, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(200, -y * 25, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(250, -y * 25, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 30, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 35, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 36, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 37, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 46, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(100, -y * 52, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(100, -y * 55, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(100, -y * 58, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 68, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 68, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 70, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 70, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 70, ImageMap.get("light"), lightSpeed));

        enemyPlaneList.add(new EnemyPlane(100, -y, ImageMap.get("epA3"), b, 3, 6));
        enemyPlaneList.add(new EnemyPlane(200, -y, ImageMap.get("epC3"), a, 9, 3));
        enemyPlaneList.add(new EnemyPlane(150, -y * 3, ImageMap.get("epC4"), f, 10, 4));
        enemyPlaneList.add(new EnemyPlane(250, -y * 3, ImageMap.get("epD3"), d, 15, 5));
        enemyPlaneList.add(new EnemyPlane(100, -y * 10, ImageMap.get("epA4"), f, 4, 8));
        enemyPlaneList.add(new EnemyPlane(280, -y * 10, ImageMap.get("epA3"), b, 3, 6));
        enemyPlaneList.add(new EnemyPlane(380, -y * 12, ImageMap.get("epA3"), b, 3, 6));
        enemyPlaneList.add(new EnemyPlane(50, -y * 13, ImageMap.get("epD3"), c, 15, 5));
        enemyPlaneList.add(new EnemyPlane(160, -y * 15, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(300, -y * 15, ImageMap.get("epD4"), f, 16, 8));
    }

    public void level7() {
        stoneList.add(new Stones(0, -y * 5, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(100, -y * 15, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(200, -y * 25, ImageMap.get("stone"), stoneSpeed));

        lightList.add(new Light(r.nextInt(500), -y * 60, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 70, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 80, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 90, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 100, ImageMap.get("light"), lightSpeed));

        enemyPlaneList.add(new EnemyPlane(100, -y * 1, ImageMap.get("epA4"), d, 4, 8));
        enemyPlaneList.add(new EnemyPlane(200, -y * 1, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(120, -y * 3, ImageMap.get("epC4"), e, 10, 4));
        enemyPlaneList.add(new EnemyPlane(200, -y * 4, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(300, -y * 5, ImageMap.get("epD4"), f, 16, 8));
        enemyPlaneList.add(new EnemyPlane(200, -y * 8, ImageMap.get("epA4"), d, 4, 8));
        enemyPlaneList.add(new EnemyPlane(350, -y * 8, ImageMap.get("epA4"), d, 4, 8));
        enemyPlaneList.add(new EnemyPlane(200, -y * 10, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(160, -y * 14, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(120, -y * 15, ImageMap.get("epC4"), e, 10, 4));
        enemyPlaneList.add(new EnemyPlane(240, -y * 15, ImageMap.get("epC4"), e, 10, 4));
        enemyPlaneList.add(new EnemyPlane(300, -y * 17, ImageMap.get("epD4"), f, 16, 8));
        enemyPlaneList.add(new EnemyPlane(400, -y * 20, ImageMap.get("epE6"), h, 9, 4));
        enemyPlaneList.add(new EnemyPlane(100, -y * 20, ImageMap.get("epE6"), h, 9, 4));
        enemyPlaneList.add(new EnemyPlane(300, -y * 22, ImageMap.get("epD4"), f, 16, 8));
        enemyPlaneList.add(new EnemyPlane(210, -y * 25, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(400, -y * 25, ImageMap.get("epE6"), a, 9, 4));
        enemyPlaneList.add(new EnemyPlane(170, -y * 28, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(150, -y * 30, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(250, -y * 30, ImageMap.get("epE6"), h, 9, 4));
    }

    public void level8() {
        stoneList.add(new Stones(300, -y * 5, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(0, -y * 10, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(300, -y * 10, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(300, -y * 15, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(120, -y * 25, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(200, -y * 20, ImageMap.get("stone"), stoneSpeed));

        lightList.add(new Light(r.nextInt(500), -y * 5, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 20, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 30, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 30, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 55, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 70, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 100, ImageMap.get("light"), lightSpeed));

        enemyPlaneList.add(new EnemyPlane(50, -y * 2, ImageMap.get("epA4"), d, 4, 8));
        enemyPlaneList.add(new EnemyPlane(150, -y * 2, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(250, -y * 2, ImageMap.get("epA4"), d, 4, 8));
        enemyPlaneList.add(new EnemyPlane(350, -y * 2, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(200, -y * 5, ImageMap.get("epD4"), f, 16, 8));
        enemyPlaneList.add(new EnemyPlane(120, -y * 18, ImageMap.get("epE2"), d, 1, 7));
        enemyPlaneList.add(new EnemyPlane(320, -y * 18, ImageMap.get("epE3"), e, 9, 6));
        enemyPlaneList.add(new EnemyPlane(220, -y * 20, ImageMap.get("epE4"), f, 14, 5));
        enemyPlaneList.add(new EnemyPlane(350, -y * 22, ImageMap.get("epD4"), f, 16, 8));
        enemyPlaneList.add(new EnemyPlane(150, -y * 25, ImageMap.get("epE2"), d, 1, 7));
        enemyPlaneList.add(new EnemyPlane(330, -y * 25, ImageMap.get("epE2"), d, 1, 7));
        enemyPlaneList.add(new EnemyPlane(150, -y * 30, ImageMap.get("epE3"), e, 9, 6));
        enemyPlaneList.add(new EnemyPlane(300, -y * 30, ImageMap.get("epE3"), e, 9, 6));
        enemyPlaneList.add(new EnemyPlane(200, -y * 35, ImageMap.get("epE4"), f, 14, 5));
        enemyPlaneList.add(new EnemyPlane(400, -y * 35, ImageMap.get("epE4"), f, 14, 5));
        enemyPlaneList.add(new EnemyPlane(100, -y * 40, ImageMap.get("epA4"), d, 4, 8));
        enemyPlaneList.add(new EnemyPlane(350, -y * 40, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(50, -y * 50, ImageMap.get("epE2"), d, 1, 7));
        enemyPlaneList.add(new EnemyPlane(100, -y * 51, ImageMap.get("epE3"), e, 9, 6));
        enemyPlaneList.add(new EnemyPlane(150, -y * 52, ImageMap.get("epE4"), f, 14, 5));
        enemyPlaneList.add(new EnemyPlane(200, -y * 53, ImageMap.get("epD4"), f, 16, 8));
        enemyPlaneList.add(new EnemyPlane(250, -y * 58, ImageMap.get("epA4"), d, 4, 8));
        enemyPlaneList.add(new EnemyPlane(200, -y * 60, ImageMap.get("epB4"), c, 2, 6));
        enemyPlaneList.add(new EnemyPlane(150, -y * 65, ImageMap.get("epD4"), f, 16, 8));
        enemyPlaneList.add(new EnemyPlane(300, -y * 70, ImageMap.get("epA4"), d, 4, 8));
    }

    public void level9() {
        stoneList.add(new Stones(10, -y * 5, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(400, -y * 10, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(100, -y * 15, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(200, -y * 20, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(300, -y * 25, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(0, -y * 35, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(0, -y * 38, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(0, -y * 41, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(300, -y * 44, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(300, -y * 47, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(300, -y * 50, ImageMap.get("stone"), stoneSpeed));
        stoneList.add(new Stones(255, -y * 60, ImageMap.get("stone"), stoneSpeed));

        lightList.add(new Light(r.nextInt(500), -y * 5, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 20, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 30, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 30, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 55, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(r.nextInt(500), -y * 70, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(0, -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(100, -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(200, -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(300, -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(400, -y * 100, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(150, -y * 110, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(250, -y * 110, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(350, -y * 110, ImageMap.get("light"), lightSpeed));
        lightList.add(new Light(450, -y * 110, ImageMap.get("light"), lightSpeed));

        enemyPlaneList.add(new EnemyPlane(300, -y * 2, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(250, -y * 3, ImageMap.get("epE2"), d, 1, 7));
        enemyPlaneList.add(new EnemyPlane(350, -y * 4, ImageMap.get("epE3"), e, 9, 6));
        enemyPlaneList.add(new EnemyPlane(200, -y * 5, ImageMap.get("epE4"), f, 14, 5));
        enemyPlaneList.add(new EnemyPlane(300, -y * 6, ImageMap.get("epE5"), g, 6, 4));
        enemyPlaneList.add(new EnemyPlane(150, -y * 7, ImageMap.get("epE6"), h, 9, 4));
        enemyPlaneList.add(new EnemyPlane(200, -y * 12, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(300, -y * 18, ImageMap.get("epE5"), g, 6, 4));
        enemyPlaneList.add(new EnemyPlane(50, -y * 22, ImageMap.get("epE6"), h, 9, 4));
        enemyPlaneList.add(new EnemyPlane(150, -y * 22, ImageMap.get("epE6"), h, 9, 4));
        enemyPlaneList.add(new EnemyPlane(250, -y * 22, ImageMap.get("epE6"), h, 9, 4));
        enemyPlaneList.add(new EnemyPlane(350, -y * 22, ImageMap.get("epE6"), h, 9, 4));
        enemyPlaneList.add(new EnemyPlane(100, -y * 26, ImageMap.get("epE2"), d, 1, 7));
        enemyPlaneList.add(new EnemyPlane(250, -y * 26, ImageMap.get("epE3"), e, 9, 6));
        enemyPlaneList.add(new EnemyPlane(150, -y * 26, ImageMap.get("epE4"), f, 14, 5));
        enemyPlaneList.add(new EnemyPlane(100, -y * 30, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(200, -y * 30, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(300, -y * 30, ImageMap.get("epE1"), h, 10, 9));
        enemyPlaneList.add(new EnemyPlane(150, -y * 35, ImageMap.get("epE2"), d, 1, 7));
        enemyPlaneList.add(new EnemyPlane(400, -y * 40, ImageMap.get("epE3"), e, 9, 6));


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


        if (backgroundList.isEmpty()) {
            backgroundList.add(new Background(0, FrameConstant.GAME_HEIGHT - ImageMap.get("bg01").getHeight(null), 1, ImageMap.get("bg01")));

        }


        level = 1;
        if (enemyPlaneList.isEmpty()) {

            enemyPlaneList.add(new EnemyPlane(100, -y, ImageMap.get("epA1"), a, 1, 1));
            enemyPlaneList.add(new EnemyPlane(200, -y, ImageMap.get("epA1"), a, 1, 1));
            enemyPlaneList.add(new EnemyPlane(300, -y * 2, ImageMap.get("epA2"), a, 2, 2));
            enemyPlaneList.add(new EnemyPlane(150, -y * 3, ImageMap.get("epA1"), a, 1, 1));
            enemyPlaneList.add(new EnemyPlane(250, -y * 3, ImageMap.get("epB1"), a, 5, 2));
            enemyPlaneList.add(new EnemyPlane(100, -y * 3, ImageMap.get("epA2"), a, 2, 2));
            enemyPlaneList.add(new EnemyPlane(200, -y * 4, ImageMap.get("epC1"), a, 9, 1));
            enemyPlaneList.add(new EnemyPlane(300, -y * 5, ImageMap.get("epB1"), a, 5, 2));
            enemyPlaneList.add(new EnemyPlane(150, -y * 6, ImageMap.get("epA1"), a, 1, 1));
            enemyPlaneList.add(new EnemyPlane(250, -y * 6, ImageMap.get("epC1"), a, 9, 1));
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