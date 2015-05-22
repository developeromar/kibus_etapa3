package pato.mundo.kibus.proseso.objetos;

import pato.mundo.kibus.Actores.ActorRocas;

/**
 * Created by Omar Sanchez on 20/02/2015.
 */
public class rockObj {
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    int x;
    int y;


    public rockObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
