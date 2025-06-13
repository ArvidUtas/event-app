export function showLogin(): string {
  return `
    <h1>Login Page</h1>
    <form id="loginForm">
      <input type="text" name="username" placeholder="Username"/>
      <input type="password" name="password" placeholder="Password"/>
      <button type="submit">Login</button>
    </form>
  `;
}