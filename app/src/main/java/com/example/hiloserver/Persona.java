package com.example.hiloserver;

import java.util.Arrays;

public class Persona {

    private String nombre;
    private String apellido;
    private String telefono ;
    private byte[]imagen ;
    private String img ;
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public void setNombre(String texto1) {
        this.nombre = texto1;
    }
    public String getNombre() {
        return nombre;
    }
    public void setApellido(String texto2) {
        this.apellido = texto2;
    }
    public String getApellido() {
        return apellido;
    }

    public Persona (){}

    public Persona(String nombre, String apellido,String telefono,String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.img = img ;
    }


}
