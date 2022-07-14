[Toc]

## Linux安装JDK

### 方式1：yum一键安装

```
### 1. 查看是否有旧版本JDK有则删除
[root@mysql java]# rpm -qa | grep java | xargs rpm -e --nodeps

### 2. 查询你自己需要安装的版本，这里会出现一个列表
[root@mysql java]# yum -y list java*
已加载插件：fastestmirror
Loading mirror speeds from cached hostfile
 * base: mirrors.tuna.tsinghua.edu.cn
 * epel: mirrors.tuna.tsinghua.edu.cn
 * extras: mirrors.tuna.tsinghua.edu.cn
 * updates: mirrors.bupt.edu.cn
可安装的软件包
java-1.6.0-openjdk.x86_64                            1:1.6.0.41-1.13.13.1.el7_3       base   
java-1.6.0-openjdk-demo.x86_64                       1:1.6.0.41-1.13.13.1.el7_3       base   
java-1.6.0-openjdk-devel.x86_64                      1:1.6.0.41-1.13.13.1.el7_3       base   
java-1.6.0-openjdk-javadoc.x86_64                    1:1.6.0.41-1.13.13.1.el7_3       base   
java-1.6.0-openjdk-src.x86_64                        1:1.6.0.41-1.13.13.1.el7_3       base   
java-1.7.0-openjdk.x86_64                            1:1.7.0.261-2.6.22.2.el7_8       base   
java-1.7.0-openjdk-accessibility.x86_64 

### 3. 安装JDK
[root@mysql java]# yum install java-1.8.0-openjdk* -y  //这条命令是安装所有匹配的1.8

选择一个java版本进行安装，这里选择java-1.8.0-openjdk-devel.x86_64。（这里有个地方要注意，上图中我用红框圈起来的两个java版本，要选择-devel的安装，因为这个安装的是jdk，而那个不带-devel的安装完了其实是jre。）
[root@mysql java]# yum install -y java-1.8.0-openjdk-devel.x86_64 //耐心等待至自动安装完成。
[root@mysql java]# java -version
openjdk version "1.8.0_302"
OpenJDK Runtime Environment (build 1.8.0_302-b08)
OpenJDK 64-Bit Server VM (build 25.302-b08, mixed mode)

### yum方式安装默认目录/usr/lib/jvm。
rpm -qa 查看所有安装的rpm包
rpm -qa|grep java 查看安装的jdk包

```

### 方式2：从官网下载包安装jdk

```
1、官方下载linux版本的jdk11，http://jdk.java.net/java-se-ri/11
2、将下载的jdk11上传至服务器（/usr/lib/java）也可以直接在linux上直接使用wget 下载安装包
3、将jdk11解压到/usr/java目录 
[root@mysql java]# tar -xzvf jdk-8u271-linux-x64.tar.gz -C /usr/java
4、#为了方便可以改个名字 
mv jdk-11 java11
5、配置环境变量
   	Linux环境变量设置文件
	/etc/profile 全局用户，应用于所有的Shell。
	/$HOME/.profile 当前用户，应用于所有的Shell。
	/etc/bash_bashrc 全局用户，应用于Bash Shell。
	~/.bashrc 局部当前，应用于Bash Sell。
	~/.bash_profile 每个用户都可使用该文件输入专用于自己使用的shell信息,当用户登录时,该文件仅仅执行一次!默认情况下,他设置一					些环境变量,执行用户的.bashrc文件.

### 用户环境变量
[root@mysql ~]# ls -a	#显示全部文件包括隐藏文件（找到.bashrc文件）
[root@mysql ~]# vi .bashrc	#配置环境变量
##vi打开文件进入命令模式，按i进入编辑模式，
##在文件最后加上环境变量配置内容，
##按exit退出编辑模式返回命令模式，输入:wq保存并退出vim
# export JAVA_HOME=/usr/local/java/jdk11
# export JRE_HOME=${JAVA_HOME}/jre
# export CLASSPATH=.：${JAVA_HOME}/lib:${JRE_HOME}/lib
# export PATH=${JAVA_HOME}/bin:$PATH

### 系统环境变量
[root@mysql /]# vim /etc/profile  #在文件最后编辑环境变量
export JAVA_HOME=/usr/local/java/jdk11
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

[root@mysql /]# source /etc/profile #刷新，使配置文件立即生效

### Java -version 或 直接java命令测试jdk运行环境是否安装成功
[root@mysql /]# java -version
openjdk version "11" 2018-09-25
OpenJDK Runtime Environment 18.9 (build 11+28)
OpenJDK 64-Bit Server VM 18.9 (build 11+28, mixed mode)

### 建立一个jdk连接
ln -s /home/java/jdk1.8.0_131/bin/java /usr/bin/java 建一个/usr/bin/java的java链接。
为什么要建这个超链接，因为一些自己注册的linux服务（如springboot的jar注册的服务），默认情况下从/usr/bin/java路径使用java，yum安装的时候，这个超链接会自动创建，如果你自己下载包安装的话，这个超链接就需要你手动创建了。
```





## 参考资料

1. https://www.cnblogs.com/zhenling/p/14321205.html