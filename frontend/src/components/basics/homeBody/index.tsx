import { useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import { Form } from "react-bootstrap";
import './index.css'
import React from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
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
    const [direction, setDirection] = useState('desc');
    const [ordenation, setOrdenation] = useState('codigo');
    const [senhaExcluir, setSenhaExcluir] = useState('');
    const [inputBuscar, setInputBuscar] = useState('');
    const [buscarCategoria, setBuscarCategoria] = useState('1');
    const [ordenarCategoria, setOrdenarCategoria] = useState('1');
    const [page, setPage] = useState(0);
    useEffect(() => {
        const loadMsg = async () => {
            const response = await api.get('/v1/ts/recados', { params: { page: page, limit: 3, direction: direction, ordenation: ordenation } });
            const limit = await api.get('/v1/ts/recados');
            if (Object.keys(response.data).length) {
                setMsg(response.data._embedded.recadoDTOList);
            } else {
                setMsg([]);
            }
            setLimit(limit.data._embedded.recadoDTOList);
        }
        loadMsg()
    }, [page]);
    useEffect(() => {
        const buscarMsg = async () => {
            if (buscarCategoria == "1") {
                const response = await api.get('/v1/ts/recados/funcionario', { params: { page: page, limit: 3, funcionario: inputBuscar} });
                if (Object.keys(response.data).length) {
                    setMsg(response.data._embedded.recadoDTOList);
                } else {
                    setMsg([]);
                }
            }
            if (buscarCategoria == "2") {
                const response = await api.get('/v1/ts/recados/empresa', { params: { page: page, limit: 3, empresa: inputBuscar} });
                if (Object.keys(response.data).length) {
                    setMsg(response.data._embedded.recadoDTOList);
                } else {
                    setMsg([]);
                }
            }
            if (buscarCategoria == "3") {
                const response = await api.get('/v1/ts/recados/setor', { params: { page: page, limit: 3, setor: inputBuscar} });
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
                setDirection('desc');
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'desc' } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '2') {
                setDirection('asc');
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'asc' } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '3') {
                setDirection('desc');
                setOrdenation('numStatus');
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'desc', ordenation: "numStatus" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '4') {
                setDirection('asc');
                setOrdenation('numStatus');
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'asc', ordenation: "numStatus" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '5') {
                setDirection('asc');
                setOrdenation('numPrioridade');
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'asc', ordenation: "numPrioridade" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
            if (ordenarCategoria == '6') {
                setDirection('desc');
                setOrdenation('numPrioridade');
                const response = await api.get('/v1/ts/recados/', { params: { page: page, limit: 3, direction: 'desc', ordenation: "numPrioridade" } });
                setMsg(response.data._embedded.recadoDTOList);
            }
        }
        ordenarMsg()
    }, [ordenarCategoria]);
    const deleteMsg = async (codigo: string) => {
        const admin = await api.get('/v1/ts/funcionarios/1');
        if (senhaExcluir == admin.data.senha_func) {
            const responseDelete = await api.delete('/v1/ts/recados/' + codigo);
            window.location.reload()
        }
    }
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
                                <li><strong>ID: {m.codigo_rec}</strong></li>
                                <li><strong>Nome da Empresa: {m.empresaDTO.nome_emp}</strong></li>
                                <li><strong>Nome do Funcionario: {m.funcionarioDTO.nome_func}</strong></li>
                                <li><strong>Status: {m.status_rec}</strong></li>
                                <li><strong>Prioridade: {m.prioridade_rec}</strong></li>
                                <li><strong>Setor: {m.setor_rec}</strong></li>
                                <li id='msgRecado'><strong>Mensagem: {m.mensagem_rec}</strong></li>
                                <li><strong>Data: {m.data_rec}</strong></li>
                                <Popup trigger={<li className='deleteButton' ><strong >EXCLUIR RECADO</strong></li>} position="bottom center">
                                    <h4>Digite a senha:</h4>
                                    <input type="text" value={senhaExcluir} onChange={e => { setSenhaExcluir(e.target.value) }} />
                                    <button className='confDelete' onClick={() => { deleteMsg(m.codigo_rec.toString()) }}>Excluir</button>
                                    
                                </Popup>
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
