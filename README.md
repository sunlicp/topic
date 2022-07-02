# topic

#### 介绍
算法海洋（刷题）。topic项目是一套在线刷题系统，是一个前后端分离的项目。前端采用VUE+elementUI实现，后端则采用SSM实现，部署则采用的是Docker容器化。主要功能有查看文章，发表文章，收藏文章等等。

#### 软件架构
软件架构说明

#### 使用说明

#### Linux部署

> Linux部署肯定是先基于win操作的前提下获取war以及dist，再进行操作的，之所以Linux在先是大部分小伙伴都会自行就启动起来(当然下面通过了win的操作步骤)

**Linux环境需求**

~~~tex
docker 
docker-compose
nginx
~~~

**部署步骤**

1.分别将前后端项目打包(war、dist)

2.将dist丢到nginx里，并配置nginx

3.修改Dockerfile以及docker-compose.yml文件（文件已提供）

4.执行docker build -t topic:1.0 .

5.docker images 查看镜像是否生成

6.docker-compose up (查看是否启动成功，成功后可后台启动  -d)

7.查看并登录   

实现

#### windows部署

**windows环境需求**

```tex
JDK = 1.8
MySQL >= 8.0
Maven >= 3.0
Node.js
```

**windows工具需求**

```tex
IntelliJ IDEA
tomcat服务器
MySQL图形化操作工具
```

- JDK = 1.8 

  在官方网站上下载1.8版本中的最新版，下载完成后安装即可。

  下载地址：https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

  ```
  #环境变量配置(现在默认会自动配置好)
  略
  
  #打开CMD命令行工具，检查是否配置成功
  java -version
  ```

- Maven >= 3.0

  从Maven官方网站上下载最新版的压缩包。

  下载地址：https://maven.apache.org/download.cgi

  ```
  #环境变量配置
  略
  
  #打开CMD命令行工具，检查是否配置成功
  mvn -v
  ```

- Mysql >= 8.0 

  安装教程：https://www.runoob.com/mysql/mysql-install.html

  下载地址：https://dev.mysql.com/downloads/mysql/

  访问Mysql的可视化数据库开发工具： Navicat Premium      工具的安装教程可自行百度搜索。

- tomcat

  安装教程：https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123891067?spm=1001.2014.3001.5501

- Node.js

  安装教程：https://www.runoob.com/nodejs/nodejs-install-setup.html

**后端启动步骤**

1. 创建数据库
2. 导入项目
3. 配置tomcat并启动tomcat
4. 启动成功

> 温馨提示：
>
> 1.sql文件已提供，只需执行即可。`topic.sql`
>
> 2.将项目导入idea(本项目使用的工具是idea，如果你用的ecplise一样可以使用，毕竟百度无所不能)
>
> 3.启动之前记得修改连接数据库的配置
>
> 4.tomcat端口指定好，不要和vue启动的端口冲突了，并且记录好，稍后启动前端项目的时候需要配置上

**前端启动步骤**

```bash
# 打开windows命令行窗口，进入项目目录
cd 路径/topicUI

# 强烈建议不要用直接使用 cnpm 安装，会有各种诡异的 bug，可以通过重新指定 registry 来解决 npm 安装速度慢的问题。
npm install --registry=https://registry.npm.taobao.org

# 启动项目
npm run dev
```

打开浏览器，输入：http://localhost:8080 （默认账户/密码有两个 `admin/1`）

>若能正确展示登录页面，并能成功登录，菜单及页面展示正常，则表明项目启动成功

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
