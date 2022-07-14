[Toc]

# 在Vue中使用百度地图GL

## 方式1

1. **在index.html 引入**

   ```
   <!DOCTYPE html>
   <html>
     <head>
       <meta charset="utf-8">
       <meta name="viewport" content="width=device-width,initial-scale=1.0">
       <title>vue使用百度地图GL</title>
       <script type="text/javascript" src="https://api.map.baidu.com/apiv=1.0&type=webgl&ak=AK&s=1"></script>
     </head>
     <body>
       <div id="app"></div>
       <!-- built files will be auto injected -->
     </body>
   </html>
   ```

2.  **在webpack.base.conf.js配置文件中配置BMapGL**

   > 在webpack.base.conf.js配置文件中配置BMapGL,(创建BMapGL对象)，在module.exports 中与entry平级；

```
  externals: {
    'BMapGL': 'BMapGL'
  },  

```

3. **在地图组件中引入BMapGL**

```
<template>
  <div id="container"> </div>
</template>
```

```
import BMapGL from 'BMapGL'

export default {
  methods: {
    initMap() {
      this.myMap = new BMapGL.Map("container"); // 创建Map实例
      this.myMap.centerAndZoom("上海市", 7); // 初始化地图,设置中心点坐标和地图级别
      this.myMap.setMinZoom(5);//最小地图级别
      this.myMap.setMaxZoom(9);//最大地图级别
      this.myMap.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
      this.myMap.setMapStyleV2(mapStyle);//自定义个性化样式
      this.myMap.setTilt(45);//倾斜角
      this.myMap.setDisplayOptions({//遮罩层
        skyColors: ["rgba(0, 0, 0, 0)", "rgba(0, 0, 0, 0)"]//不显示
      });
    },
  }
}
```

## 方式2

1.  创建`bmpgl.js`

```
//BMapGL
export function BMPGL(ak) {
  return new Promise(function(resolve, reject) {
    window.init = function() {
      // eslint-disable-next-line
      resolve(BMapGL);
    };
    const script = document.createElement("script");
    script.type = "text/javascript";
    script.src = `http://api.map.baidu.com/api?v=1.0&type=webgl&ak=${ak}&callback=init`;
    script.onerror = reject;
    document.head.appendChild(script);
  });
}
```

2. **使用**

`map.vue`

```
import { BMPGL } from "@/components/map/bmpgl.js";
import custom_map from "@/components/map/custom_map.json";

export default {
  name: "map",
  data() {
    return {
      ak: "0qKQ", // 百度的地图密钥
      myMap: null,
      greenIcon: null,
      purpleIcon: null,
      yellowIcon: null,
      redIcon: null,
      point: null
    };
  },
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      //传入密钥获取地图回调
      BMPGL(this.ak)
        .then(BMapGL => {
          // 创建地图实例
          let map = new BMapGL.Map("container");
          // 初始化地图，设置中心点坐标和地图级别
          // new BMapGL.Point(118.477, 31.346)
          map.centerAndZoom("芜湖市", 12);
          //开启鼠标滚轮缩放
          map.enableScrollWheelZoom(true);
          map.setMinZoom(9);
          map.setMaxZoom(13);
          map.setTilt(85);
          map.setMapStyleV2({ styleJson: custom_map });
          map.setDisplayOptions({
            skyColors: ["rgba(0, 0, 0, 0)"]
          });
        })
    },
    // 地址解析
    getPoint(location) {
      return new Promise((resolve, reject) => {
        let myGeo = new BMapGL.Geocoder();
        let point = null;
        myGeo.getPoint(location, point => {
          resolve(point);
        });
      });
    },
    //创建标记
    createMaker(state, point, content, option) {
      let maker = null;
      let steelContent = `<div class="padding-left-10">
          <div>单位资产信息：${content.assetsInfo}</div>
        </div>`;
      maker = new BMapGL.Marker(point, { icon: this.greenIcon });
      maker.addEventListener("mouseover", function() {
        this.openInfoWindow(new BMapGL.InfoWindow(steelContent, option));
      });
      maker.addEventListener("mouseout", function() {
        this.closeInfoWindow();
      });
      return maker;
    }
  }
};
```

```
.home {
  position: relative;
  .allmap {
    width: 100%;
    height: 450px;
    margin: 10px 0;
    position: relative;
    z-index: 1;
  }
  .point {
    position: absolute;
    bottom: 5px;
    left: 5px;
    z-index: 10;
    color: rgba(255, 255, 255, 0.7);
  }
}
```

## 方式3

1. **安装插件**

```
// 安装插件
npm install vue-bmap-gl vue-mapvgl -S
```

2. **使用**

- `main.js`

```
// 引入vue-bmap-gl
import VueBMap from "vue-bmap-gl";
import VueMapvgl from "vue-mapvgl";
import "vue-bmap-gl/dist/style.css";

