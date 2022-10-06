package gestion.banque;


import java.awt.*;

public class CalculStats  {
    Compte stats;
    private double prctDiv, prctLoy, prctAli;
    
    // Le calcul des statistiques est réalisé à partir d'un objet Compte
    public CalculStats(Compte c) {
        stats = c;
    }
    
    private  double pourcentage(double nb, double t){
        double    prct = 0;
        if (t > 0) prct = (double) nb / t  * 100;
        return prct;
    }
    
    // La méthode calcule les statistiques en fonction du motif de l'opération
    public void statParMotif() {
        double totCredit=0;
        double totDiv=0, totLoy=0, totAli=0;
        
        // Pour chaque ligne comptable enregistrée :
        for(int i = 0; i <= stats.combienLignes() ; i++){
            
            // 1- S'il s'agit d'un crédit, en faire la somme
            if (stats.quelleLigne(i).quelleValeur() > 0) 
                totCredit = totCredit +  stats.quelleLigne(i).quelleValeur();
            
            // 2- Si le motif est "Divers" en faire la somme
            if (stats.quelleLigne(i).quelMotif().equalsIgnoreCase("Divers"))
                totDiv = totDiv + Math.abs(stats.quelleLigne(i).quelleValeur());
            
            // 3- Si le motif est "Loyer" en faire la somme
            if (stats.quelleLigne(i).quelMotif().equalsIgnoreCase("Loyer"))
                totLoy = totLoy +  Math.abs(stats.quelleLigne(i).quelleValeur());
            
            // 4- Si le motif est "Alimentation" en faire la somme
            if (stats.quelleLigne(i).quelMotif().equalsIgnoreCase("Alimentation"))
                totAli = totAli +  Math.abs(stats.quelleLigne(i).quelleValeur());
        }
        
        // Calculer le pourcentage pour chacun des motifs
        prctAli = pourcentage(totAli, totCredit);
        prctLoy = pourcentage(totLoy, totCredit);
        prctDiv = pourcentage(totDiv, totCredit);
        System.out.print("A : "+prctAli+ "L : "+prctLoy+ "D : "+prctDiv); 
    }
}
