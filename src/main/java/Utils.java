import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pandaqyang
 * @date 2019/11/7 16:54
 */
public class Utils {
    static String[] fields = ("time_stamp,spe_tag,sign,fetch_sync,refund_type,refund_reason,pay_channel,loading_type,loading_cuin," +
            "loading_listid,transit_spid,uid,fetch_result,Fstandby1,fetch_fail_reason,Fstandby2 ,data_version," +
            "balance,balance_time,balance_time_stamp").split(",");
    static String[] originalData = ("1569464937000,10553,wx26102287700,821051007,wxbbdca62c011eeb38,CNY,654313022,aaaass,152437543,esfwerwerwerewewrew,58," +
            "3853029863,4950,46,4200000401201909264501593250," +
            "aab32423423ab32423423ab32423423ab32423423b32423423,2,1,33456d3e4d3456d3e4d3456d3e4d3456d3e4d456d3e4d,2").split(",");
    public static Pattern pattern = Pattern.compile("[0-9]*");
    static TupleUtil[] tuple = new TupleUtil[originalData.length];
    public static boolean isNumeric(String str){
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
     static void initTuple(){
        for (int i=0;i<tuple.length;i++){
            tuple[i] = new TupleUtil(isNumeric(originalData[i]),originalData[i].length());
        }
    }
    static {
        initTuple();
    }

    public static TupleUtil[] getTuple() {
        return tuple;
    }

    public static String[] getFields(){
        return fields;
    }
    public static void main(String[] args) {
        System.out.println(isNumeric(""));
        System.out.println(fields.length);
        System.out.println(originalData.length);
    }
}
