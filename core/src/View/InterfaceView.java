package View;

import Logic.GameObjectContainer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by landfried on 31.03.17.
 */
public class InterfaceView {
    private GameObjectContainer gameObjectContainer;
    private Stage stage;
    VerticalGroup rootGroup;
    ButtonGroup<TextButton> objectChooserGroup;

    public InterfaceView(GameObjectContainer gameObjectContainer) {
        this.gameObjectContainer = gameObjectContainer;
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
        skin.add("default", textButtonStyle);

        /*
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        */


        rootGroup = new VerticalGroup();
        rootGroup.setFillParent(true);
        rootGroup.left();
        stage.addActor(rootGroup);


        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton button1 = new TextButton("Carrier", skin);
        final TextButton button2 = new TextButton("SmallResidence", skin);
        final TextButton button3 = new TextButton("Wood", skin);
        final TextButton button4 = new TextButton("Stone", skin);

        objectChooserGroup = new ButtonGroup<>();
        objectChooserGroup.add(button1);
        objectChooserGroup.add(button2);
        objectChooserGroup.add(button3);
        objectChooserGroup.add(button4);
        objectChooserGroup.setMaxCheckCount(1);
        objectChooserGroup.setMinCheckCount(1);

        rootGroup.addActor(button1);
        rootGroup.addActor(button2);
        rootGroup.addActor(button3);
        rootGroup.addActor(button4);


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
}
