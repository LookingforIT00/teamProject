var navbarToggle = document.getElementById('navbarToggle'),
	navbar = document.getElementById('navbar');

navbarToggle.addEventListener('click', function() {
	if (navbar.classList.contains('md:hidden')) {
		navbar.classList.remove('md:hidden');
		navbar.classList.add('fadeIn');
	} else if (navbar.classList.contains('fadeIn')) {
		navbar.classList.remove('fadeIn');
		navbar.classList.add('fadeOut');

		setTimeout(() => {
			navbar.classList.remove('fadeOut');
			navbar.classList.add('md:hidden');
		}, 450);
	};
});