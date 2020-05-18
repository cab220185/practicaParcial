package com.example.hiloserver;


import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hilo extends Thread {

    Handler handler ;
    String  url ;
    Boolean texto ;
    Integer posicion ;
    String peticion ;
    JSONObject json ;
    List<Persona>listapersona ;


    public Hilo (Handler handler ,String url,Boolean texto, Integer posicion){
        this.handler = handler ;
        this.url = url ;
        this.texto= texto;
        this.posicion = posicion;

    }

    public Hilo (Handler handler ,String url,Boolean texto ,String peticion , JSONObject json){
        Log.d("entro al constructor","entro");

        this.handler = handler ;
        this.url = url ;
        this.texto= texto;
        this.peticion = peticion ;
        this.json = json ;

    }

    @Override
    public void run (){

     if(texto &&  peticion == "GET"){
     HttpManager manager = new HttpManager();
     String respuesta =  manager.consutarPersonas(this.url);
     listapersona = new ArrayList<Persona>();
     listapersona.clear();
     if (respuesta.isEmpty()){
         Log.d("tienedatos","respuesta vacia");
     }
         JSONArray array = null;
         try {

             array = new JSONArray(respuesta);


         for(int i = 0; i<array.length(); i++){
             JSONObject object = array.getJSONObject(i);
             listapersona.add( new Persona (object.getString("nombre"),object.getString("apellido"),object.getString("telefono"),object.getString("img")));

         }

         } catch (JSONException e) {
             e.printStackTrace();
         }

     Message message = new Message();
         message.obj =  listapersona ;
     message.arg1=1;
     this.handler.sendMessage(message);}

     else if(texto &&  peticion == "POST"){


         HttpManager manager = new HttpManager();

         Boolean respuesta =  manager.crearPersona(this.url , this.json);
         Log.d("llenarnuevamente",respuesta.toString());
         if (respuesta ){

             Message message = new Message();
             message.arg1=5;
             this.handler.sendMessage(message);}

         }

     else  {

         HttpManager manager = new HttpManager();
         byte[] imagen =  manager.consutarPersonasimagen(this.url);

         Message message = new Message();
         message.obj =  imagen ;
         message.arg1=2;
         message.arg2 = posicion ;
         this.handler.sendMessage(message);}

     }

}
