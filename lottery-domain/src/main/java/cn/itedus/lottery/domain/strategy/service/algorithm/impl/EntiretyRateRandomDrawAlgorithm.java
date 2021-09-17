package cn.itedus.lottery.domain.strategy.service.algorithm.impl;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import cn.itedus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.itedus.lottery.domain.strategy.service.algorithm.BaseSaveAwardRateAlgorithm;
import cn.itedus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

/**
 * @author wangkui 2021-09-09
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseSaveAwardRateAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        List<AwardRateInfo> awardRateInfos = awardRateInfoMap.get(strategyId);

        BigDecimal differenceDenominator = BigDecimal.ZERO;

        List<AwardRateInfo> differenceList = new ArrayList<>();

        for (AwardRateInfo awardRateInfo : awardRateInfos) {
            if(excludeAwardIds.contains(awardRateInfo.getAwardId())){
                continue;
            }

            differenceList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        String awardId = "未中奖";

        int i  = (new SecureRandom().nextInt(100))+1;

        for (AwardRateInfo awardRateInfo : differenceList) {
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator,2,BigDecimal.ROUND_UP).multiply(new BigDecimal("100")).intValue();

            i-=rateVal;
            if(i<=0){
                awardId = awardRateInfo.getAwardId();
            }
        }

        awardId = excludeAwardIds.contains(awardId)?"未中奖":awardId;

        return awardId;
    }

}
