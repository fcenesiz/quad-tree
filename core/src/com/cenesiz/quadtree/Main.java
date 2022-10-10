package com.cenesiz.quadtree;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cenesiz.quadtree.quadtree.QuadTreeController;
import com.cenesiz.quadtree.quadtree.QuadTreePoint;
import com.cenesiz.quadtree.quadtree.QuadTreeRectangle;

import java.util.ArrayList;
import java.util.List;

public class Main extends ApplicationAdapter {

	OrthographicCamera camera;
	Viewport viewport;
	SpriteBatch batch;

	QuadTreeController quadTreeController;

	@Override
	public void create () {
		this.camera = new OrthographicCamera();
		this.camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		this.viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.viewport.setCamera(camera);
		this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();


		List<QuadTreePoint> points = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			QuadTreePoint p = new QuadTreePoint(MathUtils.random() * 500f, MathUtils.random() * 500f);
			points.add(p);
		}

		quadTreeController = new QuadTreeController(
				0, 0,
				500, 500,
				points,
				4
		);

		QuadTreeRectangle range = new QuadTreeRectangle(225, 175 + 100, 107, 75);
		quadTreeController.query(range);
	}

	public void update(float dt){
		if (Gdx.input.isKeyPressed(Input.Keys.A)){
			camera.zoom += .1f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)){
			camera.zoom -= .1f;
		}
	}

	@Override
	public void render () {
		this.update(Gdx.graphics.getDeltaTime());


		ScreenUtils.clear(0.25f, 0.25f, 0.25f, 1);
		viewport.apply();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);

		quadTreeController.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}




}
