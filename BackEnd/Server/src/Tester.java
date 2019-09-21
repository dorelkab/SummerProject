import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.PriorityQueue;

public class Tester {
    public static void main(String[] args){
        Server recipeMatcherServer = new Server(5400, 1000, new ServerStrategyRecipeMatcher());
        recipeMatcherServer.start();
        try {
            Socket theServer = new Socket(InetAddress.getLocalHost(), 5400);
            ObjectOutputStream toServer = new ObjectOutputStream(theServer.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(theServer.getInputStream());
            toServer.writeObject("138,");
            PriorityQueue<PrioritizedRecipe> pq=(PriorityQueue<PrioritizedRecipe>)fromServer.readObject();
            PrioritizedRecipe pr;
            int n=100;
            while((pr=pq.poll())!=null && n>=0){
                Recipe r=pr.getRecipe();
                System.out.println(r.getTitle());
                System.out.println(r.getUrl());
                System.out.println(pr.getPriority());
                n--;
            }
            theServer.close();
            recipeMatcherServer.stop();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
