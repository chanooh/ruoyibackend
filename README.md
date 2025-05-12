## 城市品质提升

ruoyi-admin 后台接口&小程序接口


ruoyi-ui 后台ui

## 后台ui打包

路由baseurl设置： /src/router/index.js

请求baseurl设置： env

vue.config.js 设置 publicPath

最后执行npm run build:prod

## h5打包

若要修改运行基础路径，修改manifest.json-web配置-运行基础路径

目前基础路径为/urbanplus/

utils/request 设置请求baseurl