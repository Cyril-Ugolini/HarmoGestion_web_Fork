const menuToggle = document.getElementById('menu-toggle');
const menuClose = document.getElementById('menu-close');
const sideMenu = document.getElementById('side-menu');
const overlay = document.getElementById('overlay');

menuToggle.addEventListener('click', () => {
    sideMenu.classList.remove('closed');
    sideMenu.classList.add('open');
    overlay.classList.add('active');
});

menuClose.addEventListener('click', () => {
    sideMenu.classList.add('closed');
    sideMenu.classList.remove('open');
    overlay.classList.remove('active');
});

overlay.addEventListener('click', () => {
    sideMenu.classList.add('closed');
    sideMenu.classList.remove('open');
    overlay.classList.remove('active');
});
