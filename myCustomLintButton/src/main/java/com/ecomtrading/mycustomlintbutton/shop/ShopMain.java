package com.ecomtrading.mycustomlintbutton.shop;

public class ShopMain {

    private static int[][] monthlyCombinedSales = new int[4][3];
    private static int[] highStreetQuarterlySales = new int[4];
    private static int[] mallQuarterlySales = new int[4];
    private static int highStreetAnnualSales = 0;
    private static int mallAnnualSales = 0;

    public static void main(String[] args) {
        int[][] highStreetBranch = {
                new int[]{42000,48000,50000},
                new int[]{52000,58000,60000},
                new int[]{46000,49000,58000},
                new int[]{50000,51000,61000},
        };

        int[][] mallBranch = {
                new int[]{57000, 63000, 60000},
                new int[]{70000, 67000, 73000},
                new int[]{67000, 65000, 62000},
                new int[]{72000, 69000, 75000},
        };

        //monthly combined sales
        monthlyCombinedSales(highStreetBranch,mallBranch);
        System.out.println("----------------------------------");
        //Quarterly sales for HightStreet shop
        quarterlySales(highStreetBranch,Shop.HIGHSTREET_SHOP);

        System.out.println("----------------------------------");
        //Quarterly sales for Mall shop
        quarterlySales(mallBranch,Shop.MALL_SHOP);

        System.out.println("----------------------------------");
        //yearly Sales For HighStreet
        annualSales(highStreetQuarterlySales,Shop.HIGHSTREET_SHOP);
        System.out.println("----------------------------------");
        //yearly Sales For Mall
        annualSales(mallQuarterlySales,Shop.MALL_SHOP);

        System.out.println("----------------------------------");
        //Grand yearly Sales For All Shops combined
        annualCombinedSales();
    }

    //monthly combined sales
    private static void monthlyCombinedSales(int[][] shop1,int[][] shop2){
        int month = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 3; j++) {
                month++;
                int combined = shop1[i][j] + shop2[i][j];
                monthlyCombinedSales[i][j] = combined;
                System.out.format("Combined Monthly Sales for the two shop in Month %s : GHC %s\n",month, combined);
            }
        }
    }

    //Quarterly sales for each shops
    private static void quarterlySales(int[][] shop,String name){
        int quarter = 0;
        for (int i = 0; i < 4; i++){
            int quarterAmt = 0;
            for (int j = 0; j < 3; j++) {

                quarterAmt += shop[i][j];

            }

            quarter++;
            if (name.equals(Shop.HIGHSTREET_SHOP)){
                highStreetQuarterlySales[i] = quarterAmt;
            }else {
                mallQuarterlySales[i] = quarterAmt;
            }

            System.out.format("Quarterly Sales for %s in Quarter %s: GHC %s\n",name,quarter, quarterAmt);
        }
    }

    //Annual Sales for each shop
    private static void annualSales(int[] quarterly,String name){

        int yearlyAmt = 0;
        for (int j = 0; j < quarterly.length; j++) {

            yearlyAmt += quarterly[j];
            if (name.equals(Shop.HIGHSTREET_SHOP)){
                highStreetAnnualSales += quarterly[j];
            }else {
                mallAnnualSales += quarterly[j];
            }


        }
        System.out.format("Annual Sales for %s : GHC %s\n",name, yearlyAmt);

    }

    //The grand annual combined Sales
    private static void annualCombinedSales(){
        System.out.format("Grand Annual Total Sales for the two shop combined : GHC %s\n", highStreetAnnualSales + mallAnnualSales);
    }


    private interface Shop{
        String HIGHSTREET_SHOP = "highStreet Shop";
        String MALL_SHOP = "mall Shop";
    }
}
