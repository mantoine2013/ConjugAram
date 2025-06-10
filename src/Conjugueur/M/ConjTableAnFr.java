/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import static Conjugueur.M.ConjTable.nbcons;
import static Conjugueur.M.ConjugAnFr.TAnFr.Imparfait;
import static Conjugueur.M.ConjugAnFr.TAnFr.PasséSimple;
import static Conjugueur.M.ConjugAnFr.TAnFr.Présent;
import Conjugueur.V.*;
import java.util.StringTokenizer;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;


public class ConjTableAnFr extends ConjTable  {

    /**
     * Fonction appelante : Conjugueur.Vue.JFrameConjugAnFr::JFrameConjugAnFr
     * Algorithme : 
     * 1.Remettre le tableau de conjugaison à zéro
 2. S'aiguiller selon le temps
 2.1 Voix active
 2.1.1 S'aiguiller selon 
 2.1.1.1 Temps Passé
 2.1.1.2 Temps Inaccompli
 2.1.1.2.1 Si le verbe est régulier alors
 2.1.1.2.1.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
 2.1.1.2.1.2 S'il n'y a pas de présent particuler alors
 2.1.1.2.1.2.1 Pour ts les caractères de l'infinitif faire
 2.1.1.2.1.2.1.1 Si c'est une consonne alors
 2.1.1.2.1.2.1.1.1 Incrémenter compteur de consonnes
 2.1.1.2.1.2.1.1.2 Insérer un "וֹ" en 2è position s'il n'est pas déjà présent
 2.1.1.2.1.2.1.1.3 Copier le caractère en appelant "CopierCaractèRacine"
 2.3 Groupe Piel
 2.3.1 S'aiguiller selon le temps       
 2.3.1 Temps Passé
 2.3.2 Temps Inaccompli
 2.3.2.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
 2.7 Groupe Piel
 2.7.1 S'aiguiller selon le temps       
 2.7.1 Temps Passé
 2.7.2 Temps Inaccompli
 2.7.2.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     * @param contrôleur
     */
    @Override
    public void MAJConj (Conjugueur.C.Conjug contrôleur) {
       if (DEBUG) { System.out.println ("Conjugueur.M.ConjTableAnFr::MAJConj, Verbe = " + ConjugAnFr.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAnFr.ATTRIBUT) + ", Mode = " + Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()).toString() + ", Temps = " + Conjugueur.M.ConjugAnFr.TAnFr.i2T(contrôleur.model.getTemps()).toString() +", Voix = "+ Conjugueur.M.ConjugAnFr.V.i2V(contrôleur.model.getVoix()).toString()) ; }
        try {
            vider ();
            nbcons = 0 ;                                                // compteur de consonnes
            déclinaisonChildren =  ConjugAnFr.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
            switch (Conjugueur.M.ConjugAnFr.M.i2M(contrôleur.model.getMode())) {                                             // mode
                case Conditionnel, Indicatif -> {                                             // Indicatif
                    switch (Conjugueur.M.ConjugAnFr.TAnFr.i2T(contrôleur.model.getTemps())) {                             // temps
                        case PasséSimple -> {
                            for (AFP personne : AFP.values()) {                 // pour ttes les personnes
                                if(!déclinaisonChildren.isEmpty())
                                    CopierIrrégularités (Conjugueur.M.ConjugAnFr.M.Indicatif.toString(), personne, contrôleur) ; 
                                else {                                  // verbe régulier
                                    nbcons = 0 ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                                    Copier(personne.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
                                    DéclinerPasséSimpleRégulièrement (personne, contrôleur) ;    
                                }                                       // verbe régulier
                            }                                                   // ttes les personnes                                                                             
                        }                                                               // case Présent
                        case Imparfait, Présent -> {
                            for (AFP personne : AFP.values()) {                 // pour ttes les personnes
                                if(!déclinaisonChildren.isEmpty())
                                    CopierIrrégularités (Conjugueur.M.ConjugAnFr.M.Indicatif.toString(), personne, contrôleur) ; 
                                else {                                  // verbe régulier
                                    nbcons = 0 ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                                    Copier(personne.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                }                                       // verbe régulier
                            }                                                   // ttes les personnes                                                                             
                        }                                                               // case Présent
                    }                                                           // switch (Temps)
                }                                                               // Indicatif
                case Impératif -> {                                                     // Impératif
                    switch (Conjugueur.M.ConjugAnFr.TAnFr.i2T(contrôleur.model.getTemps())) {                               // temps
                        case Présent -> {
                            inbLignes = AjouterLigne(AFP.AF2S.ip(), AFP.AF2S.lp(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;;
                            Copier(AFP.AF2S.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
                            DéclinerRégulièrement (AFP.AF2S, contrôleur) ;    
                            inbLignes = AjouterLigne(AFP.AF2P.ip(), AFP.AF2P.lp(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;;
                            Copier(AFP.AF2P.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
                            DéclinerRégulièrement (AFP.AF2P, contrôleur) ;    
                        }                                                       // case Présent
                    }                                                           // switch (Temps)
                }                                                               // Impératif
                case Participe -> {                                                     // Participe
                    switch (Conjugueur.M.ConjugAnFr.TAnFr.i2T(contrôleur.model.getTemps())) {                               // temps
                        case Présent -> {
                            inbLignes = AjouterLigne(AFP.AF1S.ip(), "", JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;;
                            DéclinerRégulièrement (AFP.AF1S, contrôleur) ;    
                        }                                                       // case Présent
                    }                                                           // switch (Temps)
                }                                                               // Participe
                case Subjonctif -> {                                                     // Subjonctif
                    switch (Conjugueur.M.ConjugAnFr.TAnFr.i2T(contrôleur.model.getTemps())) {                               // temps
                        case Présent -> {
                            for (AFP personne : AFP.values()) {                 // pour ttes les personnes
                                if(!déclinaisonChildren.isEmpty())
                                    CopierIrrégularités (Conjugueur.M.ConjugAnFr.M.Subjonctif.toString(), personne, contrôleur) ; 
                                else {                                  // verbe régulier
                                    nbcons = 0 ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                                    Copier(personne.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                }                                       // verbe régulier
                            }                                                   // ttes les personnes                                                                             
                        }                                                       // case Présent
                        case Imparfait -> {
                            for (AFP personne : AFP.values()) {                 // pour ttes les personnes
                                if(!déclinaisonChildren.isEmpty())
                                    CopierIrrégularités (Conjugueur.M.ConjugAnFr.M.Subjonctif.toString(), personne, contrôleur) ; 
                                else {                                  // verbe régulier
                                    nbcons = 0 ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                                    Copier(personne.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                }                                       // verbe régulier
                            }                                                   // ttes les personnes                                                                             
                        }                                                       // case Présent
                    }                                                           // switch (Temps)
                }                                                               // Subjonctif
            }                                                                   // mode
            
        } catch (BadLocationException ble) {}
//        updateRowHeights(ConjugAnFrodèle.jTabConj) ;
    }                                                                           // updateTextPaneConj
       
    /**
     * Fonction : Substitue les conjugaisons régulières par irrégularités trouvées ds le xml
     * Algorithme : 
     * 1.Pour ttes irrégularités trouvées ds le xml faire
 1.1 Si cette irrégularité correspond au mode et au temps choisi alors
 1.1.1 Pour ttes les conjugaisons régulières déjà ds "tabLigneConj" faire
 1.1.1.1 Si la personne régulière se trouve être en même temps irrégulière alors
 1.1.1.1.1 si les conjugaisons diffèrent faire
 1.1.1.1.1.1 Supprimer de "tabLigneConj" la déclinaison régulière
 1.1.1.1.1.2 Insertion de l'irrégularité
 1.1.1.2 sinon
 1.1.1.2.1 Ajouter l'irrégularité
     * @param mode
    */
    public void CopierIrrégularités(String mode, AFP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {        
        if (DEBUG) System.out.println("ConjugItal::CopierIrrégularités, mode = " + mode+ ", temps = " + Conjugueur.M.ConjugAnFr.TAnFr.i2T(contrôleur.model.getTemps()).toString() +", personne = "+ personne.lp()) ;
        for (int i = 0 ; i < déclinaisonChildren.size(); i++) {                  // Pour ttes les déclinaisons
            if (   (déclinaisonChildren.get(i).getAttribute("temps").getValue().compareTo(Conjugueur.M.ConjugAnFr.TAnFr.i2T(contrôleur.model.getTemps()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("mode").getValue().compareTo(Conjugueur.M.ConjugAnFr.M.i2M(contrôleur.model.getMode()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("personne").getValue().compareTo(personne.lp()) == 0)    ) {            
                inbLignes = AjouterLigne(AFP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).ip(), AFP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lp(), JFrameConjugItal.ITALIEN, StyleConstants.ALIGN_RIGHT) ;
                Copier(inbLignes, AFP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lap(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes);
                Copier(inbLignes, déclinaisonChildren.get(i).getText(), DéclinaisonStyledDoc.stylesu);
            } 
        }                                                                       // Pour ttes les irrégularités
    }    
    
    /**
     * Fonction : remplit la colonne de déclinaisons régulière du mode indicatif
     * @param personne
     * Fction appelante : ConjTableAnFr::MAJConj
     * Algorithme
     * 1. Copier le libellé du Pronom Personnel
     * 2. Copier la racine
     */
    public void DéclinerRégulièrement(AFP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {
        ig = DéterminerConjugaison(contrôleur) ;
        if (ig == null) { System.out.println("*** erreur interne Conjugueur.M.ConjTableAnFr::DéterminerConjugaison***"); System.exit(-1);}
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableAnFr::DéclinerRégulièrement, personne = " + personne.lp + "personne.suffixes ["+contrôleur.model.getMode()+"]["+contrôleur.model.getTemps()+"]["+ig.ic()+"] = "+ personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()]) ; }
        if (personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()].isEmpty()) CopieRacine(forme[AFF.Radical.ifo()]) ;
        else {
            if (personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()].charAt(0) == '-') {
                CopieRacine(forme[AFF.Radical.ifo()].substring(0, forme[AFF.Radical.ifo()].length() -1)) ;
                Suffixer (inbLignes, personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()].substring(1)) ;
            }
            else {
                CopieRacine(forme[AFF.Radical.ifo()]) ;
                Suffixer (inbLignes, personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()]) ;
            }
        }                                                                       // il existe un suffixe
     }
    /**
     * Fonction : remplit la colonne de déclinaisons régulière du mode indicatif
     * @param personne
     * Fction appelante : ConjTableAnFr::MAJConj
     * Algorithme
     * 1. Déterminer le type de passé simple en appelant DéclinerPasséSimpleRégulièrement
     * 2. Copier le libellé du Pronom Personnel
     * 3. Copier la racine
     */
    public void DéclinerPasséSimpleRégulièrement(AFP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {
        ig = DéterminerPasséSimpleConjugaison(contrôleur) ;
        if (ig == null) { System.out.println("*** erreur interne Conjugueur.M.ConjTableAnFr::DéterminerPasséSimpleConjugaison***"); System.exit(-1);}
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableAnFr::DéclinerPasséSimpleRégulièrement, personne = " + personne.lp + "personne.suffixes ["+contrôleur.model.getMode()+"]["+contrôleur.model.getTemps()+"]["+ig.ic()+"] = "+ personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()]) ; }
        if (personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()].isEmpty()) CopieRacine(forme[AFF.Radical.ifo()]) ;
        else {
            if (personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()].charAt(0) == '-') {
                CopieRacine(forme[AFF.Radical.ifo()].substring(0, forme[AFF.Radical.ifo()].length() -1)) ;
                Suffixer (inbLignes, personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()].substring(1)) ;
            }
            else {
                CopieRacine(forme[AFF.Radical.ifo()]) ;
                Suffixer (inbLignes, personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig.ic()]) ;
            }
        }                                                                       // il existe un suffixe
     }
    /**
     * Fonction : Détermine le type de conjugaison
     * Algorithme
     *  1. Prendre l'infinitif présent (amāre, ...)
     *  2. Prendre la terminaison de la 1re pers. du présent de l’indicatif (ō, ..)
     *  3. Prendre la terminaison de la 2e pers. du présent de l’indicatif (ās,..)
     */    
    private AFC DéterminerConjugaison(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableAnFr::DéterminerConjugaison, Verbe = " + ConjugAnFr.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAnFr.ATTRIBUT)) ; }
        StringTokenizer tokenizer = new StringTokenizer(ConjugAnFr.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAnFr.ATTRIBUT), " ");
        forme[AFF.Radical.ifo()] = tokenizer.nextToken() ;
        forme[AFF.Suffixe.ifo()] = tokenizer.nextToken() ;
        switch (forme[AFF.Suffixe.ifo()]) {
            case "+er" -> { JFrameConjug.jLabelConjugaison.setText("1er groupe") ; return AFC.PremièreConjugaison ; }
            case "+ir" -> { JFrameConjug.jLabelConjugaison.setText("2ème groupe") ; return AFC.DeuxièmeConjugaison ; }
            case "ĕre" -> { JFrameConjug.jLabelConjugaison.setText("3ème déclinaison") ; return AFC.TroisièmeConjugaison  ; }
            case "īre" -> { JFrameConjug.jLabelConjugaison.setText("4ème déclinaison") ; return AFC.QuatrièmeConjugaison ; }
        }                                                   // switch
        return (null) ;
    }                                                                           // DéterminerConjugaison
    /**
     * Fonction : Détermine le type de conjugaison du Passé Simple
     * Algorithme
     *  1. Prendre le radical (chant, ...)
     *  2. Prendre la terminaison de la 1re pers. du présent de l’indicatif (er, ..)
     *  3. Prendre la terminaison de la 3e pers. du passé simple de l’indicatif (a, i ,..)
     */    
    private AFC DéterminerPasséSimpleConjugaison(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableAnFr::DéterminerPasséSimpleConjugaison, Verbe = " + ConjugAnFr.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAnFr.ATTRIBUT)) ; }
        StringTokenizer tokenizer = new StringTokenizer(ConjugAnFr.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugAnFr.ATTRIBUT), " ");
        forme[AFF.Radical.ifo()] = tokenizer.nextToken() ;
        forme[AFF.Suffixe.ifo()] = tokenizer.nextToken() ;
        forme[AFF.PartPassé.ifo()] = tokenizer.nextToken() ;
        switch (forme[AFF.Suffixe.ifo()]) {
            case "+er" -> { JFrameConjug.jLabelConjugaison.setText("1er groupe") ;
                            if (("+é".equals(forme[AFF.PartPassé.ifo()]))) return AFC.PremièreConjugaison ; }
            case "+ir" -> { JFrameConjug.jLabelConjugaison.setText("2ème groupe") ;
                            if (("+i".equals(forme[AFF.PartPassé.ifo()]))) return AFC.DeuxièmeConjugaison ; }
            case "re" -> { JFrameConjug.jLabelConjugaison.setText("3ème déclinaison") ; return AFC.TroisièmeConjugaison  ; }
            case "+oir" -> { JFrameConjug.jLabelConjugaison.setText("3ème déclinaison") ;
                            if ("+u".equals(forme[AFF.PartPassé.ifo()])) return AFC.TroisièmeConjugaison ;
                            if ("+i".equals(forme[AFF.PartPassé.ifo()])) return AFC.PartPasséFortI ; }
            case "īre" -> { JFrameConjug.jLabelConjugaison.setText("4ème déclinaison") ; return AFC.QuatrièmeConjugaison ; }
        }                                                   // switch
        return (null) ;
    }                                                                           // DéterminerConjugaison

    public enum AFC {
        PremièreConjugaison((short)0), DeuxièmeConjugaison((short)1), TroisièmeConjugaison((short)2), QuatrièmeConjugaison((short)3), PartPasséFaibla((short)4), PartPasséFortI((short)5) ;
        private final short ic;
        private AFC(short ic) {   this.ic = ic;      }
        public short ic() {      return this.ic ;     }
    }                                                                           //AFC
    public enum AFF {
        Radical((short)0), Suffixe((short)1), PartPassé((short)2), Parfait((short)3), Supin((short)4) ;
        private final short ifo;
        private AFF(short ifo) {            this.ifo = ifo;        }
        public short ifo() {            return this.ifo;        }        
    }                                                                           // AFF
    public enum AFP {                                                          // constantes définissant l'ordre des personnes
        AF1S (0, "1è. s.", "jo, je, ge (gié)", new String [][][]{/*Indicatif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/"o"}, /*Imparfait*/{/*er*/"oie", /*ir*/"oie", "ivo"}, /*Passé simple*/{/*er*/"ai", /*ir*/"i", /*oir*/"ui","","","",/*For en I*/"-i"}, /*Futur*/{"erai", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*er*/"", /*ir*/"e", /*ire*/"a"}, /*Imparfait*/{/*er*/"asse", /*ir*/"isse ", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"ant", /*ir*/"ant", /*ire*/"ei"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"ereie/eroie", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        AF2S (1, "2è. s.", "tu", new String [][][]{/*Indicatif*/{/*Présent*/{/*er*/"es", /*ir*/"-z", /*ire*/"o"}, /*Imparfait*/{/*er*/"oies", /*ir*/"oies", "ivo"}, /*Passé simple*/{/*er*/"as", /*ir*/"is", /*oir*/"us","","","",/*For en I*/"-eïs"}, /*Futur*/{"eras", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*er*/"-z", /*ir*/"es", /*ire*/"a"}, /*Imparfait*/{/*er*/"asses", /*ir*/"isses ", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"e", /*ir*/"", /*ire*/"ei"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"ereies/eroies", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        AF3MS (2, "3è. m. s. : ", "il", new String [][][]{/*Indicatif*/{/*Présent*/{/*er*/"e", /*ir*/"", /*ire*/"o"}, /*Imparfait*/{/*er*/"oit", /*ir*/"oit", "ivo"}, /*Passé simple*/{/*er*/"a", /*ir*/"i", /*oir*/"ut","","","",/*For en I*/"-it"}, /*Futur*/{"era", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*er*/"", /*ir*/"e", /*ire*/"a"}, /*Imparfait*/{/*er*/"ast", /*ir*/"ist ", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"ereit/eroit", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        AF3FS (3, "3è. f. s. : ", "ele (el)", new String [][][]{/*Indicatif*/{/*Présent*/{/*er*/"e", /*ir*/"", /*ire*/"o"}, /*Imparfait*/{/*er*/"oit", /*ir*/"oit", "ivo"}, /*Passé simple*/{/*er*/"a", /*ir*/"i",/*oir*/ "ut","","","",/*For en I*/"-it"}, /*Futur*/{"era", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*er*/"", /*ir*/"e", /*ire*/"a"}, /*Imparfait*/{/*er*/"ast", /*ir*/"ist ", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"ereit/eroit", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        AF1P (4, "1è. p.", "nos", new String [][][]{/*Indicatif*/{/*Présent*/{/*er*/"ons", /*ir*/"ons", /*ire*/"o"}, /*Imparfait*/{/*er*/"ïens/ïons", /*ir*/"ïens/ïons", "ivo"}, /*Passé simple*/{/*er*/"ames", /*ir*/"umes",/*oir*/ "umes","","","",/*For en I*/"-eïmes"}, /*Futur*/{"erons", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*are*/"ons", /*ir*/"ons/iens", /*ire*/"a"}, /*Imparfait*/{/*er*/"issons ", /*ir*/"issons ", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"eriions/erions", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        AF2P (5, "2è. p. : ", "vos", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"ez", /*ir*/"ez", /*ire*/"o"}, /*Imparfait*/{/*er*/"ïez", /*ir*/"ïez", "ivo"}, /*Passé simple*/{/*er*/"astes", /*ir*/"istes", /*oir*/"ustes","","","",/*For en I*/"-eïstes"}, /*Futur*/{"ererez", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*er*/"ez", /*ir*/"ez", /*ire*/"a"}, /*Imparfait*/{/*er*/"isseiz/issez ", /*ir*/"isseiz/issez ", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"ez", /*ir*/"ez", /*ire*/"ei"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"eriiez/eriez", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        AF3MP (6, "3è. m. p. : ", "il", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"ent", /*ir*/"ent", /*ire*/"o"}, /*Imparfait*/{/*er*/"oient", /*ir*/"oient", "ivo"}, /*Passé simple*/{/*er*/"erent", /*ir*/"irent", /*oir*/"urent","","","",/*For en I*/"-irent"}, /*Futur*/{"eront", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*er*/"ent", /*ir*/"ent", /*ire*/"a"}, /*Imparfait*/{/*er*/"assent", /*ir*/"issent", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"ereient/eroient", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        AF3FP (7, "3è. f. p.", "eles", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"ent", /*ir*/"ent", /*ire*/"o"}, /*Imparfait*/{/*er*/"oient", /*ir*/"oient", "ivo"}, /*Passé simple*/{/*er*/"erent", /*ir*/"irent", /*oir*/"urent","","","",/*For en I*/"-irent"}, /*Futur*/{"eront", "erò", "irò"}}, /*Subjonctif*/{/*Présent*/{/*er*/"ent", /*ir*/"ent", /*ire*/"a"}, /*Imparfait*/{/*er*/"assent", /*ir*/"issent", ""}, /*Futur*/{"", "", ""}}, /*Impératif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Participe*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"ereient/eroient", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}) ;
        private final int ip ;                                                  // indice de personne 0, 1, 2,...
        public final String lp ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String lap ;                                              // PP arabeu
        public final String [/*mode*/][/*temps*/][] suffixes ;                                     // suffixe par schème
        AFP(int ip, String libpers, String lap, String [][][] suffixes) { this.ip = ip ; this.lp = libpers ; this.lap    = lap ; this.suffixes = suffixes ; }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lap() { return lap; }
        public static AFP l2P(String name) {
            AFP result = null;
            for (AFP personne : AFP.values()) if (personne.lp().equals(name)) { result = personne;  break;  }
            return result;
        }       //    public String ppiyi(ConjTempsAraMM.ART it) { return PairePS[it.it()][PRÉFIXE] ; }
    }                                                                           //AFP

    String forme[] = new String [5] ;
    public static AFC ig ;                                                      // N° de groupe

    private final boolean DEBUG = true;
}
