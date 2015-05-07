package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

import pato.mundo.kibus.proseso.objetos.Position;


/**
 * Created by Omar Sanchez on 26/04/2015.
 */
public class ActorCalor extends Actor implements Disposable{
    Texture calor;
    TextureRegion calorsito;
    Color color;
    Position[][] calorMapa;




    public ActorCalor(){
        this.calor= new Texture("rojofuerte.jpg");
        this.calorsito=new TextureRegion(calor);
        setSize(32,32);
        color=getColor();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(Position[] posFila: calorMapa){
            for(Position pos: posFila) {
                batch.setColor(color.r, color.g, color.b, pos.getCalor());
                batch.draw(calorsito, pos.getX(), pos.getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        }
        batch.setColor(color.r,color.g,color.b,1f);
    }

    @Override
    public void dispose() {
        calor.dispose();

    }

    public void setPosition(Position[][] calorMapa) {
        this.calorMapa=calorMapa;
    }
}
