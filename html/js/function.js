$(function() {
	$('.menu li').click(function() {
		$('.menu li').each(function() {
			$(this).removeClass('active');
		});
		$(this).addClass('active');
	});

	$('.search input').click(function() {
		if ($(this).val() == 'Country name or number') {
			$(this).val('');
			$(this).css('color', 'white');
		}
	});

	$('.search input').blur(function() {
		if ($(this).val() == '') {
			$(this).val('Country name or number');
			$(this).css('color', '#7E8EB4');
		}
	});

	$('.log a:first').click(function() {
		if ($(this).parent().hasClass('active')) {
			$(this).parent().removeClass('active');
		} else {
			$(this).parent().addClass('active');
		}
	});

	$('.log_form input[type="text"]').click(function() {
		if ($(this).val() == 'username') {
			$(this).val('');
		}
	});

	$('.log_form input[type="text"]').blur(function() {
		if ($(this).val() == '') {
			$(this).val('username');
		}
	});

	$('.log_form input[type="password"]').click(function() {
		if ($(this).val() == 'password') {
			$(this).val('');
		}
	});

	$('.log_form input[type="password"]').blur(function() {
		if ($(this).val() == '') {
			$(this).val('password');
		}
	});

});