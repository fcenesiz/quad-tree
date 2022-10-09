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

    public Object getObject() {
        return object;
    }

}
