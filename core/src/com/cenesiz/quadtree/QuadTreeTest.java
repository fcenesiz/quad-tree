package com.cenesiz.quadtree;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.cenesiz.quadtree.quadtree.QuadTree;
import com.cenesiz.quadtree.quadtree.QuadTreePoint;
import com.cenesiz.quadtree.quadtree.QuadTreeRectangle;

import java.util.List;

public class QuadTreeTest {

    private QuadTree quadTree;
    List<QuadTreePoint> points;

    public QuadTreeTest(){
        start();
    }

    public void start(){

        QuadTreeRectangle boundary = new QuadTreeRectangle(250, 250, 500, 500);
        quadTree = new QuadTree(boundary, 4);
        for (int i = 0; i < 250; i++) {
            QuadTreePoint p = new QuadTreePoint(boundary.x - 250 + MathUtils.random() * 500, boundary.y - 250 + MathUtils.random() * 500);
            quadTree.insert(p);
        }

        QuadTreeRectangle range = new QuadTreeRectangle(225, 175 + 100, 107, 75);
        points = quadTree.query(range);

    }

    public void render(SpriteBatch batch) {
        quadTree.render(batch);

        // render queried points
        for (int i = 0; i < points.size(); i++) {
            Main.Draw(batch, Main.Color_GREEN, points.get(i).getX(), points.get(i).getY(), 2.5f, 2.5f, 5f, 5f, 1, 0);
        }
    }

}
