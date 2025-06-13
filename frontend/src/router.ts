import { showLogin } from './views/login';
import { showDashboard } from './views/dashboard';

export function router() {
  const path = window.location.pathname;
  const app = document.getElementById('app');
  if (!app) return;

  switch (path) {
    case '/login':
      app.innerHTML = showLogin();
      break;
    case '/dashboard':
      app.innerHTML = showDashboard();
      break;
    default:
      app.innerHTML = `<h1>404 - Page Not Found</h1>`;
  }
}
