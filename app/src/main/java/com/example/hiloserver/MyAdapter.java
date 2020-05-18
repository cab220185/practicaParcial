package com.example.hiloserver;

//import android.support.v7.widget.RecyclerView;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Persona> lista;
    private MyOnItemClick listener;

    public MyAdapter(List<Persona> lista, MyOnItemClick listener)
    {

        this.lista=lista;
        this.listener= listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,
                parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v , this.listener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



            Persona p = lista.get(position);

             Log.d("danull", String.valueOf(p.getImagen()) );
             Log.d("danull", String.valueOf(p.getNombre()) );

            if (p.getImagen()!= null) {
                holder.txtNombre.setText(p.getNombre());
                holder.txtApellido.setText(p.getApellido());
                holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(p.getImagen(), 0, p.getImagen().length));
                holder.setPosition(position);
            }

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
