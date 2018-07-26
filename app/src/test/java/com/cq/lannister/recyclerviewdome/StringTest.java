package com.cq.lannister.recyclerviewdome;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * create by lym on 2018/7/26.
 */
public class StringTest {
    @Test
    public void testStringFormat(){
        double dif = 2.36333;
//        String var = String.format("还差<font color='#FF9901'>%.1f元</font>可领取", dif);
        String var = ""+formatNumber(dif,1);
        System.out.println(var);
    }

    public static float formatNumber(double d,int digit) {
        // NumberFormat nf = NumberFormat.getNumberInstance();
        // nf.setMaximumFractionDigits(2);
        // return Float.valueOf(nf.format(d));

        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(digit, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
