package basic.b_tutorials.nQueens;

import java.util.Scanner;

/**
 * ステップ1: 盤面のセットアップ
 * ユーザーから盤面のサイズNを受け取り、N×Nの盤面を作成します。
 * また、盤面を表示する基本的な機能を実装します。
 * --- 4×4 の盤面を作成しました ---
 * . . . .
 * . . . .
 * . . . .
 * . . . .
 * --- (0,0)と(1,2)に女王を配置しました ---
 * Q . . .
 * . . Q .
 * . . . .
 * . . . .
 */
public class NQueensStep1 {

    // 盤面を出力するメソッド
    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q "); // 女王(Queen)
                } else {
                    System.out.print(". "); // 空きマス
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("盤面のサイズ N を入力してください (例: 8) > ");
        int n = scanner.nextInt();

        // N×Nの盤面を作成 (0で初期化)
        int[][] board = new int[n][n];

        System.out.println("\n--- " + n + "×" + n + " の盤面を作成しました ---");
        printBoard(board);

        // 例として、手動で女王を配置してみる
        board[0][0] = 1; // 1は女王がいることを示す
        board[1][2] = 1;

        System.out.println("\n--- (0,0)と(1,2)に女王を配置しました ---");
        printBoard(board);

        scanner.close();
    }
}