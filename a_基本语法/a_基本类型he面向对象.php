<input text="<?=zxc ?>"></input><!-- 输出表达式 -->
<?php
//1.类型判断方法
is_bool(123);//is_xxxx表示判断类型 xxx有 bool string float double interger  int null array object numeric
//2. 数据输出
print ("ssss"); //输出存在返回值可以最为表达式 的一部分
echo sdfsdf;
//3.定义常量
define('MAX',1123);	//前面的值表示函数名成后面的值表示常量的值
constant('MAX');	//计算常量是否存在 -- 
		//----系统的预定义常量----
//--php预定义常量  魔术变量---
__LINE__;                                        //文件中的当前行号。
__FILE__;                                        //文件的完整路径和文件名。如果用在被包含文件中，则返回被包含的文件名。
//自 PHP 4.0.2 起，__FILE__ 总是包含一个绝对路径（如果是符号连接，则是解析后的绝对路径），
//而在此之前的版本有时会包含一个相对路径。
__DIR__;                                         //件所在的目录。如果用在被包括文件中，则返回被包括的文件所在的目录。
//它等价于 dirname(__FILE__)。除非是
//根目录，否则目录中名不包括末尾的斜杠。（PHP 5.3.0中新增） =
__FUNCTION__;                                    //函数名称（PHP 4.3.0 新加）。自 PHP 5 起本常量返回该函数被定义时的名字
//（区分大小写）。在 PHP 4 中该值总是小写字母的。
__CLASS__;                                       //类的名称（PHP 4.3.0 新加）。自 PHP 5 起本常量返回该类被定义时的名字（区分大小写）。在 PHP 4 中该值总是小写字
//母的。类名包括其被声明的作用区域（例如 Foo\Bar）。注意自 PHP 5.4 起 __CLASS__ 对 trait 也起作用。当用在 trait 方法中时，__CLASS__ 是调用 trait 方法的类的名字。
__TRAIT__;                                       //Trait 的名字（PHP 5.4.0 新加）。自 PHP 5.4 起此常量返回 trait 被定义时的名字（区分大小写）。Trait 名包括其被声明
//的作用区域（例如 Foo\Bar）。
__METHOD__;                                      //类的方法名（PHP 5.0.0 新加）。返回该方法被定义时的名字（区分大小写）。
__NAMESPACE__;                                   //当前命名空间的名称（区分大小写）。此常量是在编译时定义的（PHP 5.3.0 新增）。
//
//4.php 默认使用复制传值与java不同 --- 使用引用传值需要使用&运算符 函数中需要在函数变量中指定&
function hehe(&$heh){}
//5.可变变量
$a='b';
$b=123;
echo $$a;//输出的是$b
//5.php预定义变量
//全局变量
$GLOBALS["xx"];                                 //访问全局变量
$_SERVER["PHP_SELF"];                           //简单度文档---有许多的属性
//请求的同名属性优先级 后面覆盖前面   E代表$_ENV，G代表$_GET，P代表$_POST，C代表$_COOKIE，S代表$_SESSION
$_REQUEST["name"];                              //取出表单数据 无视get还是post 直接获取数据
$_POST["name"];									//获取post方法传递的数据
$_GET["name"];									//获取get方法传递的数据
$_ENV["name"];                                  //系统自身的数据 一般用不到
$_COOKIE["cookiename"];                         //获得名字下的cookie;
setcookie($name, $value,[ $expire]);            //设置cookie 名字 值 过期时间
setcookie('mycookie','',time()-3600);           //删除方法  将原来的cookie事件变成空的 并且 设置过期事件
$_SESSION["name"];                              //详情见专题
//6.运算符 和其他的语言相通
1=='2';//true ==号将不同的类型转化成int类型然后进行比较
1==='2';//false ===直接进行比较
//7.数据类型转换
//php推荐直接使用强制类型转化
$aa=(string)123;//注意使用的都是小写字母
intval("123123");//使用面向对象的方法
settype("123", "integer");//面向对象升级版
//8.控制流程语句 大体上和java等其他语言相通
switch (true){//php的swich可以放入任何基本类型 而java 只能是 string int 或者 enum
	case 123>1234:
		break;
	default:
		break;
}
//foreach 循环迭代变量
foreach ($array as $key=>$value){//将两个属性按照相通的需求放入相通的
	
}
//结束程序
exit(123);//带返回值的结束
//-----------------------------php-类和面向对象----------------------
class AAA{
	public static $a=123321;
}
//php只有单继承
abstract class hehe extends AAA implements myinterface{  //php的继承只能继承方法和静态变量和常量--使用parent::调用
	//1. 变量的约束   --- 效果类似java var相当于java 的没有参数的类型
	public $a=123;
	protected $b=123;
	private $c="jkl";
	var $d="123123";
	//2.常量
	const yyy="123123";//静态变量不能进行 修改预定义  只能通过类进行访问
	//3.静态变量static
	static $yuyu=123;//和函数的静态变量类似,所有的类共享,只能通过类名称访问---和java不同java
	static function myfunction1(){
		Myclass::$yuyu+=12;//static 方法之只能访问static类型的参数
		
	}
	//4.final  -  使用final方法讲不能被重写覆盖   也可以修饰类  类将不能进行集成 不能修饰变量因为有const这个常量---java中没有常量
	final function thisismyclass(){
		
	}
	//5.使用 访问控制   注意 parent 和 self 只能访问常量或者静态  或者方法
	function myfunction2(){
		parent::$a;
		self::$yuyu;//只能访问静态变量
		$this->a;//不能访问静态变量从而只能访问非静态变量---不用加上$ 因为$只是一个标记而后面的东西才是真实引用
	}
}
//6.接口和抽象类   --- 实现接口的子类必须实现接口中的方法 接口中 变量必须是 const类型的变量
interface myinterface{
	const int=123;
	public function hehe();
}
//抽象类除了可以存在抽象方法之外其他的和普通类没什么区别
abstract class myabstract{
	abstract protected function heh();
}
//类似函数的自动转入  类也可以进行
$a='Myclass';
$b=new $a();//同样在全局空间的时候要使用全限定类名称---注意命名空间
//7.php使用匿名类
//1.匿名类被嵌套进普通 Class 后，不能访问这个外部类（Outer class）的 private（私有）、protected（受保护）方法或者属
//性。 为了访问外部类（Outer class）protected 属性或方法，匿名类可以 extend变成这个类或者子类（牵扯到了作用域的问题，权限的本质是 访问的问题）。
//为了使用外部类（Outer class）的 private 属性，必须通过构造器 将private参数传进来
//2.可以传递参数到匿名类的构造器，也可以扩展（extend）其他类、实现接口（implement interface），以及像其他普通的类一样使用 trait：
function hehe($hehe){
	echo $hehe->feiqi();
}
hehe(new class{//php7使用的特殊写法
	public function feiqi(){
		echo 'hehe';
	}
});
	//-----------全局的一些类的方法-------------------------------
	class_exists($classname);           //判断类是否存在
	get_class($object);                 //返回类的方法名
	get_class_methods($classname);      //以实例名字作为参数返回构造函数在内的所有方法数组
	get_object_vars($a);                 //返回实例中所有公用的属性及其值的关联数组。注意它和get_class_vars()的区别：
	/* (1) get_object_vars($a)是用实例名做参数，而get_class_vars(‘test')是用类名做参数。
	 * (2) get_object_vars($a)获得的属性值是实例运行后的值，而get_class_vars(‘test')获得的属性值是类中*/
	
	get_parent_class($b); //返回父类的名称
	method_exists($a,'getv');       //确定类或实例中，是否存在某方法。
	
	
	//----class类的自动装载--- 注意要手动导入命名空间
	//spl_autoload_register(autoload_function,throw,prepend);
	/*autoload_function
	 欲注册的自动装载函数。如果没有提供任何参数，则自动注册 autoload 的默认实现函数spl_autoload()。
	 
	 throw
	 此参数设置了 autoload_function 无法成功注册时， spl_autoload_register()是否抛出异常。
	 
	 prepend
	 如果是 true，spl_autoload_register() 会添加函数到队列之首，而不是队列尾部。*/
	//spl_autoload ( string $class_name [, string $file_extensions ] );
	/*class_name
	 file_extensions
	 在默认情况下，本函数先将类名转换成小写，再在小写的类名后加上 .inc 或 .php 的扩展名作为文件名，
	 * 然后在所有的包含路径(include paths)中检查是否存在该文件。
	 */
	//--------------例子
	function my_autoloader($class) {
		include 'classes/' . $class . '.class.php';
	}
	
	spl_autoload_register('my_autoloader');
	
	// 或者，自 PHP 5.3.0 起可以使用一个匿名函数
	spl_autoload_register(function ($class) {
		include 'classes/' . $class . '.class.php';
	});
		//导入文件
		require '';
		require_once '';
		include '';
		include_once '';
		
		
		//-----------遍历类--几乎无用--------
		//Traversable（遍历）接口 检测一个类是否可以使用 foreach 进行遍历的接口。
		//Iterator（迭代器）接口
		
		
		
		//--------魔术方法-------------
		//1.clone 关键字 clone 对象，当要为clone后的对象进行重新赋值的时候需要使用新的魔术方法clone
		class myclass{
			private  $name="liyuelin";
			function __construct() {
				//定义构造函数
			}
			function __destruct(){
				//这个是析构函数
			}
			//以下两个参数是方法名和方法参数--第一个是指定一般方法的参数  第二个是指定静态方法的参数
			function __call($name, $arguments) {
				//在对象中调用一个不可访问方法时，__call() 会被调用。
			}
			function __callStatic($name, $arguments) {
				//在静态上下文中调用一个不可访问方法时，__callStatic() 会被调用。
			}
			//获得一个属性完全相同的新对象
			function __clone() {//1.clone 关键字 clone 对象，当要为clone后的对象进行重新赋值的时候需要使用新的魔术方法clone
				$this->name="jishuya";
			}
			//与下面两个相类似的--当对不可访问属性调用 isset() 或 empty() 时，__isset() 会被调用。当对不可访问属性调用 unset() 时，__unset() 会被调用。
			function __set($name, $value) {//设置私有参数此魔术方法就会访问
				$this->$name=$value;
			}
			function __get($name) {//读取私有参数的时候这个参数就会访问
				return $this->$name;
			}
			function __toString() {//__toString() 方法用于一个类被当成字符串时应怎样回应。此方法必须返回一个字符串需要指出
				//的是在 PHP 5.2.0 之前，__toString() 方法只有在直接使用于 echo 或 print 时才能生效。PHP 5.2.0 之后，则可以在任
				//何字符串环境生效（例如通过 printf()，使用 %s 修饰符），但不能用于非字符串环境（如使用 %d 修饰符）。
				return "asdfsadf";
			}
			function __invoke() {
				//当尝试以调用函数的方式调用一个对象时，__invoke() 方法会被自动调用。
				//$Class(123);---类名加上（）传入参数
			}
			function __isset(){//isset()函数用来检测对象是否被设置
				//对不可访问的属性调用isset() 或者 empty()的时候将会被调用
			}
			function __unset(){//unset()---删除一个属性
				//对不可访问的属性调用unset()的时候将会被调用
			}
			function __clone(){
				//但进行对象赋值的时候将会调用用来进行对象的属性修改
			}
			function __autoload(){
				//
			}
		}
		$a=new myclass();
		$c=clone $a;
		echo $a->get;
		





