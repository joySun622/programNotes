[Toc]

# 开发防火墙端口

- **查看当前已经开放的端口**

```
[root@localhost ~]# firewall-cmd --list-ports
```

- **开启端口，以`8888`为例**

```
firewall-cmd --zone=public --add-port=8888/tcp --permanent
```

- **重启防火墙：**

```
firewall-cmd --reload
```

- **其他指令：**

```
# 关闭防火墙
firewall systemctl stop firewalld.service

# 关闭防火墙开机启动
firewall sustemctl disable firewalld.service
```

