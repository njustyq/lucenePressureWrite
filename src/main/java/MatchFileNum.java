import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * @author pandaqyang
 * @date 2019/11/11 15:22
 */
public class MatchFileNum {

    public static void main(String[] args) throws IOException {
        RAMDirectory[] ramDirectory = IndexWriterUtil.getIndexWriterUtil().getRamDirectory();
        IndexWriter[] ramWriter = IndexWriterUtil.getIndexWriterUtil().getRamWriter();
        IndexWriter[] fsWriter = IndexWriterUtil.getIndexWriterUtil().getFsWriter();
        Writer writer1 = new Writer(ramDirectory[0],ramWriter[0],fsWriter[0]);
        Writer writer2 = new Writer(ramDirectory[1],ramWriter[1],fsWriter[1]);
        Writer writer3 = new Writer(ramDirectory[2],ramWriter[2],fsWriter[2]);
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(fsWriter[2].getDirectory()));
        int count = indexSearcher.count(new MatchAllDocsQuery());
        System.out.println(count);
    }
}
