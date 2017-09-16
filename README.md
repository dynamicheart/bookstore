# OnlineBookstore
a homework for SE228

### Overview
- iteration1使用了php作为后端的语言，没有使用任何框架。前端管理员界面用的是easy-ui, 购书网站界面使用的是bootstrap.
- iteration2后端使用了spring mvc，前端管理员网页用的是angularJs的框架，尝试前后端分离，做得不是很好。
- iteration3属于iteration4未完成版本，请忽视。
- iteration4的架构请看下面的描述。

### 采用的技术
- Spring Boot
- Spring Data Jpa
- Hibernate
- Gridfs
- Apache Tiles

### 架构
- 采用MVC的模式，但是分为两个module, core为底层module，存放model、repository和service层，属于M的部分， store为V和C的部分，存放controller层和jsp层。
- 为什么分为三层两大块的模式？ 因为store模块是依赖于core模块的，将core分离出去有利于复用。

### model设计说明
- 本次设计将各个entity的描述性属性与关键属性分离，分离出description，用于存放name等内容，这样做的好处是，使得每种entity都可以有多种语言的描述，实现了与语言环境的解耦。

### Spring Data Jpa 和 hibernate
- 使用spring data jpa配合EntityManager，可自动扫描并映射model，不再需要hbm映射文件.
- repository层或者说dao层，继承JpaRepository，便有了基本的增删改查功能，不需要自己写。
- 定义接口并使用注解@Query便可以实现复杂的查询功能，不需要自己写实现就能使用，方便快捷。

### MongoDB 和 Gridfs
- 此次项目使用了mongoDB中的gridfs，用于存放书籍封面和用户头像。本次考虑的重点并不是nosql灵活的数据格式，而是考虑到nosql具有很好的拓展性，而且没有各种约束，适合分布式大数据存储。图片等多媒体资源相对于用户的普通数据来说，数据量大，无论是存放在sql中还是文件系统中，都不利于以后数据的移植和拓展。
- 此次项目将gridfs做成了一个cms内容管理系统，每份文件都有自己唯一的资源ID,无论是用户头像还是书籍封面的图片资源，都跟某一资源Id所关联。

### Apache Tiles
- 这个库让开发者定制自己的view模板，定制自己的layout后（如navigation、header和footer），开发者可将任意的view放到这个layout中，增加了复用度。


### 购物车
- 此次项目购物车是存放在session中的，相当于一个暂存的数据。但是更严谨的做法是，用户未登录时放到session中，登录之后，将数据库中的购物车和session中的购物车合并，再持久化到数据库中。


### 销售统计
- 考虑到查询销售统计所得到的结果是一些非常小的值，所以并不需要从数据库获取大量数据再拿回来处理，而是在数据库中调用储存过程得到返回值再返给服务器，减轻服务器和数据库之间的通信压力。


### get started
- 在core module的resource中根据本机电脑的配置，修改database.properties，在数据库中新建一个数据库，名字叫bookstore。
- 安装gradle，在根目录下，运行gradle bootRun，当系统启动之后，找到core resource中的sql文件夹，在boostore数据库中，运行statistics_function.sql脚本。
- 初始化管理员账号:admin，密码:password 管理员界面的根url：/admin
- store界面的根url： /
