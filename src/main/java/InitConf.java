import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author pandaqyang
 * @date 2019/11/11 10:23
 */
public class InitConf {
    static String path1 = null;
    static String path2 = null;
    static String path3 = null;
    static {
        init();
    }
    private static void init() {
        Properties pro = new Properties();
        try {
            pro.load(new FileInputStream("conf/config.properties"));
            String[] path = pro.getProperty("path").split(",");
            path1 = path[0];
            path2 = path[1];
            path3 = path[2];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPath1() {
        if (path1 == null){
            init();
        }
        return path1;
    }

    public static String getPath2() {
        if (path2 == null){
            init();
        }
        return path2;
    }

    public static String getPath3() {
        if (path3 == null){
            init();
        }
        return path3;
    }

    public static void main(String[] args) {
        System.out.println(getPath1());
        System.out.println(getPath2());
        System.out.println(getPath3());
    }
}
