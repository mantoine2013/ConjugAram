/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import com.ibm.icu.text.Transliterator;
import static Conjugueur.M.ConjTable.nbcons;
import Conjugueur.V.DéclinaisonStyledDoc;
import Conjugueur.V.JFrameConjug;
import Conjugueur.V.JFrameConjugRu;
import java.util.StringTokenizer;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class ConjTableRu extends ConjTable {
    /**
     * Fonction appelante : Conjugueur.V.JFrameConjugRu::JFrameConjugRu
     * Algorithme : 
     * 1.Remettre le tableau de conjugaison à zéro
     * 2. S'aiguiller selon le mode
     * 2.1 mode indicatif
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
       if (DEBUG) { System.out.println ("Conjugueur.M.ConjTableRu::MAJConj, Verbe = " + ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttribute(ConjugRu.ATTRIBUTVERBEI) != null?ConjugRu.ATTRIBUTVERBEI:ConjugRu.ATTRIBUTVERBEP) + ", Mode = " + Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()).toString() + ", Temps = " + Conjugueur.M.ConjugRu.RUT.i2T(contrôleur.model.getTemps()).toString() +", Voix = "+ Conjugueur.M.ConjugRu.V.i2V(contrôleur.model.getVoix()).toString()) ; }
        try {
            vider ();
            nbcons = 0 ;                                                // compteur de consonnes
            déclinaisonChildren =  ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
            switch (Conjugueur.M.ConjugRu.M.i2M(contrôleur.model.getMode())) {                                             // mode
                case Indicatif -> {                                             // Indicatif
                    switch (Conjugueur.M.ConjugRu.RUT.i2T(contrôleur.model.getTemps())) {                             // temps
                        case Futur, Passé, Présent -> {
                            for (RUP personne : RUP.values()) {                 // pour ttes les personnes
                                if(!déclinaisonChildren.isEmpty())
                                { CopierIrrégularités (Conjugueur.M.ConjugRu.M.Indicatif.toString(), personne, contrôleur) ; } 
                                else {                                  // verbe régulier
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugRu.CYRILLIC, StyleConstants.ALIGN_RIGHT) ;
                                    Copier(personne.lap(), DéclinaisonStyledDoc.stylepp);  Espace () ;
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                }                                       // verbe régulier
                                if (inbLignes > 0) AjouterTranslittération(cyToLatinTrans) ;
                            }                                                   // ttes les personnes                                                                             
                        }                                                       // case Présent
                    }                                                           // switch (Temps)
                }                                                               // Indicatif
            }                                                                   // mode
        } catch (BadLocationException ble) {}
    }                                                                           // MAJConj
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
    public void CopierIrrégularités(String mode, RUP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {        
        if (DEBUG) { System.out.println("ConjugRu::CopierIrrégularités, mode = " + mode + ", temps = " + Conjugueur.M.ConjugRu.RUT.i2T(contrôleur.model.getTemps()).toString() +", personne = "+ personne.lp()) ;}
        for (int i = 0 ; i < déclinaisonChildren.size(); i++) {                  // Pour ttes les déclinaisons
            if (   (déclinaisonChildren.get(i).getAttribute("temps").getValue().compareTo(Conjugueur.M.ConjugRu.RUT.i2T(contrôleur.model.getTemps()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("mode").getValue().compareTo(Conjugueur.M.ConjugRu.M.i2M(contrôleur.model.getMode()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("personne").getValue().compareTo(personne.lp()) == 0)    ) {            
                inbLignes = AjouterLigne(RUP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).ip(), RUP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lp(), JFrameConjugRu.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                Copier(inbLignes, RUP.l2P(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lap(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes);
                Copier(inbLignes, déclinaisonChildren.get(i).getText(), DéclinaisonStyledDoc.stylesu);
            } 
        }                                                                       // Pour ttes les irrégularités
    }

    /**
     * Fonction : Détermine le type de conjugaison
     * Algorithme
     *  1. S'il n'y a pas d'attribut "Imperfectif" alors
     *  1.1 Positionner la variable "aspect" du model
     *  1.2 Retourner "Imperfectif"
     *  2 sinon
     *  2.1 Positionner la variable "aspect" du model
     *  2.2 Retourner "Perfectif"
     */    
    private Conjugueur.M.ConjugRu.RUA DéterminerAspect (Conjugueur.C.Conjug contrôleur) {
        if (Conjugueur.M.ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugRu.ATTRIBUTVERBEI) != null) {
//            if (DEBUG) { System.out.println("Conjugueur.M.ConjTableRu::DéterminerAspect, Verbe imperfectif = " + ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugRu.ATTRIBUTVERBEI)) ; }
            contrôleur.model.setAspect(Conjugueur.M.ConjugRu.RUA.Imperfectif.ia()) ;
            return Conjugueur.M.ConjugRu.RUA.Imperfectif ; 
        }                                                                       // Verbe imperfectif
        else {
//            if (DEBUG) { System.out.println("Conjugueur.M.ConjTableRu::DéterminerAspect, Verbe perfectif = " + ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugRu.ATTRIBUTVERBEP)) ; }
            contrôleur.model.setAspect(Conjugueur.M.ConjugRu.RUA.Perfectif.ia()) ;
            return Conjugueur.M.ConjugRu.RUA.Perfectif ;
        }                                                                       // Verbe perfecti
    }                                                                           // DéterminerAspect
    /**
     * Fonction : Détermine le type de conjugaison
     * Algorithme
     *  1. Prendre l'infinitif présent (amāre, ...)
     *  2. Prendre la terminaison de la 1re pers. du présent de l’indicatif (ō, ..)
     *  3. Prendre la terminaison de la 2e pers. du présent de l’indicatif (ās,..)
     */    
    private Conjugueur.M.ConjugRu.RUC DéterminerConjugaison(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableRu::DéterminerConjugaison, Verbe = " + ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttribute(ConjugRu.ATTRIBUTVERBEI) != null?ConjugRu.ATTRIBUTVERBEI:ConjugRu.ATTRIBUTVERBEP)) ; }
        StringTokenizer tokenizer = new StringTokenizer(ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugRu.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttribute(ConjugRu.ATTRIBUTVERBEI) != null?ConjugRu.ATTRIBUTVERBEI:ConjugRu.ATTRIBUTVERBEP), " ");
        forme[RUF.Radical.ifo()] = tokenizer.nextToken() ;
        forme[RUF.Suffixe.ifo()] = tokenizer.nextToken() ;
        switch (forme[RUF.Suffixe.ifo()]) {
            case "+e" -> { contrôleur.model.setNConj(Conjugueur.M.ConjugRu.RUC.PremièreConjugaison.ic()) ; return Conjugueur.M.ConjugRu.RUC.PremièreConjugaison ; }
            case "+и" -> { contrôleur.model.setNConj(Conjugueur.M.ConjugRu.RUC.DeuxièmeConjugaison.ic()) ; return Conjugueur.M.ConjugRu.RUC.DeuxièmeConjugaison ; }
        }                                                   // switch
        return (null) ;
    }                                                                           // DéterminerConjugaison
    /**
     * Fonction : remplit la colonne de déclinaisons régulière du mode indicatif
     * @param personne
     * Fction appelante : ConjTableRu::MAJConj
     * Algorithme
     * 1. Copier le libellé du Pronom Personnel
     * 2. Copier la racine
     */
    public void DéclinerRégulièrement(RUP personne, Conjugueur.C.Conjug contrôleur) throws BadLocationException {
        ig = DéterminerConjugaison(contrôleur) ;
        ia = DéterminerAspect(contrôleur) ;
//        if (DEBUG) { System.out.println("Conjugueur.M.ConjTableRu::DéclinerRégulièrement, personne = " + personne.lp + "personne.suffixes ["+contrôleur.model.getMode()+"]["+contrôleur.model.getAspect()+"]["+contrôleur.model.getTemps()+"]["+ig.ic()+"] = "+ personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getAspect()][contrôleur.model.getTemps()][ig.ic()]) ; }
        CopieRacine(forme[RUF.Radical.ifo()].substring(0, forme[RUF.Radical.ifo()].length() -2)) ;
        Suffixer (inbLignes, personne.suffixes [contrôleur.model.getMode()][ia.ia()][contrôleur.model.getTemps()][ig.ic()]) ;
    }

    public enum RUF {
        Radical((short)0), Suffixe((short)1), PartPassé((short)2), Parfait((short)3), Supin((short)4) ;
        private final short ifo;
        private RUF(short ifo) {            this.ifo = ifo;        }
        public short ifo() {            return this.ifo;        }        
    }                                                                           // RUF
    public enum RUP {                                                          // constantes définissant l'ordre des personnes
        RU1MS (0, "1è. m. s.", "я", new String [][][][] {/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ю", /*и*/"Ю"}, /*Passé*/{/*e*/"л", /*i*/"л"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"л", /*i*/"л"}, /*Futur*/{"ю", "erò"}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*i*/""}, /*Imparfait*/{/*are*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Participe*/{/*Imperfectif*/{/*Présent*/{/*er*/"ant", /*ir*/"ant", /*ire*/"ei"}, /*Imparfait*/{/*are*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU1FS (1, "1è. f. s.", "я", new String [][][][] {/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ю", /*и*/"Ю"}, /*Passé*/{/*e*/"ла", /*i*/"ла"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"л", /*i*/"л"}, /*Futur*/{"ю", "erò"}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*i*/""}, /*Imparfait*/{/*are*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Participe*/{/*Imperfectif*/{/*Présent*/{/*er*/"ant", /*ir*/"ant", /*ire*/"ei"}, /*Imparfait*/{/*are*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU2MS (2, "2è. m. s.", "ты", new String [][][][]{/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ешь", /*и*/"ИШЬ"}, /*Passé*/{/*e*/"л", /*i*/"л"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"л", /*i*/"л"}, /*Futur*/{"ешь", "erò"}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*e*/"e", /*i*/""}, /*Imparfait*/{/*are*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Participe*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}}),
        RU2FS (3, "2è. f. s.", "ты", new String [][][][]{/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ешь", /*и*/"ИШЬ"}, /*Passé*/{/*e*/"ла", /*i*/"ла"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"л", /*i*/"л"}, /*Futur*/{"ешь", "erò"}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*e*/"e", /*i*/""}, /*Imparfait*/{/*are*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Participe*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/"", /*ire*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU3MS (4, "3è. m. s.", "он", new String [][][][]{/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ЕТ", /*и*/"ИТ"}, /*Passé*/{/*e*/"л", /*i*/"л"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"ет", /*ere*/""}, /*Futur*/{"ет", ""}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"", /*ere*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU3FS (5, "3è. f. s.", "она́", new String [][][][]{/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ЕТ", /*и*/"ИТ"}, /*Passé*/{/*e*/"ла", /*i*/"ла"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"ет", /*ere*/""}, /*Futur*/{"ет", ""}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"", /*ere*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU3NS (6, "3è. n. s.", "оно́", new String [][][][]{/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ЕТ", /*и*/"ИТ"}, /*Passé*/{/*e*/"ло", /*i*/"ло"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"ет", /*ere*/""}, /*Futur*/{"ет", ""}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"", /*ere*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU1P  (7, "1è. p.", "мы", new String [][][][]   {/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ЕМ", /*и*/"ИМ"}, /*Passé*/{/*e*/"ли", /*i*/"ли"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"ем", /*ere*/""}, /*Futur*/{"ем", ""}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"", /*ere*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU2P  (8, "2è. p. : ", "вы", new String [][][][]{/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ЕТЕ", /*и*/"ИТЕ"}, /*Passé*/{/*e*/"ли", /*i*/"ли"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"ете", /*ere*/""}, /*Futur*/{"ете", ""}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"", /*ere*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}),
        RU3P  (9, "3è. f. p.", "они́", new String [][][][]{/*Indicatif*/{/*Imperfectif*/{/*Présent*/{/*e*/"ЮТ", /*и*/"ЯТ"}, /*Passé*/{/*e*/"ли", /*i*/"ли"}, /*Futur*/{"", ""}}, /*Perfectif*/{{,}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"ют", ""}}}, /*Impératif*/{/*Imperfectif*/{/*Présent*/{/*er*/"", /*ir*/""}, /*Imparfait*/{/*er*/"", /*ere*/""}, /*Futur*/{"", "", ""}}, /*Conditionnel*/{/*Présent*/{/*er*/"ereient/eroient", /*ere*/"ei"}, /*Imparfait*/{/*ere*/"", /*ere*/""}, /*Futur*/{"", ""}}}, /*Conditionnel*/{/*Imperfectif*/{/*Présent*/{/*e*/"", /*ere*/""}, /*Passé*/{/*e*/"", /*ere*/""}, /*Futur*/{"", ""}}}}) ;
        private final int ip ;                                                  // indice de personne 0, 1, 2,...
        public final String lp ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String lap ;                                              // PP arabeu
        public final String [/*mode*/][/*aspect*/][/*temps*/][] suffixes ;                                     // suffixe par schème
        RUP(int ip, String libpers, String lap, String [][][][] suffixes) { this.ip = ip ; this.lp = libpers ; this.lap    = lap ; this.suffixes = suffixes ; }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lap() { return lap; }
        public static RUP l2P(String name) {
            RUP result = null;
            for (RUP personne : RUP.values()) if (personne.lp().equals(name)) { result = personne;  break;  }
            return result;
        }       //    public String ppiyi(ConjTempsAraMM.ART it) { return PairePS[it.it()][PRÉFIXE] ; }
    }                                                                           //RUP
    
    private static final String CYRILLIC_TO_LATIN = "Cyrillic-Latin", NOMPOLICE = "Times New Roman" ;
    private static final Transliterator cyToLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
    String forme[] = new String [5] ;
    public static Conjugueur.M.ConjugRu.RUC ig ; public static Conjugueur.M.ConjugRu.RUA ia   ;                                                // N° de groupe
    private final boolean DEBUG = false ;
}
