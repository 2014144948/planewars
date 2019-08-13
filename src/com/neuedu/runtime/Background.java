package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;

public class Background extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED;
    //    用于背景的多方向移动延时
    private int index = 0;
    //    index的相关参数
    private final int indexValue = 50;
    //    关卡对应的背景图
    private int level = 1;


    public Background() {
        this(0, FrameConstant.GAME_HEIGHT - ImageMap.get("bg01").getHeight(null), 1, ImageMap.get("bg01"));
    }

    public Background(int x, int y, int level, Image image) {
        super(x, y);
        this.level = level;
        this.image = image;
    }

    //    每一关背景的移动方式
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
        index++;
        if (index < indexValue * 10) {
            setY(getY() + speed);
        } else if (index >= indexValue * 10 && index <= indexValue * 20) {
            setX(getX() - speed * 2);
        } else if (index >= indexValue * 20 && index <= indexValue * 30) {
            setY(getY() - speed);
        } else if (index >= indexValue * 30 && index <= indexValue * 40) {
            setX(getX() + speed * 2);
        } else if (index >= indexValue * 40 && index <= indexValue * 45) {
            setX(getX() - speed * 2);
            setY(getY() + speed);
        } else if (index >= indexValue * 45 && index <= indexValue * 50) {
            setX(getX() - speed * 2);
            setY(getY() - speed);
        } else if (index >= indexValue * 50 && index <= indexValue * 60) {
            setX(getX() + speed * 2);
        } else {
            index = 0;
        }
    }

    public void move2() {
        index++;
        if (index < indexValue * 15) {
            setY(getY() + speed);
        } else if (index >= indexValue * 15 && index <= indexValue * 30) {
            setX(getX() - speed * 2);
        } else if (index >= indexValue * 30 && index <= indexValue * 45) {
            setY(getY() - speed);
        } else if (index >= indexValue * 45 && index <= indexValue * 60) {
            setX(getX() + speed * 2);
        } else if (index >= indexValue * 60 && index <= indexValue * 70) {
            setX(getX() - speed * 2);
            setY(getY() + speed);
        } else if (index >= indexValue * 70 && index <= indexValue * 80) {
            setX(getX() + speed * 2);
            setY(getY() - speed);
        } else {
            index = 0;
        }
    }

    public void move3() {
        index++;
        if (index < indexValue * 5) {
            setY(getY() + speed);
        } else if (index >= indexValue * 5 && index <= indexValue * 30) {
            setX(getX() - speed);
        } else if (index >= indexValue * 30 && index <= indexValue * 35) {
            setY(getY() - speed);
        } else if (index >= indexValue * 35 && index <= indexValue * 60) {
            setX(getX() + speed);
        } else {
            index = 0;
        }
    }

    public void move4() {
        index++;
        if (index < indexValue * 20) {
            setX(getX() - speed);
        } else if (index >= indexValue * 20 && index <= indexValue * 40) {
            setY(getY() + speed);
        } else if (index >= indexValue * 40 && index <= indexValue * 60) {
            setX(getX() + speed);
        } else if (index >= indexValue * 60 && index <= indexValue * 80) {
            setY(getY() - speed);
        } else {
            index = 0;
        }
    }

    public void move5() {
        index++;
        if (index < indexValue * 40) {
            setY(getY() + speed);
        } else if (index >= indexValue * 40 && index <= indexValue * 60) {
            setX(getX() - speed * 3);
        } else if (index >= indexValue * 60 && index <= indexValue * 100) {
            setY(getY() - speed);
        } else if (index >= indexValue * 100 && index <= indexValue * 120) {
            setX(getX() + speed * 3);
        } else if (index >= indexValue * 120 && index <= indexValue * 140) {
            setX(getX() - speed);
            setY(getY() + speed * 2);
        } else if (index >= indexValue * 140 && index <= indexValue * 160) {
            setX(getX() - speed);
            setY(getY() - speed * 2);
        } else if (index >= indexValue * 160 && index <= indexValue * 200) {
            setX(getX() + speed);
        } else {
            index = 0;
        }
    }

    public void move6() {
        index++;
        if (index < indexValue * 20) {
            setX(getX() - speed * 2);
            setY(getY() + speed * 2);
        } else if (index >= indexValue * 20 && index <= indexValue * 40) {
            setX(getX() + speed);
            setY(getY() - speed);
        } else {
        }
    }

    public void move7() {
        index++;
        if (index < indexValue * 20) {
            setY(getY() + speed);
        } else if (index >= indexValue * 20 && index <= indexValue * 40) {
            setX(getX() - speed);
            setY(getY() - speed);
        } else if (index >= indexValue * 40 && index <= indexValue * 60) {
            setY(getY() + speed);
        } else if (index >= indexValue * 60 && index <= indexValue * 80) {
            setX(getX() + speed);
            setY(getY() - speed);
        } else {
            index = 0;
        }
    }

    public void move8() {
        index++;
        if (index < indexValue * 30) {
            setX(getX() - speed * 3);
        } else if (index >= indexValue * 30 && index <= indexValue * 60) {
            setX(getX() + speed * 3);
        } else {
            index = 0;
        }
    }

    public void move9() {
        index++;
        if (index < indexValue * 40) {
            setY(getY() + speed);
        } else if (index >= indexValue * 40 && index <= indexValue * 80) {
            setX(getX() - speed * 2);
        } else if (index >= indexValue * 80 && index <= indexValue * 120) {
            setY(getY() - speed);
        } else if (index >= indexValue * 120 && index <= indexValue * 160) {
            setX(getX() + speed * 2);
        } else if (index >= indexValue * 160 && index <= indexValue * 200) {
            setX(getX() - speed * 2);
            setY(getY() + speed);
        } else if (index >= indexValue * 200 && index <= indexValue * 240) {
            setX(getX() - speed * 2);
            setY(getY() - speed);
        } else if (index >= indexValue * 240 && index <= indexValue * 320) {
            setX(getX() + speed * 2);
        } else {
            index = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

        g.setFont(new Font("楷体", 0, 20));
        g.setColor(Color.WHITE);

        if (Plane.hp > 0) {
            g.drawString("1P HP：" + Plane.hp, 20, 680);
        } else {
            g.drawString("1P GAMEOVER", 20, 680);
        }

        if (Plane2.hp > 0) {
            g.drawString("2P HP：" + Plane2.hp, 360, 680);
        } else {
            g.drawString("2P GAMEOVER", 360, 680);
        }

        if (Plane.hp <= 0 && Plane2.hp <= 0) {
            g.setFont(new Font("黑体", 0, 100));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 30, 350);
        }

        g.setFont(new Font("楷体", 0, 20));
        g.setColor(Color.WHITE);
        g.drawString("|", 170, 680);
        g.drawString("分数：" + GameFrame.score, 190, 680);
        g.drawString("|", 320, 680);

    }
}
