# 云E办 (yeb)
-----
此项目是来自 Bilibili 的前后端分离式项目（后端部分），目前正在根据课程编写代码

<br>
<br>

# 启动项目方式：
-----
1. 在 Navicat 或其他数据库可视化界面中新建`查询`并运行 `sql.yeb.sql` 文件
修改`java.resources.application.yml`文件中的相关内容后，启动 YebApplication 就可以。
主要修改内容包含：
- 各类密码（主要为MySQL 数据库、Redis 等）
- 如果您的 MySQL 版本为 5.7 （或更早），请删除数据库驱动中的`.cj`
 
<br>
<br>


# 其他<font color="#ffaabb">注意事项</font>
Maven 中的`fastdts`无法自动下载，具体解决方法可参考：https://blog.csdn.net/Baiguoguo/article/details/121022718


