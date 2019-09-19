import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ServerStrategyRecipeMatcher implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            String UnseparatedClientIngridients=(String)fromClient.readObject();
            String[] ClientIngridients=UnseparatedClientIngridients.split(",");
            BufferedReader bf=new BufferedReader(new FileReader("Resources/Vectors"));
            ObjectInputStream OI=new ObjectInputStream(new FileInputStream("Resources/RecipesDictionary"));
            HashMap<String,List<Recipe>> Recipes=(HashMap<String,List<Recipe>>)OI.readObject();
            PriorityQueue<PrioritizedRecipe> pq=new PriorityQueue<>(new PrioritizedRecipeComperator());
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
