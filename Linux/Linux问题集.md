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

# 使用yum安装应用报错

- **问题描述**

> 使用yum安装应用时报错，报错信息如下
>
> ```
> [root@localhost yum.repos.d]# yum install -y lsof
> 已加载插件：fastestmirror
> Loading mirror speeds from cached hostfile
>  * base: mirrors.aliyun.com
>  * extras: mirrors.aliyun.com
>  * updates: mirrors.aliyun.com
> base                                                                              | 3.6 kB  00:00:00     
> docker-ce-stable                                                                  | 3.5 kB  00:00:00     
> file:///mnt/cdrom/repodata/repomd.xml: [Errno 14] curl#37 - "Couldn't open file /mnt/cdrom/repodata/repomd.xml"
> 正在尝试其它镜像。
> 
> 
>  One of the configured repositories failed (dvd),
>  and yum doesn't have enough cached data to continue. At this point the only
>  safe thing yum can do is fail. There are a few ways to work "fix" this:
> 
>      1. Contact the upstream for the repository and get them to fix the problem.
> 
>      2. Reconfigure the baseurl/etc. for the repository, to point to a working
>         upstream. This is most often useful if you are using a newer
>         distribution release than is supported by the repository (and the
>         packages for the previous distribution release still work).
> 
>      3. Run the command with the repository temporarily disabled
>             yum --disablerepo=dvd ...
> 
>      4. Disable the repository permanently, so yum won't use it by default. Yum
>         will then just ignore the repository until you permanently enable it
>         again or use --enablerepo for temporary usage:
> 
>             yum-config-manager --disable dvd
>         or
>             subscription-manager repos --disable=dvd
> 
>      5. Configure the failing repository to be skipped, if it is unavailable.
>         Note that yum will try to contact the repo. when it runs most commands,
>         so will have to try and fail each time (and thus. yum will be be much
>         slower). If it is a very temporary problem though, this is often a nice
>         compromise:
> 
>             yum-config-manager --save --setopt=dvd.skip_if_unavailable=true
> 
> failure: repodata/repomd.xml from dvd: [Errno 256] No more mirrors to try.
> file:///mnt/cdrom/repodata/repomd.xml: [Errno 14] curl#37 - "Couldn't open file /mnt/cdrom/repodata/repomd.xml"
> ```

- **报错原因**

> 从报错信息中可以看出，yum安装软件时无法找到cdrom—也就是dvd的挂载点信息。因为在`/etc/yum.ropo.d/dvd.repo`配置了源地址`/mnt/cdrom`，但该目录下没有相应的源应用信息，所以导致报错

- **解决方案**

> 将linux光盘挂载到`/mnt/cdrom`目录下

```
[root@localhost ~]# ls /mnt/cdrom
[root@localhost ~]#  mount /dev/cdrom /mnt/cdrom
mount: /dev/sr0 写保护，将以只读方式挂载
[root@localhost ~]# ls /mnt/cdrom
CentOS_BuildTag  EULA  images    LiveOS    repodata              RPM-GPG-KEY-CentOS-Testing-7
EFI              GPL   isolinux  Packages  RPM-GPG-KEY-CentOS-7  TRANS.TBL

```

