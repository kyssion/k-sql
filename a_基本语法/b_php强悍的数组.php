<?php
//声明
$a=array("123",123,12.2);
$b=array("ha"=>666,"ll"=>77);
$c=array(
		"555"=>array("fff"=>6666),
		"5555"=>array("fff"=>6366),
		"5535"=>array("fff"=>6366),
		"5255"=>array("fff"=>6656)
);//二维数组
foreach ($d as $key=>$value){
}
while(list($canshu ,$canshu)= each($array)){
	//each -- 每次将会返回一个两个数 的数组 键是0 值是1 list 将数组中的值赋值给变量 
}
//数组输出
print_r($c);//按照格式进行输出
var_dump($c);//按照缩进方式进行输出--返回数据类型的格式和值

//-----作为数组使用   操作方法
//1.数组拆分函数
$a= array_chunk($c, $size,true);//将一个一维数组拆分成多维数组，size位二维长度  是否保留原始键名
//2.数组截取函数
$a= array_slice($c, $offset, $length, $true);//截取数组 从offset到length的长度 true表示保留原键值
$a= array_splice($c, $offset, $length, $b);//数组取代函数 同字符串取代函数
//3.数组格式化生成函数
$a= range(0, 100, 2);//生成一个数值由0-100每次跳2的一个数组
$a= array_combine($a, $b);//传入两个数组   生成指定=键值对应的数组 前面的数组表示key后面的表示value
$a= array_fill($start_index, $num, $c);//返回一个给start_index位填充num个c值的数组
$a= array_fill_keys($c, $b);//用数组$c指定的键填充$b的键
$a= array_pad($c, $size, $b);//返回数组c 填充 size个（正右负左）个 b值后的数组
//4.分割成数组函数
$a=explode($delimiter, $string,$c);//将字符串 string按照 delimiter分割负进行分割后返回数组 c指定返回的个数可省略
//数组过滤函数   使用回调 函数版的foreach
$a= array_filter($c, $callback, ARRAY_FILTER_USE_BOTH);	//将c数组的值按照最后一位的要求一次传递给ccallback函数
														//ARRAY_FILTER_USE_KEY - callback接受键名作为的唯一参数
														//ARRAY_FILTER_USE_BOTH - callback同时接受键名和键值
//5.数组统计函数
$a= count($a,1);//统计数组中元素的个数，第二个属性可选当是一的时候进行递归查询二维数组
$a= max($a,$a);//返回数组中的最大值可返回多个数组 min同理
$a= array_sum($c);//返回一个数组中所有值的和
$a= array_count_values($c);//以数组中所有的值位键 值出现的次数为值返回一个数组   --键和数字
$a= array_product($c);//统计数组的乘积
//6.数组指针函数
$a= key($c);//返回数组当前指针的键名
$a= current($c);//返回当前数组指针的值
$a= next($c);//指针后移病返回当前的指针的值，没有返回null；
$a= end($c);//将指针移动到数组最后一位，并返回值
$a= prev($c);//简之争前移并返回值
$a= reset($c);//初始化指针，移动到数组最前面并且返回值
$a= each($c);//返回数组当前指针冰箱前移动一位
//7.数组和变量间转换
extract($c);//数组变成值 必须是索引数组，将数组键变成变量名，数组值变成变量值
$a=compact("zifuchuan");//将任意个变量名 和 他们所拥有的值组合形成一个数组
//8.数组检索函数
$a= array_keys($c, $b);//返回键和对应为值组成的数组，c表示数组，b表示键
//value	可选。您可以指定键值，然后只有该键值对应的键名会被返回。
//strict	可选。与 value 参数一起使用。可能的值：
//true - 返回带有指定键值的键名。依赖类型，数字 5 与字符串 "5" 是不同的。
//false - 默认值。不依赖类型，数字 5 与字符串 "5" 是相同的。
$a= array_values($c);//将所有的值提取形成一个 数组 （不是映射数组）
$a= in_array($c, $b,true);//不忽略大小写 检索c值是否在数组b中 true表示检查数组的值和输入的值类型是否相同 返回true|false
$a= array_search($c, $b);//同上返回键名或者是null
$a= array_key_exists($c, $b);//同上检索键名
$a= array_unique($c);//数组去重，先将数组进行排序然后在将相同的只留下第一个数据
//9.数组排序
sort($c,SOFT_NUMERIC);//对数组进行排序（不保留键名） 
									//SORF_NUMERIC元素值作为数值比较 
									//SORF_REGULAR按照普通方式比较元素的值
									//SORF_STRING将元素的值当作字符串进行比较
									//SORF_LOCALE_STARING给予当前值当作字符串进行比较
asort($c);//保留键名
rsort($c);arsoft($c);//降序排列
ksort($c);    krsort($c);//将元素键名进行升序排序 降序排序
//还有krsort等
natcasesort($c);//按照自然排序进行排序
shuffle($c);//元素进行随机排序
$a=array_reverse($c,true);//相反排序 返回新数组 true表示元素保持原来的键值对对应关系
$bool = array_multisort($array ,SORT_ASC, SORT_NUMERIC,$array1,$array2);//多个数组排序 1.指定的排序数组 2.指定升降序 3.指定比较方式 4.可以有多组相同的123序号
//数组数据结构
array_push($c, $b);//入栈 c为数组，b为插入参数可义多个 返回插入的个数
array_pop($c);//出栈返回出栈元素
array_shift($c);//出队列
array_unshift($c, $b);//向前加东西 c为数组， b为添加的参数
//数组集合函数
array_merge($c,$d);//合并为新数组然后返回新数组 保留键名相同值覆盖
array_diff($c, $b);//多个数组求差集并返回新数组不保留键名
array_diff_assoc($c, $b);
array_diff_key($c, $b);
array_intersect($c, $b);//同上多个数组求交集
array_intersect_assoc($c, $b);
array_intersect_key($c, $b, $_);
//其他
$a= array_rand($c, $num);//从数组中取出num个单元 最后一个元素可以省略
$a= array_reduce($c, $callback, $cansu);//数组迭代函数 1.数组 2.调用的函数（前面的结果值，当前值） 3.初始的结果值默认是0 可以不写
$bool= array_walk($c, $callback);//为每个函数调用函数（value，key）
$bool = array_walk_recursive($c, $callback);//前面可用于二维数组版本
//array_replace_recursive — 使用传递的数组递归替换第一个数组的元素
//array_replace — 使用传递的数组替换第一个数组的元素
$a= array_unique($c);//移除数组中的重复值



