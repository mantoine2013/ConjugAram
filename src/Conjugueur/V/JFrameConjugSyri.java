/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import Conjugueur.M.*;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;

public class JFrameConjugSyri extends JFrameConjug implements ActionListener {

    /**
     * Fction appelante : Conjugueur.C.ConjugSyri::ConjugSyri
     * @param contrôleur 
     */
    @SuppressWarnings("unchecked")
    public JFrameConjugSyri(Conjugueur.C.ConjugSyri contrôleur) {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugSyri::JFrameConjugSyri") ; }
        frame.setTitle("Conjugueur Syriaque V0.8");
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        jPanelAutreMot.add(jComboBoxAutMot);   jPanelDroit.add(jPanelAutreMot, java.awt.BorderLayout.NORTH);   jComboBoxAutMot.setActionCommand(AUTREMOT);    jComboBoxAutMot.addActionListener(this) ;
        jPanelÉtat.add(jComboBoxÉtat) ;     jPanelDroit.add(jPanelÉtat, java.awt.BorderLayout.EAST);    jComboBoxÉtat.setActionCommand(ÉTAT);jComboBoxÉtat.addActionListener(this);
        jTabDecl = new JTableau(Conjugueur.M.Conjug.declTableM) ;
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(SYRIAC, ComponentOrientation.RIGHT_TO_LEFT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jPanelÉcriture.add(jComboBoxÉcriture) ;          jComboBoxÉcriture.setActionCommand(POLICE) ; /* jComboBoxÉcriture.setSelectedItem(Écriture)*/; jPanelGauche.add(jPanelÉcriture, java.awt.BorderLayout.EAST); 
        jPanelSchème.add(jComboBoxSchème) ;                 jComboBoxSchème.setActionCommand(SCHEME) ; jPanelGauche.add(jPanelSchème, java.awt.BorderLayout.CENTER)  ; jComboBoxSchème.setSelectedIndex(contrôleur.model.getSchème()) ; jComboBoxSchème.addActionListener(this) ; // ItemizeComboSchèmes() ; 
        jPanelVerbe.add(jComboBoxVerbe) ; jPanelVerbe.add(jLabelVoix) ; jPanelVerbe.add(JLabelForme) ; jPanelVerbe.add(jButExport) ; jButExport.setActionCommand(EXPORT);jButExport.addActionListener(this) ; jComboBoxVerbe.setActionCommand(VERBE); jComboBoxVerbe.setSelectedIndex(contrôleur.model.getIndVerbe()) ; jPanelGauche.add(jPanelVerbe, java.awt.BorderLayout.NORTH);jComboBoxVerbe.addActionListener(this) ;
        jPanelTemps.add(jComboBoxTemps) ; jPanelGauche.add(jPanelTemps, java.awt.BorderLayout.WEST) ; jComboBoxTemps.setActionCommand(TEMPS); jComboBoxTemps.setSelectedIndex(contrôleur.model.getTemps()) ; jComboBoxTemps.addActionListener(this);
        jTabConj = new JTableau(Conjugueur.M.Conjug.conjTableM) ;
        jTabConj.setDefaultRenderer(String.class, new PersonneCellRenderer(FRANÇAIS)); // spécifie la classe de rendu pour le DéclinaisonStyledDoc
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(SYRIAC, ComponentOrientation.RIGHT_TO_LEFT)); // spécifie la classe de rendu pour le DéclinaisonStyledDoc
        jTabConj.resizeColumnWidth() ; 
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        Map attributes = SYRIAC.getAttributes(); attributes.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON); SYRIAC = SYRIAC.deriveFont(attributes);
        jComboBoxVerbe.setRenderer(new ComboBoxVerbeRenderer(SYRIAC, ComponentOrientation.RIGHT_TO_LEFT, Conjugueur.M.ConjugSyri.ATTRIBUT));
        jComboBoxVerbe.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jComboBoxVerbe.setFont(SYRIAC); 
        jComboBoxAutMot.setRenderer(new ComboBoxVerbeRenderer(SYRIAC, ComponentOrientation.RIGHT_TO_LEFT, Conjugueur.M.ConjugSyri.ATTRIBUT));        
        jComboBoxAutMot.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jComboBoxAutMot.setFont(SYRIAC); 
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanelGauche, jPanelDroit);
        splitPane.setResizeWeight(0.5);
        frame.add(splitPane, BorderLayout.CENTER);
        Conjugueur.M.Conjug.conjTableM.MAJConj(contrôleur) ;  Conjugueur.M.Conjug.declTableM.MAJDecl(contrôleur); 
    }    
    /** Listens to the combo box.
     * @param e changement de temps, de verbe, de schème ou de police
     */
    @Override
    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {
        getContrôleur().notifyVAChanged(e.getActionCommand(), ((JComboBox) e.getSource()).getSelectedIndex()) ;
        switch(e.getActionCommand()) {
//                case EXPORT -> ConjugSyri.conjTableM.Exporter(ConjugSyri.defargs);
            case POLICE ->  { ConjÉcritureSyriM.setÉcriture( (ConjÉcritureSyriM.SYF)((JComboBox)e.getSource()).getSelectedItem()) ;
            Conjugueur.M.ConjugSyri.conjTableM.Polichange ( Conjugueur.M.Conjug.É.i2É(contrôleur.model.getÉcriture()).toString()) ;
            SYRIAC = new Font (Conjugueur.M.Conjug.É.i2É(contrôleur.model.getÉcriture()).toString(), Font.PLAIN, TAILLEPOLICEDECLINAISONS) ;
            Map attributes = SYRIAC.getAttributes(); attributes.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON); SYRIAC = SYRIAC.deriveFont(attributes);
            JFrameConjugSyri.jComboBoxVerbe.setFont(SYRIAC);}
        }
    }                                                                           // actionPerformed
    public void conjugSyriChanged(ConjugSyriChangedEvent event) {
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugSyri::conjugSyriChanged") ;
    }

 

    private final static String AUTREMOT= "A", ÉTAT = "É", EXPORT = "E", POLICE = "P", SCHEME = "S", TEMPS = "T", VERBE = "V", SYRIAC_TO_LATIN = "Syriac-Latin", XMLSOURCE = "ConjugSemi.xml", BALISÉNVERBE = "versy", BALISÉNAUTREMOT = "autremot" ;

    private enum SYÉT { Emphatique((short)0), Construit((short)1), Absolu((short)2) ;
        private final short iét;
        SYÉT (short iét) { this.iét = iét ;  }
        public short iét() { return iét; }    
    } 
    public static final JComboBox<ConjÉcritureSyriM.SYF> jComboBoxÉcriture = new JComboBox<>(new DefaultComboBoxModel<>(ConjÉcritureSyriM.SYF.values())) ;
    private final  JComboBox<SYÉT> jComboBoxÉtat = new JComboBox<>(new DefaultComboBoxModel<>(SYÉT.values())) ;   
    @SuppressWarnings("unchecked")
    public static final JComboBox<String> jComboBoxAutMot = new JComboBox<>(Conjugueur.M.ConjugSyri.comboBoxAutreMot);
    private final static short TAILLEPOLICEFRANÇAIS = 20, TAILLEPOLICEDECLINAISONS = 26 ;
    @SuppressWarnings("unchecked")
    public static final JComboBox<String> jComboBoxVerbe = new JComboBox<>(Conjugueur.M.ConjugSyri.comboBoxVerbeM);  
    public static final JComboBox<Conjugueur.M.ConjugSyri.T> jComboBoxTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugSyri.T.values())) ;
    public static final JComboBox<ConjSchèmeSyriM.SYS> jComboBoxSchème = new JComboBox<>(new DefaultComboBoxModel<>(ConjSchèmeSyriM.SYS.values())) ;
    private static final int HAUTEUR = 600, LARGEUR = 1400 ;
    private static final JButton jButExport = new JButton("Export");
    private static final boolean DEBUG = true;    
}
