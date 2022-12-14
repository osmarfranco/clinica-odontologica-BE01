function renderTabelaDentista(){
    const url = 'http://localhost:8080/dentista'
    const options = {
        method: 'GET'
    }
    fetch(url, options)
    .then(response => response.json())
    .then(data =>{
        var tbody = document.getElementById ('tabelaDentistas')
        
        data.map(resp => {
            let tr = document.createElement('tr')
            let tdNome = document.createElement('td')
            let tdSobrenome = document.createElement('td')
            let tdCRO = document.createElement('td')

            tdNome.innerHTML=resp.nome
            tdSobrenome.innerHTML=resp.sobrenome
            tdCRO.innerHTML=resp.cro

            tr.appendChild(tdNome)
            tr.appendChild(tdSobrenome)
            tr.appendChild(tdCRO)

            tbody.appendChild(tr)

        })
    } )
}

window.onload = function (){
    renderTabelaDentista()
}
