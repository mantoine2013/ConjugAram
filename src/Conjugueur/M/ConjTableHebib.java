/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.*;
import Conjugueur.ConjugHebib;
import Conjugueur.V.DéclinaisonStyledDoc;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import com.ibm.icu.text.Transliterator;
import java.awt.Font;

public class ConjTableHebib extends ConjTable  {

    /**
     * Fonction : MAJConj
     * Fonctions appelantes : Conjugueur.Vue.JFrameConjugHebib::JFrameConjugHebi
     * Algorithme : 
     * 1. Remettre le tableau de conjugaison à zéro
     * 2. Pour ttes les personnes faire
     * 2.1 Si le verbe est régulier
     * 2.1.1 S'aiguiller selon le mode
     * 2.1.1.1 Mode Yiqtol
     * 2.1.1.1.1 Préfixer suivant la personne en appelant "ppiyi"
     * 2.1.1.1.2 Si le verbe est régulier alors
     * 2.1.1.1.2.1  Créer une ligne pour chaque déclinaison et y placer les libellés des personnes à conjuguer : masc sing, masc plur, fém sing, fém plur en appelant la fction "AjouterPersonnes ()"
     * 2.1.1.1.2.2 Pour ts les caractères de l'infinitif faire
     * 2.1.1.1.2.2.1 Si c'est une consonne alors
     * 2.1.1.1.2.2.1.1 Incrémenter compteur de consonnes
     * 2.1.1.1.2.2.1.2 Insérer un "וֹ" en 2è position s'il n'est pas déjà présent
     * 2.1.1.1.2.2.1.3 Copier le caractère en appelant "CopierCaractèRacine"
     * 2.1.1.1.3 Suffixer suivant la personne en appelant "ppiyi"
     */
    @Override
    public void MAJConj(Conjugueur.C.Conjug contrôleur) {
       if (DEBUG) System.out.println ("Conjugueur.M.ConjTableHebib::MAJConj, Schème = " + Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()) + ", selectedIndVerbe = " + Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ATTRIBUTVERBE) + ", Mode = " + Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).toString()) ;
        this.contrôleur = contrôleur ; 
        switch (Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème())) {                                           // Schème
                        case Qal -> { JFrameConjug.jLabelVoix.setText("Voix active"); JFrameConjugHebib.JLabelForme.setText("Forme simple") ;  }                                                               // Qal
                        case Piel -> { JFrameConjug.jLabelVoix.setText("Voix active"); JFrameConjugHebib.JLabelForme.setText("Forme intensive") ; }                                                               // Piel
                        case Hifil -> { JFrameConjug.jLabelVoix.setText("Voix active"); JFrameConjugHebib.JLabelForme.setText("Forme causative") ;}                                                               // Hifil
                        case Pual -> { JFrameConjug.jLabelVoix.setText("Voix passive"); JFrameConjugHebib.JLabelForme.setText("Forme intensive") ;}                                                               // Pual
                        case Hofal -> { JFrameConjug.jLabelVoix.setText("Voix passive"); JFrameConjugHebib.JLabelForme.setText("Forme causative") ;}                                                               // Hofal
                        case Nifal -> {JFrameConjug.jLabelVoix.setText("Voix réfléchie"); JFrameConjugHebib.JLabelForme.setText("Forme simple") ;}                                                               // Nifal
                        case Hitpael -> {JFrameConjug.jLabelVoix.setText("Voix réfléchie"); JFrameConjugHebib.JLabelForme.setText("Forme intensive") ;}                                                               // Hitpael
        }                                                                   // Schème
       try {
            vider();
            déclinaisonChildren = Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
            switch (Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode())) {                                  // mode
                case Qatal -> {
                    if (déclinaisonChildren.isEmpty()) {             // verbe régulier
                        for (HBP personne : HBP.values()) {                             // pour ttes les personnes
                            inbLignes = AjouterLigne(personne.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ; nbcons = 0 ;   
                            Préfixer(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).pairePS[PRÉFIXE]) ;
                               for (char c : Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ATTRIBUTVERBE).toCharArray()){
                                    if (Consonne(c)) {                                // le caractère courant est une consonne
                                        nbcons++ ;
                                        Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                                        Copier (Character.toString(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).accents [(short)(nbcons-1)]), DéclinaisonStyledDoc.stylesu) ;
//                                        Copier (Character.toString(personne.accents [Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.getMode().im()] [(short)(nbcons-1)]), DéclinaisonStyledDoc.stylesu) ;
                                    }                                                 // le caractère courant était une consonne
                                }                                                    // pour ts les caractères
                        }                                               // verbe régulier
                                AjouterTranslittération(hebToLatinTrans) ;
                    }                                           // ttes les personnes
                    else {                                              // verbe irrégulier
                          CopierIrrégularités () ;
                    }                                                   // verbe irrégulier
                }
                case Yiqtol -> { 
                    JFrameConjug.jLabelMode.setText("Mode Indicatif");
                    if (déclinaisonChildren.isEmpty()) {             // verbe régulier
                        for (HBP personne : HBP.values()) {                             // pour ttes les personnes
                            inbLignes = AjouterLigne(personne.ip(), personne.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ; nbcons = 0 ;   
                            if (!"0".equals(personne.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).im()] [PRÉFIXE])) {
                                Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
                                if (!personne.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).im()] [PRÉFIXE].isEmpty()) Préfixer(inbLignes, personne.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).im()] [PRÉFIXE]) ;

                                for (char c : Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ATTRIBUTVERBE).toCharArray()){
                                    if (Consonne(c)) {                                // le caractère courant est une consonne
                                        nbcons++ ;
                                        Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                                        Copier (inbLignes, Character.toString(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).accents [(short)(nbcons-1)]), DéclinaisonStyledDoc.stylesu) ;
                                        Copier (inbLignes, Character.toString(personne.accents [Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).im()] [(short)(nbcons-1)]), DéclinaisonStyledDoc.stylesu) ;
                                    }                                                 // le caractère courant était une consonne
                                }                                                    // pour ts les caractères
                                if (!personne.pairePS [Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).im()] [SUFFIXE].isEmpty())  Suffixer (inbLignes, personne.pairePS [Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).im()] [SUFFIXE]) ;
                                AjouterTranslittération(inbLignes, hebToLatinTrans) ;
                            }
                        }                                           // ttes les personnes
                    }                                               // verbe régulier
                    else {                                              // verbe irrégulier
                          CopierIrrégularités () ;
                    }                                                   // verbe irrégulier
