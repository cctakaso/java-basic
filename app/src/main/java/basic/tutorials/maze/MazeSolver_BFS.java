package basic.tutorials.maze;

/*
 * ステップ2: 幅優先探索 (BFS) による迷路の解法
 * BFSは、スタート地点から隣接する全てのセルを探索していきます。
 * これにより、最短経路を見つけることができます。
 * 探索の過程をキューで管理し、訪れたセルを記録していきます。
 * 幅優先探索 (BFS) - 最短経路の保証
 * スタート地点から近い順に、波が広がるように全てのマスを探索します。「待ち行列（Queue）」というデータ構造を使い、見つけた通路を順番に処理していきます。
 * 長所: 必ず最短経路を見つけることができます。
 * 短所: 探索範囲が広がるため、ゴールが遠い場合は多くのメモリを消費することがあります。
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MazeSolver_BFS {
    /**
     * 【新しいAPI】迷路をBFSで解き、結果を直接コンソールに出力する
     */
    public static void solveAndPrint(char[][] originalMaze) {
        long startTime = System.nanoTime();
        List<Point> path = solve(originalMaze);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("探索結果:");
        if (path != null) {
            printSolution(path, originalMaze, "BFS", duration);
        } else {
            System.out.println("BFSでは解が見つかりませんでした。");
        }
    }

    // --- 以下は内部的なヘルパーメソッド ---

    private static List<Point> solve(char[][] maze) {
        Point start = findChar(maze, 'S'), goal = findChar(maze, 'G');
        if (start == null || goal == null) return null;
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        queue.add(start);
        cameFrom.put(start, null);
        Point current = null;
        boolean found = false;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.equals(goal)) { found = true; break; }
            int[] dr = {1, 0, -1, 0};
            int[] dc = {0, 1, 0, -1};
            for (int i = 0; i < 4; i++) {
                Point next = new Point(current.x + dr[i], current.y + dc[i]);
                if (next.x >= 0 && next.x < maze.length && next.y >= 0 && next.y < maze[0].length &&
                    maze[next.x][next.y] != '#' && !cameFrom.containsKey(next)) {
                    queue.add(next);
                    cameFrom.put(next, current);
                }
            }
        }
        return found ? reconstructPath(cameFrom, current) : null;
    }
   
    // --- ユーティリティ ---
    private static List<Point> reconstructPath(Map<Point, Point> cameFrom, Point current) { List<Point> path = new ArrayList<>(); while (current != null) { path.add(current); current = cameFrom.get(current); } Collections.reverse(path); return path; }
    private static void printSolution(List<Point> path, char[][] o, String a, double d) { char[][] disp = cloneMaze(o); for(int i=0;i<path.size()-1;i++){ Point curr = path.get(i), next = path.get(i+1); if(disp[curr.x][curr.y]=='S') continue; if(next.x>curr.x)disp[curr.x][curr.y]='v'; else if(next.x<curr.x)disp[curr.x][curr.y]='^'; else if(next.y>curr.y)disp[curr.x][curr.y]='>'; else if(next.y<curr.y)disp[curr.x][curr.y]='<';} printMaze(disp); System.out.println("アルゴリズム: "+a+"\n経路の長さ: "+(path.size()-1)+" ステップ"); System.out.printf("処理時間: %.4f ミリ秒\n", d); }
    private static Point findChar(char[][] m, char t) { for(int i=0;i<m.length;i++)for(int j=0;j<m[i].length;j++)if(m[i][j]==t)return new Point(i,j); return null; }
    private static void printMaze(char[][] m) { for(char[] r:m)System.out.println(new String(r)); }
    private static char[][] cloneMaze(char[][] o) { char[][] c=new char[o.length][]; for(int i=0;i<o.length;i++)c[i]=o[i].clone(); return c; }
}