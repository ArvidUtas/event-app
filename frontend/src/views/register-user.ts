export function registerUser(): string {
  return `
    <h1>Register New User</h1>
    <form class="form-control" id="registerUserForm">
      <input type="text" name="username" placeholder="Username" class="form-control"/><br>
      <input type="email" name="email" placeholder="Email" class="form-control"/><br>
      <input type="password" name="password" placeholder="Password" class="form-control"/><br>
      <button type="submit" class="btn border">Register</button>
    </form>
  `;
}

export function setupRegisterFormListener(){
    const form = document.getElementById("registerUserForm") as HTMLFormElement;
    if (!form) {
        console.error("Form not found");
        return;        
    }
    form.addEventListener("submit", postRegister)
}

interface User {
  username: string
  email: string
  password: string
}

function postRegister(e: Event) {
  e.preventDefault();
  const form = document.getElementById("registerUserForm") as HTMLFormElement;
  if (!form) {
    console.error("Form not found");
    return;
  }
  const formData = new FormData(form);
  const username = formData.get("username") as string;
  const email = formData.get("email") as string;
  const password = formData.get("password") as string;

  const user = { username: username, email: email, password: password };
  const headers: Headers = new Headers();
  headers.set("Content-Type", "application/json");
  headers.set("Accept", "application/json");

  const request: RequestInfo = new Request("http://localhost:8080/register", {
    method: "POST",
    headers: headers,
    body: JSON.stringify(user),
  });

  fetch(request)
    .then((response) => response.text())
    .then((data) => {
      console.log(data);
    })
    .catch((error) => console.error("Error:", error));
}