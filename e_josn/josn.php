<?php

//--php-josn支持--
$string = json_encode($value, [$options = 0]);
/*
            value: 要编码的值。该函数只对 UTF-8 编码的数据有效。
            options:由以下常量组成的二进制掩码：
            JSON_HEX_QUOT, JSON_HEX_TAG, JSON_HEX_AMP, JSON_HEX_APOS,
            JSON_NUMERIC_CHECK,JSON_PRETTY_PRINT, JSON_UNESCAPED_SLASHES, JSON_FORCE_OBJECT
 JSON_HEX_TAG (integer)
所有的 < 和 > 转换成 \u003C 和 \u003E。 自 PHP 5.3.0 起生效。
JSON_HEX_AMP (integer)
所有的 & 转换成 \u0026。 自 PHP 5.3.0 起生效。
JSON_HEX_APOS (integer)
所有的 ' 转换成 \u0027。 自 PHP 5.3.0 起生效。
JSON_HEX_QUOT (integer)
所有的 " 转换成 \u0022。 自 PHP 5.3.0 起生效。

JSON_FORCE_OBJECT (integer)
使一个非关联数组输出一个类（Object）而非数组。 在数组为空而接受者需要一个类（Object）的时候尤其有用。 自 PHP 5.3.0 起生效。

JSON_NUMERIC_CHECK (integer)
将所有数字字符串编码成数字（numbers）。 自 PHP 5.3.3 起生效。

JSON_PRETTY_PRINT (integer)
用空白字符格式化返回的数据。 自 PHP 5.4.0 起生效。

JSON_UNESCAPED_SLASHES (integer)
不要编码 /。 自 PHP 5.4.0 起生效。
JSON_UNESCAPED_UNICODE (integer)
以字面编码多字节 Unicode 字符（默认是编码成 \uXXXX）。 自 PHP 5.4.0 起生效。
JSON_PRESERVE_ZERO_FRACTION (integer)
确保浮点值始终编码为浮点值。 自PHP 5.6.6起可用。            */
$var=json_decode ($json ,$assoc = false , $depth = 512 , $options = 0 );
/*
json_string: 待解码的 JSON 字符串，必须是 UTF-8 编码数据
assoc: 当该参数为 TRUE 时，将返回数组，FALSE 时返回对象。
depth: 整数类型的参数，它指定递归深度
options: 二进制掩码，目前只支持 JSON_BIGINT_AS_STRING 。
*/