package assets;

public class GetAsset extends IllegalGoods {
    private static final int APPLE_ID = 0;
    private static final int CHEESE_ID = 1;
    private static final int BREAD_ID = 2;
    private static final int CHICKEN_ID = 3;
    private static final int SILK_ID = 10;
    private static final int PEPPER_ID = 11;
    private static final int BARREL_ID = 12;
    public GetAsset() {
        return;
    }
    /**/
    public IllegalGoods cardType(final int resourceCard) {
        IllegalGoods asset = new IllegalGoods();
        if (resourceCard == APPLE_ID) {
            asset.getApple();

        }
        if (resourceCard == CHEESE_ID) {
            asset.getCheese();
        }
        if (resourceCard == BREAD_ID) {
            asset.getBread();
        }
        if (resourceCard == CHICKEN_ID) {

            asset.getChicken();

        }
        if (resourceCard == SILK_ID) {
            asset.getSilk();

        }
        if (resourceCard == PEPPER_ID) {
            asset.getPepper();

        }
        if (resourceCard == BARREL_ID) {
            asset.getBarrel();

        }
        return asset;

    }
}
