/**
 * Created by alpoloz on 1/10/16.
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Given an array of integers, every element appears three times except for one. Find that single one.
 */
public class UniqueNumberSearch {

    public static void main(String... args) {
        searchInDoubles();
        searchInTriples();
    }

    private static void searchInTriples() {
        int[] numbers = {1, 1, 1, 11, 11, 11, 2, 2, 2, 3, 3, 3, 4, 4, 4, 6, 7, 7, 7, 8, 8, 8};
        int ones = 0, twos = 0, threes;
        for(int number : numbers) {
            twos |= ones & number;
            ones ^= number;
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        System.out.println(ones);
    }

    private static void searchInDoubles() {
        int[] numbers = {1, 1, 2, 2, 3, 3, 4, 4, 5, 7, 7, 8, 8};
        int result = 0;
        for(int number : numbers) {
            result ^= number;
        }
        System.out.println(result);
    }
}
