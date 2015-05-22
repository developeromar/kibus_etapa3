package pato.mundo.kibus.ui;


import pato.mundo.kibus.Actores.ActorAbeja;
import pato.mundo.kibus.Actores.ActorCalor;
import pato.mundo.kibus.MainKibus;
import pato.mundo.kibus.proseso.regreso;

/**
 * Created by Omar Sanchez on 26/04/2015.
 */
public class Bosque3 extends MundoKibusBosque {
    ActorCalor calor;
    ActorAbeja abeja;
    public Bosque3(MainKibus game) {
        super(game);
        calor=new ActorCalor();
        abeja=new ActorAbeja();

    }

    @Override
    protected void clickSetKibus(int kibusx, int kibusy) {
        abeja.setAbejas(kibusx,kibusy);
        stage.addActor(abeja);
    }

    @Override
    protected void clickSetCasa() {
        calor.setPosition(mapa.getMatriz());
        stage.addActor(calor);
    }

    @Override
    protected void clickRegresar() {
        new regreso(mapa, kibus,abeja).start();
    }


    @Override
    protected void clickCasa() {
        super.clickCasa();
        calor.remove();
    }

    @Override
    protected void clickUpdate() {
        super.clickUpdate();
        calor.remove();
        abeja.remove();
    }

    @Override
    protected void cargarClick() {
        super.cargarClick();
        calor.remove();
       abeja.remove();
    }

    @Override
    protected void generarClick() {
        super.generarClick();
        calor.remove();
        abeja.remove();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

    }

    @Override
    public void hide() {
        dispose();


    }

    @Override
    public void dispose() {
        calor.dispose();
        abeja.dispose();
        super.dispose();
    }
}
