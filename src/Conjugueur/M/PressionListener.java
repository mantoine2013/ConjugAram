package Conjugueur.M;

import java.util.EventListener;

public interface PressionListener extends EventListener {
    void pressionChangee(double anciennePression, double nouvellePression);
    void pressionAugmentee(double anciennePression, double nouvellePression);
    void pressionDiminuee(double anciennePression, double nouvellePression);
}