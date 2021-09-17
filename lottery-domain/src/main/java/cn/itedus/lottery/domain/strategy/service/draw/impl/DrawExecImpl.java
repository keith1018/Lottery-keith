package cn.itedus.lottery.domain.strategy.service.draw.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.itedus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.itedus.lottery.domain.strategy.model.req.DrawReq;
import cn.itedus.lottery.domain.strategy.model.res.DrawResult;
import cn.itedus.lottery.domain.strategy.repository.StrategyRepository;
import cn.itedus.lottery.domain.strategy.repository.impl.StrategyRepositoryImpl;
import cn.itedus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.itedus.lottery.domain.strategy.service.draw.AbstractDraw;
import cn.itedus.lottery.domain.strategy.service.draw.IDrawExec;
import cn.itedus.lottery.infrastructure.po.Award;

/**
 * @author wangkui 2021-09-09
 */
@Service
public class DrawExecImpl extends AbstractDraw {

    @Resource
    private StrategyRepository strategyRepository;

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {
        String awardId = drawAlgorithm.randomDraw(strategyId,excludeAwardIds);
        return awardId;
    }

    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        return strategyRepository.queryExcludeAwardIds(strategyId);
    }

}
