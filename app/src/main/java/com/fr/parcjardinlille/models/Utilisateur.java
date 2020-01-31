package com.fr.parcjardinlille.models;

import java.util.Collection;

class Utilisateur {

    private Long id;
    private String pseudo;
    private String password;
    private String photo;
    private String mail;
    private Collection<Commentaire> commentaires;

    public Utilisateur(String pseudo, String password, String photo, String mail, Collection<Commentaire> commentaires) {
        this.pseudo = pseudo;
        this.password = password;
        this.photo = photo;
        this.mail = mail;
        this.commentaires = commentaires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Collection<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", mail='" + mail + '\'' +
                ", commentaires=" + commentaires +
                '}';
    }
}
