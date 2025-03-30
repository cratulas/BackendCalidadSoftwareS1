package com.example.backend_bd;

import java.util.List;

public class Evento {
    private Integer id;
    private String nombre;
    private Categoria categoria;
    private String fecha;
    private String ubicacion;
    private List<String> juegosRelacionados;
    private String descripcion;
    private String direccion;
    private String hora;
    private String organizadores;
    private List<String> serviciosDisponibles;
    private List<String> expositores;

    public Evento() {}

    public Evento(Integer id, String nombre, Categoria categoria, String fecha, String ubicacion,
                  List<String> juegosRelacionados, String descripcion, String direccion, String hora,
                  String organizadores, List<String> serviciosDisponibles, List<String> expositores) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.juegosRelacionados = juegosRelacionados;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.hora = hora;
        this.organizadores = organizadores;
        this.serviciosDisponibles = serviciosDisponibles;
        this.expositores = expositores;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<String> getJuegosRelacionados() {
        return juegosRelacionados;
    }

    public void setJuegosRelacionados(List<String> juegosRelacionados) {
        this.juegosRelacionados = juegosRelacionados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(String organizadores) {
        this.organizadores = organizadores;
    }

    public List<String> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    public void setServiciosDisponibles(List<String> serviciosDisponibles) {
        this.serviciosDisponibles = serviciosDisponibles;
    }

    public List<String> getExpositores() {
        return expositores;
    }

    public void setExpositores(List<String> expositores) {
        this.expositores = expositores;
    }
}
