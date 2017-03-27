package com.sooying.controller;

/**********************************************************
 * 版权所有：搜影科技 保留所有权利
 * 创建日期: 2017/3/18 0018 14:34
 * 创建作者: gezhangkai
 * 版    本:
 * 功    能:
 * 最后修改时间:
 * 修改记录:
 ***********************************************************/
public class test {


    public static void main(String[] args) {
        String str = "11yb\"\",du,,com@163.com,";
        str = str.replaceAll("(?sm)\"?([^\"]*((\"{2})*[^\"]*(\"{2})*)*[^\"]*)\"?,", "$1");
        str = str.replaceAll("(?sm)(\"(\"))", "$2");
        System.out.println(str);
    }
}
