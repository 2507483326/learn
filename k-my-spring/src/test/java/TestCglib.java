import com.epat.Controller;
import com.epat.aspect.BaseAspect;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

/**
 * @author 李涛
 * @date : 2021/7/9 15:43
 */
public class TestCglib {

    public static void main(String[] args) {
        Controller controller = new Controller();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(controller.getClass());
        enhancer.setCallbacks(new Callback[]{new BaseAspect(null), NoOp.INSTANCE});
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if (method.getDeclaringClass().getName().equals(Object.class.getName())) {
                    return 1;
                }
                return 0;
            }
        });
        Object newBean = enhancer.create();
        System.out.println(newBean);
    }
}
