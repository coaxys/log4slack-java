import com.coaxys.log4slack.SlackAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class TestSlackAppender {

    private final static Logger log = Logger.getLogger(TestSlackAppender.class);
    private final static String SLACK_APPENDER_NAME = "Slack";
    private final static String LOG4J_PROPS = "src/test/resources/log4j.properties";
    //private final SlackAppender appender;

    public TestSlackAppender() throws Exception {
        //PropertyConfigurator.configure(LOG4J_PROPS);
        //appender = (SlackAppender) Logger.getRootLogger().getAppender(SLACK_APPENDER_NAME);
    }

    @Test
    public void testSingleLogEntry() throws Exception {
        log.error("test entry");
    }
}
