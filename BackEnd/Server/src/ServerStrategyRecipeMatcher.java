import javafx.scene.layout.Priority;

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
            ObjectInputStream OI=new ObjectInputStream(new FileInputStream("Resources/RecipesDictionary"));
            HashMap<String,List<Recipe>> Recipes=(HashMap<String,List<Recipe>>)OI.readObject();
            PriorityQueue<PrioritizedRecipe> pq=new PriorityQueue<>(new PrioritizedRecipeComperator());
            String line;
            BufferedReader br=new BufferedReader(new FileReader("Resources/Vectors.csv"));
            while((line = br.readLine()) != null){
                int priority=0;
                boolean minMatch=false;
                String[] seperatedVector=line.split(",");
                for(int i=0; i<seperatedVector.length; i++){
                    boolean match=false;
                    for(int j=0; j<ClientIngridients.length; j++){
                        if(ClientIngridients[j].equals(seperatedVector[i])){
                            minMatch=true;
                            match=true;
                            break;
                        }
                    }
                    if(!match){
                        priority++;
                    }
                }
                if(minMatch){
                    List<Recipe> recipeList=Recipes.get(line);
                    for(Recipe r: recipeList){
                        PrioritizedRecipe pr=new PrioritizedRecipe(r);
                        pr.setPriority(priority);
                        pq.add(pr);
                    }
                }
            }
            br.close();
            toClient.writeObject(pq);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
