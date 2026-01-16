package basic.b_tutorials.maze;

/*
 * ステップ3: A*探索による迷路の解法
 * A*探索は、各セルの評価値を計算し、最も有望なセルを優先的に探索します。
 * 評価値は、スタート地点からの距離とゴールまでの推定距離の合計で決定されます。
 * これにより、効率的に最短経路を見つけることができます。
 * A*探索 - ヒューリスティックを用いた効率的な探索
 * 各マスに「スタートからの距離」と「ゴールまでの推定距離」を足し合わせた評価値を計算し、最も評価値の低いマスから優先的に探索します。これに
 * より、効率的に最短経路を見つけることができます。
 * 長所: 最短経路を見つけることができ、探索効率も高いです。
 * 短所: ヒューリスティックの選び方によっては、最適解を見逃す可能性があります。
 * A*探索のイメージ図:
 * https://upload.wikimedia.org/wikipedia/commons/5/57/Astar_progress_animation.gif
 * (出典: Wikipedia - A* search algorithm)
 * ※ 上記のGIFは、A*探索がどのように進行するかを視覚的に示しています。青いセルが探索中、緑のセルが最終的な経路です。
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MazeSolver_AStar {
    /**
     * 【新しいAPI】迷路をA*で解き、結果を直接コンソールに出力する
     */
    public static void solveAndPrint(char[][] originalMaze) {
        long startTime = System.nanoTime();
        List<Point> path = solve(originalMaze);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("探索結果:");
        if (path != null) {
            printSolution(path, originalMaze, "A*", duration);
        } else {
            System.out.println("A*では解が見つかりませんでした。");
        }
    }
   
    // --- 以下は内部的なヘルパーメソッド ---
   
    static class Node implements Comparable<Node> {
        Point point; int g; int h; Node parent;
        public Node(Point p, int g, int h, Node parent) { this.point=p; this.g=g; this.h=h; this.parent=parent; }
        public int f() { return g + h; }
        @Override public int compareTo(Node other) { return Integer.compare(this.f(), other.f()); }
    }

    private static List<Point> solve(char[][] maze) {
        Point startP = findChar(maze, 'S'), goalP = findChar(maze, 'G');
        if (startP == null || goalP == null) return null;
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Point, Node> allNodes = new HashMap<>();
        Node startNode = new Node(startP, 0, manhattanDistance(startP, goalP), null);
        openSet.add(startNode);
        allNodes.put(startP, startNode);
        Node goalNode = null;
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.point.equals(goalP)) { goalNode = current; break; }
            int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
            for (int i=0;i<4;i++) {
                Point nextP = new Point(current.point.x+dr[i], current.point.y+dc[i]);
                if (nextP.x<0||nextP.x>=maze.length||nextP.y<0||nextP.y>=maze[0].length||maze[nextP.x][nextP.y]=='#') continue;
                int newG = current.g+1;
                Node neighborNode = allNodes.get(nextP);
                if (neighborNode==null || newG<neighborNode.g) {
                    Node newNode = new Node(nextP,newG,manhattanDistance(nextP,goalP),current);
                    allNodes.put(nextP,newNode);
                    openSet.add(newNode);
                }
            }
        }
        return (goalNode != null) ? reconstructPath(goalNode) : null;
    }
   
    // --- ユーティリティ ---
    private static int manhattanDistance(Point a, Point b) { return Math.abs(a.x-b.x)+Math.abs(a.y-b.y); }
    private static List<Point> reconstructPath(Node n) { List<Point> p=new ArrayList<>(); Node c=n; while(c!=null){p.add(c.point);c=c.parent;} Collections.reverse(p); return p;}
    private static void printSolution(List<Point> path, char[][] o, String a, double d) { char[][] disp = cloneMaze(o); for(int i=0;i<path.size()-1;i++){ Point curr = path.get(i), next = path.get(i+1); if(disp[curr.x][curr.y]=='S') continue; if(next.x>curr.x)disp[curr.x][curr.y]='v'; else if(next.x<curr.x)disp[curr.x][curr.y]='^'; else if(next.y>curr.y)disp[curr.x][curr.y]='>'; else if(next.y<curr.y)disp[curr.x][curr.y]='<';} printMaze(disp); System.out.println("アルゴリズム: "+a+"\n経路の長さ: "+(path.size()-1)+" ステップ"); System.out.printf("処理時間: %.4f ミリ秒\n", d); }
    private static Point findChar(char[][] m, char t) { for(int i=0;i<m.length;i++)for(int j=0;j<m[i].length;j++)if(m[i][j]==t)return new Point(i,j); return null; }
    private static void printMaze(char[][] m) { for(char[] r:m)System.out.println(new String(r)); }
    private static char[][] cloneMaze(char[][] o) { char[][] c=new char[o.length][]; for(int i=0;i<o.length;i++)c[i]=o[i].clone(); return c; }
}