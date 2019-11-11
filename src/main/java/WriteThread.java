
/**
 * @author pandaqyang
 * @date 2019/11/8 10:26
 */
public class WriteThread extends Thread {
    Writer writer;
    private int groups;
    private int numPerGroup;
    public WriteThread(Writer writer,int numPerGroup,int groups){
        this.writer = writer;
        this.groups = groups;
        this.numPerGroup = numPerGroup;
    }

    @Override
    public void run() {
        super.run();
        int groupCounter = 0;
        while (groupCounter < groups){
            writer.writeData(numPerGroup);
            groupCounter++;
        }
    }
}
