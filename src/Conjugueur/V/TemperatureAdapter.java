package Conjugueur.V;

import Conjugueur.M.TemperatureListener;

public abstract class TemperatureAdapter implements TemperatureListener {
    public void temperatureChangee(double ancienneTemperature, double nouvelleTemperature) {}
//    public void temperatureAugmentee(double ancienneTemperature, double nouvelleTemperature) {}
//    public void temperatureDiminuee(double ancienneTemperature, double nouvelleTemperature) {}
}