Vue.use(VueBMap);
Vue.prototype.VueBMap = VueBMap;
Vue.use(VueMapvgl);
Vue.prototype.VueMapvgl = VueMapvgl;

// 初始化vue-bmap
VueBMap.initBMapApiLoader({
  // 百度的key
  ak: "0wVRYQ",//用自己的百度地图ak
  // 百度 sdk 版本，默认为 1.0
  v: "1.0"
});
```

- `html/.vue`

```
<template>
  <div class="home">
    <!--创建地图容器-->
    <div id="container" class="allmap">
      <el-bmap
        class="bmap-box"
        :zoom="zoom"
        :center="center"
        :tilt="50"
        :vid="'bmap-vue'"
        :mapStyleV2="mapStyleV2"
      >
        <el-bmap-marker
          v-for="(marker, index) in markers"
          :key="index"
          :vid="index"
          :offset="[0, 0]"
          :icon="marker.icon"
          :visible="marker.visible"
          :position="marker.position"
          :title="marker.title"
          :status="1"
          :events="{
            mouseover(e) {
              overkMarker(e, 1);
            },
            mouseout(e) {
              outMarker(e, 1);
            }
          }"
        ></el-bmap-marker>
        <!-- mouseover(e) {
              overkMarker(e, 1);
            },
            mouseout(e) {
              outMarker(e, 2);
            } -->
        <el-bmap-info-window
          :title="currentWindow.title"
          :position="currentWindow.position"
          :visible="currentWindow.visible"
          :enable-auto-pan="false"
        >
          <template>
            <div>单位类型1</div>
            <div>单位类型2</div>
          </template>
        </el-bmap-info-window>
      </el-bmap>
    </div>
    <!-- 左下角标记 -->
    <div class="point">
      <div>
        <span class="green"></span>
        <span>单位类型1</span>
      </div>
      <div>
        <span class="blue"></span>
        <span>单位类型2</span>
      </div>
      <div>
        <span class="yellow"></span>
        <span>单位类型3</span>
      </div>
      <div>
        <span class="red"></span>
        <span>单位类型4</span>
      </div>
    </div>
  </div>
</template>
```

- `js`

```
//样式文件在我的资源中，也可自己在百度地图个性化中配置，配置地址 http://lbsyun.baidu.com/apiconsole/custommap
import mapStyleV2 from "@/components/map/mapStyleV2.js";

export default {
  name: "mymap",
  data() {
    let redIcon = require("@/assets/images/red.png");
    let yellowIcon = require("@/assets/images/yellow.png");
    let purpleIcon = require("@/assets/images/purple.png");
    let greenIcon = require("@/assets/images/green.png");

    return {
      greenIcon,
      purpleIcon,
      yellowIcon,
      redIcon,
      mapStyleV2,
      zoom: 11,
      center: [118.477, 31.346],
      markers: [
        {
          position: [118.377, 31.246],
          title: "单位类型2",
          visible: true,
          icon: {
            url: redIcon,
            size: [12, 12]
          }
        },
        {
          position: [118.5273285, 31.21515044],
          title: "单位类型1",
          visible: true,
          icon: {
            url: greenIcon,
            size: [12, 12]
          }
        }
      ],
      currentWindow: {
        title: "",
        position: [118.377, 31.546],
        content: "hello",
        visible: false
      }
    };
  },
  methods: {
    overkMarker(e) {
      console.log("mouseover");
      this.currentWindow = {
        title: "标题",
        position: [e.latLng.lng, e.latLng.lat],
        content: `<div>
          <div>单位类型1<div>
          <div>技术类型2<div>
        </div>`,
        visible: true
      };
    },
    outMarker(e) {
      console.log("outMarker");
      this.currentWindow.visible = false;
    },
  }
};
```

- `scss`

```
.home {
  position: relative;
  .allmap {
    width: 100%;
    height: 450px;
    margin: 10px 0;
    position: relative;
    z-index: 1;
  }
  .point {
    position: absolute;
    bottom: 5px;
    left: 5px;
    z-index: 10;
    color: rgba(255, 255, 255, 0.7);
    .green{
      display: inline-block;
      width: 12px;
      height: 12px;
      background: rgb(0, 255, 0);
      border-radius: 50%;
    }
    .red{
      display: inline-block;
      width: 12px;
      height: 12px;
      background: rgb(255, 0, 0);
      border-radius: 50%;
    }
    .yellow{
      display: inline-block;
      width: 12px;
      height: 12px;
      background: rgb(251, 255, 0);
      border-radius: 50%;
    }
    .blue{
      display: inline-block;
      width: 12px;
      height: 12px;
      background: rgb(247, 0, 255);
      border-radius: 50%;
    }
  }
}
```





# 参考资料

1. https://blog.csdn.net/weixin_45161039/article/details/112262625