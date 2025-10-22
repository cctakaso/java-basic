package basic.tutorials.maze;

/*
 * 深さ優先探索 (DFS) による迷路解法
 * 再帰的に探索を行い、解が見つかればその経路を返します。
 * 探索の過程で訪れたセルは再訪問しないように管理します。
 * 解が見つかった場合、経路を矢印で表示します。
 *
 * 深さ優先探索 (DFS) - 基本的な再帰バックトラック
 * 最もシンプルで直感的な探索方法です。一本の道をひたすら奥に進み、行き止まりにぶつかったら一つ前の分岐点まで戻って（バックトラック）、別の道を進みます。
 * 長所: 実装が比較的容易です。
 * 短所: 必ずしも最短経路を見つけられるとは限りません。非常に長い遠回りの経路を見つけることがあります。
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MazeSolver_DFS {

    /**
     * 【新しいAPI】迷路をDFSで解き、結果を直接コンソールに出力する
     * @param originalMaze 解く対象の迷路
     */
    public static void solveAndPrint(char[][] originalMaze) {
        long startTime = System.nanoTime();
        List<Point> path = solve(originalMaze); // 内部で探索を実行
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("探索結果:");
        if (path != null) {
            printSolution(path, originalMaze, "DFS", duration);
        } else {
            System.out.println("DFSでは解が見つかりませんでした。");
        }
    }

    // --- 以下は内部的なヘルパーメソッド ---

    private static List<Point> solve(char[][] maze) {
        Point start = findChar(maze, 'S');
        if (start == null) return null;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<Point> path = new ArrayList<>();
        if (solveRecursive(maze, visited, path, start.x, start.y)) {
            return path;
        }
        return null;
    }

    private static boolean solveRecursive(char[][] maze, boolean[][] visited, List<Point> path, int r, int c) {
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == '#' || visited[r][c]) return false;
        path.add(new Point(r, c));
        visited[r][c] = true;
        if (maze[r][c] == 'G') return true;
        if (solveRecursive(maze, visited, path, r + 1, c)) return true;
        if (solveRecursive(maze, visited, path, r, c + 1)) return true;
        if (solveRecursive(maze, visited, path, r - 1, c)) return true;
        if (solveRecursive(maze, visited, path, r, c - 1)) return true;
        path.remove(path.size() - 1);
        visited[r][c] = false;
        return false;
    }
   
    // --- ユーティリティ (結果表示用) ---
    private static void printSolution(List<Point> path, char[][] originalMaze, String algorithmName, double durationMillis) {
        char[][] display = cloneMaze(originalMaze);
        for (int i = 0; i < path.size() - 1; i++) {
            Point current = path.get(i);
            Point next = path.get(i + 1);
            if(display[current.x][current.y] == 'S') continue;
            if (next.x > current.x) display[current.x][current.y] = 'v';
            else if (next.x < current.x) display[current.x][current.y] = '^';
            else if (next.y > current.y) display[current.x][current.y] = '>';
            else if (next.y < current.y) display[current.x][current.y] = '<';
        }
        printMaze(display);
        System.out.println("アルゴリズム: " + algorithmName);
        System.out.println("経路の長さ: " + (path.size() - 1) + " ステップ");
        System.out.printf("処理時間: %.4f ミリ秒\n", durationMillis);
    }
    private static Point findChar(char[][] maze, char target) { for (int i = 0; i < maze.length; i++) for (int j = 0; j < maze[i].length; j++) if (maze[i][j] == target) return new Point(i, j); return null; }
    private static void printMaze(char[][] maze) { for(char[] row : maze) System.out.println(new String(row)); }
    private static char[][] cloneMaze(char[][] original) { char[][] copy = new char[original.length][]; for(int i=0; i<original.length; i++) copy[i] = original[i].clone(); return copy; }
}