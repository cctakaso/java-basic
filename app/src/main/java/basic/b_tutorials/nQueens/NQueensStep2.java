package basic.b_tutorials.nQueens;

/**
 * ステップ2: 安全性チェック
 * 指定したマス(行, 列)に女王を置けるかどうかを判定するロジックを実装します。
 * 同じ列、左上、右上の斜めに他の女王がいないかを確認します。
 * (行は1つずつ下に配置していくため、同じ行と下方向をチェックする必要はありません)
 * --- 現在の盤面 ---
 * . . Q . .
 * Q . . . .
 * . . . . .
 * . . . . .
 * . . . . .
 * (2, 3) に女王を置くのは安全ですか？ -> true
 * (2, 1) に女王を置くのは安全ですか？ -> false
 */
public class NQueensStep2 {

    // 指定したマスに女王を配置できるかチェックするメソッド
    private static boolean isSafe(int[][] board, int row, int col) {
        int n = board.length;

        // 1. 同じ「列」を上にチェック
        //. ? Q ? . . . . 
        //Q ? . ? . . . . 
        //. Q . Q . . . . 
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false; // 既に女王がいる
            }
        }

        // 2. 「左上」の斜めをチェック
        //. ? Q . . . . . 
        //Q . ? . . . . . 
        //. Q . Q . . . . 
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // 3. 「右上」の斜めをチェック
        //. . Q ? . ? . . 
        //Q . ? . ? . . . 
        //. Q . Q . . . . 
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // どこにも女王がいなければ安全
        return true;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] board = new int[n][n];

        // 手動で女王を配置
        board[0][2] = 1;
        board[1][0] = 1;
        //. . Q . . . . . 
        //Q . . . . . . . 
        //. . . . . . . . 

        System.out.println("--- 現在の盤面 ---");
        NQueensStep1.printBoard(board); // ステップ1のメソッドを再利用

        // 安全性チェックのテスト
        int testRow = 2;
        int testCol = 3;
        boolean safe = isSafe(board, testRow, testCol);

        System.out.println("\n(" + testRow + ", " + testCol + ") に女王を置くのは安全ですか？ -> " + safe); // trueになるはず

        testRow = 2;
        testCol = 1;
        safe = isSafe(board, testRow, testCol);
        System.out.println("(" + testRow + ", " + testCol + ") に女王を置くのは安全ですか？ -> " + safe); // (1,0)の斜め下なのでfalseになるはず
    }
}
