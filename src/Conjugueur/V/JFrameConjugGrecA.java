/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import static Conjugueur.V.JFrameConjug.NOMPOLTNR;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class JFrameConjugGrecA extends JFrameConjDouble implements ActionListener {

    @SuppressWarnings({"unchecked", "unchecked"})
    public JFrameConjugGrecA(Conjugueur.C.Conjug contrôleur) throws BadLocationException {
        super(contrôleur); 
        if (DEBUG) {System.out.println("Conjugueur.Vue.JFrameConjugGrecA::JFrameConjugGrecA") ; }
        frame.setTitle("Conjugueur Grec ancien V0.8");
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        jCBAutMot = new JComboBox(Conjugueur.M.ConjugGrecA.cbAutreMotM);
        jPanelAutreMot.add(jCBAutMot) ;       jCBAutMot.setSelectedIndex(contrôleur.model.getIAutreMot()); jCBAutMot.addActionListener(this) ;
        jPanelNb.add(jComboBoxNb) ;     jPanelDroit.add(jPanelNb, java.awt.BorderLayout.EAST);    jComboBoxNb.setActionCommand(NOMBRE);jComboBoxNb.addActionListener(this);
        jTabDecl = new JTableau(Conjugueur.M.ConjugGrecA.declTableM) ;
        jTabDecl.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabDecl.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jPanelDroit.add(jTabDecl, java.awt.BorderLayout.SOUTH);
        jCBAutMot.setRenderer(new ComboBoxVerbeRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugGrecA.ATTRIBUT)); 
        jCBVerbe = new JComboBox(Conjugueur.M.ConjugGrecA.cbVerbeM);
        jPanelMode.add(jComboBoxMode);      jPanelGauche.add(jPanelMode, java.awt.BorderLayout.WEST);      jComboBoxMode.setActionCommand(MODE);     jComboBoxMode.setSelectedItem(contrôleur.model.getMode());jComboBoxMode.addActionListener(this) ;
        jPanelThèmes.add(jComboBoxThèmes);    jPanelGauche.add(jPanelThèmes, java.awt.BorderLayout.CENTER);  jComboBoxThèmes.setActionCommand(THÈME);   jComboBoxThèmes.setSelectedItem(contrôleur.model.getThème());jComboBoxThèmes.addActionListener(this) ;
        jPanelVerbe.add(jCBVerbe) ;    jPanelGauche.add(jPanelVerbe, java.awt.BorderLayout.NORTH);   jCBVerbe.setActionCommand(VERBE);   jCBVerbe.setSelectedItem(contrôleur.model.getIndVerbe());jCBVerbe.addActionListener(this) ;
        jPanelVoix.add(jComboBoxVoix) ;     jPanelGauche.add(jPanelVoix, java.awt.BorderLayout.EAST);    jComboBoxVoix.setActionCommand(VOIX);jComboBoxVoix.addActionListener(this);
        jTabConj = new JTableau(Conjugueur.M.Conjug.conjTableM) ;
        jTabConj.setTableHeader(null);
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT));
        jTabConj.getColumnModel().getColumn(2).setPreferredWidth(0) ;        
        jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugGrecA.ATTRIBUT)); 
        jPanelGauche.add(jTabConj, java.awt.BorderLayout.SOUTH);
        jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(LATIN, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugGrecA.ATTRIBUT)); 
        frame.add(splitPane, BorderLayout.CENTER);
        Conjugueur.M.ConjugGrecA.conjTableM.MAJConj(getContrôleur());
        Conjugueur.M.ConjugGrecA.declTableM.MAJDecl(getContrôleur());
    }
 
    private static final int HAUTEUR = 470, LARGEUR = 1100, TAILLEPOLICELATIN = 20;
    public static final Font LATIN = new Font (NOMPOLTNR, Font.PLAIN, TAILLEPOLICELATIN) ;
    private final JComboBox<Conjugueur.M.ConjugGrecA.GAM> jComboBoxMode = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugGrecA.GAM.values()));
    private final  JComboBox<Conjugueur.M.ConjugGrecA.GAN> jComboBoxNb = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugGrecA.GAN.values())) ;
    private final JComboBox<Conjugueur.M.ConjugGrecA.GAT> jComboBoxThèmes = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugGrecA.GAT.values())) ;
    private final  JComboBox<Conjugueur.M.ConjugGrecA.GAV> jComboBoxVoix = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugGrecA.GAV.values())) ;
    @SuppressWarnings("unchecked")
    private static final boolean DEBUG = false;    
    
}
