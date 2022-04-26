[Toc]

# 官网地址

- [React中文说明文档](https://react.docschina.org/docs/getting-started.html)



# 环境搭建

## 1. 安装`node`

```
# 监测node是否安装成功
node -v
```

## 2. 安装：create-[react](https://so.csdn.net/so/search?q=react&spm=1001.2101.3001.7020)-app

**npm方式：**` npm install -g create-react-app`

## 3. 创建React项目

```
create-react-app hello-app
```

## 4. 运行React项目

> 进入到项目目录下，然后使用以下命令运行项目

```
 npm start   //启动开发环境服务
    Starts the development server.

  npm run build   #生成项目静态文件
    Bundles the app into static files for production.

  npm test  //启动测试环境服务
    Starts the test runner.

  npm run eject  //会将项目工具，依赖，配置文件，脚本移动到项目目录下。此过程不可逆
    Removes this tool and copies build dependencies, configuration files
    and scripts into the app directory. If you do this, you can’t go back!

We suggest that you begin by typing:

  cd hello-app
  npm start
```



# 创建`React`项目报错

## 1. `tar`需升级

- **报错信息**

```
npm WARN deprecated tar@2.2.2: This version of tar is no longer supported, and will not receive security updates. Please upgrade asap. 
```

- **解决方案**:安装最新版本tar

```
npm install -g tar
```

## 2. 无法加载文件 `C:\....\create-react-app.ps1`

- **报错信息**

```
PS D:\huadi_workspace\tjyz_workspace\old_tjyz_project\tjyz\TJYZ-bdp1> create-react-app hello-app

create-react-app : 无法加载文件 C:\Users\12613\AppData\Roaming\npm\create-react-app.ps1，因为在此系统上禁止运行脚本。有关详细信息，请参阅 htt
ps:/go.microsoft.com/fwlink/?LinkID=135170 中的 about_Execution_Policies。
所在位置 行:1 字符: 1
+ create-react-app hello-app
+ ~~~~~~~~~~~~~~~~
    + CategoryInfo          : SecurityError: (:) []，PSSecurityException
    + FullyQualifiedErrorId : UnauthorizedAccess
```

- **解决方案**

1. 搜索框输入：Windos PowerShell 并且以右键管理员身份运行

<img src="images/1881711-20200129122256589-1292011372.png" alt="img" style="zoom:50%;" />

2. 打开了命令行之后,输入`set-ExecutionPolicy RemoteSigned`，并且把权限改权限为A，然后通过 `get-ExecutionPolicy` 查看当前的状态：

![img](images/1881711-20200129122410236-1477976067.png)

3、最后我们就可以使用命令创建项目了。

![img](images/1881711-20200129122600590-1509977033.png)

# 参考资料

1. https://www.bilibili.com/video/BV1wy4y1D7JT?spm_id_from=333.337.search-card.all.click