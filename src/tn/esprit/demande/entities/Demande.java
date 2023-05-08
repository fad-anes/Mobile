/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.entities;

/**
 *
 * @author mohamed gabsi
 */
public class Demande {
    int id;
    int user;
    String description,titre,delai,langage,firstName,lastName;
    float salaire;

    public Demande() {
    }

    public Demande(int id) {
        this.id = id;
    }
    
    public Demande(String titre, String description, float salaire, String delai, String langage) {
        
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai=delai;
        this.langage=langage;
    }


public Demande(int id,String titre, String description, float salaire, String delai, String langage) {
        this.id=id;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.delai=delai;
        this.langage=langage;
    }

    public Demande(String titre, float salaire) {
        this.titre = titre;
        this.salaire = salaire;
    }



    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
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
    
    
    
     public float getSalaire() {
        return salaire;
    }


    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

     public String getDelai() {
        return delai;
    }

     public void setDelai(String delai) {
        this.delai = delai;
    }

     public String getLangage() {
        return langage;
    }

     public void setLangage(String langage) {
        this.langage = langage;
    }
    
    
    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", user=" + user + ", titre=" + titre + ", description=" + description +", salaire=" + salaire +", delai=" + delai + ", langage=" + langage + '}';
    }
    
}
