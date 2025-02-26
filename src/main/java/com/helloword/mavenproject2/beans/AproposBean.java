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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author cons
 */
@Named(value = "aproposBean")
@RequestScoped
public class AproposBean {
    @Inject
    private SessionManager sessionManager;
    
    
    
    public void deconnexion(){
        sessionManager.invalidateSession();
    }
    
    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;
    
    private String username;
    private String email;
    private String password;
    private String description;
    private String confirmPassword;

    public void modifier() {
        // Récupérer l'email de l'utilisateur connecté depuis la session
        String emailSession = sessionManager.getValueFromSession("email");
        if (emailSession == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Utilisateur non connecté", null));
            return;
        }

        // Trouver l'utilisateur en base de données
        Utilisateur utilisateur = utilisateurEntrepriseBean.trouverUtilisateurParEmail(emailSession);
        if (utilisateur == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Utilisateur introuvable", null));
            return;
        }

        // Mise à jour des données si elles sont non nulles
        if (username != null && !username.isEmpty()) {
            utilisateur.setUsername(username);
        }
        if (email != null && !email.isEmpty()) {
            utilisateur.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            utilisateur.setPassword(BCrypt.hashpw(password, BCrypt.gensalt())); // Hachage du mot de passe
        }
        if (description != null && !description.isEmpty()) {
            utilisateur.setDescription(description);
        }

        // Mettre à jour l'utilisateur dans la base
        utilisateurEntrepriseBean.mettreAjourUtilisateur(utilisateur);
        
        // Mettre à jour la session si l'email a changé
        if (!emailSession.equals(utilisateur.getEmail())) {
            sessionManager.createSession("email", utilisateur.getEmail());
        }

        // Message de confirmation
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Compte mis à jour avec succès", null));
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
