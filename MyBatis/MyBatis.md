[Toc]

# 问题记录

# invalid comparison: java.util.Date and java.lang.String(无效的比较)

在使用[mybatis](https://so.csdn.net/so/search?q=mybatis&spm=1001.2101.3001.7020)的时候，在mapper.xml中写了一段这样的代码，我的startTime是Date类型的

```
<if test="startTime != null and startTime != ''">
            
 </if>
```

此时就会报错`invalid comparison: java.util.Date and java.lang.String(无效的比较)`，不能用Date类型与String类型进行比较，问题出现在下面这段代码

`and startTime != ''`
在mybatis中对Date类型进行空判断，只需要如下即可

```
<if test="startTime != null">
 
 </if>
```

