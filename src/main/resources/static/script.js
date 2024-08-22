function storeToken(token) {
    console.log('Storing token:', token); // Debugging line
    localStorage.setItem('jwtToken', token);
}


function getToken() {
    return localStorage.getItem('jwtToken');
}

document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email, password: password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                storeToken(data.token);
                alert('Login successful!');
            } else {
                alert('Login failed: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
});

document.getElementById('fetchDataButton').addEventListener('click', function () {
    fetch('/protected-endpoint', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + getToken()
        }
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('result').textContent = JSON.stringify(data, null, 2);
        })
        .catch(error => console.error('Error:', error));
});

function logout() {
    localStorage.removeItem('jwtToken');
    alert('Logged out');
}
