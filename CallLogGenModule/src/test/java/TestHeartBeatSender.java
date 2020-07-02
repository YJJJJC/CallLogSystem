import com.callloggen.udp.HeartBeatThread;
import org.junit.Test;

/**
 * @author:yjc
 * @Date: 2019/7/7 20:21
 * @Description:
 */
public class TestHeartBeatSender {

    @Test
    public void test1() throws Exception{
        new HeartBeatThread().start();
        while (true){
            Thread.sleep(1000);
        }
    }
}
