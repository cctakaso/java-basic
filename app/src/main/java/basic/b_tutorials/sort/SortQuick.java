package basic.b_tutorials.sort;

/**
 * クイックソート (Quick Sort)
 *
 * 考え方 (分割統治法):
 * 1. 配列内から「ピボット」となる要素を1つ選択する。
 * 2. ピボットを基準にして、配列を「ピボットより小さい要素のグループ」と「ピボットより大きい要素のグループ」に分割する。
 * 3. 分割された2つのグループに対して、それぞれ再帰的にクイックソートを適用する。
 * 4. グループの要素数が1以下になったら、そのグループはソート済みとみなし、再帰を終了する。
 * この処理を繰り返すことで、最終的に配列全体がソートされる。
 * ピボットの選択方法や分割の実装方法によって、パフォーマンスが変動する特性がある。
 */
public class SortQuick {

    /**
     * クイックソートアルゴリズムを使用して整数配列をソートします。
     * @param array ソートする整数配列
     * @return ソートされた整数配列
     */
    public static int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 配列を再帰的に分割してソートするためのメソッド
     * @param array 対象の配列
     * @param low 分割範囲の開始インデックス
     * @param high 分割範囲の終了インデックス
     */
    private static void quickSort(int[] array, int low, int high) {
        // 開始インデックスが終了インデックスより小さい場合（＝分割する要素が2つ以上ある場合）
        if (low < high) {
            // partitionメソッドでピボットの位置を確定し、そのインデックスを取得
            int pi = partition(array, low, high);

            // ピボットを基準に、2つの部分配列に分けて再帰的にソート
            quickSort(array, low, pi - 1);  // ピボットより小さい要素のグループ
            quickSort(array, pi + 1, high); // ピボットより大きい要素のグループ
        }
    }

    /**
     * 配列をピボットを基準に分割し、ピボットの最終的な位置を返すメソッド
     * @param array 対象の配列
     * @param low 分割範囲の開始インデックス
     * @param high 分割範囲の終了インデックス
     * @return ピボットの最終的なインデックス
     */
    private static int partition(int[] array, int low, int high) {
        // 配列の最後の要素をピボット（基準値）として選択
        int pivot = array[high];
        
        // ピボットより小さい要素を置くべき位置のインデックス
        // 初期値はlowの一つ前
        int i = (low - 1);

        // lowからhigh-1までを走査
        for (int j = low; j < high; j++) {
            // 現在の要素がピボットより小さい場合
            if (array[j] < pivot) {
                i++; // 小さい要素を置く位置を一つ進める
                // array[i]とarray[j]を交換
                // これにより、array[i]より左側はピボットより小さい要素が集まる
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // 最後にピボットを正しい位置に移動させる
        // i+1の位置がピボットの入るべき場所
        int temp = array[i + 1];
        array[i + 1] = array[high]; // array[high]はピボット
        array[high] = temp;

        // ピボットの最終的な位置のインデックスを返す
        return i + 1;
    }
}
