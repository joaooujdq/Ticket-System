import { useEffect, useState } from "react";
import api from "../../../services/api";
import React from 'react'
import Alert from 'react-popup-alert'
import '../criarRecadoBody/index.css'
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
    const [post, setPost] = useState(false);
    const [descricao, setDescricao] = useState("");
    const [inputFuncionarioId, setInputFuncionarioId] = useState('');
    const [inputEmpresaId, setInputEmpresaId] = useState('');
    const [Emp, setEmp] = useState<iempresa[]>([]);
    const [Func, setFunc] = useState<ifuncionario[]>([]);
    const postMsg = async () => {
        let flag2 = false;
        const response = await api.post('/v1/ts/recados/', {
            "status_rec": inputStatus,
            "prioridade_rec": inputPrioridade,
            "setor_rec": inputSetor,
            "mensagem_rec": inputMensagem,
            "empresaDTO": Emp,
            "funcionarioDTO": Func
        }).then(response => response.data)
            .catch(async error => {
                if (error.response) {
                    await setDescricao(error.response.data.descricao)
                    flag2 = true;
                    setPost(!post)
                }
            });
        if (!flag2) {
            window.location.reload();
        }
    }
    useEffect(() => {
        if (post) {
            onShowAlert('error')
        }
    }, [post])
    useEffect(() => {
        const findFuncionarioById = async () => {
           
            const response = await api.get('/v1/ts/funcionarios/' + inputFuncionarioId)
            setFunc(response.data)
        }
        findFuncionarioById()
    }, [inputFuncionarioId])
    useEffect(() => {
        const findEmpresaById = async () => {
  
            const responses = await api.get('/v1/ts/empresas/' + inputEmpresaId)
            setEmp(responses.data)
        }
        findEmpresaById()
    }, [inputEmpresaId])
    const [alert, setAlert] = React.useState({
        type: 'error',
        text: descricao,
        show: false
    })
    function onCloseAlert() {
        setAlert({
            type: '',
            text: '',
            show: false
        })
        setPost(!post)
    }
    function onShowAlert(type: string) {
        setAlert({
            type: type,
            text: descricao,
            show: true
        })
    }
    return (
        <>
            <Alert
                header={''}
                btnText={'Fechar'}
                text={alert.text}
                type={alert.type}
                show={alert.show}
                onClosePress={onCloseAlert}
                pressCloseOnOutsideClick={true}
                showBorderBottom={true}
                alertStyles={{
                    "background-color": "#f8f9fa",
                    "width": "300px",
                    "height": "100px",
                    "display": "flex",
                    "flex-direction": "column",
                    "align-items": "center",
                    "justify-content": "center",
                    "left": "42%",
                    "bottom": "30%",
                    "border-radius": "8px",
                    "border": "2px solid #C4C4C4",
                    "position": "absolute"
                }}
                headerStyles={{}}
                textStyles={{}}
                buttonStyles={{
                    "background-color": "#efefef",
                    "border-radius": "8px",
                    "margin-bottom": "10px",
                    "text-decoration": "none",
                    "button-decoration": "none",
                    "align-text": "center",
                    "width": "70px",
                    "border": "2px solid #C4C4C4",
                    "height": "30px",
                    "color": "#000",
                    "padding-left": "10px"
                }}
            />
            <body id='CriarRecadoBody'>
                <h2 id='TitleBar'>Cadastro de Recado</h2>
                <form id='CriarRecadoUl'>
                    <div id='CriarRecadoForm'>
                        <div id='divH1'>
                            <h1>Status: </h1>
                            <h1>Prioridade: </h1>
                            <h1>Setor: </h1>
                            <h2>Mensagem*: </h2>
                            <h1>Empresa ID*: </h1>
                            <h1>Funcionario ID*: </h1>
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
                                        <input className="form-check-input" type="radio" name="exampleRadios1" id="statusRadiosInput" value="option3" onChange={e => setInputPrioridade(e.target.value)} />
                                        <label className="form-check-label" >
                                            Alta
                                        </label>
                                    </div>
                                    <div className="form-check">
                                        <input className="form-check-input" type="radio" name="exampleRadios1" id="statusRadiosInput" value="option4" onChange={e => setInputPrioridade(e.target.value)} />
                                        <label className="form-check-label" >
                                            Média
                                        </label>
                                    </div>
                                    <div className="form-check ">
                                        <input className="form-check-input" type="radio" name="exampleRadios1" id="statusRadiosInput" value="option5" onChange={e => setInputPrioridade(e.target.value)} />
                                        <label className="form-check-label" >
                                            Baixa
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <input type="text" value={inputSetor} onChange={e => setInputSetor(e.target.value)} />
                            <textarea value={inputMensagem} onChange={e => setInputMensagem(e.target.value)} required/>
                            <input type="text" value={inputEmpresaId} onChange={e => setInputEmpresaId(e.target.value)} required/>
                            <input type="text" value={inputFuncionarioId} onChange={e => setInputFuncionarioId(e.target.value)} required/>
                        </div>
                    </div>
                    <button type="submit" onClick={postMsg}  >Cadastrar</button>
                </form>
            </body>
        </>
    );
}
export default CriarRecadoBody;
