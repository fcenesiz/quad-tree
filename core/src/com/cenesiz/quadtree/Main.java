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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter {

	OrthographicCamera camera;
	Viewport viewport;
	SpriteBatch batch;

	public static TextureRegion Color_RED;
	public static TextureRegion Color_YELLOW ;
	public static TextureRegion Color_GREEN;

	QuadTreeTest quadTreeTest;

	@Override
	public void create () {
		this.camera = new OrthographicCamera();
		this.camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		this.viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.viewport.setCamera(camera);
		this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();

		Color_RED = CreateRegion(Color.RED);
		Color_YELLOW = CreateRegion(Color.YELLOW);
		Color_GREEN = CreateRegion(Color.GREEN);

		quadTreeTest = new QuadTreeTest();
	}

	@Override
	public void render () {

		if (Gdx.input.isKeyPressed(Input.Keys.A)){
			camera.zoom += .1f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)){
			camera.zoom -= .1f;
		}

		ScreenUtils.clear(0.25f, 0.25f, 0.25f, 1);
		viewport.apply();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);

		quadTreeTest.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

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

	public static TextureRegion CreateRegion(Color color){
		Pixmap pixmap = new Pixmap( 64, 64, Pixmap.Format.RGBA8888 );
		pixmap.setColor( color );
		pixmap.fillRectangle( 0, 0, 16, 16);
		Texture pixmaptex = new Texture( pixmap );
		pixmap.dispose();
		return new TextureRegion(pixmaptex, 0, 0, 16, 16);
	}
}
