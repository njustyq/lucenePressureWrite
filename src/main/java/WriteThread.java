import java.util.concurrent.CountDownLatch;

/**
 * @author pandaqyang
 * @date 2019/11/8 10:26
 */
public class WriteThread extends Thread {
    Writer writer;
    private int groups;
    private int numPerGroup;
    CountDownLatch countDownLatch;
    public WriteThread(Writer writer,int numPerGroup,int groups,CountDownLatch countDownLatch){
        this.writer = writer;
        this.groups = groups;
        this.numPerGroup = numPerGroup;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        super.run();
        int groupCounter = 0;
        while (groupCounter < groups){
            writer.writeData(numPerGroup);
            groupCounter++;
        }
        countDownLatch.countDown();
    }
}
