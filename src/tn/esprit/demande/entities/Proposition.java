/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.entities;

/**
 *
 * @author mohamed gabsi
 */
public class Proposition {
    int id,id_demande,id_freelancer;
    String firstName,lastName;
    String email;
    String titre;
    public Proposition() {
    }

    public Proposition(int id, int id_demande, int id_freelancer) {
        this.id = id;
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
    }

    public Proposition(int id_demande, int id_freelancer) {
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
    }

    public Proposition(int id, int id_demande, String firstName, String lastName, String email) {
        this.id = id;
        this.id_demande = id_demande;
        //this.titre= titre;
        this.firstName= firstName;
        this.lastName= lastName;
        this.email= email;
    }

    public Proposition(int id_demande, String firstName, String lastName) {
        this.id_demande = id_demande;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Proposition(int id, int id_demande, int id_freelancer,String firstName, String lastName, String titre, String email) {
        this.id = id;
        this.id_demande = id_demande;
        this.id_freelancer = id_freelancer;
        this.titre= titre;
        this.firstName= firstName;
        this.lastName= lastName;
        this.email = email;
    }
    
     public Proposition(int id, int id_demande,String firstName, String lastName, String titre, String email) {
        this.id = id;
        this.id_demande = id_demande;
        //this.id_freelancer = id_freelancer;
        this.titre= titre;
        this.firstName= firstName;
        this.lastName= lastName;
        this.email = email;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public int getId_freelancer() {
        return id_freelancer;
    }

    public void setId_freelancer(int id_freelancer) {
        this.id_freelancer = id_freelancer;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
//
   public void setEmail(String email) {
       this.email = email;
    }

    @Override
    public String toString() {
        //return "Proposition{" + "id=" + id + ", id_demande=" + id_demande + ", titre=" + titre + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
        return "Proposition{" + "id=" + id + ", id_demande=" + id_demande + ", firstName=" + firstName + ", lastName=" + lastName + ",titre= " + titre + ", email=" + email + '}';
   //return "Proposition{" +"id_demande=" + id_demande + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }

    
 
}
