package com.ren.model;

public class Column {
    //  列名
    private String columnName;
    // 列的数据类型名
    private String columnTypeName;
    //  对应数据类型的类
    private String columnClassName;
    //  在数据库中类型的最大字符个数
    private int columnDisplaySize;
    //  某列类型的精确度(类型的长度)
    private int  precision;
    //  小数点后的位数
    private int  scale;
    //  获取某列对应的表名
    private String  tableName;
    //  是否自动递增
    private Boolean  isAutoInctement;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getAutoInctement() {
        return isAutoInctement;
    }

    public void setAutoInctement(Boolean autoInctement) {
        isAutoInctement = autoInctement;
    }

    @Override
    public String toString() {
        return "\n      Column{" +
                "columnName='" + columnName + '\'' +
                ", columnTypeName='" + columnTypeName + '\'' +
                ", columnClassName='" + columnClassName + '\'' +
                ", columnDisplaySize=" + columnDisplaySize +
                ", precision=" + precision +
                ", scale=" + scale +
                ", tableName='" + tableName + '\'' +
                ", isAutoInctement=" + isAutoInctement +
                '}';
    }
}
