package basic.tutorials.maze;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * クルスカル法による迷路生成と、その解法を自動で表示するプログラム
 */
public class MazeStep4 {
  //public class MazeKruskalAndSolver {

    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char START = 'S';
    private static final char GOAL = 'G';

    // --- ここからクルスカル法による迷路生成モジュール ---
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
    static class Wall {
        int r, c;
        public Wall(int r, int c) { this.r = r; this.c = c; }
    }
    public static char[][] generateMaze(int height, int width) {
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
    // --- 迷路生成モジュールここまで ---

    // --- ここから迷路解法モジュール ---
    public static List<Point> solve(char[][] maze) {
        Point start = findChar(maze, START);
        if (start == null) return null;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<Point> path = new ArrayList<>();
        if (solveRecursive(maze, visited, path, start.x, start.y)) {
            return path;
        }
        return null;
    }
    private static boolean solveRecursive(char[][] maze, boolean[][] visited, List<Point> path, int r, int c) {
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == WALL || visited[r][c]) {
            return false;
        }
        path.add(new Point(r, c));
        visited[r][c] = true;
        if (maze[r][c] == GOAL) return true;
        if (solveRecursive(maze, visited, path, r + 1, c)) return true;
        if (solveRecursive(maze, visited, path, r, c + 1)) return true;
        if (solveRecursive(maze, visited, path, r - 1, c)) return true;
        if (solveRecursive(maze, visited, path, r, c - 1)) return true;
        path.remove(path.size() - 1);
        visited[r][c] = false;
        return false;
    }
    // --- 迷路解法モジュールここまで ---

    // --- ユーティリティメソッド ---
    public static void printMaze(char[][] maze) { for(char[] row : maze) System.out.println(new String(row)); }
    public static char[][] cloneMaze(char[][] original) {
        char[][] copy = new char[original.length][];
        for(int i = 0; i < original.length; i++) copy[i] = original[i].clone();
        return copy;
    }
    private static Point findChar(char[][] maze, char target) {
        for (int i = 0; i < maze.length; i++) for (int j = 0; j < maze[i].length; j++) if (maze[i][j] == target) return new Point(i, j);
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- クルスカル法による迷路生成と自動解法 ---");
        System.out.print("迷路の高さ (height) > "); int height = scanner.nextInt();
        System.out.print("迷路の幅 (width) > "); int width = scanner.nextInt();

        char[][] originalMaze = generateMaze(height, width);
        System.out.println("\n--- 生成された迷路 ---");
        printMaze(originalMaze);

        System.out.println("\n...この迷路の経路を探索します...");
        List<Point> solutionPath = solve(originalMaze);

        if (solutionPath != null) {
            char[][] solutionDisplay = cloneMaze(originalMaze);
            for (int i = 0; i < solutionPath.size() - 1; i++) {
                Point current = solutionPath.get(i);
                Point next = solutionPath.get(i + 1);
                if(solutionDisplay[current.x][current.y] == START) continue;
                if (next.x > current.x) solutionDisplay[current.x][current.y] = 'v';
                else if (next.x < current.x) solutionDisplay[current.x][current.y] = '^';
                else if (next.y > current.y) solutionDisplay[current.x][current.y] = '>';
                else if (next.y < current.y) solutionDisplay[current.x][current.y] = '<';
            }
            System.out.println("\n--- 解法 ---");
            printMaze(solutionDisplay);
        } else {
            System.out.println("\n解法が見つかりませんでした。(このプログラムでは発生しません)");
        }
        scanner.close();
    }
}