[toc]

# 标题

>  使用 **#** 号可表示 1-6 级标题，一级标题对应一个 **#** 号，二级标题对应两个 **#** 号，以此类推。

```
# 一级标题
## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题
```

# 字体

```
*斜体文本*
_斜体文本_
**粗体文本**
__粗体文本__
***粗斜体文本***
___粗斜体文本___
```

# Markdown 段落
```
1. Markdown 段落没有特殊的格式，直接编写文字就好，段落的换行是使用两个以上空格加上回车。
2. 当然也可以在段落后面使用一个空行来表示重新开始一个段落。
```


# 分隔线 

> 你可以在一行中用三个以上的星号、减号、底线来建立一个分隔线，行内不能有其他东西。你也可以在星号或是减号中间插入空格。下面每种写法都可以建立分隔线

```
***

* * *

*****

- - -

----------
```
# 删除线

> 如果段落上的文字要添加删除线，只需要在文字的两端加上两个波浪线 **~~** 即可，实例如下：  

```
~~BAIDU.COM~~
```

# 下划线

```
下划线可以通过 HTML 的 <u> 标签来实现：
<u>带下划线文本</u>
```

# 脚注

> 脚注是对文本的补充说明。

```
创建脚注格式类似这样 [^RUNOOB]。 // 引用脚注

[^RUNOOB]: 菜鸟教程 -- 学的不仅是技术，更是梦想！！！  //创建脚注
```

# 列表
> Markdown 支持有序列表和无序列表。

## 无序列表

> 无序列表使用星号(*)、加号(+)或是减号(-)作为列表标记，这些标记后面要添加一个空格，然后再填写内容：

```
* 第一项
* 第二项
* 第三项

+ 第一项
+ 第二项
+ 第三项


- 第一项
- 第二项
- 第三项
```

## 有序列表

> 有序列表使用数字并加上 **.** 号来表示，如：

```
1. 第一项
2. 第二项
3. 第三项
```

## 列表嵌套
> 列表嵌套只需在子列表中的选项前面添加四个空格即可：
```
1. 第一项：
    - 第一项嵌套的第一个元素
    - 第一项嵌套的第二个元素
2. 第二项：
    - 第二项嵌套的第一个元素
    - 第二项嵌套的第二个元素
```

# 区块
> Markdown 区块引用是在段落开头使用 > 符号 ，然后后面紧跟一个空格符号：
```
> 区块引用
> 菜鸟教程
> 学的不仅是技术更是梦想
```
> 另外区块是可以嵌套的，一个 > 符号是最外层，两个 > 符号是第一层嵌套，以此类推：
```
> 最外层
> > 第一层嵌套
> > > 第二层嵌套
```
> 区块中使用列表
```
> 区块中使用列表
> 1. 第一项
> 2. 第二项
> + 第一项
> + 第二项
> + 第三项
```
> 列表中使用区块
```
* 第一项
    > 菜鸟教程
    > 学的不仅是技术更是梦想
* 第二项
```

# 代码

```
# 少量代码用单个反引号包裹
`printf()` 函数  

# 大量代码块，用 ``` 包裹，并指定一种语言（也可以不指定）
​```javascript
$(document).ready(function () {
    alert('RUNOOB');
});
​```

```

# 链接&跳转

## 文档间跳转

> 从a.md文档，打开b.md文档，在Typora中的设置。
>
> a文档设置：
>
> ```
> [b文档](./b.md)
> 小括号中需为b.md的路径，相对路径，或者是绝对路径。最好是相对路径，绝对路径如果文档换个存储位置，就无法链接到。
> ./b.md  :表示在当前目录下的b.md文件
> ```
>
> 

## 行内链接

- **格式**

```
方式1：[链接名称](链接地址)

方式2：<链接地址>
```

- **范例**
  方式1：[百度](http://www.baidu.com)
  方式2：<htttp://www.baidu.com>

## 引用链接

> 在文章末尾定义链接，在文中引用即可。

- **格式**

```
引用链接定义：[标识符]: 地址 "标题,可省略"
如：[1]: http://www.google.com/ "显示标题，可省略"

