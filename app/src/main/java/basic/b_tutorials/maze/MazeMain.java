package basic.b_tutorials.maze;

import java.util.Scanner;

public class MazeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- 3つの探索アルゴリズムの比較 ---");
        System.out.print("迷路の高さ > "); int height = scanner.nextInt();
        System.out.print("迷路の幅 > "); int width = scanner.nextInt();

        // 1. 迷路を生成
        char[][] maze = MazeGenerator.generate(height, width);
        System.out.println("\n--- 生成された迷路 ---");
        for(char[] row : maze) System.out.println(new String(row));

        // A. java基本構文だけで探索・結果表示
        System.out.println("\n--- A. java基本構文だけで探索・結果表示 ---");
        System.out.println("\n--- 0. 右手法での探索結果 ---");
        MazeSolver_RightHandRule.solveAndPrint(maze);

        System.out.println("\n--- 1. 深さ優先探索Basic (DFS) ---");
        MazeSolver_DFS_Basic.solveAndPrint(maze);

        System.out.println("\n--- 2. 幅優先探索Basic (BFS) ---");
        MazeSolver_BFS_Basic.solveAndPrint(maze);

        System.out.println("\n--- 3. A*探索Basic ---");
        MazeSolver_AStar_Basic.solveAndPrint(maze);

        // B. javaコレクションなど使って探索・結果表示
        System.out.println("\n--- B. javaコレクションなど使って探索・結果表示 ---");
        System.out.println("\n--- 1. 深さ優先探索 (DFS) ---");
        MazeSolver_DFS.solveAndPrint(maze);

        System.out.println("\n--- 2. 幅優先探索 (BFS) ---");
        MazeSolver_BFS.solveAndPrint(maze);

        System.out.println("\n--- 3. A*探索 ---");
        MazeSolver_AStar.solveAndPrint(maze);

        scanner.close();
    }
}