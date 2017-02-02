public class PhraseFilter implements Filter
{
    private String where;
    private String query;
    public PhraseFilter(String where,String query){
        this.where = where;
        this.query = query;
    }
     public boolean satisfies(QuakeEntry qe) { 
         String quakeTitle = qe.getInfo();
         boolean matches = false;
       switch (where.toLowerCase()){//case-insensitive check
       case "start":
       matches = (quakeTitle.indexOf(query) == 0);
       break;
       case "end":
       matches = (quakeTitle.lastIndexOf(query) == quakeTitle.length() - query.length());
       break;
       case "any":
       matches = (quakeTitle.indexOf(query) != -1);
       break;
    }
    return matches;
    } 
}