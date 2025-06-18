export function showDashboard(): string {
  return `
    <h1>Welcome!</h1>
    <button class="btn btn-success" id="create-event">Create New Event</button>
    <button class="btn btn-danger" id="log-out">Log Out</button>
  `;
}

export function setupDashboardListeners(){
  const logOutBtn = document.getElementById("log-out") as HTMLButtonElement;
  logOutBtn?.addEventListener("click", logOut);
  const createEventBtn = document.getElementById("create-event") as HTMLButtonElement;
  createEventBtn?.addEventListener("click", () => {window.location.href = "/create-event"});
}

function logOut(){
  sessionStorage.removeItem("token");
  window.location.href="/login";
}