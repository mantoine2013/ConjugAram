/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.*;
import java.util.StringTokenizer;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class ConjTableLat extends ConjTable  {

    /**
     * Fction appelante : Conjugueur.V.JFrameConjugLat::JFrameConjugLat
     * Algorithme : 
     * 1. Remettre le tableau de conjugaison à zéro en appelant vider
     * 2. S'aiguiller selon le mode
     * 2.1 mode Indicatif ou Subjonctif
     * 2.2 S'aiguiller selon le temps
     * 2.2.1 Temps Passé, Parfait, FutureSimple ou Imparfait
     * 2.2.2 Pour ttes les personnes faire
     * 2.2.2.1 Si le verbe est régulier alors
     */
    @Override
    public void MAJConj(Conjugueur.C.Conjug contrôleur) {
        if (DEBUG) { System.out.println ("Conjugueur.M.ConjTableLat::MAJConj, Verbe = " + ConjugLat.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugLat.ATTRIBUT) + ", Mode = " + Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString() + ", Temps = " + Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps()).toString() + ", Voix = " + Conjugueur.M.ConjugAnFr.V.i2V(contrôleur.model.getVoix()).toString()) ;   }
        try {
            vider ();
            switch (Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode())) {                                          // mode
                case Indicatif, Subjonctif -> {                                                  // Indicatif, Subjonctif
                    déclinaisonChildren = ConjugLat.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
                    switch (Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps())) {
                        case Imparfait -> {                                       // temps présent
                            itype = DéterminerConjugaison(contrôleur) ; 
                            for (LAP personne : LAP.values()) {
                                if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                }                                                       // verbe régulier
                                else {                                                // verbe irrégulier
                                    if (!CopierIrrégularités (personne, contrôleur)){
                                     inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                    }
                                }                                                       // verbe irrégulier
                            }                                           // ttes les personnes
                        }                                                           // temps présent
                        case Parfait -> {                                       // temps présent
                            itype = DéterminerConjugaison(contrôleur) ; 
                            for (LAP personne : LAP.values()) {
                                if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerRégulièrementParfait (personne, contrôleur) ;    
                                }                                                       // verbe régulier
                                else {                                                // verbe irrégulier
                                    if (!CopierIrrégularités (personne, contrôleur)){
                                     inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerRégulièrementParfait (personne, contrôleur) ;    
                                    }
                                }                                                       // verbe irrégulier
                            }                                           // ttes les personnes
                        }                                                           // temps présent
                        case PlusQueParfait -> {                                       // temps PlusQueParfait
                            itype = DéterminerConjugaison(contrôleur) ; 
                            for (LAP personne : LAP.values()) {
                                if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                                }                                                       // verbe régulier
                                else {                                                // verbe irrégulier
                                    CopierIrrégularités (personne, contrôleur) ;
                                }                                                       // verbe irrégulier
                            }                                           // ttes les personnes
                        }                                                           // temps PlusQueParfait
                        case Présent, Futur -> {                                       // temps présent
                            for (LAP personne : LAP.values()) {
                                if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                                    itype = DéterminerConjugaison(contrôleur) ; 
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                }                                                       // verbe régulier
                                else {                                                // verbe irrégulier
                                    CopierIrrégularités (personne, contrôleur) ;
                                }                                                       // verbe irrégulier
                            }                                           // ttes les personnes
                        }                                                           // temps présent
                    }
                // temps
                // switch (contrôleur.model.getMode())
                }                                                               // Indicatif, Subjonctif
                case Impératif -> {                                                     // Impératif
                    StringTokenizer tokenizer = new StringTokenizer(ConjugLat.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugLat.ATTRIBUT), " ");
                    forme[LAF.InfinitifPrésent.ifo()] = tokenizer.nextToken() ;
                    if (DEBUG) { System.out.println ("Conjugueur.M.ConjTableLat::MAJConj, itype = " + itype );   }
                    inbLignes = this.AjouterLigne(LAP.LA2S.ip(), LAP.LA2S.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                    DéclinerRégulièrement (LAP.LA2S, contrôleur) ;    
                    inbLignes = this.AjouterLigne(LAP.LA2P.ip(), LAP.LA2P.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                    DéclinerRégulièrement (LAP.LA2P, contrôleur) ;    
                    // mode impératif
                }                                                               // Impératif
                case Participe -> {                                                     // Participe
                    StringTokenizer tokenizer = new StringTokenizer(ConjugLat.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugLat.ATTRIBUT), " ");
                    forme[LAF.InfinitifPrésent.ifo()] = tokenizer.nextToken() ;
                    if (DEBUG) { System.out.println ("Conjugueur.M.ConjTableLat::MAJConj, itype = " + itype );   }
                    switch (Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps())) {
                        case Présent -> {
                            inbLignes = this.AjouterLigne(LAP.LA2S.ip(), "", JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                            for (char c : (forme[LAF.InfinitifPrésent.ifo()]).substring(0, forme[LAF.InfinitifPrésent.ifo()].length()- 3).toCharArray()){
                                this.Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                                    // pour ts les caractères
                            if (!LAP.LA2S.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()].isEmpty())
                                this.Suffixer (inbLignes, LAP.LA2S.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()]) ;
                        }
                        case Parfait -> {
                            inbLignes = this.AjouterLigne(LAP.LA2S.ip(), LAP.LA2S.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                            switch (forme[LAF.InfinitifPrésent.ifo()].substring(forme[LAF.InfinitifPrésent.ifo()].length() - 3)) {
                                case "are":
                                    break;                                      // 1er groupe
                            }                                                   // groupe
                            break;                                                   
                        }                                                       // // temps parfait
                    } // temps
                }                                                               // Participe
                case Infinitif -> {                                             // Infinitif
                    switch (Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps())) {
                        case Présent -> {
                            inbLignes = AjouterLigne("", JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                            itype = DéterminerConjugaison(contrôleur) ; 
                            Copier(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(0,forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length()- 1), DéclinaisonStyledDoc.stylera);
                            if ('+' == forme[LAF.InfinitifPrésent.ifo()].charAt(0)) Copier(forme[LAF.InfinitifPrésent.ifo()].substring(1), DéclinaisonStyledDoc.stylera);
                        }
                        case Parfait -> {
                            inbLignes = AjouterLigne("", JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                            itype = DéterminerConjugaison(contrôleur) ; 
                            Copier(forme[LAF.Parfait.ifo()].substring(0,forme[LAF.Parfait.ifo()].length()- 1), DéclinaisonStyledDoc.stylera);
                            Suffixer (LAP.LA2S.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()]) ; 
                        }                                                       // // temps parfait
                    } // temps
                }                                                               // Infinitif
            }                                                                   // mode
        } catch (BadLocationException ble) {        }
    }                                                                           // updateTextPaneConj   

    /**
     * Fonction : Substitue les conjugaisons régulières par irrégularités trouvées ds le xml
     * Algorithme : 
     * 1. Pour ttes irrégularités trouvées ds le xml faire
     * 1.1 Si cette irrégularité correspond au mode et au temps choisi alors
     * 1.1.1 Pour ttes les conjugaisons régulières déjà ds "tabLigneConj" faire
     * 1.1.1.1 Si la personne régulière se trouve être en même temps irrégulière alors
     * 1.1.1.1.1 si les conjugaisons diffèrent faire
     * 1.1.1.1.1.1 Supprimer de "tabLigneConj" la déclinaison régulière
     * 1.1.1.1.1.2 Insertion de l'irrégularité
     * 1.1.1.2 sinon
     * 1.1.1.2.1 Ajouter l'irrégularité
    */
    private boolean CopierIrrégularités (LAP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {
        if (DEBUG) { System.out.println("ConjugLat::CopierIrrégularités, mode = "+ Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString() +", temps = "+ Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps()).toString() ) ; }
        trouvé = false ;
        for (int i = 0; i < déclinaisonChildren.size(); i++) {                  // Pour ttes les déclinaisons
            if (   (déclinaisonChildren.get(i).getAttribute("mode").getValue().compareTo(Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("temps").getValue().compareTo(Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("personne").getValue().compareTo(personne.lp()) == 0)) {
                inbLignes = this.AjouterLigne(LAP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).ip(), LAP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                this.Copier(inbLignes, LAP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lip(), DéclinaisonStyledDoc.stylepp); this.Espace(inbLignes);
                this.Copier(inbLignes, déclinaisonChildren.get(i).getText(), DéclinaisonStyledDoc.stylesu);
                trouvé = true ;
            } 
        }                                                                       // Pour ttes les déclinaisons
        return trouvé ;    
    }                                                                           // CopierIrrégularités
    /**
     * @param personne indice de groupe 1, 2 ou 3
     * Algorithme
     * 1. Copier le libellé du Pronom Personnel "1è. s.", "2è. s.", "3è. s.",...
     * 2. Si le préfixe existe, l'ajouter
     */
    private void DéclinerRégulièrement(LAP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {
        if (itype == null) { System.out.println("*** erreur interne Conjugueur.M.ConjTableLat::DéterminerConjugaison***"); System.exit(-1);}
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableLat::DéclinerRégulièrement, personne = " + personne.lp + ", Mode = " + Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString() + ", Temps = "+ Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps()).toString() + ", Voix = " + Conjugueur.M.ConjugLat.V.i2V(contrôleur.model.getVoix()).toString() + ", itype = " + itype + ", personne.suffixes ["+contrôleur.model.getMode()+"]["+contrôleur.model.getTemps()+"]["+contrôleur.model.getVoix()+"]["+itype.ic()+"] = "+personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()]) ; }
        CopierPersonne (personne.lip()) ;
        for (char c : (forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()]).substring(0, forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 1).toCharArray()) {
                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
        }                                                    // pour ts les caractères
//        if (itype == LAC.PremièreConjugaison) Copier (String.valueOf(forme[LAF.InfinitifPrésent.ifo()].charAt(1)), DéclinaisonStyledDoc.stylera) ;
//        else if (itype == LAC.QuatrièmeConjugaison) Copier ("e", DéclinaisonStyledDoc.stylera) ;
         if (!personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()].isEmpty())  this.Suffixer (personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()]) ;
    }                                                                          // DéclinerRégulièrement
    /**
     * Fonction : Conjugue au Parfait
     * @param personne indice de groupe 1, 2 ou 3
     * Algorithme
     * 1. Copier le libellé du Pronom Personnel
     * 2. Si le préfixe existe, l'ajouter
     */
    private void DéclinerRégulièrementParfait(LAP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {
        if (itype == null && DEBUG) { System.out.println("*** erreur interne Conjugueur.M.ConjTableLat::DéterminerConjugaison***"); System.exit(-1);}
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableLat::DéclinerRégulièrementParfait, personne = " + personne.lp + ", Mode = " + Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString() +", Temps = " + Conjugueur.M.ConjugLat.LAT.i2T(contrôleur.model.getTemps()).toString() + ", Voix = " + Conjugueur.M.ConjugLat.V.i2V(contrôleur.model.getVoix()).toString() + ", itype = " + itype + "personne.suffixes ["+contrôleur.model.getMode()+"]["+contrôleur.model.getTemps()+"]["+contrôleur.model.getVoix()+"]["+itype.ic()+"] = "+personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()]) ; }
        if (Character.compare('+',forme[LAF.Parfait.ifo()].charAt(0)) != 0) { 
            for (char c : (forme[LAF.Parfait.ifo()]).substring(0, forme[LAF.Parfait.ifo()].length()- 1).toCharArray()){
                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
            }                                                    // pour ts les caractères
            if (!personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()].isEmpty())  this.Suffixer (personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()]) ;
        } else {
            for (char c : (forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()]).substring(0, forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length()- 1).toCharArray()){
                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
            }                                                    // pour ts les caractères
            if (Character.compare('+',forme[LAF.Parfait.ifo()].charAt(0)) == 0) { 
                for (char c : (forme[LAF.Parfait.ifo()]).substring(1, forme[LAF.Parfait.ifo()].length()- 1).toCharArray()){
                    Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                }                                                    // pour ts les caractères
            }

            if (!personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()].isEmpty())  this.Suffixer (personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][contrôleur.model.getVoix()][itype.ic()]) ;
        }
    }                                                                          // DéclinerRégulièrementParfait
