/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import Conjugueur.M.*;
import static Conjugueur.V.JFrameConjug.NOMPOLTNR;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JFrameConjugLat  extends JFrameConjDouble implements ActionListener {

    /**
     * Fction appelante : Conjugueur.C.ConjugLat::ConjugLat
     * @param contrôleur 
     */
    @SuppressWarnings({"unchecked", "unchecked", "unchecked", "unchecked"})
    public JFrameConjugLat(Conjugueur.C.Conjug contrôleur) {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugLat::JFrameConjugLat" + ", Mode = " + Conjugueur.M.ConjugLat.M.i2M(contrôleur.model.getMode()).toString()) ; }
        frame.setTitle("Conjugueur Latin V0.8");
        largeur = LARGEUR ; hauteur = HAUTEUR ; 
        jCBAutMot = new JComboBox(Conjugueur.M.ConjugLat.cbAutreMotM);
        jPanelAutreMot.add(jCBAutMot) ;  jPanelAutreMot.add(jLabelDéclinaison) ; jCBAutMot.setActionCommand(AUTREMOT) ; jCBAutMot.setSelectedIndex(contrôleur.model.getIAutreMot()) ; jCBAutMot.addActionListener(this) ;
        jPanelNb.add(jComboBoxNb) ;     jPanelDroit.add(jPanelNb, java.awt.BorderLayout.EAST);    jComboBoxNb.setActionCommand(NOMBRE) ; jComboBoxNb.setSelectedIndex(contrôleur.model.getNb()) ; jComboBoxNb.addActionListener(this); jCBAutMot.setRenderer(new ComboBoxVerbeRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugLat.ATTRIBUT)); 
        jTabDecl = new JTableau(Conjugueur.M.ConjugLat.declTableM) ;
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jPanelMode.add(jComboBoxMode);      jPanelGauche.add(jPanelMode, java.awt.BorderLayout.WEST);      jComboBoxMode.setActionCommand(MODE);     jComboBoxMode.setSelectedIndex(contrôleur.model.getMode()) ; jComboBoxMode.addActionListener(this) ;
        jPanelTemps.add(jComboBoxTemps);    jPanelGauche.add(jPanelTemps, java.awt.BorderLayout.CENTER);  jComboBoxTemps.setActionCommand(TEMPS);   jComboBoxTemps.setSelectedIndex(contrôleur.model.getTemps()) ; jComboBoxTemps.addActionListener(this) ;
        jCBVerbe  = new JComboBox(Conjugueur.M.ConjugLat.cbVerbeM)  ;
        jPanelVerbe.add(jCBVerbe) ;    jPanelVerbe.add(jLabelConjugaison);   jCBVerbe.setActionCommand(VERBE);   jCBVerbe.setSelectedIndex(contrôleur.model.getIndVerbe());jCBVerbe.addActionListener(this) ;
        jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT, ConjugLat.ATTRIBUT)); 
        jPanelVoix.add(jComboBoxVoix) ;     jPanelGauche.add(jPanelVoix, java.awt.BorderLayout.EAST);    jComboBoxVoix.setActionCommand(VOIX);   jComboBoxVoix.setSelectedIndex(contrôleur.model.getVoix()) ; jComboBoxVoix.addActionListener(this);
        jTabConj = new JTableau(Conjugueur.M.ConjugLat.conjTableM) ;
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        Conjugueur.M.Conjug.conjTableM.MAJConj(contrôleur) ;
        Conjugueur.M.Conjug.declTableM.MAJDecl(contrôleur);
    }
 
    private static final short HAUTEUR = 340, LARGEUR = 910, TAILLEPOLICELATIN = 19 ;
    public static final Font LATIN = new Font (NOMPOLTNR, Font.PLAIN, TAILLEPOLICELATIN) ;
    @SuppressWarnings("unchecked")
    private final  JComboBox<Conjugueur.M.ConjugLat.N> jComboBoxNb = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugLat.N.values())) ;
    public static final JComboBox<Conjugueur.M.ConjugLat.M> jComboBoxMode = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugLat.M.values()));
    public static  final JComboBox<Conjugueur.M.ConjugLat.LAT> jComboBoxTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugLat.LAT.values())) ;
    public static final  JComboBox<Conjugueur.M.ConjugLat.V> jComboBoxVoix = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugLat.V.values())) ;

    private  final boolean DEBUG = false ;    
}                                                                               // JFrameConjugLat
