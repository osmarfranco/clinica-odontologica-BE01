const busca = document.getElementById('buscaPaciente').value

function renderTabelaPacientes(){
    const url = 'http://localhost:8080/paciente'
    const options = {
        method: 'GET'
    }
    fetch(url, options)
    .then(response => response.json())
    .then(data =>{
        var tbody = document.getElementById ('tabelaPacientes')
        
        data.map(resp => {
            let tr = document.createElement('tr')
            let tdNome = document.createElement('td')
            let tdSobrenome = document.createElement('td')
            let tdCPF = document.createElement('td')
            let tdCEP = document.createElement('td')
            let tdLogradouro = document.createElement('td')
            let tdNumero = document.createElement('td')
            let tdComplemento = document.createElement('td')
            let tdBairro = document.createElement('td')
            let tdCidade = document.createElement('td')
            let tdEstado = document.createElement('td')

            tdNome.innerHTML=resp.nome
            tdSobrenome.innerHTML=resp.sobrenome
            tdCPF.innerHTML=resp.cpf
            tdCEP.innerHTML=resp.endereco.cep
            tdLogradouro.innerHTML=resp.endereco.logradouro
            tdNumero.innerHTML=resp.endereco.numero
            tdComplemento.innerHTML=resp.endereco.complemento
            tdBairro.innerHTML=resp.endereco.bairro
            tdCidade.innerHTML=resp.endereco.cidade
            tdEstado.innerHTML=resp.endereco.estado

            tr.appendChild(tdNome)
            tr.appendChild(tdSobrenome)
            tr.appendChild(tdCPF)
            tr.appendChild(tdCEP)
            tr.appendChild(tdLogradouro)
            tr.appendChild(tdNumero)
            tr.appendChild(tdComplemento)
            tr.appendChild(tdBairro)
            tr.appendChild(tdCidade)
            tr.appendChild(tdEstado)

            tbody.appendChild(tr)

        })
    } )
}

function renderTabelaPacientesEspecifico(){
    const url = `http://localhost:8080/paciente?cpf=${busca}`
    const options = {
        method: 'GET'
        
    }
    fetch(url, options)
    .then(response => response.json())
    .then(data =>{
        var tbody = document.getElementById ('tabelaPacientes')
        
        data.map(resp => {
            let tr = document.createElement('tr')
            let tdNome = document.createElement('td')
            let tdSobrenome = document.createElement('td')
            let tdCPF = document.createElement('td')
            let tdCEP = document.createElement('td')
            let tdLogradouro = document.createElement('td')
            let tdNumero = document.createElement('td')
            let tdComplemento = document.createElement('td')
            let tdBairro = document.createElement('td')
            let tdCidade = document.createElement('td')
            let tdEstado = document.createElement('td')

            tdNome.innerHTML=resp.nome
            tdSobrenome.innerHTML=resp.sobrenome
            tdCPF.innerHTML=resp.cpf
            tdCEP.innerHTML=resp.cep
            tdLogradouro.innerHTML=resp.logradouro
            tdNumero.innerHTML=resp.numero
            tdComplemento.innerHTML=resp.complemento
            tdBairro.innerHTML=resp.bairro
            tdCidade.innerHTML=resp.cidade
            tdEstado.innerHTML=resp.estado

            tr.appendChild(tdNome)
            tr.appendChild(tdSobrenome)
            tr.appendChild(tdCPF)
            tr.appendChild(tdCEP)
            tr.appendChild(tdLogradouro)
            tr.appendChild(tdNumero)
            tr.appendChild(tdComplemento)
            tr.appendChild(tdBairro)
            tr.appendChild(tdCidade)
            tr.appendChild(tdEstado)

            tbody.appendChild(tr)

        })
    } )
}

window.onload = function (){
    renderTabelaPacientes()
}