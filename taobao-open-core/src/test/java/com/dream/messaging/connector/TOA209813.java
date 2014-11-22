package com.dream.messaging.connector;

/**
 * @author Frank
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 * 
<!-- 
    输出数据实际总长度   9(4)    整个数据区长度，包括本字段4字节在内
    五位格式码   X(5)    20H81
    积分余额(人民币)   9(8)    
    积分余额(美元)    9(8)    
 -->*/
public class TOA209813 extends TOABody {

    private Integer scoreBalanceCNY;
    private Integer scoreBalanceUSD;

    /**
     * @return Returns the scoreBalanceCNY.
     */
    public Integer getScoreBalanceCNY() {
        return scoreBalanceCNY;
    }

    /**
     * @param scoreBalanceCNY The scoreBalanceCNY to set.
     */
    public void setScoreBalanceCNY(Integer scoreBalanceCNY) {
        this.scoreBalanceCNY = scoreBalanceCNY;
    }

    /**
     * @return Returns the scoreBalanceUSD.
     */
    public Integer getScoreBalanceUSD() {
        return scoreBalanceUSD;
    }

    /**
     * @param scoreBalanceUSD The scoreBalanceUSD to set.
     */
    public void setScoreBalanceUSD(Integer scoreBalanceUSD) {
        this.scoreBalanceUSD = scoreBalanceUSD;
    }

}
