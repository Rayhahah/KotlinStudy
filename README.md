
# Kotlin在Android基础篇

@(kotlin)

## 基本简述
>JetBrain开发，亲儿子系列


1. JVM上运行的语言，100%支持和替换java
2. 减少冗余代码，简洁的书写，就很爽
3. 空指针的完美处理，findViewById的解脱
4. 可以编译成JavaScript

## Tips
1. 字符串模板
2. 善用表达式，`if...else`和`when`都是可以作为返回值
3. 参数默认值，实现方法的重载
4. 拓展函数与拓展属性
5. 懒初始化 ： 因为kotlin中的属性都必须初始化
	- `lateinit`对应`var`
	- `lazy` 对应`val`
6. 局部函数抽取重复代码
7. 数据类实现model类
	- 关键字 `data`
8.  接口委托来实现装饰器模式
	- 关键字`by`
9.  `Lambda`表达式
10.  善用高阶函数来简化代码清晰逻辑
	- `apply()`
	- `use()` : 简化代码，`close`
	- `fold()`
	- `map`/`flatmap`
11.  空安全
	- 关键字 `?`
	- `let`函数
	- `?:` 表示对象空值时候的处理
12.  运算符重载
	- 关键字 `operator`

## Kotlin在AS开发环境配置
1. Plugin中安装插件`Kotlin`和`Kotlin Extensions`
2. `ext.kotlin_version = '1.0.4'`  :  相当于版本变量，可有可无，方便一点吧
3. `classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"` :  classPath不多说
4.  `apply plugin: 'kotlin-android'` 和  `apply plugin: 'kotlin-android-extensions'` :  kotlin的引用
5.  `compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"`  :  kotlin的jar文件依赖
6.  `compile "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"`  ： kotlin的反射包的依赖

总体：

```
//project gradle
buildscript {
    ext.kotlin_version = '1.0.4'
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


//app gradle
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    ...
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    ...
    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
}
```

## 基础语法
### 基本类型

- Boolean : 不多说
- Number :
	- 浮点型
		- Double ：64位
		- Float : 32位
	- 整型
		- Long : 32位
		- Int : 16位
		- Short : 8位
	- 字节
		- Byte : 8位
	- NaN : NaN不等于NaN，表示非数字
- Char :`Character`
	- 两个字节，16位Unicode字符
	- 单引号 `'a'`
- String : 字符串
	- `==` 可以直接使用等于`equals`
	- `===`比较对象引用值
	- 字符串模板 ： `${a.getName()}`
	- 三个引号就是支持多行字符串
-  数组与区间
	- 使用具体类型的数组来避免装箱和拆箱来提升效率
	- 空区间包含一切东西，也就是返回都是true


不可隐式转换 ： `var aLong:Long=aInt.toLong()`,必须要显示调用
类型推导 ：非必须要声明类型


### 常量与变量
- val : 表示常量，也就是`final`，效率会提高，减少不必要的损耗
	- 和`final`不同的是，`val`是运行期变量，`final`是编译期
	- `const val`才表明是编译期常量
- var ：表示变量
### 类成员
- `get`和`set`不多说了
- `lateinit`表示可以不需要初始化，以后再初始化，必须指定类型
	- `val`不能使用
- `val x:String by lazy{()->String}` : 调用的时候才会去初始化，传入一个无参数的`Lambda`表达式

Tips:
- 尽量在构造方法中初始化
- 如果无法初始化，就尝试降级为局部变量

#### 属性代理
`lazy`就是属性代理的一个应用
类似于运算符重载

赋值：
- `operator <T>fun setValue(thisRef: Any?, property: KProperty<*>, value: T):Unit`
	- `thisRef` : 代理属性这个类的引用
	- `property` : 当前代理的这个属性
获取值：
- `operator <T>fun getValue(thisRef: Any?, property: KProperty<*>): T`
	- `thisRef` : 代理属性这个类的引用
	- `property` : 当前代理的这个属性
	- 返回的就是这个代理属性最终的赋值



### 类与对象
- open : 表示继承
- abstract : 抽象
- is ： `p1 is People` 和`instanceof` 用法一致，判断是否是实例
- init ： 初始化时的操作
- data ； 数据类

#### 抽象类接口
- 类继承`:A()`,实际上是实现了父类的构造方法
- `open`的才可以继承与复写
	- 接口，接口方法，抽象类默认就是`open`的
- `override`关键字是必须要写的
- `interface`
	- 可以定义变量
	- 可以实现方法
- 属性也可以是复写的
- `by` ,接口委托给具体对象执行，我们只需要实现我们需要部分
- `super<A>.funX()`,使用泛型`super`来解决两个接口当中的`fun`名称冲突的问题
	- 签名和返回值需要一致


可见性：
- `public` : 默认是公开的
- `private` : 都不可见
- `protected` : 子类可见
- `internal` : 模块内可见


