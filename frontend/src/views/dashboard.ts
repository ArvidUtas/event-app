export function showDashboard(): string {
  return `
    <h1>Welcome!</h1>
    <button class="btn btn-danger" id="logOut">Log Out</button>
  `;
}

export function setupDashboardListeners(){
  const logOutBtn = document.getElementById("logOut") as HTMLButtonElement;
  logOutBtn?.addEventListener("click", logOut);
}

function logOut(){
  sessionStorage.removeItem("token");
  window.location.href="/login";
}