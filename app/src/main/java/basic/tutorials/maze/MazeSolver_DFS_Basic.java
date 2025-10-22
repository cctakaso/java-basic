package basic.tutorials.maze;

public class MazeSolver_DFS_Basic {

    public static void solveAndPrint(char[][] maze) {
        long startTime = System.nanoTime();
        char[][] displayMaze = cloneMaze(maze); // 表示用の盤面をコピー
        Point start = findChar(displayMaze, 'S');

        boolean solved = (start != null) && solveRecursive(displayMaze, start.x, start.y);

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("探索結果:");
        if (solved) {
            displayMaze[start.x][start.y] = 'S'; // スタート地点を'S'に戻す
            printMaze(displayMaze);
            System.out.println("アルゴリズム: DFS (基本文法)");
            System.out.printf("処理時間: %.4f ミリ秒\n", duration);
        } else {
            System.out.println("解が見つかりませんでした。");
        }
    }

    private static boolean solveRecursive(char[][] maze, int r, int c) {
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == '#' || maze[r][c] == '.') {
            return false;
        }
        if (maze[r][c] == 'G') {
            return true;
        }

        char originalChar = maze[r][c];
        maze[r][c] = '.'; // 探索中の目印

        if (solveRecursive(maze, r + 1, c)) { maze[r][c] = 'v'; return true; }
        if (solveRecursive(maze, r, c + 1)) { maze[r][c] = '>'; return true; }
        if (solveRecursive(maze, r - 1, c)) { maze[r][c] = '^'; return true; }
        if (solveRecursive(maze, r, c - 1)) { maze[r][c] = '<'; return true; }

        maze[r][c] = originalChar; // バックトラック
        return false;
    }

    // --- ユーティリティ ---
    private static class Point { int x, y; public Point(int x, int y){this.x=x;this.y=y;}}
    private static Point findChar(char[][] m, char t) { for(int i=0;i<m.length;i++)for(int j=0;j<m[i].length;j++)if(m[i][j]==t)return new Point(i,j); return null;}
    private static void printMaze(char[][] m) { for(char[] r:m)System.out.println(new String(r));}
    private static char[][] cloneMaze(char[][] o) { char[][] c=new char[o.length][]; for(int i=0;i<o.length;i++)c[i]=o[i].clone(); return c;}
}