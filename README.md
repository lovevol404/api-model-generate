## 功能
把后端接口类型处理成结构化的描述，在适当的地方告知前端，减少沟通成本。

我们有如下 Java 实体类：
```java
public class TestApiBody {
    @ApiInfo(value = "这是String的描述")
    private String string;
    @ApiInfo(value = "这是Double的描述")
    private Double aDouble;
    @ApiInfo(value = "这是Integer的描述")
    private Integer integer;
    @ApiInfo(value = "这是Float的描述")
    private Float aFloat;
    @ApiInfo(value = "这是Character的描述")
    private Character character;
    @ApiInfo(value = "这是Boolean的描述")
    private Boolean aBoolean;
    @ApiInfo(value = "这是int的描述")
    private int annt;
    @ApiInfo(value = "这是double的描述")
    private double adouble;
    @ApiInfo(value = "这是float的描述")
    private float afloat;
    @ApiInfo(value = "这是char的描述")
    private char achar;
    @ApiInfo(value = "这是boolean的描述")
    private boolean aboolean;
    @ApiInfo(value = "这是引用TestApiBody的描述")
    private TestApiBody self;
    @ApiInfo(value = "这是TestEnum的描述")
    private TestEnum testEnum;
}

enum TestEnum{
    @ApiInfo(value = "这是A的描述")
    A,
    @ApiInfo(value = "这是B的描述")
    B,
    @ApiInfo(value = "这是C的描述")
    C,
    @ApiInfo(value = "这是D的描述")
    D;
}
```
经过工具类转换之后以Json输出显示：
```json
{
  "fieldInfos": [
    {
      "desc": "这是String的描述",
      "exampleValue": "string",
      "name": "string",
      "type": "String"
    },
    {
      "desc": "这是Double的描述",
      "exampleValue": 0,
      "name": "aDouble",
      "type": "Double"
    },
    {
      "desc": "这是Integer的描述",
      "exampleValue": 0,
      "name": "integer",
      "type": "Integer"
    },
    {
      "desc": "这是Float的描述",
      "exampleValue": 0,
      "name": "aFloat",
      "type": "Float"
    },
    {
      "desc": "这是Character的描述",
      "exampleValue": 0,
      "name": "character",
      "type": "Character"
    },
    {
      "desc": "这是Boolean的描述",
      "exampleValue": false,
      "name": "aBoolean",
      "type": "Boolean"
    },
    {
      "desc": "这是int的描述",
      "exampleValue": 0,
      "name": "annt",
      "type": "int"
    },
    {
      "desc": "这是double的描述",
      "exampleValue": 0,
      "name": "adouble",
      "type": "double"
    },
    {
      "desc": "这是float的描述",
      "exampleValue": 0,
      "name": "afloat",
      "type": "float"
    },
    {
      "desc": "这是char的描述",
      "exampleValue": 0,
      "name": "achar",
      "type": "char"
    },
    {
      "desc": "这是boolean的描述",
      "exampleValue": false,
      "name": "aboolean",
      "type": "boolean"
    },
    {
      "desc": "这是引用TestApiBody的描述",
      "exampleValue": "ref#TestApiBody",
      "name": "self",
      "type": "TestApiBody"
    },
    {
      "desc": "这是TestEnum的描述",
      "exampleValue": [
        {
          "desc": "这是A的描述",
          "value": "A"
        },
        {
          "desc": "这是B的描述",
          "value": "B"
        },
        {
          "desc": "这是C的描述",
          "value": "C"
        },
        {
          "desc": "这是D的描述",
          "value": "D"
        }
      ],
      "name": "testEnum",
      "type": "TestEnum"
    }
  ],
  "refs": [],
  "type": "TestApiBody"
}
```
