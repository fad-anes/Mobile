package entites;

import java.util.Date;

public class Event {

    private int id;
    private String nom;
    private Date date;
    private String lieu;
    private int nbrPlace;
    private String image;

    public Event() {
    }

    public Event(int id, String nom, Date date, String lieu, int nbrPlace, String image) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.nbrPlace = nbrPlace;
        this.image = image;
    }

    public Event(String nom, Date date, String lieu, int nbrPlace, String image) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.nbrPlace = nbrPlace;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}