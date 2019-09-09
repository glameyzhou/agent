package agent.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * 通过AgentBuilder方法，生成一个Agent。
 * 这里有两点需要特别说明：
 * 其一是在AgentBuilder.type处，这里可以指定需要拦截的类；
 * 其二是在builder.method处，这里可以指定需要拦截的方法。
 * 当然其API支持各种isStatic、isPublic等等一系列方式。
 *
 * @author yang.zhou 2019-09-09.14
 */
public class ByteBuddyAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("This is a byte buddy agent");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                    TypeDescription typeDescription,
                                                    ClassLoader classLoader, JavaModule javaModule) {
                return builder.method(ElementMatchers.any()) //拦截任意方法
                        .intercept(MethodDelegation.to(TimeCostInterceptor.class));//委托类
            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
//                System.out.println("on transformation " + typeDescription + " " + javaModule + " " + b + " " + dynamicType);
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//                System.out.println("on complete " + s + " " + javaModule + " " + b);
            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("org.glamey.example.AgentDemo")) // 指定需要拦截的类
                .transform(transformer)
                .with(listener)
                .installOn(instrumentation);
    }
}
