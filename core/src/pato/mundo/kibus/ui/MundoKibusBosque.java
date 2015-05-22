package pato.mundo.kibus.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import pato.mundo.kibus.Actores.ActorCalor;
import pato.mundo.kibus.Actores.ActorCasa;
import pato.mundo.kibus.Actores.ActorKibus;
import pato.mundo.kibus.MainKibus;
import pato.mundo.kibus.proseso.Matrix;
import pato.mundo.kibus.proseso.leeJson;
import pato.mundo.kibus.proseso.regreso;


/**
 * Created by Omar Sanchez on 12/02/2015.
 */
public class MundoKibusBosque extends Bosque{

    private boolean kibusSet = false,casaSet=false;
    ActorKibus kibus;
    Texture pad, casat, updatet, regresat,kibust;
    ImageButton arriba, izq, der, abj;
    Slider slider,slider2;
    TextField porcentaje,porcentaje2;
    TextButton set,crear,load,loadInt,velocidad,ok;
    ImageButton casa, regresar, update,btnkibus;
    ActorCasa casaKibus;
    Window auto,cargar,velo;
    List<String> lista;
    ScrollPane sp;
    leeJson leer;


    boolean sound = true;


    public MundoKibusBosque(MainKibus game) {
        super(game);
        leer=new leeJson();

        ///kibus actor
        kibus = new ActorKibus();
        //Actor Casa
        casaKibus = new ActorCasa();
        //boton casa
        casat = new Texture("casa.png");
        TextureRegion ok = new TextureRegion(casat);
        TextureRegionDrawable dibujable = new TextureRegionDrawable(ok);
        ImageButton.ImageButtonStyle imgbutton = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton.imageUp = dibujable;
        casa = new ImageButton(imgbutton);
        casa.setSize(80, 80);
        casa.setPosition(XBUTTON - 10, YBUTTON + 10);
        casa.setColor(1, 1, 1, 1);
        casa.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               clickCasa();
            }
        });
        //boton kibus
        kibust = new Texture("mono.png");
        TextureRegion tr = new TextureRegion(kibust,32,0,32,32);
        TextureRegionDrawable dibujable6 = new TextureRegionDrawable(tr);
        ImageButton.ImageButtonStyle imgbutton6 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton6.imageUp = dibujable6;
        btnkibus = new ImageButton(imgbutton6);
        btnkibus.setSize(80, 80);
        btnkibus.setPosition(XBUTTON - 10, YBUTTON -100);
        btnkibus.setColor(1, 1, 1, 1);
        btnkibus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                btnkibus.setColor(new Color(1, 0, 0, 1));
                kibus.remove();
                mapa.setKibus(false);
                //mapa.pila.clear();
                //casaKibus.remove();
                //mapa.setCasa(false);
                kibusSet = true;
            }
        });
        //Boton update
        updatet = new Texture("reset.png");
        TextureRegion ok2 = new TextureRegion(updatet);
        TextureRegionDrawable dibujable2 = new TextureRegionDrawable(ok2);
        ImageButton.ImageButtonStyle imgbutton2 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton2.imageUp = dibujable2;
        update = new ImageButton(imgbutton2);
        update.setSize(80, 80);
        update.setPosition(XBUTTON - 10, YBUTTON + 110);
        update.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickUpdate();
            }
        });
        //boton Regresar
        regresat = new Texture("regresa.png");
        TextureRegion ok3 = new TextureRegion(regresat);
        TextureRegionDrawable dibujable3 = new TextureRegionDrawable(ok3);
        ImageButton.ImageButtonStyle imgbutton3 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton3.imageUp = dibujable3;
        regresar = new ImageButton(imgbutton3);
        regresar.setSize(80, 80);
        regresar.setPosition(SLIDERX - 30, SLIDERY - 185);
        regresar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(mapa.isKibus()&&mapa.isCasa())
               clickRegresar();
            }
        });
        //padInit();
        crearInit();
        loadInit();
        velocidadInit();



        //escene && actor add

        stage.addActor(btnkibus);
        stage.addActor(casa);
        stage.addActor(update);
        stage.addActor(regresar);
        stage.addActor(crear);
        stage.addActor(load);
        stage.addActor(velocidad);

    }

    protected void clickRegresar() {

    }

    protected void clickUpdate() {
        obstaculo.remove();
        casaKibus.remove();
        mapa.setKibus(false);
        mapa.setCasa(false);
        kibus.remove();
        mapa.update();
    }

    protected void clickCasa() {
        if (sound)
            click.play();
        casa.setColor(new Color(1, 0, 0, 1));
        casaKibus.remove();
        mapa.setCasa(false);
        casaSet = true;
    }

    private void velocidadInit() {
        velocidad=new TextButton("Velocidad",skin);
        velocidad.setPosition(SLIDERX - 30, SLIDERY - 80);
        velocidad.setSize(80, 80);
        velocidad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(iniciarVentanaVelocidad());
            }
        });
    }

    private void loadInit() {
        load=new TextButton("Cargar",skin);
        load.setPosition(SLIDERX-30,SLIDERY+20);
        load.setSize(80,80);
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(cargarInitVentana());
            }
        });
    }


    private void crearInit() {
        crear=new TextButton("Generar",skin);
        crear.setPosition(SLIDERX-30,SLIDERY+120);
        crear.setSize(80,80);
        crear.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(iniciarVentanaAuto());
            }
        });
    }

    private Window iniciarVentanaVelocidad() {
        slider2Init();
        velo=new Window("Velocidad",skin);
        velo.add(slider2);
        velo.row();
        velo.add(porcentaje2).spaceTop(20).width(50);
        velo.row();
        velo.add(ok).spaceTop(15).width(80);

        velo.setPosition(x / 2 - (velo.getWidth() / 2) - 70, y / 2);
        velo.setSize(320, 180);

        return velo;
    }

    private Window iniciarVentanaAuto() {
        sliderInit();
        auto=new Window("Auto generar",skin);
        auto.add(slider);
        auto.row();
        auto.add(porcentaje).spaceTop(20).width(50);
        auto.row();
        auto.add(set).spaceTop(15).width(80);

        auto.setPosition(x/2-(auto.getWidth()/2)-70,y/2);
        auto.setSize(320,180);

        return auto;
    }
    private Window cargarInitVentana() {
        initButon();
        cargar=new Window("cargar mapas",skin);
        lista=new List<String>(skin);
        lista.setItems(leer.getNames());
        lista.setWidth(200);
        sp=new ScrollPane(lista);

        cargar.add(sp).prefWidth(320).row();
        cargar.add(loadInt).width(100).spaceTop(20);
        cargar.setPosition(x/2-(cargar.getWidth()/2)-70,y/2);
        cargar.setSize(320, 220);
        return cargar;
    }

    private void initButon() {
        loadInt=new TextButton("Cargar",skin);
        loadInt.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.getRoot().removeActor(cargar);
                ocultaTeclado();
                obstaculo.remove();
                obstaculo.setArreglo(mapa.loadObstaculos(leer.getMatriz(lista.getSelectedIndex())));
                stage.addActor(obstaculo);
                kibus.remove();
                cargarClick();
                mapa.setKibus(false);
                mapa.setCasa(false);
            }
        });
    }
    protected void cargarClick(){
        casaKibus.remove();
    }



    private void sliderInit() {
        ///Slider
        slider = new Slider(20, 80, 4, false, skin);
        porcentaje = new TextField("", skin);
        porcentaje.setText(String.valueOf(slider.getValue()));
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                porcentaje.setText(String.valueOf(slider.getValue()));

            }
        });

        //boton set
        set = new TextButton("set", skin);
        set.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.getRoot().removeActor(auto);
                ocultaTeclado();
                obstaculo.remove();
                obstaculo.setArreglo(mapa.setObstaculos(slider.getValue()));
                stage.addActor(obstaculo);
                kibus.remove();
                generarClick();
                mapa.setKibus(false);
                mapa.setCasa(false);


            }
        });
    }
    protected void generarClick(){
        casaKibus.remove();
    }

    private void slider2Init() {
        slider2 = new Slider(1,32, 16, false, skin);
        porcentaje2 = new TextField("", skin);
        porcentaje2.setText(String.valueOf(slider2.getValue()));
        slider2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                porcentaje2.setText(String.valueOf(slider2.getValue()));

            }
        });
        ok=new TextButton("Aplicar",skin);
        ok.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.getRoot().removeActor(velo);
                ocultaTeclado();
                kibus.setVELOCIDAD((int) Double.parseDouble(porcentaje2.getText().trim()));
            }
        });
    }

    private void padInit() {
        ///pad
        pad = new Texture("keys.png");
        TextureRegion trPad = new TextureRegion(pad, 32, 0, 32, 32);
        TextureRegionDrawable up = new TextureRegionDrawable(trPad);
        arriba = new ImageButton(up);
        arriba.setPosition(XBUTTON, YBUTTON);
        arriba.setSize(40, 40);
        arriba.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                if (kibus.isSeguir())
                    mueve(Matrix.ARIZ);
            }
        });
        trPad = new TextureRegion(pad, 0, 32, 32, 32);
        TextureRegionDrawable left = new TextureRegionDrawable(trPad);
        izq = new ImageButton(left);
        izq.setPosition(XBUTTON - 40, YBUTTON - 40);
        izq.setSize(40, 40);
        izq.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                if (kibus.isSeguir())
                   mueve(Matrix.ABIZ);
            }
        });
        trPad = new TextureRegion(pad, 32, 32, 32, 32);
        TextureRegionDrawable down = new TextureRegionDrawable(trPad);
        abj = new ImageButton(down);
        abj.setPosition(XBUTTON, YBUTTON - 80);
        abj.setSize(40, 40);
        abj.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                if (kibus.isSeguir())
                    if (mapa.mover(Matrix.ABDER)) {
                        kibus.camina(mapa.getKibusx(), mapa.getKibusy());
                    }
            }
        });
        trPad = new TextureRegion(pad, 64, 32, 32, 32);
        TextureRegionDrawable rig = new TextureRegionDrawable(trPad);
        der = new ImageButton(rig);
        der.setPosition(XBUTTON + 40, YBUTTON - 40);
        der.setSize(40, 40);
        der.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                if (kibus.isSeguir())
                   mueve(mapa.ARDER);

            }
        });
    }

    private void mueve(int direction) {
        if (mapa.mover(direction)) {
            kibus.camina(mapa.getKibusx(), mapa.getKibusy());
        }
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        if (kibusSet)
            setKibus();
        if(casaSet)
            setCasa();

    }

    private void setCasa() {
        if (Gdx.input.isTouched()) {
            int x = (int) Math.round(Gdx.input.getX() / 2.4);
            int y = (int) Math.round(Gdx.input.getY() / 2.25);
            if (mapa.setCAsa(x, 448 - y)) {
                //kibus.initPosition(mapa.getKibusx(), mapa.getKibusy());
                casaKibus.setPosition(mapa.getCasaX(), mapa.getCasaY());
                //stage.addActor(kibus);
                clickSetCasa();
                stage.addActor(casaKibus);
                casa.setColor(1, 1, 1, 1);
                casaSet = false;
            }
        }
    }
    protected void clickSetCasa(){

    }


    private void setKibus() {
        if (Gdx.input.isTouched()) {
            int x = (int) Math.round(Gdx.input.getX() / 2.4);
            int y = (int) Math.round(Gdx.input.getY() / 2.25);
            if (mapa.setKibus(x, 448 - y)) {
                kibus.initPosition(mapa.getKibusx(), mapa.getKibusy());

               // casaKibus.setPosition(mapa.getKibusx(), mapa.getKibusy());
                stage.addActor(kibus);
                clickSetKibus(mapa.getKibusx(), mapa.getKibusy());
                //stage.addActor(casaKibus);
                btnkibus.setColor(1, 1, 1, 1);
                kibusSet = false;
            }
        }
    }
    protected void clickSetKibus(int kibusx,int kibusy){}

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
        kibus.dispose();
        obstaculo.dispose();
        casat.dispose();
        casaKibus.dispose();
        updatet.dispose();
        regresat.dispose();
        kibust.dispose();
        super.dispose();
    }


}