文中引用：[说明/描述][标识符]
如: [google][1]
```

- **范例**

引用链接使用：[google][1]

[1]: http://www.google.com/	" 引用定义"

## 页内跳转

> 可以在当前页面内容进行跳转。
> 方式：定义锚点和锚点目标
> 锚点：跳转点
> 锚点目标：跳转的目标
>
> 有看到可以使用html设置锚点目标的，但是使用后没有生效。

### 锚点目标为标题

> 标题可以默认为一个锚点目标

```xml
锚点定义：
方式1：[描述/说明](#标题名)
方式2：[描述/说明]: #标题名
```

- **范例**
  [跳转到行内引用链接处](#行内链接)

  [跳转到行内引用链接处]: #行内链接

  

### 锚点目标为HTML标签属性

```
锚点目标为标签ID属性值/name属性值: <span id = "anchor">锚点</span>
注意：id/name属性值在页面内必须唯一，否则会导致跳转失败

锚点定义：
方式1：[描述/说明](#标签id/name属性值)
方式2：[描述/说明]: #标签id/name属性值
```

- **范例**

  **锚点定义：**

  ​	[id锚点目标跳转](#id锚点目标)

     [name锚点目标跳转](#name锚点目标)

  **锚点目标：**

  ​	<span id = "id锚点目标">id锚点目标</span>

  ​    <span name = "name锚点目标">name锚点目标</span>



# 图片

```
![alt 属性文本](图片地址)

![alt 属性文本](图片地址 "可选标题")

范例：
![RUNOOB 图标](http://static.runoob.com/images/runoob-logo.png)
```

Markdown 还没有办法指定图片的高度与宽度，如果你需要的话，你可以使用普通的 <img> 标签。

```
<img src="http://static.runoob.com/images/runoob-logo.png" width="50%">
```

# 表格

> Markdown 制作表格使用 **|** 来分隔不同的单元格，使用 **-** 来分隔表头和其他行

```
|  表头   | 表头  |
|  ----  | ----  |
| 单元格  | 单元格 |
| 单元格  | 单元格 |
```

**表格的对齐方式设置：**

- `-:`设置内容和标题栏居右对齐。
- `:-` 设置内容和标题栏居左对齐。
- `:-:` 设置内容和标题栏居中对齐

# 高级技巧

## 支持的html元素

> 不在 Markdown 涵盖范围之内的标签，都可以直接在文档里面用 HTML 撰写。
>
> 目前支持的 HTML 元素有：`<kbd> <b> <i> <em> <sup> <sub> <br>`等 ，如：

## 转义

Markdown 使用了很多特殊符号来表示特定的意义，如果需要显示特定的符号则需要使用转义字符，Markdown 使用反斜杠转义特殊字符：

```
**文本加粗** 
\*\* 正常显示星号 \*\*

Markdown 支持以下这些符号前面加上反斜杠来帮助插入普通的符号：

\   反斜线
`   反引号
*   星号
_   下划线
{}  花括号
[]  方括号
()  小括号
#   井字号
+   加号
-   减号
.   英文句点
!   感叹号
```

## 公式

**设置行内公式**：**文件→偏好设置→Markdown，勾选内联公式，重启typora**
输入$，按Esc键会自动在后面加上一个$，然后在这两个$之间输入公式。

当你需要在编辑器中插入数学公式时，可以使用两个美元符 $$ 包裹 TeX 或 LaTeX 格式的数学公式来实现。提交后，问答和文章页会根据需要加载 Mathjax 对数学公式进行渲染。如：数学公式有两种形式： inline 和 display

- **行间公式**(inline):用`$...$`将公式括起来。
- **块间公式**(displayed)，用`$$...$$`将公式括起来是无编号的形式，还有`\[...\]`的无编号独立公式形式但Markdown好像不支持。块间元素默认是居中显示的。

**上/下标**

| 算式   | Markdown |
| ------ | -------- |
| $x^2$  | x^2      |
| $y_1 $ | y_1      |

**分式**

| 算式              | Markdown    |
| ----------------- | ----------- |
| 1 / 2             | 1/2         |
| $$ \frac{1}{2} $$ | \frac{1}{2} |

**省略号**

| 省略号   | Markdown |
| -------- | -------- |
| $\cdots$ | \cdots   |

**开根号**

| 算式          | Markdown                                         |
| ------------- | ------------------------------------------------ |
| $\sqrt{2}$    | \sqrt{2}                                         |
| $\sqrt[3]{2}$ | \sqrt[3]{2}  <br />开方: \sqrt[开方数]{被开方数} |

**矢量**

| 算式       | Markdown |
| ---------- | -------- |
| $ \vec{a}$ | \vec{a}  |

**积分**

| 算式                | Markdown          |
| ------------------- | ----------------- |
| $\int{x}dx$         | \int{x}dx         |
| $\int_{1}^{2}{x}dx$ | \int_{1}^{2}{x}dx |

**极限**

| 算式                         | Markdown                   |
| ---------------------------- | -------------------------- |
| $\lim{a+b}$                  | \lim{a+b}                  |
| $\lim_{n\rightarrow+\infty}$ | \lim_{n\rightarrow+\infty} |

**累加**

| 算式                    | Markdown              |
| ----------------------- | --------------------- |
| $\sum{a}$               | \sum{a}               |
| $\sum_{n=1}^{100}{a_n}$ | \sum_{n=1}^{100}{a_n} |

**累乘**

| 算式                    | Markdown              |
| ----------------------- | --------------------- |
| $\prod{x}$              | \prod{x}              |
| $\prod_{n=1}^{99}{x_n}$ | \prod_{n=1}^{99}{x_n} |

**希腊字母**

| 大写       | Markdown | 小写          | Markdown    |
| ---------- | -------- | ------------- | ----------- |
| $A$        | A        | $$            | \alpha      |
| $B$        | B        | $$            | \beta       |
| $\Gamma$   | \Gamma   | $\gamma$      | \gamma      |
| $\Delta$   | \Delta   | $\delta$      | $$\delta    |
| $E$        | E        | $$            | \epsilon    |
|            |          | $\varepsilon$ | \varepsilon |
| $Z$        | Z        | $\zeta$       | \zeta       |
| $H$        | H        | $\eta$        | \eta        |
| $\Theta$   | \Theta   | $\theta$      | \theta      |
| $I$        | I        | $\iota$       | \iota       |
| $K$        | K        | $\kappa$      | \kappa$     |
| $\Lambda$  | \Lambda  | $\lambda$     | \lambda     |
| $M$        | M        | $\mu$         | \mu         |
| $N$        | N        | $\nu$         | \nu         |
| $\Xi$      | \Xi      | $\xi$         | \xi         |
| $O$        | O        | $\omicron$    | \omicron    |
| $\Pi$      | \Pi      | $\pi$         | \pi         |
| $P$        | P        | $\rho$        | \rho        |
| $\Sigma$   | \Sigma   | $\sigma$      | \sigma      |
| $T$        | T        | $\tau$        | \tau        |
| $\Upsilon$ | \Upsilon | $\upsilon$    | \upsilon    |
| $\Phi$     | \Phi     | $\phi$        | \phi        |
|            |          | $\varphi$     | \varphi     |
| $X$        | X        | $\chi$        | \chi        |
| $\Psi$     | \Psi     | $\psi$        | \psi        |
| $\Omega$   | \Omega   | $\omega$      | \omega      |

**三角函数**

| 三角函数 | Markdown |
| -------- | -------- |
| $\sin$   | \sin     |

**对数函数**

| 算式      | Markdown |
| --------- | -------- |
| $\ln2$    | \ln2     |
| $\log_28$ | \log_28  |
| $lg10$    | \lg10    |

**关系运算符**

| 运算符   | Markdown |
| -------- | -------- |
| $\pm$    | \pm      |
| $\times$ | \times   |
| $\cdot$  | \cdot    |
| $\div$   | \div     |
| $\neq$   | \neq     |
| $\equiv$ | \equiv   |
| $ \leq$  | \leq     |
| $\geq$   | \geq     |

**其它特殊字符**

| 符号          | Markdown   |
| ------------- | ---------- |
| $\forall$     | \forall    |
| $ \infty$     | \infty     |
| $\emptyset$   | \emptyset  |
| $\exists$     | \exists    |
| $ \nabla$     | \nabla     |
| $\bot$        | \bot       |
| $\angle$      | \angle     |
| $\because$    | \because   |
| $ \therefore$ | \therefore |

- **花括号**
  $\begin{cases} x+y+z=6① \\ 15x+7y+9z=60 ② \end{cases}$

  ```
  $\begin{cases} x+y+z=6① \\ 15x+7y+9z=60 ② \end{cases}$
  ```

- **空格**
  $a \quad b$

  ```
  a \quad b
  ```

- **矩阵**
  $$
  a = \left[
  \matrix{
    \alpha_1 & test1\\
    \alpha_2 & test2\\
    \alpha_3 & test3 
  }
  \right]
  $$
  

  ```
  $$
  a = \left[
  \matrix{
    \alpha_1 & test1\\
    \alpha_2 & test2\\
    \alpha_3 & test3 
  }
  \right]
  $$
  ```

  

## typora 画流程图、时序图(顺序图)、甘特图

**1. 横向流程图源码格式：**

```
​```mermaid
graph LR
A[方形] -->B(圆角)
    B --> C{条件a}
    C -->|a=1| D[结果1]
    C -->|a=2| E[结果2]
    F[横向流程图]
​```
```

**2. 竖向流程图源码格式：**

```
​```mermaid
graph TD
A[方形] --> B(圆角)
    B --> C{条件a}
    C --> |a=1| D[结果1]
    C --> |a=2| E[结果2]
    F[竖向流程图]
​```
```

**3. 标准流程图源码格式：**

```
​```flow
st=>start: 开始框
op=>operation: 处理框
cond=>condition: 判断框(是或否?)
sub1=>subroutine: 子流程
io=>inputoutput: 输入输出框
e=>end: 结束框
st->op->cond
cond(yes)->io->e
cond(no)->sub1(right)->op
​```
```

**4. 标准流程图源码格式（横向）：**

```
​```flow
st=>start: 开始框
op=>operation: 处理框
cond=>condition: 判断框(是或否?)
sub1=>subroutine: 子流程
io=>inputoutput: 输入输出框
e=>end: 结束框
st(right)->op(right)->cond
cond(yes)->io(bottom)->e
cond(no)->sub1(right)->op
​```
```

**5. UML时序图源码样例：**

```
​```sequence
对象A->对象B: 对象B你好吗?（请求）
Note right of 对象B: 对象B的描述
Note left of 对象A: 对象A的描述(提示)
对象B-->对象A: 我很好(响应)
对象A->对象B: 你真的好吗？
​```
```

**6、UML时序图源码复杂样例：**

```
​```sequence
Title: 标题：复杂使用
对象A->对象B: 对象B你好吗?（请求）
Note right of 对象B: 对象B的描述
Note left of 对象A: 对象A的描述(提示)
对象B-->对象A: 我很好(响应)
对象B->小三: 你好吗
小三-->>对象A: 对象B找我了
对象A->对象B: 你真的好吗？
Note over 小三,对象B: 我们是朋友
participant C
Note right of C: 没人陪我玩
​```
```

**7. UML标准时序图样例：**

```
​```mermaid
%% 时序图例子,-> 直线，-->虚线，->>实线箭头
  sequenceDiagram
    participant 张三
    participant 李四
    张三->王五: 王五你好吗？
    loop 健康检查
        王五->王五: 与疾病战斗
    end
    Note right of 王五: 合理 食物 <br/>看医生...
    李四-->>张三: 很好!
    王五->李四: 你怎么样?
    李四-->王五: 很好!
​```
```

**8. 甘特图样例：**

```
​```mermaid
%% 语法示例
        gantt
        dateFormat  YYYY-MM-DD
        title 软件开发甘特图
        section 设计
        需求                      :done,    des1, 2014-01-06,2014-01-08
        原型                      :active,  des2, 2014-01-09, 3d
        UI设计                     :         des3, after des2, 5d
    未来任务                     :         des4, after des3, 5d
        section 开发
        学习准备理解需求                      :crit, done, 2014-01-06,24h
        设计框架                             :crit, done, after des2, 2d
        开发                                 :crit, active, 3d
        未来任务                              :crit, 5d
        耍                                   :2d
        section 测试
        功能测试                              :active, a1, after des3, 3d
        压力测试                               :after a1  , 20h
        测试报告                               : 48h
​```
```

# 参考资料

1. [菜鸟教程](https://www.runoob.com/markdown/md-advance.html)
2. https://www.jianshu.com/p/4898c2e9a36d
3. https://blog.csdn.net/Small_Tsky/article/details/106799380?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param

使用Markdown编辑软件：Typora