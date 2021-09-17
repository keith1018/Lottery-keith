package cn.itedus.lottery.domain.strategy.service.algorithm.impl;

import java.security.SecureRandom;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.itedus.lottery.domain.strategy.repository.StrategyRepository;
import cn.itedus.lottery.domain.strategy.service.algorithm.BaseInitAwardArrayAlgorithm;
import cn.itedus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.itedus.lottery.infrastructure.dao.IAwardDao;
import cn.itedus.lottery.infrastructure.po.Award;

/**
 * @author wangkui 2021-09-09
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseInitAwardArrayAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        String[] awardArray = rateTupleMap.get(strategyId);
        int i  = (new SecureRandom().nextInt(100))+1;

        String awardId = awardArray[hashIdx(i)];

        awardId = excludeAwardIds.contains(awardId)?"未中奖":awardId;
        return awardId;
    }

}