#### object
一种特殊的`class`，可以保证只有一个对象，也就是一种简单的单例实现
- 但是并不推荐，原因请看单例模式

#### 伴生对象
- 关键字`companion object` ： 每一个类对应这一个伴生对象
	- 在其内部定义的方法或者属性，就可以同Java静态成员一样的访问
- 对于Java中要使用Kotlin中的伴生对象内的成员，需要使用注解`@JvmField` 和`@JvmStatic`
	- 其实不使用也没关系，只不过需要变成这样`a.companion.min()`

- 尽可能使用包级函数来代替`静态方法`

#### 数据类
- 构造函数中的属性必须指定为`var`或者`val`
- 自动生成`get`、`set`方法
- `component1`...这些是根据属性个数自动生成的
	- `var (index,value,name)=dataBean`，这样方式产生的对象就使用到了`component`属性
	- 最佳实践：`withIndex()`返回的就是一个`data class`对象，运用的就是`component`属性

```
  for ((index, value) in arrayOfInt.withIndex()) {
            l("index=$index,value=$value")
        }
```
- 默认编译成`final`的class
	- 使用插件`allOpen`
- 没有无参的构造方法
	- 使用插件`noArg`


#### 内部类

Java中内部类默认持有外部类的引用(内存泄漏)，当标记为`static`时才会独立出来

Kotlin中默认不持有外部类引用，当标记为`inner`时才会去持有外部类引用
- 使用`this@OuterClass.a`来获取外部类的属性
- `object : Outer.OnClickListener{}` 这样的方式来实现匿名内部类，本质上是一个匿名`object`实现接口，同时也支持对类的继承，如果只是简单的接口回调，建议使用`匿名函数`即可

#### 枚举与密封类
枚举类：基本与Java中一致
枚举类的构造方法是`protected`同时也是`final`这就早就了，枚举类只能在其内部创建对象的特性。

密封类 ： 子类有限的类，可以有效保护安全性
只能在相同的文件中继承密封类，原理就是构造方法被`private`了


### 重载与重写
- `@JvmOverloads`，可以让Java代码也享受`Kotlin`带来的重载体验

### 泛型
- 协变：
	- 父类出现的地方，可以用子类代替
- 逆变：
	- 子类出现的地方，可以用父类代替
- 不变：
	- 声明的是什么类型，使用或传递的就要是什么类型


Kotlin中数组：
泛型：不变
也就是数组中声明的泛型是什么，就用什么

- `in` : 就是逆变
	-  使用 `in` 符号修饰参数类型，表示只能被用作方法的参数，被操作，而不能作为返回值使用
	-  当形参使用 in 修饰符进行限定时，表示，该参数接受指定参数类型或其父类，并且是可被赋值的
- `out` : 就是协变
	-  使用 `out` 符号修饰参数类型，表示只能被用作方法的返回值
	-  作为形参使用的时候，该泛型参数只能是Any类型或其子类
```
/**
 * 作为声明类型的时候
 * in ： 表示只能作为参数传入
 * out : 表示只能作为返回值返回
 */
interface Eating<in T, out E> {
    fun eat(shit: T): E
}



fun testGenericity() {
    var phoneArray = arrayOf<Phone>()
    var chinaPhoneArray = arrayOf<ChinaPhone>()
    var miPhoneArray = arrayOf<MIPhone>()

// 编译报错，提示需要Array<Any>,而参数是Array<String>类型


    /**
     *编译不通过，basicArray必须是Phone类型
     */
//    copyGener(chinaPhoneArray, miPhoneArray, phoneArray)
    /**
     *编译不通过，outArray必须是ChinaPhone的子类或者本类
     */
//    copyGener(phoneArray, phoneArray, phoneArray)
    /**
     *编译不通过，inArray必须是ChinaPhone的父类或者本类
     */
//    copyGener(phoneArray, miPhoneArray, miPhoneArray)

    /**
     * 编译通过
     */
    copyGener(phoneArray, miPhoneArray, phoneArray)
}


/**
 * 形参的in和out
 * 无表示就表示必须是当前参数类型才可以
 * in ： 表示传入参数必须是  类型的本类或者父类
 * out : 表示传入参数必须是 类型的本类或者子类
 */
fun copyGener(basicArray: Array<Phone>, outArray: Array<out ChinaPhone>, inArray: Array<in ChinaPhone>) {
}

```


```
fun <T> singletonList(item: T): List<T> { 
    // ...
}

val l = singletonList<Int>(1)
val s = singletonList(1)//泛型类型自动推断

//泛型约束类，和Java的<T extends Object>一样的用法
//通过where,可以指定多个上边界
fun <T> cloneWhenGreater(list: List<T>, threshold: T): List<T> 
    where T : Comparable,
          T : Cloneable {
    return list.filter { it > threshold }.map { it.clone() }
}
```


