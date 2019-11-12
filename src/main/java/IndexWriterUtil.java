import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author pandaqyang
 * @date 2019/11/7 21:26
 */
public class IndexWriterUtil {
    public String path1 = InitConf.getPath1();
    public String path2 = InitConf.getPath2();
    public String path3 = InitConf.getPath3();
    public String path4 = InitConf.getPath4();
    private RAMDirectory[] ramDirectory = new RAMDirectory[4];
    private IndexWriter[] ramWriter = new IndexWriter[4];
    private FSDirectory[] fsDirectory = new FSDirectory[4];
    private IndexWriter[] fsWriter = new IndexWriter[4];
    private static IndexWriterUtil indexWriterUtil = new IndexWriterUtil();
    private IndexWriterUtil(){
        initFsWriter();
        initRamWriter();
    }

    private void initFsWriter() {
        try {
            fsDirectory[0] = FSDirectory.open(Paths.get(path1));
            fsDirectory[1] = FSDirectory.open(Paths.get(path2));
            fsDirectory[2] = FSDirectory.open(Paths.get(path3));
            fsDirectory[3] = FSDirectory.open(Paths.get(path4));
            fsWriter[0] = new IndexWriter(fsDirectory[0], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));
            fsWriter[1] = new IndexWriter(fsDirectory[1], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));
            fsWriter[2] = new IndexWriter(fsDirectory[2], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));
            fsWriter[3] = new IndexWriter(fsDirectory[3], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initRamWriter() {
        try {
            ramDirectory[0] = new RAMDirectory();
            ramDirectory[1] = new RAMDirectory();
            ramDirectory[2] = new RAMDirectory();
            ramDirectory[3] = new RAMDirectory();
            ramWriter[0] = new IndexWriter(ramDirectory[0], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
            ramWriter[1] = new IndexWriter(ramDirectory[1], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
            ramWriter[2] = new IndexWriter(ramDirectory[2], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
            ramWriter[3] = new IndexWriter(ramDirectory[3], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RAMDirectory[] getRamDirectory() {
        if (4 != ramDirectory.length){
            initRamWriter();
        }
        return ramDirectory;
    }
    public IndexWriter[] getRamWriter() {
        if (4 != ramDirectory.length){
            initRamWriter();
        }
        return ramWriter;
    }
    public FSDirectory[] getFsDirectory() {
        if (4 != ramDirectory.length){
            initFsWriter();
        }
        return fsDirectory;
    }
    public IndexWriter[] getFsWriter() {
        if (4 != ramDirectory.length){
            initFsWriter();
        }
        return fsWriter;
    }

    public static IndexWriterUtil getIndexWriterUtil() {
        return indexWriterUtil;
    }
}
