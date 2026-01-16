package basic.b_tutorials.maze;

public class MazeSolver_AStar_Basic {

    private static class Node {
        int r, c, g, h; Node parent;
        public Node(int r, int c, int g, int h, Node p) { this.r=r; this.c=c; this.g=g; this.h=h; this.parent=p;}
        public int f() { return g + h; }
    }

    public static void solveAndPrint(char[][] originalMaze) {
        long startTime = System.nanoTime();

        // --- A*探索 ---
        Point start = findChar(originalMaze, 'S');
        Point goal = findChar(originalMaze, 'G');
        Node endNode = null;

        if (start != null && goal != null) {
            // PriorityQueueの代わりにNode配列を使用
            Node[] openSet = new Node[originalMaze.length * originalMaze[0].length];
            int openSetSize = 0;
            boolean[][] closedSet = new boolean[originalMaze.length][originalMaze[0].length];

            openSet[openSetSize++] = new Node(start.r, start.c, 0, manhattanDistance(start, goal), null);

            while (openSetSize > 0) {
                // 配列から最小コストのノードを探す
                int bestNodeIndex = 0;
                for (int i = 1; i < openSetSize; i++) {
                    if (openSet[i].f() < openSet[bestNodeIndex].f()) {
                        bestNodeIndex = i;
                    }
                }
                Node current = openSet[bestNodeIndex];
                // 見つけたノードをリストから削除
                openSet[bestNodeIndex] = openSet[--openSetSize];

                if (current.r == goal.r && current.c == goal.c) {
                    endNode = current;
                    break;
                }

                closedSet[current.r][current.c] = true;

                int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
                for (int i=0;i<4;i++) {
                    int nextR = current.r + dr[i], nextC = current.c + dc[i];
                    if (nextR<0||nextR>=originalMaze.length||nextC<0||nextC>=originalMaze[0].length||
                        originalMaze[nextR][nextC]=='#'||closedSet[nextR][nextC]) continue;
           
                    int newG = current.g + 1;
                    openSet[openSetSize++] = new Node(nextR, nextC, newG, manhattanDistance(new Point(nextR, nextC), goal), current);
                }
            }
        }
        // --- 探索ここまで ---

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("探索結果:");
        if (endNode != null) {
            char[][] display = cloneMaze(originalMaze);
            Node current = endNode;
            int pathLength = 0;
            while(current.parent != null) {
                Node parent = current.parent;
                if (current.r > parent.r) display[parent.r][parent.c] = 'v';
                else if (current.r < parent.r) display[parent.r][parent.c] = '^';
                else if (current.c > parent.c) display[parent.r][parent.c] = '>';
                else if (current.c < parent.c) display[parent.r][parent.c] = '<';
                current = parent;
                pathLength++;
            }
            display[start.r][start.c] = 'S';
            printMaze(display);
            System.out.println("アルゴリズム: A* (基本文法)");
            System.out.println("経路の長さ: " + pathLength + " ステップ");
            System.out.printf("処理時間: %.4f ミリ秒\n", duration);
        } else {
            System.out.println("解が見つかりませんでした。");
        }
    }
   
    // --- ユーティリティ ---
    private static class Point { int r, c; public Point(int r, int c){this.r=r;this.c=c;}}
    private static int manhattanDistance(Point a, Point b) { return Math.abs(a.r-b.r)+Math.abs(a.c-b.c); }
    private static Point findChar(char[][] m, char t) { for(int i=0;i<m.length;i++)for(int j=0;j<m[i].length;j++)if(m[i][j]==t)return new Point(i,j); return null; }
    private static void printMaze(char[][] m) { for(char[] r:m)System.out.println(new String(r)); }
    private static char[][] cloneMaze(char[][] o) { char[][] c=new char[o.length][]; for(int i=0;i<o.length;i++)c[i]=o[i].clone(); return c; }
}