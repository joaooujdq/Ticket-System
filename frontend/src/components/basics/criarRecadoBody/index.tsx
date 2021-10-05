
import { useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import '../criarRecadoBody/index.css'

import { Link } from "react-router-dom";
import injectTapEventPlugin from 'react-tap-event-plugin';
injectTapEventPlugin();


interface imensagem {

    status_rec: boolean,
    prioridade_rec: number,
    setor_rec: string,
    mensagem_rec: string,
    data_rec: string,
    hora_rec: string,
    empresaDTO: iempresa,
    funcionarioDTO: ifuncionario,

}

interface ifuncionario {
    codigo_func: number;
    nome_func: string;
    cargo_func: string;
    email_func: string;
    telefone_func: string;
    _links: i_links

}
interface iempresa {
    codigo_emp: number,
    nome_emp: string,
    razao_emp: string,
    cnpj_emp: string,
    email_emp: string,
    endereco_emp: string,
    telefone_emp: string,
    _links: i_links
}


interface i_links {
    self: iself
}

interface iself {
    href: string
}



const CriarRecadoBody: React.FC = () => {

    const [inputStatus, setInputStatus] = useState('');
    const [inputPrioridade, setInputPrioridade] = useState('');
    const [inputSetor, setInputSetor] = useState('');
    const [inputMensagem, setInputMensagem] = useState('');
    const [inputData, setInputData] = useState('');
    const [inputHora, setInputHora] = useState('');
    const [Codigo, setCodigo] = useState('');
    const [inputFuncionarioId, setInputFuncionarioId] = useState('');
    const [inputEmpresaId, setInputEmpresaId] = useState('');
    const [page, setPage] = useState(0);

    const [Emp, setEmp] = useState<iempresa[]>([]);
    const [Func, setFunc] = useState<ifuncionario[]>([]);
    const [Msg, setMsg] = useState<imensagem[]>([]);


    /*
    "status_rec": inputStatus,
    "prioridade_rec": inputPrioridade,
    "setor_rec": inputSetor,
    "mensagem_rec": inputMensagem,
    "data_rec": inputData,
    "hora_rec": inputHora,
    "empresaDTO": Emp,
    "funcionarioDTO": Func
 
    
{
        "status_rec": "teste",
        "prioridade_rec": "teste",
        "setor_rec": "setor",
        "mensagem_rec": "mensagem",
        "data_rec": "data",
        "hora_rec": "hora",
        "empresaDTO": {
            "codigo_emp": 1,
            "nome_emp": "nome_emp",
            "razao_emp": "razao_emp",
            "cnpj_emp": "12345678901234",
            "email_emp": "email_emp",
            "endereco_emp": "endereco_emp",
            "telefone_emp": "1234567890"
        },
        "funcionarioDTO": {
            "codigo_func": 1,
            "nome_func": "nome_func",
            "cargo_func": "cargo_func",
            "email_func": "email_func",
            "telefone_func": "1234567990"
        }
        }
 
 
 
*/
    

    const findFuncionarioById = async (codigo: string) => {
        
        setCodigo(codigo);

        const response = await api.get('/v1/ts/funcionarios/' + Codigo)
       
        setFunc(response.data)
        
        //setFunc((await response).data)
    
        console.log(Func)
        findEmpresaById(inputEmpresaId)

    }

    const findEmpresaById =  async (codigo: string) => {

        setCodigo(codigo);

        const responses =  await api.get('/v1/ts/empresas/' + Codigo)
        //console.log(responses)
        setEmp(responses.data)
        console.log(Emp)
        postMsg()
    }

    const postMsg = async () => {

        const response = await api.post('/v1/ts/recados/', {

            "status_rec": "teste",
            "prioridade_rec": "teste",
            "setor_rec": "setor",
            "mensagem_rec": "mensagem",
            "data_rec": "data",
            "hora_rec": "hora",
            "empresaDTO": Emp,
            "funcionarioDTO": Func
        });

        //window.location.reload();

    }




    return (

        <>

            <body id='CriarRecadoBody'>

                <ul id='CriarRecadoUl'>
                    <div id='divH1'>


                        <h1>Status: </h1>
                        <h1>Prioridade: </h1>
                        <h1>Setor: </h1>
                        <h1>Mensagem: </h1>
                        <h1>Data: </h1>
                        <h1>Hora: </h1>
                        <h1>Funcionario ID: </h1>
                        <h1>Empresa ID: </h1>



                    </div>
                    <div id='divInput'>
                        <input type="text" value={inputStatus} onChange={e => setInputStatus(e.target.value)} />
                        <input type="text" value={inputPrioridade} onChange={e => setInputPrioridade(e.target.value)} />
                        <input type="text" value={inputSetor} onChange={e => setInputSetor(e.target.value)} />
                        <input type="text" value={inputMensagem} onChange={e => setInputMensagem(e.target.value)} />
                        <input type="text" value={inputData} onChange={e => setInputData(e.target.value)} />
                        <input type="text" value={inputHora} onChange={e => setInputHora(e.target.value)} />
                        <input type="text" value={inputFuncionarioId} onChange={e => setInputFuncionarioId(e.target.value)} />
                        <input type="text" value={inputEmpresaId} onChange={e => setInputEmpresaId(e.target.value)} />

                    </div>

                </ul>
                
               
                    <button onTouchTap={() => { findFuncionarioById(inputFuncionarioId);}}>Cadastrar</button>
                



            </body>



        </>
    );

}

export default CriarRecadoBody;



