/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 *
 * @author cons
 */
@Entity
@Table(name = "lieu")
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom_lieu;
    private String description;
    private String longitude;
    private String latitude;


    public Lieu() {
    }

    public Lieu(String nom_lieu, String description, String longitude, String latitude) {
        this.nom_lieu = nom_lieu;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public int getId() { return id; }
    public String getNom() { return nom_lieu; }
    public void setNom(String nom_lieu) { this.nom_lieu = nom_lieu; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }
}
