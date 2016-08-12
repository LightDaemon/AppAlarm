package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by ZY on 2016/7/28.
 * Description :
 */
public class GetLineDemo {

    public static void main(String[] args) {
        readFileByLines();
    }

    public static void readFileByLines() {

        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("D:\\公司文档\\翻译\\system.word.list.xml", "UTF-8");
            reader = Files.newBufferedReader(Paths.get("D:\\公司文档\\翻译", "词典360万（个人整理）.txt"), Charset.forName("UTF-8"));
            //一次读一行，读入null时文件结束
            for (String tempString = reader.readLine(); tempString != null; tempString = reader.readLine()) {
                String word = tempString.replaceAll("[a-zA-Z0-9]", "");
                String word2 = "<pattern><value>" + word.trim() + "</value></pattern>";
//                String needWord = word2.replaceAll("\t\t","");
                writer.println(word2);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }


}
