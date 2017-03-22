/**
 * Created by 蟹老板 on 2017/3/22.
 */
public class Sort {
    public static void main(String args[]) throws Exception {
        int[] array = new int[]{10, 8, 2, 9, 5, 7, 7, 5, 3, 1, 0, 8, 6};
        System.out.println("Quick Sort:" + printArray(quickSort(array)));
        array = new int[]{10, 8, 2, 9, 5, 7, 7, 5, 3, 1, 0, 8, 6};
        System.out.println("Bubble Sort:" + printArray(bubbleSort(array)));
    }

    private static int[] quickSort(int[] array) {
        return quickSort(array, 0, array.length - 1);
    }

    private static int[] quickSort(int[] array, int low, int high) {
        if (low < high) {
            int key = array[low], locLow = low + 1, locHigh = high;
            while (locLow < locHigh) {
                while (locLow < locHigh && array[locHigh] >= key) --locHigh;
                while (locLow < locHigh && array[locLow] <= key) ++locLow;
                if (locLow < locHigh) {
                    array[locLow] = array[locLow] + array[locHigh];
                    array[locHigh] = array[locLow] - array[locHigh];
                    array[locLow] = array[locLow] - array[locHigh];
                }
            }
            array[low] = array[low] + array[locLow];
            array[locLow] = array[low] - array[locLow];
            array[low] = array[low] - array[locLow];
            quickSort(array, low, locLow - 1);
            quickSort(array, locLow + 1, high);
        }
        return array;
    }

    private static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = 1; j < array.length - i; j++) {
                if (array[i] > array[i + j]) {
                    array[i] = array[i] + array[i + j];
                    array[i + j] = array[i] - array[i + j];
                    array[i] = array[i] - array[i + j];
                }
            }
        return array;
    }

    private static String printArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int item : array) {
            sb.append(item + " ");
        }
        return sb.toString();
    }

}
