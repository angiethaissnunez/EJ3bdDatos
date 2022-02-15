package com.example.ej3bddatos;

public class Persona {

    String nombres,apellidos,correo,dirrecion;
    int id,edad;
    public Persona(int id,String nombres,String apellidos,int edad,String correo,String dirreccion){
        this.id=id;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.correo=correo;
        this.edad=edad;
        this.dirrecion=dirreccion;
    }
    public Persona(){}
    public int getId() {return id;}
    public void setId(int id) { this.id = id;}
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombre) {
        this.nombres = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getDirrecion() {
        return dirrecion;
    }
    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

}
