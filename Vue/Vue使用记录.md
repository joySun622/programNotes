[Toc]

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

