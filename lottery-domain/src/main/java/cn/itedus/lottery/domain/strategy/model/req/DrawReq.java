package cn.itedus.lottery.domain.strategy.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangkui 2021-09-08
 */
@Data
@AllArgsConstructor
public class DrawReq {

    // 用户id
    private Long uId;

    //策略id
    private Long strategyId;
}
