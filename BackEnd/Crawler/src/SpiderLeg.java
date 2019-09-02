import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg
{
    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<>();
    private Document htmlDocument;

    public boolean crawl(String url)
    {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if (connection.response().statusCode() == 200) // 200 is the HTTP OK status code
                // indicating that everything is great.
                {
                    //System.out.println("\n**Visiting** Received web page at " + url);
                }
                if (!connection.response().contentType().contains("text/html")) {
                    System.out.println("**Failure** Retrieved something other than HTML");
                    return false;
                }
                Elements linksOnPage = htmlDocument.select("a[href]");
                //System.out.println("Found (" + linksOnPage.size() + ") links");
                for (Element link : linksOnPage) {
                    if(link.absUrl("href").contains("food-recipes")) {
                        this.links.add(link.absUrl("href"));
                    }
                }
                Elements ingridients = htmlDocument.select("span[itemprop=" + "recipeIngredient" + "]");
                //Element image=htmlDocument.selectFirst("img[itemprop=image]");
                //Element title=htmlDocument.selectFirst("h1[itemprop=name]");
                if (ingridients != null) {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\dordo\\AndroidStudioProjects\\SummerProject\\BackEnd\\DataBase\\MetaData.csv", true));
                    //System.out.println(title.text());
                    //System.out.println(image.attr("src"));
                    for (Element e : ingridients) {
                        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dordo\\AndroidStudioProjects\\SummerProject\\BackEnd\\DataBase\\MetaData.csv"));
                        String Ingrid = e.text().replaceAll("[\\d.]", "");
                        String banned[] = {"שלושת רבעי", "כוסות","כפית", "חופן", "כפות", "גרם", "ליטר", "מ\"ל", "ק\"ג", "וחצי", "ורבע", "רבע", "חצי", "ושליש", "שליש", "שני שלישים", "שני שליש", "מכפית", "כוס", "מכף", "כפיות", "גדול", "קטן", "קורט", "גביע", "ועוד", "פחות", "כף", "כפית", "לפחות", "מעט", "קופסה", "מיכל", "מכלי", "מכל", "קופסת", "גדושות", "כ-", "", ",", "()","%"," ים"," ות","\uFEFF"};
                        Ingrid=Ingrid.replaceAll("[^ א-ת]", "");
                        for (int i = 0; i < banned.length; i++) {
                            Ingrid = Ingrid.replace(banned[i], "");
                        }
                        Ingrid = Ingrid.replace("  ", " ");
                        Ingrid=Ingrid.replace("( )","");
                        Ingrid=Ingrid.replace("()","");
                        if (Ingrid.length()>0 && (Ingrid.charAt(0) == ' ' || Ingrid.charAt(0) == '\uFEFF')) {
                            Ingrid = Ingrid.substring(1);
                        }
                        if (Ingrid.length()>0 && Ingrid.charAt(Ingrid.length() - 1) == ' ') {
                            Ingrid = Ingrid.substring(0, Ingrid.length() - 1);
                        }
                        boolean temp = false;
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            if (line.charAt(0) == '\uFEFF')
                                line = line.substring(1);
                            if (line.equals(Ingrid)) {
                                temp = true;
                                break;
                            }
                        }
                        if (!temp) {
                            bw.newLine();
                            bw.write(Ingrid);
                        }
                        //System.out.println(Ingrid);
                        br.close();
                    }
                    bw.close();
                }
                return true;
        }
        catch (IOException ioe) {
                // We were not successful in our HTTP request
                return false;
        }
    }

    public void save(Elements ingridients){

    }

    public boolean searchForWord(String searchWord)
    {
        // Defensive coding. This method should only be used after a successful crawl.
        if(this.htmlDocument == null)
        {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return false;
        }
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }

    public List<String> getLinks()
    {
        return this.links;
    }

}