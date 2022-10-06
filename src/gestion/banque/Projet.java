package gestion.banque;

import java.util.*;

public class Projet {

    public static void main (String [] argument) {
        // Choix des variables
        int choix = 0 ;
        Scanner lectureClavier = new Scanner(System.in);
	String numeroLu = "";
        
	// Créer une liste et un fichier de compte
        ListeDeCompte ldc = new ListeDeCompte();
        FichierDeCompte fichier = new FichierDeCompte();
        if (fichier.ouvrir("L")) { // Ouvrir le fichier
            ldc = fichier.lire(); // Lire le fichier
            fichier.fermer(); // Fermer le fichier
        }       
	  
        // tant que choix different de 6 on repète l'affichage 
        do {
            System.out.println("1. Creation d'un compte");
            System.out.println("2. Affichage d'un compte");
            System.out.println("3. Ecrire une ligne comptable");
            System.out.println("4. Supprimer un compte ");
            System.out.println("5. Sortir");
            System.out.println("6. De l'aide");

            System.out.println("Saisir votre choix ");
            choix = lectureClavier.nextInt();
            
            switch (choix)	{
                case 1 :
                    System.out.print (" Compte Epargne (o/n) : ");
                    char cpte = lectureClavier.next().charAt(0);
                    if (cpte == 'o') ldc.ajouteUnCompte("E") ;
                    else ldc.ajouteUnCompte("A");  
                break;
                case 2 :
                    System.out.print ( "Quel compte souhaitez vous afficher ? : ");
                    numeroLu = lectureClavier.next();
                    ldc.rechercheUnCompte(numeroLu);
                    // Affichage des statistiques
                    Compte stats = new Compte("");
                    // Le compte existe-t-il dans la liste ?
                    stats = ldc.quelCompte(numeroLu);
                    // si Oui
                    if (stats != null) {
                    CalculStats s = new CalculStats(stats);
                    s.statParMotif();
                    }
                break;
                case 3 :
                    System.out.print ( "Pour quel compte souhaitez vous creer une ligne ? : ");
                    numeroLu = lectureClavier.next();
                    ldc.ajouteUneLigne(numeroLu);
                break;
                case 4 :
                    System.out.print ( "Quel compte souhaitez vous supprimer ? : ");
                    numeroLu = lectureClavier.next();
                    ldc.supprimeUnCompte(numeroLu);
                break;
                case 5 :
                    System.out.println("Sauvegarde des donnees dans Compte.dat");	
                    fichier.ouvrir("E"); 
                    fichier.ecrire(ldc);
                    fichier.fermer();
                    Sortir();    
                break;
                case 6 : Aide();
                break;
                default : System.out.println("Cette option n'existe pas ");
            }
	} while (choix != 6);
    }
	
    // On fait des appels de fonction pour raccourcir le main	
	
    // pour sortir poliment du programme 
    public static void Sortir( ) {
        System.out.println("Au revoir et à bientot");
        System.exit(0) ;    
    }
	
    // pour obtenir de l'aide
    public static void Aide( ) {
        System.out.println("Option 1. Pour creer un compte Courant entrer C ");
        System.out.println("          Pour creer un compte Joint entrer J ");
        System.out.println("          Pour creer un compte Epargne entrer E");
        System.out.print("          Puis, entrer le numero du compte, et"); 
        System.out.println(" sa premiere valeur creditee ");
        System.out.println("          Dans le cas d'un compte epargne, entrer le taux ");
        System.out.println("Option 2. Le systeme affiche les donnees du compte choisi ");
        System.out.println("Option 3. Ecrire une ligne comptable");
        System.out.println("Option 4. Pour supprimer un compte");
        System.out.println("Option 5. Pour quitter le programme");
        System.out.println("Option 6. Pour afficher de l'aide");
    }	
}
