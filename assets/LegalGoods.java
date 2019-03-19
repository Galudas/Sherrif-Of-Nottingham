package assets;

public class LegalGoods {
    public static final  int APPELPROFIT = 2;
    public static final  int CHEESEPROFIT = 3;
    public static final  int BREADPROFIT = 4;
    public static final  int CHICKENPROFIT = 4;
    public static final  int APPELPENALTY = 2;
    public static final  int CHEESEPENALTY = 2;
    public static final  int BREADPPENALTY = 2;
    public static final  int CHICKENPENALTY = 2;
    public static final  int APPELID = 0;
    public static final  int CHEESEID = 1;
    public static final  int BREADID = 2;
    public static final  int CHICKENID = 3;
    public static final  int APPLEKINGSBONUS = 20;
    public static final  int CHEESEKINGSBONUS = 15;
    public static final  int BREADKINGSBONUS = 15;
    public static final  int CHICKENKINGSBONUS = 10;
    public static final  int APPLEQUEENSBONUS = 10;
    public static final  int CHEESEQUEENSBONUS = 10;
    public static final  int BREADQUEENSBONUS = 10;
    public static final  int CHICKENQUEENSBONUS = 5;

    private int profit;
    private int penalty;
    private int id;
    private int kingBonus;
    private int queenBonus;
    public LegalGoods() {

    }
    /**/
    public void setKingBonus(final int kingBonus) {
        this.kingBonus = kingBonus;
    }
    /**/
    public void setQueenBonus(final int queenBonus) {
        this.queenBonus = queenBonus;
    }
    /**/
    public int getProfit() {
        return profit;
    }
    /**/
    public void setProfit(final int profit) {
        this.profit = profit;
    }
    /**/
    public int getPenalty() {
        return penalty;
    }
    /**/
    public void setPenalty(final int penalty) {
        this.penalty = penalty;
    }
    /**/
    public void setId(final int id) {
        this.id = id;
    }


    /*Gets an appel id card*/
    public void getApple() {
        setProfit(APPELPROFIT);
        setPenalty(APPELPENALTY);
        setId(APPELID);
        setKingBonus(APPLEKINGSBONUS);
        setQueenBonus(APPLEQUEENSBONUS);
    }
    /*Gets an Cheese id card*/
    public void getCheese() {
        setProfit(CHEESEPROFIT);
        setPenalty(CHEESEPENALTY);
        setId(CHEESEID);
        setKingBonus(CHEESEKINGSBONUS);
        setQueenBonus(CHEESEQUEENSBONUS);
    }
    /*Gets an Bread id card*/
    public void getBread() {
        setProfit(BREADPROFIT);
        setPenalty(BREADPPENALTY);
        setId(BREADID);
        setKingBonus(BREADKINGSBONUS);
        setQueenBonus(BREADQUEENSBONUS);

    }
    /*Gets an chicken id card*/
    public void getChicken() {
        setProfit(CHICKENPROFIT);
        setPenalty(CHICKENPENALTY);
        setId(CHEESEID);
        setKingBonus(CHEESEKINGSBONUS);
        setQueenBonus(CHEESEQUEENSBONUS);

    }
}