/* Les types sont ceux définis ici https://uoh.fr/document/c84d20f4/135f/4838/c84d20f4-135f-4838-a796-fce49a25b519/co/02_01_formes_conjuguees.html */    

    /**
     * Fonction : Détermine le type de conjugaison voir https://clg-monet-magny.ac-versailles.fr/IMG/pdf/Les_5_types_de_conjugaison.pdf
     * Algorithme
     *  1. Prendre l'infinitif présent (amāre, ...)
     *  2. Prendre la terminaison de la 1re pers. du présent de l’indicatif (ō, ..)
     *  3. Prendre la terminaison de la 2e pers. du présent de l’indicatif (ās,..)
     */    
    private LAC DéterminerConjugaison(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableLat::DéterminerConjugaison, Verbe = " + ConjugLat.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugLat.ATTRIBUT)) ; }
        StringTokenizer tokenizer = new StringTokenizer(ConjugLat.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugLat.ATTRIBUT), " ");
        forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()] = tokenizer.nextToken() ;
        forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()] = tokenizer.nextToken() ;
        forme[LAF.InfinitifPrésent.ifo()] = tokenizer.nextToken() ;
        forme[LAF.Parfait.ifo()] = tokenizer.nextToken() ;
        if (tokenizer.hasMoreTokens()) forme[LAF.Supin.ifo()] = tokenizer.nextToken() ;
        if (  (("+are".equals(forme[LAF.InfinitifPrésent.ifo()]))||("+āre".equals(forme[LAF.InfinitifPrésent.ifo()])))
            && (("+as".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()])) || ("+ās".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()])))
            && (("o".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 1)))||("ō".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 1))))    ) { JFrameConjug.jLabelConjugaison.setText("1er groupe") ; return LAC.PremièreConjugaison ; }
        else if ( (("+ere".equals(forme[LAF.InfinitifPrésent.ifo()]))||("+ēre".equals(forme[LAF.InfinitifPrésent.ifo()]))||("+ĕre".equals(forme[LAF.InfinitifPrésent.ifo()])))
            && (("+es".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()]))||("+ēs".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()])))
            && (("eo".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 2)))||("eō".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 2)))||("ēō".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 2))))) { JFrameConjug.jLabelConjugaison.setText("2ème groupe") ; return LAC.DeuxièmeConjugaison ; }
        else if ( (("+ēre".equals(forme[LAF.InfinitifPrésent.ifo()]))||("+ere".equals(forme[LAF.InfinitifPrésent.ifo()]))||("+ĕre".equals(forme[LAF.InfinitifPrésent.ifo()])))
                && (("+is".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()]))||("+ĭs".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()])))) { JFrameConjug.jLabelConjugaison.setText("3ème groupe") ;
                if ("io".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 2))) { return LAC.TroisièmeConjugaisonMixte ; }
                else if (("o".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 1)))||("ō".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 1))))  { return LAC.TroisièmeConjugaison ; }
             }
        else if ( (("+ire".equals(forme[LAF.InfinitifPrésent.ifo()]))||("+īre".equals(forme[LAF.InfinitifPrésent.ifo()])))
                 && (("+is".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()]))||("+ĭs".equals(forme[LAF.DeuPersDuPrésentDeLIndicatif.ifo()])))
                && (("io".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 2)))||("iō".equals(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].substring(forme[LAF.PrePersDuPrésentDeLIndicatif.ifo()].length() - 2))))       ) { JFrameConjug.jLabelConjugaison.setText("4ème groupe") ; return LAC.QuatrièmeConjugaison ; }
        return (LAC.Irrégulier) ;
    }                                                                           // DéterminerConjugaison
    public enum LAC {
        PremièreConjugaison((short)0), DeuxièmeConjugaison((short)1), TroisièmeConjugaison((short)2), TroisièmeConjugaisonMixte((short)3), QuatrièmeConjugaison((short)4), Irrégulier((short)5) ;
        private final short ic;
        private LAC(short ic) {   this.ic = ic;      }
        public short ic() {      return this.ic ;     }
    }                                                                           //LAC
    public enum LAF {
        PrePersDuPrésentDeLIndicatif((short)0), DeuPersDuPrésentDeLIndicatif((short)1), InfinitifPrésent((short)2), Parfait((short)3), Supin((short)4) ;
        private final short ifo;
        private LAF(short ifo) {            this.ifo = ifo;        }
        public short ifo() {            return this.ifo;        }        
    }                                                                           // LAF
    public enum LAP {
        LA1S((short)0, "1è. s.", "", new String [][][][]{/*Indicatif*/{/*Présent*/{/*active*/{/* 1è type */"o",/*2è type */ "o",/*3è type */ "o", /*3è type mixte*/ "o", /*4è type */ "o"}, /*passive*/{/* 1è type */"or",/*2è type */ "or",/*3è type */ "or",/*3è type mixte*/ "ior",/*4è type */ "or"}}, /*Parfait*/{/*active*/{/* 1è type */"ī",/*2è type */ "i",/*3è type */ "i",/*3è type mixte*/ "i",/*4è Conj*/ "i"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"bam",/*2è type */ "bam",/*3è type */ "ebam",/*3è mixte */ "ebam",/*4è Conj*/ "ebam", /*Irr*/"ibam"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"bo",/*2è type */ "bo",/*3è type */ "am",/*3è type mixte*/ "am",/*4è type */ "am"}, /*passive*/{/* 1è type */"bor",/*2è type */ "bor",/*3è type */ "ar",/*3è type mixte*/ "ar",/*4è type */ "ar"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}},/*Subjonctif*/{/*Présent*/{/*active*/{/* 1è type */"em",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}},/*Impératif*/{/*Présent*/{/*active*/{/* 1è type */"ā",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io", /*Irr*/"ibam"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}},/*Participe*/{/*Présent*/{/*active*/{/* 1è type */"ā",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}}}),
        LA2S((short)1, "2è. s.", "", new String [][][][]{/*Indicatif*/{/*Présent*/{/*active*/{/* 1ère type */"as",/*2è type */ "s",/*3è type */ "is", /*3è type mixte*/ "s", /*4è type */ "s"}, /*passive*/{/* 1è type */"aris",/*2è type */ "ris",/*3è type */ "eris",/*3è type mixte*/ "eris",/*4è type */ "ris"}}, /*Parfait*/{/*active*/{/* 1è type */"istī",/*2è type */ "isti",/*3è type */ "isti",/*3è type mixte*/ "isti",/*4è Conj*/ "isti"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"bas",/*2è type */ "bas",/*3è type */ "ebas",/*3è type mixte*/ "ebas",/*4è Conj*/ "ebas", /*Irr*/"ibam"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"bis",/*2è type */ "bis",/*3è type */ "es",/*3è type mixte*/ "es",/*4è type */ "es"}, /*passive*/{/* 1è type */"beris",/*2è type */ "beris",/*3è type */ "eris",/*3è type mixte*/ "eris",/*4è type */ "eris"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābās",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}},/*Subjonctif*/{/*Présent*/{/*active*/{/* 1è type */"ēs",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}},/*Impératif*/{/*Présent*/{/*active*/{/* 1è type */"ā",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}},/*Participe*/{/*Présent*/{/*active*/{/* 1è type */"ans",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*3è type mixte*/ "io",/*4è type */ "io"}}}, /*Conditionnel*/{}, /*Infinitif*/{/*Présent*/{/*active*/{/* 1è type */""}}, /*Parfait*/{/*active*/{/* 1è type */"īsse",/*2è type */ "īsse",/*3è type */ "īsse",/*3è type mixte*/ "īsse",/*4è Conj*/ "īsse"}}}}),
        LA3S((short)2, "3è. s.", "", new String [][][][]{/*Indicatif*/{/*Présent*/{/*active*/{/* 1ère type */"at",/*2è type */ "t",/*3è type */ "it", /*3è type mixte*/ "t", /*4è type */ "t"}, /*passive*/{/* 1è type */"atur",/*2è type */ "tur",/*3è type */ "itur",/*3è type mixte*/ "itur",/*4è type */ "tur"}}, /*Parfait*/{/*active*/{/* 1è type */"it",/*2è type */ "it",/*3è type */ "it",/*4è type */ "it",/*4è Conj*/ "it"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"bat",/*2è type */ "bat",/*3è type */ "ebat",/*3è type mixte */ "ebat",/*4è Conj*/ "ebat", /*Irr*/"ibam"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"bit",/*2è type */ "bit",/*3è type */ "et",/*3è type mixte*/ "et",/*4è type */ "et"}, /*passive*/{/* 1è type */"bitur",/*2è type */ "bitur",/*3è type */ "etur",/*3è type mixte*/ "etur",/*4è type */ "etur"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābat",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Subjonctif*/{/*Présent*/{/*active*/{/* 1è type */"et",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Impératif*/{/*Présent*/{/*active*/{/* 1è type */"ēs",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Participe*/{/*Présent*/{/*active*/{/* 1è type */"ā",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}}}),
        LA1P((short)3, "1è. p.", "", new String [][][][]{/*Indicatif*/{/*Présent*/{/*active*/{/* 1ère type */"amus",/*2è type */ "mus",/*3è type */ "imus", /*3è type mixte*/ "mus", /*4è type */ "mus"}, /*passive*/{/* 1è type */"amur",/*2è type */ "mur",/*3è type */ "imur",/*3è type mixte*/ "imur",/*4è type */ "mur"}}, /*Parfait*/{/*active*/{/* 1è type */"imus",/*2è type */ "imus",/*3è type */ "imus",/*4è type */ "imus",/*4è Conj*/ "imus"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"bamus",/*2è type */ "ebamus",/*3è type */ "bamus",/*3è type mixte */ "ebamus",/*4è Conj*/ "ebamus", /*Irr*/"ibam"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"bimus",/*2è type */ "bimus",/*3è type */ "emus",/*3è type mixte */ "emus",/*4è type */ "emus"}, /*passive*/{/* 1è type */"bimur",/*2è type */ "bimur",/*3è type */ "emur",/*3è type mixte*/ "emur",/*4è type */ "emur"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābāmus",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Subjonctif*/{/*Présent*/{/*active*/{/* 1è type */"ēmus",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Impératif*/{/*Présent*/{/*active*/{/* 1è type */"ēs",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Participe*/{/*Présent*/{/*active*/{/* 1è type */"ā",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}}}),
        LA2P((short)4, "2è. p.", "", new String [][][][]{/*Indicatif*/{/*Présent*/{/*active*/{/* 1ère type */"atis",/*2è type */ "tis",/*3è type */ "itis", /*3è type mixte*/ "tis", /*4è type */ "tis"}, /*passive*/{/* 1è type */"amini",/*2è type */ "mini",/*3è type */ "imini",/*3è type mixte*/ "imini",/*4è type */ "mini"}}, /*Parfait*/{/*active*/{/* 1è type */"istis",/*2è type */ "istis",/*3è type */ "istis",/*4è type */ "istis",/*4è Conj*/ "istis"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"batis",/*2è type */ "ebatis",/*3è type */ "batis",/*3è type mixte */ "ebatis",/*4è Conj*/ "ebatis", /*Irr*/"ibam"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"bitis",/*2è type */ "bitis",/*3è type */ "etis",/*3è type mixte*/ "etis",/*4è type */ "etis"}, /*passive*/{/* 1è type */"bimini",/*2è type */ "bimini",/*3è type */ "emini",/*3è type mixte*/ "emini",/*4è type */ "emini"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābātis",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Subjonctif*/{/*Présent*/{/*active*/{/* 1è type */"ētis",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Impératif*/{/*Présent*/{/*active*/{/* 1è type */"ā-te",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Participe*/{/*Présent*/{/*active*/{/* 1è type */"ā",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}}}),
        LA3P((short)5, "3è. p.", "", new String [][][][]{/*Indicatif*/{/*Présent*/{/*active*/{/* 1ère type */"ant", /*2è type */ "nt", /*3è type */ "unt", /*3è type mixte*/ "unt", /*4è type */ "unt"}, /*passive*/{/* 1è type */"antur",/*2è type */ "ntur",/*3è type */ "untur",/*3è type mixte*/ "iuntur",/*4è type */ "untur"}}, /*Parfait*/{/*active*/{/* 1è type */"ērunt",/*2è type */ "erunt",/*3è type */ "erunt",/*4è type */ "erunt",/*4è Conj*/ "erunt"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"bant",/*2è type */ "bant",/*3è type */ "ebant",/*3è type mixte */ "ebant",/*4è Conj*/ "ebant", /*Irr*/"ibam"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"bunt",/*2è type */ "bunt",/*3è type */ "ent",/*3è type mixte*/ "ent",/*4è type */ "ent"}, /*passive*/{/* 1è type */"buntur",/*2è type */ "buntur",/*3è type */ "entur",/*3è type mixte*/ "entur",/*4è type */ "entur"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābant",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Subjonctif*/{/*Présent*/{/*active*/{/* 1è type */"ent",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Impératif*/{/*Présent*/{/*active*/{/* 1è type */"ēs",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}},/*Participe*/{/*Présent*/{/*active*/{/* 1è type */"ā",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"āvī",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*FuturSimple*/{/*active*/{/* 1è type */"abo",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}, /*Imparfait*/{/*active*/{/* 1è type */"ābam",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}, /*passive*/{/* 1è type */"or",/*2è type */ "eō",/*3è type */ "o",/*4è type */ "io",/*4è type */ "io"}}}}) ;
        private final int ip ;                                                  // indice de personne 0, 1, 2,...
        private final String lp ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String lip;
        public final String [/*mode*/][/*temps*/][/*voix*/][/*type*/] suffixes ;                                     // préfixe et suffixe par schème
        LAP(int ipers, String lp, String lip, String [][][][] suffixes) {
            this.ip     = ipers ; this.lp     = lp ; this.lip = lip ; this.suffixes = suffixes ;
        }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lip() { return lip; }
        public static LAP l2P(String name) {
            LAP result = null;
            for (LAP personne : LAP.values()) if (personne.lp().equals(name)) { result = personne;  break;  }
            return result;
        }                                                                       //l2P
    }                                                                           //LAP
    private boolean trouvé = false ;                                            // indique qu'un conjug irrégulière a été trouvée
    private static LAC itype ;                                            // N° de groupe
    String forme[] = new String [5] ;

    private final boolean DEBUG = true ;    
}
