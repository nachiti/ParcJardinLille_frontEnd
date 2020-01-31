package com.fr.parcjardinlille.models;

import java.util.List;

public class Service {

    private  Long id;
    private String nomcategorie;

    public Service(){}

    public Service(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nomcategorie='" + nomcategorie + '\'' +
                '}';
    }
}
