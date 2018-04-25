package com.saber.lucene;

import org.apache.lucene.queryparser.classic.ParseException;
import java.io.*;
import java.util.*;

/**
 * Created by Saber on 2017/7/9.
 */
public class IKWord {//��ȡ��Ƶ�ȶȣ�������Ҫ���Ƽ��㷨���
    public static String indexDir_ik="F:\\�����������\\����1\\EnterpriseStudy2\\EnterpriseStudy\\lucene_word_index";

    public String search(String indexDir) throws IOException, ParseException {
        String str =new String();
        try{
            File file = new File("F:\\�����������\\����1\\EnterpriseStudy2\\EnterpriseStudy\\src\\new_ext.dic");
            BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
            String s = null;
            int i=0;
            while((s = br.readLine())!=null&&s!=""){//ʹ��readLine������һ�ζ�һ��
                IKSearcher ikSearcher=new IKSearcher();
                int i1=ikSearcher.search(indexDir_ik,s);
                if (i<i1){
                    str=s;
                    i=i1;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            //�����Ƚ�����õ� ��Ϣ ͨ��һ��bean�����鱣��
            //���ڲ���Ҫ��̫�������  ֻ��Ҫͨ����Ȩ��  �ҵ�����ϵ����ݼ���
            (new IKWord()).search(indexDir_ik);
            } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
class MapKeyComparator implements Comparator<Long>{
    public int compare(Long str1, Long str2) {
        return str2.compareTo(str1);
    }
}
