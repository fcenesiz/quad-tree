package com.cenesiz.quadtree.quadtree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cenesiz.quadtree.Main;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {

    private QuadTreeRectangle boundary;
    private int capacity;
    private List<QuadTreePoint> points;
    private List<QuadTreePoint> queriedPoints;
    private boolean divided;



    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southWest;
    private QuadTree southEast;

    public QuadTree(QuadTreeRectangle boundary, int capacity) {
        this.boundary = boundary;
        this.capacity = capacity;
        this.points = new ArrayList<>();
        this.queriedPoints = new ArrayList<>();


    }


    public boolean insert(QuadTreePoint point) {
        if (!this.boundary.contains(point))
            return false;
        if (this.points.size() < this.capacity) {
            this.points.add(point);
            return true;
        } else {
            if (!this.divided) {
                this.subdivide();
            }
            if (this.northWest.insert(point))
                return true;
            else if (this.northEast.insert(point))
                return true;
            else if (this.southWest.insert(point))
                return true;
            else if (this.southEast.insert(point))
                return true;
        }
        return false;
    }

    private void subdivide() {
        QuadTreeRectangle nE = new QuadTreeRectangle(this.boundary.getRightIn(), this.boundary.getTopIn(), this.boundary.getWidth() * 0.5f, this.boundary.getHeight() * 0.5f);
        QuadTreeRectangle nW = new QuadTreeRectangle(this.boundary.getLeftIn(), this.boundary.getTopIn(), this.boundary.getWidth() * 0.5f, this.boundary.getHeight() * 0.5f);
        QuadTreeRectangle sE = new QuadTreeRectangle(this.boundary.getRightIn(), this.boundary.getBottomIn(), this.boundary.getWidth() * 0.5f, this.boundary.getHeight() * 0.5f);
        QuadTreeRectangle sW = new QuadTreeRectangle(this.boundary.getLeftIn(), this.boundary.getBottomIn(), this.boundary.getWidth() * 0.5f, this.boundary.getHeight() * 0.5f);

        northWest = new QuadTree(nW, this.capacity);
        northEast = new QuadTree(nE, this.capacity);
        southWest = new QuadTree(sW, this.capacity);
        southEast = new QuadTree(sE, this.capacity);
        this.divided = true;
    }

    public List<QuadTreePoint> query(QuadTreeRectangle range) {
        queriedPoints.clear();
        if (!this.boundary.intersects(range))
            return queriedPoints;
        else {
            for (int i = 0; i < this.points.size(); i++) {
                if (points.get(i) != null && range.contains(points.get(i))) {
                    queriedPoints.add(points.get(i));
                }
            }
        }
        if (this.divided) {
            queriedPoints.addAll(this.northWest.query(range));
            queriedPoints.addAll(this.northEast.query(range));
            queriedPoints.addAll(this.southWest.query(range));
            queriedPoints.addAll(this.southEast.query(range));
        }
        return queriedPoints;
    }

    public void render(SpriteBatch batch) {
        QuadTreeController.DrawRectangle(batch, QuadTreeController.Color_RED, this.boundary.getX(), this.boundary.getY(), this.boundary.getOriginX(), this.boundary.getOriginY(), this.boundary.getWidth(), this.boundary.getHeight(), 2);
        for (int i = 0; i < points.size(); i++) {
            QuadTreeController.Draw(batch, QuadTreeController.Color_YELLOW, this.points.get(i).getX(), this.points.get(i).getY(), 1, 1, 2, 2, 1, 0);
        }
        if (this.divided) {
            this.northWest.render(batch);
            this.northEast.render(batch);
            this.southWest.render(batch);
            this.southEast.render(batch);
        }
    }

    public List<QuadTreePoint> getPoints() {
        return points;
    }
}
