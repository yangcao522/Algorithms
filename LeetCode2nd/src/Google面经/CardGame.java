package Google面经;

public class CardGame {
    public int firstPlayerScore(int[] cards) {
        int N = cards.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = cards[N - 1];
        dp[2] = Math.max(cards[N - 2], cards[N - 1] + cards[N - 2]);
        return search(N, dp, cards, N);
    }

    //还剩num张牌，
    private int search(int num, int[] dp, int[] cards, int n) {
        if(num < 0){
            return 0;
        }
        if(dp[num] != -1) return dp[num];
        int takeOne = cards[n - num] + min(search(num - 2, dp, cards, n),
                search(num - 3, dp, cards, n),
                search(num - 4, dp, cards, n));
        int takeTwo = cards[n - num - 1] + cards[n - num] +
                min(search(num - 3, dp, cards, n),
                        search(num - 4, dp, cards, n),
                        search(num - 5, dp, cards, n));
        int takeThree = cards[n - num - 2] + cards[n - num - 1] + cards[n - num] +
                min(search(num - 4, dp, cards, n),
                        search(num - 5, dp, cards, n),
                        search(num - 6, dp, cards, n));

        dp[num] = max(takeOne, takeTwo, takeThree);
        return dp[num];
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

}
