import { showLogin, setupLoginFormListener } from './views/login';
import { registerUser, setupRegisterFormListener } from './views/register-user';
import { showDashboard, setupDashboardListeners } from './views/dashboard';

function isAuthenticated(): boolean {
  return !!sessionStorage.getItem("token");
}

export function router() {
  const path = window.location.pathname;
  const app = document.getElementById('app');
  if (!app) return;

  switch (path) {
    case '/login':
      app.innerHTML = showLogin();
      setupLoginFormListener();
      break;
    case '/register-user':
      app.innerHTML = registerUser();
      setupRegisterFormListener();
      break;
    case '/dashboard':
      if(!isAuthenticated()){
        app.innerHTML = showLogin();
        setupLoginFormListener();
        return;
      }
      app.innerHTML = showDashboard();
      setupDashboardListeners();
      break;
    default:
      app.innerHTML = `<h1>404 - Page Not Found</h1>`;
  }
}
