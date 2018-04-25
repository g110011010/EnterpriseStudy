package com.saber.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.saber.databaseUtil.DBUtils;
import com.saber.pojo.Course;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saber on 2017/7/3.
 */
public class CourseManager {
    /**
     * ��ȡ�γ�
     */
    PreparedStatement titleQuery= null ;
    ResultSet results= null ;
    Connection conn=null;

    public List<Course> queryBytuijian(List<String> s) throws UnsupportedEncodingException {//ͨ���Ƽ��㷨��� �γ���������ȡ�γ�
        List<Course> courses=new ArrayList<>();
        conn= (Connection) DBUtils.getConnection();
        for (String s1:s) {
            String sql = "select * from course where cover='"+s1+"'";
            try {
                titleQuery= (PreparedStatement) conn.prepareStatement(sql);
                results=titleQuery.executeQuery();
                if(results.next()) {
                    Course u=new Course();
                    u.setDescribe(results.getString("description"));
                    u.setSource(results.getString("cover"));
                    u.setKey(results.getString("tags"));
                    u.setName(results.getString("name"));
                    u.setLink("SmartGuard.mp4");
                    courses.add(u);
                    System.out.println(u.getName());
                }
//                results.close() ;
//                titleQuery.close() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courses;
    }


    public Course queryBySource(String source){//ͨ��ͼƬ��Դ��ȡ����   ������video.com.jsp ������jsp
        Course u=new Course();
        conn= (Connection) DBUtils.getConnection();
        String sql = "select * from course where cover='"+source+"'";
        try
        {
            titleQuery= (PreparedStatement) conn.prepareStatement(sql);
            results=titleQuery.executeQuery();
            if(results.next()) {
                u.setDescribe(results.getString("description"));
                u.setSource(results.getString("cover"));
                u.setKey(results.getString("tags"));
                u.setName(results.getString("name"));
                u.setLink("SmartGuard.mp4");
                //System.out.println(u.getCourse_source());
            }
            results.close() ;
            titleQuery.close() ;
        }
        catch(Exception e)
        {
            System.out.println(e) ;
        }
        finally
        {
            DBUtils.close();
        }
        return u;
    }
    //���Ҫд�ɾ���·����������struts2�ı����������⣬��ʱû��������취
    public String indexDir_ik="F:\\�����������\\����1\\EnterpriseStudy2\\EnterpriseStudy\\lucene_index";
    public List<Course> queryBySearch(String item) throws Exception {//ͨ��lucene��������ȡ���ҵĿγ�  ������search_page.jsp

        List<Course> users=new ArrayList<>();

        Directory directory= FSDirectory.open(Paths.get(indexDir_ik));
        DirectoryReader reader=DirectoryReader.open(directory);
        IndexSearcher indexSearcher=new IndexSearcher(reader);
        Analyzer analyzer=new IKAnalyzer(true);

        //���ҵ��� address����ֶ�  ���ֹ�   String q   ���ĵ���
        //���ﻹҪ�ִʵ�ԭ����:analyzer��Ҫ����sql����Ĵʡ�
        QueryParser queryParser=new QueryParser("s_file_describe",analyzer);
        Query query=queryParser.parse(item);

        TopDocs topDocs=indexSearcher.search(query,100);
        System.out.println("�鵽���ĵ�����"+topDocs.totalHits);
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        for (ScoreDoc scoreDoc:scoreDocs) {
            Course course=new Course();
            Document document=indexSearcher.doc(scoreDoc.doc);
            course.setDescribe(document.get("s_file_describe"));
            course.setSource(document.get("s_file_source"));
            course.setKey(document.get("s_file_key"));
            course.setName(document.get("s_file_name"));
            course.setLink("SmartGuard.mp4");
            System.out.println(course.getName());
//            course.setType(results.getString("file_type"));
//            System.out.println(course.getDescribe());

            users.add(course);
        }
        directory.close();
        reader.close();
        return users;
    }

    public List<Course> queryByPage(int i, int pageSize){//��ȡ���пγ̲���ҳ  ������grid_page.jsp
        List<Course> users=new ArrayList();
        conn= (Connection) DBUtils.getConnection();
        String sql = "select * from course limit "+ i +","+ pageSize;
        try
        {
            titleQuery= (PreparedStatement) conn.prepareStatement(sql);
            results=titleQuery.executeQuery();
            while(results.next())
            {
                Course u=new Course();
                u.setDescribe(results.getString("description"));
                u.setSource(results.getString("cover"));
                u.setKey(results.getString("tags"));
                u.setName(results.getString("name"));
                u.setLink("SmartGuard.mp4");
                i++;
                users.add(u);
            }
            results.close() ;
            titleQuery.close() ;
        }
        catch(Exception e)
        {
            System.out.println(e) ;
        }
        finally
        {
            DBUtils.close();
        }
        return users;

    }
    //��ѯ������
    public int count() {
        int intRowCount = 0;//������
        conn= (Connection) DBUtils.getConnection();
        String  sql = "select count(*) from course";
        try
        {
            titleQuery= (PreparedStatement) conn.prepareStatement(sql);
            results=titleQuery.executeQuery();
            results.next();//�α�ָ���һ��
            intRowCount=results.getInt(1);//ȡ��������
            results.close() ;
            titleQuery.close() ;
        }
        catch(Exception e)
        {
            System.out.println(e) ;
        }
        finally
        {
            DBUtils.close();
        }
//        System.out.println("-----------��Ŀ������"+intRowCount+"_____________-");
        return intRowCount;
    }

}
