package pato.mundo.kibus.proseso;

import java.util.ArrayList;

import pato.mundo.kibus.Actores.ActorKibus;
import pato.mundo.kibus.proseso.objetos.rockObj;

/**
 * Created by Omar Sanchez on 24/02/2015.
 */
public class regreso extends Thread {

    Matrix matrix;
    ActorKibus kibus;
    rockObj pos;

    public regreso(Matrix matrix, ActorKibus kibus) {

        this.matrix = matrix;
        this.kibus = kibus;
    }

    @Override
    public void run() {
        boolean ciclar=true;
        while (ciclar) {
            ArrayList<rockObj> ruta = bresenham(matrix.getPOsKibusx(), matrix.getPOsKibusy(), matrix.getPOsCasax(), matrix.getPOsCasay());
            for (rockObj tem : ruta) {
                while (!kibus.isSeguir()) ;
                if (matrix.mover(tem)) {
                    kibus.camina(matrix.getKibusx(), matrix.getKibusy());
                } else {
                    break;
                }
            }
            while (!kibus.isSeguir()) ;
            if(matrix.getKibusx()==matrix.getCasaX()&&matrix.getKibusy()==matrix.getCasaY()){
                ciclar=false;
            }else {
                ajusta();
                while (!kibus.isSeguir()) ;

            }
        }



    }

    private void ajusta() {
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
    }

    public void imprimeRuta(ArrayList<rockObj> ruta){
        for(rockObj tem:ruta){
            System.out.println("x="+tem.getX()+" y="+tem.getY());
        }
    }

    public ArrayList<rockObj> bresenham(int x0, int y0, int x1, int y1) {
        ArrayList<rockObj> temp=new ArrayList<rockObj>();
        int x, y, dx, dy, p, incE, incNE, stepx, stepy;
        dx = (x1 - x0);
        dy = (y1 - y0);
        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        } else {
            stepy = 1;
        }
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        } else {
            stepx = 1;
        }
        x = x0;
        y = y0;
        if (dx > dy) {
            p = 2 * dy - dx;
            incE = 2 * dy;
            incNE = 2 * (dy - dx);
            while (x != x1) {
                x = x + stepx;
                if (p < 0) {
                    p = p + incE;
                } else {
                    y = y + stepy;
                    p = p + incNE;
                }
                temp.add(new rockObj(x,y));
               // g.drawLine(x, y, x, y);
            }
        } else {
            p = 2 * dx - dy;
            incE = 2 * dx;
            incNE = 2 * (dx - dy);
            while (y != y1) {
                y = y + stepy;
                if (p < 0) {
                    p = p + incE;
                } else {
                    x = x + stepx;
                    p = p + incNE;
                }
                temp.add(new rockObj(x,y));
                //g.drawLine(x, y, x, y);
            }
        }
        return temp;
    }
}