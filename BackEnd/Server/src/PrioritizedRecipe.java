import java.io.Serializable;

public class PrioritizedRecipe implements IPrioritized, Serializable {
    private Recipe recipe;
    private int Priority;
    private String key;

    public PrioritizedRecipe(Recipe recipe){
        this.recipe=recipe;
        this.Priority=999999;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public void setPriority(int Priority) {
        this.Priority=Priority;
    }

    @Override
    public int getPriority() {
        return this.Priority;
    }

    public void setKey(String key){
        this.key=key;
    }

    public String getKey(){
        return this.key;
    }
}
