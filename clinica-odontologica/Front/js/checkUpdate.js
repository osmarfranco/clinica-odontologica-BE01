
let buscaValue = document.getElementById('buscaUpdate').value
let campoBusca = document.getElementById('buscaUpdate')
let tabela = document.getElementById('areaTabela')
const buscaBtn = document.getElementById('buscaBtn')
        

function track() {
    
    let checkboxAlterarPaciente = document.getElementById("checkboxAlterarPaciente");
    let checkboxAlterarDentista = document.getElementById("checkboxAlterarDentista");
    let checkboxAlterarConsulta = document.getElementById("checkboxAlterarConsulta");
  

    buscaBtn.addEventListener("click", () =>{
        if (checkboxAlterarPaciente.checked == true){
            tabela.innerHTML=""
            renderTabelaPacientesEspecifico()}
            else{
                if(checkboxAlterarDentista.checked == true){
                    tabela.innerHTML=""
                    renderTabelaDentistasEspecifico()
                }else{
                    if(checkboxAlterarConsulta.checked == true){
                        tabela.innerHTML=""
                        renderTabelaConsultasEspecifica()
                    }
                }
            }
    })

    if (checkboxAlterarPaciente.checked == true){
        console.log("selecionou alterar paciente")
        tabela.innerHTML=""
        campoBusca.value=""
        buscaValue.value = null
    } else {
        if (checkboxAlterarDentista.checked == true){
            console.log("selecionou alterar dentista")
            tabela.innerHTML=""
            campoBusca.value=""
            buscaValue.value = null
          } else {
            if (checkboxAlterarConsulta.checked == true){
                console.log("selecionou alterar consulta")
                tabela.innerHTML=""
                campoBusca.value=""
                buscaValue.value = null
        }
}
}
}



function renderTabelaPacientesEspecifico(){
    const url = `http://localhost:8080/paciente?cpf=${buscaValue}`
    const options = {
        method: 'GET'
        
    }
    fetch(url, options)
    .then(response => response.json())
    .then(data =>{
        //criando area da tabela
        tabela.innerHTML=""
        tabela.setAttribute("id", "tabelaPacientes")
        tabela.className="table"
        let thead = document.createElement('thead')
        thead.className="thead-dark"
        let tbody = document.createElement('tbody')
        let thtr =document.createElement('tr')
        let thNome = document.createElement('th')
        let thSobrenome = document.createElement('th')
        let thCPF = document.createElement('th')
        let thCEP = document.createElement('th')
        let thLogradouro = document.createElement('th')
        let thNumero = document.createElement('th')
        let thComplemento = document.createElement('th')
        let thBairro = document.createElement('th')
        let thCidade = document.createElement('th')
        let thEstado = document.createElement('th')
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


        //criando campos do cabeçalho
        thNome.innerHTML = "Nome"
        thSobrenome.innerHTML = "Sobrenome"
        thCPF.innerHTML = "CPF"
        thCEP.innerHTML = "CEP"
        thLogradouro.innerHTML = "Logradouro"
        thNumero.innerHTML = "Numero"
        thComplemento.innerHTML = "Complemento"
        thBairro.innerHTML = "Bairro"
        thCidade.innerHTML = "Cidade"
        thEstado.innerHTML = "Estado"

        //var tbody = document.getElementById ('tabelaPacientes')
        
        data.map(resp => {
           

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

           
            tabela.appendChild(thead)
            tabela.appendChild(tbody)
            thead.appendChild(thtr)
            thtr.appendChild(thNome)
            thtr.appendChild(thSobrenome)
            thtr.appendChild(thCPF)
            thtr.appendChild(thCEP)
            thtr.appendChild(thLogradouro)
            thtr.appendChild(thNumero)
            thtr.appendChild(thComplemento)
            thtr.appendChild(thBairro)
            thtr.appendChild(thCidade)
            thtr.appendChild(thEstado)

        })
    } )
}

function renderTabelaDentistasEspecifico(){

    const url = `http://localhost:8080/dentista?cro=${buscaValue}`
    const options = {
        method: 'GET'
        
    }
    fetch(url, options)
    .then(response => response.json())
    .then(data =>{

        //criando area da tabela

        
        tabela.innerHTML=""
        tabela.setAttribute("id", "tabelaDentistas")
        tabela.className="table"
        let thead = document.createElement('thead')
        thead.className="thead-dark"
        let tbody = document.createElement('tbody')
        let thtr =document.createElement('tr')
        let thNome = document.createElement('th')
        let thSobrenome = document.createElement('th')
        let thCRO = document.createElement('th')
        let tr = document.createElement('tr')
            let tdNome = document.createElement('td')
            let tdSobrenome = document.createElement('td')
            let tdCRO = document.createElement('td')

        thNome.innerHTML = "Nome"
        thSobrenome.innerHTML = "Sobrenome"
        thCRO.innerHTML = "CRO"
        
        //var tbody = document.getElementById ('tabelaDentistas')
        
        data.map(resp => {
            

            tdNome.innerHTML=resp.nome
            tdSobrenome.innerHTML=resp.sobrenome
            tdCRO.innerHTML=resp.cro

            tr.appendChild(tdNome)
            tr.appendChild(tdSobrenome)
            tr.appendChild(tdCRO)

            

        })
            tbody.appendChild(tr)

        
            tabela.appendChild(thead)
            tabela.appendChild(tbody)
            thead.appendChild(thtr)
            thtr.appendChild(thNome)
            thtr.appendChild(thSobrenome)
            thtr.appendChild(thCRO)
    } )
}

function renderTabelaConsultasEspecifica(){
    const url = `http://localhost:8080/consulta?cro=${buscaValue}`
    const options = {
        method: 'GET'
        
    }
    fetch(url, options)
    .then(response => response.json())
    .then(data =>{

        tabela.innerHTML=""
        tabela.setAttribute("id", "tabelaConsultas")
        tabela.className="table"
        let thead = document.createElement('thead')
        thead.className="thead-dark"
        let tbody = document.createElement('tbody')
        let thtr =document.createElement('tr')
        var thItem = document.createElement('th')
        let thDentista = document.createElement('th')
        let thPaciente = document.createElement('th')
        let thDateTime = document.createElement('th')
        let tr = document.createElement('tr')
        let tdDentista = document.createElement('td')
        let tdPaciente = document.createElement('td')
        let tdDateTime = document.createElement('td')

        thItem.innerHTML = "#"
        thDentista.innerHTML = "Dentista"
        thPaciente.innerHTML = "Paciente"
        thDateTime.innerHTML = "Data e Hora"

        //var tbody = document.getElementById ('tabelaConsultas')
        
        data.map(resp => {
           

            tdDentista.innerHTML=resp.dentista.nome
            tdPaciente.innerHTML=resp.paciente.nome
            tdDateTime.innerHTML=resp.dataHoraConsulta

            tr.appendChild(tdDentista)
            tr.appendChild(tdPaciente)
            tr.appendChild(tdDateTime)

            tbody.appendChild(tr)


            tabela.appendChild(thead)
            tabela.appendChild(tbody)
            thead.appendChild(thtr)
            thtr.appendChild(thDentista)
            thtr.appendChild(thPaciente)
            thtr.appendChild(thDateTime)

        })
    } )
}