export function showLogin(): string {
  return `
    <h1>Login Page</h1>
    <form id="loginForm" class="row g-3">
    <div class="col-auto">
    <input type="text" name="username" placeholder="Username" class="form-control"/>
    </div>
    <div class="col-auto">
    <input type="password" name="password" placeholder="Password" class="form-control"/>
    </div>
    <div class="col-auto">
    <button type="submit" class="btn border">Login</button>
    </div>
    </form>
    <div class="text-danger mt-2" id="loginMessage"></div>
  `;
}

export function setupLoginFormListener(){
  const form = document.getElementById("loginForm") as HTMLFormElement;
  if (!form){
    console.error("Form not found");
    return;    
  }
  form.addEventListener("submit",postLogin)
}

function postLogin(e:Event){
  e.preventDefault();
  const form = document.getElementById("loginForm") as HTMLFormElement;
  if (!form) {
    console.error("Form not found");
    return;
  }
  const formData = new FormData(form);
  const user = {
    username: formData.get("username") as string,
    password: formData.get("password") as string,
  };

  const headers = new Headers();
  headers.set("Content-Type", "application/json");
  headers.set("Accept", "application/json");

  const request: RequestInfo = new Request("http://localhost:8080/login", {
    method: "POST",
    headers: headers,
    body: JSON.stringify(user),
  });

  fetch(request)
    .then((response) => {
      if (response.ok) {
        sessionStorage.setItem("token","isLoggedIn")
        window.location.href = "/dashboard";
      } else {
        response.text().then((message) => {
          const loginMessage = document.getElementById("loginMessage");
          if (loginMessage) loginMessage.innerText = message;
        });
      }
    })
    .catch((error) => console.error("Error: ", error));
}
