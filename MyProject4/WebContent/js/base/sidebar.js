// work with sidebar
var btn = document.getElementById('sliderBtn'),
	sideBar = document.getElementById('sideBar'),
	sideBarHideBtn = document.getElementById('sideBarHideBtn');

// show sidebar 
btn.addEventListener('click', function() {
	if (sideBar.classList.contains('md:-ml-64')) {
		sideBar.classList.replace('md:-ml-64', 'md:ml-0');
		sideBar.classList.remove('md:slideOutLeft');
		sideBar.classList.add('md:slideInLeft');
	};
});

// hide sideBar    
sideBarHideBtn.addEventListener('click', function() {
	if (sideBar.classList.contains('md:ml-0', 'slideInLeft')) {
		let animate = function() {
			sideBar.classList.remove('md:slideInLeft');
			sideBar.classList.add('md:slideOutLeft');

			setTimeout(() => {
				sideBar.classList.replace('md:ml-0', 'md:-ml-64');
			}, 300);
		};
		animate();
	};
});
// end with sidebar
