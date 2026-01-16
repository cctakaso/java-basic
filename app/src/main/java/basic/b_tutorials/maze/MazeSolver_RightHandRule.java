package basic.b_tutorials.maze;

/**
 * MazeSolver_RightHandRule
 *
 * 右手法 (wall follower / right-hand rule) による迷路探索ユーティリティです。
 *
 * 説明:
 * - スタート 'S' から探索を開始し、右手を壁に沿わせるように移動してゴール 'G' を目指します。
 * - 向きは整数で表現します: 0=上(^), 1=右(>), 2=下(v), 3=左(<)。
 * - 探索中、直前のマスには進行方向を表す矢印 ('^','>','v','<') を置きます（'S' は上書きしません）。
 *
 * 前提・制限:
 * - 右手法は単純迷路（ループのない囲い）ではゴール到達を保証しますが、ループを含む迷路では必ずしも到達しない場合があります。
 * - スタートから最初に右向き（1）へ進むことを仮定しています。
 * - 無限ループ防止のため、最大ステップ数を設けています。
 *
 * 計算量:
 * - 時間: 最悪 O(N) (N = セル数) 程度（最大ステップ数で打ち切るため）。
 * - 空間: O(N)（迷路をクローンして表示を行うため）。
 *
 * 表示:
 * - 探索結果を標準出力に表示し、矢印で通過経路を示します。
 */
public class MazeSolver_RightHandRule {

    /**
     * 内部で使用する座標保持クラス
     * r: 行インデックス, c: 列インデックス
     */
    private static class Point { int r, c; public Point(int r, int c){this.r=r;this.c=c;}}

    /**
     * 迷路を探索して結果を標準出力に出力します。
     *
     * アルゴリズム: 右手法 (wall follower)。スタート 'S' から開始し、矢印で通過経路を示します。
     * このメソッドは入力配列を変更しないようにクローンを作成して処理します。
     *
     * @param originalMaze 入力迷路の2次元配列（文字）; メソッド内でクローンし、元配列は変更しません
     */
    public static void solveAndPrint(char[][] originalMaze) {
        long startTime = System.nanoTime();
        char[][] maze = cloneMaze(originalMaze);
        Point start = findChar(maze, 'S');

        boolean solved = false;
        int pathLength = 0;

        if (start != null) {
            int r = start.r;
            int c = start.c;

            // 向きを整数で管理: 0=上(^), 1=右(>), 2=下(v), 3=左(<)
            int direction = 1; // スタートから通路へは右向きに進むと仮定

            // 最初の1歩を迷路内に進める
            int prevR = r;
            int prevC = c;
            r = getNextR(r, direction);
            c = getNextC(c, direction);
            pathLength++;

            // 無限ループ防止のため、最大ステップ数を設定
            int maxSteps = maze.length * maze[0].length;

            for (int i = 0; i < maxSteps; i++) {
                // ゴールに到達したらループを抜ける
                if (maze[r][c] == 'G') {
                    solved = true;
                    break;
                }

                // ★★★ 修正箇所 ★★★
                // 1. 1つ前のマス(prev)から現在地(r,c)への移動方向を決定
                char moveChar = ' ';
                if (r > prevR) moveChar = 'v';      // 下に進んだ
                else if (r < prevR) moveChar = '^'; // 上に進んだ
                else if (c > prevC) moveChar = '>'; // 右に進んだ
                else if (c < prevC) moveChar = '<'; // 左に進んだ

                // 2. 1つ前のマス(prev)に、決定した方向記号を描画
                if (maze[prevR][prevC] != 'S') {
                    maze[prevR][prevC] = moveChar;
                }

                // 3. 現在地を次の「1つ前のマス」として記憶
                prevR = r;
                prevC = c;

                // --- 右手法のロジック (次の移動先を決定) ---
                int rightDir = (direction + 1) % 4;
                if (isPath(maze, r, c, rightDir)) { // 4a. 右が空いているか？
                    direction = rightDir;
                    r = getNextR(r, direction);
                    c = getNextC(c, direction);
                }
                else if (isPath(maze, r, c, direction)) { // 4b. 前が空いているか？
                    // direction はそのまま
                    r = getNextR(r, direction);
                    c = getNextC(c, direction);
                }
                else { // 4c. 右も前も壁なら、向きだけ左に変える
                    direction = (direction + 3) % 4;
                }
                pathLength++;
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("探索結果:");
        if (solved) {
            printMaze(maze);
            System.out.println("アルゴリズム: 右手法 (矢印表示)");
            System.out.println("経路の長さ: " + pathLength + " ステップ");
            System.out.printf("処理時間: %.4f ミリ秒\n", duration);
        } else {
            System.out.println("解が見つからなかったか、最大ステップ数に到達しました。");
        }
    }

    // --- ユーティリティ (変更なし) ---
    /**
     * 指定した位置(r,c)から指定方向(dir)へ移動できるか判定します。
     *
     * @param maze 迷路配列
     * @param r 現在の行
     * @param c 現在の列
     * @param dir 方向 (0=上,1=右,2=下,3=左)
     * @return 移動先が迷路内かつ通路であれば true
     */
    private static boolean isPath(char[][] maze, int r, int c, int dir) {
        int nextR = getNextR(r, dir);
        int nextC = getNextC(c, dir);
        if (nextR < 0 || nextR >= maze.length || nextC < 0 || nextC >= maze[0].length) return false;
        char nextCell = maze[nextR][nextC];
        return nextCell != '#' && nextCell != 'S'; // 壁とスタート地点には進まない
    }
    /**
     * 指定した方向へ1歩進んだときの行インデックスを返します。
     *
     * @param r 現在の行
     * @param dir 方向 (0=上,1=右,2=下,3=左)
     * @return 次の行インデックス
     */
    private static int getNextR(int r, int dir) {
        if (dir == 0) return r - 1;
        if (dir == 2) return r + 1;
        return r;
    }
    /**
     * 指定した方向へ1歩進んだときの列インデックスを返します。
     *
     * @param c 現在の列
     * @param dir 方向 (0=上,1=右,2=下,3=左)
     * @return 次の列インデックス
     */
    private static int getNextC(int c, int dir) {
        if (dir == 1) return c + 1;
        if (dir == 3) return c - 1;
        return c;
    }
    /**
     * 迷路配列内から指定文字を検索し、その座標を返します。
     *
     * @param m 迷路配列
     * @param t 検索する文字（例: 'S' や 'G'）
     * @return 見つかれば Point、見つからなければ null
     */
    private static Point findChar(char[][] m, char t) {
        for(int i=0;i<m.length;i++) {
            for(int j=0;j<m[i].length;j++) {
                if(m[i][j]==t) {
                    return new Point(i,j);
                }
            }
        }
        return null;
    }
    /**
     * 迷路配列を標準出力に行ごとに表示します。
     *
     * @param m 表示する迷路配列
     */
    private static void printMaze(char[][] m) {
        for(char[] r:m) {
            System.out.println(new String(r));
        }
    }
    /**
     * 2次元配列をディープコピーして返します（行配列のクローン）。
     *
     * @param o 元の迷路配列
     * @return クローンされた迷路配列
     */
    private static char[][] cloneMaze(char[][] o) {
        char[][] c = new char[o.length][];
        for(int i=0;i<o.length;i++) {
            c[i]=o[i].clone();
        }
        return c;
    }
}