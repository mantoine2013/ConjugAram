/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import Conjugueur.M.*;
import com.ibm.icu.text.Transliterator;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import java.awt.GraphicsEnvironment;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public final class StyleChanger extends JPanel implements ActionListener {


    private final Conjugueur.M.SortedArrayList<org.jdom2.Element> jComboBoxVerbes = new Conjugueur.M.SortedArrayList<>();
    @SuppressWarnings("unchecked")
    private final ConjTable conjTableM = new ConjTable();
    private final JTable jTabConj = new JTable(conjTableM) ;

    @SuppressWarnings("unchecked")
    private StyleChanger () throws BadLocationException {
//        jPanelÉcriture.add(jComboBoxÉcriture) ;        jComboBoxÉcriture.addActionListener(this);
        Map attributes = SYRIAC.getAttributes(); attributes.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON); SYRIAC = SYRIAC.deriveFont(attributes);
//        jComboBoxVerbe.setRenderer(new ComboBoxVerbeRenderer(SYRIAC, ComponentOrientation.RIGHT_TO_LEFT));
        jComboBoxVerbe.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jComboBoxVerbe.setFont(SYRIAC);
   
        
        jTabConj.getColumnModel().getColumn(1).setCellRenderer(new DéclinaisonCellRenderer(HÉBREU, ComponentOrientation.RIGHT_TO_LEFT));

//        add(jPanelÉcriture) ;
//        add(jComboBoxVerbe) ;
        add(jTabConj);
        jComboBoxÉcriture.setSelectedItem(selectedIndÉcriture);
        inbLignes = conjTableM.AjouterLigne(SYP.SY1S.ip(), SYP.SY1S.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
        conjTableM.Copier(inbLignes, "ܩܶܛܠܶܬ ", DéclinaisonStyledDoc.stylepr); conjTableM.Espace (0) ;
        conjTableM.AjouterTranslittération(inbLignes, syriacToLatinTrans) ;
        inbLignes = conjTableM.AjouterLigne(SYP.SY1S.ip(), SYP.SY1S.lp(), HÉBREU, StyleConstants.ALIGN_RIGHT) ;
//        conjTableM.Copier(inbLignes, SYP.SY1S.lhp(), DéclinaisonStyledDoc.stylepp); conjTableM.Espace (inbLignes) ;
        if (!SYP.SY1S.ppiyi(SYS.Peal).isEmpty()) conjTableM.Préfixer(inbLignes, SYP.SY1S.ppiyi(SYS.Peal)) ;

        for (char c : "ܩܛܠ".toCharArray()){
            if (Consonne(c)) {                                // le caractère courant est une consonne
                nbcons++ ;
                conjTableM.Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                conjTableM.Copier (inbLignes, Character.toString(SYP.SY1S.accent(SYS.Peal, (short)(nbcons-1))), DéclinaisonStyledDoc.stylesu) ;
            }                                                 // le caractère courant était une consonne
        }                                                    // pour ts les caractères
        if (!SYP.SY1S.spip(SYS.Peal).isEmpty())  conjTableM.Suffixer (inbLignes, SYP.SY1S.spip(SYS.Peal)) ;
        conjTableM.AjouterTranslittération(inbLignes, syriacToLatinTrans) ;
        updateRowHeights() ;   resizeColumnWidth() ;     
}
    
    @Override
    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {
        if (DEBUG) System.out.println("StyleChanger:actionPerformed, ((SYÉ)jComboBoxÉcriture.getSelectedItem()).lp() = "+((SYÉ)jComboBoxÉcriture.getSelectedItem()).lp()+", jComboBoxVerbe.getFontMetrics(jComboBoxVerbe.getFont()) = "+jComboBoxVerbe.getFontMetrics(jComboBoxVerbe.getFont())) ;
        if (conjTableM.tab.isEmpty()) return ;
        selectedIndÉcriture = (SYÉ)jComboBoxÉcriture.getSelectedItem() ;
        SYRIAC = new Font (selectedIndÉcriture.lp(), Font.PLAIN, TAILLEPOLICEDECLINAISONS) ;
        Map attributes = SYRIAC.getAttributes(); attributes.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON); SYRIAC = SYRIAC.deriveFont(attributes);
       jComboBoxVerbe.setFont(SYRIAC);
        conjTableM.Polichange(selectedIndÉcriture.lp());
    }
    private void updateRowHeights() {
        for (int row = 0; row < jTabConj.getRowCount(); row++)  {
            int rowHeight = jTabConj.getRowHeight();

            for (int column = 0; column < jTabConj.getColumnCount(); column++)        {
                Component comp = jTabConj.prepareRenderer(jTabConj.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

        jTabConj.setRowHeight(row, rowHeight);
        }
    }                                                                           // updateRowHeights 
    private boolean Consonne (char c) {
      if ((c >= 'ܐ') && (c <= 'ܬ')) return true ; 
      return false ;
    }                                                                            // Consonne    
    
    public void resizeColumnWidth() {
        final TableColumnModel columnModel = jTabConj.getColumnModel();
        for (int column = 0; column < jTabConj.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < jTabConj.getRowCount(); row++) {
                TableCellRenderer renderer = jTabConj.getCellRenderer(row, column);
                Component comp = jTabConj.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }                                                                   //pour ttes les rangées
            if(width > 300)  width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }                                                                       // pour ttes les col
    }                                                                           // resizeColumnWidth
    public static void main(String[] args) {
        boolean found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals("Serto Jerusalem")) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police Serto Jerusalem n'est pas installée !") ; return ;    }
        found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals("Estrangelo Edessa")) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police Estrangelo Edessa n'est pas installée !") ; return ;    }

        javax.swing.SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Style Changer");
            try {                frame.setContentPane(new StyleChanger());            } catch (BadLocationException ex) {                Logger.getLogger(StyleChanger.class.getName()).log(Level.SEVERE, null, ex);            }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        });
    }


    private enum SYÉ {                                                          // écriture / police
        Serto("Serto Jerusalem"), Estrangela("Estrangelo Edessa") ;
        private final String lp ;

        SYÉ (String lp) { this.lp = lp ; }
        public String lp() { return lp ; }
    }                                                                           // SYÉ
    private int nbcons = 0 ;                                                    // compteur de consonnes

    
    @SuppressWarnings("unchecked")
    JComboBox<SYÉ> jComboBoxÉcriture = new JComboBox(new DefaultComboBoxModel<>(SYÉ.values())) ;
    SYÉ selectedIndÉcriture = SYÉ.Serto ;
    private final static short PRÉFIXE = 0 ;
    private final static short SUFFIXE = 1 ;
    public  final static String SYRIAC_TO_LATIN = "Syriac-Latin";
    private final Transliterator syriacToLatinTrans = Transliterator.getInstance(SYRIAC_TO_LATIN);

    private enum HMP {
        HM1S ((short)0, " 1è. s. : ", "אֲנִי") ; 

        //Instance variable
        private final int ip ;                                                  // indice de personne 0, 1, 2,...
        private final String lp ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String lhp ;                                              // PP hébreu

        //Constructor to initialize the instance variable
        HMP(int ipers, String lp, String libhpers) {
            this.ip     = ipers ; this.lp     = lp ; this.lhp    = libhpers ;
        }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lhp() { return lhp; }
    }                                                                           //HMP
    private enum SYT { Accompli(0), Inaccompli(1), PARTICIPE(2), IMPERATIF(3), INFINITIF(4) ; 
        private final int it ;                                                  // indice de personne 0, 1, 2,...
        SYT(int it) {this.it = it ;}
            public int it() { return it; }
    }                                                                           // SYT 
    private enum SYS {
        Peal((short)0, "Pe‘al"), Etpeel ((short)1,"Etpe‘el"), Pael((short)2,"Pa‘el"), Etpaal ((short)3,"Etpa‘al") ;
        private final String ls;
        private final short is;                                                   // indice de schème 0, 1, 2,...

        SYS (short is,String lschème) { this.is = is ; this.ls = lschème ; }
        public int is() { return is; }
        public String ls() { return ls; }
    }                                                                           // SYS        
        private enum SYP {
        SY1S  (0, " 1è. s. :",   "ܐܶܢܳܐ", new char [][]{/*Accompli*/{/*Pe‘al*/'\u0736', '\u0000', '\u0736'}}, new String [][]{{/*Pe‘al*/"", "ܬ݂"}} ),
        SY2MS (1, " 2è. m. s. :", "ܐܰܢ̱ܬ݁", new char [][]{/*Accompli*/{/*Pe‘al*/'\u0000', 'ܰ', '\u0000'}}, new String [][]{{/*Pe‘al*/"", "ܬ݁"}} ) ;

        private final int ip ;                                                  // indice de personne 0, 1, 2,...
        private final String lp ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String lhp ;                                              // PP hébreu
        private final String[][] PielAcco ;                                     // préfixe et suffixe par schème
        private final char[][] Accents ;                                        // Accents sur les consonnes par schème

    /** Constructeur de l'enum
     * @param ip indice de personne 0, 1, 2, 3...
     * @param libpers libellé de personne 1è. s., 2è. m. s., 2è. f. s., 3è. m. s....
     * @param Accents accents sur les 1ère, 2ème et 3ème consonne
     * @param PielAcco tableau 1ère dim : schème, 2ème dim : préfixe et suffixe
     */
        SYP(int ip, String libpers, String lhp, char [][] Accents, String [][] PielAcco) { this.ip = ip ; this.lp = libpers ; this.lhp    = lhp ; this.Accents = Accents ; this.PielAcco = PielAcco ;    }
        public int ip() { return ip; }
        public String lp() { return lp; }
        public String lhp() { return lhp; }
        public String spip(SYS is) { return PielAcco [is.is()][SUFFIXE] ; }          // renvoie le suffixe d'un schème donné
        public char accent (SYS is, short i) {
//            if (DEBUG) System.out.println("ConjugSyri::SYP Accents ["+it.it()+"]["+is.is()+"]["+i+"] = " + (int) Accents [it.it()][is.is()][i]) ;          
            return Accents [is.is()][i] ;    }
        public String ppiyi(SYS s) { return PielAcco[s.is()][PRÉFIXE] ; }
        public static SYP findByName(String name) {
            SYP result = null;
            for (SYP personne : SYP.values()) if (personne.lp().equals(name)) { result = personne;  break;  }
            return result;
        }                                                                       //findByName
    }                                                                           //SYP

    private static final String XMLSOURCE = "ConjugSemi.xml" ;
    @SuppressWarnings("unchecked")
    private final ModèleMenu1 comboBoxVerbemodel = new ModèleMenu1(XMLSOURCE, "versy", "infinitif") ; 
    @SuppressWarnings("unchecked")
    private final JComboBox<String> jComboBoxVerbe = new JComboBox<>(comboBoxVerbemodel);  

    private final JPanel jPanelÉcriture = new JPanel() ;
    private static final short TAILLEPOLICEDECLINAISONS = 35 ;
    private int inbLignes = 0 ;                                                 // indice de ligne

    private static final String NOMPOLICEDECLINAISONS = "Serto Jerusalem" ;             // police pour la col droite
    private final Font HÉBREU = new Font (selectedIndÉcriture.lp(), Font.PLAIN, TAILLEPOLICEDECLINAISONS);
    public static Font SYRIAC = new Font (NOMPOLICEDECLINAISONS, Font.PLAIN, TAILLEPOLICEDECLINAISONS);
    public static final boolean DEBUG = true;
}
