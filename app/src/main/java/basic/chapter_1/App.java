package basic.chapter_1;
// 副読本 p.5
// Scannerクラスを使用するために、プログラムの先頭で「おまじない」としてimport文を書きます。
import java.util.Scanner;

/**
 * 第1章: Javaの世界へようこそ！
 * プログラムの基本構造と、ユーザーからの入力を受け取る方法を学びます。
 *
 * 【このコードで学ぶこと】
 * 1. class, mainメソッド, System.out.println() の基本構造。
 * 2. Scannerクラスを使ったキーボードからの入力受付 。
 * 3. 文字列と変数を `+` で連結して表示する方法。
 */
public class App {

    public static void main(String[] args) {
        // --- 画面への出力 ---
        System.out.println("Hello, Java World!");

        // --- キーボードからの入力  ---
        // 1. キーボード入力を受け取る準備
        Scanner scanner = new Scanner(System.in);

        // 2. ユーザーに入力を促すメッセージを表示
        System.out.print("あなたの名前を入力してください: "); // printはprintlnと違い改行しない

        // 3. ユーザーが入力した文字列を受け取り、変数nameに保存
        String name = scanner.nextLine();

        // 4. 年齢の入力も受け取る
        System.out.print("あなたの年齢を入力してください: ");
        int age = scanner.nextInt();

        // 5. 受け取った情報を元にメッセージを表示
        System.out.println("こんにちは、" + name + "さん！ あなたは" + age + "歳なのですね。");

        // 6. Scannerを閉じる（リソースの解放）
        scanner.close();
    }
}