<?php

namespace jkl {

	// php 中的函数涉及作用域的问题--函数的参数可以使用任意一个
	// 1.函数变量作用与当使用函数的时候将会形成以个新的作用域(跨文件或者不同命名空间的肯定不能直接访问的)
	// 2.按值传递参数和按照引用传递参数的方法 还用默认参数
	function myfunction($value, &$value2, $value3 = 123) {
		static $value4 = 123; // 使用函数内部静态作用域----所用函数共享
		$attrubute_number = func_num_args (); // 获得函数参数的数量
		$attrubutes = func_get_args (); // 获得传递进来的参数数组
		return array (1,2); // 使用返回值
	}
	function a() {
		echo 'this is my method';
	}
	$result = "jkl\a"; // 函數支持包名重置但是要注意使用全限定名称
	$result ();
	// 3.使用匿名函数形成闭包
	function myfucntion2() {
		$hehe = 123;
		return function () use (&$hehe) { // 这样会让闭包函数中的$hehe在返回的函数中可以使用
			echo $hehe ++;
		};
	}
}











