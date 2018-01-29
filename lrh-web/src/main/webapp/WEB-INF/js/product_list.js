/*function getXhr() {
	var xhr = null;
	if (window.XMLHttpRequest) {
				//非ie浏览器
		xhr = new XMLHttpRequest();
	} else {
			//ie浏览器
		xhr = new ActiveXObject("Microsoft.XMLHttp");
	}
	return xhr;
}*/
/**
 * 奇怪 怎么
 */
$(function(){
	setInterval(getHotProduct,5000);
});

function getHotProduct(){
	$.ajax({
		'url':'hotProduct.product',
		'type':'post',
		'dataType':'json',
		'success':function(data){
			
			$('#hotproduct').empty();
			for(var i=0;i<data.length;i++){
				var s = data[i];
				$('#hotproduct').append(
				'<tr><td>'+s.productClassChinese+
				'</td><td>'+s.productFirm+
				'</td><td>'+s.productType+
				'</td><td>'+s.productPrice+
				'</td><td>'+s.productDesc+'</td></tr>'
				);
			}
		},
		'error':function(){
			$('#hotproduct').html(
					'<tr><td>今天没有促销商品</td></tr>');
		}
	});
}
	
	
	/*var xhr = getXhr();
	xhr.open('post','hotProduct.product',true);
	xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
	xhr.onreadystatechange=function(){
		if(xhr.readyState == 4){
			var txt = xhr.responseText;
			var arr = txt.evalJSON();
			var html = '';
			//<img src="images/${p.productPic }" width="40px">
			//arr[i].productPic
			for(var i=0;i<arr.length;i++){
				html += '<tr><td>'+arr[i].productClassChinese+
				'</td><td>'+arr[i].productFirm+
				'</td><td>'+arr[i].productType+
				'</td><td>'+arr[i].productPrice+
				'</td><td>'+arr[i].productDesc+'</td></tr>';
			}
			$('hotproduct').innerHTML = html;
		}
	};
	xhr.send(null);
}*/