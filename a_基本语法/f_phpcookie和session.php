<?php
//注意session 和cookie 的使用必须在<html>标签之前
//---cookie----
setcookie($name, $value, $expire, [$path, $domain]);//-设置cookie-------------
setcookie("user", "Alex Porter", time()+3600);      //-定义cookie 名称 user 值Alex Porter  过时事件 3600s之后
$_COOKIE["user"];                                   //-取回一个cookie 名称是 user-------

setcookie("user", "", time()-3600);                 //-设置超时事件取消cookie
//--session-----
$int= session_cache_expire ($new_cache_expire);//设置或者返回当前session到期的时间
session_commit();
session_write_close();
$bool=session_destroy();                       //销毁会话中的全部数据
$array=session_get_cookie_params();            //获取会话 cookie 的参数。
/*"lifetime" - cookie 的生命周期，以秒为单位。
 "path" - cookie 的访问路径。
 "domain" - cookie 的域。
 "secure" - 仅在使用安全连接时发送 cookie。
 "httponly" - 只能通过 http 协议访问 cookie*/
//这两个方便理解不要使用
$string= session_name($name);                   //充值或者处理 session_name
$string=session_id($id);                        //返回回话处理的id 为了能够将会话 ID 很方便的附加到 URL 之后， 你可以使用常量 SID 获取以字符串格式表达的会话名称和 ID。
/*
 * 本质上是session实现的原理导致的
 * session_name() 默认为 "PHPSESSID"
 * 而 session_id() 是 一次HTTP 请求,服务器得到的 $_POST['PHPSESSID'] 或者 $_GET['PHPSESSID'] 或者 $_COOKIE['PHPSESSID']
 */
$boolean=session_start();                       // — 启动新会话或者重用现有会话
$int=session_status();                          //返回当前会话状态
/*
 PHP_SESSION_DISABLED 会话是被禁用的。
 PHP_SESSION_NONE 会话是启用的，但不存在当前会话。
 PHP_SESSION_ACTIVE 会话是启用的，而且存在当前会话。
 */
//---session 存入对象
$String=session_encode ($data);                //序列化一个对象
$bool=session_decode ($data);                  //解码被session 序列化的类 不同于 unserialize() 函数
session_start();
//写入session时
$_session["user"] = serialize($data);
//读取session时
$obj = unserialize($_session["user"] );
//-session 销毁--
//1、清空session信息
$_SESSION = array();

//2、清楚客户端sessionid
if(isset($_COOKIE[session_name()]))
{
	setCookie(session_name(),'',time()-3600);
}
//3、彻底销毁session
session_destroy();//---清除本地的session文件






















