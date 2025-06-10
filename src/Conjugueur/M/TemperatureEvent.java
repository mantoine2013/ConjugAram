package Conjugueur.M;
public class TemperatureEvent {
 
    private double oldTemperature;
    private double newTemperature;
 
    TemperatureEvent(double oldTemperature, double newTemperature) {
        this.oldTemperature = oldTemperature;
        this.newTemperature = newTemperature;
    }
 
    public double getOldTemperature() {
        return oldTemperature;
    }
 
    public double getNewTemperature() {
        return newTemperature;
    }
 
}