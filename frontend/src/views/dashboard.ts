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
        <label for="start-time" class="form-label mt-2">Start time:</label>
        <input type="datetime-local" class="form-control w-25" name="startTime" id="start-time"/>
        <label for="end-time" class="form-label mt-2">End time:</label>
        <input type="datetime-local" class="form-control w-25" name="endTime" id="end-time"/>
        <br>
        <button type="submit" class="btn btn-success border">Search</button>
    </form>
    <div  id="results"  class="list-group mt-4">
      
    </div>
    <!-- Modal -->
    <div class="modal fade" id="eventModal" tabindex="-1" aria-labelledby="eventModalLabel" aria-inert="true">
      <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title ms-3" id="modalTitle"></h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body ms-3">
            <h5>Description:</h5>
            <div id="modalDescription"></div>
            <h5 class="mt-3">Organised by:</h5>
            <div id="modalOrg"></div>
            <h5 class="mt-3">Venue:</h5>
            <div id="modalVenue"></div>
            <h5 class="mt-3">Address:</h5>
            <div id="modalAddress"></div>
            <h5 class="mt-3">Start time:</h5>
            <div id="modalStart"></div>
            <h5 class="mt-3">End time:</h5>
            <div id="modalEnd"></div>
            <h5 class="mt-3">Visibility:</h5>
            <div id="modalVis"></div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
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
        listHTML += `<button type="button" class="list-group-item list-group-item-action" data-id="${ev.id}" 
          data-bs-toggle="modal" data-bs-target="#eventModal">${ev.title}</button>`;
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

  const utcStartTime = ev.startTime;
  const utcEndTime = ev.endTime;
  const eventTZ = ev.timeZone;
  const formatter = new Intl.DateTimeFormat("sv-SE", {
    timeZone: eventTZ,
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  });
  const formStartTime = formatter.format(new Date(utcStartTime));
  const formEndTime = formatter.format(new Date(utcEndTime));
  const visFormatted =
    ev.visibility === "PUBLIC" ? "Public":
    ev.visibility === "INVITE_ONLY" ? "Invite only":
    ev.visibility === "URL_ONLY" ? "Anyone with the URL":
    ev.visibility;

  const title = document.getElementById("modalTitle") as HTMLHeadingElement;
  const description = document.getElementById("modalDescription") as HTMLDivElement;
  const org = document.getElementById("modalOrg") as HTMLDivElement;
  const venue = document.getElementById("modalVenue") as HTMLDivElement;
  const address = document.getElementById("modalAddress") as HTMLDivElement;
  const startTime = document.getElementById("modalStart") as HTMLDivElement;
  const endTime = document.getElementById("modalEnd") as HTMLDivElement;
  const visibility = document.getElementById("modalVis") as HTMLDivElement;
  title.innerText = ev.title;
  description.innerText = ev.description;
  org.innerText = ev.organisedBy;
  venue.innerText = ev.venue ?? "";
  address.innerHTML = `${ev.address.addressOne}<br>${ev.address.addressTwo ?? ""}
    <br>${ev.address.postalCode} ${ev.address.city}`;
  startTime.innerText = formStartTime;
  endTime.innerText = formEndTime;
  visibility.innerText = visFormatted;
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
