import java.util.Random;

/**
 * Created by alpoloz on 11.1.16.
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element
 * For example, given [3,2,1,5,6,4] and k = 2, return 5.
 */
public class KthLargestElementFinder {

    public static void main(String... args) {
        int[] numbers = {3,2,1,5,6,4};
        int n = numbers.length;
        int k = 2;
        System.out.println(findKthLargest(numbers, n - k, 0, n - 1));
    }

    private static int findKthLargest(int[] numbers, int k, int left, int right) {
        int pivotIndex = getPivotIndex(left, right);
        int pivot = numbers[pivotIndex];
        swap(numbers, pivotIndex, right);
        int markerIndex = left;
        for(int i = left; i < right; i++) {
            if(numbers[i] < pivot) {
                swap(numbers, markerIndex, i);
                markerIndex++;
            }
        }
        swap(numbers, right, markerIndex);
        if(markerIndex == k) {
            return pivot;
        } else if (markerIndex < k && markerIndex < right) {
            return findKthLargest(numbers, k, markerIndex + 1, right);
        } else if(markerIndex > k && markerIndex > left) {
            return findKthLargest(numbers, k, left, markerIndex - 1);
        }
        return -1;
    }

    private static void swap(int[] numbers, int first, int second) {
        int tmp = numbers[first];
        numbers[first] = numbers[second];
        numbers[second] = tmp;
    }

    private static int getPivotIndex(int left, int right) {
        return left + (left != right ? new Random().nextInt(right - left) : 0);
    }
}
