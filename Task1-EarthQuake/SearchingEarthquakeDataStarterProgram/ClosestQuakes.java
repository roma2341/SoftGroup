
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    private class ClosestLocationComparator implements Comparator<QuakeEntry>{
        Location targetLocation;
        public ClosestLocationComparator(Location location){
            this.targetLocation = location;
        }
    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        Float distance1 = o1.getLocation().distanceTo(targetLocation);
        Float distance2 = o2.getLocation().distanceTo(targetLocation);
        return distance1.compareTo(distance2);
    }
}
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> sortedQuakeDataByClosestToLocation =  (ArrayList<QuakeEntry>)quakeData.clone();
        sortedQuakeDataByClosestToLocation.sort(new ClosestLocationComparator(current));
        ret.addAll((sortedQuakeDataByClosestToLocation.size() > howMany) ? new ArrayList<QuakeEntry>(sortedQuakeDataByClosestToLocation.subList(0,howMany)) : sortedQuakeDataByClosestToLocation);
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
