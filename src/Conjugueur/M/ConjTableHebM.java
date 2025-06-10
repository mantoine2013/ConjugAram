/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.JFrameConjugHebib;
import com.ibm.icu.text.Transliterator;
import javax.swing.text.BadLocationException;
import Conjugueur.V.DéclinaisonStyledDoc;
import Conjugueur.V.LigneConj;
import java.awt.Font;
import javax.swing.text.StyleConstants;


public class ConjTableHebM extends ConjTable  {
    
    /**
     * Fonction : updateTextPaneConj
     * Algorithme : 
     * 1. Remettre le tableau de conjugaison à zéro
     * 2. S'aiguiller selon le schème du verbe sélectionner
     * 2.1 Groupe Paal
     * 2.3 Groupe Piel
    * 2.3.1 S'aiguiller selon le temps       
     * 2.3.1 Temps Passé
     * 2.3.2 Temps Présent
     * 2.3.2.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     * 2.7 Groupe Piel
    * 2.7.1 S'aiguiller selon le temps       
     * 2.7.1 Temps Passé
     * 2.7.2 Temps Présent
     * 2.7.2.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     */
    @Override
    public void MAJConj(Conjugueur.C.Conjug contrôleur)  {
       if (DEBUG) System.out.println ("Conjugueur.M.ConjTableHebM::MAJConj, Verbe = "+ ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif") + ", Schème = " + Conjugueur.M.ConjugHebM.HMS.i2S(contrôleur.model.getSchème()).toString() + ", Temps = " + Conjugueur.M.ConjugHebM.HMT.i2T(contrôleur.model.getTemps()).toString() +", Voix = "+ Conjugueur.M.Conjug.V.i2V(contrôleur.model.getVoix()).toString()) ;
        this.contrôleur = contrôleur ; 
        try {
            vider() ;
            déclinaisonChildren = ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
            switch (Conjugueur.M.ConjugHebM.S.i2S(contrôleur.model.getSchème())){                                           // schème
                case Paal -> Paal () ;                            // SIMPLE + ACTIVE = Paal
                case Nifal -> {
                        // SIMPLE + PASSIVE = Nifal
                        AjouterPersonnes () ;
                        for (char c : ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) { nbcons++ ;
                            if ((nbcons == 1) && (c == ALEPH)) ;
                            else CopierCaractèRacine (String.valueOf(c)) ;
                            if (nbcons == 2)  {
                                CopierSuffixe("י") ; nbcons++ ;
                            }
                            }                                               // consonne
                            else CopierCaractèRacine (String.valueOf(c)) ;
                        } // pour tous les caractères
                        // SIMPLE + PASSIVE = Nifal
                    }
                case Piel -> Piel () ;
                case Pual -> {     }
                case Hifil -> Hifil () ;
                case Hitpael -> {
                        // INTENSIVE + PASSIVE = Pual
                        switch (Conjugueur.M.ConjugHebM.T.i2T(contrôleur.model.getTemps())) {
                            // temps
                            case Passé :
                                break ;                                              // Passé
                            case Présent :                                          // Présent
                                AjouterPersonnes () ;
                                Préfixer(IL_HMSS, "מְת") ;  Préfixer(IL_HMSP, "מְת") ; Préfixer(IL_HMFS, "מְת") ; Préfixer(IL_HMFP, "מְת") ;
                                for (char c : ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("racine").toCharArray()) CopierCaractèRacine(String.valueOf(c)) ;
                                Suffixer(IL_HMSP,  "ִים") ; Suffixer(IL_HMFS,  "ִת") ; Suffixer(IL_HMFP,  "וֹת") ;
                        } // switch (Conjugueur.M.ConjugHebM.T.i2T(contrôleur.model.getTemps()))
                        // INTENSIVE
                    }
            }                                                                   // schème

            /*            switch (selectedIndVoix) {                                           // voix
                case Active -> {                                                // Active
                    switch (selectedIndForme) {                                 // forme
                        case Simple -> Paal () ;                                // SIMPLE + ACTIVE = Paal
                        case Intensive -> Piel () ;                             // Intensive + Active = Piel
                        case Causative -> Hifil () ;                                   // Causative + Active = Hifil
                    }                                                               // forme
                }                                                               // Active
                case Passive -> {                                               // Passive
                    switch (selectedIndForme) {                                 // forme
                        case Simple ->  Nifal () ;                                       //  SIMPLE + PASSIVE = Nifal
                        case Intensive -> Pual () ;                              // INTENSIVE + PASSIVE = Pual
                        case Causative -> Hufal () ;                                    // Causative + PASSIVE = Hufal
                    }                                                           // forme
                }                                                               // Passive
                case Réfléchie -> {                                             // Réfléchie
                    switch (selectedIndForme) {                                 // forme
                        case Simple ->  Hitpael () ;
                    }                                                           // forme
                       
                    }                                                           // Réfléchie
            }                                                                   //  voix
/*                case Pual -> {
                    }
                case Hifil -> Hifil () ;
                case Hitpael -> {
                        
                        switch (Conjugueur.M.ConjugHebM.T.i2T(contrôleur.model.getTemps())) {
                            // temps
                            case Passé :
                                break ;                                              // Passé
                            case Présent :                                          // Présent
                                AjouterPersonnes () ;
                                Préfixer(IL_HMSS, "מְת") ;  Préfixer(IL_HMSP, "מְת") ; Préfixer(IL_HMFS, "מְת") ; Préfixer(IL_HMFP, "מְת") ;
                                for (char c : ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("racine").toCharArray()) CopierCaractèRacine(String.valueOf(c)) ;
                                Suffixer(IL_HMSP,  "ִים") ; Suffixer(IL_HMFS,  "ִת") ; Suffixer(IL_HMFP,  "וֹת") ;
                        } // switch (Conjugueur.M.ConjugHebM.T.i2T(contrôleur.model.getTemps()))
                        // INTENSIVE
                    }
                           tab.add(new LigneConj("m. s. : ")) ;
                for (char c: ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildText("verbe").toCharArray()) {
                if (Consonne(c)) nbcons++ ;
                if ((nbcons == 1) && (Chuintante(c))) ;
                }                                                                       // pour ts les caractères
                AjouterPersonnes () ;
                Préfixer(IL_HMSS, "מְ") ; Préfixer(ETI_HMFS, "מְ") ;
                for (char c: ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("racine").toCharArray()) {
                CopierCaractèRacine(String.valueOf(c)) ;
                }                                                    // pour tous les caractères
                break ;                                             // Temps Présent
                }          */
                // switch (Conjugueur.M.ConjugHebM.T.i2T(contrôleur.model.getTemps()))
                // switch (selectedIndVoix)
//             ColumnAjust(1) ;
        } catch (BadLocationException ble) {}                                   
    }                                                                           // updateTextPaneConj
    /**
     * Fonction : Ajoute les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur
     * Algorithme
     * 1.1 Mettre le 1er libellé en place
     * 1.2 Insèrer une 1ère ligne ds le tableau de conjugaison "tab" en appelant AjouterPers et son libellé ds la colonne "personne"
     * 1.3 mettre ds la colonne "déclinaison" de cette 1ère ligne le libellé du 1er pronom personnel pour l'hébreu moderne
     * 1.4 ajouter ds la colonne "déclinaison" de cette 1ère ligne une barre oblique "/"
     * 1.5  ajouter ds la colonne "déclinaison" de cette 1ère ligne  le libellé du 2è pronom personnel pour l'hébreu moderne
     * 1.6 ajouter ds la colonne "déclinaison" de cette 1ère ligne une 2è barre oblique "/"
     * 1.7 ajouter ds la colonne "déclinaison" de cette 1ère ligne  le libellé du 3è pronom personnel pour l'hébreu moderne
     * 2 Même chose pour la 2è ligne
     * 3 Même chose pour la 3è ligne
     * 4 Même chose pour la 4è ligne
    */
    private void AjouterPersonnes ()  throws BadLocationException {
        AjouterPers(IL_HMSS) ; CopierPPersonne (IL_HMSS, HMPP1MS) ; BarrOblique(IL_HMSS) ; CopierPPersonne (IL_HMSS, HMPP2MS) ; BarrOblique(IL_HMSS) ; CopierPPersonne (IL_HMSS, HMPP3MS) ;
        AjouterPers(IL_HMSP) ; CopierPPersonne (IL_HMSP, HMPP1MP); BarrOblique(IL_HMSP) ; CopierPPersonne (IL_HMSP, HMPP2MP) ; BarrOblique(IL_HMSP) ;  CopierPPersonne (IL_HMSP, " הֵם") ; 
        AjouterPers(IL_HMFS) ; CopierPPersonne (IL_HMFS, "הִיא/אַתְ/אֲנִי") ;
        AjouterPers(IL_HMFP) ; CopierPPersonne (IL_HMFP, "אְַנַֽחְנוּ ") ; BarrOblique(IL_HMFP) ; CopierPPersonne (IL_HMFP, "אַתֶן ")  ;BarrOblique(IL_HMFP) ;  CopierPPersonne (IL_HMFP, " הֵן") ;
        ColumnAjust(0) ;
    }                                                                            // AjouterPersonnes

