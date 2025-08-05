package pkg1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Greedy {

    /**
     * Returns the most number of activities in time n
     * 
     * 
     * Given input of a list of activities (start and end times)
     * return the most amount of activites that can be done
     *
     * @param s List of activity start times with activities according to index
     * @param f List of activity finish times with activities according to index
     * @param i Index of the last selected activity (start of search).
     * @param n Index of the last activity in the list.
     * @return Max activity amount
     */
    public static int activitySelection(List<Integer> s, List<Integer> f, int i, int n) {
        int m = i + 1;

        while (m <= n && s.get(m) < f.get(i)) {
            m += 1;
        }

        if (m <= n) {
            return 1 + activitySelection(s, f, m, n);
        }
        else return 0;
    }

    // Helper to prepend dummy activity and run selection
    public static int test(List<Integer> s, List<Integer> f) {
        s.add(0, 0); // dummy activity with end = 0
        f.add(0, 0);
        return activitySelection(s, f, 0, s.size() - 1);
    }

    // Sorts activities by finish time
    public static void sortByFinishTime(List<Integer> s, List<Integer> f) {
        List<int[]> activities = new ArrayList<>();
        for (int i = 0; i < s.size(); i++) {
            activities.add(new int[]{s.get(i), f.get(i)});
        }

        activities.sort(Comparator.comparingInt(a -> a[1]));

        s.clear();
        f.clear();
        for (int[] act : activities) {
            s.add(act[0]);
            f.add(act[1]);
        }
    }
}
