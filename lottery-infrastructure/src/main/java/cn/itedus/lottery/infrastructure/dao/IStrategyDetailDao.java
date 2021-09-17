package cn.itedus.lottery.infrastructure.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import cn.itedus.lottery.infrastructure.po.StrategyDetail;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Mapper
public interface IStrategyDetailDao {

    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    List<String> queryExcludeAwardIds(Long strategyId);

}
