package pato.mundo.kibus.ui;


import pato.mundo.kibus.Actores.ActorCalor;
import pato.mundo.kibus.MainKibus;

/**
 * Created by Omar Sanchez on 26/04/2015.
 */
public class Bosque3 extends MundoKibusBosque {
    ActorCalor calor;
    public Bosque3(MainKibus game) {
        super(game);
        calor=new ActorCalor();

    }

    @Override
    protected void clickSetCasa() {
        calor.setPosition(mapa.getMatriz());
        stage.addActor(calor);
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
    }

    @Override
    protected void cargarClick() {
        super.cargarClick();
        calor.remove();
    }

    @Override
    protected void generarClick() {
        super.generarClick();
        calor.remove();
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
        super.dispose();
    }
}
