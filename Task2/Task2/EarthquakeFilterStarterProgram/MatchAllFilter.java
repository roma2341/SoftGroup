import java.util.*;

public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> filters;

    public String getName(){
        StringBuilder name=new StringBuilder("[");
        int i =0;
        for (Filter filter : filters){
            if(i>0)name.append(',');
            name.append(filter.getName());
            i++;
        }
        name.append(']');
        return name.toString();
    }
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    public boolean satisfies(QuakeEntry qe) { 
        for(Filter filter : filters){
            if (!filter.satisfies(qe))return false;
        }
        return true;
    } 

}