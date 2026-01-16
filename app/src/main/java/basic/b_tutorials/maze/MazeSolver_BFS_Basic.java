package basic.b_tutorials.maze;

public class MazeSolver_BFS_Basic {
   
    // 座標を管理する単純な内部クラス
    private static class Point {
        int r, c;
        public Point(int r, int c){ this.r = r; this.c = c; }
    }

    public static void solveAndPrint(char[][] originalMaze) {
        long startTime = System.nanoTime();

        Point start = findChar(originalMaze, 'S');
        Point goal = findChar(originalMaze, 'G');
        Point endPoint = null;

        // ★★★ 修正箇所 ★★★
        // cameFrom配列をメソッドの先頭で宣言し、スコープを広げる
        Point[][] cameFrom = null;

        if (start != null && goal != null) {
            // Queueの代わりにPoint配列を使用
            Point[] queue = new Point[originalMaze.length * originalMaze[0].length];
            int head = 0, tail = 0;
   
            // Mapの代わりにPointの2次元配列をここで初期化
            cameFrom = new Point[originalMaze.length][originalMaze[0].length];
   
            queue[tail++] = start;
            cameFrom[start.r][start.c] = start; // 開始点は自身を親とする

            while (head < tail) {
                Point current = queue[head++];
                if (current.r == goal.r && current.c == goal.c) {
                    endPoint = current;
                    break;
                }

                int[] dr = {1, 0, -1, 0}; // 下, 右, 上, 左
                int[] dc = {0, 1, 0, -1};
                for (int i = 0; i < 4; i++) {
                    int nextR = current.r + dr[i];
                    int nextC = current.c + dc[i];
                    if (nextR >= 0 && nextR < originalMaze.length && nextC >= 0 && nextC < originalMaze[0].length &&
                        originalMaze[nextR][nextC] != '#' && cameFrom[nextR][nextC] == null) {
                        queue[tail++] = new Point(nextR, nextC);
                        cameFrom[nextR][nextC] = current;
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("探索結果:");
        // cameFromがnullでないことを確認してから使用
        if (endPoint != null && cameFrom != null) {
            char[][] display = cloneMaze(originalMaze);
            // cameFrom配列を逆に辿って経路を描画
            Point current = endPoint;
            int pathLength = 0;
            while(true) {
                Point parent = cameFrom[current.r][current.c];
                // スタート地点に到達したらループを抜ける
                if(parent.r == current.r && parent.c == current.c) break;
       
                if (current.r > parent.r) display[parent.r][parent.c] = 'v';
                else if (current.r < parent.r) display[parent.r][parent.c] = '^';
                else if (current.c > parent.c) display[parent.r][parent.c] = '>';
                else if (current.c < parent.c) display[parent.r][parent.c] = '<';
                current = parent;
                pathLength++;
            }
            display[start.r][start.c] = 'S'; // スタート地点の文字を'S'に戻す
            printMaze(display);
            System.out.println("アルゴリズム: BFS (基本文法)");
            System.out.println("経路の長さ: " + pathLength + " ステップ");
            System.out.printf("処理時間: %.4f ミリ秒\n", duration);

        } else {
            System.out.println("解が見つかりませんでした。");
        }
    }
   
    // --- ユーティリティ (変更なし) ---
    private static Point findChar(char[][] m, char t) { for(int i=0;i<m.length;i++)for(int j=0;j<m[i].length;j++)if(m[i][j]==t)return new Point(i,j); return null; }
    private static void printMaze(char[][] m) { for(char[] r:m)System.out.println(new String(r)); }
    private static char[][] cloneMaze(char[][] o) { char[][] c=new char[o.length][]; for(int i=0;i<o.length;i++)c[i]=o[i].clone(); return c; }
}