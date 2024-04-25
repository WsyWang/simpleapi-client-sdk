# Simple API接口开放平台SDK

## 使用方法

1. 下载源码到本地

2. 用IDEA的maven或本地maven命令`maven install`安装到本地

3. 从项目中引入maven坐标

4. 在`application.yml`或`application.properties`中配置自己的ak、sk

   1. ``` yml
      SimpleClient:
      	accessKey: xxxxx
      	secretKey: xxxxx
      ```

   2. 在项目需要调用接口的地方引入SDK

   3. 调用SDK中的接口方法即可
