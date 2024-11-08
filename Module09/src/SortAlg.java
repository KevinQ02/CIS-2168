/*
• 20 points Insertion Sort
• 30 points Quicksort
• 20 points Third sorting algorithm of student’s choice
• 15 points Data is collected.
• 15 points Data is presented in a meaningful way.
• 7 points Extra Implement Timsort as the third algorithm.
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SortAlg {

    private static FileWriter timeFileWriter, comparisonsFileWriter, exchangesFileWriter;

    public static void main(String[] args) {
        try {
            setupFiles();
            performSortingTests();
            closeFileWriters();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void setupFiles() throws IOException {
        File timeFile = new File("time.csv");
        if (timeFile.createNewFile() || timeFile.length() == 0) {
            timeFileWriter = new FileWriter(timeFile, true);
            timeFileWriter.append("Size, InsertionSort, Quicksort, ShellSort\n");
        } else {
            timeFileWriter = new FileWriter(timeFile, true);
        }

        File comparisonsFile = new File("comparisons.csv");
        if (comparisonsFile.createNewFile() || comparisonsFile.length() == 0) {
            comparisonsFileWriter = new FileWriter(comparisonsFile, true);
            comparisonsFileWriter.append("Size, InsertionSort, Quicksort, ShellSort\n");
        } else {
            comparisonsFileWriter = new FileWriter(comparisonsFile, true);
        }

        File exchangesFile = new File("exchanges.csv");
        if (exchangesFile.createNewFile() || exchangesFile.length() == 0) {
            exchangesFileWriter = new FileWriter(exchangesFile, true);
            exchangesFileWriter.append("Size, InsertionSort, Quicksort, ShellSort\n");
        } else {
            exchangesFileWriter = new FileWriter(exchangesFile, true);
        }
    }

    private static void performSortingTests() throws IOException {
        for (int i = 10; i <= 10000; i *= 2) {
            int[] originalArray = generateRandomIntArray(i);
            int[] a = originalArray.clone();
            int[] b = originalArray.clone();
            int[] c = originalArray.clone();

            long startTime = System.nanoTime();
            int[] insertResults = testInsertionSort(a);
            long insertTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            int[] quickResults = testQuickSort(b, 0, b.length - 1);
            long quickTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            int[] shellResults = testShellSort(c);
            long shellTime = System.nanoTime() - startTime;

            timeFileWriter.append(i + ", " + insertTime + ", " + quickTime + ", " + shellTime + "\n");
            comparisonsFileWriter.append(i + ", " + insertResults[0] + ", " + quickResults[0] + ", " + shellResults[0] + "\n");
            exchangesFileWriter.append(i + ", " + insertResults[1] + ", " + quickResults[1] + ", " + shellResults[1] + "\n");
        }
    }

    private static void closeFileWriters() throws IOException {
        timeFileWriter.close();
        comparisonsFileWriter.close();
        exchangesFileWriter.close();
    }

    private static int[] generateRandomIntArray(int n) {
        Random random = new Random();
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = random.nextInt(10000);
        }
        return list;
    }

    // Insertion Sort
    private static int[] testInsertionSort(int[] arr) {
        int compares = 0;
        int exchanges = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                compares++;
                arr[j + 1] = arr[j];
                j--;
                exchanges++;
            }
            arr[j + 1] = key;
            exchanges++;
        }
        return new int[]{compares, exchanges};
    }

    // QuickSort
    private static int[] testQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] pi = partition(arr, low, high);
            int[] left = testQuickSort(arr, low, pi[0] - 1);
            int[] right = testQuickSort(arr, pi[0] + 1, high);
            return new int[]{pi[1] + left[0] + right[0], pi[2] + left[1] + right[1]};
        }
        return new int[]{0, 0};
    }

    private static int[] partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        int compares = 0;
        int exchanges = 0;
        for (int j = low; j < high; j++) {
            compares++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                exchanges += 2;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        exchanges += 2;
        return new int[]{i + 1, compares, exchanges};
    }

    // Shell Sort
    private static int[] testShellSort(int[] arr) {
        int n = arr.length;
        int compares = 0;
        int exchanges = 0;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    compares++;
                    arr[j] = arr[j - gap];
                    exchanges++;
                }
                arr[j] = temp;
                exchanges++;
            }
        }
        return new int[]{compares, exchanges};
    }
}


