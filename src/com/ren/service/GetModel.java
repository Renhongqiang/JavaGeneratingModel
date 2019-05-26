package com.ren.service;


import com.ren.model.Column;
import com.ren.model.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetModel {
    private Connection connection;
    private List<Table> tables;

    public GetModel() {
    }

    public GetModel(Connection connection) throws SQLException {
        this.connection = connection;
        this.tables = getTableList();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * 获取数据库中所有表信息封装到List<Table> tables
     * @return List<Table> 包含数据库中所有的表
     * @throws SQLException
     */
    private List<Table> getTableList() throws SQLException {
        List<Table> tableList = new ArrayList<>();
        DatabaseMetaData dbMetaData = connection.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, null, new String[] { "TABLE" });
        int i = 0;
        while (rs.next()) {
            Table table = new Table();
            String tableName = rs.getString("TABLE_NAME");
            table.setTableName(tableName);
            table.setDatabaseName(rs.getString("TABLE_CAT"));
            table.setColumns(getTableColumnList(tableName));
            tableList.add(table);
        }
        return tableList;
    }

    /**
     * 获取某表的所有column信息封装到List<Column> columnList中
     */
    private List<Column> getTableColumnList(String tableName) throws SQLException {
        List<Column> columnList = new ArrayList<>();
        String  sql  =  "select  *  from " + tableName;
        PreparedStatement  stmt;
        try  {
            stmt  =  connection.prepareStatement(sql);
            ResultSet  rs  =  stmt.executeQuery(sql);
            ResultSetMetaData  data  =  rs.getMetaData();
            for  (int  i  =  1; i  <=  data.getColumnCount(); i++)  {
                Column column = new Column();
                //  获得所有列的数目及实际列数
                int  columnCount  =  data.getColumnCount();
                //  获得指定列的列名
                String  columnName  =  data.getColumnName(i);
                //  获得指定列的数据类型名
                String  columnTypeName  =  data.getColumnTypeName(i);
                //  对应数据类型的类
                String  columnClassName  =  data.getColumnClassName(i);
                //  在数据库中类型的最大字符个数
                int  columnDisplaySize  =  data.getColumnDisplaySize(i);
                //  某列类型的精确度(类型的长度)
                int  precision  =  data.getPrecision(i);
                //  小数点后的位数
                int  scale  =  data.getScale(i);
                //  是否自动递增
                Boolean  isAutoInctement  =  data.isAutoIncrement(i);
                //  是否为空
                int  isNullable  =  data.isNullable(i);
                column.setColumnName(columnName);
                column.setColumnTypeName(columnTypeName);
                column.setColumnClassName(columnClassName);
                column.setTableName(tableName);
                column.setColumnDisplaySize(columnDisplaySize);
                column.setPrecision(precision);
                column.setScale(scale);
                column.setAutoInctement(isAutoInctement);
                columnList.add(column);
            }
        }
        catch  (SQLException  e)  {
            e.printStackTrace();
        }
        return columnList;
    }

    /**
     * 为table创建pojo
     * @param table 表
     * @param path 输出路径
     */
    private static void writeFilePojo(Table table, String path, String packagePath) {
        String fileName = path + "\\" + upperCase(table.getTableName()) + ".java";
        try {
            FileWriter writer = new FileWriter(fileName);
            StringBuilder  getterSetter = new StringBuilder();
            //需要添加包路径
            if (!packagePath.equals("0")) {
                writer.write("package " + packagePath + ";\n\n");
            }
            writer.write("public class " + upperCase(table.getTableName()) + " {\n");
            for (Column column : table.getColumns()) {
                writer.write("    private " + changeType(column.getColumnTypeName()) + " " + column.getColumnName() + "\n");
                //getter()
                getterSetter.append("\n    public " + changeType(column.getColumnTypeName()) + " get" + upperCase(column.getColumnName()) + "() {\n");
                getterSetter.append("        return " + column.getColumnName() + ";\n");
                getterSetter.append("    }\n\n");
                //setter()
                getterSetter.append("    public void set" + upperCase(column.getColumnName()) + "(" + changeType(column.getColumnTypeName()) + " " + column.getColumnName() + ") {\n");
                getterSetter.append("        this." + column.getColumnName() + " = " + column.getColumnName() + ";\n");
                getterSetter.append("    }\n");
            }
            writer.write(getterSetter.toString());
            writer.write("}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 为table创建Bean
     * @param table 表
     * @param path 输出路径
     */
    private static void writeFileBean(Table table, String path, String packagePath) {
        String fileName = path + "\\" + upperCase(table.getTableName()) + ".java";
        try {
            FileWriter writer = new FileWriter(fileName);
            StringBuilder  getterSetter = new StringBuilder();
            //需要添加包路径
            if (!packagePath.equals("0")) {
                writer.write("package " + packagePath + ";\n\n");
            }
            writer.write("import java.io.Serializable;\n\n");
            writer.write("public class " + upperCase(table.getTableName()) + " implements Serializable {\n");
            for (Column column : table.getColumns()) {
                writer.write("    private " + changeType(column.getColumnTypeName()) + " " + column.getColumnName() + "\n");
                //getter()
                getterSetter.append("\n    public " + changeType(column.getColumnTypeName()) + " get" + upperCase(column.getColumnName()) + "() {\n");
                getterSetter.append("        return " + column.getColumnName() + ";\n");
                getterSetter.append("    }\n\n");
                //setter()
                getterSetter.append("    public void set" + upperCase(column.getColumnName()) + "(" + changeType(column.getColumnTypeName()) + " " + column.getColumnName() + ") {\n");
                getterSetter.append("        this." + column.getColumnName() + " = " + column.getColumnName() + ";\n");
                getterSetter.append("    }\n");
            }
            //无参构造器
            writer.write("\n    public " + upperCase(table.getTableName()) + "() { }\n");
            writer.write(getterSetter.toString());
            writer.write("}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 str 首字母大写
     * @param str
     * @return
     */
    private static String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将数据库类型转换为java中合适的类型
     * @param typeName
     * @return
     */
    private static String changeType(String typeName) {
        switch (typeName) {
            case "VARCHAR":
            case "CHAR":
            case "TEXT":
            case "TINYTEXT":
            case "MEDIUMTEXT":
            case "LONGTEXT":
            case "ENUM":
            case "SET":
                return "String";
            case "BLOB":
            case "BINARY":
            case "VARBINARY":
            case "TINYBLOB":
            case "MEDIUMBLOB":
            case "LONGBLOB":
                return "byte[]";
            case "INTEGER":
            case "ID":
            case "BIGINT":
                return "Long";
            case "TINYINT":
            case "SMALLINT":
            case "MEDIUMINT":
            case "INT":
                return "Integer";
            case "DECIMAL":
                return "BigDecimal";
            case "BIT":
                return "Boolean";
            case "FLOAT":
                return "Float";
            case "DOUBLE":
                return "Double";
            case "DATE":
            case "YEAR":
                return "Date";
            case "TIME":
                return "Time";
        }
        return "String";
    }

    /**
     * 根据tables生成pojo到path
     * @param path 输出路径
     */
    public void generatePojo(String path) {
        for (Table table : tables) {
            writeFilePojo(table, path, "0");
            System.out.println("已创建Pojo: " + path + "\\" + upperCase(table.getTableName()) + ".java");
        }
    }

    /**
     * 根据tables生成Bean到path
     * @param path 输出路径
     */
    public void generateBean(String path) {
        for (Table table : tables) {
            writeFileBean(table, path, "0");
            System.out.println("已创建Bean: " + path + "\\" + upperCase(table.getTableName()) + ".java");
        }
    }

    /**
     * 根据tables生成pojo到path
     * @param path 输出路径
     * @param packagePath 添加包路径
     */
    public void generatePojo(String path, String packagePath) {
        for (Table table : tables) {
            writeFilePojo(table, path, packagePath);
            System.out.println("已创建Pojo: " + path + "\\" + upperCase(table.getTableName()) + ".java    包名：" + packagePath);
        }
    }

    /**
     * 根据tables生成Bean到path
     * @param path 输出路径
     * @param packagePath 添加包路径
     */
    public void generateBean(String path, String packagePath) {
        for (Table table : tables) {
            writeFileBean(table, path, packagePath);
            System.out.println("已创建Bean: " + path + "\\" + upperCase(table.getTableName()) + ".java    包名：" + packagePath);
        }
    }
}
