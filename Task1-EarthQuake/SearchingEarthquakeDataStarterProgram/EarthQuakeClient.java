import java.util.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry entry : quakeData){
            if( entry.getMagnitude() > magMin ) 
                answer.add(entry);    
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry quakeEntry : quakeData) {
            if (from.distanceTo(quakeEntry.getLocation())<distMax)
            answer.add(quakeEntry);
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> filteredQuakeData = new ArrayList<QuakeEntry>();
        for (QuakeEntry quakeEntry : quakeData){
        if (quakeEntry.getDepth()>minDepth && quakeEntry.getDepth() < maxDepth)
        filteredQuakeData.add(quakeEntry);
        }
        return filteredQuakeData;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> filteredQuakeData = new ArrayList<QuakeEntry>();
       for (QuakeEntry quakeEntry : quakeData){
           boolean matches = false;
           String quakeTitle = quakeEntry.getInfo();
       switch (where.toLowerCase()){//case-insensitive check
       case "start":
       matches = (quakeTitle.indexOf(phrase) == 0);
       break;
       case "end":
       matches = (quakeTitle.indexOf(phrase) == quakeTitle.length() - phrase.length());
       break;
       case "any":
       matches = (quakeTitle.indexOf(phrase) != -1);
       break;
    }
        if (matches)
        filteredQuakeData.add(quakeEntry);
        } 
        return filteredQuakeData;
    }
    
    public void quakesByPhrase(){
         ArrayList<QuakeEntry> earthquakes = findAllEarthQuakes("data/nov20quakedata.atom");
         ArrayList<QuakeEntry> filteredEarthQuakes = filterByPhrase(earthquakes,"end","ia");
         outputEarthQuakeList(filteredEarthQuakes);
         
    }
    public void quakesOfDepth(){
        final double MIN_DEPTH_VALUE = -8865.0;
        final double MAX_DEPTH_VALUE = -4000.0;
        ArrayList<QuakeEntry> earthquakes = findAllEarthQuakes("data/nov20quakedata.atom");
        ArrayList<QuakeEntry> filteredEarthQuakes = filterByDepth(earthquakes,MIN_DEPTH_VALUE,MAX_DEPTH_VALUE);
        outputEarthQuakeList(filteredEarthQuakes);
         System.out.println("Answer:"+filteredEarthQuakes.get(6).getMagnitude());
    }

      public void bigQuakes() {
        final double MAGNITUDE_MINIMAL = 5.0;
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredQuakesList = filterByMagnitude(list,MAGNITUDE_MINIMAL);
        for (QuakeEntry quakeEntry : filteredQuakesList){
       System.out.println(quakeEntry);
    }
          System.out.println("found "+filteredQuakesList.size()+" quakes that match that criteria");

    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void closeToMe(){
        final double MAX_DISTANCE_METERS= 1000000;
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
 
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> filteredQuakesList = filterByDistanceFrom(list,MAX_DISTANCE_METERS,city);
          for (QuakeEntry quakeEntry : filteredQuakesList){
              System.out.println(quakeEntry.getInfo());
    }
         System.out.println("Found "+filteredQuakesList.size()+" quakes that match that criteria");
        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    private ArrayList<QuakeEntry> findAllEarthQuakes(String dataURL){
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(dataURL);
        return list;
    }
    private void outputEarthQuakeList(ArrayList<QuakeEntry> earthquakes){
        for (QuakeEntry quakeEntry : earthquakes){
              System.out.println(quakeEntry); 
        }
          System.out.printf("Found %d quakes that match criteria%n",earthquakes.size());
    }
    
}
