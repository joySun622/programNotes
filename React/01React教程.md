[Toc]

# 环境说明

1. 前端开发工具:VS-CODE
2. NODEJS版本：v18.8.0

# React简介

轻量级的视图层库。A JavaScript library for building user interfaces 

![image-20220901111408431](images/image-20220901111408431.png)

## 特性

1．声明式设计—React采用声明范式，可以轻松描述应用。

2．高效—React通过对DOM的模拟（虚拟dom），最大限度地减少与DOM的交互。

3．灵活 —React可以与已知的库或框架很好地配合。

4．JSX - JSX 是 JavaScript 语法的扩展。

5．组件—通过React构建组件，使得代码更加容易得到复用，能够很好的应用在大项目的开发中。

6．单向响应的数据流—React实现了单向响应的数据流，从而减少了重复代码，这也是它为什么比传统数据绑定更简单。



# 安装脚手架`create-react-app`&创建项目

1. 确保你安装了较新版本的 [Node.js](https://nodejs.org/en/)。
2. 安装react脚手架

## 1. 安装 create-react-app

```
### 全局安装:可以在任意地方使用create-react-app创建项目，缺点是如果需要更新版本，需要重新全局安装
D:\huadi_workspace\zj-view-workspace\zj-td-view> npm install -g create-react-app

### 临时安装：安装的是当前最新版本的create-react-app；使用以下命令会同时创建项目your-app
D:\huadi_workspace\zj-view-workspace\zj-td-view> npx create-react-app your-app

### 查看react版本
D:\huadi_workspace\zj-view-workspace\zj-td-view> create-react-app -V
5.0.1
```

## 2. 创建一个项目

```
D:\huadi_workspace\zj-view-workspace\zj-td-view> create-react-app my-app   注意命名方式
```

这需要等待一段时间，这个过程实际上会安装三个东西

- react：react的顶级库

- react-dom：因为react有很多的运行环境，比如app端的react-native，我们要在web上运行就使用react-dom 
- react—scripts：包含运行和打包react应用程序的所有脚本及配置



- **若出现以下信息表示创建成功**

![image-20220901120725999](images/image-20220901120725999.png)

## 3. 启动项目

> 进入创建的项目目录下，使用`npm start`启动项目，启动成功后会自动打开浏览器

```
D:\huadi_workspace\zj-view-workspace\zj-td-view\my-app> npm start
```

<img src="images/image-20220901121045677.png" alt="image-20220901121045677" style="zoom:33%;" />

## 4. 基础项目目录结构

```
|——README.md   使用方法的文档
|——node_modules	所有的依赖安装的目录
|——package-1ock.json   锁定安装时的包的版本号，保证团队的依赖能保证一致。
|——package.json  项目所需要的各种模块，以及项目的配置信息（比如名称、版本、许可证等元数据）。npm install命令根据这个配置文					 件，自动下载所需的模块，也就是配置项目所需的运行和开发环境。
|——public   静态公共目录
|——src   开发用的源代码目录
```

## 5. 常见问题

- **npm安装失败** 

> - 切换为npm镜像为淘宝镜像
>
> - 使用yarn，如果本来使用yarn还要失败，还得把yarn的源切换到国内
>
> - 如果还没有办法解决，请删除`node＿modules`及package-lock.json然后重新执行`npm install`命令 。再不能解决就删除`node＿modules`及`package-lock.json`的同时清除npm缓存`npm cache clean--force `之后再执行`npm install`命令

- **安装`nrm`镜像管理工具**

> `nrm`专门管理镜像的一个工具
>
> - **安装**
>
> ```
> npm -i nrm
> 
> ### 查看nrm版本
> nrm -V
> ### 查看当前所有镜像
> nrm -ls
> ### 使用指定镜像
> nrm use taobao  指定使用taobao镜像
> ```

## 6. 开发辅助插件

### `ES7 React/Redux/GraphQL/React-Native snippets`

> **自动提示React错误信息插件**

- **快捷方式**

> 1. 快速创建类组件,在js文档中输入`rcc`

# 编写第一个React应用

1. 创建新项目`create-react-app my-app`
2. 删除掉新项目中 `src/` 文件夹下的所有文件。**不要删除整个 `src` 文件夹，删除里面的源文件**。
3. 在`src`目录下创建入口文件——`index.js`

>  react开发需要引入多个依赖文件：react.js、react-dom.js,分别又有开发版本和生产版本，create-react-app里 已经帮我们把这些东西都安装好了。把通过CRA创建的工程目录下的src目录清空，然后在里面重新创建一个 index.js.写入以下代码：
>
> ```
> //从react的包当中引AJ/ React。只要你要写React, js组件就必须引入React,因为react里有一种语法叫 jsx,稍后会讲到jsx,要写jsx,就必须引入React
> import React from  'react'
> // ReactDOM可以帮助我们把React组件洎染到页面上去，没有其它的作用了。它是从react-dom中引入的，而 不是从react引入。
> import ReactDOM from 'react-dom'
> // ReactDOM里有一个render方法，功能就是把组件渲染并且构造DOM树，然后插入到页面上某个特定的元素上 
> ReactDOM.render(
> //这里就比较奇怪了，它并不是一个字符串，看起来像是纯HTML代码写在Javascript代码里面。语法错误吗？这 并不是合法的JavaScript代码，
> //"在JavaScript写的标签的”语法叫JSX- JavaScript XML。
> <hl>欢迎进入React的世界</hl>,
> //渲染到哪里
> document.getElementById('root')
> )
> ```
>
> 上述写法原始React写法为
>
> ```
> ReactDOM.render(React.createElement("div",{
>     id:"aaa",
>     class:"ddd"
> } ,"111111111"),document. getElementById("root"))
> ```



# JSX语法

- **简介**

> jsx <=> js + xml
>
> 所谓的JSX其实就是JavaScript对象，所以使用React和JSX的时候一定要经过编译的过程：
>  JSX —>使用react构造组件，bable进行编译 一＞ JavaScript对象 一>`ReactDOM. render()`—＞DOM元素一＞
>  插入页面 

> 1. JSX在组件html标签中，`{}`中可进行逻辑运算以及使用表达式；

## 组件的创建

> 1. 创建组件需继承`React.Component`类，否则为普通类，无法自动渲染组件，使定义的组件可以以标签形式`<myApp></myApp> 或 <myApp/>`使用；
> 2. 组件导入时，定义的组件名首字母必须大写，否则浏览器会报错，提示无法识别为React组件；
> 3. 组件`render()`返回的HTML，必须有一个父标签，包裹其他html标签，否则会报错；
> 4. `return`回车换行返回数据时，应使用以下格式,在`return `后加`()`,`()`中可以随意使用回车换行，若不使用括号，在`return`后使用回车换行会返回`undefined`
>
>    ```
> render(){ 
>       return (
>         <div>Hello React Component</div>
>       )
>     }
>    ```
>
> 

### 类(Class)组件

> ES6的加入让JavaScript直接支持使用class来定义一个类，react创建组件的方式就是使用的类的继承，ES6 class是目前官方推荐的使用方式，它使用了ES6标准语法来构建。  `class`组件其实就是一个构造器，每次使用组件都相当于在实例化组件。

1. **定义一个类(Class)组件**

```
import React from "react";
// App 类名可自定义
class App extends React.Component{
  render(){  //render 函数为固定函数
      return <div>Hello React Component</div>
  }
}

export default App  //导出类
```

2. **使用定义的类组件**:在`index.js`使用自定义的类组件

```
import React from  'react'
import ReactDOM from 'react-dom'
//引入组件js；App组件名可以随意定义，不需和组件名定义一致，一致的定义只是为了和谐统一
import App from "./0-base/1-class组件"

ReactDOM.render(
<App></App>,
document.getElementById('root')
)
```

### 函数组件

> - 在React 16.8版本之前，函数式组件为无状态组件；在16.8之后，引入了React Hooks组件，函数组件变为有状态组件

1. **函数式组件定义**

```
function App(){
    return (
        <div>function  Component</div>
    )
}
// 在React 16.8版本之前，函数式组件为无状态组件；在16.8之后，引入了React Hooks组件，函数组件变为有状态组件
export default App
```

2. **函数式组件应用**

```
import React from  'react'
import ReactDOM from 'react-dom'
//引入组件js；App组件名可以随意定义，不需和组件名定义一致，一致的定义只是为了和谐统一
import App from "./0-base/1-class组件"

ReactDOM.render(
<App></App>,
document.getElementById('root')
)
```

### 组件嵌套

> 1. 组件的嵌套，分清组件的父子关系，若有祖孙情况,一般子组件都放在父组件里，而不是在祖辈组件里定义；

- **嵌套组件定义**

> - 父组件:`APP`
> - 子组件：`Navbar,Swiper,Toorbar`

```
import React, { Component } from 'react'

class Navbar extends Component{
    render() {
      return <div>Navbar</div>
    }
}

function Swiper(){
    return <div>Swiper</div>
}

// ES6函数写法
const Toorbar = ()=>{
    return <div>Toorbar</div> 
}
// ES6函数写法
const Toorbar2 = ()=><div>Toorbar2</div> 

export default class App extends Component {
  render() {
    return (
      <div>
        <Navbar></Navbar>
        <Swiper></Swiper>
        <Toorbar></Toorbar>
        <Toorbar2></Toorbar2>
      </div>
    )
  }
}

```

### 组件的样式

> 1. `css`样式属性名，若有`-`需改成驼峰写法；
> 2. `css`样式定义中，若碰上与`js`关键字重合的，最好改写成对应的名称
>    `class => className  for =>htmlFor`
>
> 3. 在组件的html标签上直接引用变量或者进行逻辑运算，需使用`{}`;否则会被解析为字符串；
> 4. 直接在行内定义样式时，需使用`{{}} 如：<p style={{color:'red',fontSize:'14px'}}>Hello World</p>`；第一个`{}`表示要在JSX里插入JS了，第二个`{}`表示为对象;
> 5. 注释写法`return({/* 多行注释，单行注释，都需使用在{}中，表示为JS注释*/})`;VSCODE快捷键`Ctrl+C`

####   行内样式

> - **直接引用**
>
> 想给虚拟dom添加行内样式，需要使用表达式传入样式对象的方式来实现：
>
> ```
>  //注意这里的两个括号，第一个表示我们在要JSX里插入JS了，第二个是对象的括号
> <p style={{color:'red',fontSize:'14px'}}>Hello World</p>
> ```
>
> 行内样式需要写入一个样式对象，而这个样式对象的位置可以放在很多地方，例如render函数里、组件原型上、 外链js文件中
>
> - **先定义表达式的写法**
>
> ```
> //定义样式表达式
> var obj={
>       backgroundColor:"yellow", //此处 不能使用backgroun-color,需改成驼峰写法
>       fontSize:"30px"
>     }
>     
> //行业引用样式
> <div style={obj}>{10+20} + "哈哈哈"  </div>
> ```

####   使用class

>  **React推荐我们使用行内样式，因为React觉得每一个组件都是一个独立的整体**
>  其实我们大多数情况下还是大量的在为元素添加类名，但是需要注意的是，class需要写成className (因为毕 竟是在写类js代码，会受到js规则的限制，而`class`是关键字)
>
> ```
>  <p className="hello">Hello world</p>
> ```
>
> 注意：
>    class ==> className, for ==> htmlFor(label)

- **范例**

```
import React, { Component } from 'react'
import './css/0-index.css' //导入css模块，这种导入方式是基于webpack的支持；webpack会自动将样式以内部样式<style></style>的方式，引入到页面中

export default class APP extends Component {
  render() {
    var name="aaa"
    //在{} 可进行逻辑运算，以及使用表达式
    var obj={
      backgroundColor:"yellow", //此处 不能使用backgroun-color,需改成驼峰写法
      fontSize:"30px"
    }
    return (
      <div>
        <div style={obj}>{10+20} + {name}   </div>
        <br/>
        <div style={{backgroundColor:"red"}}>{10>20?"aaa":"bbb"}</div>

        <div className='active'>Haha</div>

         {/* 使用for使点击用户名后，自动聚焦输入框：需使用htmlFor，避免和js关键字冲突 */}
        <label htmlFor='usename'>用户名</label>
        <input type="text" id='usename'/>
      </div>
    )
  }
}

```

- **定义`0-index.css`文件**

```
.active {
    background-color: blue;
}
```

## 事件处理

### **事件函数定义方式**

```
import React, { Component } from 'react'

export default class App extends Component {
  render() {
    return (
      <div>
        {/*事件绑定写法1:直接在html标签上定义函数*/}
        <input/>
        <button onClick={()=>{
            console.log("click1；如果处理内容过多，不适合");
        }}>Add1</button>
         {/*事件绑定写法2:在组件类中定义对应的函数，然后再绑定在标签上。
         注意：此处引用函数，不需加(),否则会导致组件在一被初始化就调用该函数，而不是点击后调用该函数 会涉及到this指向问题*/}
        <button onClick={this.handleClick2}>Add2</button>

         {/*事件绑定写法3:使用ES6箭头函数定义click操作对应函数；会涉及到this指向问题*/}
        <button onClick={this.handleClick3}>Add3</button>

         {/*事件绑定写法4:使用ES6箭头函数,在箭头函数里调用指定的click函数*/}
         <button onClick={()=>{
             this.handleClick4();//当逻辑比较复杂，推荐使用
         }}>Add4</button>
      </div>
    )
  }

  handleClick2(){
      console.log("click2");
  }

  handleClick3  = ()=>{
      console.log("click3");
  }

  handleClick4  = ()=>{
    console.log("click4");
}
}

```

### `this`指向问题

> 在js中，`this`是谁调用指向谁，但有三种方式改变`this`执行
>
> - `call`:改变`this`指向，自动执行函数
> - `apply`:改变`this`指向，自动执行函数
> - `bind`:改变`this`指向，手动执行函数

- **范例**

```
var obj1 = {
  name:"obj1",
  getName(){
    console.log(this.name);
  }
}

var obj2 = {
  name:"obj2",
  getName(){
    console.log(this.name);
  }
}
//obj1.getName();

obj1.getName.call(obj2);  //打印结果:obj2 ;将对象obj1的this指向改为obj2，会自动执行函数getName
obj1.getName.apply(obj2); //打印结果:obj2 ； 将对象obj1的this指向改为obj2，会自动执行函数getName
obj1.getName.bind(obj2)();//打印结果:obj2,将对象obj1的this指向改为obj2，需加(),才会执行函数getName()
```

- **范例**

```
import React, { Component } from 'react'

export default class App extends Component {
  a = 100;
  render() {
    return (
      <div>
        {/*在此处this指向的是当前类的实例*/}
        <input/>
        <button onClick={()=>{
            console.log("click1；如果处理内容过多，不适合==="+this.a);
        }}>Add1</button>
         {/*使用bind改变this指向,此处this执行App实例；不推荐该函数写法，需要手动改变this指向*/}
        <button onClick={this.handleClick2.bind(this)}>Add2</button>

         {/*事件绑定写法3:使用ES6箭头函数定义click操作对应函数；比较推荐该种写法*/}
        <button onClick={this.handleClick3}>Add3</button>

         {/*比较推荐，传参的时候比较适用*/}
         <button onClick={()=>{
             this.handleClick4();
         }}>Add4</button>
      </div>
    )
  }

  handleClick2(){
      console.log("click2"+this.a);//若不使用bind改变this指向，此时this为undefined会报错
  }

  handleClick3  = (evt)=>{
      console.log("click3"+this.a);//在箭头函数中，this始终指向最外层调用对象；无法使用函数如call等改变this执行
      console.log(evt.target);
  }

  handleClick4  = ()=>{
    console.log("click4"+this.a);
}

}
```

### Reac&原生事件区别

> 采用on+事件名的方式来绑定一个事件，注意，这里和原生的事件是有区别的，原生的事件全是小写。`onclick`, React里的事件是驼峰`onClick`, **React的事件并不是原生事件，而是合成事件**。
>
> React并不会真正的绑定事件到每一个具体的元素上，而是采用事件代理的模式,将事件绑定在根节点`root`上。有点类似冒泡的算法。
> ![image-20220905130938177](images/image-20220905130938177.png)

####  事件handler的写法

 • 直接在render里写行内的箭头函数（不推荐）
 • 在组件内使用箭头函数定义一个方法（推荐）
 • 直接在组件内定义一个非箭头函数的方法，然后在render里直接使用。`onClick= {this, handleclick, bind （this）}`（不推荐）
 • 直接在组件内定义一个非箭头函数的方法，然后在constructor里`bind(this)（推荐）`

#### Event 对象

和普通浏览器一样，事件handler会被自动传入一个event对象，这个对象和普通的浏览器`event`对象所包含的 方法和属性都基本一致。不同的是React中的`event`对象并不是浏览器提供的，而是它自己内部所构建的。它同 样具有`event.stopPropagation、event.preventDefault` 这种常用的方法

## Ref的应用

- **给标签设置`ref="username"`**
        通过这个获取`this.refs.username`, `ref`何以获取到应用的真实dom
- **给组件设置 `ref="username"`**
  通过这个获取`this.refs.username` ,`ref`何以获取到组件对象

- **新的写法**

```
 myRef = React.c reateRef()
 <div ref={this.myRef}>hel1o</div>
  访问 this. myRef. current
```

- **范例**

```
import React, { Component } from 'react'

export default class App extends Component {
  a = 100;
  myRef=React.createRef();
  render() {
    return (
      <div>
        {/*refs将过期写法*/}
        <input ref="myText"/>
        <button onClick={()=>{
            {/**此处获取的是input dom节点 */}
            console.log("click1==="+this.refs.myText);
             {/**此处获取的是input dom节点值 */}
             console.log("click1==="+this.refs.myText.value);
        }}>Add1</button>
        <br/>
        {/*新的写法*/}
        <input ref={this.myRef}/>
        <button onClick={()=>{
            {/**此处获取当前input dom节点 */}
            console.log("click2==="+this.myRef.current);
             {/**此处获取的是input dom节点值 */}
             console.log("click2==="+this.myRef.current.value);
        }}>Add2</button>
      </div>
    )
  }
}
```

## 组件的数据挂载方式

### 状态

> 状态就是组件描述某种显示情况的数据，由组件自己设置和更改，也就是说由组件自己维护，使用状态的目的就是为了在不同的状态下使组件的显示不同(自己管理)

#### 状态定义方式1

> `this.state`是纯js对象，在vue中，data属性是利用`object.defineProperty`处理过的，更改data的数据的时 候会触发数据的`getter`和`setter`,但是React中没有做这样的处理，如果直接更改的话，`react`是无法得知的， 所以，需要使用特殊的更改状态的方法`setState`
>
> `myShow`存放在实例的`state`对象当中，组件的`render`函数内，会根据组件的`state`的中的`myShow`不同显 示‘取消"或'收藏”内容。下面给`button`加上了点击的事件监听。
>
> - `setState`函数里的对象，其实是与类组件定义的属性对象`state`进行合并，当其中有属性值进行修改时，则替换，没有则保持原样

```
import React, { Component } from 'react'

export default class APP extends Component {
    // state 名称是固定写法，不能更改
  state={
      text: "收藏",
      myShow:true
  }
  render() {
    return (
      <div>
        <h1>欢迎欢迎</h1>
        <button onClick={()=>{
            //this.state.text="取消收藏"  直接修改text值会报错
            this.setState({//间接修改text值
                myShow:!this.state.myShow //myShow取反
            })
        }}>
            {this.state.myShow?this.state.text:"取消收藏"}
        </button>
      </div>
    )
  }
}

```

#### 状态定义方式2

```
import React, { Component } from 'react'

export default class APP extends Component {
    // state 名称是固定写法，不能更改
//   state={
//       text: "收藏",
//       myShow:true
//   }
 constructor(){
     super() //使用构造器定义state，必须加上该函数，继承Component属性
     this.state={
        text: "收藏",
        myShow:true,
        name:"Joy"
     }
 }
  render() {
    return (
      <div>
        <h1>欢迎欢迎</h1>
        <button onClick={()=>{
            //this.state.text="取消收藏"  直接修改text值会报错
            this.setState({//间接修改text值
                myShow:!this.state.myShow,
                name:"张三"
            })
        }}>
            {this.state.myShow?this.state.text:"取消收藏"}
        </button>
      </div>
    )
  }
}

```

### 列表渲染

```
import React, { Component } from 'react'

export default class APP extends Component {

  state = {
      list:["1111","2222","3333"]
  }
  render() {
    return (
      <div>
        <ul>
            {
                //使用原生js map循环实现列表渲染;列表渲染，最好设置key值，作为虚拟dom的唯一标识
                //1.在数据更新的时候避免数据婚礼,便于列表的复用和重排;2.提交性能：虚拟dom节点可以直接根据key值进行比对，不需重复循环比对
                //不涉及列表的删除，增加，key设置成索引没有问题
                this.state.list.map(item=>
                <li key={item}>{item}</li>
                )
            }
        </ul>
      </div>
    )
  }
}

```

#### 案例todolist



# VS code react插件快捷键

# 参考资料来源

1. https://www.bilibili.com/video/BV1dP4y1c7qd?spm_id_from=333.337.search-card.all.click&vd_source=fac4be3bb979a84f531a60420866c84a
2. https://blog.csdn.net/weixin_41016986/article/details/125762439