### 空安全类型和智能转换
在声明变量类型或者返回类型的后面加上`?`，表示可以返回`null`
在对象调用的时候加上`?.getXXX()`,表示对象不为`null`则调用，否则整体输出`null`
在对象调用的时候加上`!!.getXXX()`,表示忽略空安全，强行执行，可能会出现空指针异常
`?:` : 表示当对象为null的情况下执行的语句
- `person?.eat()?:println("person is null")` ： 这段代码就很好理解了


智能转换
当判断一个对象属于一个类型的时候，就可以直接使用该子类的方法了，而不需要再去转换类型



### Lambda表达式
>也就是匿名函数，的一种简写

- 类型 ： `()->Unit`具体看具体的使用了
	- `()->Unit` : 无参数，无返回值的匿名函数
	- `(Int) -> Int` : 参数为一个`Int`类型，返回值为一个`Int`类型的函数
	- `(Int,(String)->String) -> String` : 传入两个参数，一个`Int`类型，一个`Lambda`表达式类型，返回值是`String`类型
- 调用，使用`()`调用，等价于`invoke()`
	- `val sum={a:Int,b:Int -> a+b}`
	-`sum(1,2)`或者`sum.invoke(1,2)`
- 最佳的理解实践就是`foreach`
		- 传入一个`Lambda`表达式作为参数，`Lambda`表达式接收的参数就是`Array`迭代的元素
- `Lambda`表达式绝不是函数，在它里面`return`会直接退出当前函数，需要使用`标签`
	- `array.forEach f@{ print(it)   return@f}`
- 当 函数类型，是参数类型，或者包含其中就可以直接引用
	- `fun println(msg:Any?){}` 类型： `(Any)->Unit`
	- `fun forEach(target:(T)-Unit)`
	- 所以上面的类型`(Any)->Unit`就是`(T)->Unit`是等价的，所以可以如下使用引用
	- `arrys.forEach(::println)`

Tips:
- 最后一行作为返回值，也可以没有返回值
- Lambda是最后一个参数就可以从小括号中移出去
- 参数只有一个`Lambda`表达式，小括号就可以去掉咯
- 在Kotlin里面，函数最多的参数就是22个
	- 具体每个函数都有其父类对应的参数个数的`FunctionX`


### 运算符、表达式
对应运算符有很多如：`+`,`()`,`/`等，我们可以使用`operator`关键字来重载运算符对应的方法
重载注意事项
- 方法名要一致
- 参数个数要一致
- 参数类型可变
- 返回值类型可变


`infix` ： 中缀表达式，可以实现自己的运算符
```
infix fun on(str:String) :Boolean{
return str.size()>3
}

调用：b就作为参数进入了
a on b
```

表达式都是有返回值的，这个需要注意，都是最后一行作为返回值

- `if...else`
- `when`
- `try..catch`


---
## 高阶函数
### 基本概念
>高阶函数：传入或者返回函数的函数

- 函数引用：`::println`
	- 包级函数 ：直接引用即可
	- 拓展函数 ：`String.print()` 实际上是`print($receiver:String)`，这样的形式，所以需要额外注意，引用写法：`String::print()`
	- 类函数 ： 与拓展函数类似，注意编译后的函数第一个参数是类本身
	- 对象函数 ： 需要先初始化对象，然后`a::print()`即可，Kotlin 1.1以后才开始支持
### 常见高阶函数
- `forEach` :
- `map` : 遍历集合数组
	- 传入`Lambda`表达式，最后一行返回，元素
	- 整体`map()`返回最终形成的集合
- `flatmap` :  和Map类似，只不过每次迭代返回的是一个Iterable
- `reduce` : 返回一个集合，
	- `acc` : 上一次的结果
	- `i` : 本次迭代的元素
	- 最佳实践 ： 阶乘
	- 注意 ： acc的类型必须是i的父类
- `fold` : 等于是有初始值的`reduce`
	- 注意 ： 初始值的类型与acc是一致的，且没有任何限制
	- 可以完全替代`reduce`
- `filter` : 返回true的元素就加入到最终的集合当中
- `takeWhile` :遇到第一个return false的情况就中断
	-  返回前面元素构成的集合
- `let` : 调用者在内部，就可以避免了繁琐的  `?.`
- `apply` : `fun <T> T.apply(block: T.() -> Unit): T { block(); return this }`
	- 等于这在在对面内部调用，比`let`还要爽
	- 返回值是对象本身
- `run` : 等于有返回结果的的`apply`
- `with` : `fun <T, R> with(receiver: T, block: T.() -> R): R = receiver.block()` 
	- 传入对象执行匿名函数
- `use` : 就等于自动帮你`close()`, 是`Closeable`接口的拓展函数
### 尾递归优化
>函数在调用自己以后没有任何操作
>解决：写出了递归程序，编译器按照迭代来运行

