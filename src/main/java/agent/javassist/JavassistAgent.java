package agent.javassist;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @author yang.zhou 2019-09-09.14
 */
public class JavassistAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("This is the first agent");
        ClassFileTransformer transformer = new TimeCostTransFormer();
        instrumentation.addTransformer(transformer);
    }
}
