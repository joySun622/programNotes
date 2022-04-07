[Toc]

# 运行jar包

- **前台运行**

```
 java -Dfile.encoding=utf-8 -jar TJServer.jar
 
 -Dfile.encoding=utf-8 :运行jar文件编码设置
```

- **后台运行**

```
//运行TJServer.jar文件，并将日志写入tmp.text文件中
nohup  java -Dfile.encoding=utf-8  -jar TJServer.jar > ./tmp.text &
```

# 访问http

## `curl`

```
cur http://10.11.156.17:8082/car/list
```

## `wget`

```
wget http://10.11.156.17:8082/car/list
```

# 查找指定程序

```
### 查找nginx
[root@bj-scjg-aj-xtyy-001-4 data]# whereis nginx
nginx: /etc/nginx

### 查找passwd
[root@bj-scjg-aj-xtyy-001-4 data]# which passwd
/usr/bin/passwd
```

# 查看端口启用情况

## `lsof`

```
### 使用lsof
[root@bj-scjg-aj-xtyy-001-4 data]# lsof -i:8082
COMMAND   PID USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
java     9777 root  310u  IPv6 778235      0t0  TCP *:us-cli (LISTEN)
```

## `netstat`

```
[root@bj-scjg-aj-xtyy-001-4 data]# netstat -anp | grep 80
tcp        0      0 0.0.0.0:8086            0.0.0.0:*               LISTEN      11630/nginx: master 
tcp        0      0 0.0.0.0:8089            0.0.0.0:*               LISTEN      11630/nginx: master 
```

# 查看程序运行情况

## `ps`

> 静态查看进程运行状态

```
 ps -ef | grep tomcat
```

## `top`

> 动态查看进程状态

```
top
```

# 查看日志/文件

```
常见查看文件内容命令汇总如下：

cat     filename           查看日志，会打开整个文件，直接跑到最后面
tac     filename           查看日志，会打开整个文件，倒序显示，不常用
more    filename             查看日志，可以上下翻页，上下行移动显示
less    filename           查看日志，和more命令类似，但不能往回翻页
tail   -f   filename         查看文件，实时显示最后一页
vi        filename           查看或编辑文件
```

