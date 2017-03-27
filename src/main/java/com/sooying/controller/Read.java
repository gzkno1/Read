package com.sooying.controller;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**********************************************************
 * 版权所有：搜影科技 保留所有权利
 * 创建日期: 2017/3/3 0003 14:54
 * 创建作者: gezhangkai
 * 版    本:
 * 功    能:
 * 最后修改时间:
 * 修改记录:
 ***********************************************************/
public class Read {
    public static void main(String[] args) throws Exception {
        String path = "E:\\csv\\20170327.csv";
        File file = new File(path);
        List<String[]> list = readCSVFile(file);
        int i = 20000;
        int x = 2017032720;
        FileWriter output = new FileWriter(String.format("D://%d.txt",x));
        BufferedWriter bf = new BufferedWriter(output);
        for(String[] strings:list){
//            StringBuffer stringBuffer = new StringBuffer();
//            String[] title = "website_id,user_id,user_name,website_name,url,icp,company".split(",");
//
//            stringBuffer.append("insert into final")
//                    .append(" (website_id,user_id,user_name,website_name,url,icp,company)")
//                    .append("VALUES (")
//                    .append(strings[0])
//            .append(",").append(strings[1])
//            .append(",").append("'").append(strings[2]).append("'")
//                    .append(",").append("'").append(strings[3]).append("'")
//                    .append(",").append("'").append(strings[4]).append("'")
//                    .append(",").append("'").append(strings[5]).append("'")
//                    .append(",").append("'").append(strings[6]).append("'").append(");");
//            String s = stringBuffer.toString();
//            for(int j =0;j< strings.length - 1;j++){
//                if(StringUtils.isBlank(strings[j])){
//                    s = s.replace(title[j],"");
//                }
//            }
//            s = s.replaceAll(",+",",");
            String s = csv1(strings,i++);
            if(i%10000 == 1){
                bf.close();
                output = new FileWriter(String.format("D://%d.txt",x++));
                bf = new BufferedWriter(output);
            }
            bf.write(s + "\r\n");
        }
        bf.close();
    }


    //tbl_sys_export 系统中导出
    public static String csv1(String[] strings,int i){
        StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("insert into tbl_sys_export" )
                    .append(" (id,riqi,yonghu_id,yonghu_name,company_name,shuxing,guanggao_name,toufang_plattype,guanggao_type,danjia,zhanshiliang,dianji,dianji_rata,jiesuanshu,toufange)")
            .append("VALUES (")
                    .append(i)
                    .append(",").append("'").append(strings[0].replaceAll("/", "-")).append("'")
                    .append(",").append(strings[1])
                    .append(",").append("'").append(strings[2]).append("'")
                    .append(",").append("'").append(strings[3]).append("'")
                    .append(",").append("'").append(strings[4]).append("'")
                    .append(",").append("'").append(strings[5]).append("'")
                    .append(",").append("'").append(strings[6]).append("'")
                    .append(",").append("'").append(strings[7]).append("'")
                    .append(",").append(strings[8].replace("#DIV/0!","-1"))
                    .append(",").append(strings[9].replace("-","0"))
                    .append(",").append(strings[10])
                    .append(",").append(strings[11].replace("#DIV/0!","-1"))
                    .append(",").append(strings[12])
                    .append(",").append(strings[13].replace("-","0"))
//                    .append(",").append(strings[14])
                    .append(");");
        String s = stringBuffer.toString();
        if(StringUtils.isBlank(strings[1])){
           s = s.replace("yonghu_id,","");
        }
//        if(StringUtils.isBlank(strings[2])){
//            s = s.replace("yonghu_name,","");
//        }
        s = s.replaceAll(",+",",");
        return s;

    }

    public static String csv2(String[] strings){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into tbl_needget_detial")
                .append(" (id,xiangmubu,chanping_type,chanping_name,guanggao_type,android_danjia,ios_danjia,kehu_name)")
                .append("VALUES (")
                .append(strings[0])
                .append(",").append("'").append(strings[1]).append("'")
                .append(",").append("'").append(strings[2]).append("'")
                .append(",").append("'").append(strings[3]).append("'")
                .append(",").append("'").append(strings[4]).append("'")
                .append(",").append(strings[5])
                .append(",").append(strings[6])
                .append(",").append("'").append(strings[7]).append("'")
                .append(");");
        String s = stringBuffer.toString();
        if(StringUtils.isBlank(strings[5])){
            s = s.replace("android_danjia,","");
        }
        if(StringUtils.isBlank(strings[6])){
            s = s.replace("ios_danjia,","");
        }
        s = s.replaceAll(",+",",");
        System.out.println(s);
        return s;
    }


    public static void csv3(String[] strings){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into tbl_needget_detial")
                .append(" (id,android_danjia,ios_danjia,month,money)")
                .append("VALUES (")
                .append(strings[0])
                .append(",").append(strings[1])
                .append(",").append(strings[2]);
        Calendar calender = Calendar.getInstance();
        calender.set(2015,11,01);
        for(int i = 0;i<14;i++){
            StringBuffer stringBuffer1 = new StringBuffer(stringBuffer);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(Calendar.getInstance().getTime());
            stringBuffer1.append(",").append("'").append(date).append("'")
                    .append(",").append(strings[i + 4])
                    .append(");").append(" \r\n");
            calender.add(Calendar.MONTH, 1);
        }

    }


    public static List<String[]> readCSVFile(File f) throws Exception {
        InputStreamReader fr = null;
        BufferedReader br = null;
        String rec = null;// 一行
        String str;
        List<String[]> listFile = new ArrayList<String[]>();
        try {
            String code = codeString(new FileInputStream(f));
            fr = new InputStreamReader(new FileInputStream(f),code);
            br = new BufferedReader(fr);
            while ((rec = br.readLine()) != null) {
                Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,?");
                Matcher mCells = pCells.matcher(rec);
                List<String> cells = new ArrayList<String>();// 每行记录一个list
                // 读取每个单元格
                while (mCells.find()) {
                    str = mCells.group();
                    if(str.endsWith("\"")){
                        str+=",";
                    }
                    str = str.replaceAll("(?sm)\"?([^\"]*((\"{2})*[^\"]*(\"{2})*)*[^\"]*)\"?,", "$1");
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
                    cells.add(str);
                }
                String[] s = new String[cells.size()];
                cells.toArray(s);
                listFile.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
            if (fr != null) {
                fr.close();
            }

        }
        return listFile;
    }


    public static String codeString(FileInputStream fileIn) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(fileIn);
        int p = (bin.read() << 8) + bin.read();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        if(bin != null){
            bin.close();
        }
        return code;
    }
}
