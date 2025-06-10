/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conjugueur.V;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class JPanelSpinnerConjugHié extends JFrameConjug implements ActionListener {
    private JPanel frame = null;
    private JPanel contentPane = null;
    private JSpinner spinner = null;
    private SpinnerNumberModel spinnerModel = null;
    private JButton button = null;
 
    public JPanelSpinnerConjugHié(Conjugueur.C.ConjugHié contrôleur) {
        this(contrôleur, 0);
    }
 
    public JPanelSpinnerConjugHié(Conjugueur.C.ConjugHié contrôleur, int conjugHié){
        super(contrôleur); 
 
        buildPanel(conjugHié);
    }
 
    private void buildPanel(int conjugHié) {
        frame = new JPanel();
 
        contentPane = new JPanel();
 
        spinnerModel = new SpinnerNumberModel(conjugHié, 0, 100, 1);
 
        spinner = new JSpinner(spinnerModel);
        contentPane.add(spinner);
 
        button = new JButton("Mettre à jour");
        button.addActionListener(this);
        contentPane.add(button);
 
     /*   frame.setContentPane(contentPane);
        frame.setTitle("JPanelSpinnerConjugHié");
        frame.pack();*/
    }
 
    @Override
    public void close() {
     /*   frame.dispose();*/
    }
 
    @Override
    public void display() {
        frame.setVisible(true);
    }
 
 

    
    
}
