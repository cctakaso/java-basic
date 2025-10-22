package basic.tutorials.maze;
/*
 * クルスカル法による迷路生成アルゴリズムの実装
 * 迷路は2次元のグリッドで表現され、壁と通路で構成されます。
 * クルスカル法は、ランダムに壁を取り除きながら、全ての通路が連結されるまで続けます。
 * これにより、解が一意に定まる迷路が生成されます。
 * 迷路の開始点と終了点も設定されます。
 * 生成された迷路の例 (5x5):
 * S #   #
 *   # # #
 * #     #
 * # # # #
 * #   # G
 * S: スタート地点, G: ゴール地点
 * # : 壁,   : 通路
 * このコードは、迷路生成の基本的な考え方とアルゴリズムを学ぶのに役立ちます。
 * また、データ構造としてのグリッドや、ランダム化アルゴリズムの使用例も示しています。
 * グラフ理論における**クルスカル法（Kruskal's Algorithm）**を用いて、SからGまでの経路が必ず存在する迷路を生成するJavaプログラムを作成します。
 * クルスカル法による迷路生成の理論
 *  このプログラムは、迷路をグラフと見立てて「最小全域木」を求めるクルスカル法を応用したものです。
 * 初期状態: まず、格子状に壁を配置し、各通路（セル）が壁で完全に孤立している状態を作ります。この時点では、各セルがそれぞれ独立した「木（集合）」であると考えます。
 *  壁のリスト化: 取り除くことができる内部の壁をすべてリストアップし、その順番をランダムにシャッフルします。これがグラフの「辺（エッジ）」のリストに相当します。
 * 壁の除去と集合のマージ:
 *  シャッフルされた壁のリストを順に見ていきます。
 *  壁を取り除いたときに、その壁が隔てていた2つのセルが、まだ同じ集合（連結された通路）に属していない場合にのみ、その壁を破壊して通路にします。
 *  そして、その2つのセルが属していた集合を1つに統合（マージ）します。
 *  もし2つのセルが既に同じ集合に属している場合、その壁を壊すとサイクル（ループする道）ができてしまうため、壁はそのまま残します。
 * 完成: このプロセスを繰り返すと、最終的に全てのセルが1つの大きな集合に統合され、サイクルを持たない**「全域木」**が完成します。これにより、どのセルからでも他の全てのセルへ到達できる、単一の経路を持つ完璧な迷路が保証されます。
 *  この処理のために、集合の管理には**Union-Find（素集合データ構造）**という効率的なデータ構造を使用します。
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {

    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char START = 'S';
    private static final char GOAL = 'G';
    private static final Random random = new Random();

    // Union-Find (Disjoint Set Union) データ構造
    static class DisjointSet {
        private int[] parent;
        public DisjointSet(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) parent[rootI] = rootJ;
        }
    }
    // 内部の壁を表すクラス
    static class Wall {
        int r, c;
        public Wall(int r, int c) { this.r = r; this.c = c; }
    }

    public static char[][] generate(int height, int width) {
        if (height % 2 == 0) height++;
        if (width % 2 == 0) width++;
        char[][] maze = new char[height][width];
        List<Wall> walls = new ArrayList<>();
        int cellHeight = (height - 1) / 2;
        int cellWidth = (width - 1) / 2;
        DisjointSet ds = new DisjointSet(cellHeight * cellWidth);

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (r % 2 != 0 && c % 2 != 0) maze[r][c] = PATH;
                else maze[r][c] = WALL;
            }
        }
        for (int r = 1; r < height - 1; r++) {
            for (int c = 1; c < width - 1; c++) {
                if ((r % 2 == 0 && c % 2 != 0) || (r % 2 != 0 && c % 2 == 0)) {
                    walls.add(new Wall(r, c));
                }
            }
        }
        Collections.shuffle(walls);
        for (Wall wall : walls) {
            int r = wall.r, c = wall.c;
            int r1, c1, r2, c2;
            if (r % 2 == 0) { r1 = r - 1; c1 = c; r2 = r + 1; c2 = c; }
            else { r1 = r; c1 = c - 1; r2 = r; c2 = c + 1; }
            int cell1Index = ((r1 - 1) / 2) * cellWidth + ((c1 - 1) / 2);
            int cell2Index = ((r2 - 1) / 2) * cellWidth + ((c2 - 1) / 2);
            if (ds.find(cell1Index) != ds.find(cell2Index)) {
                ds.union(cell1Index, cell2Index);
                maze[r][c] = PATH;
            }
        }
        maze[1][0] = START;
        maze[height - 2][width - 1] = GOAL;
        return maze;
    }
}