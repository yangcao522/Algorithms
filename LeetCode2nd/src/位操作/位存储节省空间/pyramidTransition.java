package 位操作.位存储节省空间;

import java.util.List;

public class pyramidTransition {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        int[][] tr = new int[7][7];
        for (String s : allowed)
            tr[s.charAt(0) - 'A'][s.charAt(1) - 'A'] |= 1 << (s.charAt(2) - 'A');
        return pyramid(bottom.toCharArray(), tr, 0, new char[bottom.length() - 1]);
    }

    boolean pyramid(char[] row, int[][] tr, int i, char[] nextRow) {
        if (row.length == 1) return true;
        if (i == row.length - 1)
            return pyramid(nextRow, tr, 0, new char[nextRow.length - 1]);
        int bit = tr[row[i] - 'A'][row[i + 1] - 'A'];
        for (int j = 0; j < 7; j++)
            if ((bit & (1 << j)) != 0) {
                nextRow[i] = (char) ('A' + j);
                if (pyramid(row, tr, i + 1, nextRow))
                    return true;
            }
        return false;
    }
}
