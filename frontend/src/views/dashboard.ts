export function showDashboard(): string {
  return `
    <h1>Welcome!</h1>
    <button class="btn btn-success" id="create-event">Create New Event</button>
    <button class="btn btn-danger" id="log-out">Log Out</button>
    <h2 class="mt-5">Find Event</h2>
    <form class="form-control border-0" id="search-event-form">
        <input type="text" class="form-control w-25 mt-2" name="keyword" placeholder="Keyword:">
        <input type="text" class="form-control w-25 mt-2" name="organisedBy" placeholder="Organised by:">
        <input type="text" class="form-control w-25 mt-2" name="venue" placeholder="Venue:">
        <input type="text" class="form-control w-25 mt-2" name="city" placeholder="City:">
        <label for="start-time" class="form-label mt-2" id="start-time">Start time:</label>
        <input type="datetime-local" class="form-control w-25" name="startTime"/>
        <label for="end-time" class="form-label mt-2" id="end-time">End time:</label>
        <input type="datetime-local" class="form-control w-25" name="endTime"/>
        <br>
        <button type="submit" class="btn btn-success border">Search</button>
    </form>
    <div  id="results"  class="list-group mt-4">
      
    </div>
  `;
}

export function setupDashboardListeners() {
  const logOutBtn = document.getElementById("log-out") as HTMLButtonElement;
  logOutBtn?.addEventListener("click", logOut);
  const createEventBtn = document.getElementById(
    "create-event"
  ) as HTMLButtonElement;
  createEventBtn?.addEventListener("click", () => {
    window.location.href = "/create-event";
  });
  const form = document.getElementById("search-event-form") as HTMLFormElement;
  form?.addEventListener("submit", getSearch);
}

function logOut() {
  sessionStorage.removeItem("token");
  window.location.href = "/login";
}

let searchCache: Map<string, EventData> = new Map();

function getSearch(e: Event) {
  e.preventDefault();
  const resultsList = document.getElementById("results") as HTMLElement;
  const form = e.target as HTMLFormElement;
  if (!form) {
    console.error("Form not found");
    return;
  }
  const formData = new FormData(form);
  const params: Record<string, string> = {timeZone: Intl.DateTimeFormat().resolvedOptions().timeZone};

  formData.forEach((value, key) => {
    if (value) {
      params[key] = String(value);
    }
  });
  console.log(params);

  const queryString = new URLSearchParams(params).toString();
  let listHTML: string = "";

  fetch(`http://localhost:8080/events/search?${queryString}`)
    .then((res) => res.json())
    .then((data: EventData[]) => {
      searchCache.clear();
      data.forEach((ev) => {
        searchCache.set(ev.id, ev);
        listHTML += `<button type="button" class="list-group-item list-group-item-action" data-id="${ev.id}">${ev.title}</button>`;
      });
    })
    .then(() => {
      resultsList.innerHTML = listHTML;

      resultsList.querySelectorAll("button").forEach((btn) => {
        btn.addEventListener("click", () => {
          const id = (btn as HTMLButtonElement).dataset.id!;
          showEvent(id);
        });
      });
    });
}

async function showEvent(id: string) {
  let ev: EventData; 

  if (searchCache.has(id)){
    ev = searchCache.get(id)!;
  } else {
    const res = await fetch(`http://localhost:8080/events/ID/${id}`);
    ev = await res.json();
  }

  console.log(ev);
  
}

interface EventData {
  title: string;
  id: string;
  organisedBy: string;
  description: string;
  startTime: string; // ISO datetime string
  endTime: string;
  timeZone: string;
  venue?: string;
  address: {
    addressOne: string;
    postalCode: string;
    city: string;
    addressTwo?: string;
  };
  visibility: "PUBLIC" | "INVITE_ONLY" | "URL_ONLY";
}
