import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * @author pandaqyang
 * @date 2019/11/7 14:12
 */
public class PressureWrite {

    public static void main(String[] args) throws IOException {
        args = new String[]{"1", "1", "3"};
        if (args.length != 3){
            System.out.println("args must be \"groups\" \"numPerGroup\" \"threadNum\"");
            return;
        }
        int groups = Integer.parseInt(args[0]);
        int numPerGroup = Integer.parseInt(args[1]);
        int threadNum = Integer.parseInt(args[2]);
        int threadNum1 = (threadNum+2)/3;
        int threadNum2 = (threadNum+1)/3;
        int threadNum3 = threadNum/3;
        WriteThread[] writeThreads = new WriteThread[threadNum];
        RAMDirectory[] ramDirectory = IndexWriterUtil.getIndexWriterUtil().getRamDirectory();
        IndexWriter[] ramWriter = IndexWriterUtil.getIndexWriterUtil().getRamWriter();
        IndexWriter[] fsWriter = IndexWriterUtil.getIndexWriterUtil().getFsWriter();
        Writer writer1 = new Writer(ramDirectory[0],ramWriter[0],fsWriter[0]);
        Writer writer2 = new Writer(ramDirectory[1],ramWriter[1],fsWriter[1]);
        Writer writer3 = new Writer(ramDirectory[2],ramWriter[2],fsWriter[2]);
        int threadIndex = 0;
        for (int i=0;i<threadNum1;i++){
            writeThreads[threadIndex] = new WriteThread(writer1,numPerGroup,groups);
            threadIndex++;
        }
        for (int i=0;i<threadNum2;i++){
            writeThreads[threadIndex] = new WriteThread(writer2,numPerGroup,groups);
            threadIndex++;
        }
        for (int i=0;i<threadNum3;i++){
            writeThreads[threadIndex] = new WriteThread(writer3,numPerGroup,groups);
            threadIndex++;
        }
        long startTime = System.currentTimeMillis();
        System.out.println("start time at: " + startTime);
        for (int i=0;i<threadIndex;i++){
            writeThreads[i].start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("end time at: " + endTime);
        System.out.println("cos time: " + (endTime -startTime) + "ms" );
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(fsWriter[0].getDirectory()));
        int count = indexSearcher.count(new MatchAllDocsQuery());
        System.out.println(count);
    }

}