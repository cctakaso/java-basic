package basic.b_chapter_0;
/**
 * 第0章: Javaの世界へようこそ！
 * Javaプログラミングの最も基本的な構造を学びます。
 *
 * 【このコードで学ぶこと】
 * 1. Javaプログラムの骨格である `class` と `main` メソッド。
 * 2. 画面に文字を表示する命令 `System.out.println()` 。
 * 3. プログラムの文の終わりを示すセミコロン `;` 。
 * 4. コードにメモを残すコメントの書き方。
 *
 * 【実行方法】
 * 1. コンパイル: javac app/src/main/java/basic/chapter_0/BasicApp0.java
 * 2. 実行: java -cp app/src/main/java/ basic/chapter_0/BasicApp0
 *
 * 【期待される出力】
 * Hello, Java World!
 */
// public class クラス名 { ... } というのがJavaプログラムの基本単位です。
// クラス名はファイル名 (0-App.java) と一致させる必要があります。
public class App {

    // `main` メソッドは、プログラムを実行したときに最初に呼び出される特別な玄関口です。
    // public static void main(String[] args) { ... } はお決まりの形として覚えましょう。
    public static void main(String[] args) {
        System.out.println("Hello, Java World!"); // "Hello, Java World!"と表示
    }
}