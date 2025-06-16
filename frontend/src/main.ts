import { router } from './router';

window.addEventListener('DOMContentLoaded', router);
window.addEventListener('popstate', router);
window.addEventListener('submit', postRegister);

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