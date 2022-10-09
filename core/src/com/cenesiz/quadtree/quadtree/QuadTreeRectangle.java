package com.cenesiz.quadtree.quadtree;


public class QuadTreeRectangle {

    public float x;
    public float y;
    public float width;
    public float height;
    private float originX;
    private float originY;
    private boolean initialized;
    private boolean filled;
    private boolean empty;

    public QuadTreeRectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.originX = width * 0.5f;
        this.originY = height * 0.5f;
        initialized = true;
    }

    public QuadTreeRectangle(){
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.originX = 0;
        this.originY = 0;
    }

    public void setOriginCenter(){
        this.originX = this.width * 0.5f;
        this.originY = this.height * 0.5f;
    }

    public void setOrigin(float originX, float originY){
        this.originX = originX;
        this.originY = originY;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float height){
        this.width = width;
        this.height = height;
    }

    public void setBounds(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.originX = 0;
        this.originY = 0;
        this.initialized = true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public float getLeft(){
        return this.x - this.originX;
    }

    public float getRight(){
        return this.x - this.originX + this.width;
    }

    public float getTop(){
        return this.y - this.originY + this.height;
    }

    public float getBottom(){
        return this.y - this.originY;
    }

    public float getLeftIn(){
        return this.x - this.originX + this.width * 0.25f;
    }
    public float getRightIn(){
        return this.x - this.originX + this.width * 0.75f;
    }
    public float getBottomIn(){
        return this.y - this.originY + this.height * 0.25f;
    }
    public float getTopIn(){
        return this.y - this.originY + this.height * 0.75f;
    }

    public boolean contains(QuadTreePoint point){
        return (point.getX() >= this.x - this.width * 0.5f &&
                point.getX() <= this.x + this.width * 0.5f &&
                point.getY() >= this.y - this.height * 0.5f &&
                point.getY() <= this.y + this.height * 0.5f);
    }

    public boolean intersects(QuadTreeRectangle rect2) {
        return (this.getLeft() <= rect2.getRight() &&
                this.getRight() >= rect2.getLeft() &&
                this.getTop() >= rect2.getBottom() &&
                this.getBottom() <= rect2.getTop());
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }
}
