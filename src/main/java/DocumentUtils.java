import org.apache.lucene.document.*;

/**
 * @author pandaqyang
 * @date 2019/11/7 16:12
 */
public class DocumentUtils {

    public static Document getDoc(){
        Document doc = new Document();
        TupleUtil[] tuples = Utils.getTuple();
        for (int i=0;i<10;i++){
            if (tuples[i].getIsString()){
                doc.add(new StringField(Utils.getFields()[i],SimulationData.simuString((int) tuples[i].getLength()),Field.Store.YES));
            }
            else{
                doc.add(new LongPoint(Utils.getFields()[i],SimulationData.simuLong((int) tuples[i].getLength())));
            }
        }
        for (int i=10;i<tuples.length;i++){
            if (tuples[i].getIsString()){
                doc.add(new StoredField(Utils.getFields()[i],SimulationData.simuString((int) tuples[i].getLength())));
            }
            else{
                doc.add(new StoredField(Utils.getFields()[i],SimulationData.simuLong((int) tuples[i].getLength())));
            }
        }
        return doc;
    }



}
