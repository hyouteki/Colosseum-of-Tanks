package com.mygdx.colosseum_of_tanks.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.colosseum_of_tanks.TheGame;

public class HUD implements Disposable {
    public Stage stage;
    public Viewport viewport;
    public Skin skin;

    private int tank1HealthCount;
    private int tank1FuelCount;
    private int tank1MissileCount;
    private int tank2HealthCount;
    private int tank2FuelCount;
    private int tank2MissileCount;

    public Table table;
    public Label tank1Label;
    public Label tank1HealthLabel;
    public Label tank1FuelLabel;
    public Label tank1MissileLabel;
    public Label tank1HealthCountLabel;
    public Label tank1FuelCountLabel;
    public Label tank1MissileCountLabel;
    public Label tank2Label;
    public Label tank2HealthLabel;
    public Label tank2FuelLabel;
    public Label tank2MissileLabel;
    public Label tank2HealthCountLabel;
    public Label tank2FuelCountLabel;
    public Label tank2MissileCountLabel;

    public HUD(SpriteBatch batch) {
        this.viewport = new FitViewport(TheGame.WORLD_WIDTH, TheGame.WORLD_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);
        this.skin = new Skin(Gdx.files.internal("skins/commodore/uiskin.json"));
        this.table = new Table();
        this.table.top();
        this.table.setFillParent(true);

        this.tank1HealthCount = 100;
        this.tank1FuelCount = 100;
        this.tank1MissileCount = 6;
        this.tank2HealthCount = 100;
        this.tank2FuelCount = 100;
        this.tank2MissileCount = 6;

        this.tank1Label = new Label("Tank1 stats", skin);
        this.tank1HealthLabel = new Label("Health", skin);
        this.tank1FuelLabel = new Label("Fuel", skin);
        this.tank1MissileLabel = new Label("Missile", skin);
        this.tank1HealthCountLabel = new Label(Integer.toString(tank1HealthCount), skin);
        this.tank1FuelCountLabel = new Label(Integer.toString(tank1FuelCount), skin);
        this.tank1MissileCountLabel = new Label(Integer.toString(tank1MissileCount), skin);
        this.tank2Label = new Label("Tank2 stats", skin);
        this.tank2HealthLabel = new Label("Health", skin);
        this.tank2FuelLabel = new Label("Fuel", skin);
        this.tank2MissileLabel = new Label("Missile", skin);
        this.tank2HealthCountLabel = new Label(Integer.toString(tank2HealthCount), skin);
        this.tank2FuelCountLabel = new Label(Integer.toString(tank2FuelCount), skin);
        this.tank2MissileCountLabel = new Label(Integer.toString(tank2MissileCount), skin);

        table.add(tank1Label).left();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
