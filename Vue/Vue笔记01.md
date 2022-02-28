[Toc]

# 前提

1. 学习版本：vue 2.0 API;其中会有部分与vue3的知识与对比
2. 开发工具：Visual Studio Code 8.6
3. [VUE2.0官方教程](https://cn.vuejs.org/v2/guide/)
4. [VUE3.0官方教程](https://v3.cn.vuejs.org/guide/introduction.html)
5. vue2.0&vue3.0:vue3.0向下兼容vue2.0

# 下载网址

- **CDN网址**

> - [Staticfile CDN（国内](https://cdn.staticfile.org/vue/2.2.2/vue.min.js) 
> - [unpkg](https://unpkg.com/vue@2.6.14/dist/vue.min.js)
> - [cdnjs]( https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js)
> - [JS Deliver](https://cdn.jsdelivr.net/npm/vue@next/dist/)

- **CDN**
  CDN的全称是Content Delivery Network，即内容分发网络。CDN是构建在现有网络基础之上的智能虚拟网络，依靠部署在各地的边缘服务器，通过中心平台的负载均衡、内容分发、调度等功能模块，使用户就近获取所需内容，降低网络拥塞，提高用户访问响应速度和命中率。CDN的关键技术主要有内容存储和分发技术。
- **脚手架**
  原解释：脚手架是为了保证各施工过程顺利进行而搭设的工作平台。
  编程中的脚手架：为了保证快速的开发功能，而搭建的架构，开发员在搭建好的架构上，无需关心底层架构，可以直接关注业务，直接运用已有架构的东西，完成业务功能的顺利实现。
- **渐进式框架**：随着需求的增多，VUE可以推出更多的组件以满足需求。

# Vue2.0安装方式

- **方式1**：直接使用`script`引入

  > 直接下载并用 `<script>` 标签引入，`Vue` 会被注册为一个全局变量。

  ```
  <!-- 开发环境版本，包含了有帮助的命令行警告 -->
  <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
  或者
  <!-- 生产环境版本，优化了尺寸和速度 -->
  <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
  
  ###下载vue.js到本地后，再引用
  <script src="../plugins/vue.min.js"></script>
  ```

- **方式2**：[NPM](https://cn.vuejs.org/v2/guide/installation.html#NPM)安装

  > 在用 Vue 构建大型应用时推荐使用 NPM 安装[[1\]](https://cn.vuejs.org/v2/guide/installation.html#footnote-1)。NPM 能很好地和诸如 [webpack](https://webpack.js.org/) 或 [Browserify](http://browserify.org/) 模块打包器配合使用。同时 Vue 也提供配套工具来开发[单文件组件](https://cn.vuejs.org/v2/guide/single-file-components.html)。

  ```
  # 最新稳定版
  $ npm install vue
  ```

- **方式3**：使用官方的 [CLI](https://v3.cn.vuejs.org/guide/installation.html#命令行工具-cli) 来构建一个项目

# Vue3.0 安装

- **目前有四种安装方式**：
  1. 在页面上以 [CDN 包](https://v3.cn.vuejs.org/guide/installation.html#cdn)的形式导入;
  2. 下载 JavaScript 文件并[自行托管](https://v3.cn.vuejs.org/guide/installation.html#下载并自托管);
  3. 使用 [npm](https://v3.cn.vuejs.org/guide/installation.html#npm) 安装;
  4. 使用官方的 [CLI](https://v3.cn.vuejs.org/guide/installation.html#命令行工具-cli) 来构建一个项目，它为现代前端工作流程提供了功能齐备的构建设置 (例如，热重载、保存时的提示等等)。

## 1. 以CDN包的形式导入

> 推荐链接到一个明确的版本号和构建文件，以避免新版本造成的不可预期的破坏

```
<script src="https://unpkg.com/vue@next"></script>

### 范例：
https://unpkg.com/browse/vue@3.2.31/dist/
```

## 2. 下载JS文件并自托管

- **建议应用场景**

> 1. 不想使用构建工具；
> 2. 无法在生产环境使用CDN.
>
> 符合以上场景时，可以直接下载所需`.js`文件，使用`script`在页面使用：
> 如：已下载`vue.min.js`文件，在`html`文件上使用
>
> ```
> <script src="../plugins/vue.min.js"></script>
> ```
>
> 下载网址，参见[下载网址](#下载网址)

- **下载注意事项**

> - 通常需要同时下载开发环境构建版本以及生产环境构建版本;
> - 不同文件说明，

## 3. 使用npm安装

> 在用 Vue 构建大型应用时推荐使用 npm 安装[[1\]](https://v3.cn.vuejs.org/guide/installation.html#footnote-1) 。npm 能很好地和诸如 [webpack](https://webpack.js.org/) 或 [Rollup](https://rollupjs.org/) 模块打包器配合使用。
> Vue 还提供了编写[单文件组件](https://v3.cn.vuejs.org/guide/single-file-component.html)的配套工具。如果你想使用单文件组件，那么你还需要安装 `@vue/compiler-sfc`

## 4. 使用官方的 [CLI](https://v3.cn.vuejs.org/guide/installation.html#命令行工具-cli) 来构建

> Vue 提供了一个[官方的 CLI](https://cli.vuejs.org/zh/)，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。只需要几分钟的时间就可以运行起来并带有热重载、保存时 lint 校验，以及生产环境可用的构建版本

# JQuery&Vue

> VUE&JQuery都可以对DOM进行操作，VUE封装了JQuery的关于Dom的操作，简化了JQUERY关于DOM的操作。

# vue拦截原理

## `{{}}`

> 在vue中，`{{}}`中的语句作为js语句执行，满足js语法

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../vue.js"></script>
</head>
<body>
    <div id="box">
        <!--JS表达式-->
        {{10>20?'aaa':'bbb'}}  <!--结果显示为 bbb-->

        {{myName}} <!--结果显示为data中定义的myName状态值-->
    </div>
    
    <div></div>
    <script>
    //vue变量为Vue实例变量，通过变量vue可以访问到所有定义在new Vue()中的状态
    var vue = new Vue({
        el:"#box",//element，绑定操作的DOM，在此DOM内所有操作满足vue语法
        data:{
            myName:"zhangsan",//定义一个【状态】
        }
    });
    </script>
</body>
</html>
```

- **在Chrome浏览器中手动改变DOM值**

  上述代码执行结果显示：
  ![image-20220224183645303](images/image-20220224183645303.png)

  - 在chrome检查的console页面，操作vue这个全局变量。可以发现，不需要查找DOM，通过改变定义在vue中的状态值，可以随之改变dom中的变量值。
    ![image-20220224183908779](images/image-20220224183908779.png)

- **产生以上结果的原理**:VUE里封装了DOM绑定&设置值的JS,其底层处理过程如下html中的js处理。只是VUE将该JS进行了封装，在使用的时候不需要再通过获取DOM，然后再进行操作。

  ```
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>Document</title>
  </head>
  <body>
      <div id="box"></div>
      <script>
      const obj = {};
      var vbox=document.getElementById("box")
      Object.defineProperty(obj,"myName",{
          get(){
              console.log("被访问了");
              return vbox.innerHTML;
          },
          set(value){
              console.log("被修改了:"+value);
              vbox.innerHTML=value;
          }
      })
      </script>
  </body>
  </html>
  ```

## 响应式原理

> 在v2的官方文档中，有详细说明VUE的响应式原理。
>
> 响应式：当vue数据模型data进行修改时，视图会进行相应的更新。
>
> **原理**：
>
> 当你把一个普通的 JavaScript 对象传入 Vue 实例作为 `data` 选项，Vue 将**遍历此对象所有**的 property，并使用 [`Object.defineProperty`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/defineProperty) 把这些 property 全部转为 [getter/setter](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Guide/Working_with_Objects#定义_getters_与_setters)。
> 每个组件实例都对应一个 **watcher** 实例，它会在组件渲染的过程中把“接触”过的数据 property 记录为依赖。之后当依赖项的 setter 触发时，会通知 watcher，从而使它关联的组件重新渲染。
>
> ![data](images/data.png)

## 检测变化注意事项

> 由于 JavaScript 的限制，Vue **不能检测**数组和对象的变化。vue中采取了一些规避方法
>
> Object.defineProperty有以下缺点：
> 1. 无法监听es6的Set,Map变化；
> 2. 无法监听Class类型的数据；
> 3. 属性的新加或者删除也无法监听；
> 4. 数组元素的增加和删除也无法监听
>
> **vue3 针对`Object.defineProperty`的缺点**，ES6 Proxy都能够完美得到解决，它唯一的缺点是，对IE不友好，所以vue3在检测到如果是使用IE的情况下(IE11都不支持Proxy),会自动降级为`Object.defineProperty`的数据监听系统

### 对于对象

Vue 无法检测 property 的添加或移除。由于 Vue 会在初始化实例时对 property 执行 getter/setter 转化，所以 property 必须在 `data` 对象上存在才能让 Vue 将它转换为响应式的。例如：

```
var vm = new Vue({
  data:{
    a:1
  }
})

// `vm.a` 是响应式的
vm.b = 2
// `vm.b` 是非响应式的
```

对于已经创建的实例，Vue 不允许动态添加根级别的响应式 property。但是，可以使用 `Vue.set(object, propertyName, value)` 方法向嵌套对象添加响应式 property。例如，对于：

```
Vue.set(vm.someObject, 'b', 2)
```

您还可以使用 `vm.$set` 实例方法，这也是全局 `Vue.set` 方法的别名：

```
this.$set(this.someObject,'b',2)
```

有时你可能需要为已有对象赋值多个新 property，比如使用 `Object.assign()` 或 `_.extend()`。但是，这样添加到对象上的新 property 不会触发更新。在这种情况下，你应该用原对象与要混合进去的对象的 property 一起创建一个新的对象。

```
// 代替 `Object.assign(this.someObject, { a: 1, b: 2 })`
this.someObject = Object.assign({}, this.someObject, { a: 1, b: 2 })
```

### 对于数组

> Vue 不能检测以下数组的变动
>
> 1. 当你利用索引直接设置一个数组项时，例如：`vm.items[indexOfItem] = newValue`
> 2. 当你修改数组的长度时，例如：`vm.items.length = newLength`
>
> 举个例子：
>
> ```
> var vm = new Vue({
>   data: {
>     items: ['a', 'b', 'c']
>   }
> })
> vm.items[1] = 'x' // 不是响应性的
> vm.items.length = 2 // 不是响应性的
> ```
>
> 为了解决第一类问题，以下两种方式都可以实现和 `vm.items[indexOfItem] = newValue` 相同的效果，同时也将在响应式系统内触发状态更新：
>
> ```
> // Vue.set
> Vue.set(vm.items, indexOfItem, newValue)
> // Array.prototype.splice
> vm.items.splice(indexOfItem, 1, newValue)
> ```
>
> 你也可以使用 [`vm.$set`](https://cn.vuejs.org/v2/api/#vm-set) 实例方法，该方法是全局方法 `Vue.set` 的一个别名：
>
> ```
> vm.$set(vm.items, indexOfItem, newValue)
> ```
>
> 为了解决第二类问题，你可以使用 `splice`：
>
> ```
> vm.items.splice(newLength)
> ```

## 声明响应式 property

由于 Vue 不允许动态添加根级响应式 property，所以你必须在初始化实例前声明所有根级响应式 property，哪怕只是一个空值：

```
var vm = new Vue({
  data: {
    // 声明 message 为一个空值字符串
    message: ''
  },
  template: '<div>{{ message }}</div>'
})
// 之后设置 `message`
vm.message = 'Hello!'
```

如果你未在 `data` 选项中声明 `message`，Vue 将警告你渲染函数正在试图访问不存在的 property。

这样的限制在背后是有其技术原因的，它消除了在依赖项跟踪系统中的一类边界情况，也使 Vue 实例能更好地配合类型检查系统工作。但与此同时在代码可维护性方面也有一点重要的考虑：`data` 对象就像组件状态的结构 (schema)。提前声明所有的响应式 property，可以让组件代码在未来修改或给其他开发人员阅读时更易于理解。

# vue 模板语法

- **指令**：带有`v-`前缀的特殊属性

  ```
  v-bind	动态绑定属性
  v-if	动态创建/删除
  v-show	动态显示/隐藏
  v-on:click		绑定事件
  v-for	遍历
  v-model	双向绑定表单;v-model 指令在表单 <input>、<textarea> 及 <select> 元素上创建双向数据绑定
  ```

- **缩写**

  ```
  v-bind:src  => :src
  v-on:click  => @click
  ```

- **范例**

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../vue.js"></script>
    <style>
    .myred{
        background-color: red
    }
    .myblue{
        background-color: blue
    }

    .hidden{
        display: none;
    }
    </style>
</head>
<body>
    <div id="box">
         <!-- vue实例下DOM中变量操作============================================================================================-->
        <!--在vue绑定的dom元素下的{{}}中，适用所有JS语法操作-->
        {{myName}} <!--结果显示为data中定义的myName状态值-->
        {{10>20?'aaa':'bbb'}}

         <!-- 事件绑定============================================================================================-->
        <button @click="handleClick">click</button><!--绑定点击事件，@ 为必需符号，简略写法-->
        <button v-on:click="handleClick">click</button><!-- 绑定事件完整写法 -->

         <!-- 属性操作============================================================================================-->
        <!-- :attributeName 写法等价于v-bind:attributeName -->
        <div :class="whichColor">动态切换class</div> <!--速写方式：引用属性变量语法  :attitudeName  这种方式绑定，才会将属性中的值解析为变量，然后被vue渲染-->
        <div v-bind:class="whichColor">动态切换class</div><!-- 绑定属性完整写法 -->
        <!--<img :src="" />实现动态绑定图片路径-->

        <!-- 在属性绑定的属性变量定义中，可以使用所有JS语法 -->
        <div :class="isHidden?'hidden':''">动态显示和隐藏-class</div>

        <!-- 指令============================================================================================-->

        <!--使用vue中定义的指令实现div动态显示和隐藏，可以通过操作定义的isShow变量的值，控制div的显示和隐藏-->
        <div v-show="isShow">动态显示和隐藏-指令</div>
        <!--删除的时候会删除节点，而不是隐藏:if为条件，当达到条件创建，否则删除节点-->
        <div v-if="isCreated">动态创建和删除-指令</div>

        <!--操作数组：v-for指令-->
        <!-- 遍历数组，显示数组元素 item为定义的临时变量-->
        <ul>
            <li v-for="item in dataList">{{item}}</li>
        </ul>
        <!-- 遍历数组，并显示数组元素和索引-->
        <ul>
            <li v-for="(item,index) in dataList">{{item}}---{{index}}</li>
        </ul>
    </div>
    
    <script>
    //vue变量为Vue实例变量
    new Vue({
        el:"#box",//element，绑定操作的DOM，在此DOM内所有操作满足vue语法
        data:{//定义状态
            myName:"zhangsan",
            whichColor:"myred",
           // whichsrc:"",//状态可以为空字符串
           isHidden:false,
           isShow:true,
           isCreated:true,
           dataList:["111","222","333"]
        },
        methods:{
            handleClick:function(){
                this.myName="liming";//this执行当前vue实例，点击后改变myName的值
                this.whichColor="myblue";//点击按钮后改变div的背景色
                //this.whichsrc="./demo.jpg" //点击按钮后导入图片
                this.isHidden=!this.isHidden;//点击按钮后，隐藏&显示div切换
                this.isShow=!this.isShow; //点击按钮后，隐藏&显示div切换
                this.isCreated=!this.isCreated;//点击按钮后，创建/删除div
            }
        }
    });
    </script>
</body>
</html>
```



# class&style

# 条件渲染

# 列表渲染

# 事件处理器

# 表单控件绑定

# 计算属性



# 参考资料

1. https://www.bilibili.com/video/av254249324?p=4&spm_id_from=pageDriver
2. https://cn.vuejs.org/v2/guide/reactivity.html
3. https://v3.cn.vuejs.org/guide/reactivity-fundamentals.html#%E5%88%9B%E5%BB%BA%E7%8B%AC%E7%AB%8B%E7%9A%84%E5%93%8D%E5%BA%94%E5%BC%8F%E5%80%BC%E4%BD%9C%E4%B8%BA-refs