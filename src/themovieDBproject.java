

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;

/**
 * Created by dremon on 09/11/15.
 */
public class themovieDBproject {


    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static void main(String[] args){
        String s = "";
        String j = "";
        String api_key = "5e9707dca1d918600724188d7bcdb593";

        for (int i = 0; i < 40; i++) {
            int peli = 600 +i;
            int actor = 234351 +i;
            String film = String.valueOf(peli);
            String peticio = "https://api.themoviedb.org/3/movie/"+film+"?api_key="+api_key;
            String actorPeticion ="https://api.themoviedb.org/3/movie/"+film+"/credits"+"?api_key="+api_key;
            try {
                s = getHTML(peticio);
                SJS(s);
                j = getHTML(actorPeticion);
                SJC(j);
            } catch (Exception e) {
                System.out.println("La peli "+film+" no existeix");
            }
        }


    }

    public static void SJS (String cadena){

        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        // Films
        int ID = Integer.parseInt(String.valueOf(arra02.get("id")));
        String NAME =  String.valueOf(arra02.get("original_title"));
        String FECHA_ESTRENO= String.valueOf(arra02.get("release_date"));

        System.out.println("ID :"+ID);
        System.out.println("Titul original :"+NAME);
        System.out.println("Data d'estrena :" + FECHA_ESTRENO);

        insertSQLite.insertFilms(ID,NAME,FECHA_ESTRENO);

        //Personajes



    }

    public static void SJC (String cadena){
        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        JSONArray arra03 = (JSONArray)arra02.get("cast");

        for (int i = 0; i < arra03.size(); i++) {

            JSONObject jb= (JSONObject)arra03.get(i);

            int ID = Integer.parseInt(String.valueOf(jb.get("id")));
            String NAME = String.valueOf(jb.get("name"));
            String CHARACTER = String.valueOf(jb.get("character"));

            System.out.println("ID :"+ID);
            System.out.println("Nom :"+NAME);
            System.out.println("Personatge :"+CHARACTER);
            System.out.println();

            insertSQLite.insertActores(ID,NAME,CHARACTER);

        }

    }

}
