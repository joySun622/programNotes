[Toc]

# 问题记录

# Element UI 中 日期插件 el-date-picker 报错 placement 的解决方法

- **问题描述**

> - **报错信息如下**
>
> ```
> vue.runtime.esm.js?c320:619 [Vue warn]: Avoid mutating a prop directly since the value will be overwritten whenever the parent component re-renders. Instead, use a data or computed property based on the prop's value. Prop being mutated: "placement"
> 
> found in
> 
> ---> <ElDatePicker> at packages/date-picker/src/picker.vue
>        <ElFormItem> at packages/form/src/form-item.vue
>          <ElForm> at packages/form/src/form.vue
>            <ElHeader> at packages/header/src/main.vue
>              <ElContainer> at packages/container/src/main.vue
>                <WaybillList> at src/views/UnusualWaybillList.vue
>                  <App> at src/App.vue
>                    <Root>
> ```
>
> 该报错提示，不要擅自在子模块中改动父模块属性`placement`，但是找了一遍，没有找到`placement`这个属性。

- **报错原因**

> 版本兼容性问题

- **解决方案**

> 解决方法：将element ui版本降至 2.15.6（含）以下即可
>
> ```
> npm install elment-ui@2.15.6 --save
> ```

# The template root requires exactly one element.(vue/valid-template-root)错误处理

- **问题描述**

> ```
> npm run lint 执行时，报错如下：
> The template root requires exactly one element.(vue/valid-template-root)
> 
> 或者提示：
> D:\huadi_workspace\tjyz_workspace\old_tjyz_project\tjyz\zj_waybill\src\views\UnusualWaybillUpload.vue
>   1:1  error  The template requires child element  vue/valid-template-root
> 
> ✖ 1 problem (1 error, 0 warnings)
> ```

- **报错原因&解决方案**

> 在使用自定义组件的时候 ，`<template>`标签里只能有一个根节点，且必须有一个子元素在`<template>`标签里，否则无法通过`eslint`的校验
>
> **错误示范**
>
> ```
> <template>
>    <el-row :gutter="20"  class="el-row">
>    </el-row>
>    <EditApp ></EditApp>
> </template>
> ```
>
> - **正确示范**
>
> ```
> <template>
>    <div>
>      <el-row :gutter="20"  class="el-row">
>      </el-row>
>      <EditApp ></EditApp>
>    </div>
> </template>
> ```

# 图片加载

- **问题描述**

> 图片加载，当点击后才显示图片，但因为是写在另一个插件里中，图片会提前加载，因为没有路径，导致浏览器报错
>
> 报错信息如下：
> ![image-20220825175500977](images/image-20220825175500977.png)

- **解决方案**

> - 方案1：
>   添加懒加载设置`:lazy="true"`,但该解决方案适合有滚动的情况，没有滚动的场景无法正常展示图片
>
> - 方案2：设置`:src`默认值，置空也可以
>
>   ```
>   <el-image 
>       style="width: 100px; height: 100px"
>       :src="processDetailInfo.express_img_url?processDetailInfo.express_img_url:''" 
>       :preview-src-list="processDetailInfo.preImgUrls">
>   </el-image>
>   ```
>
>   

# 参考资料

1. https://blog.csdn.net/drogan_/article/details/125263491
2. 