package gestion.banque;

import java.io.*;
import java.text.*;
import java.util.*;

public class LigneComptable implements Serializable {
    private double valeur;
    private String date;
    private String motif;
    private String mode;
    
    Scanner lectureClavier = new Scanner(System.in);

    public LigneComptable()	{
        
        System.out.print("Entrer la valeur à créditer (+) ou débiter (-) : ");
        valeur = lectureClavier.nextDouble();
        System.out.print("Date de l'opération [jj/mm/an] : ");
        date = lectureClavier.next();
        motif = controleMotif();
        mode = controleMode();
    }
	
    private String controleMode() {
        String tpmS = "";
        char tpmc ;
        do {
            System.out.print("Mode (Taper C pour CB, N pour Cheque, V pour Virement) : ");
            tpmc = lectureClavier.next().charAt(0);
        } while ( tpmc != 'C' && tpmc != 'N' && tpmc != 'V');
        switch (tpmc) {
            case 'C' : tpmS = "CB";
            break;
            case 'N' : tpmS = "Cheque";
            break;
            case 'V' : tpmS = "Virement";
            break;
            default :	System.out.println("Ce mode de paiement n'existe pas ");
        }
        return tpmS;
    }
	
    private String controleMotif() {
        String tpmS = "";
        char tpmc ;
        do {
            System.out.print("Motif de l'operation ");
            System.out.print("(Taper S pour Salaire, L pour Loyer, A pour Alimenation, D pour Divers) : ");
            tpmc = lectureClavier.next().charAt(0);
        } while ( tpmc != 'S' && tpmc != 'L' && tpmc != 'A' && tpmc != 'D');
        switch (tpmc) {
            case 'S' : tpmS = "Salaire";
            break;
            case 'L' : tpmS = "Loyer";
            break;
            case 'A' : tpmS = "Alimentation";
            break;		
            case 'D' : tpmS = "Divers";
            break;
            default :	System.out.println("Motif incorrect ");
        }
        return tpmS;
    }
    
    // Contrôle de la validité d'une date
    private String controleDate() {
        int nb = 0;
        Date d = null;
        SimpleDateFormat formatIn=new SimpleDateFormat("jj/mm/yyyy");
        String sdate;
        // Tant que d n'est pas correctement initialisée
        while (d == null){
            try {
                System.out.print("Entrer une date (jj/mm/aaaa): ");
                d = formatIn.parse(lectureClavier.next());
            }
            catch(ParseException p) {
                // Si la traduction ne peut se réaliser, une exception est détectée et on incrémente un compteur
                nb++;
                
                // d. Si le compteur >= 3, la date est initialisée à la date placée en mémoire de l'ordinateur
                if (nb >= 3) d = new Date();
            }
        }
        // Lorsque la date est au bon format, on la transforme en String
        sdate = formatIn.format(d);
        return sdate;
    }
        
    public double quelleValeur() {
        return valeur ;
    }

    public String quelMotif(){
        return motif ;
    }

    public String quelMode(){
        return mode ;
    }

    public String quelleDate(){
        return date ;
    }
	
    public void afficherLigne()  {
        if (valeur < 0) 
            System.out.print("Débiter : " + valeur);
        else
            System.out.print("Créditer : " + valeur);
            System.out.println(" le : " + date + " motif  : " + motif + " mode : " + mode);
    }
}
