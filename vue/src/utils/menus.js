import {getRequest} from "./api";

export const initMenu = (router,store)=>{
    if(store.state.routes.length>0){
        return;
    }
    getRequest('/menu/menu').then(data=>{
        if(data){
            // 格式化好的 Router
            let fmtRoutes = formatRoutes(data);
            // 添加到路由
            router.addRoutes(fmtRoutes);
            // 数据存到 vuex
            store.commit('initRoutes',fmtRoutes);
        }
    })
}


export const formatRoutes = (routes)=>{
    let fmtRoutes = [];
    routes.forEach(router=>{
        let{
            path,
            component,
            name,
            iconCls,
            children,
        } = router;
        if(children && children instanceof  Array){
            // 递归
            children = formatRoutes(children);
        }

        let fmRouter = {
            path:path,
            name:name,
            iconCls:iconCls,
            children:children,
            component(resolve){
                require(['../views/'+component+'.vue'],resolve);
            }
        }
        fmtRoutes.push(fmRouter)
    })
    return fmtRoutes;
}