//8.php使用 面向切面编程
//!当方法或属性同名时，当前类中的方法会覆盖 trait的 方法，而 trait 的方法又覆盖了基类中的方法。
	trait A { //  声明一个特点
		public function smallTalk() {
			echo 'a';
		}
		public function bigTalk() {
			echo 'A';
		}
	}
	trait B {
		public function smallTalk() {
			echo 'b';
		}
		public function bigTalk() {
			echo 'B';
		}
	}
	class Talker {//将特点导入  class中进行继承  注意  从基类继承的成员会被 trait 插入的成员所覆盖
		use A,B {//用 , 表示加入多个节点
			B::smallTalk insteadof A;  //instanceof关键字：用于分析一个对象是否是某一个类的实例或子类或是实现了某个特定的接口
			// 同名方法表示进行排除表示 只使用 B中的smalltalk方法
			A::bigTalk insteadof B;
		}
	}
	class Aliased_Talker {
		use A,
		B {
			B::smallTalk insteadof A;
			A::bigTalk insteadof B;
			B::bigTalk as private talk;//将 B中的bigtalk方法进行重命名 从而使ab中相同的方法可义相互转换
							//as 还能控制访问权限
		}
	}
	class myclass_one {
		//use Mytrait,
		//Mytrait2; //将特点导入  class中进行继承  注意  从基类继承的成员会被 trait 插入的成员所覆盖
		//独傲哥mytrait可以使用 ,好进行分割
	}
	//-------------------------
	// 修改 sayHello 的访问控制
	class MyClass1 {
		use HelloWorld { sayHello as protected; }//改变原来的特点类的此方法作为私有方法
	}
	// 给方法一个改变了访问控制的别名
	// 原版 sayHello 的访问控制则没有发生变化
	class MyClass2 {
		use HelloWorld { sayHello as private myPrivateHello; }//重新定义一新的同作用方法并且进行改名处理
	}
	trait HelloWorld {
		use A, B;   //  trait的继承性
		public static function doSomething() {//静态方法
			return 'Doing something';
		}
		abstract public function getWorld();//抽象方法
	}
