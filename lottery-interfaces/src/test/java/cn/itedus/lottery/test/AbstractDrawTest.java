package cn.itedus.lottery.test;


import java.util.HashMap;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.itedus.lottery.domain.strategy.model.req.DrawReq;
import cn.itedus.lottery.domain.strategy.model.res.DrawResult;
import cn.itedus.lottery.domain.strategy.service.draw.IDrawExec;

/**
 * @author wangkui 2021-09-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractDrawTest {

    @Resource
    IDrawExec iDrawExec;

    @Test
    public void doDrawExec() {
        DrawReq req = new DrawReq(-1L,10001L);
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            DrawResult drawResult = iDrawExec.doDrawExec(req);
            System.out.println(drawResult.toString());

            if(map.containsKey(drawResult.getAwardId())){
                map.put(drawResult.getAwardId(),map.get(drawResult.getAwardId()) + 1);
            }else{
                map.put(drawResult.getAwardId(),1);
            }
        }
        for (Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"==============="+entry.getValue());
        }
    }

}