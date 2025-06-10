/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import Conjugueur.M.*;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.BadLocationException;

public class JFrameConjugCopt  extends JFrameConjug implements ActionListener {

    /**
     * Fction appelante : Conjugueur.Contrôleur.ConjugCopt::ConjugCopt
     * @param contrôleur 
     */
    @SuppressWarnings("unchecked")
    public JFrameConjugCopt(Conjugueur.C.ConjugCopt contrôleur) throws BadLocationException {
       super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugCopt::JFrameConjugCopt, Mode = " + Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()).toString()+ ", temps = " + Conjugueur.M.Conjug.T.i2T(contrôleur.model.getTemps()).toString()) ; }
        frame.setTitle("Conjugueur Coptien V0.8");
        largeur = LARGEUR ; hauteur = HAUTEUR ;
//        TableColumn col0 = jTabConj.getColumnModel().getColumn(0);        col0.setPreferredWidth(30);
//        TableColumn col1 = jTabConj.getColumnModel().getColumn(1);        col1.setPreferredWidth(500);
//        TableColumn col2 = jTabConj.getColumnModel().getColumn(2);        col2.setPreferredWidth(0);
        jPanelMode.add(jCBMode);      contentPane.add(jPanelMode, java.awt.BorderLayout.WEST) ;       jCBMode.setActionCommand(MODE) ;      jCBMode.setSelectedIndex(contrôleur.model.getMode()) ; jCBMode.addActionListener(this);
        jPanelTemps.add(jCBTemps);    contentPane.add(jPanelTemps, java.awt.BorderLayout.CENTER) ;    jCBTemps.setActionCommand(TEMPS) ;    jCBTemps.setSelectedIndex(contrôleur.model.getTemps()); jCBTemps.addActionListener(this);
        jPanelVerbe.add(jCBVerbe);    contentPane.add(JFrameConjug.jPanelVerbe, java.awt.BorderLayout.NORTH) ;     jCBVerbe.setActionCommand(VERBE) ;    jCBVerbe.setSelectedItem(contrôleur.model.getIndVerbe()) ; jCBVerbe.addActionListener(this);
        jTabConj = new JTableau (Conjugueur.M.ConjugCopt.conjTableM) ;
        jTabConj.setDefaultRenderer(DéclinaisonStyledDoc.class, new DéclinaisonCellRenderer(COPT, ComponentOrientation.LEFT_TO_RIGHT)); 
        jTabConj.setDefaultRenderer(String.class, new TextPaneRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT)); jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(COPT, ComponentOrientation.LEFT_TO_RIGHT, "infinitif")); 
        contentPane.add(jTabConj, java.awt.BorderLayout.SOUTH);                
        Conjugueur.M.ConjugCopt.conjTableM.MAJConj(contrôleur);
        frame.setContentPane(contentPane);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
//       if (DEBUG) System.out.println ("ConjugCopt::actionPerformed, e.getActionCommand() = " + e.getActionCommand()) ;
        getContrôleur().notifyVAChanged(e.getActionCommand(), ((JComboBox) e.getSource()).getSelectedIndex()) ;
    }
    

    @Override
    public void close() {
        frame.dispose(); 
    }
 
    private final String NOMPOLICE = "Times New Roman", TEMPS = "T", VERBE = "V", MODE = "M" ;
    @SuppressWarnings("unchecked")
    public static final JComboBox jCBVerbe = new JComboBox(Conjugueur.M.ConjugCopt.comboBoxVerbeM);
    private static final DefaultComboBoxModel<Conjugueur.M.ConjugCopt.ITM> cModelModes = new DefaultComboBoxModel<>(Conjugueur.M.ConjugCopt.ITM.values()) ; 
    public static final JComboBox<Conjugueur.M.ConjugCopt.ITM> jCBMode = new JComboBox<>(cModelModes);
    private final int LARGEUR = 750, HAUTEUR = 400, TAILLEPOLICE = 25 ;
    public static final DefaultComboBoxModel<ConjTempsCopt.ITT> cModelTemps = new DefaultComboBoxModel<>(ConjTempsCopt.ITT.values()) ; 
    public static final JComboBox jCBTemps = new JComboBox<>(cModelTemps);
    public final Font COPT = new Font (Conjugueur.ConjugCopt.NOMPOLICOPT, Font.PLAIN, TAILLEPOLICE), FRANÇAIS = new Font (NOMPOLICE, Font.PLAIN, TAILLEPOLICE) ;

    private final boolean DEBUG = true;    
}
