<?php
filter_has_var($type, "string_name");       //函数检查是否存在指定输入类型的变量一边量名查询。
/* --(1)----
 * 可能的输入类型：
 * INPUT_GET
 * INPUT_POST
 * INPUT_COOKIE
 * INPUT_SERVER
 * INPUT_ENV
 */
$int = filter_id("filter_name");            //函数返回指定过滤器的 ID 号
$var = filter_input($input_type, $variable, [filter, options]); //如果成功，则返回被过滤的数据。如果失败，则返回 FALSE。如果 "variable" 参数未设置，则返回 NULL。
/*
  input_type		必需。规定输入类型。参见上面的列表中可能的类型。 类型见上面1
  variable			必需。规定要过滤的变量。
  filter			可选。规定要使用的过滤器的 ID。默认是 FILTER_SANITIZE_STRING。
 */
if (!filter_input(INPUT_POST, 'email', FILTER_VALIDATE_EMAIL)) {
    echo "E-Mail is not valid";
} else {
    echo "E-Mail is valid";
}
$var = filter_input_array($input_type, $filter_args); //定义组合属性过滤器   第二个参数是要进行过滤的数组属性键 要过滤的元素 值为过滤标准
$filters = array//第一个参数表示提交的url名称 filter 表示过滤的类型比如int或者string option-表示配置项比如最大最小
    (
    "name" => array
        (
        "filter" => FILTER_CALLBACK,
        "flags" => FILTER_FORCE_ARRAY,
        "options" => "ucwords"
    ),
    "age" => array
        (
        "filter" => FILTER_VALIDATE_INT,
        "options" => array
            (
            "min_range" => 1,
            "max_range" => 120
        )
    ),
    "email" => FILTER_VALIDATE_EMAIL,
);
print_r(filter_input_array(INPUT_POST, $filters));
/* ---以上的输出
  Array
  (
  [name] => Peter
  [age] => 41
  [email] => peter@example.com
  )
 */
$array = filter_list();                              	//返回过滤器的名称数组
$array = filter_var_array($array, $args); 				//两个属性 filter_input_array的 去除 输入方式验证版
$var = filter_var($variable, $filter, $options); 		// ilter_input 去除 输入方式验证版
														//过滤器的格式   具体见网站


//这个是重点使用自定义的过滤器
function convertSpace($string) {
    return str_replace(" ", "_", $string);
}
$string = "Peter is a great guy!";
echo filter_var($string, FILTER_CALLBACK, array("options" => "convertSpace"));


