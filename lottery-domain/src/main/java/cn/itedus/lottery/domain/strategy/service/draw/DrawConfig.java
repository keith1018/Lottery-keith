package cn.itedus.lottery.domain.strategy.service.draw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import cn.itedus.lottery.common.Constants.StrategyMode;
import cn.itedus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

/**
 * @author wangkui 2021-09-13
 */
public class DrawConfig {

    @Resource
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    /** 抽奖策略组 */
    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmGroup.put(StrategyMode.ENTIRETY.getCode(), entiretyRateRandomDrawAlgorithm);
        drawAlgorithmGroup.put(StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
    }


}
