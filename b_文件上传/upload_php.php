<?php
var_dump($_FILES);//--格式化输出 $_files数组的形式
$allowedExts = array("gif", "jpeg", "jpg", "png");
$temp = explode(".", $_FILES["file"]["name"]);//使用.进行分割字符串从而使字符串可以进行处理
$extension = end($temp);//验证文件上传的尾部
echo '<br/>';
//使用files数组 第一维表示表单的名称 第二维 name(文件名称) size(文件大小) tmp_name(缓存文件名称) type(上传类型) error
print($_FILES["file"]["size"]);echo '<br/>';
if ((($_FILES["file"]["type"] == "image/gif") || ($_FILES["file"]["type"] == "image/jpeg") ||
        ($_FILES["file"]["type"] == "image/jpg") || ($_FILES["file"]["type"] == "image/pjpeg") 
        || ($_FILES["file"]["type"] == "image/x-png") || ($_FILES["file"]["type"] == "image/png"))
        && ($_FILES["file"]["size"] < 99999999) && in_array($extension, $allowedExts)) {//对上传的文件进行验证
    if ($_FILES["file"]["error"] > 0) {
        echo "Return Code: " . $_FILES["file"]["error"] . "<br>";
    } else {
        echo "Upload: " . $_FILES["file"]["name"] . "<br>";
        echo "Type: " . $_FILES["file"]["type"] . "<br>";
        echo "Size: " . ($_FILES["file"]["size"] / 1024) . " kB<br>";
        echo "Temp file: " . $_FILES["file"]["tmp_name"] . "<br>";
		//判断文件是否存在
		is_uploaded_file("");//判断文件是否是上传的文件
        if (file_exists("upload/" . $_FILES["file"]["name"])) {
            echo $_FILES["file"]["name"] . " already exists. ";
        } else {
            move_uploaded_file($_FILES["file"]["tmp_name"], "d://Php_upload/" . $_FILES["file"]["name"]);
            echo "Stored in: " . "upload/" . $_FILES["file"]["name"];
        }
    }
} else {
    echo "Invalid file";
}
?>