package Conjugueur.V;

import javax.swing.text.DefaultStyledDocument;

public class Personne {                               // ligne de conjugaison
      String personne ;
      DefaultStyledDocument déclinaison ;
      public Personne(String p) {
    	this.personne = p;
    	this.déclinaison = new DefaultStyledDocument ();
    }
}                                                                                // Ligneconj
