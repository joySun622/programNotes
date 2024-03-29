[Toc]

# 运行Vue项目

## 1. 配置Vue运行环境

1. node.js环境（npm包管理器）

   ```
   ### 查看nodejs版本
   node -v
   ### 若未安装node先安装node;从网上下载后直接安装
   ```

2. vue-cli 脚手架构建工具

   ```
   cnpm install -g vue-cli
   ```

3. 进入项目目录下，安装依赖

```
npm install
```

 4. 运行项目

    - **查看项目运行脚本配置**：客户端服务运行名

    ![image-20220309161848456](images/image-20220309161848456.png)

    - **运行项目**

    ```
    npm run serve
    ```

    运行结束后：可以看到本地访问地址，直接以该地址访问
    ![image-20220309162050283](images/image-20220309162050283.png)

## 2. 运行项目报错问题

- **场景描述**

> 运行项目时，提示无法识别某个模块，或者命令，或者找不到依赖的模块等
>
> 如下报错信息
>
> ```
> Error: Cannot find module './lib/spinner'
> Require stack:
> ```

- **解决方案**

1. 删除`node_modules` 整个文件夹和 package-lock.json 文件(这个文件不一定有)，注意不是 `package.json`
2. 清除缓存`npm cache clear --force`;
3. 重新安装依赖： `npm install`
4. 重新运行项目： `npm run serve` 

## 3. 编译vue项目报错

- **场景描述**

> 运行vue项目时，无法正常引入模块，报错信息如下：

```
 error  in ./src/views/Box.vue

Module Error (from ./node_modules/@vue/cli-service/node_modules/vue-loader/lib/index.js):


Vue packages version mismatch:

- vue@2.6.14 (D:\huadi_workspace\tjyz_workspace\old_tjyz_project\tjyz\TJYZ-v1.0\node_modules\vue\dist\vue.runtime.common.js)
- vue-template-compiler@2.6.10 (D:\huadi_workspace\tjyz_workspace\old_tjyz_project\tjyz\TJYZ-v1.0\node_modules\vue-template-compiler\package.json)
```

- **问题原因**

> vue-template-compiler和vue版本需要一致

- **解决方案**

> 安装vue对应版本的`vue-template-compiler`版本

```
npm i vue-template-compiler@2.6.14(你的vue版本号)
```

# 跨域问题

- **场景描述**

> 使用`axios`请求数据时，报错信息如下：
>
> ```
> Access to XMLHttpRequest at 'http://127.0.0.1:3000/' from origin 'http://localhost:63342' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource
> ```
>
> 无法正常获取到数据。使用Postman可以正常获取数据，浏览器无法正常获取数据。	

- **问题探索**

