<?php
$to = "someone@example.com";                            //地址
$subject = "Test mail";                                 //主题
$message = "Hello! This is a simple email message.";    //内容
$from = "someonelse@example.com";
$headers = "From:" . $from;                             //副标题
mail($to, $subject, $message, $headers);
echo "Mail Sent.";
?>



<html>
    <body>
        <?php
        //----防止email 注入 
        /*在内容加入Cc:、Bcc: 和 To: 字段如
         * someone@example.com%0ACc:person2@example.com
         * %0ABcc:person3@example.com,person3@example.com,
         * anotherperson4@example.com,person5@example.com
         *  %0ABTo:person6@example.com 
         *  这封 e-mail 会被发送到上面所有的地址！        
         *  */
        //解决办法使用过滤器进行过滤
        function spamcheck($field) {
            //filter_var() sanitizes the e-mail
            //address using FILTER_SANITIZE_EMAIL
            $field = filter_var($field, FILTER_SANITIZE_EMAIL);

            //filter_var() validates the e-mail
            //address using FILTER_VALIDATE_EMAIL
            if (filter_var($field, FILTER_VALIDATE_EMAIL)) {
                return TRUE;
            } else {
                return FALSE;
            }
        }

        if (isset($_REQUEST['email'])) {//if "email" is filled out, proceed
            //check if the email address is invalid
            $mailcheck = spamcheck($_REQUEST['email']);
            if ($mailcheck == FALSE) {
                echo "Invalid input";
            } else {//send email
                $email = $_REQUEST['email'];
                $subject = $_REQUEST['subject'];
                $message = $_REQUEST['message'];
                mail("someone@example.com", "Subject: $subject", $message, "From: $email");
                echo "Thank you for using our mail form";
            }
        } else {//if "email" is not filled out, display the form
            echo "<form method='post' action='mailform.php'>
 Email: <input name='email' type='text'><br>
 Subject: <input name='subject' type='text'><br>
 Message:<br>
 <textarea name='message' rows='15' cols='40'>
 </textarea><br>
 <input type='submit'>
 </form>";
        }
        ?>

    </body>
</html> 