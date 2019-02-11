package Google面经;

/**
 * 这个limiter允许有n个events工作
 * 调用一次MayContinue，就消耗一个event
 * 当event消耗完了，就进入冷却时间
 */
public class MyLimiter {
    private int events;
    private int coolDown;
    private long startCoolDown;
    private int currEvents;

    public MyLimiter(int events, int coolDown) {
        this.events = events;
        this.coolDown = coolDown;
        this.currEvents = events;
    }

    public boolean MayContinue() {
        if (currEvents > 0) {
            currEvents --;
            if (currEvents == 0) {
                startCoolDown = System.currentTimeMillis();
            }
            return true;
        } else {
            long curTime = System.currentTimeMillis();
            if (curTime - startCoolDown < coolDown) {
                return false;
            } else {
                currEvents = events;
                currEvents --;
                if (currEvents == 0) {
                    startCoolDown = System.currentTimeMillis();
                }
            }
        }
        return true;
    }
}
