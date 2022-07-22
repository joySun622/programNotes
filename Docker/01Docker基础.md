[Toc]

# 开发环境配置说明

> 1. 操作系统版本
>
>    ```
>    [root@hf-ssjzxwj-12 nginx]# cat /proc/version
>    Linux version 3.10.0-1160.11.1.el7.x86_64 (mockbuild@kbuilder.bsys.centos.org) (gcc version 4.8.5 20150623 (Red Hat 4.8.5-44) (GCC) ) #1 SMP Fri Dec 18 16:34:56 UTC 2020
>    ```
>
>    ```
>    [root@hf-ssjzxwj-12 jdk]# cat /etc/centos-release
>    CentOS Linux release 7.9.2009 (Core)
>    ```
>
>    
>
> 2. `docker`版本
>
>    ```
>    [root@hf-ssjzxwj-12 nginx]# docker -v
>    Docker version 20.10.14, build a224086
>    ```

# 常用命令说明
## 查看docker容器系统信息

> 查看docker 容器的系统版本：`cat /etc/os-release`
>
> ```
> [root@hf-ssjzxwj-12 opt]# cat /etc/os-release
> NAME="CentOS Linux"
> VERSION="7 (Core)"
> ID="centos"
> ID_LIKE="rhel fedora"
> VERSION_ID="7"
> PRETTY_NAME="CentOS Linux 7 (Core)"
> ANSI_COLOR="0;31"
> CPE_NAME="cpe:/o:centos:centos:7"
> HOME_URL="https://www.centos.org/"
> BUG_REPORT_URL="https://bugs.centos.org/"
> 
> CENTOS_MANTISBT_PROJECT="CentOS-7"
> CENTOS_MANTISBT_PROJECT_VERSION="7"
> REDHAT_SUPPORT_PRODUCT="centos"
> REDHAT_SUPPORT_PRODUCT_VERSION="7"
> 
> # 系统版本详情，redhat的命令：cat /etc/redhat-release   debain的命令：cat /etc/debian_version
> centos： cat /etc/centos-release
> ```

## 查看系统脚本类型

```
[root@hf-ssjzxwj-12 opt]# cat /etc/shells
/bin/sh
/bin/bash
/usr/bin/sh
/usr/bin/bash
```


## `docker run 参数`

```
命令格式：docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
Usage: Run a command in a new container
中文意思为：通过run命令创建一个新的容器（container）

常用选项说明
-d, --detach=false， 指定容器运行于前台还是后台，默认为false
-i, --interactive=false， 打开STDIN，用于控制台交互
-t, --tty=false， 分配tty设备，该可以支持终端登录，默认为false
-u, --user=""， 指定容器的用户
-a, --attach=[]， 登录容器（必须是以docker run -d启动的容器）
-w, --workdir=""， 指定容器的工作目录
-c, --cpu-shares=0， 设置容器CPU权重，在CPU共享场景使用
-e, --env=[]， 指定环境变量，容器中可以使用该环境变量
-m, --memory=""， 指定容器的内存上限
-P, --publish-all=false， 指定容器暴露的端口
-p, --publish=[]， 指定容器暴露的端口
-h, --hostname=""， 指定容器的主机名
-v, --volume=[]， 给容器挂载存储卷，挂载到容器的某个目录
--volumes-from=[]， 给容器挂载其他容器上的卷，挂载到容器的某个目录
--cap-add=[]， 添加权限，权限清单详见：http://linux.die.net/man/7/capabilities
--cap-drop=[]， 删除权限，权限清单详见：http://linux.die.net/man/7/capabilities
--cidfile=""， 运行容器后，在指定文件中写入容器PID值，一种典型的监控系统用法
--cpuset=""， 设置容器可以使用哪些CPU，此参数可以用来容器独占CPU
--device=[]， 添加主机设备给容器，相当于设备直通
--dns=[]， 指定容器的dns服务器
--dns-search=[]， 指定容器的dns搜索域名，写入到容器的/etc/resolv.conf文件
--entrypoint=""， 覆盖image的入口点
--env-file=[]， 指定环境变量文件，文件格式为每行一个环境变量
--expose=[]， 指定容器暴露的端口，即修改镜像的暴露端口
--link=[]， 指定容器间的关联，使用其他容器的IP、env等信息
--lxc-conf=[]， 指定容器的配置文件，只有在指定--exec-driver=lxc时使用
--name=""， 指定容器名字，后续可以通过名字进行容器管理，links特性需要使用名字
--net="bridge"， 容器网络设置:
bridge 使用docker daemon指定的网桥
host //容器使用主机的网络
container:NAME_or_ID >//使用其他容器的网路，共享IP和PORT等网络资源
none 容器使用自己的网络（类似--net=bridge），但是不进行配置
--privileged=false， 指定容器是否为特权容器，特权容器拥有所有的capabilities
--restart="no"， 指定容器停止后的重启策略:
no：容器退出时不重启
on-failure：容器故障退出（返回值非零）时重启
always：容器退出时总是重启
--rm=false， 指定容器停止后自动删除容器(不支持以docker run -d启动的容器)
--sig-proxy=true， 设置由代理接受并处理信号，但是SIGCHLD、SIGSTOP和SIGKILL不能被代理
示例
运行一个在后台执行的容器，同时，还能用控制台管理：docker run -i -t -d ubuntu:latest
运行一个带命令在后台不断执行的容器，不直接展示容器内部信息：docker run -d ubuntu:latest ping www.docker.com
运行一个在后台不断执行的容器，同时带有命令，程序被终止后还能重启继续跑，还能用控制台管理，docker run -d --restart=always ubuntu:latest ping www.docker.com
为容器指定一个名字，docker run -d --name=ubuntu_server ubuntu:latest
容器暴露80端口，并指定宿主机80端口与其通信(: 之前是宿主机端口，之后是容器需暴露的端口)，docker run -d --name=ubuntu_server -p 80:80 ubuntu:latest
指定容器内目录与宿主机目录共享(: 之前是宿主机文件夹，之后是容器需共享的文件夹)，docker run -d --name=ubuntu_server -v /etc/www:/var/www ubuntu:latest
```

