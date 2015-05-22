package pato.mundo.kibus.proseso;

import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import pato.mundo.kibus.Actores.ActorAbeja;
import pato.mundo.kibus.Actores.ActorKibus;
import pato.mundo.kibus.proseso.objetos.rockObj;

/**
 * Created by Omar Sanchez on 24/02/2015.
 */
public class regreso extends Thread {

    Matrix matrix;
    ActorKibus kibus;
    ActorAbeja abeja;
    final int DELAY=10;
    rockObj pos;

    public regreso(Matrix matrix, ActorKibus kibus,ActorAbeja abeja) {

        this.matrix = matrix;
        this.kibus = kibus;
        this.abeja=abeja;
    }

    @Override
    public void run() {

        boolean ciclar=true;
        while (ciclar) {
            rockObj[] ruta = new rockObj[5];
            for(byte i=0;i<5;i++ ){
                rockObj[] abejas = matrix.posAbejas();
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                abeja.setAbejas(matrix.getRealAbejasPosition(abejas));
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ruta[i]=matrix.enfriarMatriz(matrix.mayorCalor(abejas));
                abeja.setAbejas(matrix.getRealAbejasPosition(matrix.unirAbejas(ruta[i])));
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(matrix.getAbejasx()==matrix.getCasaX()&&matrix.getAbejasy()==matrix.getAbejasy()){
                    break;
                }
            }


            for (rockObj tem : ruta) {
                while (!kibus.isSeguir()) ;
                if (matrix.mover(tem)) {
                    kibus.camina(matrix.getKibusx(), matrix.getKibusy());
                    while (!kibus.isSeguir()) ;
                    if(matrix.getKibusx()==matrix.getCasaX()&&matrix.getKibusy()==matrix.getCasaY()){
                        ciclar=false;
                        break;
                    }
                }
            }

        }



    }

    /*private void ajusta() {
        matrix.marcaBandera();
        matrix.marcaBanderaAnt();
        rockObj temp=matrix.coreccionRuta();
        if (matrix.mover(temp)) {
            kibus.camina(matrix.getKibusx(), matrix.getKibusy());
        }
        temp=matrix.coreccionRuta();
        if (matrix.mover(temp)) {
            kibus.camina(matrix.getKibusx(), matrix.getKibusy());
        }
    }*/




}