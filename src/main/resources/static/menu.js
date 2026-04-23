const menuToggle = document.getElementById('menu-toggle');
const menuClose = document.getElementById('menu-close');
const sideMenu = document.getElementById('side-menu');
const dropdownMenu = document.getElementById('dropdown-menu');
const overlay = document.getElementById('overlay');

const isDesktop = () => window.innerWidth >= 768;

menuToggle.addEventListener('click', (e) => {
    e.stopPropagation();
    if (isDesktop()) {
        // Desktop : dropdown à droite
        dropdownMenu.classList.toggle('closed');
        dropdownMenu.classList.toggle('open');
    } else {
        // Mobile : menu latéral
        sideMenu.classList.remove('closed');
        sideMenu.classList.add('open');
        overlay.classList.add('active');
    }
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

// Fermer le dropdown en cliquant ailleurs
document.addEventListener('click', (e) => {
    if (dropdownMenu && !dropdownMenu.contains(e.target) && e.target !== menuToggle) {
        dropdownMenu.classList.add('closed');
        dropdownMenu.classList.remove('open');
    }
});