# 安装`nginx`

## `nginx`安装前说明

> 1. 网络状态：内网状态，无法连接外网
> 2. `nginx`源码包`nginx-1.22.0.tar.gz`

## 1. 查看`nginx`可用版本

```
docker search nginx
```

## 2. 离线安装

> 1. 在可连网机器上导出nginx镜像
>
>    ```
>    docker save -o /root/app/nginx.tar nginx:latest
>    ```
>
> 2. 把`/root/app/nginx.tar`文件上传到无法上网的机器上然后执行：
>
>    ```
>    ### 创建文件夹
>    mkdir -p /data/nginx/conf
>    mkdir -p /data/nginx/html
>    mkdir -p /data/nginx/log
>    ### 导入镜像 --input , -i : 指定导入的文件，代替 STDIN。--quiet , -q : 精简输出信息。
>    docker load -i nginx.tar
>    ### 运行容器：’ -v ‘标志将当前工作目录挂载到容器中。’ -w ‘通过在目录中更改为’ pwd '返回的值
>    docker run -it -d --name nginx -p 80:80 -v /data/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v /data/nginx/html:/usr/share/nginx/html -v /data/nginx/log:/var/log/nginx nginx:latest
>    ```
>
> 3. 输入`docker images`检查nginx是否已安装
>
> 4. 启动nginx
>
> ```
> 启动Nginx: 
> docker run --name nginx -p 80:80 -d nginx
> -name nginx：容器名称。
> -p 80:80： 端口进行映射，将本地 80 端口映射到容器内部的 80 端口。
> -d nginx： 设置容器在在后台一直运行。
> ```

## 在线安装

> 1. 查看Docker镜像仓库中Nginx的可用版本
>
> ```
> docker search nginx
> ```
>
> 2. 拉取最新的Nginx镜像
>
> ```
> docker pull nginx:latest
> ```
>
> 3. 查看本地镜像
>
> ```
> docker images
> ```
>
> 4. 运行容器
>
> ```
> docker run --name nginx-test -p 8080:80 -d nginx
> 
> 命令参数说明：
> --name nginx-test：容器名称。
> -p 8080:80： 端口进行映射，将本地8080端口映射到容器内部的80端口。
> -d nginx： 设置容器在后台一直运行。
> ```



# JDK安装

1. **上传&解压jdk**

```
### 1. 将下载的jdk包导入/usr/local 下，解压
tar -zxvf jdk-8u271-linux-x64.tar.gz
### 2. 创建文件夹jdk,将解压文件拷贝到jdk目录下
mkdir jdk
```

2. **创建`Dockerfile`文件**

```
###创建Dockerfile:在/usr/local/docker/jdk文件夹下创建Dockerfile，Dockerfile内容如下：

FROM centos:centos7
MAINTAINER tao
# 新建目录
RUN mkdir /usr/local/jdk
WORKDIR /usr/local/jdk 
# 将jdk文件拷贝到容器/usr/local/jdk/并解压 
ADD jdk-8u144-linux-x64.tar.gz /usr/local/jdk 
# 设置环境变量 
ENV JAVA_HOME /usr/local/jdk/jdk1.8.0_144 
ENV JRE_HOME /usr/local/jdk/jdk1.8.0_144/jre 
ENV CLASSPATH .:${JAVA_HOME}/lib:${JRE_HOME}/lib 
ENV PATH $JAVA_HOME/bin:$PATH
```

3. **构建镜像**

```
#构建镜像:--name java-8 容器名，自定义的java1.8:0.1 镜像名：标签名 ， 使用 docker images 查看
docker build -t jdk1.8:0.1 .    //  .表示当前目录；Dockerfile 所处的文件路径，当前目录使用"." 即可
#查看镜像文件是否构建成功 
docker images
```

- **构建镜像报错**

```
### 构建镜像报错，报错信息如下
[root@hf-ssjzxwj-12 jdk]# docker build -t jdk1.8 .
Sending build context to Docker daemon  369.9MB
Step 1/8 : from centos:7.9.2009
Get "https://registry-1.docker.io/v2/": net/http: request canceled while waiting for connection (Client.Timeout exceeded while awaiting headers)

### 解决方案

```

4. **启动容器**

```
#运行：
docker run -di --name=jdk1.8 jdk1.8:0.1 
#进入容器查看JDK是否安装成功 
docker exec -it jdk1.8 /bin/bash java -version
```






# 参考来源

1. https://blog.csdn.net/aa_xff/article/details/119323983
2. https://www.cnblogs.com/nuli/p/15101308.html