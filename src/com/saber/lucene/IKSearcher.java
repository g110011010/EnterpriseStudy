package com.saber.lucene;

import com.saber.pojo.Course;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saber on 2017/7/9.
 */
public class IKSearcher {//�����࣬���ھ��Ⱥ�ģ�������γ�
    public int search(String indexDir, String sql) throws IOException, ParseException {
        Directory directory= FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader=DirectoryReader.open(directory);
        IndexSearcher indexSearcher=new IndexSearcher(reader);
        Analyzer analyzer=new IKAnalyzer(true);

        //���ҵ��� address����ֶ�  ���ֹ�   String q   ���ĵ���
        //���ﻹҪ�ִʵ�ԭ����:analyzer��Ҫ����sql����Ĵʡ�
        QueryParser queryParser=new QueryParser("s_comment",analyzer);
        Query query=queryParser.parse(sql);

        TopDocs topDocs=indexSearcher.search(query,100);
//        System.out.println("�鵽���ĵ�����"+topDocs.totalHits);
        directory.close();
        reader.close();
        return topDocs.totalHits;
    }
}
