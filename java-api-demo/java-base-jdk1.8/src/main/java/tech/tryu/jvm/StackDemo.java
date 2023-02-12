package tech.tryu.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ${DESCRIPTION}
 *
 * @author ${USER}
 * @date ${YEAR}-${MONTH}-${DAY} ${TIME}
 * @since 1.0.0
 */
public class StackDemo{
    private static  int count = 0;

    private final static Logger logger = LoggerFactory.getLogger(StackDemo.class);
    public static void main(String[] args) {

        byte a=1;
        short b=2;
        int c=3;
        long d=4L;
        float e=1.0f;
        double f=6d;
        char g = '1';
        boolean h = false;
        try {
           /* emptyCallMethod();*/ // -Xss1K count = 23956
           /* fiveParameterCallMethod(a,b,c,d,e); */ // -Xss1K   count = 13124
            eightParameterCallMethod(a,b,c,d,e,f,g,h); // -Xss1K count = 8996
        }catch (Throwable ex) {
            logger.debug("deep of calling = " + count);
            logger.error(ex.getMessage(),ex);
        }
    }
    private static void emptyCallMethod(){
        count ++;
        emptyCallMethod();
    }

    private static void fiveParameterCallMethod(byte a, short b, int c, long d, float e){
        count ++;
        fiveParameterCallMethod(a,b,c,d,e);
    }

    private static void eightParameterCallMethod(byte a, short b, int c, long d, float e, double f, char g, boolean h){
        count ++;
        eightParameterCallMethod(a, b, c, d, e, f, g, h);
    }
}