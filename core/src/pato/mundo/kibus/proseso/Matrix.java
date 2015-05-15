package pato.mundo.kibus.proseso;

import java.util.ArrayList;

import pato.mundo.kibus.Actores.ActorCalor;
import pato.mundo.kibus.proseso.objetos.Position;
import pato.mundo.kibus.proseso.objetos.rockObj;

/**
 * Created by Omar Sanchez on 19/02/2015.
 */
public class Matrix {


    private Position matriz[][];
    private final int x = 160;
    private final int y = 448;
    private int kibusx;
    private int kibusy;
    private int casaX, casaY;


    private boolean kibus;

    public boolean isCasa() {
        return casa;
    }

    private boolean casa;
    public static final int ARRIBA = 0, IZQUIERDA = 1, DERECHA = 2, ABAJO = 3, ARIZ = 4, ARDER = 5, ABIZ = 6, ABDER = 7;
    private int obsReales;
    private ArrayList<rockObj> rocas;


    private rockObj anteriorPosition;

    public Matrix() {
        init();
        kibus = false;
        obsReales = 0;
        rocas = new ArrayList<rockObj>();
    }

    public void init() {
        matriz = new Position[15][15];
        initMatriz();
    }

    public void initMatriz() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                matriz[i][j] = new Position(0, x + (j * 32), y - (i * 32));
            }
        }
    }


    public Position getPosition(int x, int y) {
        return matriz[y][x];
    }

    public boolean setKibus(int x, int y) {
        this.kibusx = damex(x);
        this.kibusy = damey(y);
        this.anteriorPosition = new rockObj(this.kibusx, this.kibusy);
        if (kibusx >= 0 && kibusy >= 0 && matriz[kibusy][kibusx].getEstate() == 0) {
            this.kibus = true;
            return true;
        }
        return false;
    }

    public boolean setCAsa(int x, int y) {
        this.casaX = damex(x);
        this.casaY = damey(y);
        if (casaX >= 0 && casaY >= 0 && matriz[casaY][casaX].getEstate() == 0) {
            this.casa = true;
            initCalor();
            imprimir();
            return true;
        }
        return false;
    }

    private void initCalor() {
        matrizCalor();
        int maxTemp;
        boolean sentidoX = dameSentidoCalor(casaX);
        boolean sentidoY = dameSentidoCalor(casaY);
        System.out.println(casaX);
        System.out.println(casaY);
        int maxCasaX = Math.max(casaX, 14 - casaX);
        int maxCasaY = Math.max(casaY, 14 - casaY);
        boolean mayorX = (maxCasaX > maxCasaY);
        int dif = calcularDif(mayorX, maxCasaX, maxCasaY);
        maxTemp = Math.max(maxCasaX, maxCasaY);
        ActorCalor.max=maxTemp;
        if (mayorX) {
            if (sentidoX) {

                for (int i = 0; i < 15; i++) {
                    int k = 2;
                    for (int j = 0; j < 15; j++) {
                        if (j <= maxTemp)
                            matriz[i][j].setCalor(j);
                        else {
                            matriz[i][j].setCalor(j - k);
                            k += 2;
                        }
                    }

                }
            } else {
                int k = 2;
                for (int i = 14; i >= 0; i--) {
                    for (int j = 0; j < 15; j++) {
                        if (14 - i <= maxTemp)
                            matriz[j][i].setCalor(14 - i);
                        else {
                            matriz[j][i].setCalor(14 - i - k);
                        }
                    }
                    if (!(14 - i <= maxTemp))
                        k += 2;
                }
            }
            if (sentidoY) {
                int k = 2;
                for (int i = 0; i < 15; i++) {

                    for (int j = 0; j < 15; j++) {
                        if (i+dif <= maxTemp)
                            matriz[i][j].setCalor(Math.min((i + dif),matriz[i][j].getCalor()));
                        else {
                            matriz[i][j].setCalor(Math.min(((i + dif)-k),matriz[i][j].getCalor()));
                        }
                    }
                    if (!(i+dif <= maxTemp))
                        k += 2;
                }
            } else {
                int k = 2;
                for (int i = 14; i >=0; i--) {

                    for (int j = 0; j <15; j++) {
                        if ((14 - i)+dif <= maxTemp)
                            matriz[i][j].setCalor(Math.min(((14-i)+dif), matriz[i][j].getCalor()));
                        else {
                            matriz[i][j].setCalor(Math.min((((14 - i )+dif)-k),matriz[i][j].getCalor()));
                        }
                    }
                    if (!((14 - i)+dif <= maxTemp))
                        k += 2;
                }
            }
        }

    }

    private int calcularDif(boolean mayorX, int maxCasaX, int maxCasaY) {
        if (mayorX)
            return maxCasaX - maxCasaY;
        else
            return maxCasaY - maxCasaX;
    }

    private boolean dameSentidoCalor(int valor) {
        if (valor >= 14 - valor) {
            return true;
        }
        return false;
    }

    private void matrizCalor() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                matriz[i][j].setCalor(0);
            }
        }
    }


    public int damex(int x) {
        int temp;
        for (int j = 0; j < 15; j++) {
            temp = matriz[0][j].getX();
            if (temp <= x && temp + 32 > x)
                return j;
        }
        return -1;
    }

    public int damey(int y) {
        int temp;
        for (int j = 0; j < 15; j++) {
            temp = matriz[j][0].getY();
            if (temp >= y && temp - 32 < y)
                return j;
        }
        return -1;
    }

    public boolean mover(int direccion) {
        if (!kibus)
            return false;
        switch (direccion) {
            case ARRIBA:
                if (kibusy == 0)
                    return false;
                if (matriz[kibusy - 1][kibusx].getEstate() == 1 || matriz[kibusy - 1][kibusx].getEstate() == 2)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusy -= 1;
                }

                break;
            case ARIZ:
                if (kibusx == 0 || kibusy == 0)
                    return false;
                if (matriz[kibusy - 1][kibusx - 1].getEstate() == 1 || matriz[kibusy - 1][kibusx - 1].getEstate() == 2)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusy -= 1;
                    kibusx -= 1;
                }
                break;
            case IZQUIERDA:
                if (kibusx == 0)
                    return false;
                if (matriz[kibusy][kibusx - 1].getEstate() == 1 || matriz[kibusy][kibusx - 1].getEstate() == 2)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusx -= 1;
                }
                break;
            case ARDER:
                if (kibusx == 14 || kibusy == 0)
                    return false;
                if (matriz[kibusy - 1][kibusx + 1].getEstate() == 1 || matriz[kibusy - 1][kibusx + 1].getEstate() == 2)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusy -= 1;
                    kibusx += 1;
                }
                break;

            case DERECHA:
                if (kibusx == 14)
                    return false;
                if (matriz[kibusy][kibusx + 1].getEstate() == 1 || matriz[kibusy][kibusx + 1].getEstate() == 2)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusx += 1;
                }
                break;
            case ABIZ:
                if (kibusx == 0 || kibusy == 14)
                    return false;
                if (matriz[kibusy + 1][kibusx - 1].getEstate() == 1 || matriz[kibusy + 1][kibusx - 1].getEstate() == 2)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusy += 1;
                    kibusx -= 1;
                }
                break;
            case ABDER:
                if (kibusx == 14 || kibusy == 14)
                    return false;
                if (matriz[kibusy + 1][kibusx + 1].getEstate() == 1 || matriz[kibusy + 1][kibusx + 1].getEstate() == 2)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusy += 1;
                    kibusx += 1;
                }
                break;
            case ABAJO:
                if (kibusy == 14)
                    return false;
                if (matriz[kibusy + 1][kibusx].getEstate() == 1 || matriz[kibusy + 1][kibusx].getEstate() == 1)
                    return false;
                else {
                    guardaPosicion(new rockObj(kibusx, kibusy));
                    kibusy += 1;
                }
                break;

        }
        return true;
    }

    public boolean mover(rockObj obj) {
        int dir = comprarPos(obj);
        return mover(dir);
    }

    private int comprarPos(rockObj pos) {
        int res = 0;
        int x = pos.getX();
        int y = pos.getY();
        if (x == kibusx && kibusy > y)
            res = ARRIBA;
        if (x < kibusx && kibusy == y)
            res = IZQUIERDA;
        if (x > kibusx && kibusy == y)
            res = DERECHA;
        if (x == kibusx && kibusy < y)
            res = ABAJO;
        if (x < kibusx && y < kibusy)
            res = ARIZ;
        if (x > kibusx && y < kibusy)
            res = ARDER;
        if (x < kibusx && y > kibusy)
            res = ABIZ;
        if (x > kibusx && y > kibusy)
            res = ABDER;
        return res;
    }

    public int getKibusy() {
        return matriz[kibusy][kibusx].getY();
    }

    public int getPOsKibusy() {
        return kibusy;
    }

    public int getPOsKibusx() {
        return kibusx;
    }

    public int getPOsCasay() {
        return casaY;
    }

    public int getPOsCasax() {
        return casaX;
    }

    public int getKibusx() {
        return matriz[kibusy][kibusx].getX();
    }

    public int getCasaX() {
        return matriz[casaY][casaX].getX();
    }

    public int getCasaY() {
        return matriz[casaY][casaX].getY();
    }

    public ArrayList<rockObj> setObstaculos(float numObstaculos) {
        numObstaculos = Math.round(numObstaculos / 100 * 225);
        if (numObstaculos == obsReales)
            return posicionreal();
        if (numObstaculos > obsReales) {
            int Diferencia = (int) numObstaculos - obsReales;
            agregar(Diferencia);
        } else {
            int diferencia = obsReales - (int) numObstaculos;
            int tamano = rocas.size() - 1;
            rockObj roca;
            for (int i = 0; i < diferencia; i++) {
                roca = rocas.get(tamano);
                matriz[roca.getX()][roca.getY()].setEstate(0);
                rocas.remove(tamano);
                tamano--;
            }


        }
        obsReales = (int) numObstaculos;
        return posicionreal();

    }

    public ArrayList<rockObj> loadObstaculos(int[][] matriz) {
        rocas.clear();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.matriz[i][j].setEstate(matriz[i][j]);
                if (matriz[i][j] == 1) {
                    rocas.add(new rockObj(i, j));
                }
            }
        }
        return posicionreal();
    }

    private void agregar(int diferencia) {
        rockObj roca;
        while (diferencia > 0) {
            roca = new rockObj((int) (Math.random() * (14 + 1)), (int) (Math.random() * (14 + 1)));
            if (existRock(roca)) {
                rocas.add(roca);
                diferencia--;
            }
        }
    }

    private boolean existRock(rockObj roca) {
        if (matriz[roca.getX()][roca.getY()].getEstate() == 0) {
            matriz[roca.getX()][roca.getY()].setEstate(1);
            return true;
        } else
            return false;
    }

    public ArrayList<rockObj> getRocas() {
        return rocas;
    }

    public void update() {
        obsReales = 0;
        initMatriz();
        this.kibus = false;
        rocas.clear();
    }

    private ArrayList<rockObj> posicionreal() {
        ArrayList<rockObj> temp = new ArrayList<rockObj>();
        Position x;
        for (rockObj i : rocas) {
            x = matriz[i.getX()][i.getY()];
            temp.add(new rockObj(x.getX(), x.getY()));
        }
        return temp;
    }

    public Position[][] getMatriz() {
        return this.matriz;
    }

    public rockObj getFondoPosition() {
        return new rockObj(matriz[0][0].getX(), matriz[14][0].getY());
    }

    public boolean isKibus() {
        return kibus;
    }

    public void setKibus(boolean kibus) {
        this.kibus = kibus;
    }

    public void guardaPosicion(rockObj pos) {
        this.anteriorPosition = pos;
    }

    public void imprimir() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                // System.out.print(Integer.toString(matriz[i][j].getEstate())+" ");
                System.out.print(Float.toString(matriz[i][j].getCalor()) + " ");


            }
            System.out.println();
        }
    }

    public void setCasa(boolean b) {
        this.casa = b;
    }

    public void marcaBandera() {
        try {
            if (matriz[kibusy][kibusx].getEstate() != 2) {
                if (matriz[kibusy][kibusx].getEstate() == 0)
                    matriz[kibusy][kibusx].setEstate(3);
                else if (matriz[kibusy][kibusx].getEstate() >= 6)
                    matriz[kibusy][kibusx].setEstate(2);
                else
                    matriz[kibusy][kibusx].setEstate(matriz[kibusy][kibusx].getEstate() + 1);
            }
        } catch (NullPointerException e) {

        }
    }

    public void marcaBanderaAnt() {
        try {
            if (matriz[kibusy][kibusx].getEstate() != 2) {
                if (matriz[anteriorPosition.getY()][anteriorPosition.getX()].getEstate() == 0)
                    matriz[anteriorPosition.getY()][anteriorPosition.getX()].setEstate(3);
                else if (matriz[anteriorPosition.getY()][anteriorPosition.getX()].getEstate() >= 6)
                    matriz[anteriorPosition.getY()][anteriorPosition.getX()].setEstate(2);
                else
                    matriz[anteriorPosition.getY()][anteriorPosition.getX()].setEstate(matriz[anteriorPosition.getY()][anteriorPosition.getX()].getEstate() + 1);
            }
        } catch (NullPointerException e) {

        }
    }

    public rockObj coreccionRuta() {
        ArrayList<rockObj> poss = posDisponibles();
        ArrayList<rockObj> poss2 = getMenores(poss);
        return seleccionAlAzar(poss2);

    }

    private rockObj initBanderas() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].getEstate() == 2)
                    matriz[i][j].setEstate(6);
            }
        }
        ArrayList<rockObj> poss = posDisponibles();
        ArrayList<rockObj> poss2 = getMenores(poss);
        return seleccionAlAzar(poss2);
    }

    private rockObj seleccionAlAzar(ArrayList<rockObj> poss) {
        int numMax = poss.size();
        System.out.println(numMax + "  al azar");
        rockObj p;
        int index;
        do {
            index = (int) Math.floor(Math.random() * (numMax - 0) + 0);

            p = poss.get(index);
        }
        while (poss.size() > 1 && (p.getX() == anteriorPosition.getX() && p.getY() == anteriorPosition.getY()));
        return p;


    }

    private ArrayList<rockObj> getMenores(ArrayList<rockObj> poss) {
        ArrayList<rockObj> temp = new ArrayList<rockObj>();
        boolean first = true;
        int min = 110;
        int max = poss.size();
        if (max > 1) {
            for (rockObj p : poss) {
                if (!(p.getX() == anteriorPosition.getX() && p.getY() == anteriorPosition.getY())) {
                    if (first) {
                        min = matriz[p.getY()][p.getX()].getEstate();
                        first = false;
                    } else if (matriz[p.getY()][p.getX()].getEstate() < min)
                        min = matriz[p.getY()][p.getX()].getEstate();
                }
            }
            for (rockObj p : poss) {
                if (!(p.getX() == anteriorPosition.getX() && p.getY() == anteriorPosition.getY())) {
                    if (matriz[p.getY()][p.getX()].getEstate() == min)
                        temp.add(p);
                }
            }
        } else {
            for (rockObj p : poss) {

                if (first) {
                    min = matriz[p.getY()][p.getX()].getEstate();
                    first = false;
                } else if (matriz[p.getY()][p.getX()].getEstate() < min)
                    min = matriz[p.getY()][p.getX()].getEstate();

            }
            for (rockObj p : poss) {

                if (matriz[p.getY()][p.getX()].getEstate() == min)
                    temp.add(p);

            }
        }

        System.out.println(temp.size() + "   menores");
        return temp;
    }

    public ArrayList<rockObj> posDisponibles() {
        ArrayList<rockObj> resp = new ArrayList<rockObj>();
        if (kibusx > 0) {
            if (disponiblePos(IZQUIERDA))
                resp.add(new rockObj(kibusx - 1, kibusy));
        }
        if (kibusx > 0 && kibusy > 0) {
            if (disponiblePos(ARIZ))
                resp.add(new rockObj(kibusx - 1, kibusy - 1));
        }
        if (kibusx > 0 && kibusy < 14)
            if (disponiblePos(ABIZ))
                resp.add(new rockObj(kibusx - 1, kibusy + 1));
        if (kibusy > 0)
            if (disponiblePos(ARRIBA))
                resp.add(new rockObj(kibusx, kibusy - 1));
        if (kibusy < 14)
            if (disponiblePos(ABAJO))
                resp.add(new rockObj(kibusx, kibusy + 1));
        if (kibusx < 14)
            if (disponiblePos(DERECHA))
                resp.add(new rockObj(kibusx + 1, kibusy));
        if (kibusx < 14 && kibusy > 0)
            if (disponiblePos(ARDER))
                resp.add(new rockObj(kibusx + 1, kibusy - 1));
        if (kibusx < 14 && kibusy < 14)
            if (disponiblePos(ABDER))
                resp.add(new rockObj(kibusx + 1, kibusy + 1));
        if (resp.size() == 0) {
            initBanderas();
            resp = posDisponibles();
        }

        return resp;
    }

    private boolean disponiblePos(int dir) {
        boolean resp = true;
        switch (dir) {
            case ARRIBA:
                if (matriz[kibusy - 1][kibusx].getEstate() == 1 || matriz[kibusy - 1][kibusx].getEstate() == 2)
                    return false;
                break;
            case ARIZ:
                if (matriz[kibusy - 1][kibusx - 1].getEstate() == 1 || matriz[kibusy - 1][kibusx - 1].getEstate() == 2)
                    return false;
                break;
            case ARDER:
                if (matriz[kibusy - 1][kibusx + 1].getEstate() == 1 || matriz[kibusy - 1][kibusx + 1].getEstate() == 2)
                    return false;
                break;
            case DERECHA:
                if (matriz[kibusy][kibusx + 1].getEstate() == 1 || matriz[kibusy][kibusx + 1].getEstate() == 2)
                    return false;
                break;
            case IZQUIERDA:
                if (matriz[kibusy][kibusx - 1].getEstate() == 1 || matriz[kibusy][kibusx - 1].getEstate() == 2)
                    return false;
                break;
            case ABAJO:
                if (matriz[kibusy + 1][kibusx].getEstate() == 1 || matriz[kibusy + 1][kibusx].getEstate() == 2)
                    return false;
                break;
            case ABIZ:
                if (matriz[kibusy + 1][kibusx - 1].getEstate() == 1 || matriz[kibusy + 1][kibusx - 1].getEstate() == 2)
                    return false;
                break;
            case ABDER:
                if (matriz[kibusy + 1][kibusx + 1].getEstate() == 1 || matriz[kibusy + 1][kibusx + 1].getEstate() == 2)
                    return false;
                break;
        }
        return resp;
    }
}
