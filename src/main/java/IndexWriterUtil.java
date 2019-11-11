import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pandaqyang
 * @date 2019/11/7 21:26
 */
public class IndexWriterUtil {
    public String path1 = InitConf.getPath1();
    public String path2 = InitConf.getPath2();
    public String path3 = InitConf.getPath3();
    private RAMDirectory[] ramDirectory = new RAMDirectory[3];
    private IndexWriter[] ramWriter = new IndexWriter[3];
    private FSDirectory[] fsDirectory = new FSDirectory[3];
    private IndexWriter[] fsWriter = new IndexWriter[3];
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
            fsWriter[0] = new IndexWriter(fsDirectory[0], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));
            fsWriter[1] = new IndexWriter(fsDirectory[1], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));
            fsWriter[2] = new IndexWriter(fsDirectory[2], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initRamWriter() {
        try {
            ramDirectory[0] = new RAMDirectory();
            ramDirectory[1] = new RAMDirectory();
            ramDirectory[2] = new RAMDirectory();
            ramWriter[0] = new IndexWriter(ramDirectory[0], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
            ramWriter[1] = new IndexWriter(ramDirectory[1], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
            ramWriter[2] = new IndexWriter(ramDirectory[2], new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RAMDirectory[] getRamDirectory() {
        if (3 != ramDirectory.length){
            initRamWriter();
        }
        return ramDirectory;
    }
    public IndexWriter[] getRamWriter() {
        if (3 != ramDirectory.length){
            initRamWriter();
        }
        return ramWriter;
    }
    public FSDirectory[] getFsDirectory() {
        if (3 != ramDirectory.length){
            initFsWriter();
        }
        return fsDirectory;
    }
    public IndexWriter[] getFsWriter() {
        if (3 != ramDirectory.length){
            initFsWriter();
        }
        return fsWriter;
    }

    public static IndexWriterUtil getIndexWriterUtil() {
        return indexWriterUtil;
    }
}
