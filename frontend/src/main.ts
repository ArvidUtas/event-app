fetch('http://localhost:8080/hello')
.then(response => response.text())
.then(data => {
  const hello = document.getElementById('app');
  if (hello) hello.innerText = data;
})
.catch(error => console.error('Error:', error));
