package com.bjpowernode.controller;

import com.bjpowernode.dao.QueryDao;
import com.bjpowernode.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryProviceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        //调用dao，获取所有的省份信息， 是一个List集合
        QueryDao dao  = new QueryDao();
        List<Province> provinces = dao.queryProvinceList();
        //把list转为json格式的数据，输出给ajax请求
        if( provinces != null ){
            //调用jackson工具库，实现List--json
            ObjectMapper om  = new ObjectMapper();
            json = om.writeValueAsString(provinces);
        }

        //输出json数据，响应ajax请求的，返回数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw  = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }
}
