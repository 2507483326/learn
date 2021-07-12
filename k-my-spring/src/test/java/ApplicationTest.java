import com.epat.Application;
import com.epat.Controller;
import com.epat.model.School;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 李涛
 * @date : 2021/7/4 19:10
 */
public class ApplicationTest {
    public static Logger logger =  LogManager.getLogger(ApplicationTest.class);

    public static void main(String[] args) throws Exception {
        Application application = new Application("application.json");
        Controller controller = (Controller) application.getBean("controller");
        controller.say();
    }

}
