window.onload = function() {
	setting();
	createform();
}

function setting() {
	var member = document.getElementsByClassName('memberName');
	var vacation = document.getElementsByClassName('vacation');
	var attend = document.getElementsByClassName('attend');
	var textData = document.getElementsByClassName('textData');
	var content = document.getElementById('content');

	for (var i = 0; i < attend.length; i++) {
		var vacationable = vacation[i].value;
		if (vacationable == "true") {
			content.value += member[i].value + " : 휴가중\n";
		} else {
			content.value += member[i].value + " : ";
			content.value += attend[i].value + " ";
			content.value += textData[i].value + "\n";
		}
	}

}

function createform() {
	var access_token = "ZQAAAbCOKETePdyxkSVv-Pdqs14uIw12YBTV5mFNpTHLAxbrcXn7PQfSaqCZSPGAi3JBQxzSaOOsZRVo1MOUQhAri1DK8V7_xkQEcnN5KeDuLdWC";
	var band_key = "AACJ3FlYYxAvdo3pvqiEzAqL";
	var url = "https://openapi.band.us/v2.2/band/post/create";

	var form = document.getElementById("writeForm");
	form.setAttribute("method", "Post"); // Get 또는 Post 입력
	form.setAttribute("action", url);

	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "access_token");
	hiddenField.setAttribute("value", access_token);
	form.appendChild(hiddenField);

	hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "band_key");
	hiddenField.setAttribute("value", band_key);
	form.appendChild(hiddenField);
}
function writePosts() {
	form.submit();
}
