[Toc]

# 常用命令

# git pull 强制覆盖本地的代码

- **场景描述**

> git pull 强制覆盖本地的代码方式，不需要保留本地修改代码的情况

- **解决方案**

> 1. `git fetch --all`  从远程下载最新的，而不尝试合并或[rebase](https://so.csdn.net/so/search?q=rebase&spm=1001.2101.3001.7020)任何东西。
>
> 2. 然后，你有两个选择：
>
>    ```bash
>    git reset --hard origin/master
>    ```
>
>    或者如果你在其他分支上：
>
>    ```bash
>    git reset --hard origin/<branch_name>
>    ```
>
>    git [reset](https://so.csdn.net/so/search?q=reset&spm=1001.2101.3001.7020)将主分支重置为您刚刚获取的内容。 --hard选项更改工作树中的所有文件以匹配origin/master中的文件。

# windows下git显示文件被修改，实际没有改动

- **场景描述**

> 当拉取代码到本地后，没有对代码做任何改动，但是使用`git status`查看代码情况，发现项目的所有文件都提示有改动。

- **原因**

> 1. 不同操作系统使用的换行符是不同导致；
>    Unix/Linux使用的是LF，Mac后期也采用了LF，但Windows一直使用CRLF【回车(CR, ASCII 13, \r) 换行(LF, ASCII 10, \n)】作为换行符。而git入库的代码采用的是LF格式，它考虑到了跨平台协作的场景，提供了“换行符自动转换”的功能：如果在Windows下安装git，在拉取文件时，会自动将LF换行符替换为CRLF；在提交时，又会将CRLF转回LF。但是这个转换是有问题的：有时提交时，CRLF转回LF可能会不工作，尤其是文件中出现中文字符后有换行符时。
> 2. 可能是文件读写等权限的变更

- **解决方案**

> 1. 禁用git的自动换行功能：
>
>    在本地路径C:\ Users\ [用户名] \ .gitconfig下修改git配置[core]，如果没有就直接添加上去：
>
>    ```
>    [core]
>    autocrlf = false  //禁用自动换行
>    filemode = false   //禁用文件读写权限/模式设置的变更
>    safecrlf = true
>    ```
>
> 2. git bash命令行也可以修改，最终也是修改.gitconfig配置文件：分别执行以下命令
>
>         ```
> git config --global core.autocrlf false
> git config --global core.filemode false
> git config --global core.safecrlf true
>         ```
>
> 

# 参考资料来源

1. https://javaforall.cn/112449.html