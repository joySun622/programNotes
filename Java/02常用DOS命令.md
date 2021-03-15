### 常用DOS命令
```
dir 列出当前目录下的文件以及文件夹
md +文件名 创建目录
rd  删除目录，只能删除空目录
cd  进入指定目录
cd..  退回到上一级目录
cd\  退回到根目录
del  删除文件
exit  退出dos命令行
echo javase>1.doc  创建内容为javase的1.doc文件

常用快捷键：
<——  ——> 移动光标
↓ ↑ 调阅历史操作命令
Delete Backspace  删除字符

```

**示例**
1. 进入d盘
```
C:\Users\12613>d:

//结果
D:\>

```
2. 显示d盘目录
```
D:\>dir

//执行结果

 驱动器 D 中的卷是 新加卷
 卷的序列号是 3AB1-75ED
 D:\ 的目录

2020/02/13  16:55    <DIR>          360
2020/02/15  13:23    <DIR>          commonApp
2020/07/03  16:02    <DIR>          download
2020/05/12  17:15    <DIR>          freeworkspace
2019/10/19  10:55    <DIR>          KuGou
2020/01/02  17:48    <DIR>          programApp
2020/01/02  16:57    <DIR>          QMDownload
2020/07/03  14:57    <DIR>          temp
2020/02/14  13:15    <DIR>          程序
2020/06/20  00:01    <DIR>          迅雷下载
               0 个文件              0 字节
              10 个目录 184,597,286,912 可用字节
```
3. 目录切换
```
C:\Users\12613>d:   //由c盘进入d盘

D:\>md java  //在d盘创建目录java

D:\>cd d:\java  //进入d盘java目录方式1

d:\java>md temp1 //在d盘java目录下创建temp1目录

d:\java>cd temp1 // 进入d盘java目录下创建temp1目录

d:\java\temp1>cd .. //返回temp1上层目录方式1

d:\java>cd.. //返回上一层目录

d:\>cd java\temp1 //进入指定目录方式2

d:\java\temp1>cd/  //直接返回根目录方式1

d:\>cd java/temp1

d:\java\temp1>cd\  //直接返回根目录方式2

d:\java>echo javase>1.doc //在java目录下创建1.doc文件
d:\java>del 1.doc //删除java目录下的1.doc文件
d:\java>del *.doc //删除所有以doc结尾的文件
d:\java>rd temp1  //删除temp1空目录

//使用rd删除不为空目录时
d:\java>rd temp2  //直接使用rd会提示目录不为空
目录不是空的。
d:\java>del temp2 //使用del删除temp2下文件
d:\java\temp2\*, 是否确认(Y/N)? y
d:\java>rd temp2 //此时temp2目录下为空，可删除成功
```