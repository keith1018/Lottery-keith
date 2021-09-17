package cn.itedus.lottery.domain.strategy.service.draw;

import cn.itedus.lottery.domain.strategy.model.req.DrawReq;
import cn.itedus.lottery.domain.strategy.model.res.DrawResult;

/**
 * @author wangkui 2021-09-09
 */
public interface IDrawExec {

    DrawResult doDrawExec(DrawReq req);

}
