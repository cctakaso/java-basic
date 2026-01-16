package basic.b_tutorials.maze;
/*
 * クルスカル法による迷路生成アルゴリズムの実装
 * グラフ理論におけるクルスカル法（Kruskal's Algorithm）を用いて、
 * SからGまでの経路が必ず存在する迷路を生成するJavaプログラムを作成します。
 * 迷路は2次元のグリッドで表現され、壁と通路で構成されます。
 * クルスカル法は、ランダムに壁を取り除きながら、全ての通路が連結されるまで続けます。
 * これにより、解が一意に定まる迷路が生成されます。迷路の開始点と終了点も設定されます。
 * 生成された迷路の例 (5x5):
 * #######
 * S #   #
 * # # # #
 * #   # G
 * #######
 * S:スタート地点, G:ゴール地点
 * #:壁,  :通路
 *
 * クルスカル法による迷路生成の理論
 *  このプログラムは、迷路をグラフと見立てて「最小全域木」を求めるクルスカル法を応用した
 * ものです。
 * 1) 初期状態:
 *   まず、格子状に壁を配置し、各通路（セル）が壁で完全に孤立している状態を
 * 作ります。この時点では、各セルがそれぞれ独立した「木（集合）」であると考えます。
 * 2) 壁のリスト化:
 *   取り除くことができる内部の壁をすべてリストアップし、その順番をランダムに
 * シャッフルします。これがグラフの「辺（エッジ）」のリストに相当します。
 * 3) 壁の除去と集合のマージ:
 *   シャッフルされた壁のリストを順に見ていきます。
 * 壁を取り除いたときに、その壁が隔てていた2つのセルが、まだ同じ集合（連結された通路）に
 * 属していない場合にのみ、その壁を破壊して通路にします。
 * そして、その2つのセルが属していた集合を1つに統合（マージ）します。
 * もし2つのセルが既に同じ集合に属している場合、その壁を壊すとサイクル（ループする道）が
 * できてしまうため、壁はそのまま残します。
 * 4) 完成:
 *   このプロセスを繰り返すと、最終的に全てのセルが1つの大きな集合に統合され、サイクルを
 * 持たない「全域木」が完成します。これにより、どのセルからでも他の全てのセルへ到達できる、
 * 単一の経路を持つ完璧な迷路が保証されます。この処理のために、集合の管理には Union-Find
 * （素集合データ構造）という効率的なデータ構造を使用します。
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {

    // マップ上で使用する文字定数
    private static final char WALL = '#';    // 壁
    private static final char PATH = ' ';    // 通路
    private static final char START = 'S';   // スタート位置
    private static final char GOAL = 'G';    // ゴール位置

    // 乱数（将来的にシード指定をする場合はここを変更することで再現可能な迷路を生成できます）
    private static final Random random = new Random();

    // Union-Find (Disjoint Set Union) データ構造
    /**
     * 簡易的な Union-Find (Disjoint Set) 実装
     * クルスカル法でセルの集合を管理し、サイクルを作らないようにするために使用します。
     */
    static class DisjointSet {
        private int[] parent;
        public DisjointSet(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }
        /**
         * パス圧縮ありの find
         */
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        /**
         * root をつなげる union（簡易版）
         */
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) parent[rootI] = rootJ;
        }
    }
    /**
     * 迷路生成時に扱う "内部の壁" を表す構造体的なクラス
     * r,c は迷路配列上の座標（行, 列）
     */
    static class Wall {
        int r, c;
        public Wall(int r, int c) { this.r = r; this.c = c; }
    }

    /**
     * 指定した高さ・幅でクルスカル法に基づく迷路を生成して返します。
     * パラメータは内部で奇数に丸められ、境界は壁で囲まれます。
     *
     * 実装のポイント:
     * - グリッドを"セル"(通路) と "内部の壁" に分けて扱う
     * - 内部の壁をランダムに処理し、Union-Find でセルが異なる集合のときだけ壁を壊す
     * - 壊すとその壁は通路となり、サイクルを作らない迷路ができる
     *
     * @param height 出力迷路の行数（偶数が与えられた場合は +1 して奇数にする）
     * @param width 出力迷路の列数（偶数が与えられた場合は +1 して奇数にする）
     * @return 生成された迷路（2次元 char 配列）
     */
    public static char[][] generate(int height, int width) {
        // 外枠を壁で囲み、内部セルがきれいに並ぶように高さ/幅を奇数にする
        if (height % 2 == 0) height++;
        if (width % 2 == 0) width++;

        char[][] maze = new char[height][width];
        List<Wall> walls = new ArrayList<>();

        // セル数（実際に通路となるセル）は ((height-1)/2) x ((width-1)/2)
        int cellHeight = (height - 1) / 2;
        int cellWidth = (width - 1) / 2;
        DisjointSet ds = new DisjointSet(cellHeight * cellWidth);

        // 初期化: 偶数行・偶数列を壁、それ以外（奇数,奇数）を通路セルにする
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (r % 2 != 0 && c % 2 != 0) maze[r][c] = PATH;
                else maze[r][c] = WALL;
            }
        }

        // 壁候補を収集 (上下または左右の内部壁のみ)
        for (int r = 1; r < height - 1; r++) {
            for (int c = 1; c < width - 1; c++) {
                // (偶数行,奇数列) または (奇数行,偶数列) が内部の壁に相当する
                if ((r % 2 == 0 && c % 2 != 0) || (r % 2 != 0 && c % 2 == 0)) {
                    walls.add(new Wall(r, c));
                }
            }
        }

        // 壁リストをシャッフルしてランダムな順序で処理する
        Collections.shuffle(walls);

        // 壁を順に見て、取り除ける（= 隣接セルが別集合）なら取り除く
        for (Wall wall : walls) {
            int r = wall.r, c = wall.c;
            int r1, c1, r2, c2;

            // 壁の向きによって、壁が仕切る左右または上下の2セルを決定
            if (r % 2 == 0) { // 水平方向の壁 (上下のセルをつなぐ)
                r1 = r - 1; c1 = c; r2 = r + 1; c2 = c;
            }
            else { // 垂直方向の壁 (左右のセルをつなぐ)
                r1 = r; c1 = c - 1; r2 = r; c2 = c + 1;
            }

            // セル座標を 1次元インデックスに変換して DisjointSet で比較
            int cell1Index = ((r1 - 1) / 2) * cellWidth + ((c1 - 1) / 2);
            int cell2Index = ((r2 - 1) / 2) * cellWidth + ((c2 - 1) / 2);

            // 異なる集合なら壁を壊して集合を統合する
            if (ds.find(cell1Index) != ds.find(cell2Index)) {
                ds.union(cell1Index, cell2Index);
                maze[r][c] = PATH; // 壁を取り除き通路にする
            }
        }

        // 開始・終了の固定位置を設置（左上の外枠は壁のままにしておく）
        maze[1][0] = START;                  // 入口
        maze[height - 2][width - 1] = GOAL;  // 出口
        return maze;
    }
}