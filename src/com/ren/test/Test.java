package com.ren.test;

import com.ren.service.GetModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/databsename?useSSL=false";
        String user = "username";
        String pass = "password";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn  =  DriverManager.getConnection(url,  user,  pass);
        //java文件输出路径
        String path = "H:\\pojo";
        GetModel getModel  = new GetModel(conn);
        /*
        使用：
            pojo: 包含属性与getter、setter
            bean: 包含属性与getter、setter与无参构造器，并实现Serializable接口
         */
        //生成pojo到path
        //getModel.generatePojo(path);
        //生成pojo到path，在pojo中添加包信息"package com.ren.model"
        //getModel.generatePojo(path, "com.ren.model");
        //生成Bean到path
        //getModel.generateBean(path);
        //生成Bean到path，在Bean中添加包信息"package com.ren.bean"
        //getModel.generateBean(path, "com.ren.bean");
    }
}
