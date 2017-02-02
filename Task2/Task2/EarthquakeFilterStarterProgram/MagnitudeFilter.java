
/**
 * Write a description of class MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter
{
    private double minimalMagnitude;
    private double maximalMagnitude;
    private String name = "MagnitudeFilter";
    public String getName(){
        return this.name;
    }
    public MagnitudeFilter(double minMagnitude,double maxMagnitude){
        this.minimalMagnitude = minMagnitude;
        this.maximalMagnitude = maxMagnitude;
    }
     public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= minimalMagnitude && qe.getMagnitude() <= maximalMagnitude; 
    } 
}
