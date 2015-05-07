package pato.mundo.kibus.proseso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import org.json.JSONArray;
import org.json.JSONObject;

import pato.mundo.kibus.proseso.objetos.Position;



/**
 * Created by Omar Sanchez on 08/03/2015.
 */
public class GuardaFile{
    int[][] matrix;
    Preferences pref;
    private final String MAPAS="mapas";


    public GuardaFile(String name,Position[][] matriz){
        JSONObject temp=convertToJSON(name,matriz);
        pref=Gdx.app.getPreferences("datos");
        String jsontemp2=pref.getString(MAPAS,"-");
        if(jsontemp2.equals("-")){
            JSONArray jsa=new JSONArray();
            jsa.put(temp);
            pref.putString(MAPAS, String.valueOf(jsa));
            pref.flush();

        }else{
            JSONArray jsa =new JSONArray(jsontemp2);
            jsa.put(temp);
            pref.putString(MAPAS, String.valueOf(jsa));
            pref.flush();

        }
        System.out.println(pref.getString(MAPAS,"-"));

    }



    private JSONObject convertToJSON(String name, Position[][] matriz) {

        this.matrix=getString(matriz);
        JSONObject json=new JSONObject();
        JSONArray js=new JSONArray(this.matrix);
        json.put(name,js);
        json.put("name",name);

        return json;
    }

    private int[][] getString(Position[][] matriz) {
        int temp[][]=new int[15][15];
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                temp[i][j]= matriz[i][j].getEstate();
            }
        }
        return temp;
    }



}
