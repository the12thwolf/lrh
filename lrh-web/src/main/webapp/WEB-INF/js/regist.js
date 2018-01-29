
function getXhr() {
	var xhr = null;
	if (window.XMLHttpRequest) {
				//非IE浏览器
		xhr = new XMLHttpRequest();
	} else {
			//ie浏览器
		xhr = new ActiveXObject("Microsoft.XMLHttp");
	}
	return xhr;
}

function check_userName(){
	/**
	 *检查用户名
	 */
	
	//step1,获取ajax对象
	$('userName_message').innerHTML = '';
	if($F('userName').strip().length == 0){
		$('userName_message').innerHTML = '请输入用户名';
		return false;
	}
	
	
	var flag = true;
	var xhr = getXhr();
	//step2,发送请求
	xhr.open('post','checkInputValue.do',false);
	xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState == 4){
			if(xhr.status==200){
				var txt = xhr.responseText;
				if('ok'==txt){
					flag = true;
					$('userName_message').innerHTML = '用户名可以使用';
				}else if('fail'==txt){
					flag = false;
					$('userName_message').innerHTML = '您选择的用户名太抢手了已经被注册了 请换一个试试...';
				}else if('empty'==txt){
					flag = false;
					$('userName_message').innerHTML = '用户名不能为空';
				}
			}else{
				flag = false;
				$('userName_message').innerHTML = '检查用户名失败';
			}
		}
	};
	$('userName_message').innerHTML = '正在检查...';
	xhr.send('userName='+$F('userName'));
	return flag;
}


function check_captcha(){
	$('captcha_message').innerHTML = '';
	if($F('captchaFromClient').strip().length == 0){
		$('captcha_message').innerHTML = '请输入验证码';
		return false;
	}
	/**
	 * 检查验证码
	 */
	var flag = false;
	var xhr = getXhr();
	//step2,发送请求
	xhr.open('post','checkInputValue.do',false);
	xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
	//调用函数处理服务器响应
	xhr.onreadystatechange=function(){
		if(xhr.readyState == 4){
			if(xhr.status==200){
				var txt = xhr.responseText;
				if('ok'==txt){
					/**
					 * 服务器验证结果是验证码正确
					 */
					flag = true;
					//$('captcha_message').innerHTML = '验证码正确';
					//$('captcha_message').innerHTML = '验证码正确';
				}else if('fail'==txt){
					/**
					 * 服务器验证结果是验证码错误
					 */
					flag = false;
					$('captcha_message').innerHTML = '验证码错误';
				}else if('empty'==txt){
					flag = false;
					$('captcha_message').innerHTML = '请输入验证码';
				}
			}else{
				$('captcha_message').innerHTML = '检查验证码失败';
			}
		}
	};
	//$('captcha_message').innerHTML = '正在检查...';
	xhr.send('captchaFromClient='+$F('captchaFromClient'));
	return flag;
}

/**
 * 检查真实姓名
 */
function check_realName(){
	//先将提示信息设置为空
	$('realName_message').innerHTML='';
	//判断真是姓名是不是为空 ，为空返回false 并提示错误
	if($F('realName').strip().length==0){
		$('realName_message').innerHTML='姓名不能为空';
		return false;
	}
	return true;
}
/**
 * 检查密码
 */
function check_password(){
	$('password_message').innerHTML='';
	//先将提示信息设置为空
	$('password_message').innerHTML='';
	//判断密码是不是为空 ，为空返回false 并提示错误
	if($F('password').strip().length==0){
		$('password_message').innerHTML='密码不能为空';
		return false;
	}
	return true;
}

function check_telphone(){
	$('telphone_message').innerHTML='';
	var flag = true;
	/*//先将提示信息设置为空
	$('telphone_message').innerHTML='';
	//判断手机号是不是为空 ，为空返回false 并提示错误
	if($F('telphone').strip().length==0){
		$('telphone_message').innerHTML='手机号不能为空';
		return false;
	}*/
	//判断手机号是不是11位字母 不是返回false 并提示错误
	var rgExp = /^\d{11}$/gi;//11位数字
	//获取用户输入信息
	var telphoneNumber = $F('telphone');
	//检验输入信息
	flag = rgExp.test(telphoneNumber);
	if(flag==false){
		$('telphone_message').innerHTML='手机号必须是11为数字';
	}else{
		$('telphone_message').innerHTML='';
	}
	return flag;
}

function checkValues(){
	var flag = check_userName()&&check_captcha()
	&&check_realName()&&check_password()
	&&check_telphone();
	return flag;
}


!function(){
    laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
    laydate({elem: '#demo'});//绑定元素
}();
//日期范围限制
var start = {
    elem: '#start',
    format: 'YYYYMMDD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,
    istoday: false,
    choose: function(datas){
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
    }
};
var end = {
    elem: '#end',
    format: 'YYYYMMDD',
    min: laydate.now(),
    max: '20990616',
    istime: true,
    istoday: false,
    choose: function(datas){
        start.max = datas; //结束日选好后，充值开始日的最大日期
    }
};
laydate(start);
laydate(end);
//自定义日期格式
laydate({
    elem: '#test1',
    format: 'YYYY年MM月DD日',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
        alert('得到：'+datas);
    }
});
//日期范围限定在昨天到明天
laydate({
    elem: '#hello3',
    min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
    max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
});








