package com.saber.action;

import com.mysql.jdbc.Statement;
import com.saber.databaseUtil.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Saber on 2017/6/29.
 */
public class Comment {
    /**
     * ���۵�action
     */
    private String commentContent;//��������
    private String commentTime;//���۷���ʱ��
    private String commentName;//�������۵���
    private String commentGood;//���޴���

    //�ں������ݿ⽨��ʱ�����������������ڿγ̡�����ͬ���۶�Ӧ��ͬ�γ̣�commentClassId ���
    //�ں������ݿ⽨��ʱ�������������۵����ͣ����ʻ������ۡ�commentType

    public String getCommentGood() {
        return commentGood;
    }

    public void setCommentGood(String commentGood) {
        this.commentGood = commentGood;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }


    public String execute(){//���۲������ݿ�
        try {
            Connection connection= DBUtils.getConnection();
            Statement statement=(Statement) connection.createStatement();
            String sql="insert into commentinfo(name,comment,date,good) VALUES (\""
                    +getCommentName()+"\",\""+getCommentContent()+"\",\""+getCommentTime()+"\",\""+0+"\")";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
