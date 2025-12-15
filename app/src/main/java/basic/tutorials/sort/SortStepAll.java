package basic.tutorials.sort;

import java.util.Scanner;

public class SortStepAll {

    public static void main(String[] args) {
        // 1. 試験データの作成
        Scanner scanner = new Scanner(System.in);
        System.out.print("試験データ数を入力してください (10〜100程度) > ");
        int n = scanner.nextInt();
        int[] originalData = SortStep1.createTestData(n);

        // 2. ソート前のデータを表示
        System.out.println("--- Original Data ---");
        SortStep2.displayData(originalData);
        System.out.println();


        // 3. 各アルゴリズムで、ソートの実行と結果表示
        // --- 選択ソート ---
        System.out.println("--- Selection Sort ---");
        int[] selectionSortData = originalData.clone();
        long startTime = System.nanoTime();
        SortStep3.SelectionSort(selectionSortData);
        long endTime = System.nanoTime();
        SortStep2.displayData(selectionSortData);
        System.out.println("Execution Time: " + (endTime - startTime)/1000 + " ms");
        System.out.println();


        // --- バブルソート ---
        System.out.println("--- Bubble Sort ---");
        int[] bubbleSortData = originalData.clone();
        startTime = System.nanoTime();
        BubbleSort.sort(bubbleSortData);
        endTime = System.nanoTime();
        SortStep2.displayData(selectionSortData);
        System.out.println("Execution Time: " + (endTime - startTime)/1000 + " ms");
        System.out.println();

        // --- マージソート ---
        System.out.println("--- Merge Sort ---");
        int[] mergeSortData = originalData.clone();
        startTime = System.nanoTime();
        MergeSort.sort(mergeSortData);
        endTime = System.nanoTime();
        SortStep2.displayData(selectionSortData);
        System.out.println("Execution Time: " + (endTime - startTime)/1000 + " ms");
        System.out.println();

        // --- クイックソート ---
        System.out.println("--- Quick Sort ---");
        int[] quickSortData = originalData.clone();
        startTime = System.nanoTime();
        QuickSort.sort(quickSortData);
        endTime = System.nanoTime();
        SortStep2.displayData(selectionSortData);
        System.out.println("Execution Time: " + (endTime - startTime)/1000 + " ms");
        System.out.println();


        scanner.close();
    }




}
