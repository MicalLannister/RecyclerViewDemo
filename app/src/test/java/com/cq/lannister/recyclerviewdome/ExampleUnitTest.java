package com.cq.lannister.recyclerviewdome;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testStringFormat(){
        double dif = 2.33333;
        String var = String.format("还差%.1f元可领取", dif);
        System.out.println(var);
    }
}