package com.fr.parcjardinlille.models;

import com.google.gson.annotations.SerializedName;

public class Commentaire {

    @SerializedName("id" )
    private Long id;
    @SerializedName("nomUtilisateur" )
    private String utilisateur;
    @SerializedName("note" )
    private int note;
    @SerializedName("message" )
    private String message;
    @SerializedName("ParcJardin" )
    private ParcJardin parcJardin;

    public Commentaire(Long id, String utilisateur, int note, String message, ParcJardin parcJardin) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.note = note;
        this.message = message;
        this.parcJardin = parcJardin;
    }

    public Commentaire(String utilisateur, int note, String message, ParcJardin parcJardin) {
        this.utilisateur = utilisateur;
        this.note = note;
        this.message = message;
        this.parcJardin = parcJardin;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", note=" + note +
                ", message='" + message + '\'' +
                ", parcJardin=" + parcJardin +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ParcJardin getParcJardin() {
        return parcJardin;
    }

    public void setParcJardin(ParcJardin parcJardin) {
        this.parcJardin = parcJardin;
    }
}
