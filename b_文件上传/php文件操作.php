<?php
$fp=fopen("test.txt","r");
//以只读方式打开文件，将文件指针指向文件头
$fp=fopen("test.txt","r+");
//以读写方式打开文件，将文件指针指向文件头
$fp=fopen("test.txt","w");
//写入方式打开，将文件指针指向文件头并将文件大小截为零。如果文件不存在则尝试创建
$fp=fopen("test.txt","w+");
//以读写方式打开，将文件指针指向文件头并将文件大小截为零。如果文件不存在则尝试创建
$fp=fopen("test.txt","a");
//以写入方式打开，将文件指针指向文件末尾。如果文件不存在则尝试创建
$fp=fopen("test.txt","a+");
//以读写方式打开，将文件指针指向文件末尾。如果文件不存在则尝试创建
$fp=fopen("test.txt","wb+");
//以写入方式打开二进制文件，将文件指针指向文件头并将文件大小截为零。如果文件不存在则尝试创建
$fp=fopen("c: est est.txt","r");
//在windows平台上，要转义文件路径中的每个反斜线，或者用斜线
$fp=fopen("http://www.domain.com/","r");
//以只读方式打开web地址
$fp=fopen("ftp://username:password@domain.com/test.txt","r");
//与ftp服务器连接，以username和password登录服务器
/*
 本实例代码无输出结果
 */
$fp=fopen("test.txt","r");          //打开文件
$msg=fread($fp,filesize("test.txt"));        //读取文件内容到变量
print "$msg";            //输出文件内容
fclose($fp);            //关闭文件

$fp=popen("test.txt","r");          //以只读方式打开文件
$fp=popen($_post['command'],'r');
$read=fread($fp,2096);          //读取内容
echo $read;            //输出内容
pclose($fp);            //关闭文件
//这里用popen执行命令，命令是以post方式提交的参数，并返回执行结果。需要注意的是如果未找到要执行的命令，会返回一个合法的资源。它允许访问shell返回的任何错误信息。在打开了安全模式时，只能执行在safe_mode_exec_dir之内的程序，不能在指向程序的路径中包含..成分，即不能在指定目下外执行命令。

$fp=popen($_post['command'],'r');        //打开进程文件
$read=fread($fp,2096);          //读取进程文件指针到变量
echo $read;            //输出内容
pclose($fp);            //关闭进程文件

$file="test.txt";        //定义文件
if(file_exists($file))       //判断文件是否存在
{
	echo "下面清除缓存";
}
echo "<p>";
clearstatcache();       //清除文件状态缓存
if(file_exists($file))       //判断文件是否存在
{
	die('清除完毕');
}

$filename="test.txt";     //定义文件
$user="admin";      //定义用户
chgrp($filename,$group);    //改变文件/test/testfile.txt的所有者为"admin"

chmod("/test/testfile.txt",0600);
//只有文件所有者拥有读写权限
chmod("/test/testfile.txt",0644);
//文件所有者拥有读写权限，其他用户拥有只读权限
chmod("/test/testfile.txt",0755);
//文件所有者拥有所有权限，其他用户拥有读和执行权限
chmod("/test/testfile.txt",0750);
//文件所有者拥有所有权限，文件所有者所在用户组拥有读和执行权限

$file="test.txt";         //定义文件
delete($file);         //删除文件
//在php编程时一般还是用unlink函数来删除文件。


//php 文件目录操作
mkdir();//创建目录
is_dir("");//
is_file("");
basename("");

$bool=copy($file,$to_file);// 注意是文件的全名称
$string=dirname($path) ;//返回指定文件全名称的目录
file_get_contents();	//把文件读入字符串。
file_put_contents();	//把字符串写入文件。
//-----以上两个暂时不考虑
fclose($file);                      //关闭文件
$boolean=feof($file);               //判断文件是否在文件的结尾
$String=fgets($file);               //逐行读取文件
$String=fgetc($file);               //逐个字符读取文件
//path	必需。规定要读取的文件。
$array=file($path,$include_path,$context);
/*
 include_path
 可选参数include_path 可以是以下一个或多个常量：
 FILE_USE_INCLUDE_PATH
 在 include_path 中查找文件。
 FILE_IGNORE_NEW_LINES
 在数组每个元素的末尾不要添加换行符
 FILE_SKIP_EMPTY_LINES
 跳过空行
 context	可选。规定文件句柄的环境。context 是一套可以修改流的行为的选项。
 若使用 NULL，则忽略。
 */
//文件流写入
$int = fprintf($handle, $str, $string, $str5);    //1-文件系统指针，是典型地由 fopen() 创建的 resource(资源)。2-类似c语言的格式化
//                                                //后的字符串 3---字符串对应的格式化参数

$bool=fflush($file); // 函数向打开的文件写入所有的缓冲输出。
$file = fopen("test.txt","r");
$boolean=move_uploaded_file($file,$newloc); //移动上传的文件  注意是完整的路径 包括文件名称
file_exists(path);// 检查完全路径是否存在--可以搜索文件
//--------------日期操作
date($format,$timestamp);                               //format 必需。规定时间戳的格式。timestamp 可选。规定时间戳。
//默认是当前的日期和时间。
mktime($hour,$minute,$second,$month,$day,$year,$is_dst);//添加时间戳戳


