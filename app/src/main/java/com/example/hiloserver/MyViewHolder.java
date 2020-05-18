package com.example.hiloserver;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView txtNombre;
    TextView txtApellido;
    ImageView imageView  ;

    private MyOnItemClick listener;
    private int position;

    public void setPosition(int position)
    {
        this.position = position;
    }

    public MyViewHolder(View itemView , MyOnItemClick listener) {
            super (itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtApellido = (TextView) itemView.findViewById(R.id.txtApellido);
            imageView  = (ImageView) itemView.findViewById(R.id.imageView) ;
            itemView.setOnClickListener(this);
            this.listener = listener ;
        }

    @Override
    public void onClick(View v) {

        listener.onItemClick(position);
    }
}