1. 同样是使用axios请求数据，为什么有的会产生跨域问题，有的不会？
   如：本地访问网址:http://localhost:5001/#/

   | 请求数据接口                                                 | 有无存在跨域问题 | 请求响应头比对                                               |
   | ------------------------------------------------------------ | ---------------- | ------------------------------------------------------------ |
   | [互联网上请求ali测试API接口](http://rap2api.taobao.org/app/mock/223607) | 无               | Access-Control-Allow-Origin: *<br/>Access-Control-Allow-Methods: POST, GET, PUT, DELETE, OPTIONS<br/>Access-Control-Allow-Credentials: true |
   | [内网请求数据API接口1](http://120.160.20.248:8090/maildatav/) | 无               | Access-Control-Allow-Credentials: true                       |
   | [内网请求API数据接口3](http://110.11.36.143:7090/Mobile)     | 有               | 无                                                           |

   - http://rap2api.taobao.org/app/mock/223607 请求&响应信息如下(使用Chrome检查工具)

   ```
   - General
   Request URL: http://rap2api.taobao.org/app/mock/223607/index/map
   Request Method: GET
   Status Code: 200 OK
   Remote Address: 203.107.44.46:80
   Referrer Policy: strict-origin-when-cross-origin
   
   - Reponse Headers
   HTTP/1.1 200 OK
   Date: Fri, 18 Mar 2022 02:46:31 GMT
   Content-Type: application/json; charset=utf-8
   Content-Length: 1617
   Connection: keep-alive
   Server: nginx/1.16.1
   Access-Control-Allow-Origin: *
   Access-Control-Allow-Methods: POST, GET, PUT, DELETE, OPTIONS
   Access-Control-Allow-Credentials: true
   Vary: Origin
   Set-Cookie: koa.sid=C7z-5FD7A4RgBf4w-OOUsjWm7Fv6aXEm; path=/; expires=Sat, 19 Mar 2022 02:17:26 GMT; httponly
   Set-Cookie: koa.sid.sig=P1PydaIt4zpMMVIt6SWK6S4oInE; path=/; expires=Sat, 19 Mar 2022 02:17:26 GMT; httponly
   
   - Request Headers
   GET /app/mock/223607/index/map HTTP/1.1
   Host: rap2api.taobao.org
   Connection: keep-alive
   Cache-Control: max-age=0
   Upgrade-Insecure-Requests: 1
   User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36
   Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
   Accept-Encoding: gzip, deflate
   Accept-Language: zh-CN,zh;q=0.9
   Cookie: aliyungf_tc=8bd63cd691946412b3d6546db9cbac27c759179c7f7f669d7bcc91e15498889b; koa.sid=C7z-5FD7A4RgBf4w-OOUsjWm7Fv6aXEm; koa.sid.sig=P1PydaIt4zpMMVIt6SWK6S4oInE
   ```

   - http://121.160.20.248:8090/maildatav/  请求&响应信息如下(使用Chrome检查工具)

   ```
   - General
   Request URL: http://121.160.20.248:8090/maildatav/mail/send/bb05?starttime=2019-08-01&endtime=2019-08-07
   Request Method: GET
   Status Code: 200 
   Remote Address: 111.160.20.248:8090
   Referrer Policy: strict-origin-when-cross-origin
   
   
   - Reponse Headers
   HTTP/1.1 200
   Server: nginx/1.20.0
   Date: Fri, 18 Mar 2022 03:09:55 GMT
   Content-Type: application/json;charset=UTF-8
   Transfer-Encoding: chunked
   Connection: keep-alive
   Access-Control-Allow-Credentials: true
   Expires: Wed, 23 Mar 2022 03:09:55 GMT
   Cache-Control: max-age=432000
   X-Frame-Options: SAMEORIGIN
   
   - Request Headers
   GET /maildatav/mail/send/bb05?starttime=2019-08-01&endtime=2019-08-07 HTTP/1.1
   Host: 121.160.20.248:8090
   Connection: keep-alive
   Cache-Control: max-age=0
   Upgrade-Insecure-Requests: 1
   User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36
   Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
   Accept-Encoding: gzip, deflate
   Accept-Language: zh-CN,zh;q=0.9
   Cookie: JSESSIONID=2628BD57460AC152D836B8EFD1CF0FFA; _ga=GA1.1.1128314729.1647572385; _gid=GA1.1.725497467.1647572385
   ```

   - http://110.11.36.143:7090/Mobile 请求&响应信息如下(使用Chrome检查工具) 存在跨域

   ```
   - General
   Request URL: http://111.160.20.248:8090//Mobile/ywljc/ywljc!getTjLclJson.action?{%22startdate%22:%222021-12-13%22,%22enddate%22:%222021-12-13%22,%22type%22:%220%22}
   Request Method: GET
   Status Code: 200 
   Referrer Policy: strict-origin-when-cross-origin
   
   
   - Reponse Headers
   HTTP/1.1 200 OK
   Server: nginx/1.20.0
   Date: Fri, 18 Mar 2022 03:20:55 GMT
   Content-Type: text/html; charset=utf-8
   Content-Length: 2167
   Last-Modified: Fri, 22 Oct 2021 09:47:01 GMT
   Connection: keep-alive
   ETag: "61728895-877"
   X-Frame-Options: SAMEORIGIN
   Accept-Ranges: bytes
   
   - Request Headers
   GET /Mobile/ywljc/ywljc!getTjLclJson.action?{%22startdate%22:%222021-12-13%22,%22enddate%22:%222021-12-13%22,%22type%22:%220%22} HTTP/1.1
   Host: 111.160.20.248:8090
   Connection: keep-alive
   Accept: application/json, text/plain, */*
   User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36
   Origin: http://localhost:5001
   Referer: http://localhost:5001/
   Accept-Encoding: gzip, deflate
   Accept-Language: zh-CN,zh;q=0.9
   ```

   **问题原因**：前端的跨域问题产生是因为浏览器的安全策略导致。

- **解决方案**

  - 方案1：使用`nginx`代理，将web请求&接口请求放在同一域中，真正的请求服务端口由nginx去请求。

  - 方案2：在Vue配置文件中配置代理

    ```
    - 修改vue.config.js配置，在配置中配置代理
    
    module.exports = {
        productionSourceMap: false,
        devServer: {
            port: 5001,
            proxy: {
                '/ywljc': {
                  target: 'http://11.11.11.111:7090', //API服务器的地址
                  ws: true, //代理websockets
                  changeOrigin: true, // 是否跨域，虚拟的站点需要更管origin
                  pathRewrite: {
                    // '^/api'是一个正则表达式，表示要匹配请求的url中，全部'http://localhost:8081/api' 转接为 http://localhost:8081/
                    '^/ajapi': '/',
                  }
                }
            },
        },
    
        publicPath: './'
    }
    ```

    - 页面请求网址：`/ajapi/Mobile/ywljc/ywljc!`

# 动态下拉选实现

- **场景描述**

> 进入页面后，ajax请求数据，将请求返回的数据在下拉选显示

- **实现**

> - 实现工具说明
>
> 1. vue2.0；
> 2. element-ui  基于vue 2.0
> 3. axios  //异步请求数据插件

- **源码**

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../plugins/element-ui/index.css">
    <script src="../plugins/vue.min.js"></script>
    <script src="../plugins/element-ui/index.js"></script>
    <script src="../plugins/axios/axios.min.js"></script>
    <title>Document</title>
    <style>
        .el-row {
          margin-bottom: 20px;
        }
        .el-col {
          border-radius: 4px;
        }
        .bg-purple-dark {
          background: #001529;
        }
        .bg-purple {
          background: #d3dce6;
        }
        .bg-purple-light {
          background: #e5e9f2;
        }
        .grid-content {
          border-radius: 4px;
          min-height: 36px;
        }
        .row-bg {
          padding: 10px 0;
          background-color: #f9fafc;
        }
      </style>
</head>
<body>
    <div id="app">
      <template>
        <el-select v-model="value" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.abbr"
            :label="item.name"
            :value="item.abbr">
            <span style="float: left">{{ item.name }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.abbr }}</span>
          </el-option>
        </el-select>
      </template>
    </div>
      
    <script>
      var Main = {
        data() {
          return {
            value:'',
            options:[],
          }
        },beforeCreate(){ //在实例初始化之后、进行数据侦听和事件/侦听器的配置之前同步调用
          var _this = this;//这步很重要，在axios函数中的this不会指向vue绑定的实例对象
          axios.get('city-img.json', {  
            }).then(function (response) {
              _this.options=response.data;//方式1：直接将返回数据赋值给vue实例对象中的options对象
              /**方式2：通过循环，将所需数据传递给vue实例对象中的options对象
              var tmpDatas=response.data;
              for(var i=0;i<tmpDatas.length;i++){
                console.log(tmpDatas[i]);
                _this.options.push(tmpDatas[i]);
              }**/
          }).catch(function (error) {
              console.log(error);
            })
        },
        methods:{}
      }
    var Ctor = Vue.extend(Main)
    new Ctor().$mount('#app')  
  </script>
</body>
</html>
```

- **实现中遇到的问题**

1. 使用axios获取到后台返回的数据后，无法设置到下拉选中

- **错误代码如下**：

```
beforeCreate(){ 
          axios.get('city-img.json', {  
            }).then(function (response) {
            this.options=response.data;//此方式无法将返回的数据赋值到options对象，原因：在axios函数中this此时不是指向											vue实例化的对象
          }).catch(function (error) {
              console.log(error);
            })
        }
```

# 动态加载img

- **场景描述**

> 从后台服务获取到img路径后，然后在页面上显示。但是设置好图片路径后一直无法显示，以为是路径问题，但是不使用动态加载`<img src="../assets/icon/index.png" slot="icon">`图片是能正常出来的。图片路径经过验证没有问题。
>
> ```
> <mt-tabbar v-model="selected" fixed class="border-1px-top">
> <p>
>   <img :src="imgs.img1" slot="icon">首页
> </p>
> ```
>
> - vue数据
>
> ```
> imgs:{
>     'img1': "../assets/icon/index.png",
>     'img2': "../assets/icon/brand.png",
>     'img3': "../assets/icon/cart.png",
>     'img4': "../assets/icon/me.png
> },
> ```

- **解决方案**

> 原因：vue中动态加载图片需要使用require引用资源的，修改代码如下
>
> ```
> imgs:{
>     'img1': require("../assets/icon/index.png"),
>     'img2': require('../assets/icon/brand.png'),
>     'img3': require("../assets/icon/cart.png"),
>     'img4': require("../assets/icon/me.png")
> },
> ```

# webpack和Vue.config.js中配置url-loader

> **url-loader**:一个用于将文件转换为 [base64](https://so.csdn.net/so/search?q=base64&spm=1001.2101.3001.7020) URI 的 webpack 加载器。
> **作用**：项目打包时，可以将符合条件的图片打包成base64 URL，减少http资源请求。
>
> **注意**：在使用url-loader时，需要下载file-loader，因为url-loader的使用依赖于file-loader。

- **webpack配置(webpack.config.js)**：

```
const {resolve} = require("path")
module.exports =  {
  mode:"development",
  entry:"./index.js", //resolve(__dirname,"src","index.js")
  output:{
    path: resolve(__dirname,"dist")
  },
  module:{
    rules:[
      {
        test:/\.(png|jpg|jpeg)$/, //小于条件的图片采用base64。减少请求
        exclude:"/node_modules/",//但是排除node_modules里面的图片
        use:[
          {
            loader:"url-loader",
            options:{
              limit: 10*1024, //如果图片小于10k，就使用base64处理，
              esModule:false, // url-loader默认采用ES6模块语法  html-loader使用commonJs  所以这里需要关闭es模块语法即可
            }
          }
        ]

      }
    ]
  }

}

```

- **`vue.config.js`配置**

> 除了在webpack中配置外，开发Vue项目时，会发现**vue.config.js**这个文件里面也可以去配置，但是发现语法跟webpack不太一样。

```
'use strict'
const path = require('path')
const defaultSettings = require('./src/settings.js')

function resolve(dir) {
  return path.join(__dirname, dir)
}
const name = defaultSettings.title
const port = process.env.port || process.env.npm_config_port || 9527 // dev port
module.exports = {

   */
  publicPath: '/',
  outputDir: 'dist',
  assetsDir: 'static',
  lintOnSave: process.env.NODE_ENV === 'development',
  productionSourceMap: false,
  configureWebpack: {
    name: name,
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  },
  chainWebpack(config) {
      config.module.rule("images")
      .test(/\.(png|jpeg|jpg)$/)
      .use("url-loader")
      .loader("url-loader").options({
        limit: 1024*10,// 小于10k的图片采用baseurl，大于和等于8k的就正常打包成图片
        name:"static/[name].[ext]"//图片大于等于10k时，设置打包后图片的存放位置 name是文件名   ext是文件后缀
      })
  }
}

```



# 知识点集

## vue.config.js配置解析

```
const path = require('path')
module.exports = {
    publicPath:  publicPath: process.env.NODE_ENV === 'production'? '/production-sub-path/': './'
    outputDir: 'zjjc',    //输出文件目录
    assetsDir: 'assets',    //放置生成的静态资源
    indexPath: 'indx.html',  //指定生成的 index.html 的输出路径 (相对于 outputDir)
    lintOnSave: false,   //eslint-loader 是否在保存的时候检查 安装@vue/cli-plugin-eslint有效
    runtimeCompiler: true,    //是否使用包含运行时编译器的 Vue 构建版本。设置true后你就可以在使用template
    productionSourceMap: false,   // 如果您不需要生产时的源映射，那么将此设置为false可以加速生产构建
    devServer: {
        https: false,
        open: true, //配置自动启动浏览器
        host: 'localhost',
        port: 8080,
        proxy: {
            '/zj-server/': {
                target:'http://172.6.4.11:8010',  //开发环境
                changeOrigin: true, //是否开启代理,
            },
        }
    },
    configureWebpack: config => {  //webpack配置
        const baseConfig = {
            resolve: {
                alias: {
                    '@assets': resolve('src/assets') //别名
                }
            },
        }
        return { ...baseConfig }
    },
    filenameHashing:false //去掉文件名中的hash
    //多页面应用
    pages:{
        //1对象模式（与字符串模式互斥）
        index: {
          // page 的入口
          entry: 'src/index/main.js',
          // 模板来源
          template: 'public/index.html',
          // 在 dist/index.html 的输出
          filename: 'index.html',
          // 当使用 title 选项时，
          // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
          title: 'Index Page',
          //chunks 选项的作用主要是针对多入口(entry)文件。当你有多个入口文件的时候，对应就会生成多个编译后的 js 文件。那么 chunks 选项就可以决定是否都使用这些生成的 js 文件，也就是编译完后html中引入的js文件（webpack打包入口）
          chunks: ['chunk-vendors', 'chunk-common', 'index']
        },
        //2字符串模式(与对象模式互斥)
        // 当使用只有入口的字符串格式时，
        // 模板会被推导为 `public/subpage.html`
        // 并且如果找不到的话，就回退到 `public/index.html`。
        // 输出文件名会被推导为 `subpage.html`。
        subpage: 'src/subpage/main.js'
    }
    //vuecli的webpack配置项修改
    //chaiwebpack对应api地址:https://github.com/Yatoo2018/webpack-chain/tree/zh-cmn-Hans
    
    chainWebpack: config => {
        //不生成index.html(不推荐性能不好,且不能在现代浏览器运行)
        config.plugins.delete('html')
        config.plugins.delete('preload')
        config.plugins.delete('prefetch')
        //限制内联文件加载大小，减少http请求数量
        config.module.rule('images').use('url-loader').loader('url-loader')
        .top(option=>Object.assign(options,{limit:10240})
        //css自动导入(oneOf唯一匹配 执行效率高)
        const types = ['vue-modules','vue','normal-modules','normal']
        types.forEach(type=>addStyleResource(config.module.rule('stylus').oneOf(type))
        //替换loader规则
        const svgRule = config.module.rule('svg')
        svgRule.uses.clear()
        svgRule.use('vue-svg-loader').loader('vue-svg-loader')
        //cache-loader：缓存编译。文件会缓存在node/modules/.cache中 遇到解决不了的编译问题可以删除该目录试试
        //thread-loader:多进程转换语法（主要用于资源过大打包使用-目前无使用场景占时只做了解）
        //修改插件选项
        //args[{
            title:'package.js name属性',
            templateParameters:[Function:templateParameters], //模板函数
            template:'对应html文件本地地址'
        }]
        config.plugin('html').tap(args=>args)
    },
    //直接合并到最初的webpack配置
    configureWebpack:{
        plugins:[
            new MyAwesomeWebpackPlugin()
        ]
    },
    css: {
        extract: process.env.NODE_ENV === 'production'
    }
};
function addStyleResource(rule){
    rule.use('style-resource').loader('style-resources-loader').option({
        patterns:[
            path.resolve(__dirname,'./src/style/imoirts.styl')
        ]
    })
}
```



## 什么是跨域

> - **跨域**：浏览器对于javascript的同源策略的限制,例如a.cn下面的js不能调用b.cn中的js,对象或数据(因为a.cn和b.cn是不同域),所以跨域就出现了.
>   **注意**：跨域限制访问，其实是**浏览器的限制**;
>   - 跨域问题一般在B-S结构项目中出现，在`C-S`结构项目中少有；
>   - postman 没有跨域限制，相当于客户端请求服务端，然后结构化数据后展现在postman客户端上；
>   - 前端的跨域问题产生是因为浏览器的安全策略导致。
>
> - **同源策略**：是指协议，域名，端口都要相同，其中有一个不同都会产生跨域。比如:我在本地上的域名是study.cn,请求另外一个域名一段数据
>
>   ![img](images/825922-20151028230107904-1333387603.png)
>
>   这个时候在浏览器上会报错:
>
>   ![img](images/825922-20151028230247091-934044692.png)
>
>   这个就是同源策略的保护,如果浏览器对javascript没有同源策略的保护,那么一些重要的机密网站将会很危险~
>
>   | study.cn/json/jsonp/jsonp.html             |                       |      |
>   | ------------------------------------------ | --------------------- | ---- |
>   | 请求地址                                   | 形式                  | 结果 |
>   | http://study.cn/test/a.html                | 同一域名,不同文件夹   | 成功 |
>   | http://study.cn/json/jsonp/jsonp.html      | 同一域名,统一文件夹   | 成功 |
>   | http://a.study.cn/json/jsonp/jsonp.html    | 不同域名,文件路径相同 | 失败 |
>   | http://study.cn:8080/json/jsonp/jsonp.html | 同一域名,不同端口     | 失败 |
>   | https://study.cn/json/jsonp/jsonp.html     | 同一域名,不同协议     | 失败 |

- **前端跨域情况**

> 1. AJAX直接请求普通文件存在跨域无权限访问的问题,不管是静态页面也好.
>
> 2. 不过我们在调用js文件的时候又不受跨域影响,比如引入jquery框架的,或者是调用相片的时候
>
> 3. 凡是拥有scr这个属性的标签都可以跨域例如<script><img><iframe>
>
> 4. 如果想通过纯web端跨域访问数据只有一种可能,那就是把远程服务器上的数据装进js格式的文件里.
>
> 5. 而json又是一个轻量级的数据格式,还被js原生支持

### Access-Control-Allow-Credentials

`Access-Control-Allow-Credentials`:`response`头部字段：

- 表示当请求的认证模式`Request.credentials=include`是否可以将对请求的响应暴露给前端`JS`页面(代码)。
  返回true表示可以，其他值均表示不可以。
  `credentials`可以是`cookies`,`authorization headers`或`TLS client centificates`

- 当作为对预检请求的响应的一部分时，能表示是否真正的请求可以使用`credentials`,如果这个响应头没有随资源返回，响应就会被浏览器忽视，不会返回到web内容。
  注意：简单的`GET`请求没有预检

这个配置的唯一有效值为`true`,当不需要证书验证时，省略此配置

```
### 当服务端设置
Access-Control-Allow-Credentials=true

### 方式1：XHR使用证书
var xhr = new XMLHttpRequest();
xhr.open('GET','http://example.com/',true);
xhr.withCredentials = true;
xhr.send(null);

###方式2：  Fetch with credentials
fetch(url,{
  credentials:'include'
});
```

### Access-Control-Allow-Origin

> 响应头指定了该响应的资源是否被允许与给定的[origin](https://developer.mozilla.org/zh-CN/docs/Glossary/Origin)共享
> origin:Web内容的源由用于访问它的[URL](https://developer.mozilla.org/zh-CN/docs/Glossary/URL) 的方案(协议)，主机(域名)和端口定义。只有当方案，主机和端口都匹配时，两个对象具有相同的起源。
> 服务器默认是不被允许跨域的。给Nginx服务器配置`Access-Control-Allow-Origin *`后，表示服务器可以接受所有的请求源（Origin）,即接受所有跨域的请求。

```
Access-Control-Allow-Origin: *    //允许所有域访问资源权限
Access-Control-Allow-Origin: <origin>   //指定一个可以访问资源的URI

### 范例
### 如需允许所有资源都可以访问您的资源，您可以如此设置：
Access-Control-Allow-Origin: *
### 如需允许https://developer.mozilla.org访问您的资源，您可以设置：
Access-Control-Allow-Origin: https://developer.mozilla.org

### 如果服务器未使用“*”，而是指定了一个域，那么为了向客户端表明服务器的返回会根据Origin请求头而有所不同，必须在Vary响应头中包含Origin。
Access-Control-Allow-Origin: https://developer.mozilla.org
Vary: Origin
```

- 设置接口资源可被所有域访问

```
Access-Control-Allow-Credentials=true  //需要证书验证
Access-Control-Allow-Origin: *   //允许所有域访问资源权限

### 当不需要证书验证&允许特定的URI访问资源
Access-Control-Allow-Origin: https://developer.mozilla.org
```

# 部署

## 1. vue项目打包

```
npm run build
```

## 2. 将dist文件夹拷贝到nginx项目目录下

```
修改dist文件夹为tjapp_2022,然后目录如下
/etc/nginx/tjapp_2022;
```



## 3. 配置nginx.conf

```
server {
    listen 5002;
    fastcgi_connect_timeout  300s;
    fastcgi_send_timeout  300s;
    fastcgi_read_timeout  300s;
    location / {
      root /etc/nginx/tjapp_2022;
	  try_files $uri $uri/ /index.html index.htm index.html.gz;
    }
    location /api/ { //配置api 代理
	proxy_pass  http://11.11.11.11:8082/;
	proxy_set_header Accept application/json,text/javascript,*/*;
	proxy_set_header Content-Type application/json;
      #proxy_set_header Host $host:$proxy_port;
      #proxy_set_header  X-Real-IP        $remote_addr;
      #proxy_set_header  X-Forwarded-For  $proxy_add_x_forwarded_for;
      #proxy_set_header X-NginX-Proxy true;
        proxy_connect_timeout    300s;
        proxy_read_timeout   300s;
        proxy_send_timeout   300s;
     # proxy_buffer_size          4k;
     # proxy_buffers              4 32k;
     # proxy_busy_buffers_size    64k;
     # proxy_temp_file_write_size 64k;
    }

   }
```

# 问题集

##  ...ules\core-js\modules\es6.regexp.exec.jsBrowserslist: caniuse-lite is outdated. Please run next command `npm update`

- **问题描述**

> 运行项目时，提示信息如下：
>
> ```
>  57% building 394/432 modules 38 active ...ules\core-js\modules\es6.regexp.exec.jsBrowserslist: caniuse-lite is outdated. Please run next command `npm update`
> ```

- **问题原因**

> 相关组件过期导致需要的组件缺失，需要更新版本

- **解决方案**

> 更新组件；可选择全局更新或指定更新.`npm update`:会先到远程仓库查询最新版本，然后查询本地版本。如果本地版本不存在，或者远程版本较新，就会安装

```
### 更新已安装的模块
npm update //

### 指定更新组件
npm update core-js@2
```



## 98%’ after emitting CopyPlugin 无法运行

- **问题描述**

> 跑项目中突然发现这个问题，卡在这里，也不报错，就是服务不运行

- **解决方案**

> **原因**：引入图片时，路径为空`require('')`或者`import`后未填充路径
>
> 填充图片路径：`require('/asserts/img/test.jpg')`

## vue打包时时候报错asset size limit ：the following asset（s）exceed the recommended size limit （244kib）

- **问题描述**

> vue项目打包时报文件过大的问题，虽然不影响正常打包，但是看起来很不美观
>
> ![image-20220705181823966](images/image-20220705181823966.png)

- **解决方案**

```
错误原因，资源(asset)和入口起点超过指定文件限制，需要在 vue.config.js 文件内做如下配置：

module.exports = {
    //webpack配置
	configureWebpack: {
	    //关闭 webpack 的性能提示
	    performance: {
		    hints:false
	    }
 
	    //或者
 
	    //警告 webpack 的性能提示
	    performance: {
	    	hints:'warning',
	    	//入口起点的最大体积
	    	maxEntrypointSize: 50000000,
	    	//生成文件的最大体积
	    	maxAssetSize: 30000000,
	    	//只给出 js 文件的性能提示
	    	assetFilter: function(assetFilename) {
	    		return assetFilename.endsWith('.js');
	    	}
	    }
    }
}
```

- 问题2：报错提示信息如下

  ```
  The code generator has deoptimised the styling of xx/typescript.js as it exceeds the max of 500kb
  ```

- **解决方案**

```
在babel.config.js或者.babelrc添加
{
  "compact": false
}
```



## 打包图片时报错

- **问题描述**

> 运行项目时，发现有警告信息，警告信息如下
>
> ```
>  warning  in ./src/assets/village/1627439206821_9369.JPG
> 
> Module parse failed: Unexpected character '�' (1:0)
> You may need an appropriate loader to handle this file type.
> (Source code omitted for this binary file)
> 
>  @ ./src/assets/village sync ^\.\/.*$
>  @ ./node_modules/cache-loader/dist/cjs.js??ref--12-0!./node_modules/babel-loader/lib!./node_modules/cache-loader/dist/cjs.js??ref--0-0!./node_modules/@vue/cli-service/node_modules/vue-loader/lib??vue-loader-options!./src/views/component/CircleMap.vue?vue&type=script&lang=js&        
>  @ ./src/views/component/CircleMap.vue?vue&type=script&lang=js&
>  @ ./src/views/component/CircleMap.vue
>  @ ./node_modules/cache-loader/dist/cjs.js??ref--12-0!./node_modules/babel-loader/lib!./node_modules/cache-loader/dist/cjs.js??ref--0-0!./node_modules/@vue/cli-service/node_modules/vue-loader/lib??vue-loader-options!./src/views/ExpressVillage.vue?vue&type=script&lang=js&
>  @ ./src/views/ExpressVillage.vue?vue&type=script&lang=js&
>  @ ./src/views/ExpressVillage.vue
>  @ ./src/router.js
>  @ ./src/main.js
>  @ multi (webpack)-dev-server/client?http://192.168.1.105:5002/sockjs-node (webpack)/hot/dev-server.js ./src/main.js
> ```

## Cannot read properties of null (reading ‘pickAlgorithm‘)

- **场景描述**

> 新导入项目，重新安装依赖，提示报错信息如下：
>
> ```
> npm ERR! Cannot read properties of null (reading 'pickAlgorithm')
> 
> npm ERR! A complete log of this run can be found in:
> npm ERR!     C:\Users\12613\AppData\Local\npm-cache\_logs\2022-04-21T03_30_54_505Z-debug-0.log
> ```

- **解决方案**:清除缓存`npm cache clear --force`之后再重新安装依赖`npm install`
  ![在这里插入图片描述](images/bfff3d5f507343ea80da85277e48d3c1.png)

## 运行项目报错

- **报错信息**

```
Error: Cannot find module './lib/spinner'
Require stack:
```

- **解决方案**

> 1. 删除 node_modules 整个文件夹和 package-lock.json 文件(这个文件不一定有)，注意不是 package.json
> 2. 在项目下运行 `npm install`
> 3. 继续运行 `npm start`

## vue 项目疯狂请求/sockjs-node/info?t=****

- **问题描述**

> 使用Vue，发现页面一直有请求`sockjs`
> ![image-20220510180522640](images/image-20220510180522640.png)

- **原因**

```mipsasm
1.首先看看sockjs的定义：是一个JavaScript库，提供跨浏览器JavaScript的API，创建了一个低延迟、全双工的浏览器和web服务器之间通信通道。在项目运行以后，network会一直调用这个接口。如果没有使用，那么就一直会报这个异常。
2.SockJS是一个JavaScript库（用于浏览器），提供类似于WebSocket的对象。其作用就是开发环境下，保证我们在改完代码重新编译之后，能够通知浏览器重新加载变更结果
3.可以理解为，这个库是用来让本地与浏览器之间的热模块更新通信的，但是现在这两个地址对接不上了，所以报了这个错。
```

- **解决方案**

**方案1**：（推荐）在项目根目录找到/package-lock.json文件，修改如下：

![img](images/c4c6e62d98954ae99dde9a7a2226ba94.png)

**方案2**：(没试过，只做记录)

```
config.devServer.host("localhost");

或者

devServe:{
  host:'localhost'
}
```

**方案3：**

```
在 node_modules/sockjs-client/dist/sockjs.js找到1609行

try {
  //  self.xhr.send(payload); 把这里注掉
} catch (e) {
  self.emit('finish',0,'');
  self.\_cleanup(false);
}
```

## vue项目一直`WebSocketClient`连接报错

- **场景描述**

> 运行vue项目后，发现页面可以显示正常，但在浏览器控制器上发现一直提示`WebSocketClient.js?5586:16 WebSocket connection to 'ws://192.168.0.100:8080/ws' failed`；而且在不断请求
> ![image-20220620162252718](images/image-20220620162252718.png)

- **原因**

> 原理是在运行vue前端代码的时候，服务器还会运行一个websocketClient,与服务器通信。如果检查到代码有修改，就会刷新页面。 如果websocket通信有问题是不会正常进行热重载的。
>  因为是直接在测试服务器上做修改，查看的。但是这里有一个问题，在服务器上，vue-cli-servece 不能正常检测到公网ip，而是直接使用了局域网的ip。这就导致ws的域名是内网ip,websocket通信失败。得到这样的错误提示：WebSocketClient.js?5586:16 WebSocket connection to 'ws://x.x.x.60:8080/ws' failed:
> 在webpack-dev-server的代码，在node_moudles目录下。 其中lib/options.json 里写了各个配置项的详细信息。 在其中也找到了对应的文档链接地址： [DevServer | webpack webpack is a module bundler. Its main purpose is to bundle JavaScript files for usage in a browser, yet it is also capable of transforming, bundling, or packaging just about any resource or asset. https://webpack.js.org/configuration/dev-server/#devserverclient](https://www.233tw.com/outurl?goto=https%3A%2F%2Fwebpack.js.org%2Fconfiguration%2Fdev-server%2F%23devserverclient)

- **解决方案**

  > 关键的就是client配置项。修改后，websocketClient可以正常使用公网ip发起通信。

> ```javascript
> const { defineConfig } = require('@vue/cli-service')
> module.exports = defineConfig({
>   devServer: {
>       host: '0.0.0.0',
>     // https:true,
>       port: 8080,
>       client: {
>         webSocketURL: 'ws://0.0.0.0:8080/ws', //设置为本地IP&端口端口
>       },
>       headers: {
>         'Access-Control-Allow-Origin': '*',
>       }
>   },
> 
>   transpileDependencies: true
> })
> ```

## 访问已部署vue项目，无法正常显示

- **问题描述**

> vue项目打包，使用nginx部署后，发现可以跳转到主页面，但是页面显示为空。浏览器报错信息如下
> ![image-20220722170739934](images/image-20220722170739934.png)
>
> 查看报错的请求路径，可以看到接收类型有问题：应该是js类型，但是请求响应后的类型为text/css
> ![image-20220722170857572](images/image-20220722170857572.png)

- **解决方案**

> 修改`vue.config.js`文件
>
> ```
> 原配置：
> publicPath: './'
> 修改后配置
> publicPath: '/'
> ```
>
> 原因：
> ![img](images/15916125-63472f96de1713bc.png)
>
> 一般nginx都会配置一个静态资源目录，打包后的文件都会放到这个静态资源目录里面，nginx去做映射，所以publicPath这个属性基本不用改。'/'就行。

## 绑定方法一直循环

- **场景描述**

> 在element-ui 中，点击表格数据，弹出信息框,绑定显示事件`:show="getCheckMachineInfo(scope.row.check_machine_code)"`,源码如下
>
> ```
> template slot-scope="scope">
>               <el-popover trigger="hover" placement="top-start" :show="getCheckMachineInfo(scope.row.check_machine_code)" >
>                 <p>预警数: {{ checkMachineInfo.warn_num }}</p>
>                 <div slot="reference" class="name-wrapper" style="display: inline-block !important;">
>                   <el-tag size="medium" >{{ scope.row.check_machine_code }}</el-tag>
>                 </div>
>               </el-popover>
>             </template>
> ```
>
> 但是启动页面后发现页面一直在触发绑定的函数，陷入死循环。

- **原因**

> `:`是指令`v-bind`的写法，在上述代码上，应该使用事件监听，而不是指令触发。而`@`用于事件监听。

- **解决方案**

```
原码：
:show="getCheckMachineInfo(scope.row.check_machine_code)"
修改为：
@show="getCheckMachineInfo(scope.row.check_machine_code)"
```



# Vue 百度地图离线版

## 1. 下载百度api的`js`

- **访问地址**

```
### 3.0版本
http://api.map.baidu.com/getscript?v=3.0&ak= 你的百度地图ak密钥 &services=&t=20210201100830

### 2.0版本
http://api.map.baidu.com/getscript?v=2.0&ak= 你的百度地图ak密钥 &services=&t=20210201100830
```

> 打开后看到堆压缩代码，复制代码，找一个工具格式化一下，变成容易编辑的代码格式；
> 工具地址：http://www.bejson.com/
> 格式化后全部复制，另存为js文件到本地，比如baidu-api.js，用nginx代理；
> 测试访问代理地址，比如 http://127.0.0.1:9800/baidu-api.js ，看是否能查看到此文件内容

## 2. 创建并引入baidu-init.js文件

> 添加一个js文件，和baidu-api.js保存在同一文件夹，可以将文件命名为baidu-init.js,代码如下：
> 测试访问代理地址，比如 http://127.0.0.1:9800/baidu-init.js ，看是否能查看到此文件内容.
>
> 修改`baidu-init.js`文件， 替换成`baidu-api.js`的代理地址

```
(function() {
	window.HOST_TYPE = "2";
	window.BMap_loadScriptTime = (new Date).getTime();
	window.BMap = window.BMap || {};
	window.BMap.apiLoad = function() {
		delete window.BMap.apiLoad;
		if (typeof _initBaiduMap == "function") {
			_initBaiduMap()
		}
	};
	var s = document.createElement('script');
	s.src = './baidu-api.js';  //此地址为步骤1下载的baidu-api.js存储地址
	document.body.appendChild(s);
})();
```

## 3. 修改依赖包下文件中远程访问api地址

> 在vue依赖包 node_modules文件找到，vue-baidu-map

![在这里插入图片描述](images/20210409151412243.png)

- 找到根目录的 index.js文件，可以看到是压缩的js代码，查找 `https://api.map.baidu.com/api?v`
  ![在这里插入图片描述](images/20210409151729836.png)

- 将地址改为你代理本地文件baidu-init.js的地址，比如：

`http://127.0.0.1:9080/baidu-init.js`

## 4. 下载百度地图瓦片

- 网上下载资源和资料很多，都可以试一下能不能下载，最好下载到16、17级试一下，17、18、19级瓦片会很大，下载过程比较慢。

![在这里插入图片描述](images/20210409152201460.png)

- 下载好以后，同样将瓦片资源代理到本地。

![在这里插入图片描述](images/20210409152341863.png)然后测试，比如我代理的地址为：

- 按照上图下载的文件，测试 http://127.0.0.1:9900/13/1546/310.png 看是否能访问本地图片；
  

## 5. 修改baidu-api.js

> 用 url.domain.main_domain_cdn多找几次，定位到下面代码：
> 我修改后的代码（注意有些变量不同）：
> 可以打印一下变量看看代表的是目录的哪一级，修改自己代理的瓦片资源路径；

```
	bu: function(a) {
		var b = this.map.ya();
		if (!this.map.Vd() && (a ? this.map.M.c0 = a : a = this.map.M.c0, a))
			for (var c = q, c = "2" == D.Au ? [D.url.proto + D.url.domain.main_domain_cdn.other[0] + "/"] : [D.url.proto + D.url.domain.main_domain_cdn.baidu[0] + "/", D.url.proto + D.url.domain.main_domain_cdn.baidu[1] + "/", D.url.proto + D.url.domain.main_domain_cdn.baidu[2] + "/"], e = 0, f; f = this.Cj[e]; e++)
				if (f.P_ == p) {
					b.m.qc = 18;
					f.getTilesUrl = function(b, e) {
						var f = b.x,
							f = this.map.ef.Hw(f, e).nm,
							m = b.y,
							n = Vb("normal"),
							o = 1;
						this.map.Ix() && (o = 2);
						n = "customimage/tile?&x=" + f + "&y=" + m + "&z=" + e + "&udt=" + n + "&scale=" + o + "&ak=" + ra;
						n = a.styleStr ? n + ("&styles=" + encodeURIComponent(a.styleStr)) : n + ("&customid=" + a.style);
						return c[Math.abs(f + m) % c.length] + n
					};
					break
				}
			}

```

- 修改为：

```
	bu: function(a) {
		var b = this.map.ya();
		if (!this.map.Vd() && (a ? this.map.M.c0 = a : a = this.map.M.c0, a))
			for (var c = q, c = "2" == D.Au ? [D.url.proto + D.url.domain.main_domain_cdn.other[0] + "/"] : [D.url.proto + D.url.domain.main_domain_cdn.baidu[0] + "/", D.url.proto + D.url.domain.main_domain_cdn.baidu[1] + "/", D.url.proto + D.url.domain.main_domain_cdn.baidu[2] + "/"], e = 0, f; f = this.Cj[e]; e++)
				if (f.P_ == p) {
					b.m.qc = 18;
					f.getTilesUrl = function(b, e) {
						var f = b.x,
							f = this.map.ef.Hw(f, e).nm,
							m = b.y,
							n = Vb("normal"),
							o = 1;
						this.map.Ix() && (o = 2);
						n = "customimage/tile?&x=" + f + "&y=" + m + "&z=" + e + "&udt=" + n + "&scale=" + o + "&ak=" + ra;
						n = a.styleStr ? n + ("&styles=" + a.styleStr) : n + ("&customid=" + a.style);
						// return c[Math.abs(f + m) % c.length] + n
						//e：一级目录 f：二级目录 m：文件名
						return "http://127.0.0.1:9900/" + e + "/" + f + "/" + m + ".png"
					};
					break
				}
	}

```

> 同理，换成自己代理的瓦片地址。
>
> 最后根据自己的需求按照vue-baidu-map的官方文档正常编写就可以了

## 6. demo实例

```
<template>
  <el-row :gutter="15">
    <el-col :span="18">
      <div class="map marginBottom" style="border-radius: 4px;overflow: hidden;">
        <!-- 给地图加点击事件getLocationPoint，点击地图获取位置相关的信息，经纬度啥的 -->
        <!-- scroll-wheel-zoom：是否可以用鼠标滚轮控制地图缩放，zoom是视图比例 -->
        <baidu-map :mapStyle="mapStyle" :mapClick="false" :scroll-wheel-zoom="false" :center="center" :zoom="zoom" @click="getLocationPoint">
          <bm-view style="width: 100%; height:600px; flex: 1"></bm-view>
          <bm-local-search :keyword="addressKeyword" :auto-viewport="true" style="display: none"></bm-local-search>
          <map-overlay :position="{lng: 114.075673,lat: 22.521582}" text="福田口岸" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.072825,lat: 22.529861}" text="福民" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.074473, lat: 22.540912}" text="岗厦" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.07425, lat: 22.555351}" text="莲花村" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.074174, lat: 22.565942}" text="冬瓜岭" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.074156, lat: 22.574152}" text="孖岭" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.066938, lat: 22.609605}" text="雅宝" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.067005, lat: 22.616831}" text="南坑" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.067396, lat: 22.622319}" text="光雅园" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.067286, lat: 22.632149}" text="五和" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.069952, lat: 22.640902}" text="坂田北" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.072948, lat: 22.647426}" text="贝尔路" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.073885, lat: 22.656419}" text="华为" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.078323, lat: 22.663063}" text="岗头" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.086368, lat: 22.662458}" text="雪象" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.112471, lat: 22.654079}" text="甘坑" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.125145, lat: 22.653388}" text="凉帽山" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.133661, lat: 22.660692}" text="上李朗" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.128775, lat: 22.676866}" text="木古" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.128631, lat: 22.683068}" text="华南城" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.127484, lat: 22.692649}" text="禾花" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.132394, lat: 22.700272}" text="平湖" @changeZoom="changeZoom"></map-overlay>
          <map-overlay :position="{lng: 114.139279, lat: 22.705197}" text="双拥街" @changeZoom="changeZoom"></map-overlay>
          <bm-polyline :path="path" v-for="path of polyline.paths"></bm-polyline>
          <bm-control>
            <el-button style="margin-top: 10px;margin-left: 10px;" @click="changeZoom(13,{lng: 114.143455, lat: 22.609943})" size="mini" icon="el-icon-c-scale-to-original">还原</el-button>
          </bm-control>
        </baidu-map>
      </div>
</template>

<script>
  import MapOverlay from "@/components/MapOverlay"
  export default {
    components:{MapOverlay},
    data(){
      return {
        addressKeyword:"",
        center: {lng: 114.143455, lat: 22.609943},
        zoom: 13,
        text:"",
        mapType:"BMAP_NORMAL_MAP",
        mapStyle:{
          styleJson:[
            {"featureType": "land", "elementType": "all", "stylers": {"color": "#111424ff" }},
            {"featureType": "road", "elementType": "all", "stylers": {"color": "#293142ff"}},
            {"featureType": "water", "elementType": "all", "stylers": { "color": "#1b2638ff" }},
            {"featureType": "green", "elementType": "all", "stylers": { "color": "#111424ff"}},
            {"featureType": "manmade", "elementType": "all", "stylers": { "color": "#111424ff" }},
            {"featureType": "building", "elementType": "all", "stylers": {"color": "#f1c232ff"}},
            {"featureType": "all", "elementType": "labels", "stylers": { "visibility": "off"}}
          ]
        },
        polyline: {
          paths:[
            [
              {lng: 114.075673,lat: 22.521582},
              {lng: 114.072825,lat: 22.529861},
              {lng: 114.074473, lat: 22.540912},
              {lng: 114.07425, lat: 22.555351},
              {lng: 114.074174, lat: 22.565942},
              {lng: 114.074156, lat: 22.574152},
              {lng: 114.066938, lat: 22.609605},
              {lng: 114.067005, lat: 22.616831},
              {lng: 114.067396, lat: 22.622319},
              {lng: 114.067286, lat: 22.632149},
              {lng: 114.069952, lat: 22.640902},
              {lng: 114.072948, lat: 22.647426},
              {lng: 114.073885, lat: 22.656419},
              {lng: 114.078323, lat: 22.663063},
              {lng: 114.086368, lat: 22.662458},
              {lng: 114.112471, lat: 22.654079},
              {lng: 114.125145, lat: 22.653388},
              {lng: 114.133661, lat: 22.660692},
              {lng: 114.128775, lat: 22.676866},
              {lng: 114.128631, lat: 22.683068},
              {lng: 114.127484, lat: 22.692649},
              {lng: 114.132394, lat: 22.700272},
              {lng: 114.139279, lat: 22.705197},
            ],
            []
          ]
        },
      }
    },
    created(){
      
    },
    methods: {
      
    }
  }
</script>
```

![在这里插入图片描述](images/20210409162223582.png)

![在这里插入图片描述](images/20210409162436807.png)

> 拖动或缩放会自动加载本地资源瓦片，如果下载的瓦片不全，就会出现上图的白板情况，根据自己的需要下载相应的区域瓦片即可。

# 参考资料

1. https://www.cnblogs.com/chenshishuo/p/4919224.html
2. https://blog.csdn.net/qq_29483485/article/details/123711261
3. https://www.jianshu.com/p/8fd1e4fc6b91
4. https://www.233tw.com/php/113155

- **百度离线地图**

1. https://blog.csdn.net/Zwain_M/article/details/115548665?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-2.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-2.no_search_link
2. https://blog.csdn.net/nihaokangzheng/article/details/120987362
3. https://blog.csdn.net/weixin_44000275/article/details/110482508
4. https://www.csdn.net/tags/NtzaggysODI3MTEtYmxvZwO0O0OO0O0O.html
5. https://ask.csdn.net/questions/7572399
6. https://www.csdn.net/tags/Mtjacg5sMDEzODctYmxvZwO0O0OO0O0O.html