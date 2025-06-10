/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import java.awt.ComponentOrientation;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import java.util.ArrayList;

public class JFrameConjugHebM extends JFrameConjug implements ActionListener {

    @SuppressWarnings("unchecked")
    public JFrameConjugHebM(Conjugueur.C.ConjugHebM contrôleur)  {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugHebM::JFrameConjugHebM, mode = " +  Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()).toString()+ ", temps = " + Conjugueur.M.ConjugHebM.HMT.i2T(contrôleur.model.getTemps()).toString() + ", écriture = " + Conjugueur.V.JFrameConjugHebM.HMÉ.i2É(contrôleur.model.getÉcriture()).toString()) ; }
        frame.setTitle("Conjugueur hébreu moderne V0.8");
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        for (HMÉ é : HMÉ.values()) { tFonts.add (new Font (é.i2LP(é.ié()).toString(), Font.PLAIN, TAILLEPOLICE)) ;}
        jCBVerbe = new JComboBox<>(Conjugueur.M.ConjugHebM.cbVerbeM) ; jCBVerbe.setSelectedIndex(contrôleur.model.getIndVerbe()) ; jCBVerbe.addActionListener(this) ; jCBVerbe.setActionCommand(VERBE) ; jCBVerbe.setFont(tFonts.get(contrôleur.model.getÉcriture())) ; jCBVerbe.setRenderer(new ComboBoxVerbeRenderer(tFonts.get(contrôleur.model.getÉcriture()), ComponentOrientation.RIGHT_TO_LEFT, ATTRIBUTVERBE)) ;
        jPanelÉcriture.add(jCBÉcriture) ;   contentPane.add(jPanelÉcriture, java.awt.BorderLayout.EAST);  jCBÉcriture.addActionListener(this) ;         jCBÉcriture.setActionCommand(ÉCRITURE) ;  jCBÉcriture.setSelectedIndex(contrôleur.model.getÉcriture());
        jPanelSchème.add(jCBSchèmes) ;   contentPane.add(jPanelSchème, java.awt.BorderLayout.CENTER) ;        jCBSchèmes.setActionCommand(SCHEME) ;  jCBSchèmes.setSelectedIndex(contrôleur.model.getSchème()) ;jCBSchèmes.addActionListener(this) ;
        jPanelTemps.add(jCBTemps) ;   contentPane.add(jPanelTemps, java.awt.BorderLayout.WEST);          jCBTemps.setActionCommand(TEMPS) ;  jCBTemps.setSelectedIndex(contrôleur.model.getTemps()) ;jCBTemps.addActionListener(this);
        jPanelVerbe.add(jCBVerbe) ;  contentPane.add(jPanelVerbe, java.awt.BorderLayout.NORTH) ; jPanelVerbe.add(jLabelVoix) ; jPanelVerbe.add(JLabelForme)  ;                     
        jTabConj = new JTableau(Conjugueur.M.Conjug.conjTableM) ;
        jTabConj.getColumnModel().getColumn(0).setCellRenderer(new PersonneCellRenderer(FRANÇAIS));
        jTabConj.getColumnModel().getColumn(1).setCellRenderer(new DéclinaisonCellRenderer(tFonts.get(contrôleur.model.getÉcriture()), ComponentOrientation.RIGHT_TO_LEFT));
        jTabConj.getColumnModel().getColumn(2).setCellRenderer(new PersonneCellRenderer(FRANÇAIS));
        jTabConj.getColumnModel().getColumn(1).setPreferredWidth(50) ;        
        contentPane.add(jTabConj, java.awt.BorderLayout.SOUTH);
        Conjugueur.M.ConjugHebM.conjTableM.MAJConj (contrôleur) ;
        Conjugueur.M.ConjugHebM.conjTableM.Polichange (HMÉ.i2É(contrôleur.model.getÉcriture()).lp()) ;
        frame.setContentPane(contentPane);
    }

    
    /** Listens to the combo box.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       if (DEBUG) { System.out.println ("Conjugueur.V.JFrameConjugHebM::actionPerformed, e.getActionCommand() = " + e.getActionCommand()) ; }
        getContrôleur().notifyVAChanged(e.getActionCommand(), ((JComboBox) e.getSource()).getSelectedIndex()) ;
        if(e.getActionCommand().equals(ÉCRITURE)) {
                Conjugueur.M.ConjugHebM.conjTableM.Polichange (HMÉ.i2É(contrôleur.model.getÉcriture()).lp()) ;
                jCBVerbe.setFont(tFonts.get(contrôleur.model.getÉcriture()));}
    }                                                                           // actionPerformed

    @Override
    public void close() {
        frame.dispose(); 
    }
 
    
    public static final short HAUTEUR = 500, LARGEUR = 700;
    public enum HMÉ {
        Droite((short)0, "Times New Roman"), Cursive((short)1, "Dana Yad AlefAlefAlef Normal") ;
        private final short ié;
        private final String lp ;

        HMÉ (short ié, String lp) { this.ié = ié ; this.lp = lp ; }
        public short ié() { return ié; }
        public String lp() { return lp ; }
        public static HMÉ i2É(short nb) { HMÉ result = null;
            for (HMÉ é : HMÉ.values()) { if (nb == é.ié()) { result = é ;  break; }}
            return result;
        }                                                                       //findByName
        public  String i2LP(short nb) { String result = null;
            for (HMÉ é : HMÉ.values()) {if (nb == é.ié()) { result = this.lp ;  break; }}
            return result;
        }                                                                       //findByName
    }
    
    @SuppressWarnings("unchecked")
      
    private final JComboBox<Conjugueur.M.ConjugHebM.T> jCBTemps = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugHebM.T.values())) ;    
    private final JComboBox<Conjugueur.M.ConjugHebM.S> jCBSchèmes = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugHebM.S.values())) ;
    private final JComboBox<HMÉ> jCBÉcriture = new JComboBox<>(new DefaultComboBoxModel<>(HMÉ.values())) ;
    private final String  ATTRIBUTVERBE = "infinitif" ;
    public static ArrayList<Font> tFonts = new ArrayList<>() ;        
    private static final boolean DEBUG = false;       
}
