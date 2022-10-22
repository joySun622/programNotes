[Toc]

# 官网网址

- [官方文档](https://www.npmjs.com/package/doc)

# 简介

> NPM是随同NodeJS一起安装的包管理工具，能解决NodeJS代码部署上的很多问题，常见的使用场景有以下几种：
>
> - 允许用户从NPM服务器下载别人编写的第三方包到本地使用。
> - 允许用户从NPM服务器下载并安装别人编写的命令行程序到本地使用。
> - 允许用户将自己编写的包或命令行程序上传到NPM服务器供别人使用。

# 监测是否安装`npm`

> 新版的nodejs已经集成了npm

```
$ npm -v
2.3.0
```

# 升级`npm`

```
$ sudo npm install npm -g
/usr/local/bin/npm -> /usr/local/lib/node_modules/npm/bin/npm-cli.js
npm@2.14.2 /usr/local/lib/node_modules/npm

### 如果是 Window 系统使用以下命令即可：
npm install npm -g

###使用淘宝镜像的命令升级：
npm install -g cnpm --registry=https://registry.npmmirror.com
```

# 安装模块

> npm 的包安装分为本地安装（local）、全局安装（global）两种

```
# 安装模块到项目目录下，(在package.json文件的dependencies节点也会写入依赖)
npm install moduleName
 
#-save 的意思是将模块安装到项目目录下，并在package.json文件的dependencies节点写入依赖。
npm install moduleName -save
 
#--save-dev 的意思是将模块安装到项目目录下，并在package.json文件的devDependencies节点写入依赖。
npm install moduleName --save-dev
```

- **范例**

1. 使用 npm 命令安装常用的 Node.js web框架模块 **express**:

```
$ npm install express
```

2. 安装好之后，express 包就放在了工程目录下的 node_modules 目录中，因此在代码中只需要通过 **require('express')** 的方式就好，无需指定第三方包路径。

```
var express = require('express');
```

## 全局安装

> 1. 将安装包放在 /usr/local 下或者你 node 的安装目录。
>
> 2. 可以直接在命令行里使用。

```
npm install express -g   # 全局安装
```

## 本地安装

> 1. 将安装包放在 `./node_modules` 下（运行 npm 命令时所在的目录），如果没有 `node_modules `目录，会在当前执行 npm 命令的目录下生成 `node_modules` 目录。
>
> 2. 可以通过 `require() `来引入本地安装的包。

```
npm install express          # 本地安装

# install 命令总是安装模块的最新版本，如果要安装模块的特定版本，可以在模块名后面加上@和版本号。
$ npm install sax@latest
$ npm install sax@0.1.1
$ npm install sax@">=0.1.0 <0.2.0"

# 如果使用--save-exact参数，会在package.json文件指定安装模块的确切版本
$ npm install readable-stream --save --save-exact

$ npm install sax --save
$ npm install node-tap --save-dev
# 或者
$ npm install sax -S
$ npm install node-tap -D

# 如果要安装beta版本的模块，需要使用下面的命令
# 安装最新的beta版
$ npm install <module-name>@beta (latest beta)
# 安装指定的beta版
$ npm install <module-name>@1.3.1-beta.3

# npm install默认会安装dependencies字段和devDependencies字段中的所有模块，如果使用--production参数，可以只安装dependencies字段的模块
$ npm install --production
# 或者
$ NODE_ENV=production npm install
```

## 查看安装信息

```
$ npm list -g

├─┬ cnpm@4.3.2
│ ├── auto-correct@1.0.0
│ ├── bagpipe@0.3.5
│ ├── colors@1.1.2
│ ├─┬ commander@2.9.0
│ │ └── graceful-readlink@1.0.1
│ ├─┬ cross-spawn@0.2.9
│ │ └── lru-cache@2.7.3
……
```

## 查看某个模块的版本号

```
$ npm list grunt

projectName@projectVersion /path/to/project/folder
└── grunt@0.4.1
```

## 使用 package.json

> package.json 位于模块的目录下，用于定义包的属性。
>
> - **属性说明**
>
> - **name** - 包名。
> - **version** - 包的版本号。
> - **description** - 包的描述。
> - **homepage** - 包的官网 url 。
> - **author** - 包的作者姓名。
> - **contributors** - 包的其他贡献者姓名。
> - **dependencies** - 依赖包列表。如果依赖包没有安装，npm 会自动将依赖包安装在 node_module 目录下。
> - **repository** - 包代码存放的地方的类型，可以是 git 或 svn，git 可在 Github 上。
> - **main** - main 字段指定了程序的主入口文件，require('moduleName') 就会加载这个文件。这个字段的默认值是模块根目录下面的 index.js。
> - **keywords** - 关键字

- **范例**

> express 包的 package.json 文件，位于 node_modules/express/package.json 内容：

```
{
  "name": "express",
  "description": "Fast, unopinionated, minimalist web framework",
  "version": "4.13.3",
  "author": {
    "name": "TJ Holowaychuk",
    "email": "tj@vision-media.ca"
  },
  "contributors": [
    {
      "name": "Aaron Heckmann",
      "email": "aaron.heckmann+github@gmail.com"
    },
   .......
  ],
  "license": "MIT",
  "repository": {
    "type": "git",
    "url": "git+https://github.com/strongloop/express.git"
  },
  "homepage": "http://expressjs.com/",
  "keywords": [
    "express",
    "framework",
    "sinatra",
    "web",
    "rest",
    "restful",
    "router",
    "app",
    "api"
  ],
  "dependencies": {
    "accepts": "~1.2.12",
    "array-flatten": "1.1.1",
  ........
  },
  "devDependencies": {
    "after": "0.8.1",
    "ejs": "2.3.3",
    "istanbul": "0.3.17",
    ......
  },
  "engines": {
    "node": ">= 0.10.0"
  },
  "files": [
    "LICENSE",
    "History.md",
    "Readme.md",
    "index.js",
    "lib/"
  ],
  "scripts": {
    "test": "mocha --require test/support/env --reporter spec --bail --check-leaks test/ test/acceptance/",
    "test-ci": "istanbul cover node_modules/mocha/bin/_mocha --report lcovonly -- --require test/support/env --reporter spec --check-leaks test/ test/acceptance/",
    "test-cov": "istanbul cover node_modules/mocha/bin/_mocha -- --require test/support/env --reporter dot --check-leaks test/ test/acceptance/",
    "test-tap": "mocha --require test/support/env --reporter tap --check-leaks test/ test/acceptance/"
  },
  "gitHead": "ef7ad681b245fba023843ce94f6bcb8e275bbb8e",
  "bugs": {
    "url": "https://github.com/strongloop/express/issues"
  },
  "_id": "express@4.13.3",
  "_shasum": "ddb2f1fb4502bf33598d2b032b037960ca6c80a3",
  "_from": "express@*",
  "_npmVersion": "1.4.28",
  "_npmUser": {
    "name": "dougwilson",
    "email": "doug@somethingdoug.com"
  },
  "maintainers": [
    {
      "name": "tjholowaychuk",
      "email": "tj@vision-media.ca"
    }
  ],
  "dist": {
    "shasum": "ddb2f1fb4502bf33598d2b032b037960ca6c80a3",
    "tarball": "http://registry.npmjs.org/express/-/express-4.13.3.tgz"
  },
  "directories": {},
  "_resolved": "https://registry.npmjs.org/express/-/express-4.13.3.tgz",
  "readme": "ERROR: No README data found!"
}
```

## 安装报错

### 错误1

- **错误信息**

```
npm err! Error: connect ECONNREFUSED 127.0.0.1:8087 
```

- **解决方案**

```
$ npm config set proxy null
```

# 卸载模块

```
$ npm uninstall express

### 卸载后，你可以到 /node_modules/ 目录下查看包是否还存在，或者使用以下命令查看：
$ npm ls
```

# npm删除项目所有依赖和清缓存

```
1. 清理缓存的办法，一个是
npm cache verify
npm cache clean -f
npm cache clean --force

2. 删除项目所有依赖
npm uninstall *
```



# 更新模块

```
$ npm update express
```

# 搜索模块

```
$ npm search express
```

# 查看依赖包版本

```
查看依赖包的版本：
npm view sass-loader versions --json

查看依赖包本地安装的：
npm ls sass-loader

npm ls sass-loader -g （全局安装）
```



# 创建&发布模块

> 创建模块，package.json 文件是必不可少的。我们可以使用 NPM 生成 package.json 文件，生成的文件包含了基本的结果
>
> `-f`（代表`force`）、`-y`（代表`yes`），则跳过提问阶段，直接生成一个新的`package.json`文件。

```
$ npm init
This utility will walk you through creating a package.json file.
It only covers the most common items, and tries to guess sensible defaults.

See `npm help json` for definitive documentation on these fields
and exactly what they do.

Use `npm install <pkg> --save` afterwards to install a package and
save it as a dependency in the package.json file.

Press ^C at any time to quit.
name: (node_modules) runoob                   # 模块名
version: (1.0.0) 
description: Node.js 测试模块(www.runoob.com)  # 描述
entry point: (index.js) 
test command: make test
git repository: https://github.com/runoob/runoob.git  # Github 地址
keywords: 
author: 
license: (ISC) 
About to write to ……/node_modules/package.json:      # 生成地址

{
  "name": "runoob",
  "version": "1.0.0",
  "description": "Node.js 测试模块(www.runoob.com)",
  ……
}

Is this ok? (yes) yes  #  在最后输入 "yes" 后会生成 package.json 文件。
```

## 在 npm 资源库中注册用户（使用邮箱注册）

```
$ npm adduser
Username: mcmohd
Password:
Email: (this IS public) mcmohd@gmail.com
```

## 发布模块

```
$ npm publish
```

# 执行脚本

> npm 不仅可以用于模块管理，还可以用于执行脚本。package.json 文件有一个 scripts 字段，可以用于指定脚本命令，供npm直接调用。
>
> npm run 命令会自动在环境变量 $PATH 添加 node_modules/.bin 目录，所以 scripts 字段里面调用命令时不用加上路径，这就避免了全局安装 NPM 模块。
>
> npm run 如果不加任何参数，直接运行，会列出 package.json 里面所有可以执行的脚本命令。
>
> npm内置了两个命令简写，npm test 等同于执行 npm run test，npm start 等同于执行 npm run start。

```
npm run

npm bin
# 项目根目录下执行
$ npm bin
./node_modules/.bin

# 放弃某个版本模板号
$ npm deprecate my-thing@"< 0.2.3" "critical bug fixed in v0.2.3"


pre- 和 post- 脚本
npm run 为每条命令提供了 pre- 和 post- 两个钩子（hook）。以 npm run lint 为例，执行这条命令之前，npm会先查看有没有定义 prelint 和 postlint 两个钩子，如果有的话，就会先执行 npm run prelint，然后执行 npm run lint，最后执行npm run postlint。
```



# 常用命令

```
### 查看所有命令
npm help

# 查看某条命令的详细帮助：npm help <command>
范例：npm help install

# 查看 npm 命令列表
$ npm help

# 查看各个命令的简单用法
$ npm -l

# 查看 npm 的版本
$ npm -v

# 查看 npm 的配置
$ npm config list -l
# 把当前目录下node_modules子目录里边的对应模块更新至最新版本
npm update <package>
# 把全局安装的对应命令行程序更新至最新版
npm update <package> -g
# 清空NPM本地缓存，用于对付使用相同版本号发布新版本代码的人
npm cache clear
# 撤销发布自己发布过的某个版本代码
npm unpublish <package>@<version>
```

# 问题记录

## npm安装报错解决办法

- **报错信息如下**

> npm安装以及卸载的时候报的这个错误。反正是说什么依赖不能解析的。

```
npm ERR! code ERESOLVE
npm ERR! ERESOLVE unable to resolve dependency tree
npm ERR!
npm ERR! While resolving: flexbox@0.1.0
npm ERR! Found: vue@3.2.37
npm ERR! node_modules/vue
npm ERR!   vue@"^3.2.13" from the root project
npm ERR!
npm ERR! Could not resolve dependency:
npm ERR! peer vue@"^2.5.17" from element-ui@2.15.9
npm ERR! node_modules/element-ui
npm ERR!   element-ui@"*" from the root project
npm ERR!
npm ERR! Fix the upstream dependency conflict, or retry
npm ERR! this command with --force, or --legacy-peer-deps
npm ERR! to accept an incorrect (and potentially broken) dependency resolution.
npm ERR!
npm ERR! See C:\Users\Administrator\AppData\Local\npm-cache\eresolve-report.txt for a full report.

npm ERR! A complete log of this run can be found in:
npm ERR!     C:\Users\Administrator\AppData\Local\npm-cache\_logs\2022-07-19T05_46_30_652Z-debug-0.log
```

- **解决办法**

> 不能解析依赖树 ，需要先修复上面依赖关系冲突或者重新执行一下`npm install`命令，
> 后面跟`–force`或者`–legacy-peer-deps`去接受不正确的(并可能被破坏的)依赖解析。
>
> 根据它的建议，我们去执行`npm install --force`或者 `npm install --legacy-peer-deps`

# 参考资料

1. https://www.runoob.com/nodejs/nodejs-npm.html
2. https://my.oschina.net/u/3563387/blog/4633086
3. https://blog.csdn.net/seeeeeeeeeee/article/details/125870653