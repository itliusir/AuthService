# 授权服务（开发中ing 如有兴趣可以一起学习 +Q571624628）#


== 技术栈
- jdk8
- spring-boot
- spring-security
- spring-jpa
- mysql
- ehcache
- OAuth
- jwt
- SpringCloud

== 如何开始

- `git clone https://github.com/itliusir/AuthService.git`
- 修改配置文件 `src/main/resources/application.yml` 里的配置项
- 创建数据库 `auth_database`
- 运行 `AuthServiceApplication.java` 里的 `main` 方法
- 启动项目后表是自动创建的，然后将 `auth_database.sql` 导入数据库
- 浏览器 http://localhost:8080/
- 默认帐户 `admin` `123456`

== 打jar包运行

- `git clone https://github.com/itliusir/AuthService.git`
- 修改配置文件 `src/main/resources/application.yml` 里的配置项
- 创建数据库 `auth_database`
- 运行 `mvn clean package -Dmaven.test.skip=true` 命令，生成jar包，位置在 `target/AuthService.jar`
- 运行 `java -jar AuthService.jar` 即可启动服务
- 启动项目后表是自动创建的，然后将 `auth_database.sql` 导入数据库
- 浏览器 http://localhost:8080/
- 默认帐户 `admin` `123456`