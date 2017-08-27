<?php

//mysqli 面向过程方法
$connID=mysqli_connect("localhost","root","14159265jkl");//创建连接
mysqli_select_db($connID, "school");//选择数据库
$result=mysqli_query($connID, "select * from people");//执行语句

/*
①对于insert，update，delete等不会返回数据的SQL语句，在执行没有错误时将返回true。
②对于返回数据的SQL语句执行成功的时候会返回结果集对象可以使用操作结果集对象的函数来从中获取数据
③MYSQLI_STORE_RESULT和MYSQLI_USE_RESULT决定了mysqli client和server之间取结果集的方式。
MYSQLI_STORE_RESULT：执行SQL时提取结果集返回给client，并分配内存，存储到用户程序空间中,之后mysqli_fetch_array()
相当于是从本地取数据；而MYSQLI_USE_RESULT方式下，mysqli_fetch_array()每次都要向server请求结果行。
MYSQLI_USE_RESULT：执行SQL的时候并没有从server将结果集取回
*/

if ($connID==null){
	echo mysqli_connect_error();//返回最后一次错误的时候的连接代码
}
mysqli_set_charset($connID, "UTF-8");
while($resultarray=mysqli_fetch_array($result)){//使用下表数组
	echo $resultarray['name']."   ".$resultarray['age'];
	echo '</br>';
}
while($resultarray=mysqli_fetch_object($result)){//使用对象
	echo $resultarray->name."   ".$resultarray->age;
	echo '</br>';
}
while($resultarray=mysqli_fetch_row($result)){//使用数字下表数组
	echo $resultarray[0]."   ".$resultarray[1];
	echo '</br>';
}
echo mysqli_num_rows($result);//统计结果集
mysqli_free_result($result);//释放结果集
mysqli_close($connID);//关闭连接
mysqli_real_query($connID, "");//也可以使用本函数对数据库执行一条SQL语句，返回结果为布尔值，不返回结果集。如果想获取结果集可以使用mysqli_store_result()获取结果集对象

mysqli_multi_query ( $connID ,"");//执行多条语句之间用,号斤进行分割
//预处理语句
$startment= mysqli_prepare ($connID,"");/*
参数string $types说明：表示后面多个可选参数变量的数据类型，一一对应.
i：int类型
d：double或者float类型
s：字符串类型
b：二进制数据类型（BLOB、二进制字符串）*/
mysqli_stmt_execute ($startment);//执行一个prepared准本好的语句

//上述三步骤举例
$query='insert into t1(id,info,content,filed1) values(?,?,?,?)';
//准备要执行的SQL语句
$stmt=mysqli_prepare($link, $query);

//为?绑定变量
mysqli_stmt_bind_param($stmt,'issd',$val1,$val2,$val3,$val4);

$val1=1;
$val2='第五代青蛙';
$val3='的武器大全我带我去';
$val4=60.5;
//执行准备好的SQL语句
var_dump(mysqli_stmt_execute($stmt));
//如果是select之类的语句需要具体的结果；
//4)将查询出的数据绑定到PHP变量上

mysqli_stmt_store_result ("");//当时使用多语句查询的时候,返回多个结果集取回之后可以使用int mysqli_stmt_num_rows ( mysqli_stmt $stmt );返回语句结果集中的行数
//8)释放给定语句处理存储的结果集所占内存
//void mysqli_stmt_free_result ( mysqli_stmt $stmt );
//9)关闭一个prepared语句
//bool mysqli_stmt_close ( mysqli_stmt $stmt );


