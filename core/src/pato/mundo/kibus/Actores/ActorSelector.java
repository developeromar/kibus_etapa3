package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import pato.mundo.kibus.proseso.Matrix;
import pato.mundo.kibus.proseso.objetos.Position;


/**
 * Created by Omar Sanchez on 08/03/2015.
 */
public class ActorSelector extends Actor implements Disposable{
    public final int ARRIBA = 0, IZQUIERDA = 1, DERECHA = 2, ABAJO = 3;
    Texture selector;
    TextureRegion selectorR;
    int X;
    int Y;
    Matrix matrix;

     public  ActorSelector(){
         selector=new Texture("selector.png");
         selectorR=new TextureRegion(selector);
         setSize(32,32);
         X=0;
         Y=0;
     }
    public void setPosition(Position pos){
        setPosition(pos.getX(),pos.getY());
    }
    public void mover(int direccion){
        switch (direccion){
            case ARRIBA:
                if(Y>0)
                    Y-=1;
                break;
            case IZQUIERDA:
                if(X>0)
                    X-=1;
                break;
            case DERECHA:
                if(X<14)
                    X+=1;
                break;
            case ABAJO:
                if(Y<14)
                    Y+=1;
                break;
        }

    }
    public int dameX(){
        return this.X;
    }
    public int dameY(){
        return this.Y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       batch.draw(selectorR,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void dispose() {
        selector.dispose();
    }
}
