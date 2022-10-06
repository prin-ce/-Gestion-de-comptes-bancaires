package gestion.banque;


import java.io.*;
import java.util.Scanner;


public class CpteEpargne extends Compte implements Serializable {	 
    private double  taux ; 
    Scanner lectureClavier = new Scanner(System.in);
    
    //
    public CpteEpargne() {
        super("Epargne");
        taux = controleTaux();
     }
    
    //
    private double controleTaux() {
        double tmp;
        do {
            System.out.print("Taux de placement :     ");
            tmp = lectureClavier.nextDouble();
        } while (tmp < 0);
        return tmp;
    }
    
    //
    public  void afficherCpte()   {
            super.afficherCpte();
            System.out.println(" Taux d'epargne du compte :  " + taux);
    }
    
    //
    public double quelTaux()	{
            return taux;
    }
}
