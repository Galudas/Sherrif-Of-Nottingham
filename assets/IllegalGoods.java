package assets;

public class IllegalGoods extends LegalGoods {
    private int profit;
    private int penalty;
    private int bonus;
    private int id;
    public static final  int SILKPROFIT = 9;
    public static final  int PEPPERPROFIT = 8;
    public static final  int BARRELPROFIT = 7;
    public static final  int SILKPENALTY = 4;
    public static final  int PEPPERPENALTY = 4;
    public static final  int BARRELPPENALTY = 4;
    public static final  int SILKID = 10;
    public static final  int PEPPERID = 11;
    public static final  int BARRELID = 12;
    public static final  int SILKBONUS = 1;
    public static final  int PEPPERBONUS = 3;
    public static final  int BARRELBONUS = 2;
    /**/
    @Override
    public int getProfit() {
        return profit;
    }

    /**/
    @Override
    public void setProfit(final int profit) {
        this.profit = profit;
    }

    /**/
    @Override
    public int getPenalty() {
        return penalty;
    }

    /**/
    @Override
    public void setPenalty(final int penalty) {
        this.penalty = penalty;
    }

    /**/
    @Override
    public void setId(final int id) {
        this.id = id;
    }

    /**/
    public int getBonus() {
        return bonus;
    }

    /**/
    public void setBonus(final int bonus) {
        this.bonus = bonus;
    }

    public IllegalGoods() {
        super();
    }
    /**/
    public void getSilk() {
        setProfit(SILKPROFIT);
        setPenalty(SILKPENALTY);
        setBonus(SILKBONUS);
        setId(SILKID);
    }
    /**/
    public void getPepper() {
        setProfit(PEPPERPROFIT);
        setPenalty(PEPPERPENALTY);
        setBonus(PEPPERBONUS);
        setId(PEPPERID);
    }
    /**/
    public void getBarrel() {
        setProfit(BARRELPROFIT);
        setPenalty(BARRELPPENALTY);
        setBonus(BARRELBONUS);
        setId(BARRELID);
    }


}

