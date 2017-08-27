<?php
//1.命名空间的问题
//(1)当当前的命名空间是A的时候 new B()会被转义成 new \A\B(); new C\B() 转义成new \A\C\B();

//2.命名空间操作符
//use \a\b\c as AAA;//将前面的命名空间使用后面的运算符替换

//3.命名空间只能使用函数或者类,基本变量会满足 include 加载 覆盖的原则 ,没办法直接通过namespace找到相关的基本变量
//4.引用外部文件类
include "";//使用的时候才会被引用
require '';//程序一执行就会被引用
//加上once将会检查是否在其他页面上引用
include_once '';//进行查重
require_once '';


//php 类自动装载
function loader($class)
{
	$file = $class . '.php';
	if (is_file($file)) {
		require_once($file);
	}
}

spl_autoload_register('loader');

//$a = new A();
function __autoload($class)//和下面的大致相通
{
	$file = $class . '.php';
	if (is_file($file)) {
		require_once($file);
	}
}
spl_autoload_register(array('Loader', 'loadClass'));//    php将会自动的调用自己使用的函数

?>