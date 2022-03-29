[Toc]

# 官方网址

[官方文档](https://echarts.apache.org/zh/index.html)

# GEO

## 设置区域颜色

```
regions: [      //对不同的区块进行着色
            {
              name: '马山县', //区块名称
              itemStyle: {
                  normal: {
                      areaColor: '#fbd8f3'
                  }
              }
            },{
              name: '上林县',
              itemStyle: {
                  normal: {
                      areaColor: '#fcc8b8'
                  }
              }
          },{
            name: '宾阳县',
            itemStyle: {
                normal: {
                    areaColor: '#2b97df'
                }
            }
        }]
```

## GEO&Series[type=map]

> 默认情况下，map series 会自己生成内部专用的 `geo` 组件。但是也可以用这个 `geoIndex` 指定一个 [geo](https://echarts.apache.org/v4/zh/option.html#geo) 组件。这样的话，map 和 其他 series（例如散点图）就可以共享一个 [geo](https://echarts.apache.org/v4/zh/option.html#geo) 组件了。并且，[geo](https://echarts.apache.org/v4/zh/option.html#geo) 组件的颜色也可以被这个 map series 控制，从而用 [visualMap](https://echarts.apache.org/v4/zh/option.html#visualMap) 来更改。

- `geoIndex`  指定`option.geo`中定义的`geo`组件下标

```
var option={
	geo: [{
		zlevel:2,//geo显示级别，默认是0
		map: 'china',//地图名
		roam: false,//设置为false,不启动roam就无所谓缩放拖曳同步了
		zoom:1.1,//缩放级别
		itemStyle:{//顶层地图的样式，如地图区域颜色，边框颜色，边框大小等
			normal:{
				areaColor:'#08407D',
				borderColor:'#00FFFF',
				borderWidth:0.5
			}
			
		},
	},{
		map: 'china',
		roam: false,//roam与上一个geo配置相同
		zoom:1.1,
		itemStyle:{//底层地图样式
			normal:{
                            //此处border设置你想要显示的地图外边框样式，可设置border颜色，阴影等
			    borderColor:'#0A609D',
			    borderWidth:2
			},
		},
	},
   ],
   series:[{
	name:'china',
	type:'map',
	mapType:map,
	roam:false,
	geoIndex: 0,
        //指定要用哪个geo的配置，这里使用第一个geo，因为第一个geo的显示级别zlevel更高，展示数据需要在最顶层展示
	//注意：当你设置了geoindex以后，就会使用对应geo的label、itemstyle样式，当前series的label等样式就无效了，
	data:[],//设置地图数据
   }]
};

```

## `visualMap`自定义每个区间颜色&标签样式

> 当`visualMap`的` type = piecewise`,类型为分段型时
>
> 自定义『分段式视觉映射组件（visualMapPiecewise）』的每一段的范围，以及每一段的文字，以及每一段的特别的样式。例如：
>
> ```javascript
> pieces: [
>     {min: 1500}, // 不指定 max，表示 max 为无限大（Infinity）。
>     {min: 900, max: 1500},
>     {min: 310, max: 1000},
>     {min: 200, max: 300},
>     {min: 10, max: 200, label: '10 到 200（自定义label）'},
>     {value: 123, label: '123（自定义特殊颜色）', color: 'grey'}, // 表示 value 等于 123 的情况。
>     {max: 5}     // 不指定 min，表示 min 为无限大（-Infinity）。
> ]
> ```
>
> 或者，更精确得，可以使用 `lt`（小于，little than），`gt`（大于，greater than），`lte`（小于等于 lettle than or equals），`gte`（大于等于，greater than or equals）来表达边界：
>
> ```javascript
> pieces: [
>     {gt: 1500},            // (1500, Infinity]
>     {gt: 900, lte: 1500},  // (900, 1500]
>     {gt: 310, lte: 1000},  // (310, 1000]
>     {gt: 200, lte: 300},   // (200, 300]
>     {gt: 10, lte: 200, label: '10 到 200（自定义label）'},       // (10, 200]
>     {value: 123, label: '123（自定义特殊颜色）', color: 'grey'},  // [123, 123]
>     {lt: 5}                 // (-Infinity, 5)
> ]
> ```
>
> 注意，如果两个 piece 的区间重叠，则会自动进行去重。

### 范例

```
### GEO组件
geo: {
          map: "china",
          label: {
            show:true,
            normal: {
              show: true,
              textStyle: {
                color: "#fff",
                fontSize: 16
              }
            },
            emphasis: {
              show: false,
              textStyle: {
                color: "#fff",
                fontSize: 13
              }
            }
          },
          roam: true,
          // 自定义map中心经纬度
          center: [119.848013, 38.640351],
          // layoutCenter layoutSize配合使用
          layoutCenter: ["75%", "40%"],
          layoutSize: 900,
          itemStyle: {
            normal: {
              areaColor: "none",
              borderColor: "#77c9d6",
              borderWidth: 2
              /*areaColor: '#323c48',
                            borderColor: '#404a59'*/
            },
            emphasis: {
              // areaColor: '#e0decf'
              areaColor: "#77c9d6"
            }
          }
        },
        
        
 ### 视图映射设置
  //设置视图映射
        visualMap: {
            min: 0,
            max: 1000000,
            text: ['High', 'Low'],
            realtime: false,
            calculable: true,
            type:"piecewise",
            showLabel:true,
            pieces: [      // 自定义每一段的范围，以及每一段的文字
              { gt: 800000, label: '80万以上',color:'#ff3333'}, // 不指定 max，表示 max 为无限大（Infinity）。
              { gt: 600000, lte: 800000, label: '(60万,80万]',color:'#f19d3a'},
              { gt: 400000, lte: 600000, label: '(40万,60万]',color:'#e0e011'},
              { gt: 200000, lte: 400000, label: '(20万,40万]',color:'#caca56'},
              { gt: 100000, lte: 200000, label: '(10万,20万]',color:'#1890ff'},
              { lte: 100000, label: '10万以下',color:'#62bb82'}    // 不指定 min，表示 min 为无限大（-Infinity）。
           ],
            // inRange: { //使用inRange配置，颜色有可能无法显示
            //     //color: ['lightskyblue', 'yellow', 'orangered',"red"]
            //     color: [ '#62bb82','#1890ff','#bdea33','yellow','#f19d3a','#ff3333']
            // },
            seriesIndex:0,
            top:'0%',
            textStyle: {
              color: '#737373'
            },
            emphasis:{
              label:{
                show:true,
              }
            },
        },
        
  ### series
   series: [
          {
            name:"区域设置",
            type:"map",
            geoIndex:0,
            label: {
                show: true
            },
            data:mapData.map(function(dataItem) {
              return {
                name: isOutFlow ? dataItem["toName"]:dataItem["fromName"],
                value: dataItem.value
              }
              })
          }]
```

# tooltip

> 提示框组件
>
> **提示框组件的通用介绍：**
>
> 提示框组件可以设置在多种地方：
>
> - 可以设置在全局，即 [tooltip](https://echarts.apache.org/v4/zh/option.html#tooltip)
> - 可以设置在坐标系中，即 [grid.tooltip](https://echarts.apache.org/v4/zh/option.html#grid.tooltip)、[polar.tooltip](https://echarts.apache.org/v4/zh/option.html#polar.tooltip)、[single.tooltip](https://echarts.apache.org/v4/zh/option.html#single.tooltip)
> - 可以设置在系列中，即 [series.tooltip](https://echarts.apache.org/v4/zh/option.html#series.tooltip)
>   **注意：**`series.tooltip` 仅在 [tooltip.trigger](https://echarts.apache.org/v4/zh/option.html#tooltip.trigger) 为 `'item'` 时有效。
> - 可以设置在系列的每个数据项中，即 [series.data.tooltip](https://echarts.apache.org/v4/zh/option.html#series.data.tooltip)

## 触发类型

> `tooltip.trigger=''`
>
> string
>
> **触发类型**。
>
> 可选：
>
> - `'item'`
>
>   数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
>
> - `'axis'`
>
>   坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
>
>   在 ECharts 2.x 中只支持类目轴上使用 axis trigger，在 ECharts 3 中支持在[直角坐标系](https://echarts.apache.org/v4/zh/option.html#grid)和[极坐标系](https://echarts.apache.org/v4/zh/option.html#polar)上的所有类型的轴。并且可以通过 [axisPointer.axis](https://echarts.apache.org/v4/zh/option.html#tooltip.axisPointer.axis) 指定坐标轴。
>
> - `'none'`
>
>   什么都不触发。

- 范例：全局`tooltip`

  > 对不同seriesType定义提示不同的格式的提示框信息

```
tooltip: {
          trigger: "item",
          formatter: function(params, ticket, callback) {//params.data 为每个定义在series中传入的data数据项
            if (params.seriesType == "effectScatter") {
              return "" + params.data.name + "：" + params.data.value[2];
            } else if (params.seriesType == "lines") {
              return (
                params.data.fromName +
                ">" +
                params.data.toName +
                "<br />" +
                params.data.value
              );
            }else if(params.seriesType == "map"){
              const flag = isOutFlow ? "流入 > ":"流出 < ";
              return flag + params.data.name +":"+ params.data.value;
            }else {
              return params.name;
            }
          }
        },
```





# 参考资料

1. https://www.cnblogs.com/it-Ren/p/10942263.html
2. https://blog.csdn.net/m0_37294207/article/details/96879705
3. https://echarts.apache.org/v4/zh/option.html#geo

