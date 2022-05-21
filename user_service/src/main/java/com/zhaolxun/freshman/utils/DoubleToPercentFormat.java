package com.zhaolxun.freshman.utils;

import java.text.NumberFormat;

/**
 * Double转化为百分数
 * @author ganhongliang
 * @date 2018年10月1日
 */
public class DoubleToPercentFormat {

    /**
     *
     * @param d
     * @param IntegerDigits
     * @param FractionDigits
     * @return String
     * @time 2018年10月1日 上午11:09:35
     */
    public static String getPercentFormat(double d,int IntegerDigits,int FractionDigits){
        NumberFormat nf = java.text.NumberFormat.getPercentInstance();
        nf.setMaximumIntegerDigits(IntegerDigits);//小数点前保留几位
        nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
        String str = nf.format(d);
        return str;
    }

}