    /**
     * Fonction : Renvoie True si le caractère passé en paramètre est une consonne
    */
    private static boolean Consonne (char c) {
      return ((c >= 'א') && (c <= 'ת')) || (c == 'שׁ')|| (c == 'שׂ') ;
    }                                                                            // Consonne
    private boolean ConsonneFinale (char c) {
      return (c == 'ך') || (c == 'ם') || (c == 'ן') || (c == 'ף') || (c == 'ץ') ;
    }                                                                            // Consonne    
    /**
     * Fonction : Hifil
     * Algorithme : 
     * 1 S'aiguiller selon le temps
     * 1.1 Temps INFINITIF
     * 1.1.1 Ajouter une ligne ds tableau ConjTablemodele et préfixer
     */
    private void Hifil ()  throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugHebM:Hifil, ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(\"infinitif\") = "+ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")) ;
        switch (Conjugueur.M.ConjugHebM.T.i2T(contrôleur.model.getTemps())) {
            case Infinitif -> {
                AjouterLigne("", Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ; Préfixer(0, "לה") ;
                nbcons = 0 ;
                for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                    if (Consonne(c)) {                          // le caractère courant est une consonne
                        nbcons++ ;
                        if (DEBUG) System.out.println("ConjugHebM:Hifil, c = " + c +", nbcons = " + nbcons) ;
                        if ((nbcons == 2) && (c != 'י'))  Copier(0, "י", DéclinaisonStyledDoc.stylesu) ;                                                        // ajouter "י"
                        Copier(0, String.valueOf(c), DéclinaisonStyledDoc.stylera)  ;                                    // copier le caractère de la racine
                    }                                           // le caractère courant était une consonne
                }                                                                       // pour ts les caractères
                AjouterTranslittération(0, hebmToLatinTrans) ;
            }
            case Passé -> {
                for (HMP personne : HMP.values()) {                        // pour ttes les personnes
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()) {                           // verbe régulier
                        inbLignes = AjouterLigne(personne.ip(), personne.lp(), FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                        if (HÉ == new StringBuffer(Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).toString().toCharArray()[2]) {                           // verbe en hé
                            inbLignes = 0 ;
                        }                                               // verbe en hé
                        else {                                          // pas verbe en hé
                    AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                        }                                                   // pas verbe en hé
                        }                                                   // verbe régulier
                }                                           // ttes les personnes
            }                                           // Passé
            case Présent -> {                                                   // Présent
                for (HMP personne : HMP.values()) {                             // pour ttes les personnes
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()){                 // verbe régulier
                        inbLignes = AjouterLigne(personne.ip(), personne.lp(), FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                        Préfixer(inbLignes, personne.php()) ; 
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ;
                                if ((nbcons == 2) && (c != 'י'))  Copier(inbLignes, "י", DéclinaisonStyledDoc.stylesu) ; 
                                CopierCaractèRacine(inbLignes, String.valueOf(c))   ;                                     // copier le caractère de la racine
                            }                                           // le caractère courant était une consonne
                        }                                                                       // pour ts les caractères
                        Suffixer (inbLignes, personne.shipr()) ;
        //                            Suffixer(IL_HMFS, "ֶת") ;   Suffixer(IL_HMSP, "ִים") ; Suffixer(IL_HMFP, "וֹת") ;
                    }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                }                                           // ttes les personnes
            }
                                                                   // Présent
        }                                                                       // switch (Conjugueur.Modèle. ConjugHebMModèle.Temps)
    }                                                                           // Hifil

    private void Hitpael () {
    }
    private void Hufal () {
    }
   
    
    private void Nifal () throws BadLocationException {
                        AjouterPersonnes () ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) { nbcons++ ;
                            if ((nbcons == 1) && (c == ALEPH)) ;
                            else CopierCaractèRacine (String.valueOf(c)) ;
                            if (nbcons == 2)  {
                                CopierSuffixe("י") ; nbcons++ ;
                            }
                            }                                               // consonne
                            else CopierCaractèRacine (String.valueOf(c)) ;
                        } // pour tous les caractères
    }
    
    /**
     * Fonction : Paal
     * Algorithme : 
     * 1 S'aiguiller selon le temps
     * 1.1 Temps Passé
     * 1.1.1 Pour toutes les personnes faire
     * 1.1.1.1 Si le verbe est régulier alors
     * 1.1.1.1.1  Créer une ligne pour chaque déclinaison en appelant AjouterLigne
     * 1.1.1.1.2 y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     * 1.2 Temps Présent
     * 1.2.1 Pour toutes les personnes faire
     * 1.2.1.1 Si le verbe est régulier alors
     * 1.2.1.1.1 Créer une ligne pour chaque déclinaison en appelant AjouterLigne
     * 1.2.1.1.2 Y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     * 1.2.1.2 S'il n'y a pas de présent particuler alors
     * 1.2.1.2.1 Pour ts les caractères de l'infinitif faire
     * 1.2.1.2.1.1 Si c'est une consonne alors
     * 1.2.1.2.1.1.1 Incrémenter compteur de consonnes
     * 1.2.1.2.1.1.2 Insérer un "וֹ" en 2è position s'il n'est pas déjà présent
     * 1.2.1.2.1.1.3 Copier le caractère en appelant "CopierCaractèRacine"
     * 1.2.1.2.1.2 Si c'est une voyelle alors
     */
    public void Paal () throws BadLocationException {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableHebM:Paal, Verbe = " + Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif") + ", Schème = " + Conjugueur.M.ConjugHebM.HMS.i2S(contrôleur.model.getSchème()).toString()  + ", Temps = " + Conjugueur.M.ConjugHebM.HMT.i2T(contrôleur.model.getTemps()).toString()) ;     }
        JFrameConjugHebib.jLabelVoix.setText("Voix Active"); JFrameConjugHebib.JLabelForme.setText("Forme simple");
        switch (Conjugueur.M.ConjugHebM.HMT.i2T(contrôleur.model.getTemps())) {                                             // temps
            case Impératif -> {
                nbcons = 0 ;
                        inbLignes = AjouterLigne(ConjTableHebM.HMP.HM2MS.ip(), ConjTableHebM.HMP.HM2MS.lp(), Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ; 
                                if ((nbcons == 3) && (c != 'ו')) {
                                     Copier(0, "וֹ", DéclinaisonStyledDoc.stylesu) ;                                                        // ajouter "וֹ"
                               }
                                CopierCaractèRacine(inbLignes, String.valueOf(c)) ;
                            }                                           // le caractère courant était une consonne
                        }                                                                       // pour ts les caractères
                
            }                                                                   // Impératif
            case Futur -> {
                for (HMP personne : HMP.values()) {                             // pour ttes les personnes
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()) {                           // verbe régulier
                        inbLignes = AjouterLigne(personne.ip(), personne.lp(), Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                        Préfixer(inbLignes, personne.ppafu()) ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ; 
                                CopierCaractèRacine(inbLignes, String.valueOf(c)) ;
                            }                                           // le caractère courant était une consonne
                        }                                                                       // pour ts les caractères
                        Suffixer (inbLignes, personne.spafu()) ;                            
                    }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                }                                           // ttes les personnes
            }                                                                   // futur
            case Infinitif -> {
                nbcons = 0 ;
                if (déclinaisonChildren.isEmpty()){             // verbe régulier
                    if (HÉ == new StringBuffer(Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).toString().toCharArray()[2]) {                           // verbe de type Lamed- hé
                        AjouterLigne("", Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ; Préfixer(0, "ל") ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ; 
                                if ((nbcons == 3) && (c != 'ו')) {
                                    Copier(0, "וֹ", DéclinaisonStyledDoc.stylesu) ;                                                        // ajouter "וֹ"
                                    Copier(0, "ת", DéclinaisonStyledDoc.stylesu) ;                                                        // remplacer le 'ה' par "ת"
                                }
                                else Copier(0, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;                                     // copier le caractère de la racine
                            }                                           // le caractère courant était une consonne
                        }                                                                       // pour ts les caractères
                    }                                               // verbe en hé
                    else if (YUD == new StringBuffer(Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).toString().toCharArray()[0]){                                       // verbe régulier 
                        AjouterLigne("", Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ; Préfixer(0, "ל") ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ; 
                                if (nbcons != 1) {
                                    Copier(0, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                                    if (nbcons == 3) {
                                        Copier(0, "ת", DéclinaisonStyledDoc.stylesu) ;                                                        // remplacer le 'ה' par "ת"                                    
                                    }
                                }
                            }                                           // le caractère courant était une consonne
                        }                                                                       // pour ts les caractères
                        
                    }
                    else {                                          // verbe régulier 
                        AjouterLigne("", Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ; Préfixer(0, "ל") ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
//                                if (DEBUG) System.out.println("ConjugHebM:Paal, c = " + c +", nbcons = " + nbcons) ;
                                nbcons++ ;                                         
                                if ((nbcons == 3) && (c != 'ו')) Copier (0, "וֹ", DéclinaisonStyledDoc.stylesu) ;
                                Copier(0, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                           // le caractère courant était une consonne
                        }                                                                       // pour ts les caractères
                    }                                                    // verbe régulier régulier
                }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
            }
            case Passé -> {
                for (HMP personne : HMP.values()) {                        // pour ttes les personnes
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()) {                           // verbe régulier
                        if (HÉ == new StringBuffer(Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).toString().toCharArray()[2]) {                           // verbe en hé
                            inbLignes = 0 ;
                         AjouterPers(IL_HMSS) ; CopierPPersonne (IL_HMSS, HMPP2MS) ;
                         AjouterPers(IL_HMFS) ; CopierPPersonne (IL_HMFS, HMPP2FS) ;
                        for (char c: Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                  // consonne
                                nbcons++ ;
                                if (nbcons == 3) {              // remplacer le 'ה' par "י"
                                }
                                else {
                                    Copier(IL_HMFS, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                                }
                            }                                   // consonne
                            else {                              // voyelle
                                Copier(IL_HMFS, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                               // voyelle
                        }                                           // pour ts les caractères
                        CopierSuffixe (IL_HMFS, "ְת") ;
                        }                                               // verbe en hé
                        else {                                          // pas verbe en hé
                            inbLignes = AjouterLigne(personne.ip(), personne.lp(), Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                            Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp) ;  Espace (inbLignes) ;
                            for (char c: Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                                if (Consonne(c)) {                          // consonne
                                    if (nbcons == 1) CopierCaractère (inbLignes, String.valueOf(QĀMEṢ)) ;
                                    if (nbcons == 2) CopierCaractère (inbLignes, String.valueOf(PATAH)) ;
                                    nbcons++ ;
                                    CopierCaractèRacine(inbLignes, String.valueOf(c)) ;
                                }                                           // consonne
                                else {                                          // voyelle
                                    if (nbcons == 1) Conjugueur.M.ConjugHebM.conjTableM.tab.get(ETIPMS).déclinaison.insertString(Conjugueur.M.ConjugHebM.conjTableM.tab.get(ETIPMS).déclinaison.getLength(), "ָ", DéclinaisonStyledDoc.stylera) ;
                                    else if (nbcons == 2) Conjugueur.M.ConjugHebM.conjTableM.tab.get(ETIPMS).déclinaison.insertString(Conjugueur.M.ConjugHebM.conjTableM.tab.get(ETIPMS).déclinaison.getLength(), "\u05b5", DéclinaisonStyledDoc.stylera) ;
                                }                                               // voyelle
                        }                                                                       // pour ts les caractères
                            Suffixer (inbLignes, personne.spip(Conjugueur.M.ConjugHebM.T.Passé.it(), Conjugueur.M.ConjugHebM.S.Paal.is())) ;
                        }                                                   // verbe régulier
                    } // pas verbe en hé
                    else {
                    // verbe irrégulier
                        CopierIrrégularités (personne, "passé", "simple", "active") ;
                    } // verbe irrégulier
                    AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                }                                           // ttes les personnes
            }                                           // Passé
            case Présent -> {                                                   // Présent
                if (déclinaisonChildren.isEmpty()){                         // verbe régulier
                    if (HÉ == new StringBuffer(Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).toString().toCharArray()[2]) {  // verbe   Lamed-hé                       // verbe de type Lamed- hé
                        inbLignes = AjouterLigne(HMP.HM1MS.lp+HMP.HM2MS.lp+HMP.HM3MS.lp, Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HMP.HM1MS.lhp+" "+HMP.HM2MS.lhp+" "+HMP.HM3MS.lhp, DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                        nbcons = 0 ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ;
                                if ((nbcons == 2) && (c != 'ו')) Copier(inbLignes, "וֹ", DéclinaisonStyledDoc.stylesu) ;
                                if (nbcons < 3) Copier(inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                           // le caractère courant était une consonne
                        }                                                    // pour ts les caractères
                        Suffixer (inbLignes, HMP.HM1MS.spipLH(Conjugueur.M.ConjugHebM.T.Présent.it(), Conjugueur.M.ConjugHebM.S.Paal.is()));
                        AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                        inbLignes = AjouterLigne(HMP.HM1FS.lp+HMP.HM2FS.lp+HMP.HM3FS.lp, Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HMP.HM1FS.lhp+" "+HMP.HM2FS.lhp+" "+HMP.HM3FS.lhp, DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                        nbcons = 0 ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ;
                                if ((nbcons == 2) && (c != 'ו')) Copier(inbLignes, "וֹ", DéclinaisonStyledDoc.stylesu) ;
                                if (nbcons < 3)Copier(inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                           // le caractère courant était une consonne
                        }                                                    // pour ts les caractères
                        Suffixer (inbLignes, HMP.HM1FS.spipLH(Conjugueur.M.ConjugHebM.T.Présent.it(), Conjugueur.M.ConjugHebM.S.Paal.is()));
                        AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                        inbLignes = AjouterLigne(HMP.HM1MP.lp+HMP.HM2MP.lp+HMP.HM3MP.lp, Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HMP.HM1MP.lhp+" "+HMP.HM2MP.lhp+" "+HMP.HM3MP.lhp, DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                        nbcons = 0 ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ;
                                if ((nbcons == 2) && (c != 'ו')) Copier(inbLignes, "וֹ", DéclinaisonStyledDoc.stylesu) ;
                                if (nbcons < 3) Copier(inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                           // le caractère courant était une consonne
                        }                                                    // pour ts les caractères
                        Suffixer (inbLignes, HMP.HM1MP.spipLH(Conjugueur.M.ConjugHebM.T.Présent.it(), Conjugueur.M.ConjugHebM.S.Paal.is()));
                        AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                        inbLignes = AjouterLigne(HMP.HM1FP.lp+HMP.HM2FP.lp+HMP.HM3FP.lp, Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HMP.HM1FP.lhp+" "+HMP.HM2FP.lhp+" "+HMP.HM3FP.lhp, DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                        nbcons = 0 ;
                        for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ;
                                if ((nbcons == 2) && (c != 'ו')) Copier(inbLignes, "וֹ", DéclinaisonStyledDoc.stylesu) ;
                                if (nbcons < 3) Copier(inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                           // le caractère courant était une consonne
                        }                                                    // pour ts les caractères
                        Suffixer (inbLignes, HMP.HM1FP.spipLH(Conjugueur.M.ConjugHebM.T.Présent.it(), Conjugueur.M.ConjugHebM.S.Paal.is()));
                        AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                    }                                                           // // verbe   Lamed-hé
                    else {
                        for (HMP personne : HMP.values()) {                             // pour ttes les personnes
                            inbLignes = AjouterLigne(personne.ip(), personne.lp(), Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                            Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                            if (Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("Présent") == null) {   // pas de Présent particulier
                                for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                                    if (Consonne(c)) {                          // le caractère courant est une consonne
                                        nbcons++ ;
                                        if ((nbcons == 2) && (c != 'ו')) Copier(inbLignes, "וֹ", DéclinaisonStyledDoc.stylesu) ;
                                        if (nbcons == NbTotalConsonnes()) {
                                            CopierCaractèRacine(inbLignes, String.valueOf(v)) ;
                                            CopierCaractèRacine(inbLignes, String.valueOf(c)) ;
                                            Suffixer (inbLignes, personne.spip(Conjugueur.M.ConjugHebM.T.Présent.it(), Conjugueur.M.ConjugHebM.S.Paal.is())) ;                            
                                        } else CopierCaractèRacine(inbLignes, String.valueOf(c)) ;
                                    }                                           // le caractère courant était une consonne
                                    else {                                      // le caractère courant est une voyelle
                                        if (nbcons == NbTotalConsonnes() -1) v = c ;
                                    }                                                 // voyelles
                                }                                                    // pour ts les caractères
                            }                                               // pas de Présent particulier
                            else {                                          // Présent particulier
                                switch (Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("Présent"))   {
                                    case "54" : break ;                                      
                                }
                            }                                               // Présent particulier
                            AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                        }                                                   // verbe régulier
                    }                                           // ttes les personnes
                }
                    else {                                              // verbe irrégulier
                        if (déclinaisonChildren.get(0).getText().isEmpty()) {
                            switch (déclinaisonChildren.get(0).getAttribute("temps").getValue()) {
                                case "présent" :
//                                    ConjugHebM.jComboBoxTemps.setSelectedIndex(PASSÉ);
                                    break ;
                            }
                        }
                        else                 for (HMP personne : HMP.values())                              // pour ttes les personnes
                            CopierIrrégularités (personne, "présent", "simple", "active") ;
                    }                                                   // verbe irrégulier
            }
                                                                   // Présent
        }                                                                       // switch (Conjugueur.Modèle. ConjugHebMModèle.Temps)
    }                                                                           // Paal

    /**
     * Fonction : Ajoute une ligne d'indice inbLignes
    */
    private void AjouterLigne (String personne, String déclinaison) throws BadLocationException {
       if (DEBUG) System.out.println ("ConjTableHebM::AjouterLigne, personne = "+ personne+", déclinaison = "+ déclinaison) ;
        switch (personne) {
            case "HM1MS" -> {
                inbLignes = AjouterLigne(ConjTableHebM.HMP.HM1MS.ip(), ConjTableHebM.HMP.HM1MS.lp(), FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                Copier(inbLignes, déclinaison, DéclinaisonStyledDoc.stylesu) ;
                AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
            }
            case "HM1FS" -> {
                inbLignes = AjouterLigne(ConjTableHebM.HMP.HM1FS.ip(), ConjTableHebM.HMP.HM1FS.lp(), FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                Copier(inbLignes, déclinaison, DéclinaisonStyledDoc.stylesu) ;
                AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
            }
            case "HM2FS" -> {
                inbLignes = AjouterLigne(ConjTableHebM.HMP.HM2FS.ip(), ConjTableHebM.HMP.HM2FS.lp(), FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                Copier(inbLignes, déclinaison, DéclinaisonStyledDoc.stylesu) ;
                AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
            }
            case "1è. m. s." -> { CopierPPersonne ("אֲנִי") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "1è. p. s." -> { CopierPPersonne ("אֲנִי") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "2è. m. s." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "2è. f. s." -> { CopierPPersonne ("אַתְ") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "f. s." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "1è. m. p." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "1è. p. p." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;          }
            case "1è. f. p." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;         }
            case "2è. m. p." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;       }
            case "2è. f. p." -> { CopierPPersonne ("אַתֶן") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;         }
            case "3è. m. s." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "3è. f. s." -> { CopierPPersonne ("הִיא") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "3è. m. p." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu) ;            }
            case "3è. f. p." -> { CopierPPersonne ("אַתָה") ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu);            }
            case "3è. p. p." -> { CopierPPersonne ("הֵם") ; BarrOblique(inbLignes) ; CopierPPersonne ("הֵן") ;Espace (inbLignes) ; Copier(déclinaison, DéclinaisonStyledDoc.stylesu);            }
        }                                                               // switch
        inbLignes++ ; 
    }                                                                           // AjouterLigne
    
    /**
     * Fonction : Piel INTENSIVE + ACTIVE
     * Algorithme : 
     */
    private void Piel () throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugHebM:Piel, Conjugueur.M.ConjugHebM.cbVerbeM.get(Conjugueur.Modèle. Données.getIndVerbe()).getAttributeValue(\"infinitif\") = "+Conjugueur.M.ConjugHebM.cbVerbeM.get(Conjugueur.Modèle. Données.getIndVerbe()).getAttributeValue("infinitif")) ;
        switch (Conjugueur.M.ConjugHebM.T.i2T(contrôleur.model.getTemps())) {                             // temps
            case Présent -> {
                for (HMP personne : HMP.values()) {                        // pour ttes les personnes
                    nbcons = 0 ;
                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT) ;
                    CopierPPersonne (inbLignes, personne.lhp()) ;
                    Préfixer(inbLignes, personne.ppp()) ; 
                    for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                        CopierCaractèRacine(inbLignes, String.valueOf(c)) ;
                    }                                                    // pour tous les caractères
                    Suffixer(inbLignes, personne.spip(Conjugueur.M.ConjugHebM.T.Présent.it(), Conjugueur.M.ConjugHebM.S.Piel.is())) ;   
                    AjouterTranslittération(inbLignes, hebmToLatinTrans) ;
                }                                           // ttes les personnes
            }                                                                   // Présent
            case Infinitif -> {
                if (déclinaisonChildren.isEmpty()) {                            // verbe régulier
                    Conjugueur.M.ConjugHebM.conjTableM.tab.add(new LigneConj("", Conjugueur.V.JFrameConjugHebM.tFonts.get(contrôleur.model.getÉcriture()), StyleConstants.ALIGN_RIGHT)) ; Préfixer(0, "ל") ;
                    for (char c : Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
                        if (Consonne(c)) {                          // le caractère courant est une consonne
                            Copier(0, String.valueOf(c), DéclinaisonStyledDoc.stylera)  ;                                     // copier le caractère de la racine
                        }                                           // le caractère courant était une consonne
                    }                                                                       // pour ts les caractères
                }                                                                // verbe régulier
            }
                                                                   //  Infinitif :
        }                                                                       // switch (Conjugueur.Modèle. ConjugHebMModèle.Temps)
    }                                                                           // Piel
    // Calculates the width based on the widest cell renderer for the given
    // column.
    private int getColumnDataWidth(int column) {
        int preferredWidth = 0;
/*        for (int row = 0 ; row < JFrameConjug.jTabConj.getRowCount(); row++)
            preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));*/
        return preferredWidth;
    }
    
        // Gets the preferred width for the specified cell
    private int getCellDataWidth(int row, int column) {
        // Invoke the renderer for the cell to calculate the preferred width
/*        TableCellRenderer cellRenderer = JFrameConjug.jTabConj.getCellRenderer(row, column);
        Object value = JFrameConjug.jTabConj.getValueAt(row, column);
        Component c = cellRenderer.getTableCellRendererComponent(JFrameConjug.jTabConj, value, false, false, row, column);
        return c.getPreferredSize().width + JFrameConjug.jTabConj.getIntercellSpacing().width ;*/
return 0 ;
    }

        // Calculates the given column's width based on header name
    private int getColumnHeaderWidth(int column) {

/*        TableColumn tableColumn = JFrameConjug.jTabConj.getColumnModel().getColumn(column);
        Object value = tableColumn.getHeaderValue();
        TableCellRenderer renderer = JFrameConjug.jTabConj.getColumnModel().getColumn(0).getCellRenderer();

        if (renderer == null)
            renderer = JFrameConjug.jTabConj.getTableHeader().getDefaultRenderer();

        Component c = renderer.getTableCellRendererComponent(JFrameConjug.jTabConj, value, false, false, -1, column);
        return c.getPreferredSize().width; */
return 0 ;
    }
   
    private  void ColumnAjust (int column) {
        if (DEBUG) System.out.println("ConjTableHebM:ColumnAjust, column = " + column) ;
/*        TableColumn tableColumn = JFrameConjug.jTabConj.getColumnModel().getColumn(column);
        if (!tableColumn.getResizable()) return;
         int columnHeaderWidth = getColumnHeaderWidth(column);
        int columnDataWidth = getColumnDataWidth(column);
       JFrameConjug.jTabConj.getTableHeader().setResizingColumn(tableColumn);
        tableColumn.setWidth(Math.max(columnHeaderWidth, columnDataWidth) + SPACING);*/
    }
    /**
     * Fonction : Copie un caractère donné en paramètre pour ttes les personnes
     */
    private  void CopierCaractère (int ip, String caractère) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugHebM::CopierCaractère, ip = "+ ip+", caractère = "+caractère) ;
        Copier(ip, caractère, DéclinaisonStyledDoc.stylesu) ; 
    }    
    /**
     * Place la racine pour ttes les personnes ds la table "tab" 
    */
    private  void CopierCaractèRacine (String caractère) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugHebM::CopierCaractèRacine, caractère = "+caractère) ;
        Copier(IL_HMSS, caractère, DéclinaisonStyledDoc.stylera)  ;  // Copier(IL_HMSP, caractère, DéclinaisonStyledDoc.stylera) ; Copier(IL_HMFS, caractère, DéclinaisonStyledDoc.stylera) ; Copier(IL_HMFP, caractère, DéclinaisonStyledDoc.stylera) ; 
    }
    

    /**
     * Place la racine pour ttes les personnes ds la table "ConjugHebMM.conjTableM.tab" 
     */
    private  void CopierCaractèRacine (int ipersonne, String caracine) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugHebM::CopierCaractèRacine, ipersonne = "+ipersonne+", caracine = "+ caracine) ;
        Copier(ipersonne, caracine, DéclinaisonStyledDoc.stylera)  ;  
    }    
    private  void CopierIrrégularités (HMP personne, String temps, String forme, String voix) throws BadLocationException {
        if (DEBUG) System.out.println("ConjTableHebM::CopierIrrégularités, temps = "+ temps + ", forme = "+ forme + ", voix = "+ voix + ", personne = "+ personne) ;
        for (int i = 0 ; i < déclinaisonChildren.size(); i++) {                  // Pour ttes les déclinaisons
            if (   (déclinaisonChildren.get(i).getAttribute("temps").getValue().compareTo(temps) == 0)
                && (déclinaisonChildren.get(i).getAttribute("forme").getValue().compareTo(forme) == 0)
                && (déclinaisonChildren.get(i).getAttribute("voix").getValue().compareTo(voix) == 0)     
                && (déclinaisonChildren.get(i).getAttribute("personne").getValue().compareTo(personne.toString()) == 0)    ) 
                AjouterLigne (déclinaisonChildren.get(i).getAttribute("personne").getValue(), déclinaisonChildren.get(i).getText()) ;
        }                                                                       // Pour ttes les déclinaisons
    }                                                                           // CopierIrrégularités
    private  void CopierSuffixe (int ip, String caractères) throws BadLocationException {
        if (DEBUG) System.out.println("ConjTableHebM::CopierSuffixe, ip = " + ip+", caractères = "+caractères) ;
     /*RemplaConsonneFinale (ip) ;*/ Copier(ip, caractères, DéclinaisonStyledDoc.stylesu) ; 
   }
   private  void CopierSuffixe (String caractère) throws BadLocationException {
     Copier(IL_HMFS, caractère, DéclinaisonStyledDoc.stylesu) ; Copier(IL_HMSS, caractère, DéclinaisonStyledDoc.stylesu) ;
   }

   /**
    * Affiche le pronom personnel identifiée par l'indice "ip" 
    */
    private  void CopierPPersonne (int ip, String caractères) throws BadLocationException {
        if (DEBUG) System.out.println("ConjTableHebM::CopierPPersonne, ip = "+ ip +", caractères = "+caractères) ;
       Copier (ip, caractères, DéclinaisonStyledDoc.stylepp) ; Espace (ip) ;
    }
    
   /**
    * Affiche le pronom personnel identifiée par l'indice "personne" 
    */
    private  void CopierPPersonne (String caractères) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugHebM::CopierPPersonne, inbLignes= "+inbLignes) ;
       Copier (inbLignes, caractères, DéclinaisonStyledDoc.stylepp) ; Espace (inbLignes) ;
    }


    
    /**
     * Fonction : Calcule le nb total de consonnes d'un mot
     */
    private  int NbTotalConsonnes () {
        int nbtotcons = 0 ;
        for (char c: Conjugueur.M.ConjugHebM.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").toCharArray()) {
            if (Consonne(c)) nbtotcons++ ;
        }                                                                       // pour ts les caractères
        return nbtotcons ;
    }                                                                           // NbTotalConsonnes    

    
    private void Pual () {
    //    jLabelSchème.setText("Pual");
        
    }
    public enum HMP {
        HM1MS ((short)0, " 1è. m. s. :", "אני", new String []{"מ",""}, new String []{"מ",""}, new String []{"מְ", ""}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, {/*Présent*/{/*Paal*/"", "","","ֶה"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "תִּי"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}), 
        HM1FS ((short)1, " 1è. f. s. :", "אני", new String []{"מ",""}, new String []{"מ",""}, new String []{"מְ", ""}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "", "", "ַה"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "תִּי"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}), 
        HM2MS ((short)2, " 2è. m. s. :", "‫אתה‬", new String []{"מ",""}, new String []{"ת",""}, new String []{"מְ", ""}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "","","ֶה"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "תָּ"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}), 
        HM2FS ((short)3, " 2è. f. s. :", "‫את‬", new String []{"מ","ה"}, new String []{"ת",""}, new String []{"מְ", "ֶת"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "", "", "ַה"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "תְּ"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}} ),
        HM3MS ((short)4, " 3è. m. s. :", "הוּא", new String []{"מ",""}, new String []{"י",""}, new String []{"מְ", ""}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "","","ֶה"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}),
        HM3FS ((short)5, " 3è. f. s. :", "היא", new String []{"מ","ה"}, new String []{"ת",""}, new String []{"מְ", "ֶת"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "", "", "ַה"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "ה"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}} ),
        HM1MP  ((short)6, " 1è. m. p. :", "אנחנו", new String []{"מ","ימ"}, new String []{"נ",""}, new String []{"מְ", "ִום"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "ִום", "", "ִום"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "נוּ"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}), 
        HM1FP  ((short)7, " 1è. f. p. :", "אנחנו", new String []{"מ","ימ"}, new String []{"נ",""}, new String []{"מְ", "ִום"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "ִום", "", "וֹת"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "נוּ"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}), 
        HM2MP ((short)8, " 2è. m. p. :", "‫אתם‬", new String []{"מ","ימ"}, new String []{"ת",""}, new String []{"מְ", "ִום"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "ִום", "", "ִום"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "תֶּם"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}),
        HM2FP ((short)9, " 2è f. p. :", "‫אתן‬", new String []{"מ","ותּ"}, new String []{"ת",""}, new String []{"מְ", "וֹת"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "וֹת", "", "וֹת"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "תֶּן"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}),
        HM3MP ((short)10, " 3è. m. p. :", "‫הם‬", new String []{"מ","ימ"}, new String []{"י",""}, new String []{"מְ", "ִום"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "ִום", "", "ִום"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "וּ"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}}),
        HM3FP ((short)11, " 3è. f. p. :", "‫הן‬", new String []{"מ","ותּ"}, new String []{"י",""}, new String []{"מְ", "וֹת"}, new String [][][]{{/*Infinitif*/{/*Paal*/"", ""}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}},{/*Présent*/{/*Paal*/"", "וֹת", "", "וֹת"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}, { /*Passé*/ {/*Paal*/"", "וּ"}, {/*Piel*/"",""}, {/*Hifil*/"", ""}, {/*Hitpael*/"", ""}}});
                                                                         // Suffixes Paal passé      
      
        private enum SufPaalPrésVHé {                                              // Suffixes Paal Présent verbe en hé
             HMPAPRFP("וֹת") ;
            private final String sufpapr ;                                          // déclinaison au présent
            SufPaalPrésVHé (String prpapr) { this.sufpapr = prpapr ; }
            public String spapr() { return sufpapr ; }
        }                                                                           // Suffixes Paal Présent      
        // constantes définissant les préfixes du Piel Présent
        private enum SufPielPrés {
            HMPIPRMS(""), HMPIPRFS("ֶת"), HMPIPRMP("ִום"), HMPIPRFP("וֹת") ;
            private final String sufpipr ;                                          // déclinaison au présent
            SufPielPrés (String prpipr) { this.sufpipr = prpipr ; }
            public String spipr() { return sufpipr ; }
        }                                                                           // Préfixes Piel Présent    // constantes définissant les personnes   
        //Instance variable
        private final int ip ;                                                  // indice de personne 0, 1, 2,...
        public final String lp ;                                               // libellé de personne "1è. s.", "2è. m. s."
        public final String lhp ;                                              // PP hébreu
        private final String[] HifilPrés, PaalFut, PielPrés ;
        private final String[][][] PairePS ;                                     // préfixe et suffixe par schème

        //Constructor to initialize the instance variable
        HMP(int ipers, String lp, String libhpers, String [] HifilPrés, String [] PaalFut, String [] PielPrés, String [][][] PairePS) {
            this.ip     = ipers ; this.lp     = lp ; this.lhp    = libhpers ;  this.PairePS = PairePS ;
            this.HifilPrés = HifilPrés ; this.PaalFut = PaalFut ; this.PielPrés = PielPrés ;
        }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lhp() { return lhp; }
        public String ppafu() { return PaalFut[PRÉFIXE] ; }
        public String php() { return HifilPrés[PRÉFIXE] ; }
        public String ppp() { return PielPrés [PRÉFIXE] ; }
        public String shipr() { return HifilPrés[SUFFIXE] ; }
        public String spafu() { return PaalFut[SUFFIXE] ; }
        public String spip(short it, short is) { return PairePS [it][is][SUFFIXE] ; }
        public String spipLH(short it, short is) { return PairePS [it][is][SUFVLH] ; }
    }                                                                           //HMP

    private static char v  ;
    public final static char HÉ = 'ה', YUD = 'י', PATAH = 'ַ' ;
    private final static char QĀMEṢ = 'ָ' ;
    public static final String NOMPOLICEFRANÇAIS = "Times New Roman", NOMPOLICEHÉBREU = "Dana Yad AlefAlefAlef Normal", HEBM_TO_LATIN = "Hebrew-Latin" ;
    public static  final Transliterator hebmToLatinTrans = Transliterator.getInstance(HEBM_TO_LATIN);
    private static final short TAILLEPOLICE = 26, TAILLEPOLICEFRANÇAIS = 19 ;

    public static final Font FRANÇAIS = new Font (NOMPOLICEFRANÇAIS, Font.PLAIN, TAILLEPOLICEFRANÇAIS) ;

    public final static String HMPP1MS = "" ;                                      // hébreu moderne pronom personnel 1ère personne masc. sing.
    public final static String HMPP2MS = "" ;
    public final static String HMPP2FS = "" ;
    public final static String HMPP3MS = "" ;
    public final static String HMPP1MP = "" ;
    public final static String HMPP2MP = "" ;
    private final static short PRÉFIXE = 0 ;
    private final static short SUFFIXE = 1 ;
    private final static short PRÉVLH = 2 ;                                     // préfixe verbe Lamed-hé
    private final static short SUFVLH = 3 ;                                     // suffixe verbe Lamed-hé
    private final static short IL_HMSS = 0 ;                                    // hébreu moderne  masc. sing.
    private final static short IL_HMSP = 1 ;                                    // masc. plur.
    private final static short IL_HMFS = 2 ;                                    //   fém. sing.
    private final static short IL_HMFP = 3 ;                                    //  fém. plur.
    private final static short ETIPMS = 0 ;                                        // participe masc. sing.
    
    private final static char ALEPH = 'א' ;
    private Conjugueur.C.Conjug contrôleur ;
    
    private static final boolean DEBUG = false;    
}
