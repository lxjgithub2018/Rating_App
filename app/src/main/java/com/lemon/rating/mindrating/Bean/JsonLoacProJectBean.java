package com.lemon.rating.mindrating.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/23.
 */

public class JsonLoacProJectBean {
    private ArrayList<ProjectBean> proJectList;
    private String msg;

    public ArrayList<ProjectBean> getProJect() {
        return proJectList;
    }
    public void setPeoJect(ArrayList<ProjectBean> proJectList) {
        this.proJectList = proJectList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
