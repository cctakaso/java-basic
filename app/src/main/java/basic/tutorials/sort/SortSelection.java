package basic.tutorials.sort;

/**
 * 選択ソート (Selection Sort)
 *
 * 考え方:
 * 1. 配列の中から最小値（または最大値）を見つけ出す。
 * 2. 見つけ出した最小値と、配列の未ソート部分の先頭の要素とを交換する。
 * 3. 交換したことで、先頭の要素はソート済みとなる。
 * 4. 未ソート部分の範囲を一つ狭め、1〜3の処理を繰り返す。
 * これを配列のすべての要素がソート済みになるまで続けることで、全体のソートが完了する。
 */
public class SortSelection {

    /**
     * 選択ソートアルゴリズムを使用して整数配列をソートします。
     * @param array ソートする整数配列
     * @return ソートされた整数配列
     */
    public static int[] sort(int[] array) {
        // 配列の左端から順に、各位置に収まるべき要素を選択していく
        for (int i = 0; i < array.length - 1; i++) {
            // 未ソート部分の先頭を、仮の最小値のインデックスとする
            int minIndex = i;
            // 未ソート部分全体を走査して、真の最小値を見つける
            for (int j = i + 1; j < array.length; j++) {
                // 現在の最小値よりも小さい値が見つかれば、そのインデックスを更新
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 見つかった最小値（array[minIndex]）を、現在の位置（array[i]）の要素と交換する
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
