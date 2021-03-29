package com.ecomtrading.mycustomlintbutton.studentPool;

public class StudentRator {

    private static int[] scale = new int[8];
    public static void main(String[] args) {
        int[] rating = {1,3,2,5,1,1,1,1,1,1,1,2,4,5,6,6,5,6,7,7,7,7};
        System.out.format("Size of rating : %s",""+ rating.length);
        for (int i = 0; i < rating.length; i++){
            scale[rating[i]]++;
        }

        for (int i = 1; i < scale.length; i++){
            System.out.format("\nFrequency of %d = %s\n",i,""+scale[i]);
        }
    }


}
