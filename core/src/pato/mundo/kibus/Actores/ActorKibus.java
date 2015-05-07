package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;


/**
 * Created by Omar Sanchez on 12/02/2015.
 */
public class ActorKibus extends Actor implements Disposable {
    Texture mono;
    TextureRegion monos;
    int posNewx;
    int posNewY;
    int posActualX,posActualY;
    private int VELOCIDAD=16;
    int VELOCIDADt=16;
    boolean seguir = false;


    public void setVELOCIDAD(int VELOCIDAD) {
        this.VELOCIDADt = VELOCIDAD;
    }

    public ActorKibus() {
        mono = new Texture("mono.png");
        monos = new TextureRegion(mono,32,0,32,32);
        setSize(32, 32);
}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(monos, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        if (seguir) {
            mover();
        }

    }

    private void mover() {
        posActualX=(int)getX();
        posActualY=(int)getY();
        if(posActualX==this.posNewx&&posActualY==this.posNewY) {
            seguir = false;
            if(VELOCIDADt!=VELOCIDAD)
                VELOCIDAD=VELOCIDADt;
            return;
        }else if(posActualX!=this.posNewx&&posActualY!=this.posNewY){
            if(posNewY>posActualY&&posNewx>posActualX){
                posActualY+=VELOCIDAD;
                posActualX+=VELOCIDAD;
            }else if(posNewY<posActualY&&posNewx>posActualX){
                posActualX+=VELOCIDAD;
                posActualY-=VELOCIDAD;
            }else if(posNewY>posActualY&&posNewx<posActualX){
                posActualY+=VELOCIDAD;
                posActualX-=VELOCIDAD;
            }else{
                posActualX-=VELOCIDAD;
                posActualY-=VELOCIDAD;
            }

        }else if(posActualX==posNewx){
            if(posActualY<posNewY){
                posActualY+=VELOCIDAD;
            }else
                posActualY-=VELOCIDAD;
        }else if(posActualY==posNewY){
            if(posActualX<posNewx)
                posActualX+=VELOCIDAD;
            else
                posActualX-=VELOCIDAD;
        }
        setPosition(posActualX,posActualY);

    }

    @Override
    public void dispose() {
        mono.dispose();
    }

    public void camina(int newX,int newY){
        seguir=true;
        this.posNewY=newY;
        this.posNewx=newX;
    }
    public void initPosition(int x,int y){
        this.posNewx=x;
        this.posNewY=y;
        setPosition(x,y);
    }

    public boolean isSeguir() {
        return !seguir;
    }

}
