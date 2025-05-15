## 城市品质提升说明

主要包含以下两部分：

- **ruoyi-admin**：后台管理接口 & 小程序接口
- **ruoyi-ui**：后台管理系统前端 UI

---

## 后台 UI 打包（ruoyi-ui）

1. **设置路由基础路径**  
   若需要则修改文件：`/src/router/index.js`
   目前的部署需要改为： `/urbanplusadmin/`


```
export default new Router({
  mode: 'history',
  base: '/urbanplusadmin/', //打包
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
```


2. **执行打包命令**
   ```bash
   npm run build:prod

---

## 配置 

阿里云oss：application.yml