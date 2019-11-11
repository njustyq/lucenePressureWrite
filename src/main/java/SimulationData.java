import org.apache.commons.lang.RandomStringUtils;

/**
 * @author pandaqyang
 * @date 2019/11/7 14:44
 */
public class SimulationData {

    public static String simuString(int cLenth){
        RandomStringUtils randomStringUtils = new RandomStringUtils();
        String str = randomStringUtils.randomAlphanumeric(cLenth);
        return str;
    }
    public static long simuLong(int cLenth){
        long l = System.currentTimeMillis();
        if (cLenth > 13){
            for (int i=0;i<(cLenth-13);i++){
                l *= 10;
            }
        }
        else{
            Long index = 1L;
            for (int j=0;j<cLenth;j++){
                index *= 10;
             }
             l %= index;
        }
        return l;
    }


}
