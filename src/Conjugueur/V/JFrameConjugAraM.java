/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class JFrameConjugAraM extends JFrameConjug implements ActionListener {
 
    @SuppressWarnings({"unchecked", "unchecked"})
    public JFrameConjugAraM(Conjugueur.C.ConjugAraM contrôleur) {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.Vue.JFrameConjugAraM::JFrameConjugAraM, contrôleur.toString() ="+contrôleur.toString()+ ", Mode = " + Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString()) ; }
        frame.setTitle("Conjugueur arabe moderne V0.8"); 
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        jCBAutMot = new JComboBox(Conjugueur.M.ConjugAraM.cbAutreMotM) ; jCBAutMot.setRenderer(new ComboBoxVerbeRenderer(ARABE, ComponentOrientation.RIGHT_TO_LEFT, Conjugueur.M.ConjugAraM.AAM)) ; 
        jPanelAutreMot.add(jCBAutMot) ; jPanelDroit.add(jPanelAutreMot, java.awt.BorderLayout.NORTH) ; jCBAutMot.setActionCommand(AUTREMOT) ;     jCBAutMot.setSelectedIndex(contrôleur.model.getIAutreMot()); jCBAutMot.addActionListener(this) ;
        jTabDecl = new JTableau(Conjugueur.M.Conjug.declTableM) ;
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(ARABE, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jCBVerbe = new JComboBox<>(Conjugueur.M.ConjugAraM.cbVerbeM) ; jCBVerbe.setSelectedIndex(contrôleur.model.getIndVerbe());
        jPanelMode.add(jCBMode) ; jCBMode.setActionCommand(MODE) ;     jPanelGauche.add(jPanelMode, java.awt.BorderLayout.WEST);          jCBMode.setSelectedIndex(contrôleur.model.getMode());jCBMode.addActionListener(this) ;
        jPanelTemps.add(jCBTemps) ; jCBTemps.setActionCommand(TEMPS); jPanelGauche.add(jPanelTemps, java.awt.BorderLayout.EAST); jCBTemps.setSelectedIndex(contrôleur.model.getTemps());jCBTemps.addActionListener(this); 
        jPanelVerbe.add(jCBVerbe) ; jCBVerbe.setActionCommand(VERBE) ;  jPanelGauche.add(jPanelVerbe, java.awt.BorderLayout.NORTH) ; jCBVerbe.setSelectedItem(contrôleur.model.getIndVerbe()); jCBVerbe.addActionListener(this) ; jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(ARABE, ComponentOrientation.RIGHT_TO_LEFT, Conjugueur.M.ConjugAraM.ATTRIBUT)); jCBVerbe.setFont(ARABE);    
        jTabConj = new JTableau(Conjugueur.M.Conjug.conjTableM) ;
        jTabConj.setTableHeader(null);
        jTabConj.setDefaultRenderer(String.class, new PersonneCellRenderer(FRANÇAIS)); // spécifie la classe de rendu pour le DéclinaisonStyledDoc
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(ARABE, ComponentOrientation.RIGHT_TO_LEFT)); // spécifie la classe de rendu pour le DéclinaisonStyledDoc
//        TableColumnModel tcm =  jTabConj.getColumnModel();
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        Conjugueur.M.ConjugAraM.conjTableM.MAJConj(contrôleur) ;
        Conjugueur.M.ConjugAraM.declTableM.MAJDecl(getContrôleur());
        frame.add(splitPane, BorderLayout.CENTER);
    }
    
    public final static short PRÉFIXE = 0 ;
    public final static short SUFFIXE = 1 ;
    public final static String NOMPOLICA = "Times New Roman" ;
    
    public final static JComboBox<Conjugueur.M.Conjug.M> jCBMode = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.Conjug.M.values()));
    public final static JComboBox<Conjugueur.M.ConjugAraM.ART> jCBTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugAraM.ART.values())) ;
    public static final  JComboBox<Conjugueur.M.ConjugAraM.ARV> jCBVoix = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugAraM.ARV.values())) ;
    public static final short HAUTEUR = 550, LARGEUR = 1000, TAILLEPOLICEARABE = 19 ;
    public static final Font ARABE = new Font (NOMPOLICA, Font.PLAIN, TAILLEPOLICEARABE) ;

    private final boolean DEBUG = false;        
}
