package basic.tutorials.nQueens;

import java.util.Scanner;

/**
 * ステップ3: バックトラッキングによる問題解決
 * 最初の行から順に女王を配置し、次の行、そのまた次の行へと進みます。
 * もし行き詰まったら、一つ前の行に戻って女王の場所をずらし、探索を続けます。
 * これを繰り返すことで、全ての解のパターンを見つけ出します。
 * 4クイーン問題の全ての解:
 * 4クイーン問題の全ての解:
 *
 * --- 解 #1 ---
 * . Q . .
 * . . . Q
 * Q . . .
 * . . Q .
 *
 * --- 解 #2 ---
 * . . Q .
 * Q . . .
 * . . . Q
 * . Q . .
 *
 * 合計で 2 個の解が見つかりました。
 */
public class NQueensStep3 {

    private static int solutionCount = 0; // 解の総数をカウントする変数

    // メインとなる再帰関数
    public static void solve(int[][] board, int row) {
        int n = board.length;

        // ベースケース: 全ての行に女王を配置できたら解として出力
        if (row == n) {
            solutionCount++;
            System.out.println("\n--- 解 #" + solutionCount + " ---");
            NQueensStep1.printBoard(board); // ステップ1のメソッドを再利用
            return;
        }

        // 再帰ステップ: 現在の行の各列に女王を配置してみる
        for (int col = 0; col < n; col++) {
            // ステップ2のメソッドで安全かチェック
            if (isSafe(board, row, col)) {
                // 1. 配置する
                board[row][col] = 1;

                // 2. 次の行へ進む (再帰呼び出し)
                solve(board, row + 1);

                // 3. バックトラック: 配置した女王を取り除く
                // (次の列の試行や、前の行に戻った時のために盤面を元に戻す)
                board[row][col] = 0;
            }
        }
    }

    // 安全性チェックメソッド (ステップ2からコピー)
    private static boolean isSafe(int[][] board, int row, int col) {
        int n = board.length;
        // 列のチェック
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }
        // 左上の斜めチェック
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        // 右上の斜めチェック
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("盤面のサイズ N を入力してください > ");
        int n = scanner.nextInt();

        int[][] board = new int[n][n];

        System.out.println(n + "クイーン問題の全ての解:");
        solve(board, 0); // 0行目から探索を開始

        if (solutionCount == 0) {
            System.out.println("解は見つかりませんでした。");
        } else {
            System.out.println("\n合計で " + solutionCount + " 個の解が見つかりました。");
        }
        scanner.close();
    }
}