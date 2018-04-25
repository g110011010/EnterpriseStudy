package com.saber.action;

import com.opensymphony.xwork2.ActionSupport;
import com.saber.dao.DetailsManager;
import com.saber.pojo.Course;
import com.saber.dao.CourseManager;
import com.saber.pojo.Details;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saber on 2017/8/16.
 */
public class CourseAction extends ActionSupport {
    /**
     * ��ȡ�γ̵�action
     */
    private String source;//�γ̵�ͼƬ
    private Course course;//�γ�
    private String course_describe;//�γ�����
    private String course_name;//�γ���
    //���ڻ�������γ̵�������Ƶ����Ƶ��PPT���ж�
    private String course_type;//�γ�����
    private String course_source;//�γ�����
    private List<Details> details;
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getCourse_source() {
        return course_source;
    }

    public void setCourse_source(String course_source) {
        this.course_source = course_source;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getCourse_describe() {
        return course_describe;
    }

    public void setCourse_describe(String course_describe) {
        this.course_describe = course_describe;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String queryTuijian() throws Exception{
        CourseManager courseManager=new CourseManager();
        List<String> s=new ArrayList<>();
        s.add("picture/Echarts3.0���Ż�����ʵս.jpg");
        s.add("picture/Hadoop������ƽ̨�ܹ���ʵ��--����ƪ.jpg");
        s.add("picture/HBase����.jpg");
        s.add("picture/IS-IS·��Э��ԭ�������.jpg");
        courses=courseManager.queryBytuijian(s);

        return "tuijian";

    }

    public String queryCourse() throws Exception {
        CourseManager courseManager = new CourseManager();
        course = courseManager.queryBySource(source);

        course_source=course.getLink();
        course_name = course.getName();
        course_describe = course.getDescribe();
        if (course_type.equals("��Ƶ"))
            return "video";//��ʾ���ص���video
        else if (course_type.equals("��Ƶ"))
            return "music";
            //return "music"��ʾ���ص�����Ƶ
        else if (course_type.equals("PPT"))
            return "ppt";
            //return "ppt"��ʾ���ص�����Ƶ
        else return "video";
    }

    public String queryDetails() throws Exception {
        CourseManager courseManager = new CourseManager();
        course = courseManager.queryBySource(source);
        course_name = course.getName();
        course_describe = course.getDescribe();
        DetailsManager detailsManager = new DetailsManager();
        details = detailsManager.queryDetails();
        return "success";
    }
}

