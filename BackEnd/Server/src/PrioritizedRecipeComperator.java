import java.io.Serializable;
import java.util.Comparator;

public class PrioritizedRecipeComperator implements Comparator<PrioritizedRecipe>, Serializable {
    @Override
    public int compare(PrioritizedRecipe o1, PrioritizedRecipe o2) {
        if(o1.getPriority()>o2.getPriority())
            return 1;
        else if(o1.getPriority()<o2.getPriority())
            return -1;
        return 0;
    }
}
