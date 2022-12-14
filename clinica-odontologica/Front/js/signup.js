let btn = document.getElementById('btnCriarUsuario')
btn.addEventListener('click', event => {
    signup();
  })

function signup(){
    const data = {
        username: document.getElementById('inputUsername').value,
        password: document.getElementById('inputPassword').value,
       
    }
    console.log(data)
    
    const url = 'http://localhost:8080/auth'
    const options ={
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }
    fetch(url,options)
}