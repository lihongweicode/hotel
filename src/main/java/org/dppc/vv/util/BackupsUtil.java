package org.dppc.vv.util;

import java.io.*;

/**
 * @Description TODO
 * @Author lhw
 * @Data 2019/6/14 19:06
 * @Version 1.0
 **/
public class BackupsUtil {
    /**
     * @Author lhw
     * @Description 写入内容到文件
     * @Date 19:06 2019/6/14
     * @Param c    要写入的内容
     * @Param dirname  目录
     * @Param filename 文件名
     * @Param isAppend  如果 true ，则字节将被写入文件的末尾而不是开头
     * @return boolean
     **/
    public static boolean writeContent(String c, String dirname,String filename,boolean isAppend) {
        File f=new File(dirname);
        if (!f.exists())
        {
            f.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream( dirname+File.separator+filename,isAppend);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(c);
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 从文件读取内容
     *
     * @param filename
     * @return
     */
    public static String readText(String filename) {
        String content = "";
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String str = "";
                String newline = "";
                while ((str = br.readLine()) != null) {
                    content += newline + str;
                    newline = "\n";
                }
                br.close();
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    /**
     * @Author lhw
     * @Description 删除指定目录下的所有文件
     * @Date 19:14 2019/6/14
     * @Param filePath
     * @return void
     **/
    public static void clearFiles(String filePath){
        File scFileDir = new File(filePath);
        File TrxFiles[] = scFileDir.listFiles();
        for(File curFile:TrxFiles ){
            curFile.delete();
        }
    }
}
