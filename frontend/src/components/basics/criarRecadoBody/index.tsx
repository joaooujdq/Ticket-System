import { useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import '../criarRecadoBody/index.css'
import { Link } from "react-router-dom";

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
    const [prioridade, setPrioridade] = useState(0);
    const [status, setStatus] = useState(0);
  
    const [inputFuncionarioId, setInputFuncionarioId] = useState('');
    const [inputEmpresaId, setInputEmpresaId] = useState('');
    const [page, setPage] = useState(0);
    const [Emp, setEmp] = useState<iempresa[]>([]);
    const [Func, setFunc] = useState<ifuncionario[]>([]);
    const [Msg, setMsg] = useState<imensagem[]>([]);


    useEffect(() => {
        const findFuncionarioById = async () => {
            const response = await api.get('/v1/ts/funcionarios/' + inputFuncionarioId)
            setFunc(response.data)
            console.log(Func)
        }
        findFuncionarioById()
    }, [inputFuncionarioId])

    useEffect(() => {
        const findEmpresaById = async () => {
            const responses = await api.get('/v1/ts/empresas/' + inputEmpresaId)
            setEmp(responses.data)
            console.log(Emp)
        }
        findEmpresaById()
    }, [inputEmpresaId])

    const postMsg = async () => {
        
        const response = await api.post('/v1/ts/recados/', {
            "status_rec": inputStatus,
            "prioridade_rec": inputPrioridade,
            "setor_rec": inputSetor,
            "mensagem_rec": inputMensagem,
            "empresaDTO": Emp,
            "funcionarioDTO": Func
        });
        window.location.reload();
    }
    return (
        <>
            
            <body id='CriarRecadoBody'>
            <h2 id='TitleBar'>Cadastro de Recado</h2>
                <form id='CriarRecadoUl'>
                <div id='CriarRecadoForm'>
                    <div id='divH1'>
                        <h1>Status: </h1>
                        <h1>Prioridade: </h1>
                        <h1>Setor: </h1>
                        <h2>Mensagem: </h2>
                        <h1>Empresa ID: </h1>
                        <h1>Funcionario ID: </h1>
                    </div>
                    <div id='divInput'>
                        <div id='divRadios'>
                        <div className="radios">
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="exampleRadios" id="statusRadiosInput" value="option1" onChange={e => setInputStatus(e.target.value)} />
                            <label className="form-check-label" >
                            Pendente
                            </label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="exampleRadios" id="statusRadiosInput" value="option2" onChange={e => setInputStatus(e.target.value)} />
                            <label className="form-check-label" >
                            Concluido
                            </label>
                        </div>
                    
                        </div>

                        <div className="radios">
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="exampleRadios1" id="statusRadiosInput" value="option3" onChange={e => setInputPrioridade(e.target.value)}/>
                            <label className="form-check-label" >
                            Alta
                            </label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="exampleRadios1" id="statusRadiosInput" value="option4" onChange={e => setInputPrioridade(e.target.value)}/>
                            <label className="form-check-label" >
                            Média
                            </label>
                        </div>

                        <div className="form-check ">
                            <input className="form-check-input" type="radio" name="exampleRadios1" id="statusRadiosInput" value="option5" onChange={e => setInputPrioridade(e.target.value)}/>
                            <label className="form-check-label" >
                            Baixa
                            </label>
                        </div>
                    </div>
                        </div>
                        <input type="text" value={inputSetor} onChange={e => setInputSetor(e.target.value)} />
                        <textarea  value={inputMensagem} onChange={e => setInputMensagem(e.target.value)} required/>
                        <input type="text" value={inputEmpresaId} onChange={e => setInputEmpresaId(e.target.value)} required/>
                        <input type="text" value={inputFuncionarioId} onChange={e => setInputFuncionarioId(e.target.value)} required/>
                    </div>
                    </div>
                    <button type="submit" onClick={postMsg}>Cadastrar</button>
                </form>
                    


            </body>
        </>
    );
}
export default CriarRecadoBody;
