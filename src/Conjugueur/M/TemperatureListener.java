package Conjugueur.M;
import java.util.EventListener;



public interface TemperatureListener  extends EventListener {
    void temperatureChangee(double ancienneTemperature, double nouvelleTemperature);
}