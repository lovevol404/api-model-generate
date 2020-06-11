/*TestApiBody.java
Copyright 2011-2020 Qunhe Tech, all rights reserved.
Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.

@Author: tianming
@created: 2020/6/11
*/

/**
 * @author tianming
 */
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

