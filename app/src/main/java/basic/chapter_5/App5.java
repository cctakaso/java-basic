package basic.chapter_5;

/**
 * 第5章: もう一つの選択肢！スマートな分岐 switch文
 *
 * 【このコードで学ぶこと】
 * - switch文の基本 (case, break, default)。
 * - enum型と組み合わせた使い方 
 * - 新しいswitch式 (アロー構文, Java 14+) 
 */
public class App5 {

    // 曜日を表現するためのenum (列挙型) を定義（← 但し、オブジェクト指向設計編で学びます）
    public enum DayOfWeek {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    };

    public static void main(String[] args) {

        // --- 基本的なint型を使ったswitch文 ---
        int commandNum = 1;
        System.out.println("\nコマンド: " + commandNum);
        switch (commandNum) {
            case 0:
                System.out.println("音楽を停止します。");
                break;
            case 1:
                System.out.println("音楽を再生します。");
                break;
            default:
                System.out.println("コマンドが不正です。");
                break;
        }

        // --- 基本的なString型を使ったswitch文 ---
        String command = "play";
        System.out.println("\nコマンド: " + command);
        switch (command) {
            case "stop":
                System.out.println("音楽を停止します。");
                break;
            case "play":
                System.out.println("音楽を再生します。");
                break;
            default:
                System.out.println("コマンドが不正です。");
                break;
        }

        // --- enumを使ったswitch文  ---
        DayOfWeek today = DayOfWeek.WEDNESDAY;
        System.out.println("\n今日は " + today + " です。");
        switch (today) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println("平日です。");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("休日です。");
                break;
        }

        // --- 新しいswitch式 (アロー構文, Java 14+)  ---
        // breakが不要になり、より簡潔に書ける。
        System.out.println("\n(アロー構文)");
        System.out.println("今日は " + today + " です。");
        switch (today) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
                -> System.out.println("平日です。");
            case SATURDAY, SUNDAY -> System.out.println("休日です。");
        }
    }
}