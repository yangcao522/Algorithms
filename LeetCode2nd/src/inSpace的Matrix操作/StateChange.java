package inSpace的Matrix操作;

public class StateChange {
    /**
     * 这类题目：如何用in space的方式完成状态转换
     * 核心技巧：在原来matrix的基础上记录状态
     * 第一个pass: 设置标记(难点在这里，如何在原matrix的内容上设置标记而不影响matrix内容)
     * 第二个pass: 根据标记更新状态
     */

    /**
     * 73. Set Matrix Zeroes
     * 通过矩阵的第一行和第一列记录状态
     */

    /**
     * 289. Game of Life
     * 通过正负数来记录状
     * <= 0代表当前是死了， -1， -2 可以表示不同的状态
     * >= 1达标当前是活着， 2， 3可以表示不同的状态
     * 所以不影响对当前是活着还是死了的判断
     */
}
