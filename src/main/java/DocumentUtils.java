import org.apache.lucene.document.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.lucene.document.StringField.TYPE_STORED;

/**
 * @author pandaqyang
 * @date 2019/11/7 16:12
 */
public class DocumentUtils {

    public static Document getDoc(){
        Document doc = new Document();
        TupleUtil[] tuples = Utils.getTuple();
        for (int i=0;i<10;i++){
            if (tuples[i].isString){
                doc.add(new StringField(Utils.getFields()[i],SimulationData.simuString((int) tuples[i].length),Field.Store.YES));
            }
            else{
                doc.add(new LongPoint(Utils.getFields()[i],SimulationData.simuLong((int) tuples[i].length)));
            }
        }
        for (int i=10;i<tuples.length;i++){
            if (tuples[i].isString){
                doc.add(new StoredField(Utils.getFields()[i],SimulationData.simuString((int) tuples[i].length)));
            }
            else{
                doc.add(new StoredField(Utils.getFields()[i],SimulationData.simuLong((int) tuples[i].length)));
            }
        }
        return doc;
    }



}
