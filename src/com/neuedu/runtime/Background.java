package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

public class Background extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED;
//    用于背景的多方向移动延时
    private int index = 0;
//    index的相关参数
    private final int indexValue = 50;

    private int score = 0;


    public Background() {
        this(0, FrameConstant.GAME_HEIGHT - ImageMap.get("bg04").getHeight(null), ImageMap.get("bg04"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        index++;
        if(index < indexValue * 10){
            setY(getY() + speed);
        }else if(index >= indexValue * 10 && index <= indexValue * 20){
            setX(getX() - speed * 2);
        }else if(index >= indexValue * 20 && index <= indexValue * 30){
            setY(getY() - speed);
        }else if(index >= indexValue * 30 && index <= indexValue * 40){
            setX(getX() + speed * 2);
        }else if(index >= indexValue * 40 && index <= indexValue * 45){
            setX(getX() - speed * 2);
            setY(getY() + speed);
        }else if(index >= indexValue * 45 && index <= indexValue * 50){
            setX(getX() - speed * 2);
            setY(getY() - speed);
        }else if(index >= indexValue * 50 && index <= indexValue * 60){
            setX(getX() + speed * 2);
        }else{
            index = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        score++;
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

        g.setColor(Color.WHITE);
        g.drawString("分数："+ score,400,600);
        move();
    }
}
