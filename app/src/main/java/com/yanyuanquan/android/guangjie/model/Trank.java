package com.yanyuanquan.android.guangjie.model;

import com.yanyuanquan.android.guangjie.base.api.WrapData;

/**
 * Created by guider on 16/7/24.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class Trank<T> extends WrapData<T> {

    /**
     * rankdate : 2016-07-24
     * displaydate : 今日
     * rankduring : 19:00-20:00
     * hasnexthour : 1
     * nexthourhour : 21
     * nexthourdate : 20160724
     * lasthourhour : 19
     * lasthourdate : 20160724
     */

    private String rankdate;
    private String displaydate;
    private String rankduring;
    private String hasnexthour;
    private String nexthourhour;
    private String nexthourdate;
    private String lasthourhour;
    private String lasthourdate;

    public String getRankdate() {
        return rankdate;
    }

    public void setRankdate(String rankdate) {
        this.rankdate = rankdate;
    }

    public String getDisplaydate() {
        return displaydate;
    }

    public void setDisplaydate(String displaydate) {
        this.displaydate = displaydate;
    }

    public String getRankduring() {
        return rankduring;
    }

    public void setRankduring(String rankduring) {
        this.rankduring = rankduring;
    }

    public String getHasnexthour() {
        return hasnexthour;
    }

    public void setHasnexthour(String hasnexthour) {
        this.hasnexthour = hasnexthour;
    }

    public String getNexthourhour() {
        return nexthourhour;
    }

    public void setNexthourhour(String nexthourhour) {
        this.nexthourhour = nexthourhour;
    }

    public String getNexthourdate() {
        return nexthourdate;
    }

    public void setNexthourdate(String nexthourdate) {
        this.nexthourdate = nexthourdate;
    }

    public String getLasthourhour() {
        return lasthourhour;
    }

    public void setLasthourhour(String lasthourhour) {
        this.lasthourhour = lasthourhour;
    }

    public String getLasthourdate() {
        return lasthourdate;
    }

    public void setLasthourdate(String lasthourdate) {
        this.lasthourdate = lasthourdate;
    }

    @Override
    public String toString() {
        return "Trank{" +
                "rankdate='" + rankdate + '\'' +
                ", displaydate='" + displaydate + '\'' +
                ", rankduring='" + rankduring + '\'' +
                ", hasnexthour='" + hasnexthour + '\'' +
                ", nexthourhour='" + nexthourhour + '\'' +
                ", nexthourdate='" + nexthourdate + '\'' +
                ", lasthourhour='" + lasthourhour + '\'' +
                ", lasthourdate='" + lasthourdate + '\'' +
                '}';
    }
}
