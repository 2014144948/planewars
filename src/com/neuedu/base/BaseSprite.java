package com.neuedu.base;

import java.awt.Rectangle;

public abstract class BaseSprite {
    private double x;
    private double y;

    public BaseSprite() {
    }

    public BaseSprite(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getRectangle(){
        return null;
    }
}
