[Toc]

# 安装报错

- **报错原文**

  ```
  Public key for mysql-community-server-5.7.37-1.el7.x86_64.rpm is not installed
  Failing package is: mysql-community-server-5.7.37-1.el7.x86_64
  GPG Keys are configured as: file:///etc/pki/rpm-gpg/RPM-GPG-KEY-mysql
  
  
  Mysql安装失败
  操作系统：CentOS 7.6
  Mysql版本：mysql5.7
  CentOS7.6 安装mysql5.7的时候报错，提示某一个包安装不成功。
  ```

- **失败原因**
  GPG对于包的源key的验证没有通过

- **解决办法**
  在yum install 版本后面加上 --nogpgcheck，即可绕过GPG验证成功安装。比如`yum install mysql-community-server --nogpgcheck`
  


