/**
 *  Tradedun Custom JS File
 */

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

//페이지 최상단 슬라이드 이벤트
function backToTop(){
    //e.preventDefault();
    $('html,body').animate({
        scrollTop: 0
    }, 300);
}

$(window).scroll(function() {
    if ($(document).scrollTop() > 100) {
        $('#back-to-top').addClass('show');
    } else {
        $('#back-to-top').removeClass('show');
    }
});