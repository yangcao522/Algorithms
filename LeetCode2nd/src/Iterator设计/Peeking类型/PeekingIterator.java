package Iterator设计.Peeking类型;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer>{
    /**
     * 284. Peeking Iterator
     * java的iterator是没有peek()方法的
     * 所以这里的peek()实际上返回的是已经准备好的当前值，iterator已经指到下一个数据。
     */
    Integer peek;
    Iterator<Integer> cur;
    public PeekingIterator(Iterator<Integer> iterator){
        cur = iterator;
        peek = internalNext();
    }

    public Integer internalNext(){
        if(cur.hasNext()){
            return cur.next();
        }else{
            return null;
        }

    }

    public Integer peek(){
        return peek;
    }

    @Override
    public boolean hasNext() {
        return peek != null;
    }

    @Override
    public Integer next() {
        Integer tmp = peek;
        peek = internalNext();
        return tmp;
    }
}
