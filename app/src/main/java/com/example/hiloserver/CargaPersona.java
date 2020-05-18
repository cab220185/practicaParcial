package com.example.hiloserver;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;


public class CargaPersona extends AppCompatActivity implements  View.OnClickListener {

     Handler mihandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_persona);




        Button btnGuardar = (Button) super.findViewById(R.id.button_guardar);
        btnGuardar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        EditText edt_nombre = super.findViewById(R.id.edittext_nombre);
        EditText edt_apellido = super.findViewById(R.id.edittext_apellido);
        EditText edt_telefono = super.findViewById(R.id.edittext_telefono);
        EditText edt_imagen = super.findViewById(R.id.edittext_imagen);


        JSONObject json = new JSONObject();

        try {
            json.put("nombre", edt_nombre.getText());
            json.put("apellido", edt_apellido.getText());
            json.put("telefono", edt_telefono.getText());
            json.put("img", edt_imagen.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mihandler = new Handler(MainActivity.getInstance());

        Hilo hilo = new Hilo(mihandler, "http://192.168.1.40:3000/nuevaPersona", true, "POST", json);
        hilo.start();



    }


}
