
/**
 * Hi Man !
 * 
 * @author zigzag
 * @version 1.00
 */
    import java.util.*;
public class LargestQuakes
{
  private class MagnitudeComparatorDescend implements Comparator<QuakeEntry>{
        Location targetLocation;
    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        return Double.compare(o2.getMagnitude(),o1.getMagnitude());
    }
}

    public LargestQuakes()
    {
      
    }
    public void findLargestQuakes(){
        final int LARGEST_QUAKES_MAX = 2755;
        ArrayList<QuakeEntry> quakesList = findAllEarthQuakes("data/nov20quakedata.atom");
        ArrayList<QuakeEntry> largestQuakes = getLargest(quakesList,LARGEST_QUAKES_MAX);
        for (QuakeEntry quakeEntry : largestQuakes){
            System.out.println(quakeEntry);
        }
        //int indexOfLargestEarthQuake = indexOfLargest(quakesList);
        //System.out.printf("Largest earthquake has index %d:%s",indexOfLargestEarthQuake,quakesList.get(indexOfLargestEarthQuake));
        System.out.printf("There are total %s earthquakes%n",quakesList.size());
    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double largestMagnitude=Double.MIN_VALUE;
        int largestMagnitudeIndex=0;
        int i = 0;
        for (QuakeEntry quakeEntry : data){
            double currentMagnitude = quakeEntry.getMagnitude();
            if (currentMagnitude > largestMagnitude) {
                largestMagnitude = currentMagnitude;
                largestMagnitudeIndex = i;
            }
            i++;
        }
        return largestMagnitudeIndex;
    }
     private ArrayList<QuakeEntry> findAllEarthQuakes(String dataURL){
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(dataURL);
        return list;
    }
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
     ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> sortedQuakeData =  quakeData;
        Collections.sort(sortedQuakeData,new MagnitudeComparatorDescend());
        ret.addAll((sortedQuakeData.size() > howMany) ? new ArrayList<QuakeEntry>(sortedQuakeData.subList(0,howMany)) : sortedQuakeData);
        return ret;   
    }
}
