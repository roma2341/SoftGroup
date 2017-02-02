import java.util.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        /*final double MAGNITUDE_MIN_CRITERIA = 4.0;
        final double MAGNITUDE_MAX_CRITERIA = 5.0;
        
        final double DEPTH_MIN_CRITERIA = -35000.0;
        final double DEPTH_MAX_CRITERIA = -12000.0;*/
        
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*Filter filterMagnitude = new MagnitudeFilter(MAGNITUDE_MIN_CRITERIA,MAGNITUDE_MAX_CRITERIA);
        Filter filterDepth = new DepthFilter(DEPTH_MIN_CRITERIA,DEPTH_MAX_CRITERIA);
        
        ArrayList<QuakeEntry> filterdByMagnitude  = filter(list, filterMagnitude);
        ArrayList<QuakeEntry> filterdByMagnitudeAndDepth  = filter(filterdByMagnitude, filterDepth); */ //Result: filterdByMagnitudeAndDepth
        
        Location tokyoJapan = new Location(35.42,139.43);
        final int MAX_DISTANCE_METERS = 10000*1000;//10km
        Filter filterDistance = new DistanceFilter(tokyoJapan,MAX_DISTANCE_METERS);
        Filter filterPhrase = new PhraseFilter("end","Japan");
        
               ArrayList<QuakeEntry> filteredByDistance  = filter(list, filterDistance);
        ArrayList<QuakeEntry> filteredByDistanceAndPhrase  = filter(filteredByDistance, filterPhrase);
        
        for (QuakeEntry qe: filteredByDistanceAndPhrase) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
