import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

/**
 * @author pandaqyang
 * @date 2019/11/7 17:07
 */
public class TestApi {
    long l = System.currentTimeMillis();
    @Test
    public void testLong(){
        l %= 10;
        System.out.println(l);
        System.out.println(SimulationData.simuLong(13));
    }
    @Test
    public void testString(){
        System.out.println(SimulationData.simuString(5));
    }

}
