public class DepthFilter implements Filter
{
    private double minimalDepth;
    private double maximalDepth;
    private String name = "DepthFilter";
    public String getName(){
        return this.name;
    }
    public DepthFilter(double minDepth,double maxDepth){
        this.minimalDepth = minDepth;
        this.maximalDepth = maxDepth;
    }
     public boolean satisfies(QuakeEntry qe) { 
        return qe.getDepth() >= minimalDepth && qe.getDepth() <= maximalDepth; 
    } 
}