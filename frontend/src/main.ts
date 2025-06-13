import { router } from './router';

window.addEventListener('DOMContentLoaded', router);
window.addEventListener('popstate', router);

document.body.addEventListener('click', (e) => {
  const target = e.target as HTMLElement;
  if (target.matches('[data-link]')) {
    e.preventDefault();
    const href = target.getAttribute('href');
    if (href) {
      history.pushState(null, '', href);
      router();
    }
  }
});

// fetch('http://localhost:8080/hello')
// .then(response => response.text())
// .then(data => {
//   const hello = document.getElementById('app');
//   if (hello) hello.innerText = data;
// })
// .catch(error => console.error('Error:', error));
