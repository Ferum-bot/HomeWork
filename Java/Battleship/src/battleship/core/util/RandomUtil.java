package battleship.core.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static List<Integer> generateRandomIndexes(Integer until) {
        Set<Integer> uniqueRandomIndexes = new HashSet<>();
        List<Integer> randomIndexes = new LinkedList<>();
        for (int i = 0; i < until * 2; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, until);
            if (uniqueRandomIndexes.add(random)) {
                randomIndexes.add(random);
            }
        }
        return randomIndexes;
    }
}
