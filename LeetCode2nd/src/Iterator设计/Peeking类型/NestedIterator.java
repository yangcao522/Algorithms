package Iterator设计.Peeking类型;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

public class NestedIterator implements Iterator<Integer> {

    Iterator<NestedInteger> cur;
    Stack<Iterator<NestedInteger>> stack;
    Integer peek;

    public NestedIterator(List<NestedInteger> nestedList){
        cur = nestedList.iterator();
        stack = new Stack<>();
        peek = internalNext();
    }

    public Integer internalNext(){
        if (cur.hasNext()) {
            NestedInteger tmp = cur.next();
            if(tmp.isInteger()) {
                return tmp.getInteger();
            } else {
                stack.push(cur);
                cur = tmp.getList().iterator();
                return internalNext();
            }
        } else if(!stack.isEmpty()){
            cur = stack.pop();
            return internalNext();
        } else {
            return null;
        }
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
