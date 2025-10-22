package basic.tutorials.maze;

public class MazeSolver_RightHandRule {

    private static class Point { int r, c; public Point(int r, int c){this.r=r;this.c=c;}}

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
    private static boolean isPath(char[][] maze, int r, int c, int dir) {
        int nextR = getNextR(r, dir);
        int nextC = getNextC(c, dir);
        if (nextR < 0 || nextR >= maze.length || nextC < 0 || nextC >= maze[0].length) return false;
        char nextCell = maze[nextR][nextC];
        return nextCell != '#' && nextCell != 'S'; // 壁とスタート地点には進まない
    }
    private static int getNextR(int r, int dir) { if (dir == 0) return r - 1; if (dir == 2) return r + 1; return r;}
    private static int getNextC(int c, int dir) { if (dir == 1) return c + 1; if (dir == 3) return c - 1; return c;}
    private static Point findChar(char[][] m, char t) { for(int i=0;i<m.length;i++)for(int j=0;j<m[i].length;j++)if(m[i][j]==t)return new Point(i,j); return null;}
    private static void printMaze(char[][] m) { for(char[] r:m)System.out.println(new String(r));}
    private static char[][] cloneMaze(char[][] o) { char[][] c=new char[o.length][]; for(int i=0;i<o.length;i++)c[i]=o[i].clone(); return c;}
}