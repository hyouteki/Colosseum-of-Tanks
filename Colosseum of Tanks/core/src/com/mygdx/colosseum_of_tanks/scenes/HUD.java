package com.mygdx.colosseum_of_tanks.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.colosseum_of_tanks.TheGame;
import com.mygdx.colosseum_of_tanks.sprites.Tank;

public class HUD implements Disposable {
    private final Stage stage;
    private final Skin skin;

    private final Tank tankL;
    private final Tank tankR;

    private final Label tankLHealthCountLabel;
    private final Label tankLFuelCountLabel;
    private final Label tankLMissileCountLabel;
    private final Label tankRHealthCountLabel;
    private final Label tankRFuelCountLabel;
    private final Label tankRMissileCountLabel;
    private final Label tankLTurn;
    private final Label tankRTurn;

    public HUD(SpriteBatch batch, Tank tankL, Tank tankR) {
        Viewport viewport = new FitViewport(TheGame.WORLD_WIDTH, TheGame.WORLD_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);
        this.skin = new Skin(Gdx.files.internal("skins/commodore/uiskin.json"));
        this.tankL = tankL;
        this.tankR = tankR;
        Table parentTable = new Table();
        Table tankLTable = new Table();
        Table tankLStatsTable = new Table();
        Table tankRTable = new Table();
        Table tankRStatsTable = new Table();
        Image tankLCard = new Image(new TextureRegionDrawable(tankL.tankCard));
        Image tankRCard = new Image(new TextureRegionDrawable(tankR.tankCardFlipped));

        Label tankLHealthLabel = new Label("HP:", skin);
        tankLHealthCountLabel = new Label(Integer.toString(tankL.getHealth()), skin);
        Label tankLFuelLabel = new Label("FP:", skin);
        tankLFuelCountLabel = new Label(Integer.toString(tankL.getFuel()), skin);
        Label tankLMissileLabel = new Label("MP:", skin);
        tankLMissileCountLabel = new Label("00" + tankL.getMissileCount(), skin);
        Label tankRHealthLabel = new Label("HP:", skin);
        tankRHealthCountLabel = new Label(Integer.toString(tankR.getHealth()), skin);
        Label tankRFuelLabel = new Label("FP:", skin);
        tankRFuelCountLabel = new Label(Integer.toString(tankR.getFuel()), skin);
        Label tankRMissileLabel = new Label("MP:", skin);
        tankRMissileCountLabel = new Label("00" + tankR.getMissileCount(), skin);
        tankLTurn = new Label("TURN", skin);
        tankRTurn = new Label("TURN", skin);

        tankLTurn.setVisible(true);
        tankRTurn.setVisible(false);

        tankLHealthLabel.setFontScale(0.5f);
        tankLHealthCountLabel.setFontScale(0.5f);
        tankLFuelLabel.setFontScale(0.5f);
        tankLFuelCountLabel.setFontScale(0.5f);
        tankLMissileLabel.setFontScale(0.5f);
        tankLMissileCountLabel.setFontScale(0.5f);
        tankRHealthLabel.setFontScale(0.5f);
        tankRHealthCountLabel.setFontScale(0.5f);
        tankRFuelLabel.setFontScale(0.5f);
        tankRFuelCountLabel.setFontScale(0.5f);
        tankRMissileLabel.setFontScale(0.5f);
        tankRMissileCountLabel.setFontScale(0.5f);
        tankLTurn.setFontScale(0.5f);
        tankRTurn.setFontScale(0.5f);

        tankLStatsTable.add(tankLHealthLabel);
        tankLStatsTable.add(tankLHealthCountLabel).padLeft(5);
        tankLStatsTable.row();
        tankLStatsTable.add(tankLFuelLabel).padTop(5);
        tankLStatsTable.add(tankLFuelCountLabel).padTop(5).padLeft(5);
        tankLStatsTable.row();
        tankLStatsTable.add(tankLMissileLabel).padTop(5);
        tankLStatsTable.add(tankLMissileCountLabel).padTop(5).padLeft(5).align(Align.left);
        tankRStatsTable.add(tankRHealthLabel);
        tankRStatsTable.add(tankRHealthCountLabel).padLeft(5);
        tankRStatsTable.row();
        tankRStatsTable.add(tankRFuelLabel).padTop(5);
        tankRStatsTable.add(tankRFuelCountLabel).padTop(5).padLeft(5);
        tankRStatsTable.row();
        tankRStatsTable.add(tankRMissileLabel).padTop(5);
        tankRStatsTable.add(tankRMissileCountLabel).padTop(5).padLeft(5).align(Align.left);

        tankLTable.add(tankLCard).width(40).height(40);
        tankLTable.add(tankLStatsTable).padLeft(10);
        tankLTable.row();
        tankLTable.add(tankLTurn).padTop(5);
        tankRTable.add(tankRStatsTable).padRight(10);
        tankRTable.add(tankRCard).width(40).height(40);
        tankRTable.row();
        tankRTable.add(new Label("", skin));
        tankRTable.add(tankRTurn).padTop(5);

        parentTable.setPosition(0, 0);
        parentTable.setFillParent(true);
        parentTable.top().padTop(5);
        tankLTable.top().padLeft(10).padRight(100);
        tankRTable.top().padRight(10).padLeft(100);
        parentTable.add(tankLTable);
        parentTable.add(tankRTable);
        this.stage.addActor(parentTable);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public void update(boolean turn) {
        if (this.tankL.getHealth() == 0) {
            this.tankLHealthCountLabel.setText("000");
            this.tankLHealthCountLabel.setColor(Color.RED);
        } else {
            this.tankLHealthCountLabel.setText(String.format("%03d", tankL.getHealth()));
            this.tankLHealthCountLabel.setColor(TheGame.LIGHT_BLUE);
        }
        if (this.tankR.getHealth() == 0) {
            this.tankRHealthCountLabel.setText("000");
            this.tankRHealthCountLabel.setColor(Color.RED);
        } else {
            this.tankRHealthCountLabel.setText(String.format("%03d", tankR.getHealth()));
            this.tankRHealthCountLabel.setColor(TheGame.LIGHT_BLUE);
        }
        if (this.tankL.getFuel() == 0) {
            this.tankLFuelCountLabel.setText("000");
            this.tankLFuelCountLabel.setColor(Color.RED);
        } else {
            this.tankLFuelCountLabel.setText(String.format("%03d", tankL.getFuel()));
            this.tankLFuelCountLabel.setColor(TheGame.LIGHT_BLUE);
        }
        if (this.tankR.getFuel() == 0) {
            this.tankRFuelCountLabel.setText("000");
            this.tankRFuelCountLabel.setColor(Color.RED);
        } else {
            this.tankRFuelCountLabel.setText(String.format("%03d", tankR.getFuel()));
            this.tankRFuelCountLabel.setColor(TheGame.LIGHT_BLUE);
        }
        if (this.tankL.getMissileCount() == 0) {
            this.tankLMissileCountLabel.setText("000");
            this.tankLMissileCountLabel.setColor(Color.RED);
        } else {
            this.tankLMissileCountLabel.setText(String.format("%03d", tankL.getMissileCount()));
            this.tankLMissileCountLabel.setColor(TheGame.LIGHT_BLUE);
        }
        if (this.tankR.getMissileCount() == 0) {
            this.tankRMissileCountLabel.setText("000");
            this.tankRMissileCountLabel.setColor(Color.RED);
        } else {
            this.tankRMissileCountLabel.setText(String.format("%03d", tankR.getMissileCount()));
            this.tankRMissileCountLabel.setColor(TheGame.LIGHT_BLUE);
        }
        if (turn) {
            this.tankLTurn.setVisible(true);
            this.tankRTurn.setVisible(false);
        } else {
            this.tankLTurn.setVisible(false);
            this.tankRTurn.setVisible(true);
        }
    }

    public Stage getStage() {
        return this.stage;
    }
}
