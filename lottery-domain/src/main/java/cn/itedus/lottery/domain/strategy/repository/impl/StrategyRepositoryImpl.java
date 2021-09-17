package cn.itedus.lottery.domain.strategy.repository.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import cn.itedus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.itedus.lottery.domain.strategy.repository.StrategyRepository;
import cn.itedus.lottery.infrastructure.dao.IAwardDao;
import cn.itedus.lottery.infrastructure.dao.IStrategyDao;
import cn.itedus.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.itedus.lottery.infrastructure.po.Award;
import cn.itedus.lottery.infrastructure.po.Strategy;
import cn.itedus.lottery.infrastructure.po.StrategyDetail;

/**
 * @author wangkui 2021-09-08
 */
@Component
public class StrategyRepositoryImpl implements StrategyRepository {

    @Resource
    private IStrategyDao iStrategyDao;

    @Resource
    private IStrategyDetailDao iStrategyDetailDao;

    @Resource
    private IAwardDao iAwardDao;


    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        StrategyRich strategyRich = new StrategyRich();
        strategyRich.setStrategyId(strategyId);
        strategyRich.setStrategy(iStrategyDao.queryStrategy(strategyId));
        strategyRich.setStrategyDetailList(iStrategyDetailDao.queryStrategyDetailList(strategyId));
        return strategyRich;
    }

    @Override
    public Award queryAward(String awardId) {
        return iAwardDao.queryAwardInfo(awardId);
    }

    @Override
    public List<String> queryExcludeAwardIds(Long strategyId) {
        return iStrategyDetailDao.queryExcludeAwardIds(strategyId);
    }

}