/*                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()){             // verbe régulier
                        inbLignes = AjouterLigne(personne.ip(), personne.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes) ;
                        Préfixer(inbLignes, personne.pairePS[HBM.Yiqtol.im][PRÉFIXE]) ;
                            Yiqtol() ;
                            Suffixer (inbLignes, personne.spiyi(HBM.Yiqtol.im)) ;
                    }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebToLatinTrans) ;*/
                }                                                               // Yiqtol
                case Volitif -> {                                               // Volitif
                    for (HBP personne : HBP.values()) {                             // pour ttes les personnes
                        nbcons = 0 ;
                        if (déclinaisonChildren.isEmpty()){             // verbe régulier
                            inbLignes = AjouterLigne(personne.ip(), personne.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
                            Copier(inbLignes, personne.lhp(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes) ;
                            Préfixer(inbLignes, personne.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.Volitif.im()][PRÉFIXE]) ;
                            Suffixer (inbLignes, personne.spiyi(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is(), Conjugueur.M.ConjugHebib.HBM.Volitif.im())) ;
                        }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebToLatinTrans) ;
                    }                                           // ttes les personnes
                }                                                               // Volitif
                case Cohortatif -> {                                               // Cohortatif
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()){             // verbe régulier
                        inbLignes = AjouterLigne(HBP.HB1S.ip(), HBP.HB1S.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HBP.HB1S.lhp(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes) ;
                                    Préfixer(inbLignes, HBP.HB1S.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.Volitif.im()][PRÉFIXE]) ;
                            Yiqtol() ;
                                    Suffixer (inbLignes, HBP.HB1S.spiyi(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is(), Conjugueur.M.ConjugHebib.HBM.Volitif.im())) ;
                    }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebToLatinTrans) ;
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()){             // verbe régulier
                        inbLignes = AjouterLigne(HBP.HB1P.ip(), HBP.HB1P.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HBP.HB1P.lhp(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes) ;
                                    Préfixer(inbLignes, HBP.HB1P.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.Volitif.im()][PRÉFIXE]) ;
                            Yiqtol() ;
                                    Suffixer (inbLignes, HBP.HB1P.spiyi(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is(), Conjugueur.M.ConjugHebib.HBM.Volitif.im())) ;
                    }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebToLatinTrans) ;
                }                                                               // Cohortatif
                case Jussif -> {                                               // Jussif
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()){             // verbe régulier
                        inbLignes = AjouterLigne(HBP.HB3MS.ip(), HBP.HB3MS.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HBP.HB3MS.lhp(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes) ;
                                    Préfixer(inbLignes, HBP.HB3MS.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.Volitif.im()][PRÉFIXE]) ;
                            Yiqtol() ;
                                    Suffixer (inbLignes, HBP.HB3MS.spiyi(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is(), Conjugueur.M.ConjugHebib.HBM.Volitif.im())) ;
                    }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebToLatinTrans) ;
                    nbcons = 0 ;
                    if (déclinaisonChildren.isEmpty()){             // verbe régulier
                        inbLignes = AjouterLigne(HBP.HB3MP.ip(), HBP.HB3MP.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
                        Copier(inbLignes, HBP.HB3MP.lhp(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes) ;
                                    Préfixer(inbLignes, HBP.HB3MP.pairePS[Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is()][Conjugueur.M.ConjugHebib.HBM.Volitif.im()][PRÉFIXE]) ;
                            Yiqtol() ;
                                    Suffixer (inbLignes, HBP.HB3MP.spiyi(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).is(), Conjugueur.M.ConjugHebib.HBM.Volitif.im())) ;
                    }                                                   // verbe régulier
                    AjouterTranslittération(inbLignes, hebToLatinTrans) ;
                }                                                               // Jussif
                case InfCons -> {                                               // InfCons
                        inbLignes = AjouterLigne(0, "", HÉBREU, StyleConstants.ALIGN_RIGHT) ;
                        for (char c : Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ATTRIBUTVERBE).toCharArray()) {
                            if (Consonne(c)) {                          // le caractère courant est une consonne
                                nbcons++ ; 
                                Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                            }                                           // le caractère courant était une consonne
                        }                                                                       // pour ts les caractères
                }                                                               // InfCons
            }                                                           // mode
 /*               case Piel -> {                                                  // Piel
                                    Copier (inbLignes, Character.toString(SHEWA), DéclinaisonStyledDoc.stylesu) ;
                }            */                                                   // Piel

