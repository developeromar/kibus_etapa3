package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import pato.mundo.kibus.proseso.objetos.rockObj;

/**
 * Created by Omar Sanchez on 17/05/2015.
 */
public class ActorAbeja extends Actor implements Disposable {
    Texture abeja;
    TextureRegion abejon;
    rockObj[] abejas;
    boolean visible;

    public ActorAbeja() {
        abeja=new Texture("bee.png");
        abejon=new TextureRegion(abeja);
        abejas=new rockObj[5];
        visible=false;
        setSize(32,32);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(visible)
            for(rockObj abeja:abejas){
                batch.draw(abejon,abeja.getX(),abeja.getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
            }
    }

    public void setAbejas(rockObj[] abejas){
        this.abejas=abejas;
        visible=true;
    }

    @Override
    public void dispose() {

    }
}
