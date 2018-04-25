package com.lemon.rating.mindrating.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 */

public class ProjectBean {
    //项目Id
    private int proJectId;
    //项目创建人
    private String proJectPeo;
    //项目名称
    private String projectName;
    //项目图片
    private String imgPath;
    //评分规则
    private String rules;
    //项目介绍
    private String projectDescribe;
    //项目发布时间
    private String projectTime;
    //项目选手
    private List<String> listPlayer;
    //项目内容
    private List<String> listItem;
    //项目评委
    private List<String> listScore;

    public int getProJectId() {
        return proJectId;
    }
    public void setProJectId(int proJectId) {
        this.proJectId = proJectId;
    }
    public String getProJectPeo() {
        return proJectPeo;
    }
    public void setProJectPeo(String proJectPeo) {
        this.proJectPeo = proJectPeo;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public String getRules() {
        return rules;
    }
    public void setRules(String rules) {
        this.rules = rules;
    }
    public String getProjectDescribe() {
        return projectDescribe;
    }
    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }
    public String getProjectTime() {
        return projectTime;
    }
    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }
    public List<String> getListPlayer() {
        return listPlayer;
    }
    public void setListPlayer(List<String> listPlayer) {
        this.listPlayer = listPlayer;
    }
    public List<String> getListItem() {
        return listItem;
    }
    public void setListItem(List<String> listItem) {
        this.listItem = listItem;
    }
    public List<String> getListScore() {
        return listScore;
    }
    public void setListScore(List<String> listScore) {
        this.listScore = listScore;
    }
}
