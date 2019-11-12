/**
 * @author pandaqyang
 * @date 2019/11/7 16:40
 */
public class TupleUtil {
    private long length = 0;
    private boolean isString = true;
    TupleUtil(boolean isString,long length){
        this.isString = isString;
        this.length = length;
    }
    public long getLength() {
        return length;
    }
    public boolean getIsString() {
        return isString;
    }
}
