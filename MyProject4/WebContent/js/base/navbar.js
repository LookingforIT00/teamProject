var navbarToggle = document.getElementById('navbarToggle'),
	navbar = document.getElementById('navbar');

let navShow = -1;
navbarToggle.addEventListener('click', function() {
	if (navShow) {
		if (navShow === -1) {
			navbar.classList.remove('md:hidden');
		} else {
			navbar.classList.remove('fadeOut');
		}
		navbar.classList.add('fadeIn');
	} else {
		navbar.classList.remove('fadeIn');
		navbar.classList.add('fadeOut');
	};
	navShow = !navShow;
});