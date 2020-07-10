# Spring boot with SQL server CRUD demo

連飯粒都算不上的範例

## 用內嵌湯姆貓運行

### 建置 docker img

1. `docker build -t sb-crud-demo .`
2. `cd installdb`
3. `docker build -t demo-mssql .`

> 資料庫已建立好隨後API要操作的表
>
> 表名為 Inventory，欄位如下
>
> id| name | quantity

### 使用服務

1. `docker run -d -p 18787:8080  --name sb-crud-demo sb-crud-demo`
2. `docker run -d -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Password@1234" -p 1433:1433  --name demo-mssql demo-mssql`

- C：`http://localhost:18787/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433&name={name}&quantity={quantity}`
- R：`http://localhost:18787/api/mssql-crud-example/inventories?ip={你的IP}&port=1433`
- U：`http://localhost:18787/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433&name={name}&quantity={quantity}`
- D：`http://localhost:18787/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433`

## 用 Wildfly

### 建置 docker img

`docker build -t fly-sb -f Dockerfile_wildfly .`

### 使用服務

`docker run -p 18787:8080 -p 9990:9990 -d --name fly-sb fly-sb`

- 測試是否活著：`http://localhost:18787/springboot-demo-0.0.1-SNAPSHOT/api/alive`，成功會輸出 `I am tim huang, a handsome guy.`。
- C：`http://localhost:18787/springboot-demo-0.0.1-SNAPSHOT/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433&name={name}&quantity={quantity}`
- R：`http://localhost:18787/springboot-demo-0.0.1-SNAPSHOT/api/mssql-crud-example/inventories?ip={你的IP}&port=1433`
- U：`http://localhost:18787/springboot-demo-0.0.1-SNAPSHOT/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433&name={name}&quantity={quantity}`
- D：`http://localhost:18787/springboot-demo-0.0.1-SNAPSHOT/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433`

## 待改進

- Image size too large
- All codes needs improved, especially DAO class
- Configuration needed

## Reference

- [Wildfly 官方docker hub](https://hub.docker.com/r/jboss/wildfly/)
- [部署sb應用到wildfly法一](https://dzone.com/articles/deploying-spring-boot-app-to-jboss-wildfly)
- [部署sb應用到wildfly法二](https://medium.com/swlh/how-to-deploy-spring-boot-application-in-wildfly-application-server-b3670c031ad4)