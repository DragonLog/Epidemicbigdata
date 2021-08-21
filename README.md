> **写在前面的话：由于安卓项目是由gradle构建的，而maven用习惯的我用gradle的时候简直被它的各种报错和配置整的很惨，所以项目调试成功后不敢再做任何修改就直接上传了。整个项目copy下来导入Android Studio后连上手机开启USB调试应该直接就能运行（前提是你的SDK和gradle都配好了，文件夹和包结构都不能有错），由于还集成了百度定位SDK，可能有些配置信息会过期或失效，需要各位同学自行结合官方文档调整！（截止项目上传后，我用另外一台AS纯净的电脑拉取该项目直接运行是没有任何问题的！）**
## 疫情大数据App（**基于Java**）
***
> **整个项目都使用 Java进行开发**
1. **对新浪和网易疫情数据接口进行分析，拆分出实体类和有价值信息。**  
2. **soJson网站有很多免费且有用的API，关于疫情新闻的API就由它提供。**
3. **对retrofit进行封装，它作为整个app的核心网络请求，提供数据的更新和交互。**
4. **依照大厂布局进行模仿，使用各种布局结合cardview确定整个页面风格，简洁清爽。**
5. **整个项目采用单Activity+多Fragment进行开发，在原有底部导航栏基础上使用viewpager+TabLayout增加顶部导航栏，同时使用FrameLayout为fragment跳转做铺垫。（由于知识水平有限，该项目对页面跳转的处理存在问题！）**
6. **使用SwipeRefreshLayout，支持页面下拉刷新。**
7. **集成了LoadingDialog加载动画，人机交互更好。**
8. **Echarts+WebView+jQuery+JavaScript实现“前后端传值”，渲染疫情地图和折线统计图。**
9. **recyclerview+adapter实现可上滑和下拉的列表，配合各种样式展现更多数据。**
10. **集成百度定位SDK，可根据当前位置显示该省的疫情数据。（仅限于中国以“省”字结尾的省份名称。）**
11. **这是我安卓的课设作业，整个项目大概有五个难点：（1）首先就是对gradle的不熟悉，让整个项目的构建很多时候都处于焦急等待和无计可施；（2）就是对数据接口的分析，新浪和网易的接口因为是大厂在维护所以比较稳定，但由于没有相关文档说明，你只能在茫茫英文和数字中慢慢去结合他们的前端自己分析出有价值的信息；（3）图表问题，最开始想打算用安卓这一块的地图和统计图的，深入后发现又是一片复杂的知识领域，通过对大厂的前端观察发现根本没有必要用安卓端的图表，使用echarts结合webview是完美解决方案，由此只需要学习webview相关知识然后在前端用js渲染即可，这一块的调试需要反复进行，因为AS无法调试html页面；（4）页面跳转问题，由于老师对这一块补充的很少，网上资料也很少，所以整个项目没有系统性的跳转流程，可以说很多页面是硬跳的；（5）百度定位问题，由于需要集成百度定位SDK，所以你又需要对新的东西进行探索，光常规配置就费时费力，后期结合动态权限获取定位信息还有依赖冲突不调试个几十遍真是搞不定。**
> **注：百度定位目前用的是我的AK和AS码，如果这一块报错可能是配置过期，需要结合官方文档修正！** 
***
![示例图片](https://github.com/DragonLog/Epidemicbigdata/blob/main/pictureForExample/show1.jpg)
![示例图片](https://github.com/DragonLog/Epidemicbigdata/blob/main/pictureForExample/show2.jpg)
![示例图片](https://github.com/DragonLog/Epidemicbigdata/blob/main/pictureForExample/show3.jpg)
![示例图片](https://github.com/DragonLog/Epidemicbigdata/blob/main/pictureForExample/show4.jpg)
