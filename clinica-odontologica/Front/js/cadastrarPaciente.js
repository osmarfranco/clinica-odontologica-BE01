let btn = document.getElementById('btnCadastrarPaciente')
btn.addEventListener('click', event => {
    cadastrarPaciente();
  })

const username = "mateusAnjos"
const password = "senha123"
const profile = "PACIENTE"


function cadastrarPaciente(){
    const data = {
        nome: document.getElementById('inputNome').value,
        sobrenome: document.getElementById('inputSobrenome').value,
        cpf: document.getElementById('inputCPF').value,
        endereco: {
            cep: document.getElementById('inputCEP').value,
            logradouro: document.getElementById('inputLogradouro').value,
            numero: document.getElementById('inputNumero').value,
            complemento: document.getElementById('inputComplemento').value,
            bairro: document.getElementById('inputBairro').value,
            cidade: document.getElementById('inputCidade').value,
            estado: document.getElementById('inputEstado').value
        },
        usuario:{
            username:`${username}`,
            password:`${password}`,
            perfil:`${profile}`
        }

    }
    console.log(data)
    const url = 'http://localhost:8080/paciente'
    const options ={
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }
    fetch(url,options)
}