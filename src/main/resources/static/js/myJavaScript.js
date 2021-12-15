
function AadharValidate() {
	var aadhar = document.getElementById("txtAadhar").value;
	var adharcardTwelveDigit = /^\d{12}$/;
	var adharSixteenDigit = /^\d{16}$/;
	if (aadhar != '') {
		if (aadhar.match(adharcardTwelveDigit)) {
			lblAadharCard.style.display = "none";

		}
		else if (aadhar.match(adharSixteenDigit)) {
			lblAadharCard.style.display = "none";

		}
		else {

			lblAadharCard.style.display = "block";

		}
	}
}


function testing() {
	alert("hii");
}


jQuery(document).ready(function($) {

	$('#validation-form').validate({
		errorClass: "my-error-class",
		
       
		submitHandler: function(form) { // for demo
			//alert('valid form');
			return true
		}
	});

	jQuery.validator.addMethod("noHTML", function(value, element) {
		return this.optional(element) || /^([a-z0-9A-Z- ,.'"()]+)$/.test(value);
	}, "No HTML tags are allowed!");


});






jQuery.validator.addMethod("noHTMLtags", function(value, element) {
	if (this.optional(element) || /<\/?[^>]+(>|$)/g.test(value)) {
		return false;
	} else {
		return true;
	}
}, "tags and special character are Not allowed.");

