package Iterator设计.Peeking类型;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;

/**
 * 251. Flatten 2D Vector
 */
public class Vector2D implements Iterator<Integer> {

    Iterator<List<Integer>> iter;
    Iterator<Integer> cur;
    Integer peek;

    public Vector2D(List<List<Integer>> vec2d){
        iter = vec2d.iterator();
        peek = internalNext();
    }

    public Integer internalNext(){
        if(cur != null && cur.hasNext()){
            return cur.next();
        }
        //cur 已经用完了，那么就从下一个iter中去取
        else if(iter.hasNext()){
            cur = iter.next().iterator();
            return internalNext();
        }
        else{
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
