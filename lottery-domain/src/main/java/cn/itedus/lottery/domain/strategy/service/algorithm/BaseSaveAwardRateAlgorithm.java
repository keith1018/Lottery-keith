package cn.itedus.lottery.domain.strategy.service.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.itedus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

/**
 * @author wangkui 2021-09-10
 */
public abstract class BaseSaveAwardRateAlgorithm implements IDrawAlgorithm {

    /** 奖品区间概率值，strategyId -> [awardId->begin、awardId->end] */
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();


    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList) {
       awardRateInfoMap.put(strategyId,awardRateInfoList);


    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return awardRateInfoMap.containsKey(awardRateInfoMap);
    }


}
