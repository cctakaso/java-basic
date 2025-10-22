package basic.tutorials.sort;

import java.util.Scanner;

public class SortTest {

    public static void main(String[] args) {
        // 1. 試験データの作成
        Scanner scanner = new Scanner(System.in);
        System.out.print("試験データ数を入力してください (10〜100程度) > ");
        int n = scanner.nextInt();
        int[] originalData = SortUtils.createTestData(n);

        // 2. ソート前のデータを表示
        System.out.println("--- Original Data ---");
        SortUtils.displayData(originalData);
        System.out.println();

        // 3. 各アルゴリズムで、ソートの実行と結果表示
        // --- 選択ソート ---
        System.out.println("--- Selection Sort ---");
        int[] selectionSortData = originalData.clone();
        long startTime = System.nanoTime();
        SelectionSort.sort(selectionSortData);
        long endTime = System.nanoTime();
        SortUtils.displayData(selectionSortData);
        System.out.println("Execution Time: " + (endTime - startTime) + " ns");
        System.out.println();

        // --- バブルソート ---
        System.out.println("--- Bubble Sort ---");
        int[] bubbleSortData = originalData.clone();
        startTime = System.nanoTime();
        BubbleSort.sort(bubbleSortData);
        endTime = System.nanoTime();
        SortUtils.displayData(bubbleSortData);
        System.out.println("Execution Time: " + (endTime - startTime) + " ns");
        System.out.println();

        // --- マージソート ---
        System.out.println("--- Merge Sort ---");
        int[] mergeSortData = originalData.clone();
        startTime = System.nanoTime();
        MergeSort.sort(mergeSortData);
        endTime = System.nanoTime();
        SortUtils.displayData(mergeSortData);
        System.out.println("Execution Time: " + (endTime - startTime) + " ns");
        System.out.println();

        // --- クイックソート ---
        System.out.println("--- Quick Sort ---");
        int[] quickSortData = originalData.clone();
        startTime = System.nanoTime();
        QuickSort.sort(quickSortData);
        endTime = System.nanoTime();
        SortUtils.displayData(quickSortData);
        System.out.println("Execution Time: " + (endTime - startTime) + " ns");
        System.out.println();

        scanner.close();
    }
}
