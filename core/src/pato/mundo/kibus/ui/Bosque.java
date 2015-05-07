package pato.mundo.kibus.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import pato.mundo.kibus.Actores.ActorFondo;
import pato.mundo.kibus.Actores.ActorRocas;
import pato.mundo.kibus.MainKibus;
import pato.mundo.kibus.Pantalla;
import pato.mundo.kibus.proseso.Matrix;

/**
 * Created by Omar Sanchez on 05/03/2015.
 */
public class Bosque extends Pantalla {
    public static final float SCALA = 1;
    protected final float x = 800 * SCALA;
    protected final float y = 480 * SCALA;
    protected Skin skin;
    protected final float YBUTTON = 140;
    protected final int XBUTTON = 60;
    protected final int SLIDERX = 720;
    protected final int SLIDERY = 230;

    ActorFondo fondo;
    Stage stage;
    Texture audiot;
    Sound click;
    Music bgSound;
    Matrix mapa;
    ActorRocas obstaculo;
    ImageButton audio;
    boolean sound = true;


    public Bosque(MainKibus game) {
        super(game);
        //music
        bgSound = Gdx.audio.newMusic(Gdx.files.internal("bgsound.mp3"));
        //bgSound.play();
        bgSound.setLooping(true);
        //fondo
        fondo = new ActorFondo();
        //sound
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        //generamos las skins
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //genereamos la matriz metodo de control totaly agregamos la posicion de kibus
        mapa = new Matrix();
        fondo.setPositioninit(mapa.getFondoPosition());
        //Boton audio
        audiot = new Texture("audioon.png");
        TextureRegion ok0 = new TextureRegion(audiot);
        TextureRegionDrawable dibujable0 = new TextureRegionDrawable(ok0);
        ImageButton.ImageButtonStyle imgbutton0 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton0.imageUp = dibujable0;
        audio = new ImageButton(imgbutton0);
        audio.setSize(60, 60);
        audio.setPosition(XBUTTON , YBUTTON + 220);
        audio.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (bgSound.isPlaying()) {
                    bgSound.pause();
                    audio.setColor(1, 0, 0, 1);
                    sound = false;
                } else {
                    bgSound.play();
                    audio.setColor(1, 1, 1, 1);
                    sound = true;
                }
            }
        });

        //rocas
        obstaculo = new ActorRocas();

        //escene && actor add
        stage = new Stage(new StretchViewport(x, y));
        stage.addActor(fondo);
        stage.addActor(obstaculo);
        stage.addActor(audio);

        Gdx.input.setInputProcessor(stage);


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(delta);
        if(back()){
            game.setScreen(new Opening(game));
            bgSound.stop();
        }

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        click.dispose();
        obstaculo.dispose();
        fondo.dispose();
        obstaculo.dispose();
        stage.dispose();
        bgSound.dispose();
        audiot.dispose();
    }
    protected boolean back() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK))
            return true;
        else
            return false;
    }
    public void ocultaTeclado(){
        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.OnscreenKeyboard))
            Gdx.input.setOnscreenKeyboardVisible(false);
    }



}
