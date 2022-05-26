# 云E办 ( yeb )
-----
此项目来自Bilibili，视频地址：https://www.bilibili.com/video/BV1ff4y1M7XK?p=1
![示例图](https://files.catbox.moe/vme765.png)
![邮件示例图](https://files.catbox.moe/t5sjdm.png)


### 主要技术栈
#### 后端技术栈
![后端技术栈](https://files.catbox.moe/72gk5j.png)
- Springboot
- Redis
- Jwt
- Security
- MyBatis-Plus
- 数据库存储过程


<br>

#### 前端技术栈
- Vue
- css
- Axios


<br>
<br>


# 后端启动的注意事项
-----
- 安装 rabiitmq 并启动（ Macbook 可以直接用 brew 进行安装）
- 安装 redis 并启动
- 申请网易/QQ 邮箱并申请开通 SMTP 后，将 username@163.com 以及 SMTP 密码填写到 yeb-mail 项目下的 application.yml 文件中
- 使用 sql 文件初始化数据库
- 安装 Maven，并下载相关依赖
- 先启动 yeb-server 项目，后启动 yeb-mail 项目


-----

 
<br>
<br>


# 其他<font color="#ffaabb">注意事项</font>
Maven 中的`fastdts`无法自动下载，具体解决方法可参考：https://blog.csdn.net/Baiguoguo/article/details/121022718


