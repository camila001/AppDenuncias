package com.camila.appdenuncias.model;

public class Usuario {

    private String id;
    private String nombre;
    private String uid;
    private String email;
    private String telefono;

    public Usuario(){
    }

    public Usuario(String id, String nombre, String uid, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.uid = uid;
        this.email = email;
        this.telefono = telefono;
    }

    public Usuario(String id, String nombre, String uid, String email) {
        this.id = id;
        this.nombre = nombre;
        this.uid = uid;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
