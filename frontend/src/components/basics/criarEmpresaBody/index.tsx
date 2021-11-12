import { useEffect, useState } from "react";
import api from "../../../services/api";
import React from 'react'
import Alert from 'react-popup-alert'
import '../criarEmpresaBody/index.css'
const CriarEmpresaBody: React.FC = () => {
    const [post, setPost] = useState(false);
    const [descricao, setDescricao] = useState("");
    const [inputNomeEmp, setInputNomeEmp] = useState('');
    const [inputRazaoEmp, setInputRazaoEmp] = useState('');
    const [inputCnpjEmp, setInputCnpjEmp] = useState('');
    const [inputEmailEmp, setInputEmailEmp] = useState('');
    const [inputEnderecoEmp, setInputEnderecoEmp] = useState('');
    const [inputTelefoneEmp, setInputTelefoneEmp] = useState('');
    async function postMsg() {
        let flag2 = false;
        const response = await api.post('/v1/ts/empresas', {
            "nome": inputNomeEmp,
            "razao": inputRazaoEmp,
            "cnpj": inputCnpjEmp,
            "email": inputEmailEmp,
            "endereco": inputEnderecoEmp,
            "telefone": inputTelefoneEmp
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
    async function onShowAlert(type: string) {
        await setAlert({
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
            <body id='CriarEmpresaBody'>
                <h2 id='TitleBar'>Cadastro de Empresa</h2>
                <ul id='CriarEmpresaUl'>
                    <div id='CriarEmpresaForm'>
                        <div id='divH1'>
                            <h1>Nome*: </h1>
                            <h1>Razão Social*: </h1>
                            <h1>CNPJ*: </h1>
                            <h1>Email*: </h1>
                            <h1>Endereço: </h1>
                            <h1>Telefone: </h1>
                        </div>
                        <div id='divInput'>
                            <input type="text" value={inputNomeEmp} onChange={e => setInputNomeEmp(e.target.value)} required/>
                            <input type="text" value={inputRazaoEmp} onChange={e => setInputRazaoEmp(e.target.value)} required/>
                            <input type="text" value={inputCnpjEmp} onChange={e => setInputCnpjEmp(e.target.value)} required/>
                            <input type="text" value={inputEmailEmp} onChange={e => setInputEmailEmp(e.target.value)} required/>
                            <input type="text" value={inputEnderecoEmp} onChange={e => setInputEnderecoEmp(e.target.value)} />
                            <input type="text" value={inputTelefoneEmp} onChange={e => setInputTelefoneEmp(e.target.value)} />
                        </div>
                    </div>
                    <button type="submit" onClick={postMsg}>Cadastrar</button>
                </ul>
            </body>
        </>
    );
}
export default CriarEmpresaBody;
