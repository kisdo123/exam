var cal = null;
var year = null;
var month = null;
var today = null;
var checkDate = null;
// 오늘날짜 비교를 위한 캘린더
var reCal = new Date();
var reMonth = reCal.getMonth() + 1; // 오늘 월
var reYear = reCal.getFullYear(); // 오늘 년도
var reday = reCal.getDate(); // 오늘 일

$(document).ready(function() {
	// 이전달로 이동
	$("#prevCalendar").on("click", function() {
		prevCalendar();
	});
	// 다음달로 이동
	$("#nextCalendar").on("click", function() {
		nextCalendar();
	});

});

// 현재 달 달력 만들기
function buildCalendar() {
	var cookie = getCookie("checkDate");
	// 쿠키가 존재하지않으면 데이터 초기화
	if (!cookie) {
		cal = new Date();
		year = cal.getFullYear(); // 년
		month = "0" + (cal.getMonth() + 1); // 월
		today = cal.getDate();
	} else {
		cal = new Date();
		year = cookie.substring(0, 4);
		month = cookie.substring(5, 7);
		today = cal.getDate();
	}

	// 달의 첫번째 날
	var firstDate = new Date(year, month - 1, 1);

	// 달의 마지막날
	var lastDate = new Date(year, month, 0).getDate();

	// id에 해당하는 td에 년 월 찍기
	var tbCalendar = document.getElementById("calendar");
	var tbCalendarYM = document.getElementById("tbCalendarYM");
	tbCalendarYM.innerHTML = year + "년 " + month + "월";

	/* while은 이번달이 끝나면 다음달로 넘겨주는 역할 */
	// 열을 지워줌
	// 기본 열 크기는 body 부분에서 2로 고정되어 있다.
	while (tbCalendar.rows.length > 2) {
		// 테이블의 tr 갯수 만큼의 열 묶음은 -1칸 해줘야지
		// 30일 이후로 담을달에 순서대로 열이 계속 이어진다.
		tbCalendar.deleteRow(tbCalendar.rows.length - 1);
	}

	// 테이블에 새로운 열 삽입//즉, 초기화
	var row = null;
	row = tbCalendar.insertRow();

	// count, 셀의 갯수를 세어주는 역할
	var cnt = 0;

	// 1일이 시작되는 칸을 맞추어 줌
	for (i = 0; i < firstDate.getDay(); i++) {
		/* 이번달의 day만큼 돌림 */
		// 열 한칸한칸 계속 만들어주는 역할
		cell = row.insertCell();
		// 열의 갯수를 계속 다음으로 위치하게 해주는 역할
		cnt = cnt + 1;
	}

	/* 달력 출력 */
	// 1일부터 마지막 일까지 돌림
	for (i = 1; i <= lastDate; i++) {
		// 열 한칸한칸 계속 만들어주는 역할
		cell = row.insertCell();

		// 셀을 1부터 마지막 day까지 HTML 문법에 넣어준다.
		cell.innerHTML = i;

		// 열의 갯수를 계속 다음으로 위치하게 해주는 역할
		cnt = cnt + 1;

		/* 일요일 계산 */
		// 1주일이 7일 이므로 일요일 구하기
		// 월화수목금토일을 7로 나눴을때 나머지가 1이면 cnt가 1번째에 위치함을 의미한다
		if (cnt % 7 == 1) {
			cell.bgColor = "#FFA7A7";
		}

		/* 1주일이 7일 이므로 토요일 구하기 */
		// 월화수목금토일을 7로 나눴을때 나머지가 0이면 cnt가 7번째에 위치함을 의미한다
		if (cnt % 7 == 0) {
			cell.bgColor = "#B2CCFF";
			row = calendar.insertRow();
		}

		/* 오늘의 날짜에 노란색 칠하기 */
		// 달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
		if (year == reYear && month == reMonth && i == reday) {
			cell.bgColor = "#FAF58C";
		}

		// 히든처리한 input의 class이름을 검색
		var event = document.getElementsByClassName('event');

		// 일 기준으로 검색후 맞으면 출근을 추가한다.
		for (var j = 0; j < event.length; j++) {
			if (event[j].value == i) {
				cell.innerHTML += "<div>출근</div>";
			}
		}
	}
}

// 쿠키에 저장된값 가져오기
var getCookie = function(name) {
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value ? value[2] : null;
};

// 이전 달
function prevCalendar() {
	month--;
	cal = new Date(year, month, today);

	if (month < 10) {
		month = "0" + month;
		if (month == 0) {
			month = 12;
			year--;
		}
	}
	checkDate = year + "-" + month;

	// 쿠키에 저장
	document.cookie = "checkDate" + "=" + checkDate;
	location.href = "moveCommute.do?checkDate=" + checkDate;
}

// 다음달
function nextCalendar() {
	month++;
	cal = new Date(year, month, today);

	if (month < 10) {
		month = "0" + month;
	}

	if (month >= 13) {
		month = "0" + 1;
		year++;
	}
	checkDate = year + "-" + month;

	// 쿠키에 저장
	document.cookie = "checkDate" + "=" + checkDate;
	location.href = "moveCommute.do?checkDate=" + checkDate;
}
