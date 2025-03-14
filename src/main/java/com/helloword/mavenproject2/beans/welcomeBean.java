/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helloword.mavenproject2.beans;

import business.SessionManager;
import business.UtilisateurEntrepriseBean;
import entities.Utilisateur;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * @author cons
 */
@Named("welcomeBean")
@RequestScoped
public class welcomeBean {
   private String nom;
   private String message;
   private String email;
   private String password;
   
   @Inject
   private UtilisateurEntrepriseBean utilisateurEntrepriseBean;
   
   @Inject
    private SessionManager sessionManager;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

   public String getNom() {
        return nom;
   }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String sAuthentifier(){
        Utilisateur utilisateur = utilisateurEntrepriseBean.authentifier(email, password);
        FacesContext context = FacesContext.getCurrentInstance();
        if(utilisateur != null){
            sessionManager.createSession("user", email);
            return "home?faces-redirect-true";
        }
        else{
            this.message = "Votre email ou mot de passe est incorrect.";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
        
        return null;
    }
    
    public void afficherMessage(){
        if (nom != null && !nom.trim().isEmpty()) {
            message = "Selamat datang, " + nom + "!";
       }
        else{
            message = "";
        }
    }
}
