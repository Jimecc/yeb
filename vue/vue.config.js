
let proxyObj={}

proxyObj['/']={

    // websocket
    ws:false,

    // 目标地址
    target:'http://110.40.191.51:8081',
    // target:'http://127.0.0.1:8081',

    // 发送请求头 host 会被改成 target
    changeOrigin:true,

    //不重写请求地址
    pathRewrite: {
        '^/': '/'
    }


}




module.exports={
    devServer:{
        host:'localhost',
        port:8080,
        proxy: proxyObj
    }
}
