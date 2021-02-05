## 开发规范

- 在控制器类上或者方法体上加上@ResponseResult注解, 就可以实现统一结果封装

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
- 尽可能进行单元测试(service)

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

---

- 避免在程序中使用魔鬼数字，必须用有意义的常量来标识。
- 明确方法的功能，一个方法仅完成一个功能。
- 方法参数不能超过5个
- 方法调用尽量不要返回null，取而代之以抛出异常，或是返回特例对象（SPECIAL CASE object，SPECIAL CASE PATTERN）；对于以集合或数组类型作为返回值的方法，取而代之以空集合或0长度数组。
- 在进行数据库操作或IO操作时，必须确保资源在使用完毕后得到释放，并且必须确保释放操作在finally中进行。
- 异常捕获不要直接catch (Exception ex) ，应该把异常细分处理。
- 对于if „ else if „(后续可能有多个else if …)这种类型的条件判断，最后必须包含一个else分支，避免出现分支遗漏造成错误；每个switch-case语句都必须保证有default，避免出现分支遗漏，造成错误。
- 覆写对象的equals()方法时必须同时覆写hashCode()方法。
- 禁止循环中创建新线程，尽量使用线程池。
- 在进行精确计算时(例如:货币计算)避免使用float和double，浮点数计算都是不精确的，必须使用BigDecimal或将浮点数运算转换为整型运算。

---

- 捕获异常, 使用try-with-resource语句代替 Finally 块释放资源。
- 异常处理时指定具体的异常, 首先捕获最具体的异常
- 申明异常时进行文档说明
- 不要捕获Throwable, Throwable是所有异常和错误的父类。
- 不要记录并抛出异常, 容易同一异常，重复记录
  ```java
  try {
    new Long("xyz");
  } catch (NumberFormatException e) {
    log.error(e);
    throw e;
  }
  ```
- 包装异常时不要抛弃原始的异常, 一定要把原始的异常设置为cause(Exception有构造方法可以传入cause)。否则，丢失了原始的异常信息会让错误的分析变得困难。
- 如果不打算处理异常，请使用finally块而不是catch块
  ```java
  try {
    someMethod();
  } finally {
    cleanUp();    
  }
  ```

--- 
- 方法只使用一级缩进, 方法内没有嵌套的 if/switch/for/while 等关键字
- 拒绝使用 else 关键字, 三目表达式不能嵌套超过两级
- 一行只能有一个“.”运算符
- 使用 Assertions 类避免冗长的 if-else 检查
- 不要使用Java assert 关键字，因为它可能会被禁用。
- 生产线环境为Java8或以上版本时, 使用Java 8 有日期和时间API(主包 java.time), 禁止使用java.util包的日期和时间API
  ```text
  Java 8 的所有日期和时间API都是不可变类并且线程安全，而现有的Date和Calendar API中的java.util.Date和SimpleDateFormat是非线程安全的。
  ```



