import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit=new LinkedList<String>();
}
