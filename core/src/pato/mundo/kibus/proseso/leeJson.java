package pato.mundo.kibus.proseso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Omar Sanchez on 13/03/2015.
 */
public class leeJson {
    JSONArray contenido;
    Preferences pref;
    private final String MAPAS="mapas";
    public leeJson(){
        pref= Gdx.app.getPreferences("datos");
        try {
            contenido = new JSONArray(pref.getString(MAPAS));
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    public String[] getNames(){
        ArrayList<String> temp=new ArrayList<String>();
        for(int i=0;i<contenido.length();i++){
            temp.add(contenido.getJSONObject(i).getString("name"));
        }
        return temp.toArray(new String[temp.size()]);
    }

    public int[][] getMatriz(int index) {
       // System.out.println(Integer.toString(index));
        JSONObject jso =contenido.getJSONObject(index);
        JSONArray temp, ja=jso.getJSONArray(jso.getString("name"));
        int[][] tempInt=new int[15][15];
        for(int i=0;i<15;i++){
            temp=ja.getJSONArray(i);
            for(int j=0;j<15;j++){
                tempInt[i][j]=temp.getInt(j);
                //   System.out.print(Integer.toString(temp.getInt(j))+" ");
            }
          //  System.out.println();
        }

    return tempInt;
    }
}
