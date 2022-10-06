package gestion.banque;

import java.io.*;

public class FichierDeCompte  {
    private String nomDuFichier = "Compte.dat";
    private ObjectOutputStream ofW; // Pour ecrire dans un fichier d'objet
    private ObjectInputStream ofR; // Pour lire dans un fichier d'objet
    private char mode; // Mode de traitement (lecture / ecriture)
  
    public boolean ouvrir(String s) {
        try {
            mode = (s.toUpperCase()).charAt(0);
            if (mode == 'R' || mode == 'L') 
                ofR  = new ObjectInputStream(new FileInputStream(nomDuFichier));
            else if (mode == 'W' || mode == 'E') 
                ofW  = new ObjectOutputStream(new FileOutputStream(nomDuFichier));
            return true;
        }
        catch (IOException e)	{
            return false;
        }
    }
    
    public void fermer() {
        try   {
            if (mode == 'R' || mode == 'L') ofR.close();
            else if (mode == 'W' || mode == 'E')  ofW.close();
        }
        catch (IOException e)  {
            System.out.println(nomDuFichier+" : Erreur à la fermeture "); 
        }
    }

    // Retourner un objet de type ListeDeCompte
    public ListeDeCompte lire() {
        try {
            ListeDeCompte tmp = (ListeDeCompte) ofR.readObject();
            return tmp;
        }
        catch (IOException e) { 
            System.out.println(nomDuFichier + " : Erreur  de lecture "); 
        }
        catch (ClassNotFoundException e) { 
            System.out.println(nomDuFichier + " n'est pas du bon format "); 
        }
        return null;
    }
        
    // Sauvegarder un objet de type ListeDeCompte
    public void ecrire(ListeDeCompte tmp) {
        try {
            if (tmp != null)  ofW.writeObject(tmp);
        }
        catch (IOException e) {  
            System.out.println(nomDuFichier + " : Erreur en cours d'écriture "); 
        }
    }
}