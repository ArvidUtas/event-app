export function registerUser(): string {
  return `
    <h1>Register New User</h1>
    <form class="form-controll" id="registerUserForm">
      <input type="text" name="username" placeholder="Username" class="form-control"/><br>
      <input type="email" name="email" placeholder="Email" class="form-control"/><br>
      <input type="password" name="password" placeholder="Password" class="form-control"/><br>
      <button type="submit" class="btn border">Register</button>
    </form>
  `;
}


