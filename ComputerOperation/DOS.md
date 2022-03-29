[Toc]

# 常用命令

## 查看网络端口

```
### 查找对应端口
netstat -ano | findstr "LISTENING" | findstr ":8080"
### 关闭指定pid程序
C:\Users\12613>taskkill /f /pid 17024
```

## 查看IP&物理地址信息

```
### 查看本机所有网络配置信息
ipconfig /all
```



## 查看本机开启的端口

```
netstat -a -n
```

