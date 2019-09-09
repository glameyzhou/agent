package agent.origin;

import java.lang.instrument.Instrumentation;

/**
 * @author yang.zhou 2019-09-09.10
 */
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("pre main agent..");
        System.out.println(agentArgs);
    }
}
