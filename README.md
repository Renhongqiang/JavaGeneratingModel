# JavaGeneratingModel
Generate POJO or Bean java files based on database using java.

### IDE: 
IntelliJ IDEA 2017.2.6 x64

### JDK: 
JDK1.8.0_201

### Directory tree

<pre>
└─src
    └─com
        └─ren
            ├─model
            │      Column.java      # Data Table Column Model
            │      Table.java       # Table Model
            │      
            ├─service
            │      GetModel.java    # Output POJO or Bean java files based on database connections
            │      
            └─test
                    Test.java
</pre>

---

### Explain：
Sometimes I create models based on table information in the database. This tool automatically generates all models based on a database connection. You can generate POJOs that contain only attributes and getter and setter methods. You can also generate JavaBeans, it contain Non-parametric constructor and implement Serializable.

### How to use

1、Import jar getmodel-1.0.jar or use this project.

2、You can refer to the example <a href="https://github.com/Renhongqiang/JavaGeneratingModel/blob/master/src/com/ren/test/Test.java">src/com/ren/test/Test.java</a>

<pre>
	//File output path
       	String path = "H:\\pojo";
        GetModel getModel  = new GetModel(conn);
        /*
        Description：
            pojo: Contains attributes and getter、setter
            bean: Contains properties and getters, setters and no-argument constructors, and implements the Serializable interface
         */
        //Generate pojo to path
        //getModel.generatePojo(path);
        //Generate pojo to path and add package information "package com.ren.model" in pojo
        //getModel.generatePojo(path, "com.ren.model");
        //Generate Bean to path
        //getModel.generateBean(path);
        //Generate a bean to the path and add the package information "package com.ren.bean" to the bean.
        //getModel.generateBean(path, "com.ren.bean");
</pre>




