package com.example.productosmascotasbd.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "productomascota")
public class Productomascotabd extends RepresentationModel<Productomascotabd>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "No puede ingresar un nombre vacio")
    @Column(name= "nombreProducto")
    private String nombreProducto;
    @NotBlank(message = "No puede ingresar un destino vacio")
    @Column(name= "destino")
    private String destino;
    @NotBlank(message = "No puede ingresar una ubicación actual vacio")
    @Column(name= "ubicacionActual")
    private String ubicacionActual;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name= "estadoEnvio")
    private Estadoenviobd estadoEnvio;

    public Productomascotabd() {
    }
    
    public Productomascotabd(Long id, String nombreProducto, String destino, String ubicacionActual, Estadoenviobd estadoEnvio) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.destino = destino;
        this.ubicacionActual = ubicacionActual;
        this.estadoEnvio = estadoEnvio;
    }

    // Getters y Setters

    public Long getId(){
        return id;
    }

    public String getNombreProducto(){
        return nombreProducto;
    }

    public String getDestino(){
        return destino;
    }

    public String getUbicacionActual(){
        return ubicacionActual;
    }

    public Estadoenviobd getEstadoEnvio(){
        return estadoEnvio;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setNombreProducto(String nombreProducto){
        this.nombreProducto = nombreProducto;
    }

    public void setDestino(String destino){
        this.destino = destino;
    }

    public void setUbicacionActual(String ubicacionActual){
        this.ubicacionActual = ubicacionActual;
    }

    public void setEstadoEnvio(Estadoenviobd estadoEnvio){
        this.estadoEnvio = estadoEnvio;
    }

}