public class DistanceFilter implements Filter
{
    private Location location;
    private double maximalDistance;
    private String name="DistanceFilter";
    public String getName(){
        return this.name;
    }
    public DistanceFilter(Location location,double maxDistance){
        this.location = location;
        this.maximalDistance = maxDistance;
    }
     public boolean satisfies(QuakeEntry qe) { 
        return location.distanceTo( qe.getLocation() ) < maximalDistance; 
    } 
}