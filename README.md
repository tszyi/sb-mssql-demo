# 拉G

Trash app

## 建置

1. docker build -t sb-crud-demo .
2. docker run -d -p 18787:8080  --name sb-crud-demo sb-crud-demo
3. cd installdb
4. docker build -t demo-mssql .
5. docker run -d -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=c8763" -p 1433:1433  --name demo-mssql demo-mssql

> 資料庫已建立好隨後API要操作的表
>
> 表名為 Inventory，欄位如下
>
> id| name | quantity

## 使用API

- C：`http://localhost:18787/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433&name={name}&quantity={quantity}`
- R：`http://localhost:18787/api/mssql-crud-example/inventories?ip={你的IP}&port=1433`
- U：`http://localhost:18787/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433&name={name}&quantity={quantity}`
- D：`http://localhost:18787/api/mssql-crud-example/inventories/{id}?ip={你的IP}&port=1433`