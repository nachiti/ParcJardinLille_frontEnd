package com.fr.parcjardinlille.models;

public class Commentaire {

    private Long id;
    private Utilisateur utilisateur;
    private int note;
    private String message;
    private ParcJardin parcJardin;

    public Commentaire(Utilisateur utilisateur, int note, String message, ParcJardin parcJardin) {
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
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
