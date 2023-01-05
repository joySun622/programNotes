[Toc]

# 下载PL/SQL developer

下载安装地址: https://www.allroundautomations.com/registered-plsqldev/.

1. 自己选择合适版本。

![在这里插入图片描述](images/a2341748f1ee45d4b7d6f0dac0f5f66e.png)

2. 接下来默认安装即可
3. 本地连接直接输入

![在这里插入图片描述](images/bf44ff8f5d7748ba9e8d9c09a21a87e9.png)

4. **远程连接需要安装Instant Client**

# 安装Instant Client (PLSQL Developer远程连接工具)

下载安装地址:https://www.oracle.com/database/technologies/instant-client/downloads.html

1. **下载解压**
2. 进入目录`network–>admin`，删除已有文件，创建`tnsnames.ora`

![在这里插入图片描述](images/69a139d224744f08a441ccc227b5fe00.png)

```
#写入tnsnames.ora文件更改ip地址和端口号
BRXT_XC_CS =
  (DESCRIPTION =
    (ADDRESS_LIST =
      (ADDRESS = (PROTOCOL = TCP)(HOST = 127.0.0.1)(PORT = 8080))
    )
    (CONNECT_DATA =
      (SERVICE_NAME = BRXT)
    )
  )
```



3. 进入PLSQL–>配置–>首选项–>连接，更改oracle主目录和oci库。

![在这里插入图片描述](images/56ac8175f8d8437490d600112867d22e.png)

4. 保存配置后重启oracle，登陆界面会变化成下面，没有变化进行第五步

![在这里插入图片描述](images/eb84f0cccfbb4381be5ffeef4dc12933.png)

5. 配置环境变量
   ![在这里插入图片描述](images/7b0a28cbafae44b298b161f67bb51503.png)

# PL/SQL试用版破解方法

**打开注册表，在其中删除**
1.HKEY_CURRENT_USER/Software/Allround Automations
2.HKEY_CURRENT_USER/Software/Microsoft/Security
这个方法相当于无限试用期，应该一直都可以使用

# PLSQL配置快捷键

1. 打开插件所在文件夹：D:softwarePLSQL_Developer_15PlugIns
2. 创建shortcusts.txt文本，任意命名

![在这里插入图片描述](images/3483a115ae964affa0d5b256ca57ec36.png)

3. 写入以下快捷键，可自己设置

```
sf =select * from
sr = select t.* ,t.rowid from
w = where
sc=select count(1) from
```



4. 打开plsql进行设置

5. 打开PLSQL进行编辑
   输入`sf+空格`或者`tab`键



# 参考资料

1. https://blog.csdn.net/begefefsef/article/details/126773958