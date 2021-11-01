import { SetStateAction, useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import { FiSearch } from "react-icons/fi";
import { Link } from "react-router-dom";
import './index.css'
import { idText } from "typescript";


interface imensagem {
    codigo_rec: number,
    status_rec: string,
    prioridade_rec: string,
    setor_rec: string,
    mensagem_rec: string,
    data_rec: string,
    empresaDTO: iempresa,
    funcionarioDTO: ifuncionario,
    _links_rec: i_links
}
interface ifuncionario {
    codigo_func: number;
    nome_func: string;
    cargo_func: string;
    email_func: string;
    telefone_func: string;
}
interface iempresa {
    codigo_emp: number,
    nome_emp: string,
    razao_emp: string,
    cnpj_emp: string,
    email_emp: string,
    endereco_emp: string,
    telefone_emp: string
}
interface i_links {
    self: iself
}
interface iself {
    href: string
}


const HomeBody: React.FC = () => {
    const [Msg, setMsg] = useState<imensagem[]>([]);
    const [Limit, setLimit] = useState<imensagem[]>([]);
    const [page, setPage] = useState(0);
    useEffect(() => {
        const loadMsg = async () => {
            const response = await api.get('/v1/ts/recados', { params: { page: page, limit: 3 } });
            const limit = await api.get('/v1/ts/recados');
            setMsg(response.data._embedded.recadoDTOList);
            setLimit(limit.data._embedded.recadoDTOList);
        }
        loadMsg()
    }, [page]);


    const deleteMsg = async (codigo: string) => {
   
        //setDeleteCodigo(codigo);
        const responseDelete = await api.delete('/v1/ts/recados/' + codigo);
        window.location.reload()
    }


    return (
        <>
        <div id='searchBar'>
            <div id='search'>
            <h3>Procurar por: </h3>
                <input type="text" />
                <select id="dropdownSearch">
                <option value="1" selected>Nome de Funcionário</option>
                <option value="2">Nome de Empresa</option>
                <option value="3">Setor</option>
                </select>
                <button>Buscar</button>
            </div>
                <div id='ordenation' >
                <h3>Ordernar por: </h3>
                <select id='dropdownOrdenation'>
                <option value='1' selected>ID | Decrescente</option>
                <option value='2' >ID | Crescente</option>
                <option value='3' >Status | Decrescente</option>
                <option value='4' >Status | Crescente</option>
                <option value='5' >Prioridade | Decrescente</option>
                <option value='6' >Prioridade | Crescente</option>
                 </select>

                </div>
            </div>
            <body>
                <thead>
                    {
                        Msg.map(m => (
                            <ul id='homeBody'>
                                <li>ID: {m.codigo_rec}</li>
                                <li>Nome da Empresa: {m.empresaDTO.nome_emp}</li>
                                <li>Nome do Funcionario: {m.funcionarioDTO.nome_func}</li>
                                <li>Status: {m.status_rec}</li>
                                <li>Prioridade: {m.prioridade_rec}</li>
                                <li>Setor: {m.setor_rec}</li>
                                <li id='msgRecado'>Mensagem: {m.mensagem_rec}</li>
                                <li>Data: {m.data_rec}</li>
                                <li id='deleteButton' onClick={() => { deleteMsg(m.codigo_rec.toString()) }}> <strong>EXCLUIR RECADO</strong> </li>
                            </ul>
                        ))
                    }
                </thead>
            </body>
            <div id='carouselBar'>
                <FiArrowLeft id='carouselIcon' onClick={() => { if (page - 1 >= 0) setPage(page - 1) }} />
                <FiArrowRight id='carouselIcon' onClick={() => { if (Msg.length == 3 && page + 1 < Limit.length / 3) { setPage(page + 1) } }} />
            </div>
        </>
    );
}
export default HomeBody;
