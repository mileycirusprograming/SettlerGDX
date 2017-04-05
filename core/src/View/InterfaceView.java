package View;

import Logic.GameObjectContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by landfried on 31.03.17.
 */
public class InterfaceView {
    private Stage stage;
    private ButtonGroup<TextButton> objectChooserGroup;
    public InterfaceView() {
        stage = new Stage();




        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        Skin skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        buttonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        buttonStyle.checked = skin.newDrawable("white", Color.DARK_GRAY);
        buttonStyle.over = skin.newDrawable("white", Color.DARK_GRAY);

        skin.add("default", textButtonStyle);
        skin.add("default", buttonStyle);

        /*
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        */


        VerticalGroup rootGroup = new VerticalGroup();
        //rootGroup.setFillParent(true);
        rootGroup.left();




        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton button1 = new TextButton("Carrier", skin);
        final TextButton button2 = new TextButton("Builder", skin);
        final TextButton button3 = new TextButton("SmallResidence", skin);
        final TextButton button4 = new TextButton("Wood", skin);
        final TextButton button5 = new TextButton("Stone", skin);


        objectChooserGroup = new ButtonGroup<>();
        objectChooserGroup.add(button1);
        objectChooserGroup.add(button2);
        objectChooserGroup.add(button3);
        objectChooserGroup.add(button4);
        objectChooserGroup.add(button5);
        objectChooserGroup.setMaxCheckCount(1);
        objectChooserGroup.setMinCheckCount(1);

        rootGroup.addActor(button1);
        rootGroup.addActor(button2);
        rootGroup.addActor(button3);
        rootGroup.addActor(button4);
        rootGroup.addActor(button5);



        int bgWidth = 100;
        int bgHeight = 100; //Gdx.graphics.getHeight();
        Pixmap backgroundPixmap = new Pixmap(bgWidth, bgHeight, Pixmap.Format.RGBA8888);
        //backgroundPixmap.setColor(1, 1, 1, .5f);
        //backgroundPixmap.fillCircle(100, 10, 20);
        //backgroundPixmap.fillRectangle(110, 110, bgWidth, bgHeight);
        Texture backgroundTexture = new Texture(backgroundPixmap, Pixmap.Format.RGB888, false);
        TextureRegion backgroundRegion = new TextureRegion(backgroundTexture, bgWidth, bgHeight);
        TextureRegionDrawable backgroundDrawable = new TextureRegionDrawable(backgroundRegion);

        Table menuTable = new Button(skin);
        Table mapTable = new Table();

        menuTable.add(rootGroup);
        menuTable.setBackground(backgroundDrawable);

        Table rootTable = new Table();
        rootTable.add(menuTable);
        rootTable.add(mapTable);
        rootTable.setFillParent(true);
        rootTable.left().top();


        //rootTable.setBackground(backgroundDrawable);

        stage.addActor(rootTable);



         //renderer.setView((OrthographicCamera)stage.getCamera());

    }

    public Stage getStage() {
        return stage;
    }

    public void subscribeButtons(EventListener eventListener) {
        for (TextButton button : objectChooserGroup.getButtons()) {
            button.addListener(eventListener);
        }
    }

    public void update() {

        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        //stage.getActors().first().setSize(width, height);
        stage.getViewport().update(width, height, true);

    }
}
