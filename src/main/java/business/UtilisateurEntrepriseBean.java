/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package business;

import entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author cons
 */
@Stateless
@LocalBean
public class UtilisateurEntrepriseBean {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void ajouterUtilisateurEntreprise(String username, String email, String password, String description){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Utilisateur utilisateur = new Utilisateur (username, email,hashedPassword, description);
        em.persist(utilisateur);
    }
    
     public List<Utilisateur> listerTousLesUtilisateurs() {
        return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
    }

    @Transactional
    public void supprimerUtilisateur(Long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        if (utilisateur != null) {
            em.remove(utilisateur);
        }
    }

    public Utilisateur trouverUtilisateurParId(Long id) {
        return em.find(Utilisateur.class, id);
    }
    
    @Transactional
    public void mettreAjourUtilisateur(Utilisateur utilisateur){
        em.merge(utilisateur);
    }
    
    public Utilisateur TrouverUtilisateurParUsername(String username){
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.username = :username", Utilisateur.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Utilisateur trouverUtilisateurParEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    } 
    
    public boolean verifierMotdePasse(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword); 
    }
    
    public Utilisateur authentifier(String email, String password){
        Utilisateur user = trouverUtilisateurParEmail(email);
        if (user != null && verifierMotdePasse(password, user.getPassword())){
            return user;
        }
        return null;
    }
}
