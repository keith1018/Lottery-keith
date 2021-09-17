package cn.itedus.lottery.domain.strategy.service.draw;

import javax.annotation.Resource;
import cn.itedus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.itedus.lottery.domain.strategy.repository.StrategyRepository;
import cn.itedus.lottery.infrastructure.po.Award;

/**
 * @author wangkui 2021-09-14
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    private StrategyRepository strategyRepository;

    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }

    protected Award queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAward(awardId);
    }

}
