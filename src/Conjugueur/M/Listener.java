/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public interface Listener {
    public void conjugLatChanged(ConjugLatChangedEvent event);
    public void conjugSansChanged(ConjugSansChangedEvent event);
    public void conjugSyriChanged(ConjugSyriChangedEvent event);    
}
