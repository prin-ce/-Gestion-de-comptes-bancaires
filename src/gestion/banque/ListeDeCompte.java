package gestion.banque;

import java.util.*;
import java.io.*;


public class ListeDeCompte implements Serializable {
    
    // Le constructeur de la classe ListeDeCompte fait appel au constructeur de la classe HashMap
    private HashMap<String, Compte> liste;
    
    public ListeDeCompte()   {
        liste = new HashMap<String, Compte>();		
    }
    
    // Ajouter un compte
    public void ajouteUnCompte(String t) {
        Compte nouveau = new Compte("");
        if (t.equalsIgnoreCase("A")) nouveau = new Compte(); // Si le paramètre vaut "A" un compte est créé
        else if (t.equalsIgnoreCase("E"))  nouveau = new CpteEpargne(); // Sinon un compte épargne est créé
        String cle = nouveau.quelNumeroDeCompte();
        if (liste.get(cle) == null) liste.put(cle, nouveau); // Une fois créé, le compte est inséré dans le dictionnaire
        else System.out.println("Ce compte existe deja !");
    }
    
    // Créer une ligne pour un compte donné
    public void ajouteUneLigne(String n) {
        // Creation de la ligne
        String cle = n;
        Compte c = (Compte) liste.get(cle);
        
        // Tester si la ligne existe ou pas dans la liste
        if (c != null)	c.creerLigne();
        else System.out.println("Le systeme ne connait pas le compte "+n);
    }
    
    // Retourner un objet Compte
    public Compte  quelCompte(String n){
        String cle = n;
        Compte c = (Compte) liste.get(cle);
        if (c == null)  
        System.out.println("Le systeme ne connait pas le compte "+n);
        return(c);
    }
    
    // Rechercher et afficher un compte
    public void rechercheUnCompte (String n) {
        String cle = n;
        Compte c = (Compte) liste.get(cle);
        if (c != null)	c.afficherCpte();
        else System.out.println("Le systeme ne connait pas le compte "+n);
    }
    
    // Supprimer un compte de la liste
    public void supprimeUnCompte(String n) {
        String cle = n;
        Compte c = (Compte) liste.get(cle);
        if (c != null)  {
            liste.remove(cle);
            System.out.println(n + " a ete supprime ");
        } 
        else System.out.println(n + " est inconnu ! ");
    }
}

