<?php
//字符串运符 . .= ---重新定义左侧的元素
//Heredoc 	结构---里面的值进行比较
$c= <<<"end"
sdfsdfsdfsdf
sdfsdfsf
end;
//Nowdoc 	结构---里面的值进行修改
$c= <<<'end'
sdfsdfsdf
fsfsfsf
end;
//变量解析可以明确使用 ｛｝来明确解析边界
echo 'asfdsaf{$sdfsdfsdf}sadfas';
$str= "sf'asd'f'asf";
//字符串转义 字符串前面加上
$str2= addslashes($str);               	//该字符串为了数据库查询语句等的需要在某些字符前加上了反斜线。这些字符是单引号（'）、双引号（"）、反斜线（\）与 NUL（NULL 字符）。
$str3= stripslashes($str2);            	//对addslashes转义出来的字符串进行 还原
$str4= addcslashes($str3, "a");        	//在指定的字符（或者字符集合--字符串）前面进行转义
$str5= stripcslashes($str4);          	//对stripcslashes转义出来的字符串进行 还原
$string= quotemeta($str);              	//. \ + * ? [ ^ ] ( $ ) 为这些元素加上转义字符集
//字符串转化函数
$string = implode($str, $pieces);                 	//将数组转化为字符串   1-分隔符 2合并的数组
$string = join($glue, $pieces);                   	//上一个函数的别名
$string =number_format($number, $decimals, $str, $thousands_sep);//点分计数法   转化数字成为字符串-只有一个参数1-number 去掉小数点后面的值每1000用，
//                                                	//隔开 2两个参数 格式number到小数点后decimals位   3四个参数格式number到小数点后decimals位str 代替。thousands_sep代替 ，
$array = str_split($str, $split_length);         	//返回将 str字符串拆分位每个长度为length的字符串
//字符串分割函数
$string = chunk_split($str, $chunklen, $c);       	//将字符串分割成两个部分 第一部分长度为chinklen 结束的标志是 end
explode($str, $string, $limit);                   	//返回字符数组 1-分隔符 2-分割数组 3-正数 数组长度-负数 返回除了最后limit个
                                                	//元素之外所有的元素
//数据编码解码函数
//使用 uuencode 编码后的数据 将会比源数据大35%左右 可以被安全的应用于网络传输
$string=convert_uudecode ($data );//解码一个 uuendode编码的字符
convert_uuencode($str);//使用 uuencode 算法对一个字符串进行编码。
//字符串 特殊字符空格去掉函数
ltrim($str, $character_mask);//去左1 - 处理的串 2 - 要处理的特使字符
rtrim($str, $character_mask);//去右1 - 处理的串 2 - 要处理的特使字符
trim($str, $character_mask);//去左右
trim($binary, "\x00..\x1F");//-----第二个参数是特使字符
// 字符串解析函数
parse_str($str, $arr);//将url传值类似的字符进行解析解析的结果，如果第二个字符为空则变成当前环境中的变量，否则装载到传入数组中
//name=Bill&age=60---类似这种数组
//字符串替换函数
$string =str_ireplace($str5, $str4, $str3, $count); //1-查找的值 2-替换的值 3-处理的字符串 4-处理的个数 返回一个新的字符串
$string =substr_replace($str5, $str4, $int, $int);//1-被替换的串 2-要进行替换的串 3-替换的起始位置 4-替换的长
//字符串填充函数
$string = str_pad($str, $pad_length, $c,STR_PAD_BOTH);
					//将str通过c字符（没有默认为空格）填充pad_length位 最后一个参数指定填充方式
					//STR_PAD_BOTH 		- 填充到字符串的两头。如果不是偶数，则右侧获得额外的填充。
					//STR_PAD_LEFT 		- 填充到字符串的左侧。
					//STR_PAD_RIGHT 	- 填充到字符串的右侧。这是默认的。
//字符串比较函数
$int = strcasecmp($str1 ,$str2 );      		//以二进制进行比较函数 不区分大小写
$int = strcmp($str, $c);                	//区分大小写
$int = strnatcmp($str, $c);               	//使用自然排序算法比较字符串  比较数字的值
$int = strncasecmp($str, $c, $len);      	//二进制安全比较字符串开头的若干个字符（不区分大小写）
$int = strncmp($str, $c, $len);          	//二进制安全比较字符串开头的若干个字符
//获取字符串长度
$int=strlen($str);//获取字符串长度
//截取字符串
$string=substr($str, $start, $length);//start负数表示倒数第几个数字
$string=mb_substr($str, $start, $length, $encoding);//指定字符集进行截取
$string =strtok ($str ,$token );//按照token字符分割str
//字符串检索函数
$string =strstr($str, $str5);//检索从第一个字符串中首次出现的后一个字符到最后的全部字符串
//before_search
//可选。默认值为 "false" 的布尔值。
//如果设置为 "true"，它将返回 search 参数第一次出现之前的字符串部分。
$int=substr_count($str, $needle);//检索后字符串在前字符串中出现的次数
strpos($str, $str5);//在一字符串查找第二个字符串首次出现的为值
//其他操作
strrev($str);//将英文字符串前后颠倒 返回
str_repeat($str, $multiplier);//重复 multiplierc次的str 返回
mb_convert_encoding($str, $to_encoding, $str5);//字符串编码转换1-转换前的编码 2-要转换的编码 3-转换前的编码 返回
ord($str);//返回字符串的ascll字符
$string =str_shuffle ($str );//随即打乱一个字符串
echo $str4;
?>