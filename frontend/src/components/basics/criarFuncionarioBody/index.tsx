
import { useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import '../criarFuncionarioBody/index.css'
import { Link } from "react-router-dom";


interface ifuncionario {
    nome: string;
    cargo: string;
    email: string;
    telefone: string;
}

const CriarFuncionarioBody: React.FC = () => {
    const [Msg, setMsg] = useState<ifuncionario[]>([]);
    const [inputNomeFunc, setInputNomeFunc] = useState('');
    const [inputCargoFunc, setInputCargoFunc] = useState('');
    const [inputTipoFunc, setInputTipoFunc] = useState('');
    const [inputLoginFunc, setInputLoginFunc] = useState('');



    async function postMsg() {

        const response = api.post('/v1/elx/funcionarios', {
            "nome": inputNomeFunc,
            "cargo": inputCargoFunc,
            "login": inputLoginFunc,
            "senha": "password",
            "tipo": inputTipoFunc
        }).then(response => response)
            .catch(error => {
                console.log(error.response)
            });
            window.location.reload();
    }





    return (
        <>
            <body id='CriarFuncionarioBody'>
                <ul id='CriarFuncionarioUl'>
                    <div id='divH1'>
                        <h1>Nome Funcionario: </h1>
                        <h1>Cargo Funcionario: </h1>
                        <h1>Tipo Funcionario: </h1>
                        <h1>Login: </h1>
                        
                    </div>
                    <div id='divInput'>
                        <input type="text" value={inputNomeFunc} onChange={e => setInputNomeFunc(e.target.value)} />
                        <input type="text" value={inputCargoFunc} onChange={e => setInputCargoFunc(e.target.value)} />
                        <input type="text" value={inputTipoFunc} onChange={e => setInputTipoFunc(e.target.value)} />
                        <input type="text" value={inputLoginFunc} onChange={e => setInputLoginFunc(e.target.value)} />
                        
                    </div>
                </ul>
                <Link id='linkButton' to='/funcionarios'>
                <button onClick={postMsg}>Cadastrar</button>
                </Link>
                
            </body>
        </>
    );
}

export default CriarFuncionarioBody;

