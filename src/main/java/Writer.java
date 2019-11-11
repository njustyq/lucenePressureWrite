/**
 * @author pandaqyang
 * @date 2019/11/8 17:10
 */
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
        import org.apache.lucene.index.IndexWriter;
        import org.apache.lucene.index.IndexWriterConfig;
        import org.apache.lucene.store.RAMDirectory;

        import java.io.IOException;

public class Writer {
    RAMDirectory ramDirectory;
    IndexWriter ramWriter;
    IndexWriter fsWriter;


    public Writer(RAMDirectory ramDirectory,IndexWriter ramWriter,IndexWriter fsWriter){
        this.ramDirectory = ramDirectory;
        this.ramWriter = ramWriter;
        this.fsWriter = fsWriter;

    }

    public void writeData(int dataSize){
        try {
            for (int indexNum=0;indexNum<dataSize;indexNum++){
                ramWriter.addDocument(DocumentUtils.getDoc());
            }
            ramWriter.commit();
            ramWriter.close();
            fsWriter.addIndexes(ramDirectory);
            ramDirectory.close();
            fsWriter.commit();
//            ramDirectory = new RAMDirectory();
//            ramWriter = new IndexWriter(ramDirectory, new IndexWriterConfig(new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}