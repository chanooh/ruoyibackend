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