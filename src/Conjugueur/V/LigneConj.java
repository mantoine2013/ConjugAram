package Conjugueur.V;

import Conjugueur.V.DéclinaisonStyledDoc;
import java.awt.Font;

public class LigneConj {                               // ligne de conjugaison
      public String libpersonne ;                              // ex : "1è. p. s."
      public DéclinaisonStyledDoc déclinaison ;
      public String translittération ;                                                                            // LigneConj

    /**
     * Fonction : ligne ds le tab des déclinaison
     * Fction appelante : ConjTableM::AjouterLigne
     * @param plibpersonne libellé de la personne : "1è. s.", "2è. s.", ...
     * @param PoliceDéclinaison police 2è col
     * @param alignement StyleConstants.ALIGN_RIGHT
    */
    public LigneConj(String plibpersonne, Font PoliceDéclinaison, int alignement) {
        if (DEBUG) { System.out.println("LigneConj::LigneConj, plibpersonne = " +  plibpersonne+ ", PoliceDéclinaison.getFamily() = " + PoliceDéclinaison.getFamily()) ;  }
        this.libpersonne = plibpersonne;
        this.déclinaison = new DéclinaisonStyledDoc (PoliceDéclinaison, alignement);
    }                                                                           // LigneConj
    private static final boolean DEBUG = false ;
}                                                                               // LigneConj                                                                               // Ligneconj