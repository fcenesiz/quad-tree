package com.cenesiz.quadtree.quadtree;

public class QuadTreePoint {

    private float x;
    private float y;
    private Object object;

    public QuadTreePoint(float x, float y, Object object) {
        this.x = x;
        this.y = y;
        this.object = object;
    }

    public QuadTreePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void translateX(float dx) {
        this.x += dx;
    }

    public void translateY(float dy) {
        this.y += dy;
    }

    public Object getObject() {
        return object;
    }

}