//             ColumnAjust(1) ;
        } catch (BadLocationException e) {}
    }                                                                           // updateTextPaneConj
    
    private void CopierIrrégularités () throws BadLocationException {
        for (int i = 0 ; i < déclinaisonChildren.size(); i++) {                  // Pour ttes les déclinaisons
            if (   (déclinaisonChildren.get(i).getAttribute("mode").getValue().compareTo(Conjugueur.M.ConjugHebib.HBM.i2M(contrôleur.model.getMode()).toString()) == 0)
//                && (déclinaisonChildren.get(i).getAttribute("forme").getValue().compareTo(forme) == 0)
                && (déclinaisonChildren.get(i).getAttribute("voix").getValue().compareTo(Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème()).toString()) == 0)    ) 
                AjouterLigne (déclinaisonChildren.get(i).getAttribute("personne").getValue(), déclinaisonChildren.get(i).getText()) ;
        }                                                                       // Pour ttes les déclinaisons
    }                                                                           // CopierIrrégularités

   /**
     * Fonction : Ajoute une ligne d'indice inbLignes
     * Algorithme :
     * 1 insère une ligne ds le tableau de conjugaison "ConjugM.conjTableM.tabLigneConj" et en 1ère colonne "personne" le libellé passé en paramètre
    */
    public void AjouterLigne (String personne, String forme) throws BadLocationException {
        AjouterPers(inbLignes, ConjugHebib.ETIPERS, HÉBREU, StyleConstants.ALIGN_RIGHT) ; ConjugHebib.ETIPERS[inbLignes] = personne + " : " ; 
        switch (personne) {
            case ETI_FR2MS : 
                Copier (inbLignes, HBPP2MS, DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case ETI_FR2FS : 
                Copier (inbLignes, HBPP2FS, DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "f. s." : 
                Copier (inbLignes, HBPP3MS, DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "1è. m. p." : 
                Copier (inbLignes, "אַתָה", DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "1è. p. p." : 
                Copier (inbLignes, "אַתָה", DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "1è. f. p." : 
                Copier (inbLignes, "אַתָה", DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "2è. m. p." : 
                Copier (inbLignes, "אַתָה", DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "2è. f. p." : 
                Copier (inbLignes, "אַתֶן", DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case ETI_FR3MS : 
                Copier (inbLignes, HBPP3MS, DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case ETI_FR3FS : 
                Copier (inbLignes, HBPP3FS, DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "3è. m. p." : 
                Copier (inbLignes, "אַתָה", DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu) ; break ;
            case "3è. f. p." : 
                Copier (inbLignes, "אַתָה", DéclinaisonStyledDoc.stylepp) ; Copier(forme, DéclinaisonStyledDoc.stylesu); break ;
            case "3è. p. p." : 
                Copier (inbLignes, "הֵם", DéclinaisonStyledDoc.stylepp) ; BarrOblique(inbLignes) ; Copier (inbLignes, "הֵן", DéclinaisonStyledDoc.stylepp) ;  Espace(inbLignes) ; Copier(forme, DéclinaisonStyledDoc.stylesu); break ;
        }                                                               // switch
        inbLignes++ ; 
    }                                                                           // AjouterLigne

    /**
     * Fonction : Renvoie True si le caractère passé en paramètre est une consonne
    */
    private boolean Consonne (char c) {
      return ((c >= 'א') && (c <= 'ת')) || (c == 'שׁ')|| (c == 'שׂ') ;
    }                                                                            // Consonne
    private boolean ConsonneFinale (char c) {
      return (c == 'ך') || (c == 'ם') || (c == 'ן') || (c == 'ף') || (c == 'ץ') ;
    }                                                                            // Consonne
    private void Yiqtol () throws BadLocationException {
        if (DEBUG) System.out.println("ConjugHebM:Yiqtol, cbVerbeM.get(Verbe).getAttributeValue(\"infinitif\") = "+Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ATTRIBUTVERBE)) ;
        for (char c : Conjugueur.M.ConjugHebib.cbVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ATTRIBUTVERBE).toCharArray()) {
            if (Consonne(c)) {                          // le caractère courant est une consonne
                nbcons++ ; 
                Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;

//            Copier (inbLignes, selectedIndSchème.accent((short)(nbcons-1)), DéclinaisonStyledDoc.stylesu) ;
        /*                                            else if (nbcons == 2) { 
                                                switch (Conjugueur.M.ConjugHebib.HBS.i2S(contrôleur.model.getSchème())){                                           // Schème
                                                    case Piel -> {                                                  // Piel
                                                        Copier (inbLignes, Character.toString(DAGESH), DéclinaisonStyledDoc.stylesu) ;
                                                        Copier (inbLignes, Character.toString(ṢÉRÉ), DéclinaisonStyledDoc.stylesu) ;
                                                    }                                                               // Piel
                                                }                                                                   // Schème
                                            }             */                              // 2ème consonne
            }                                           // le caractère courant était une consonne
        }                                                                       // pour ts les caractères
    }                                                                           // Yiqtol

    public static final String HBPP2MS = "אַתָּה", HBPP2FS = "אַתְּ", HBPP3MS = "הוּא", HBPP3FS = "הִיא" ;    
    private final static char YOD = 'י' ;

    // constantes dénissant les terminaisons de noms   
    public enum HBN {
        HBMSA ((short)0, "Masc. Sg. abs.", ""),
        HBMSC ((short)1, "Masc. Sg. cstr.", ""),
        HBMPA ((short)2, "Masc. Pl. abs.", "ים"),
        HBMPC ((short)3, "Masc. Pl. cstr.", "י"),
        HBMDA ((short)4, "Masc. Du. abs.", "יִם"),
        HBMDC ((short)5, "Masc. Du. cstr.", "י"),
        HBFSA ((short)6, "Fém. Sg. abs.", "ָה"),
        HBFSC ((short)7, "Fém. Sg. cstr.", "ַת"),
        HBFPA ((short)8, "Fém. Pl. abs.", "וֹת"),
        HBFPC ((short)9, "Fém. Pl. cstr.", "וֹת"),
        HBFDA ((short)10, "Fém. Du. abs.", "יִם"),
        HBFDC ((short)11, "Fém. Du. cstr.", "י");
        private final short in;                                                 // indice de personne 0, 1, 2,...
        public final String ln;                                                // libellé de personne "1è. s.", "2è. m. s."
        private String term ;                                                   // terminaison du nom construit ou absolu
        //Constructor to initialize the instance variable
        HBN(short in, String ln, String term) { this.in = in ; this.ln = ln ; this.term = term; }
        public int in() { return in; }    
        public String ln() { return ln; }
        public String term() { return term; }
    }                                                                           // HBN    
    // constantes dénissant les personnes   
    public enum HBP {
        HB1S ((short)0, "1è. s.",   "אֲנִי", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u05B7','\u05B5','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"אֶ",""}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"אֲ",""}, {"א","ה"}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"אֶ",""}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"אֶ",""}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"אֶ",""}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"אֶ",""}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"אֶ",""}}}),
        HB2MS((short)1, "2è. m. s.", "אַתָה", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u05B7','\u05B5','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"תְּ",""}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"תִֶּ",""}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}}),
        HB2FS((short)2, "2è. f. s.", "אְת", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u05B7','\u05B0','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","י"}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"תְּ","ִי"}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","י"}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","י"}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","י"}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","י"}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","י"}}}),
        HB3MS((short)3, "3è. m. s.", "הוּא", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"יִ",""}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"יִ",""}, {String.valueOf(YOD),""}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"יִ",""}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"יִ",""}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"יִ",""}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"יִ",""}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"יִ",""}}}),
        HB3FS((short)4, "3è. f. s.", "הִיא", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"תִּ",""}}}),
        HB1P ((short)5, "1è. p.",   "אְַנַֽחְנוּ ", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"נִ",""}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"נִ",""}, {"נ","ה"}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"נִ",""}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"נִ",""}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"נִ",""}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"נִ",""}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"נִ",""}}}),
        HB2MP((short)6, "2è. m. p.", "אַתֵם ", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","וּ"}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","וּ"}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","וּ"}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","וּ"}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","וּ"}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","וּ"}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","וּ"}}}),
        HB2FP((short)7, "2è. f. p.", "אַתֶן ", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}}),
        HB3MP((short)8, "3è. m. p.", "הֵם", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"יִ","וּ"}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"יִ","וּ"}, {String.valueOf(YOD),"וּ"}}, {/*Hifil*/{/*Yiqtol*/"יִ","וּ"}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"יִ","וּ"}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"יִ","וּ"}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"יִ","וּ"}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"יִ","וּ"}}}),
        HB3FP((short)9, "3è. f. p.", "הֵן", new char [][][]{{/*Qal*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}, {/*Piel*/{/*Qatal*/'\u0000','\u0000','\u0000'}, {/*Yiqtol*/'\u0000','\u0000','\u0000'}}}, new String [][][]{{/*Qal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Piel*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Hifil*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Pual*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Hofal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Nifal*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}, {/*Hipael*/{/*Qatal*/}, {/*Yiqtol*/"תִּ","נָה"}}}) ;
        //Instance variable
        private final short ip;                                                 // indice de personne 0, 1, 2,...
        public final String lp;                                                // libellé de personne "1è. s.", "2è. m. s."
        private final String lhp ;                                              // PP hébreu
        public final String [][][] pairePS;
        public final char[][][] accents ;                                    // Accents sur les consonnes par schème
        
        //Constructor to initialize the instance variable
        HBP(short ip, String lp, String lhp, char [][][] accents, String [][][] pairePS) { this.ip = ip ; this.lp = lp ; this.lhp    = lhp ; this.accents = accents;  this.pairePS = pairePS ;        }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lhp() { return lhp; }
        public String spiyi(short is, short im) { return pairePS[is][im][JFrameConjugHebib.SUFFIXE] ; }
    }                                                                           // HBP    
    private static final short PRÉFIXE = 0, SUFFIXE = 1, TAILLEPOLICE = 25 ;
    private final static String MATRESLECTIONIS = "יוה", ATTRIBUTVERBE = "infinitif", ETI_FR2MS = "2è. m. s.", ETI_FR2FS = "2è. f. s.", ETI_FR3MS = "3è. m. s.", ETI_FR3FS = "3è. f. s.", HEBIB_TO_LATIN = "Hebrew-Latin", NOMPOLICE = "Times New Roman" ;
    private static final Font HÉBREU = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE);
    public static final Transliterator hebToLatinTrans = Transliterator.getInstance(HEBIB_TO_LATIN);
    private Conjugueur.C.Conjug contrôleur ;
    private static final boolean DEBUG = false;    
}
