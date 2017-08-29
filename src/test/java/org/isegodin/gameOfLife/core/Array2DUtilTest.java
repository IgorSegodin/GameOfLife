package org.isegodin.gameOfLife.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author isegodin
 */
public class Array2DUtilTest {

    @Test
    public void test_success() {
        int width = 4;
        int height = 3;
        int[] data = new int[]{
                1,  2,  3,  4,
                5,  6,  7,  8,
                9, 10, 11, 12
        };

        Assert.assertEquals(1, (int) Array2DUtil.get(0, 0, data, width));
        Assert.assertEquals(2, (int) Array2DUtil.get(1, 0, data, width));
        Assert.assertEquals(3, (int) Array2DUtil.get(2, 0, data, width));
        Assert.assertEquals(4, (int) Array2DUtil.get(3, 0, data, width));
        Assert.assertEquals(5, (int) Array2DUtil.get(0, 1, data, width));
        Assert.assertEquals(6, (int) Array2DUtil.get(1, 1, data, width));
        Assert.assertEquals(7, (int) Array2DUtil.get(2, 1, data, width));
        Assert.assertEquals(8, (int) Array2DUtil.get(3, 1, data, width));
        Assert.assertEquals(9, (int) Array2DUtil.get(0, 2, data, width));
        Assert.assertEquals(10, (int) Array2DUtil.get(1, 2, data, width));
        Assert.assertEquals(11, (int) Array2DUtil.get(2, 2, data, width));
        Assert.assertEquals(12, (int) Array2DUtil.get(3, 2, data, width));
    }
}
