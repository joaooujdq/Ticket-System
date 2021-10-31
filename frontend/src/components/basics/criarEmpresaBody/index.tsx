
import { useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import '../criarEmpresaBody/index.css'
import { Link } from "react-router-dom";


interface iempresa {
    codigo: number,
    nome: string,
    razao: string,
    cnpj: string,
    email: string,
    endereco: string,
    telefone: string
}

const CriarEmpresaBody: React.FC = () => {
   
    const [Emp, setEmp] = useState<iempresa[]>([]);
    const [inputNomeEmp, setInputNomeEmp] = useState('');
    const [inputRazaoEmp, setInputRazaoEmp] = useState('');
    const [inputCnpjEmp, setInputCnpjEmp] = useState('');
    const [inputEmailEmp, setInputEmailEmp] = useState('');
    const [inputEnderecoEmp, setInputEnderecoEmp] = useState('');
    const [inputTelefoneEmp, setInputTelefoneEmp] = useState('');


    async function postMsg() {

        const response = await api.post('/v1/ts/empresas', {
            "nome": inputNomeEmp,
            "razao": inputRazaoEmp,
            "cnpj": inputCnpjEmp,
            "email": inputEmailEmp,
            "endereco": inputEnderecoEmp,
            "telefone":  inputTelefoneEmp 
        }).then(response => response)
            .catch(error => {
                console.log(error.response)
            });
  


            window.location.reload();

    }




    return (

        <>

            <body id='CriarEmpresaBody'>
            <h2 id='TitleBar'>Cadastro de Empresa</h2>
                <ul id='CriarEmpresaUl'>
                    
                <div id='CriarEmpresaForm'>
                <div id='divH1'>
                <h1>Nome: </h1> 
                <h1>Razão Social: </h1> 
                <h1>CNPJ: </h1>
                <h1>Email: </h1>
                <h1>Endereço: </h1>
                <h1>Telefone: </h1>
                </div>

                <div id='divInput'>
                <input type="text" value={inputNomeEmp} onChange={e => setInputNomeEmp(e.target.value)} />  
                <input type="text" value={inputRazaoEmp} onChange={e => setInputRazaoEmp(e.target.value)} />
                <input type="text" value={inputCnpjEmp} onChange={e => setInputCnpjEmp(e.target.value)} />
                <input type="text" value={inputEmailEmp} onChange={e => setInputEmailEmp(e.target.value)} />
                <input type="text" value={inputEnderecoEmp} onChange={e => setInputEnderecoEmp(e.target.value)} />
                <input type="text" value={inputTelefoneEmp} onChange={e => setInputTelefoneEmp(e.target.value)} />
                    
                </div>
                </div>
                
                <button onClick={postMsg}>Cadastrar</button>
            </ul>
           
                
            
          



        </body>

              

        </>
            );

}

export default CriarEmpresaBody;

