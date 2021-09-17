package cn.itedus.lottery.domain.strategy.model.res;

import lombok.Data;

/**
 * @author wangkui 2021-09-08
 */
@Data
public class DrawResult {

    //用户id
    private Long uId;

    //策略id
    private Long strategyId;

    //奖品id
    private String awardId;

    //奖品名称
    private String awardName;

    public static void main(String[] args) {
        int i = 1;
        i >>>= 12;
        System.out.println(i);
        System.out.println(1>>>12);

    }

}