关键字`tailrec`
标记了这个尾递归关键字，编译器在编译的时候就会扁平为`迭代实现`来运行
以此来便面`stackOverFlow`
不符合尾递归结构的函数，编译器会给出提示的

### 闭包
>闭包的意义是：当前作用域总是能够访问外部作用域中的变量；函数是唯一拥有自身作用域的结构，所以闭包的创建依赖于函数
>函数运行时的环境，持有着函数运行状态

可以说Lambda表达式，基本就是一个闭包

详细可以看看这个[js中闭包原理谈和原型及例子](http://blog.csdn.net/u011411356/article/details/50208433)


### 科里化
> 多元函数变成一元函数调用链

更加简明与可拓展

### 偏函数
>传入了部分


---
## 协程
### 基本概念
> 协作程序，解决异步问题，是应用层来完成调度（协同式）
> 我们一般使用的线程是系统层面的调度（抢占式）

挂起：标记为协程的代码块会等待异步的结果返回，而协程代码块之外就会正常执行
优点：
- 异步代码像同步代码一样直观
- 简化异步代码异常处理
- 轻量级的并发方案

协程支持
- 编译器对suspend函数的编译支持
- 标准库的基本API支持
- `kotlinx.coroutine`应用级支持

### 协程的使用
基本API：
- `createCoroutine` : 创建协程
- `startCoroutine` : 启动协程（默认会创建协程）
	- 表示这一块区域是协程的作用区域
- `suspendCoroutine` : 挂起协程
	- 这里具体操作的地方，传入一个`Lambda`表达式，携带参数`continuation`,可以理解为回调函数
	- 需要调用`continuation.resume`和`continuation.resumeWithException`，字面意思很好理解了，这个也就是最终`suspendCoroutine`的返回结果

- `Continuation`接口
	- 运行控制类，负责结果和异常的返回
- `CoroutineContext` 接口
	- 运行上下文，持有资源，运行调度
	- 可以配合`ContinuationInterceptor` 来劫持`Continuation`（有那么一点OkHttp的感觉）


### 封装协程库
### 协程原理分析
- 协程会被编译成状态机
- suspend即状态转移


---
## Java混合开发
### 基本互操作
- 自动识别`Getter/Setter`
	- Kotlin : 可以直接通过属性访问Java的`get/set`属性
	- Java : 可以通过`get/set` 方法来访问Kotlin的属性
- 包级函数
	- Kotlin在编译的时候会为包级函数生成一个类，而包级函数就是里面的静态方法而已
- 拓展方法： 
	- 在上面的基础上，带`Receiver`作为参数的静态方法
- 运算符重载
	- 带`Receiver`参数的对应名称的静态方法，如（`plus`）
- `@JvmField` : 将属性编译成`Java`变量
- `@JvmStatic` : 将对象的方法编译成`Java`的静态方法
- `@JvmOverloads` : 对于定义了默认参数的`Kotlin`方法，可以自动生成`Java`方法对应的重载
- `@file:JvmName` ： 指定`Kotlin`编译后的类名
- `NoArg` : 为标注的类生成无参数的构造方法
	- 因为`data class`默认是不具备无参构造方法的
- `AllOpen` : 为标注的类去掉`final`
	- 因为`data class` 默认是`final`的
- 协变和逆变 `out/in`
	- `ArrayList<out String>`

### SAM转换
`Java`中一个接口只有一个方法的时候
这个接口就可以转换成`lambda`表达式

需要注意的是，`Kotlin`是把`lambda`函数强制转换成接口对象，所以实质上它的对象是没有被保存起来的，也就是每一次传入的对象都是不一样的。

注意：
- `SAM`转换只支持对`Java`接口的转换，对`Kotlin`接口是不支持的，需要使用`typealias`关键字来使用

### 集合框架
- `Kotlin`里面的`list`和`map`是不可变的，可变的是他们的子类`MutableList`和`MutableMap`

- `Kotlin`中的集合框架，在转变成`class`文件的时候，都是映射到`Java`的`list`和`map`
	- tips: 尽量使用`Mutable`，否则在`Java`中使用并且做修改操作会直接崩溃的


## 作用领域
### Kotlin-Script
环境搭建
-  去[kotlin-github]()下载`kotlin-compiler-1.2.20.zip`,具体看最新版本
-  解压，然后添加`/bin`到环境变量
-  就ojbk啦，记住要有`jdk`的哦

命令 ： `kotlin`的脚本文件是以`.kts`结尾的
- `kotlinc -version` : 可以查看当前环境`kotlin`的版本
- `kotlinc -script xxx.kts` ： 就可以执行`kotlin`的脚本文件了


具体作用可以参考`python`，熟悉`Kotlin`可以完全取代`python`的作用了
