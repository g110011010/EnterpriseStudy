package com.saber.pojo;

/**
 * Created by Saber on 2017/7/3.
 */
public class Course {//�γ�
    private String type;//�γ�����
    private String name;//�γ���
    private String describe;//�γ�����
    private String key;//�γ̹ؼ���
    private String link;//�γ̵�����
    private String source;//�γ�ͼƬ������

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
