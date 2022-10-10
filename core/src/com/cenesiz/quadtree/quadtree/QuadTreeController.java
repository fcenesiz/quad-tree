package com.cenesiz.quadtree.quadtree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.cenesiz.quadtree.Main;

import java.util.ArrayList;
import java.util.List;

public class QuadTreeController {

    private QuadTree quadTree;
    private List<QuadTreePoint> queriedPoints;
    private float x;
    private float y;
    private float width;
    private float height;
    private int capacity;

    public static TextureRegion Color_RED;
    public static TextureRegion Color_YELLOW;
    public static TextureRegion Color_GREEN;

    public QuadTreeController(float x, float y, float width, float height, List<QuadTreePoint> points, int capacity) {
        this.width = width;
        this.height = height;
        this.x = x + width * 0.5f; // origin x center
        this.y = y + height * 0.5f; // origin y center
        this.capacity = capacity;
        this.queriedPoints = new ArrayList<>();

        Color_RED = CreateRegion(Color.RED);
        Color_YELLOW = CreateRegion(Color.YELLOW);
        Color_GREEN = CreateRegion(Color.GREEN);

        this.init(points);
    }

    private void init(List<QuadTreePoint> points) {

        QuadTreeRectangle boundary = new QuadTreeRectangle(x, y, width, height);
        quadTree = new QuadTree(boundary, capacity);
        for (int i = 0; i < points.size(); i++) {
            QuadTreePoint point = points.get(i);
            quadTree.insert(point);
        }
    }

    public void insert(QuadTreePoint point) {
        quadTree.insert(point);
    }

    public List<QuadTreePoint> query(QuadTreeRectangle rectangle) {
        queriedPoints.clear();
        queriedPoints.addAll(quadTree.query(rectangle));
        return queriedPoints;
    }

    public void render(SpriteBatch batch) {
        quadTree.render(batch);

        // render queried points
        for (int i = 0; i < queriedPoints.size(); i++) {
            Draw(batch, Color_GREEN, queriedPoints.get(i).getX(), queriedPoints.get(i).getY(), 2.5f, 2.5f, 5f, 5f, 1, 0);
        }
    }

    public static TextureRegion CreateRegion(Color color) {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, 16, 16);
        Texture pixmaptex = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegion(pixmaptex, 0, 0, 16, 16);
    }

    public static void Draw(SpriteBatch batch, TextureRegion region, float x, float y, float oX, float oY, float width, float height, float scale, float rotation) {
        batch.draw(region, x - oX, y - oY, oX, oY,
                width, height, scale, scale, rotation);
    }

    public static void DrawRectangle(SpriteBatch batch, TextureRegion region, float x, float y, float oX, float oY, float width, float height, float stroke){
        float left = x - oX;
        float right = left + width;
        float bottom = y - oY;
        float top = bottom + height;
        //left
        batch.draw(region, left, bottom, stroke, height);
        //right
        batch.draw(region, right, bottom, stroke, height);
        //bottom
        batch.draw(region, left, bottom, width, stroke);
        //top
        batch.draw(region, left, top, width, stroke);
    }

    public List<QuadTreePoint> getPoints(){
        return quadTree.getPoints();
    }

}
