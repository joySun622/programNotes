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

# 解决Git 出现 Unlink of file 'xxx' failed. Should I try again.

- **问题描述**

​       出现如下图所示弹框 Unlink of file 'xxxxx'

- **问题原因**
   该版本控制中的文件被其他程序或者IDE调用。导致Git无法对它进行操作。
-  **解决办法**
   关闭IDE或者其他调用该文件的程序。再次点击【是(Y)】按钮进行提交。

![img](images/25546667-0dd2fbf06a5f3d6b.png)



# Git 执行reset后恢复误删文件

- **场景描述**

> 执行`git reset`后，一些修改文件夹下的文件也被删除了，但这部分文件是所需要的，需要将误删的文件找回来

- **解决方案1**

> 1. `git reflog` 获取之前所有操作日志
>
> ![img](images/v2-9ad72bcc34935cbac63c138d2acccb5e_720w.jpg)
>
> 2. **`git reset --hard [索引值]`** 
>
>    ```
>    git reset --soft HEAD^ ：保留工作目录，并把重置 HEAD 所带来的新的差异放进暂存区；
>    git reset --mixed HEAD^ ： reset 如果不加参数 那么默认使用–mixed 参数 。它的行为是：保留工作目录 并且清空暂存区. 也就是说 工作目录的修改、暂存区的内容以及由reset所导致的新的文件的差异，都会被放进工作目录。
>    git reset --hard HEAD^ ： 重置stage区和工作目录，没有commit的修改会被全部丢失，即，工作目录里的新改动和已经add到stage区的新改动全部丢失（慎用！）
>    ```
>
> 3. 输入`git fsck --lost-found` 然后到项目的 `.git/lost-found `文件夹里可以找回部分或者全部的文件 ( `git fsck --lost-found `可以通过一些神奇的方式把曾经add到暂存区过的文件以某种算法算出来加到  .git/lost-found 文件夹里，直接去文件夹里找便可以找到丢失的特殊文件）
>
>    ![img](images/2474459-20220517200457385-1149649364.png)

- **解决方案2**

> 选择项目目录/文件查看VS code 【打开时间线】查看本地历史记录，选择对应的记录进行恢复
>
> ![image-20220921100013448](images/image-20220921100013448.png)



# git 单个文件回滚到指定版本

##### 1 .进入到文件所在文件目录，或者能找到文件的路径

查看文件的修改记录：git log fileName
 `git log pdf_extractor.py`

![img](https:////upload-images.jianshu.io/upload_images/24452689-f6a400c5952c888c.png?imageMogr2/auto-orient/strip|imageView2/2/w/586/format/webp)

git log


 ab50632384c452dcfec99c13e7bc64182cee5d0f



##### 2.回退到指定版本

git reset 版本号  fileName
 `git reset ab50632384c452dcfec99c13e7bc64182cee5d0f pdf_extractor.py`

##### 3.提交到本地参考

git commit -m “提交的描述信息”
 `git commit -m “提交的描述信息”`

##### 4.更新到工作目录

git checkout fileName
 `git checkout pdf_extractor.py`

![img](https:////upload-images.jianshu.io/upload_images/24452689-9deda3f4b990c0c2.png?imageMogr2/auto-orient/strip|imageView2/2/w/568/format/webp)

stepp3&4



##### 5.提交到远程仓库

git push



![img](https:////upload-images.jianshu.io/upload_images/24452689-410bf4d02e6bbbec.png?imageMogr2/auto-orient/strip|imageView2/2/w/521/format/webp)

push

# 参考资料来源

1. https://javaforall.cn/112449.html
2. https://www.jianshu.com/p/3d94619fb1d8
3. https://www.jianshu.com/p/551741eb1735