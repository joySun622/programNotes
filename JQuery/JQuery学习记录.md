[Toc]

# js ,css,html执行顺序

1. **正常网页加载的顺序**
   1) 浏览器一边下载html网页,一边开始解析;

   2) 解析过程中,若发现script标签,暂停解析,网页渲染的控制权交给js引擎;

   3) 如果scirpt引用了外部脚本,就下载该脚本,否则直接执行;

   4) 执行完毕,控制权交还给js渲染引擎.恢复往下解析html网页。

2. **HTML 加载顺序**
   1）在html中 body部分中的js会在页面加载的时候被执行；

   2）在html中head中的js会预先进行加载,反而会在页面需要调用的时候才会执行代码,所以可以将预先不执行的代码放在head中；

   3）需调用先执行的时候把js放在head中,在head页面载入的时候,就同时载入了代码,在body中调用的时候就不用载入代码,速度会提高；

   4）如果head中的js代码需要传入一个参数,需要body中的参数,那么这时候就会报错,因为此时页面的DOM树还未生成；

   5）从js对页面下载性能方向考虑. 由于脚本会阻塞其他资源的下载,如图片和页面渲染,直到脚本全部被下载并执行完毕后,页面的渲染才会继续,因此推荐将所有的script标签放到底部.

# 文档就绪函数

> - `JQuery(callback)`允许你绑定一个在DOM文档载入完成后执行的函数。这个函数的作用如同$(document).ready()一样，只不过用这个函数时，需要把页面中所有需要在 DOM 加载完成时执行的$()操作符都包装到其中来。从技术上来说，这个函数是可链接的－－但真正以这种方式链接的情况并不多。 你可以在一个页面中使用任意多个$(document).ready事件。参考 ready(Function) 获取更多 ready 事件的信息。
>
>   ```
>   ### 写法1:JQuery(callback) 当DOM加载完成后，执行其中的函数
>   $(function(){
>     // 文档就绪
>   });
>   ### 写法2：使用 $(document).ready() 的简写，同时内部的 jQuery 代码依然使用 $ 作为别名，而不管全局的 $ 为何。
>   jQuery(function($) {
>     // 你可以在这里继续使用$作为别名...
>   });
>   ```
>
> - `ready(fn)`当DOM载入就绪可以查询及操纵时绑定一个要执行的函数。
>   简单地说，这个方法纯粹是对向window.load事件注册事件的替代方法。通过使用这个方法，可以在DOM载入就绪能够读取并操纵时立即调用你所绑定的函数，而99.99%的JavaScript函数都需要在那一刻执行。
>
>   有一个参数－－对jQuery函数的引用－－会传递到这个ready事件处理函数中。可以给这个参数任意起一个名字，并因此可以不再担心命名冲突而放心地使用$别名。
>
>   请确保在 <body> 元素的onload事件中没有注册函数，否则不会触发+$(document).ready()事件。
>
>   可以在同一个页面中无限次地使用$(document).ready()事件。其中注册的函数会按照（代码中的）先后顺序依次执行
>
>   ```
>   ### 写法1：在DOM加载完成时运行的代码
>   $(document).ready(function(){
>     // 在这里写你的代码...
>   })
>   
>   ### 写法2：使用 $(document).ready() 的简写，同时内部的 jQuery 代码依然使用 $ 作为别名，而不管全局的 $ 为何。
>   $(function($) {
>     // 你可以在这里继续使用$作为别名...
>   });
>   ```

# 闭包

> 闭包是一种保护私有变量的机制，在函数执行时形成私有的作用域，保护里面的私有变量不受外界干扰。
>
> 直观的说就是形成一个不销毁的栈环境。

- **经典闭包函数**

```
var add = (function () {
    var counter = 0;
    return function () {return counter += 1;}
})();
add();
add();
add(); //此时countor为3

### 解析
变量 add 指定了函数自我调用的返回字值。
自我调用函数只执行一次。初始化counter变量为 0。并返回函数表达式。
add变量可以作为一个函数使用。非常棒的部分是它可以访问函数上一层作用域的计数器。
这个叫作 JavaScript 闭包。它使得函数拥有私有变量变成可能。
计数器受匿名函数的作用域保护，只能通过 add 方法修改。
```

- **自调用函数(自执行函数)**

  > 自调用函数：为一次性调用函数，在页面加载该js代码的时候，自动执行。**只会执行一次**

```
函数表达式可以 "自调用"。
自调用表达式会自动调用。
如果表达式后面紧跟 () ，则会自动调用。
不能自调用声明的函数。
(function () {
    var x = "Hello!!";      // 我将调用自己
})();

### 定义一些需要预先定义好的函数.立即执行函数；相当于先申明一个函数，声明完后直接调用
(function($){})为匿名函数，$为形参，形参使用$,是为了不与其他库冲突；jQuery 为实参

;(function($){
// $为形参
})(jQuery);

函数前的分号 ; 是为了与其他操作分离而添加的，以防其他语句影响，所以用;隔离。该函数执行过程类似
var fn=function($){函数体}; //注意fn是不存在的
fn(jQuery);//传入jQuery作为实参，执行
```

# 扩充 jQuery 对象

## jQuery.extend(*[deep]*, target, object1, *[objectN]*)

> 如果不指定target，则给jQuery命名空间本身进行扩展。这有助于插件作者为jQuery增加新方法。 如果第一个参数设置为true，则jQuery返回一个深层次的副本，递归地复制找到的任何对象。否则的话，副本会与原对象共享结构。 未定义的属性将不会被复制，然而从对象的原型继承的属性将会被复制。
>
> **参数**
>
> ```
> **target,[object1],[objectN]**
> 
> **target**:一个对象，如果附加的对象被传递给这个方法将那么它将接收新的属性，如果它是唯一的参数将扩展jQuery的命名空间。
> **object1**:待合并到第一个对象的对象。
> **objectN**:待合并到第一个对象的对象。
> 
> **[deep],target,object1,[objectN]**
> **deep**:如果设为true，则递归合并。
> **target**:待修改对象。
> **object1**:待合并到第一个对象的对象。
> **objectN**:待合并到第一个对象的对象。
> ```

## jQuery.fn.extend(object)

> 扩展 jQuery 元素集来提供新的方法（通常用来制作插件）。
> `object`:用来扩充 jQuery 对象。
>
> ```
> 增加两个插件方法。
> jQuery 代码:
> jQuery.fn.extend({
>   check: function() {
>     return this.each(function() { this.checked = true; });
>   },
>   uncheck: function() {
>     return this.each(function() { this.checked = false; });
>   }
> });
> 
> 结果:
> $("input[type=checkbox]").check();
> $("input[type=radio]").uncheck();
> ```

## jQuery.fn

```
jQuery.fn = jQuery.prototype = {
　　　init: function( selector, context ) {//....　
　　　//......
};

jQuery.fn：为jQuery原型对象的赋值
为jQuery原形对象添加新元素：
jQuery.fn.testDemo=function(){}
```



# 参考资料

1. https://blog.csdn.net/mocas_wang/article/details/108290609
2. https://www.jb51.net/shouce/jquery1.82/
3. https://www.w3school.com.cn/jquery/jquery_events.asp
4. https://api.jquery.com/jQuery.ready/
5. https://www.runoob.com/js/js-function-closures.html

