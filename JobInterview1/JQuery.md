[toc]

### $ 的原理？

1. $ (“选择器”) 是先查找DOM元素，再将DOM元素放入jQuery对象中
   其中自带优化:
   如果选择器是#id，则自动调用getElementById
   如果选择器是.class，则自动调用getElementsByClassName
   如果选择器是标签名，则自动调用getElementsByTagName
   否则，其它选择器，都自动调用querySelectorAll()
2. $ (DOM元素) 是直接将DOM元素放入jQuery对象中
3. $ (“HTML片段”) 是创建一个新元素
4. $(function(){}) 是绑定事件，在DOM内容加载后就提前触发。

### 实现动画有几种方式，哪种好？

1. CSS: transition, animateion
   优点: 由专门的排版引擎解析，效率高
   缺点: 无法随意控制交互行为
2. JS: 定时器， $().animate()
   优点: 可随意控制交互行为
   缺点: 效率不如css动画
3. requestAnimationFrame()
   优点: 可根据浏览器的刷新频率自动优化动画效果
   缺点: 新API，有兼容性问题

### 实现跨域访问有几种方式？

答：主要有两种
1.JSONP:
客户端: 客户端动态添加script元素，用script发送请求，代替ajax请求，并携带客户端一个函数名 到服务端
服务端: 接收客户端发来的函数名，将函数名和要返回的数据拼接为一条可执行的js语句，返回
2.CORS: Cross-Origin Resources Sharing
客户端正常发送ajax请求，服务端定义响应头，允许指定来源的请求跨域访问:
res.writeHead(200,{
…，
“Access-Control-Allow-Origin”:”允许的请求来源域名”
})

### 请解释一下 JavaScript 的同源策略, 为什么要有同源限制？

1. 概念：同源策略是客户端脚本（尤其是 Javascript）的重要的安全度量标准。它最早出自 Netscape Navigator2.0，其目的是防止某个文档或脚本从多个不同源装载。这里的同源策略指的是：协议，域名，端口相同，同源策略是一种安全协议，指一段脚本只能读取来自同一来源的窗口和文档的属性
2. 我们举例说明：比如一个黑客程序，他利用 Iframe 把真正的银行登录页面嵌到他的页面上，当你使用真实的用户名，密码登录时，他的页面就可以通过 Javascript 读取到你的表单中 input 中的内容，这样用户名，密码就轻松到手了。]
3. 缺点: 现在网站的 JS 都会进行压缩，一些文件用了严格模式，而另一些没有。这时这些本来是严格模式的文件，被 merge 后，这个串就到了文件的中间，不仅没有指示严格模式，反而在压缩后浪费了字节

### $ (document).ready() 是个什么函数？为什么要用它？

答：ready() 函数用于在文档进入ready状态时执行代码。当DOM 完全加载（例如HTML被完全解析DOM树构建完成时），jQuery允许你执行代码。使用$(document).ready()的最大好处在于它适用于所有浏览器，jQuery帮你解决了跨浏览器的难题。

### JavaScript window.onload 事件和 jQuery ready 函数有何不同？

答：这个问答是紧接着上一个的。JavaScript window.onload 事件和 jQuery ready 函数之间的主要区别是，前者除了要等待 DOM 被创建还要等到包括大型图片、音频、视频在内的所有外部资源都完全加载。如果加载图片和媒体内容花费了大量时间，用户就会感受到定义在 window.onload 事件上的代码在执行时有明显的延迟。
另一方面，jQuery ready() 函数只需对 DOM 树的等待，而无需对图像或外部资源加载的等待，从而执行起来更快。使用 jQuery $(document).ready() 的另一个优势是你可以在网页里多次使用它，浏览器会按它们在 HTML 页面里出现的顺序执行它们，相反对于 onload 技术而言，只能在单一函数里使用。鉴于这个好处，用 jQuery ready() 函数比用 JavaScript window.onload 事件要更好些

### 参考资料

1. https://blog.csdn.net/weixin_45579891/article/details/100931907