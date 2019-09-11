import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

public class tester {
    public static void main(String args[]){
        //System.out.println("מ\"ל");
        //SpiderLeg sl=new SpiderLeg();
        Spider sp=new Spider();
        sp.search("https://www.mako.co.il/food-recipes?partner=SecondNav");
        //sl.crawl("https://www.mako.co.il/food-recipes/recipes_column-chicken/Recipe-d1440427d56fc61027.htm?sCh=55e439cdf3178110&pId=25483675");
        //Categorizer cat=new Categorizer();
        //cat.MarkOff();
    }
}