//--1.自定义错误处理器-------使用系统内建的异常-------------------------------------------------------------------
	//error handler function
	function error_function($error_level,$error_message,
	$error_file,$error_line,$error_context){//---定义处理错误的函数  有四个参数 两个必选
		/*
		 * error_level	必需。为用户定义的错误规定错误报告级别。必须是一个数字。参见下面的表格：
		 * 错误报告级别。
		 * error_message	必需。为用户定义的错误规定错误消息。
		 * error_file	可选。规定错误发生的文件名。
		 * error_line	可选。规定错误发生的行号。
		 * error_context	可选。规定一个数组，包含了当错误发生时在用的每个变量以及它们的值。
		 */
		echo "<b>Error:</b> [$errno] $errstr";
	}
	//set error handler
	set_error_handler("customError"); //内建的函数声明出现错误的时候使用那个函数  注册异常
	//trigger error
	echo($test);
	//-2 类异常处理-
	class customException extends Exception {//----自定义异常处理类
		
		public function errorMessage() {
			//error message
			$errorMsg = 'Error on line ' . $this->getLine() . ' in ' . $this->getFile()
			. ': <b>' . $this->getMessage() . '</b> is not a valid E-Mail address';
			return $errorMsg;
		}
		
	}
	$email = "someone@example.com";
	try {
		//check if
		if (filter_var($email, FILTER_VALIDATE_EMAIL) === FALSE) {
			//throw exception if email is not valid
			throw new customException($email); //---定义抛出异常-- 异常处理的类的构造函数必须出入一个参数参数
		}
		//check for "example" in mail address
		if (strpos($email, "example") !== FALSE) {
			throw new Exception("$email is an example e-mail");
		}
	} catch (customException $e) {
		echo $e->errorMessage();
	} catch (Exception $e) {
		echo $e->getMessage();
	}
	//基本die函数
	die("sfsdf");
	
	
	
	//后期静态绑定
	
?>
