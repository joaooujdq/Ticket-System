
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
    const [inputEmailFunc, setInputEmailFunc] = useState('');
    const [inputTelefoneFunc, setInputTelefoneFunc] = useState('');



    async function postMsg() {

        const response = await api.post('/v1/ts/funcionarios', {
            "nome": inputNomeFunc,
            "cargo": inputCargoFunc,
            "email": inputEmailFunc,
            "telefone": inputTelefoneFunc,
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
                        <h1>Nome: </h1>
                        <h1>Cargo: </h1>
                        <h1>Email: </h1>
                        <h1>Telefone: </h1>
                        
                    </div>
                    <div id='divInput'>
                        <input type="text" value={inputNomeFunc} onChange={e => setInputNomeFunc(e.target.value)} />
                        <input type="text" value={inputCargoFunc} onChange={e => setInputCargoFunc(e.target.value)} />
                        <input type="text" value={inputEmailFunc} onChange={e => setInputEmailFunc(e.target.value)} />
                        <input type="text" value={inputTelefoneFunc} onChange={e => setInputTelefoneFunc(e.target.value)} />
                        
                    </div>
                </ul>

                <button onClick={postMsg}>Cadastrar</button>

            </body>
        </>
    );
}

export default CriarFuncionarioBody;

