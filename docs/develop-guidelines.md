## 开发规范

- [关于lombok使用](../docs/lombok.md)
- 禁止 使用 [Spring-Validation](../docs/Spring-Validation.md) (外部参数特别多，校验特别繁琐的项目除外)
- 禁止 方法返回 null(null表示特殊含义时除外)。  
  - 返回类型是collections或Array, 返回空集合而不是null, 通常工具包中已经有这类封装, 如下代码就说hutool中的封装;  
    ```java
    public static <T> List<T> emptyIfNull(List<T> set) {
        return null == set ? Collections.emptyList() : set;
    }
    ```
  - 返回类型不是collections, 使用Java8或者guava lib中提供的Optional类来包装返回对象, 禁止用Optional类包装collections。
- POJO类属性使用包装数据类型，局部变量使用基本数据类型
- POJO类布尔类型属性, 都不要加is, 否则部分框架(RCP)解析会引起序列化错误
- POJO类必须有setter、getter、ToString
- 慎用阿里json包FastJson, 存在很多Issues, json格式大一点的对象时容易栈溢出
----------------------
- MyBatis 多条件查询时应该使用动态sql。 禁止使用 where 1 = 1, 它会使索引失效,进行全表扫描; 还存在sql注入的风险。
- 迭代entrySet() 获取Map 的key 和value  
  当循环中只需要获取Map 的主键key时，迭代keySet() 是正确的；  
  但是，当需要主键key 和取值value 时，迭代entrySet() 才是更高效的做法，其比先迭代keySet() 后再去通过get 取值性能更佳。
  ```java
  HashMap<String, String> map = new HashMap<>();
  
  //Map 获取value 反例:
  for (String key : map.keySet()){
    String value = map.get(key);
  }
  
  //Map 获取key & value 正例:
  for (Map.Entry<String,String> entry : map.entrySet()){
    String key = entry.getKey();
    String value = entry.getValue();
  }
  ```
- 使用Collection.isEmpty() 检测是否为空集合
- 初始化集合时尽量指定其大小, 如HashMap 使用 HashMap(int initialCapacity) 初始化  
  ```text
  initialCapacity = (需要存储的元素个数 / 负载因子) + 1。注意负载因子（即 loader factor）默认
  为 0.75，如果暂时无法确定初始值大小，请设置为 16（即默认值）。
  ```
- 使用StringBuilder 拼接字符串
- 若需频繁调用Collection.contains 方法则使用Set
- 使用静态代码块实现赋值静态成员变量
- 删除未使用的局部变量、方法参数、私有方法、字段和多余的括号。
- 工具类中屏蔽构造函数(构造私有化)
- 删除多余的异常捕获并抛出
- 字符串转化使用String.valueOf(value) 代替 " " + value
- 避免使用BigDecimal(double)
- 优先使用常量或确定值调用equals 方法
- 枚举的属性字段必须是私有且不可变
- string.split(String regex)部分关键字需要转译

