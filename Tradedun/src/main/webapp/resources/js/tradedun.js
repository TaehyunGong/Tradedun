/**
 *  Tradedun Custom JS File
 */

//경매장 리스트 클릭시 테이블 down 
$(document).on('click', 'table tbody .trLine', function(){
    $(this).parents().next('.hide').fadeToggle();
});

//경매장 리스트 선택시 이펙트 및 텍스트 수정
$(document).on('click', 'table tbody .auctionList', function(){
	var tbody = $(this).parents().prev('.selectAuction')[0];

	//옵션 부터 가격까지 수정
	for(var i=2; i<5; i++){
		var prev = $(this).children('td').eq(i).html();
		var next = $(tbody).children().children('td').eq(i).html(prev);
	}
	$(this).siblings().removeClass('selectListAuction');
	$(this).addClass('selectListAuction')
	$(tbody).finish().effect( "highlight", {}, 1000 );
	
	var test = $('*[data-price="auctionSelectPiece"]');
	var price = 0;
	test.each(function(i, item){
		price += Number(fn(item.textContent));
	});
	
	var charBox = $(this).parents()[4];
	var charBoxId = $(charBox).attr('id');
	var charBoxNumber = charBoxId.substr(charBoxId.length - 1);
	
	$('#auctionSumPrice' + charBoxNumber).val(numberWithCommas(price));
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