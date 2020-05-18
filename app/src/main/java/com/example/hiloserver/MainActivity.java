package com.example.hiloserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;



import java.util.ArrayList;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Handler.Callback ,MyOnItemClick  {


    List<Persona> personas;
    Handler handler ;
    MyAdapter adapter;
    RecyclerView list;

    private static MainActivity instance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        ActionBar toolbar =  getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);


        handler = new Handler(this);
        personas = new ArrayList<>();


        llenarLista( handler);




        list = (RecyclerView)findViewById(R.id.list);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);




    }

    public static MainActivity getInstance() {
        return instance; }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

        if (msg.arg1 ==1) {


           this.personas = (List<Persona>) msg.obj;

            for (int i = 0 ;i< personas.size();i++) {

                Log.d("datos en personas", String.valueOf(personas.get(i).getNombre()));
                Hilo hiloimagen = new Hilo(handler,personas.get(i).getImg(),false ,i);
                hiloimagen.start();

            }

        }else if(msg.arg1 ==2) {

            personas.get(msg.arg2).setImagen((byte[]) msg.obj);
            Log.d("danull", String.valueOf(msg.arg2));

            this.adapter = new MyAdapter(personas ,MainActivity.this);
            list.setAdapter(adapter);
            this.adapter.notifyDataSetChanged();
        }

        else if(msg.arg1 ==5) {
            Log.d("llenarnuevamente","respuesta vacia");
            llenarLista(this.handler);
        }






        return false;
    }


    public void llenarLista(Handler handler){

        Hilo hilo = new Hilo(handler,"http://192.168.1.40:3000/personas",true,"GET", null);
        hilo.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.agregar_persona) {

            Intent intent = new Intent(this,CargaPersona.class);

            super.startActivity(intent);

        }
        if (id == android.R.id.home) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onItemClick(int position) {

        Log.d("se hizo click", "se hizo click en la posicion " + position);
    }
}
