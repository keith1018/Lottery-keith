package cn.itedus.lottery.domain.strategy.service.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;

/**
 * 适用于扩展使用固定的奖池数组的抽奖策略
 *
 * @author wangkui 2021-09-10
 */
public abstract class BaseInitAwardArrayAlgorithm implements IDrawAlgorithm{

    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    private static final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度
    private final int RATE_TUPLE_LENGTH = 128;

    // 存放概率与奖品对应的散列结果，strategyId -> rateTuple
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList) {

        //斐波那契数列必须为2的整数次方，所以为128；
        String[] awardArray = rateTupleMap.computeIfAbsent(strategyId,v->new String[RATE_TUPLE_LENGTH]);

        int offset = 0;

        for (AwardRateInfo awardRateInfo : awardRateInfoList) {
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal("100")).intValue();

            for (int i = 1; i <= rateVal; i++) {
                awardArray[hashIdx(offset+i)] = awardRateInfo.getAwardId();
            }

            offset += rateVal;
        }

    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(rateTupleMap);
    }


    public static int hashIdx(int num){
        return (HASH_INCREMENT*num+HASH_INCREMENT)&(127);
    }
}
