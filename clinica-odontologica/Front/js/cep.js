
    const cepInput = document.getElementById('inputCEP')
    const logradouro = document.getElementById('inputLogradouro')
    const bairro = document.getElementById('inputBairro')
    const cidade = document.getElementById('inputCidade')
    const estado = document.getElementById('inputEstado')

    //apenas números
    cepInput.addEventListener("keypress", (e) =>{
        const onlyNumbers = /[0-9]/;
        const key = String.fromCharCode(e.keyCode);
    
        if(!onlyNumbers.test(key)){
            e.preventDefault();
            return;
        }
    });

    //apenas oito dígitos
    cepInput.addEventListener("keyup", (e) =>{
        const inputValue = e.target.value
        if(inputValue.length===8){
            getAddress(inputValue)
        }
    })

    //limpa os campos se cep estiver vazio
    cepInput.addEventListener("keyup", (e)=>{
        const inputValue = e.target.value
        if(inputValue===""){
            logradouro.value = ""
        bairro.value = ""
        cidade.value = ""
        estado.value = ""
        }
    })
    //coleta endereçod a API
    const getAddress = async (cep) =>{
        const apiURL = `https://viacep.com.br/ws/${cep}/json/ `
        const response = await fetch(apiURL)
        const data = await response.json()

        logradouro.value = data.logradouro
        bairro.value = data.bairro
        cidade.value = data.cidade
        estado.value = data.uf
    }


document.getElementById('inputCEP')
.addEventListener('focusout',searchCEP);

