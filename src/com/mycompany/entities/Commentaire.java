/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author marni
 */
public class Commentaire {
    private int idcommentaire,user,offre,jaime,djaime;
    private String commentaire;

    public Commentaire() {
    }

    public Commentaire(int idcommentaire, String commentaire, int user,int offre,int jaime,int djaime) {
        this.idcommentaire = idcommentaire;
        this.commentaire = commentaire;
        this.user = user;
        this.offre = offre;
        this.jaime = jaime;
        this.djaime = djaime;
    }

    public Commentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    
    public int getOffre() {
        return offre;
    }

    public void setOffre(int offre) {
        this.offre = offre;
    }
    
    
     public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }
    
    
     public int getDjaime() {
        return djaime;
    }

    public void setDjaime(int djaime) {
        this.djaime = djaime;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + idcommentaire + ", user=" + user + ", offre=" + offre + ", jaime=" + jaime + ", djaime=" + djaime +", commentaire=" + commentaire + '}';
    }
    
    
    
    
    
}
