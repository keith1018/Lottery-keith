package cn.itedus.lottery.domain.strategy.service.algorithm;

import java.util.List;
import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;

/**
 * @author wangkui 2021-09-09
 */
public interface IDrawAlgorithm {

    void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList);

    /**
     * 判断是否已经，做了数据初始化
     * @param strategyId
     * @return
     */
    boolean isExistRateTuple(Long strategyId);

    /**
     * SecureRandom 生成随机数，索引到对应的奖品信息返回结果
     *
     * @param strategyId 策略ID
     * @param excludeAwardIds 排除掉已经不能作为抽奖的奖品ID，留给风控和空库存使用
     * @return 中奖结果
     */
    String randomDraw(Long strategyId, List<String> excludeAwardIds);

}
