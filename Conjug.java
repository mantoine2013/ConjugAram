/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;

public class Conjug {
    public Conjugueur.M.Conjug model = null ;
    public Conjugueur.V.JFrameConjug fieldVue = null; 

    protected void addListenersToModèle() {
        if (DEBUG) { System.out.println("Conjugueur.C.Conjug::addListenersToModèle") ; }
        model.addConjugListener(fieldVue);
    }

    public void notifyVAChanged(String va, int valeur){
       if (DEBUG) { System.out.println ("Conjugueur.C.Conjug::notifyVAChanged, va = " + va + ", valeur = " + valeur) ; }
        switch (va) {
            case AUTREMOT -> model.setIAutreMot(valeur);
            case ÉCRITURE -> model.setÉcriture((short)valeur);
            case ÉTAT  -> model.setÉtat((short)valeur);
            case MODE   -> model.setMode((short)valeur);
            case NOMBRE -> model.setNb((short)valeur) ;
            case SCHEME -> model.setSchème((short) valeur) ;
            case TEMPS, THÈME ->  model.setTemps((short)valeur);
            case VERBE ->    model.setIVerbe(valeur);
            case VOIX -> model.setVoix((short)valeur);
        }                                                                       // switch
    }

    public void displayVues(){
        if (DEBUG) { System.out.println("Conjugueur.C.Conjug::displayVues") ; }
        fieldVue.display();
    }
    public void closeVues(){
        fieldVue.close();
    }
    protected final static String AUTREMOT= "A", ÉCRITURE = "ÉC", ÉTAT="ÉT", MODE = "M", NOMBRE = "N", SCHEME = "S", TEMPS = "T", THÈME = "TH", VERBE = "V", VOIX = "VO" ;
    private static final boolean DEBUG = false;      
}
