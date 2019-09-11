import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {
    private static final int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();
    private SpiderLeg leg = new SpiderLeg();

    private String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = this.pagesToVisit.remove(0);
            if(this.pagesVisited.contains(nextUrl) && this.pagesToVisit.isEmpty())
                return null;
        } while(this.pagesVisited.contains(nextUrl));
        System.out.println("B");
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }

    public void search(String url)
    {
        String currentUrl;
        currentUrl = url;
        leg.crawl(currentUrl);
        this.pagesVisited.add(url);
        this.pagesToVisit.addAll(leg.getLinks());
        while(!this.pagesToVisit.isEmpty())
        {
            System.out.println("A");
            currentUrl = this.nextUrl();
            if(currentUrl!=null) {
                leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
                // SpiderLeg
                //boolean success = leg.searchForWord(searchWord);
                //if(success)
                //{
                //    System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                //    break;
                //}
                this.pagesToVisit.addAll(leg.getLinks());
            }
        }
        System.out.println("E");
        leg.save();
        System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
    }
}