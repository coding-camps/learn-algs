package jacob.algos.intro;

import java.util.List;

public class ListOperation {

    public static void listAdd(List<Integer> list, int maxLen) {
        for (int i = 0; i < maxLen; i++) {
            list.add(i);
        }
    }

    public static void listFind(List<Integer> list, Integer target) {
        Integer result = list.indexOf(target);
    }
}
