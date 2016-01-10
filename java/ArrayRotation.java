import java.util.Arrays;

/**
 * Created by alpoloz on 1/10/16.
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 */
public class ArrayRotation {

    public static void main(String... args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        rotate(array, 3);
        System.out.println(Arrays.toString(array));

    }

    private static void rotate(int[] array, int k) {
        int n = array.length;
        reverse(array, 0, n - k - 1);
        reverse(array, n - k, n - 1);
        reverse(array, 0, n - 1);

    }

    private static void reverse(int[] array, int from, int till) {
        for(int i = 0; i <= (till - from) / 2; i++) {
            int tmp = array[from + i];
            array[from + i] = array[till -i];
            array[till - i] = tmp;
        }
    }
}
