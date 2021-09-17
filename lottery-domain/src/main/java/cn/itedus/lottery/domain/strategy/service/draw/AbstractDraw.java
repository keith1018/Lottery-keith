package cn.itedus.lottery.domain.strategy.service.draw;

import java.util.ArrayList;
import java.util.List;
import cn.itedus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.itedus.lottery.domain.strategy.model.req.DrawReq;
import cn.itedus.lottery.domain.strategy.model.res.DrawResult;
import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.itedus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.itedus.lottery.infrastructure.po.Award;
import cn.itedus.lottery.infrastructure.po.StrategyDetail;

/**
 * @author wangkui 2021-09-14
 */
public abstract class AbstractDraw extends DrawStrategySupport implements IDrawExec {

    @Override
    public DrawResult doDrawExec(DrawReq req) {

        Long strategyId = req.getStrategyId();

        // 1.查询策略信息
        StrategyRich strategyRich = super.queryStrategyRich(strategyId);

        // 2.根据策略信息区去获取策略实现类
        IDrawAlgorithm iDrawAlgorithm = drawAlgorithmGroup.get(strategyRich.getStrategy().getStrategyMode());

        // 3.查询库存不足的奖品


        // 3.检验策略是否初始化
        if(!iDrawAlgorithm.isExistRateTuple(strategyId)){
            iDrawAlgorithm.initRateTuple(strategyId,transRateInfo(strategyRich.getStrategyDetailList()));
        }

        // 4.获取抽奖结果
        String awardId = drawAlgorithm(strategyId,iDrawAlgorithm,queryExcludeAwardIds(strategyId));

        // 5.包装结果返回
        return buildDrawResult(req,awardId);
    }

    /**
     * 执行抽奖算法
     *
     * @param strategyId      策略ID
     * @param drawAlgorithm   抽奖算法模型
     * @param excludeAwardIds 排除的抽奖ID集合
     * @return 中奖奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    private DrawResult buildDrawResult(DrawReq req, String awardId){
        DrawResult drawResult = new DrawResult();
        drawResult.setUId(req.getUId());
        drawResult.setStrategyId(req.getStrategyId());
        drawResult.setAwardId(awardId);


        Award award = super.queryAwardInfoByAwardId(awardId);
        if(award!=null) {
            drawResult.setAwardName(award.getAwardName());
        }

        return drawResult;
    }

    /**
     * 查询库存不足的奖品
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    private List<AwardRateInfo> transRateInfo(List<StrategyDetail> strategyDetailList){
        List<AwardRateInfo> awardRateInfos = new ArrayList<>(strategyDetailList.size());
        strategyDetailList.forEach(v->{
            awardRateInfos.add(new AwardRateInfo(v.getAwardId(),v.getAwardRate()));
        });
        return awardRateInfos;
    }

}
