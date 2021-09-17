package cn.itedus.lottery.domain.strategy.model.aggregates;

import java.util.List;
import cn.itedus.lottery.infrastructure.po.Strategy;
import cn.itedus.lottery.infrastructure.po.StrategyDetail;
import lombok.Data;

/**
 * @author wangkui 2021-09-08
 */
@Data
public class StrategyRich {


    /**
     * 策略id
     */
    private Long strategyId;

    /**
     * 策略主体
     */
    private Strategy strategy;

    /**
     * 策略详情
     */
    private List<StrategyDetail> strategyDetailList;


}
