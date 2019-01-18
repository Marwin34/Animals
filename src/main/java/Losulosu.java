import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Losulosu {

    public static int getRandom(List<Integer> chances){
        Random rand = new Random();

        List<Integer> seed = new LinkedList<>();

        for(int i = 0; i < chances.size(); ++i){
            for(int j = 0; j < chances.get(i); ++j){
                seed.add(i);
            }
        }

        int index = rand.nextInt(seed.size() - 1);
        return seed.get(index);
    }
}
