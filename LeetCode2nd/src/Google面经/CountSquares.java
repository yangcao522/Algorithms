package Google面经;

public class CountSquares {
    /**
     * 输入是一堆线段，找所有可以形成的正方形的数量，自己设计数据结构和算法。
     * clarify之后如下，平面无限大，线段都是水平或者竖直，不会cross，但端点可能在另一个线段上。
     * 解法大概是枚举对角线，然后check四条边，但是不能直接check每一条边是不是在input 的线段当中，
     * 因为可能正方形的一条边是由多个input线段组成的，或者完全被包含在某一个线段当中，
     * 所以需要对所有x和y值建segment tree，然后每一个查四条边是不是完全被当前坐标的segment tree cover
     */
}
