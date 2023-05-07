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
public class Offre {
    int idoffre;
    int user,count;
    String description,titre,firstName,lastName,srcimage;
    float rating,salaireh,sumr;

    public Offre() {
        
    }

    public Offre(String description, String titre, float salaireh) {
        
        this.description = description;
        this.titre = titre;
        this.salaireh = salaireh;
    }


    public int getIdoffre() {
        return idoffre;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getSrcimage() {
        return srcimage;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    
    public void setSrcimage(String srcimage) {
        this.srcimage = srcimage;
    }

    public void setIdoffre(int idoffre) {
        this.idoffre = idoffre;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    public float getSalaireh() {
        return salaireh;
    }

    public void setSalaireh(float salaireh) {
        this.salaireh = salaireh;
    }

    
    public float getSumr() {
        return sumr;
    }

    public void setSumr(float sumr) {
        this.sumr = sumr;
    }
   
    @Override
    public String toString() {
        return "Offre{" + "id=" + idoffre + ", user=" + user + ", count=" + count + ", description=" + description + ", titre=" + titre +", rating=" + rating +", salaireh=" + salaireh +", sumr=" + sumr  + '}';
    }
    
    
    
}
