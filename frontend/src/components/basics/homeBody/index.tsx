import { SetStateAction, useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import { Form } from "react-bootstrap";
import './index.css'



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
    const [inputBuscar, setInputBuscar] = useState('');
    const [buscarCategoria, setBuscarCategoria] = useState('1');
    const [ordenarCategoria, setOrdenarCategoria] = useState('1');
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

    /* useEffect(() => {
        const buscarMsg = async () => {
   
            //setDeleteCodigo(codigo);
            const response = await api.get('/v1/ts/funcionarios/nomes/:nomes?page=0&limit=12&direction=asc&nomes='+ inputBuscar, { params: { page: page, limit: 3 } });
            setMsg(response.data._embedded.recadoDTOList);
            //window.location.reload()
        }
        buscarMsg()
    }, [inputBuscar]);
 */

    const deleteMsg = async (codigo: string) => {
   
        //setDeleteCodigo(codigo);
        const responseDelete = await api.delete('/v1/ts/recados/' + codigo);
        window.location.reload()
    }

    useEffect(() => {
        const buscarMsg = async () => {
            if (buscarCategoria == "1") {
                const response = await api.get('/v1/ts/recados/funcionario', { params: { page: page, limit: 3, funcionario: inputBuscar } });
                if (Object.keys(response.data).length) {
                    setMsg(response.data._embedded.recadoDTOList);
                } else {
                    setMsg([]);
                }
            }
            if (buscarCategoria == "2") {
                const response = await api.get('/v1/ts/recados/empresa', { params: { page: page, limit: 3, empresa: inputBuscar } });
                if (Object.keys(response.data).length) {
                    setMsg(response.data._embedded.recadoDTOList);
                } else {
                    setMsg([]);
                }
            }
            if (buscarCategoria == "3") {
                const response = await api.get('/v1/ts/recados/setor', { params: { page: page, limit: 3, setor: inputBuscar } });
                if (Object.keys(response.data).length) {
                    setMsg(response.data._embedded.recadoDTOList);
                } else {
                    setMsg([]);
                }
            }
        }
        buscarMsg()
    }, [inputBuscar]);
    useEffect(() => {
        const ordenarMsg = async () => {
            if (ordenarCategoria == '1') {
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'desc' } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '2') {
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'asc' } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '3') {
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'desc', ordenation: "numStatus" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '4') {
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'asc', ordenation: "numStatus" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '5') {
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'asc', ordenation: "numPrioridade" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '6') {
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'desc', ordenation: "numPrioridade" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
        }
        ordenarMsg()
    }, [ordenarCategoria]);

    

    return (
        <>

            <div id='searchBarRecado'>
                <div id='search'>
                    <h3>Selecione a categoria que deseja pesquisar: </h3>
                    <input type="text" value={inputBuscar} onChange={e => setInputBuscar(e.target.value)} />
                    <Form.Group controlId="dropdownSearch" >
                        <Form.Control
                            as="select"
                            value={buscarCategoria}
                            onChange={e => { setBuscarCategoria(e.target.value) }} >
                            <option value="1" selected>Nome de Funcionário</option>
                            <option value="2">Nome de Empresa</option>
                            <option value="3">Setor</option>
                        </Form.Control>
                    </Form.Group>
                </div>
                <div id='ordenation' >
                    <h3>Selecione a ordenação: </h3>
                    <Form.Group controlId="dropdownOrdenation" >
                        <Form.Control
                            as="select"
                            value={ordenarCategoria}
                            onChange={e => { setOrdenarCategoria(e.target.value) }} >
                            <option value='1' selected>ID | Decrescente</option>
                            <option value='2' >ID | Crescente</option>
                            <option value='3' >Status | Decrescente</option>
                            <option value='4' >Status | Crescente</option>
                            <option value='5' >Prioridade | Decrescente</option>
                            <option value='6' >Prioridade | Crescente</option>
                        </Form.Control>
                    </Form.Group>
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
