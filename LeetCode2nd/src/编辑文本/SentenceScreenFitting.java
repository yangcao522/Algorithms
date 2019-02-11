package 编辑文本;

public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int index = 0;
        int N = sentence.length;
        for (int i = 0; i < rows; i++) {
            int j = 0;
            while (j < cols) {
                if (sentence[index % N].length() <= cols - j) {
                    j += (sentence[index % N].length() + 1);
                    index ++;
                } else {
                    break;
                }
            }
        }
        return index / N;
    }
}
