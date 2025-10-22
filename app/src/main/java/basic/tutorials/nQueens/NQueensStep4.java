package basic.tutorials.nQueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 完成版: 洗練されたNクイーン問題プログラム
 * O(1)の高速な安全性チェックと、オブジェクト指向に基づいたクラス設計を採用し、
 * 効率と構造、再利用性を大幅に向上させたバージョンです。
 * 改良点:
  ステップ3のコードを、効率、構造、再利用性の観点からさらに洗練させた完成版のプログラムを以下に示します。
  変更点と洗練されたポイント
  1. 効率の向上 (isSafeメソッド):
    女王を置けるかのチェック isSafe は、このプログラムで最も頻繁に呼ばれる部分です。以前のバージョンでは、毎回ループを回して列と斜めをチェックしていましたが、このバージョンでは3つの配列を使って攻撃されている場所を記録します。
    これにより、チェックの計算量がO(N)から**O(1)**に改善され、盤面が大きくなるほど劇的に高速化します。
  2. 構造の改善 (クラス化):
    以前のバージョンでは、すべての機能が static メソッドとして実装されていました。これを**NQueensSolver という専用のクラス**にまとめることで、盤面の状態や解のリストといった関連データを一つのオブジェクトとして管理できます。
    これはオブジェクト指向の考え方に基づいた設計で、コードが整理され、再利用しやすくなります。
  3. 柔軟性の向上 (解の保存):
    以前のバージョンでは解が見つかるたびにコンソールに出力していましたが、このバージョンでは見つかった全ての解をリストに保存してから、最後にまとめて出力します。
    これにより、解をただ表示するだけでなく、後から他の処理（例えば特定の解だけを取り出すなど）に利用することが可能になります。
 *
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

public class NQueensStep4 {
  static class NQueensSolver {
      private final int n;
      private int solutionCount = 0;
      private final List<int[][]> solutions = new ArrayList<>();

      // 攻撃されている場所を記録する配列
      private final boolean[] cols;      // 列
      private final boolean[] diag1;     // 右肩下がりの斜め (row - col)
      private final boolean[] diag2;     // 右肩上がりの斜め (row + col)

      public NQueensSolver(int n) {
          this.n = n;
          this.cols = new boolean[n];
          this.diag1 = new boolean[2 * n - 1]; // (row - col) の範囲は -(n-1) から (n-1)
          this.diag2 = new boolean[2 * n - 1]; // (row + col) の範囲は 0 から 2n-2
      }

      // 問題を解くメインのメソッド
      public void solve() {
          int[][] board = new int[n][n];
          backtrack(board, 0);
          printSolutions();
      }

      // バックトラッキングを行う再帰メソッド
      private void backtrack(int[][] board, int row) {
          // ベースケース: 全ての女王を配置できたら解として保存
          if (row == n) {
              solutionCount++;
              solutions.add(cloneBoard(board));
              return;
          }

          // 再帰ステップ: 現在の行の各列に女王を配置してみる
          for (int col = 0; col < n; col++) {
              // O(1)の高速チェック
              if (isSafe(row, col)) {
                  // 1. 配置する
                  placeQueen(board, row, col);

                  // 2. 次の行へ進む
                  backtrack(board, row + 1);

                  // 3. バックトラック: 配置した女王を取り除く
                  removeQueen(board, row, col);
              }
          }
      }

      // O(1)の高速な安全性チェック
      private boolean isSafe(int row, int col) {
          // 同じ列、または同じ斜めに女王がいなければ安全
          return !cols[col] && !diag1[row - col + n - 1] && !diag2[row + col];
      }

      // 女王を配置し、攻撃範囲を記録
      private void placeQueen(int[][] board, int row, int col) {
          board[row][col] = 1;
          cols[col] = true;
          diag1[row - col + n - 1] = true;
          diag2[row + col] = true;
      }

      // 女王を取り除き、攻撃範囲の記録を解除
      private void removeQueen(int[][] board, int row, int col) {
          board[row][col] = 0;
          cols[col] = false;
          diag1[row - col + n - 1] = false;
          diag2[row + col] = false;
      }

      // 見つかった解をまとめて出力
      private void printSolutions() {
          System.out.println(n + "クイーン問題の全ての解:");
          if (solutions.isEmpty()) {
              System.out.println("解は見つかりませんでした。");
              return;
          }

          for (int i = 0; i < solutions.size(); i++) {
              System.out.println("\n--- 解 #" + (i + 1) + " ---");
              int[][] board = solutions.get(i);
              for (int r = 0; r < n; r++) {
                  for (int c = 0; c < n; c++) {
                      System.out.print(board[r][c] == 1 ? "Q " : ". ");
                  }
                  System.out.println();
              }
          }
          System.out.println("\n合計で " + solutionCount + " 個の解が見つかりました。");
      }

      // 解を保存するために盤面の状態をコピーするヘルパーメソッド
      private int[][] cloneBoard(int[][] board) {
          int[][] newBoard = new int[n][n];
          for (int i = 0; i < n; i++) {
              System.arraycopy(board[i], 0, newBoard[i], 0, n);
          }
          return newBoard;
      }
  }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("盤面のサイズ N を入力してください > ");
        int n = scanner.nextInt();

        NQueensSolver solver = new NQueensSolver(n);
        solver.solve();

        scanner.close();
    }
}