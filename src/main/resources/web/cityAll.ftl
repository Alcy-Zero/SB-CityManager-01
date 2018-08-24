<!DOCTYPE HTML>
<html>


<body>

<h3>城市列表</h3>

    <#list list as city>
        id：${city.id}            <br/>
        name：${city.cityName}    <br/>
        省份: ${city.provinceId}  <br/>
        描述: ${city.description} <br/>
        <br/>
    </#list>

</body>
</html>