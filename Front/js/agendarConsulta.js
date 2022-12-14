let btn = document.getElementById('btnAgendarConsulta')
btn.addEventListener('click', event => {
    agendarConsulta();
  })

function agendarConsulta(){
    const data = {
        dentista:{ cro:document.getElementById('inputDentista').value,},
        paciente:{cpf:document.getElementById('inputPaciente').value,},
        dataHoraConsulta: document.getElementById('inputDateTime').value,
       
    }
    console.log(data)
    
    const url = 'http://localhost:8080/consulta'
    const options ={
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }
    fetch(url,options)
}