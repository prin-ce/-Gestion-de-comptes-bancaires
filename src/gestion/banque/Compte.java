package gestion.banque;

import java.io.*;
import java.util.*;

public class Compte implements Serializable {
    private String typeCpte ;
    protected double val_courante, taux;
    public String numeroCpte ;
    private LigneComptable []ligne;
    public static final int NBLigne = 10 ;
    private int nbLigneReel ; 
    
    Scanner lectureClavier = new Scanner(System.in);
	
    public void creerCpte() 	{ 
        char tpm;

        do {
            System.out.print("Type du compte ");
            System.out.print("(Tapez C pour Courant, J pour Joint, E pour Epargne) : ");
            tpm = lectureClavier.next().charAt(0);
        } while ( tpm != 'C' && tpm!= 'J' && tpm != 'E');
        switch (tpm)	{
            case 'C' : typeCpte = "Courant";
            break;
            case 'J' : typeCpte = "Joint";
            break;
            case 'E' : typeCpte = "Epargne";
            break;
            default :	System.out.println("Ce type de compte n'existe pas ");
        }
        System.out.print("Numéro du compte : ");
        numeroCpte = lectureClavier.next();;
        if ( typeCpte.equalsIgnoreCase("Epargne")){
            System.out.print("Taux de placement :     ");
            taux = lectureClavier.nextDouble();
        }    
        System.out.print("Valeur initiale du compte : ");
        val_courante = lectureClavier.nextDouble();   
        nbLigneReel = 0;
     }
	
    public Compte () { 
        typeCpte = controleType();
        System.out.print("Numéro du compte : ");
        numeroCpte = lectureClavier.next();
        val_courante = controleValinit();  
        ligne = new LigneComptable[NBLigne]; //on crée en mémoire ligne sous la forme d'un tableau
        nbLigneReel = -1; // initialisation
    }
    
    // Préparation du calcul des stats
    public LigneComptable quelleLigne(int n) {
        return ligne[n];
    }
    
    public int combienLignes() {
        return nbLigneReel;
    }

    public  Compte( String type)  {
        if (type.equalsIgnoreCase("Epargne")) {
            typeCpte = type;
            System.out.print("Numéro du compte : ");
            numeroCpte = lectureClavier.next();
            val_courante = controleValinit();   
            ligne = new LigneComptable[NBLigne];
            nbLigneReel = -1;
        }
    }

    public String quelTypeDeCompte() 	{
        return typeCpte;
    }

    public String quelNumeroDeCompte()	{
        return numeroCpte;
    }

    public double quelleValeurCourante()	{
        return val_courante;
    }

    private String controleType()		{
        char tpmc;
        String tpmS = "";
        do {
            System.out.print("Type du compte ");
            System.out.print("(Tapez C pour Courant, J pour Joint) : ");
            tpmc = lectureClavier.next().charAt(0);
        } while ( tpmc != 'C' && tpmc != 'J' );
        switch (tpmc) {
            case 'C' : tpmS = "Courant";
            break;
            case 'J' : tpmS = "Joint";
            break;
            default :	System.out.println("Ce type de compte n'existe pas ");
        }
        return tpmS;
    }

    private double controleValinit()	 {
        double tpm, tpmval;
        do {
                System.out.print("Valeur initiale du compte : ");
                tpmval= lectureClavier.nextDouble();   
        } while ( tpmval <= 0);
        return tpmval;
    }

    public void creerLigne() {
        nbLigneReel++; // pour incrémenter le nombre de lignes à chaque ligne créée

        // Si le nombre de ligne est < 10, création d'une nouvelle ligne
        if (nbLigneReel < NBLigne) 
            ligne [nbLigneReel] = new LigneComptable();

        // Sinon décaler toutes les lignes vers le haut
        else {
            nbLigneReel--;
            decalerLesLignes();
            ligne [nbLigneReel] = new LigneComptable();
        }
        val_courante = val_courante + ligne[nbLigneReel].quelleValeur(); // On modifie la valeur courante du compte
    }

    private void decalerLesLignes() {
        for(int i = 1; i < NBLigne ; i++)
        ligne[i-1] = ligne[i];
    }

    public  void afficherCpte() {
        System.out.print("Le compte n° : " + numeroCpte );
        System.out.println(" est un compte " + typeCpte);

        // Si une ligne comptable a été créée, l'afficher
        if (nbLigneReel >=0) {
                for (int i = 0; i <= nbLigneReel; i++) ligne[i].afficherLigne();
        }
        System.out.println("Valeur courante : " + val_courante);
        if (val_courante < 0) System.out.println("Attention compte débiteur ... !!!");
    }
}
