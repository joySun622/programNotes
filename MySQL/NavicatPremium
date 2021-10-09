[Toc]

## NavicatPremium简介

> Navicat Premium 是MySQL，SQL Server，Oracle和PostgreSQL的一体化数据库管理工具，功能非常强大。

## 下载地址

[navicat-premium](https://www.navicat.com.cn/download/navicat-premium)

注册机下载地址：

[Navicat_Keygen_Patch_v5.6](http://pan.defcon.cn/file/1855579-449147144)

**注意事项：**

1. 运行注册机时最好关闭电脑的杀毒软件；

2. 运行注册机请断网，无需将注册机放到Navicat Premium安装目录下；
3. 请选择对各个版本，Products那块；
4. **安装完成后不要运行软件，然后打开注册机。**

## 激活方法

1. 运行注册机，勾选Backup、Host和Navicat v15，如图所示。**然后点击Patch按钮**，找到Navicat Premium 15安装路径下的navicat.exe，选中并点击打开，此时会提示：navicat.exe - x64 -> Cracked，提示已破解。

2. 点击下图的红色箭头所指的Generate，将自动生成Serial Keygen（即注册码），然后复制上。

[![img](images/Navicat_Keygen_Patch.png)](https://defcon.cn/wp-content/uploads/2013/11/Navicat_Keygen_Patch.png)

3. **现在**打开Navicat Premium 15，点击注册（或菜单栏的帮助——》注册），输入上一步生成的注册码，然后点击激活，**紧接着点手动激活**。

4. 将Navicat手动激活窗口的请求码框中内容复制到注册机Request Code框中，点击Activation Code下面的Generate按钮。

[![img](images/Navicat_Request_code.png)](https://defcon.cn/wp-content/uploads/2013/11/Navicat_Request_code.png)

5. 将注册机Activation Code处生成的激活码内容复制到Navicat激活码框中激活即可。

若多次激活失败，请先卸载已安装的Navicat Premium并清理残留文件夹和注册表，重启电脑，再尝试激活；

## 连接数据库问题集

1. ### 连接数据库报错

   <img src="images/image-20210930175208721.png" alt="image-20210930175208721" style="zoom:50%;" />

   - **原因探索**

     ```
     1. 在服务端查看mysql用户表，看root用户端口是否允许可以在任意IP或指定IP下访问
     mysql> select host,user from mysql.user ;
     +-----------------+---------------+
     | host            | user          |
     +-----------------+---------------+    |
     | localhost       | root          |
     +-----------------+---------------+
     2. 由上可知，root用户只允许本地IP访问，修改root用户IP访问权限
     mysql> rename user 'root'@'localhost' to 'root'@'%';
     Query OK, 0 rows affected (0.00 sec)
     3. 刷新下权限
     mysql> FLUSH PRIVILEGES;
     4. 重新使用客户端连接，连接成功
     ```

     

## 参考来源

1. https://www.jianshu.com/p/aca31d8f4c5b