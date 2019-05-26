package com.ren.model;

import java.util.List;

public class Table {
    private String tableName;
    private String databaseName;
    private List<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "\n     tableName='" + tableName + '\'' +
                "\n     databaseName='" + databaseName + '\'' +
                "\n     columns=" + columns +
                '}';
    }
}
