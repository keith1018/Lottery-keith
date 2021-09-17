package cn.itedus.lottery.domain.strategy.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.itedus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.itedus.lottery.infrastructure.po.Award;

/**
 * @author wangkui 2021-09-08
 */
public interface StrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAward(String awardId);

    List<String> queryExcludeAwardIds(@Param("strategyId") Long strategyId);
}
