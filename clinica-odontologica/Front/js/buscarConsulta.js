function renderTabelaConsultas(){
    const url = 'http://localhost:8080/consulta'
    const options = {
        method: 'GET'
    }
    fetch(url, options)
    .then(response => response.json())
    .then(data =>{
        var tbody = document.getElementById ('tabelaConsultas')
        
        data.map(resp => {
            let tr = document.createElement('tr')
            let tdDentista = document.createElement('td')
            let tdPaciente = document.createElement('td')
            let tdDateTime = document.createElement('td')
            

            tdDentista.innerHTML=resp.dentista.nome
            tdPaciente.innerHTML=resp.paciente.nome
            tdDateTime.innerHTML=resp.dataHoraConsulta
            
            tr.appendChild(tdDentista)
            tr.appendChild(tdPaciente)
            tr.appendChild(tdDateTime)
            

            tbody.appendChild(tr)

        })
    } )
}

window.onload = function (){
    renderTabelaConsultas()
}

