let btn = document.getElementById('btnCadastrarDentista')
btn.addEventListener('click', event => {
    cadastrarDentista();
  })

function cadastrarDentista(){
    const data = {
        nome: document.getElementById('inputNome').value,
        sobrenome: document.getElementById('inputSobrenome').value,
        cro: document.getElementById('inputCRO').value,
       
    }
    console.log(data)
    
    const url = 'http://localhost:8080/dentista'
    const options ={
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }
    fetch(url,options)
}