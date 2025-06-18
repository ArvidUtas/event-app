export function createEvent() : string {
    return `
      <h1>Create New Event</h1>
      <form class="form-control" id="create-event-form">
        <input type="text" name="title" placeholder="Title" class="form-control"/><br>
        <textarea name="description" placeholder="Description" class="form-control" rows="4"></textarea><br>
        <label for="start-time" class="form-label">Start time:</label>
        <input type="datetime-local" name="start-time" class="form-control"/><br>
        <label for="start-time" class="form-label">End time:</label>
        <input type="datetime-local" name="end-time" class="form-control"/><br>
        <input type="text" name="venue" placeholder="Venue (optional)" class="form-control"/><br>
        <input type="text" name="address-one" placeholder="Address Line 1" class="form-control"/><br>
        <input type="text" name="address-two" placeholder="Address Line 2" class="form-control"/><br>
        <input type="text" name="postal-code" placeholder="Postal code" class="form-control"/><br>
        <input type="text" name="city" placeholder="City" class="form-control"/><br>
        <select class="form-select" aria-label="Visibility select" name="visibility">
          <option selected disabled value="">Who can see this event?</option>
          <option value="PUBLIC">Public</option>
          <option value="INVITE_ONLY">Invite only</option>
          <option value="URL_ONLY">Anyone with the URL</option>
        </select><br>
        <button type="submit" class="btn btn-success border">Create event</button>
      </form>
      <div class="text-danger mt-2" id="create-event-message"></div>`;
}

export function setupCreateEventListeners(){
    const form = document.getElementById("create-event-form") as HTMLFormElement;
    if (!form) {
        console.error("Form not found");
        return;        
    }
    form.addEventListener("submit", postNewEvent);
}

function postNewEvent(e:Event){
    e.preventDefault();
    const form = e.target as HTMLFormElement;
    if (!form){
        console.error("Form not found");
        return;
    }

    const formData = new FormData(form);
    
    const eventData: EventFormData = {
        title: formData.get("title") as string,
        description: formData.get("description") as string,
        startTime: formData.get("start-time") as string,
        endTime: formData.get("end-time") as string,
        venue: (formData.get("venue") as string) || undefined,
        address: {
            addressOne: formData.get("address-one") as string,
            addressTwo: (formData.get("address-two") as string) || undefined,
            postalCode: formData.get("postal-code") as string,
            city: formData.get("city") as string},
        visibility: formData.get("visibility") as "PUBLIC" | "INVITE_ONLY" | "URL_ONLY",
    };

    const headers = new Headers();
    headers.set("Content-Type", "application/json");
    headers.set("Accept", "application/json");

    const request: RequestInfo = new Request("http://localhost:8080/addEvent", {
    method: "POST",
    headers: headers,
    body: JSON.stringify(eventData),
  });

  fetch(request)
  .then((response) => {
    if (response.ok){
        response.text().then((message) => {
                const eventMessage = document.getElementById("create-event-message");
                if (eventMessage) eventMessage.innerText = message;
        console.log("ok");        
    })
  }})
}

interface EventFormData {
  title: string;
  description: string;
  startTime: string; // ISO datetime string
  endTime: string;
  venue?: string;
  address: {addressOne: string,
    postalCode: string,
    city: string,
    addressTwo?: string}
  visibility: "PUBLIC" | "INVITE_ONLY" | "URL_ONLY";
}