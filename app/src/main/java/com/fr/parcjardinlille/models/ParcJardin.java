package com.fr.parcjardinlille.models;


import java.util.Collection;

public class ParcJardin   {

    private Long id;
    private Type type;
    private String nom;
    private double latitude, longitude;
    private String description;
    private Collection<Service> services;
    private String adresse;
    private String horaire;
    private int noteGlobale;
    private Collection<Commentaire> commentaires;
    private Collection<Image> images;

    public ParcJardin(Type type, String nom, double latitude, double longitude, String description, String adresse) {
        this.type = type;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public int getNoteGlobale() {
        return noteGlobale;
    }

    public void setNoteGlobale(int noteGlobale) {
        this.noteGlobale = noteGlobale;
    }

    public Collection<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Collection<Image> getImages() {
        return images;
    }

    public void setImages(Collection<Image> images) {
        this.images = images;
    }
}