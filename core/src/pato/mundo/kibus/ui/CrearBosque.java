package pato.mundo.kibus.ui;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


import pato.mundo.kibus.Actores.ActorSelector;
import pato.mundo.kibus.MainKibus;
import pato.mundo.kibus.proseso.GuardaFile;



/**
 * Created by Omar Sanchez on 05/03/2015.
 */
public class CrearBosque extends Bosque {
    private final int medida=40;
    Texture pad,piedrat,savet;
    ImageButton arriba, izq, der, abj,piedra,save;
    ActorSelector select;
    Button guarda,cancela;
    Window notificacion;
    TextField campo;
    Dialog alert;

    public CrearBosque(MainKibus game) {
        super(game);
        //selector
        select=new ActorSelector();
        select.setPosition(mapa.getPosition(select.dameX(),select.dameY()));
        //boton poner roca
        piedrat = new Texture("rock.png");
        TextureRegion ok3 = new TextureRegion(piedrat);
        TextureRegionDrawable dibujable3 = new TextureRegionDrawable(ok3);
        ImageButton.ImageButtonStyle imgbutton3 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton3.imageUp = dibujable3;
        piedra = new ImageButton(imgbutton3);
        piedra.setSize(80, 80);
        piedra.setPosition(SLIDERX - 30, SLIDERY - 150);
        piedra.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                obstaculo.addRocas(mapa.getPosition(select.dameX(),select.dameY()));
               // mapa.imprimir();
            }
        });
        //boton guarda dentro de la notif
        guarda=new TextButton("Guardar",skin);
        guarda.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(campo.getText().equals(""))
                    return;
                else{
                    new GuardaFile(campo.getText(), mapa.getMatriz());
                    stage.getRoot().removeActor(notificacion);
                    ocultaTeclado();
                    alert.show(stage);

                }
            }
        });
        cancela=new TextButton("Cancelar",skin);
        cancela.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.getRoot().removeActor(notificacion);
                ocultaTeclado();
            }
        });
        campo=new TextField("",skin);
        //alert
        alert=new Dialog("Felicidades",skin);
        alert.text("Guardado Con Exito");
        alert.button("Aceptar");
        alert.layout();
        alert.setPosition(x/2-(alert.getWidth()/2),y/2);
        //boton guardar
        savet=new Texture("save.png");
        TextureRegion tr=new TextureRegion(savet);
        TextureRegionDrawable dibujablesave=new TextureRegionDrawable(tr);
        ImageButton.ImageButtonStyle imgSave=new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgSave.imageUp=dibujablesave;
        save=new ImageButton(imgSave);
        save.setSize(80,80);
        save.setPosition(piedra.getX(),piedra.getY()+140);
        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(ventana());
            }
        });



        ///pad
        pad = new Texture("keys.png");
        TextureRegion trPad = new TextureRegion(pad, 32, 0, 32, 32);
        TextureRegionDrawable up = new TextureRegionDrawable(trPad);
        arriba = new ImageButton(up);
        arriba.setPosition(XBUTTON, YBUTTON);
        arriba.setSize(medida,medida);
        arriba.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                arribaListener();
            }


        });
        trPad = new TextureRegion(pad, 0, 32, 32, 32);
        TextureRegionDrawable left = new TextureRegionDrawable(trPad);
        izq = new ImageButton(left);
        izq.setPosition(XBUTTON - 40, YBUTTON - 40);
        izq.setSize(medida,medida);
        izq.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                izquierdaListener();
            }
        });
        trPad = new TextureRegion(pad, 32, 32, 32, 32);
        TextureRegionDrawable down = new TextureRegionDrawable(trPad);
        abj = new ImageButton(down);
        abj.setPosition(XBUTTON, YBUTTON - 80);
        abj.setSize(medida,medida);
        abj.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                abajoListener();
            }
        });
        trPad = new TextureRegion(pad, 64, 32, 32, 32);
        TextureRegionDrawable rig = new TextureRegionDrawable(trPad);
        der = new ImageButton(rig);
        der.setPosition(XBUTTON + 40, YBUTTON - 40);
        der.setSize(medida,medida);
        der.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                derechaListener();

            }
        });
        stage.addActor(arriba);
        stage.addActor(der);
        stage.addActor(izq);
        stage.addActor(abj);
        stage.addActor(select);
        stage.addActor(piedra);
        stage.addActor(save);
    }

    private Window ventana() {
        notificacion=new Window("Guardar Mapa",skin);
        notificacion.add(new Label("Nombre del mapa",skin)).colspan(2);
        notificacion.row();
        notificacion.add(campo).spaceTop(10).colspan(2);
        notificacion.row();
        notificacion.add(guarda).spaceTop(10);
        notificacion.add(cancela).spaceTop(10);
        notificacion.setSize(320,180);
        notificacion.setPosition(x/2-(notificacion.getWidth()/2),y/2);
        return notificacion;
    }

    private void derechaListener() {
        select.mover(select.DERECHA);
        update();
    }

    private void abajoListener() {
        select.mover((select.ABAJO));
        update();
    }

    private void izquierdaListener() {
        select.mover(select.IZQUIERDA);
        update();
    }


    private void arribaListener() {
        select.mover(select.ARRIBA);
        update();
    }
    private  void update(){
        select.setPosition(mapa.getPosition(select.dameX(),select.dameY()));
    }
    @Override
    public void dispose() {
        super.dispose();
        pad.dispose();
        select.dispose();
        savet.dispose();
    }

    @Override
    public void hide() {
        dispose();
    }

}
