package com.fr.parcjardinlille.models;

class Image {

    private Long id;
    private String nom;
    private ParcJardin parcJardin;

    public Image(String nom, ParcJardin parcJardin) {
        this.nom = nom;
        this.parcJardin = parcJardin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ParcJardin getParcJardin() {
        return parcJardin;
    }

    public void setParcJardin(ParcJardin parcJardin) {
        this.parcJardin = parcJardin;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", parcJardin=" + parcJardin +
                '}';
    }
}
