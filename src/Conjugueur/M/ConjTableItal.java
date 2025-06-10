/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import static Conjugueur.M.ConjTable.inbLignes;
import static Conjugueur.M.ConjTable.nbcons;
import Conjugueur.V.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class ConjTableItal extends ConjTable {
    /**
     * Fonction : Constitue le panneau d'affichage de la conjugaison
     * Algorithme : 
     * 1. Récupère l'éventuelle déclinaison
     * 2. Détermine le N° de groupe du verbe (0, 1 ou 2)
    */
    @Override
    public void MAJConj (Conjugueur.C.Conjug contrôleur) {
       if (DEBUG) { System.out.println ("ConjTableItal::MAJConj, Verbe = "+ ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif") +", Conjugueur.M.ConjugItalM.T.i2T(contrôleur.model.getTemps()).toString() = " +Conjugueur.M.ConjugItal.T.i2T(contrôleur.model.getTemps()).toString()) ; }
        this.contrôleur = contrôleur ; 
        try {
            vider();
            déclinaisonChildren = ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
            switch (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").substring(ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 3)) {
                case "are" -> {  ig =  VERBENARE ; }
                case "ere" -> {  ig =  VERBENERE ; }
                case "ire" -> {  ig =  VERBENIRE ; }
            }                                                                       // switch
            switch (Conjugueur.M.ConjugItal.M.i2M(contrôleur.model.getMode())) {                                             // mode
                case Impératif -> {                                                     // Impératif
                    CopieRacinePlusSuffImpé (ITP.IT2S, AjouterPers(ITP.IT2S)) ;
                    CopieRacinePlusSuffImpé (ITP.IT1P, AjouterPers(ITP.IT1P)) ;
                    CopieRacinePlusSuffImpé (ITP.IT2P, AjouterPers(ITP.IT2P));
                }                                                               // mode Impératif
                case Indicatif -> {                                                     // Indicatif
                    switch (Conjugueur.M.ConjugItal.T.i2T(contrôleur.model.getTemps())) {                                       // temps
                        case Futur, Imparfait, Présent -> {
                            for (ITP personne : ITP.values()) {
                                if(!déclinaisonChildren.isEmpty() && Testir(Conjugueur.M.ConjugItal.M.Indicatif.toString(), personne.lp()))
                                    CopierIrrégularités (Conjugueur.M.ConjugItal.M.Indicatif.toString(), personne) ; 
                                else {                                  // verbe régulier
                                    nbcons = 0 ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugItal.ITALIEN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerIndicatifRégulièrement (personne) ;    
                                }                                       // verbe régulier
                            }                                           // ttes les personnes
                        }                                                       // Présent
                        case Gérondif -> {
                            for (ITP personne : ITP.values()) {                 // pour ttes les personnes                   
                                AjouterPers(personne) ;
                                for (char c : ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").substring(0, ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 3).toCharArray()) {
                                    Copier(personne.ip(), String.valueOf(c), DéclinaisonStyledDoc.stylera);
                                } 
                                switch (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").substring(ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 3)) {
                                    case "are" -> Copier(personne.ip(), "ando", DéclinaisonStyledDoc.stylesu) ;
                                    case "ere", "ire" -> Copier(personne.ip(), "endo", DéclinaisonStyledDoc.stylesu) ;
                                }                                               // switch
                            } // ttes les personnes
                        }                                                       // Gérondif
                    }                                                           // switch (Conjugueur.M.ConjugItal.M.getTemps())
                // temps
                // Temps présent
                
                }                                                                // Indicatif
                case Conditionnel -> {                                                     // 
                    switch (Conjugueur.M.ConjugItal.T.i2T(contrôleur.model.getTemps())) {                                       // temps
                        case Présent -> {
                            for (ITP personne : ITP.values()) {
                                if(!déclinaisonChildren.isEmpty() && Testir(Conjugueur.M.ConjugItal.M.Indicatif.toString(), personne.lp()))
                                    CopierIrrégularités (Conjugueur.M.ConjugItal.M.Indicatif.toString(), personne) ; 
                                else {                                  // verbe régulier
                                    nbcons = 0 ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugItal.ITALIEN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerConditionnelRégulièrement (personne) ;    
                                }                                       // verbe régulier
                            }                                           // ttes les personnes
                        }                                                       // Conditionnel
                    }                                                           // switch (Conjugueur.M.ConjugItal.M.getTemps())
                }                                                                // Conditionnel
                case Subjonctif -> {                                                     // Subjonctif
                    switch (Conjugueur.M.ConjugItal.T.i2T(contrôleur.model.getTemps())) {                                       // temps
                        case Présent -> {
                            for (ITP personne : ITP.values()) {
                                if(!déclinaisonChildren.isEmpty() && Testir(Conjugueur.M.ConjugItal.M.Subjonctif.toString(), personne.lp()))
                                    CopierIrrégularités (Conjugueur.M.ConjugItal.M.Subjonctif.toString(), personne) ; 
                                else {                                  // verbe régulier
                                    nbcons = 0 ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugItal.ITALIEN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerIndicatifRégulièrement (personne) ;    
                                }                                       // verbe régulier
                            }                                           // ttes les personnes
                        }                                                       // Présent
                    }                                                           // switch (Conjugueur.M.ConjugItal.M.getTemps())
                }                                                               // Subjonctif                                                             // Conditionnel
            }                                                                   // mode
        } catch (BadLocationException ble) {        }
    }                                                                           // updateTextPaneConj
        /**
     * Fonction : Ajoute une personne spécifiée par l'indice "ipersonne" : 1, 2, ....
     * Algorithme : insère une ligne ds le tableau de conjugaison "ConjugM.conjTableM.tabLigneConj" et en 1ère colonne "personne" le libellé passé en paramètre
    */
    private int AjouterPers (ITP personne){
//        if (DEBUG) System.out.println("ConjugItal::AjouterPers, personne = " + personne) ;
        return AjouterLigne(personne.ip(), personne.lp(), JFrameConjugItal.FRANÇAIS, StyleConstants.ALIGN_LEFT) ;
    }                                                                            // AjouterPers  
// AjouterPers  

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
    public void CopierIrrégularités(String mode, ITP personne) throws BadLocationException {        
    //    if (DEBUG) System.out.println("ConjugItal::CopierIrrégularités, mode = " + mode+ ", temps = " + Conjugueur.M.ConjugItal.M.getTemps().toString()+", personne = "+ personne) ;
        for (int i = 0 ; i < déclinaisonChildren.size(); i++) {                  // Pour ttes les déclinaisons
            if (   (déclinaisonChildren.get(i).getAttribute("temps").getValue().compareTo(Conjugueur.M.ConjugItal.T.i2T(contrôleur.model.getTemps()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("mode").getValue().compareTo(Conjugueur.M.ConjugItal.M.i2M(contrôleur.model.getMode()).toString()) == 0)
                && (déclinaisonChildren.get(i).getAttribute("personne").getValue().compareTo(personne.lp()) == 0)    ) {            
                inbLignes = AjouterLigne(ITP.findByName(déclinaisonChildren.get(i).getAttribute("personne").getValue()).ip(), ITP.findByName(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lp(), JFrameConjugItal.ITALIEN, StyleConstants.ALIGN_RIGHT) ;
                Copier(inbLignes, ITP.findByName(déclinaisonChildren.get(i).getAttribute("personne").getValue()).lip(), DéclinaisonStyledDoc.stylepp); Espace(inbLignes);
                Copier(inbLignes, déclinaisonChildren.get(i).getText(), DéclinaisonStyledDoc.stylesu);
            } 
        }                                                                       // Pour ttes les irrégularités
    }
    // CopieRacine
     // CopierIrrégularités

        private void CopieRacinePlusSuffImpé(ITP personne, int ipersonne) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugItal::CopieRacinePlusSuffImpé, personne = " +personne+ " ipersonne = " + ipersonne) ;
        CopieRacine (ipersonne) ;
        CopierSuffImpé (personne, ipersonne) ;
    }                                                                           // CopieRacine 
        private void CopieRacine(int ipersonne) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugItal::CopieRacine, ipersonne = " + ipersonne) ;
        for (char c : ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").substring(0, ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 3).toCharArray()) {
            Copier(ipersonne, String.valueOf(c), DéclinaisonStyledDoc.stylera);
        }                                                                       // pour ts les caractères
    }                                                                           // CopieRacine
        private void CopierSuffImpé(ITP personne, int ipersonne) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugItal::CopierSuffImpé, personne = " + personne + ", ipersonne = " + ipersonne) ;
        switch (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").substring(ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 3)) {
            case "are" -> { 
                switch (personne.lp()) {
                    case "2è. s." -> Copier(ipersonne, "a", DéclinaisonStyledDoc.stylesu);
                    case "1è. p." -> Copier(ipersonne, "iamo", DéclinaisonStyledDoc.stylesu);
                    case "2è. p." -> Copier(ipersonne, "ate", DéclinaisonStyledDoc.stylesu);
                }                                       // switch (personne.lp())
            }
            case "ere" -> { 
                switch (personne.lp()) {
                    case "2è. s." -> Copier(ipersonne, "i", DéclinaisonStyledDoc.stylesu);
                    case "1è. p." -> Copier(ipersonne, "iamo", DéclinaisonStyledDoc.stylesu);
                    case "2è. p." -> Copier(ipersonne, "ete", DéclinaisonStyledDoc.stylesu) ;
                }                                       // switch (personne.lp())
            }
            case "ire" -> { 
                switch (personne.lp()) {
                    case "2è. s." -> Copier(ipersonne, "i", DéclinaisonStyledDoc.stylesu);
                    case "1è. p." -> Copier(ipersonne, "iamo", DéclinaisonStyledDoc.stylesu);
                    case "2è. p." -> Copier(ipersonne, "ite", DéclinaisonStyledDoc.stylesu) ;
                }                                       // switch (personne.lp())
            }                                                                   // case "ire"
        }                                                                       // switch groupe
    }                                                                           // CopierSuffImpé
    /**
     * @param ig indice de groupe 1, 2 ou 3
    * Algorithme
    * 1. Copier le libellé du Pronom Personnel
    * 2. Si le préfixe existe, l'ajouter
    */
    private void DéclinerConditionnelRégulièrement(ITP personne) throws BadLocationException {
//        if (DEBUG) System.out.println("ConjugItal::DéclinerConditionnelRégulièrement, personne = " + personne.lp+", groupe = "+ig+", Conjugueur.M.ConjugItal.Mode = "+Conjugueur.M.ConjugItal.M.i2M(contrôleur.model.getMode())+", Conjugueur.M.ConjugItal.M.getTemps() = "+contrôleur.model.getIndVerbe().M.getTemps()) ;
        Copier(inbLignes, personne.lip(), DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;
        switch (ig) {
            case VERBENARE -> {
                for (char c : (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).substring(0, ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length()- 3).toCharArray()){
                    Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                }                                                    // pour ts les caractères
                if  (    (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").charAt(ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 4) == 'c')
                        || (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").charAt(ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 4) == 'g')) {
                    Copier(personne.ip(), "h", DéclinaisonStyledDoc.stylesu);
                } 
            Copier (inbLignes, "e", DéclinaisonStyledDoc.stylesu) ;            
            Copier (inbLignes, "r", DéclinaisonStyledDoc.stylera) ;
            }                                                                       // switch
            case VERBENERE, VERBENIRE -> { 
                if (Conjugueur.M.ConjugItal.M.i2M(contrôleur.model.getMode()) == Conjugueur.M.Conjug.M.Subjonctif) {
                    for (char c : (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).substring(0, ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length()- 3).toCharArray()){
                        Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                    }                                                    // pour ts les caractères                    
                }
                else for (char c : (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).substring(0, ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length()- 1).toCharArray()){
                    Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                }                                                    // pour ts les caractères
            }
        }
        if (!personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig].isEmpty())  Suffixer (inbLignes, personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig]) ;
    }                                                                           // DéclinerConditionnelRégulièrement

    /**
     * Fonction : remplit la colonne de déclinaisons régulière du mode indicatif
     * @param ig indice de groupe 1, 2 ou 3
    * Algorithme
    * 1. Copier le libellé du Pronom Personnel
    * 2. Si le préfixe existe, l'ajouter
    */
    private void DéclinerIndicatifRégulièrement(ITP personne) throws BadLocationException {
        if (DEBUG) System.out.println("ConjTableItal::DéclinerIndicatifRégulièrement, personne = " + personne.lp+", groupe = "+ig) ;
        Copier(inbLignes, personne.lip(), DéclinaisonStyledDoc.stylepp); Espace (inbLignes) ;

        for (char c : (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif")).substring(0, ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length()- 3).toCharArray()){
    //        if (Consonne(c)) {                                // le caractère courant est une consonne
      //          nbcons++ ;
                Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
//                Copier (inbLignes, Character.toString(personne.accent(temps, mode, (short)(nbcons-1))), DéclinaisonStyledDoc.stylesu) ;
//            }                                                 // le caractère courant était une consonne
        }                                                    // pour ts les caractères
        if ((ig == VERBENARE) && ((personne.ip == 1) || (personne.ip == 3)) &&(   (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").charAt(ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 4) == 'c')
                                                            || (ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").charAt(ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue("infinitif").length() - 4) == 'g'))) {
            Copier(personne.ip(), "h", DéclinaisonStyledDoc.stylesu);
        } else
        if ((ig == VERBENIRE) && !ConjugItal.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("isc").isEmpty() && ((personne.ip == 0)||(personne.ip == 1)||(personne.ip == 2)||(personne.ip == 5)))
            Copier(personne.ip(),  "isc" , DéclinaisonStyledDoc.stylesu) ;
        
        if (!personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig].isEmpty())  Suffixer (inbLignes, personne.suffixes [contrôleur.model.getMode()][contrôleur.model.getTemps()][ig]) ;
//        jTabConj.ColumnAjust(1) ;

     } 



    

    public boolean Testir(String mode, String lpers) {
        boolean ir = false;
        for (int irrégularités = 0 ; irrégularités < déclinaisonChildren.size(); irrégularités++) {    // Pour ttes les irrégularités
            if (   (déclinaisonChildren.get(irrégularités).getAttribute("temps").getValue().compareTo(Conjugueur.M.ConjugItal.T.i2T(contrôleur.model.getTemps()).toString()) == 0)
                && (déclinaisonChildren.get(irrégularités).getAttribute("mode").getValue().compareTo(mode) == 0)
                && (déclinaisonChildren.get(irrégularités).getAttribute("personne").getValue().compareTo(lpers) == 0)    ) {
                ir = true ; break ;
            }
        }                                                                       // Pour ttes les irrégularités
//        if (DEBUG) System.out.println("ConjTableItal::Testir," + (ir ? " irrégularité " : " régularité ") + "pour mode = " + mode+", temps = "+Conjugueur.M.ConjugItal.M.getTemps()+", lpers = "+lpers) ;
        return ir ; 
    }                                                                           // Testir 
    private enum ITG {are, ere, ire ; } 
    
    // constantes dénissant les personnes   
    public enum ITP {
        IT1S(0, "1è. s.", "io", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"o", /*ere*/"o", /*ire*/"o"}, /*Imparfait*/{/*are*/"avo", /*ere*/"evo", "ivo"}, /*Futur*/{"erò", "erò", "irò"}}, /*Conditionnel*/{/*Présent*/{/*are*/"ei", /*ere*/"ei", /*ire*/"ei"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Subjonctif*/{/*Présent*/{/*are*/"i", /*ere*/"a", /*ire*/"a"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        IT2S(1, "2è. s.", "tu", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"i", /*ere*/"i", /*ire*/"i"}, /*Imparfait*/{/*are*/"avi", /*ere*/"evi", "ivi"}, /*Futur*/{"erai", "erai", "irai"}}, /*Conditionnel*/{/*Présent*/{/*are*/"esti", /*ere*/"esti", /*ire*/"esti"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Subjonctif*/{/*Présent*/{/*are*/"i", /*ere*/"a", /*ire*/"a"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        IT3S(2, "3è. s.", "lui/egli/esso/lei/ella/essa", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"a", /*ere*/"e", /*ire*/"e"}, /*Imparfait*/{"ava", "eva", "iva"}, /*Futur*/{"irà", "irà", "irò"}}, /*Conditionnel*/{/*Présent*/{/*are*/"ebbe", /*ere*/"ebbe", /*ire*/"ebbe"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Subjonctif*/{/*Présent*/{/*are*/"i", /*ere*/"a", /*ire*/"a"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        IT1P(3, "1è. p.", "noi", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"iamo", /*ere*/"iamo", /*ire*/"iamo"}, /*Imparfait*/{"avamo", "evamo", "ivamo"}, /*Futur*/{"eremo", "eremo", "iremo"}}, /*Conditionnel*/{/*Présent*/{/*are*/"emmo", /*ere*/"emmo", /*ire*/"emmo"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Subjonctif*/{/*Présent*/{/*are*/"iamo", /*ere*/"iamo", /*ire*/"iamo"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        IT2P(4, "2è. p.", "voi", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"ate", /*ere*/"ete", /*ire*/"ite"}, /*Imparfait*/{"avate", "evate", "ivate"}, /*Futur*/{"erete", "erete", "irete"}}, /*Conditionnel*/{/*Présent*/{/*are*/"este", /*ere*/"este", /*ire*/"este"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Subjonctif*/{/*Présent*/{/*are*/"iate", /*ere*/"iate", /*ire*/"iate"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}}),
        IT3P(5, "3è. p.", "loro/essi/esse", new String [][][]{/*Indicatif*/{/*Présent*/{/*are*/"ano", /*ere*/"ono", /*ire*/"ono"}, /*Imparfait*/{"avano", "evano", "ivano"}, /*Futur*/{"eranno", "eranno", "iranno"}}, /*Conditionnel*/{/*Présent*/{/*are*/"ebbero", /*ere*/"ebbero", /*ire*/"ebbero"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}, /*Subjonctif*/{/*Présent*/{/*are*/"ino", /*ere*/"ano", /*ire*/"ano"}, /*Imparfait*/{/*are*/"", /*ere*/"", ""}, /*Futur*/{"", "", ""}}});
        //Instance variable
        public final int ip;
        public final String lp;
        private final String lip;
        public final String [][][] suffixes ;                                     // préfixe et suffixe par schème

    /** Constructeur de l'enum
     * @param ipers indice de personne 0, 1, 2, 3...
     * @param libpers libellé de personne "1è. s.", "2è. m. s.", "2è. f. s.", "3è. m. s"....
     * @param lip libellé de personne en italien "io", "tu", "lui/egli/esso/lei/ella/essa"
     */
        ITP(int ipers, String libpers, String lip, String [][][] suffixes) {
            this.ip = ipers ; this.lp = libpers ; this.lip = lip ; this.suffixes = suffixes ;
        }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lip() { return lip; }
        public static ITP findByName(String name) {
            ITP result = null;
            for (ITP personne : ITP.values()) if (personne.lp().equals(name)) { result = personne;  break;  }
            return result;
        }                                                                       //findByName
    }                                                                           // ITP            
    public static final short VERBENARE = 0, VERBENERE = 1, VERBENIRE = 2 ;
    public static short ig = 0 ;                                                      // N° de groupe
    private Conjugueur.C.Conjug contrôleur ;

    private static final boolean DEBUG = false;    
}
