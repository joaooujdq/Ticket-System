
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
    const [inputSenhaFunc, setInputSenhaFunc] = useState('');
    const [inputConfSenhaFunc, setInputConfSenhaFunc] = useState('');
    const [inputAdminFunc, setInputAdminFunc] = useState(false);
    const [adminFunc, setAdminFunc] = useState('0');



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

    useEffect(() => {
        console.log(adminFunc)
        console.log(inputAdminFunc)
        if(inputAdminFunc == true){
            setAdminFunc('0')
        }else{
            setAdminFunc('1')
        }
    }, [inputAdminFunc])

    useEffect(() => {
        
        
        if(inputSenhaFunc == inputConfSenhaFunc){
            console.log("igual")
        }else{
            console.log("diferente")
        }
    }, [inputConfSenhaFunc, inputSenhaFunc])
    




    return (
        <>
            
            <body id='CriarFuncionarioBody'>
            <h2 id='TitleBar'>Cadastro de Funcionario</h2>
                <ul id='CriarFuncionarioUl'>
                <div id='CriarFuncionarioForm'>
                    <div id='divH1'>
                        <h1>Nome: </h1>
                        <h1>Cargo: </h1>
                        <h1>Telefone: </h1>
                        <h1>Email: </h1>
                        <h1>Senha: </h1>
                        <h1>Confirmar senha: </h1>
                        <h1>Admin: </h1>
                        
                    </div>
                    <div id='divInput'>
                        <input type="text" value={inputNomeFunc} onChange={e => setInputNomeFunc(e.target.value)} />
                        <input type="text" value={inputCargoFunc} onChange={e => setInputCargoFunc(e.target.value)} />
                        <input type="text" value={inputTelefoneFunc} onChange={e => setInputTelefoneFunc(e.target.value)} />
                        <input type="text" value={inputEmailFunc} onChange={e => setInputEmailFunc(e.target.value)} />
                        <input type="text" value={inputSenhaFunc} onChange={e => setInputSenhaFunc(e.target.value)} />
                        <input type="text" value={inputConfSenhaFunc} onChange={e => setInputConfSenhaFunc(e.target.value)} />
                        <div id='checkAdmin'>
                        <input type="checkbox" id="scales" name="scales"  onChange={e => setInputAdminFunc(!inputAdminFunc)} />
                        <h3>É administrador(a)?</h3>
                        </div>
                        
                       
                        
                    </div>
                    </div>
                    <button onClick={postMsg}>Cadastrar</button>
                </ul>

                

            </body>
        </>
    );
}

export default CriarFuncionarioBody;

