/**
 *  Tradedun Custom JS File
 */

//경매장 리스트 클릭시 테이블 down 
$(document).on('click', 'table tbody .trLine', function(){
    $(this).parents().next('.hide').fadeToggle();
});

//input type[text] 의 onlyNumber는 무조건 onlyNumber이다.
$(document).on("keyup", "input:text[onlyNumber]", function() {
    $(this).val( $(this).val().replace(/[^0-9]/gi,"") );
});

//정규식을 사용해 숫자만 출력
function fn(str){
	var res;
	res = str.replace(/[^0-9]/g,"");
	return res;
}

//정규식을 사용해 숫자의 3자리마다 콤마 찍기
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//엔터 이벤트
function enterkey(num) {
    if (window.event.keyCode == 13) {
    	search(num);
    }
}