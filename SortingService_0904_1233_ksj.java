// 代码生成时间: 2025-09-04 12:33:26
 * This class provides a unified interface for different sorting
 * algorithms to be implemented and used interchangeably.
 */
public interface SortingService {
    
    /**
     * Sorts the given array using a specified sorting algorithm.
     * 
     * @param array The array to sort.
     * @return The sorted array.
     * @throws IllegalArgumentException If the input array is null.
     */
    Integer[] sort(Integer[] array);
}

/**
 * Implementation of bubble sort algorithm.
 */
public class BubbleSortService implements SortingService {
    @Override
    public Integer[] sort(Integer[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1] > array[j]) {
                    // Swap the elements.
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}

/**
 * Implementation of quick sort algorithm.
 */
public class QuickSortService implements SortingService {
    private static void sort(Integer[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(array, start, end);
            sort(array, start, pivotIndex - 1);
            sort(array, pivotIndex + 1, end);
        }
    }

    private static int partition(Integer[] array, int start, int end) {
        Integer pivot = array[end];
        int i = (start - 1);
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                Integer temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Integer temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;
        return i + 1;
    }

    @Override
    public Integer[] sort(Integer[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        sort(array, 0, array.length - 1);
        return array;
    }
}

/**
 * A factory class for creating instances of sorting services.
 * This class allows for the creation of different sorting service
 * implementations based on a specified algorithm name.
 */
public class SortingServiceFactory {
    public static SortingService getSortingService(String algorithm) {
        switch (algorithm.toLowerCase()) {
            case "bubble":
                return new BubbleSortService();
            case "quick":
                return new QuickSortService();
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
        }
    }
}

/**
 * A simple demonstration of how to use the sorting service.
 */
public class SortingApp {
    public static void main(String[] args) {
        try {
            Integer[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
            // Choose the sorting algorithm to use.
            SortingService service = SortingServiceFactory.getSortingService("quick");
            Integer[] sortedArray = service.sort(array);
            for (int value : sortedArray) {
                System.out.print(value + " ");
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}