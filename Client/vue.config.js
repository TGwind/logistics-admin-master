
module.exports = {
    // 执行 npm run build 统一配置文件路径（本地访问dist/index.html需'./'）
    // NODE_ENV：Node.js 暴露给执行脚本的系统环境变量。通常用于确定在开发环境还是生产环境
    // publicPath: '/',
    publicPath: process.env.NODE_ENV === 'production' ? '' : '/',
    outputDir: 'studentManager', // 输出文件目录
    // assetsDir: 'static', // 放置静态资源
    // indexPath: 'index.html', // 可以不设置一般会默认
    // filenameHashing:true, // 文件命名
    lintOnSave: false, //设置是否在开发环境下每次保存代码时都启用 eslint验证

    // 跨域配置
    devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
        port: 7866,               //当前vue项目的启动端口
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                target: 'http://localhost:8888',     //代理的目标地址，后端项目的ip
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '^/api': ''                     //选择忽略拦截器里面的内容
                }
            }
        }
    },

}

