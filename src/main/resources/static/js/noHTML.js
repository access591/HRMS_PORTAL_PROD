jQuery(document).ready(function ($) {

		$('#validation-form').validate({
			errorClass: "my-error-class",
			submitHandler: function (form) { // for demo
				//alert('valid form');
				return true
			}
		});

		jQuery.validator.addMethod("noHTML", function (value, element) {
			return this.optional(element) || /^([a-zA-Z0-9]+)$/.test(value);
		}, "No HTML tags are allowed!");


	});