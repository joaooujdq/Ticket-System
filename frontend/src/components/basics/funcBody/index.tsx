import { useEffect, useState } from "react";
import React from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import { Form } from "react-bootstrap";
import '../funcBody/index.css'
interface ifuncionario {
    codigo_func: number;
    nome_func: string;
    cargo_func: string;
    email_func: string;
    telefone_func: string;
    senha_func: string;
    _links: i_links
}
interface i_links {
    self: iself
}
interface iself {
    href: string
}
const FuncBody: React.FC = () => {
    const [Func, setFunc] = useState<ifuncionario[]>([]);
    const [Limit, setLimit] = useState<ifuncionario[]>([]);
    const [direction, setDirection] = useState('desc');
    const [inputBuscar, setInputBuscar] = useState('');
    const [buscarCategoria, setBuscarCategoria] = useState('1');
    const [ordenarCategoria, setOrdenarCategoria] = useState('1');
    const [senhaExcluir, setSenhaExcluir] = useState('');
    const [page, setPage] = useState(0);
    useEffect(() => {
        const loadMsg = async () => {
            const response = await api.get('/v1/ts/funcionarios/', { params: { page: page, limit: 3, direction: direction } });
            const limit = await api.get('/v1/ts/funcionarios/');
            if (Object.keys(response.data).length) {
                setFunc(response.data._embedded.funcionarioDTOList);
            } else {
                setFunc([]);
            }
            setLimit(limit.data._embedded.funcionarioDTOList);
        }
        loadMsg()
    }, [page]);
    useEffect(() => {
        const buscarMsg = async () => {
            if (buscarCategoria == "1") {
                const response = await api.get('/v1/ts/funcionarios/nomes', { params: { page: page, limit: 3, nomes: inputBuscar } });
                if (Object.keys(response.data).length) {
                    setFunc(response.data._embedded.funcionarioDTOList);
                } else {
                    setFunc([]);
                }
            }
            if (buscarCategoria == "2") {
                const response = await api.get('/v1/ts/funcionarios/cargo', { params: { page: page, limit: 3, cargo: inputBuscar} });
                if (Object.keys(response.data).length) {
                    setFunc(response.data._embedded.funcionarioDTOList);
                } else {
                    setFunc([]);
                }
            }
        }
        buscarMsg()
    }, [inputBuscar]);
    useEffect(() => {
        const ordenarMsg = async () => {
            if (ordenarCategoria == '1') {
                setDirection('desc');
                const response = await api.get('/v1/ts/funcionarios/', { params: { page: page, limit: 3, direction: 'desc' } });
                setFunc(response.data._embedded.funcionarioDTOList);
            }
            if (ordenarCategoria == '2') {
                setDirection('asc');
                const response = await api.get('/v1/ts/funcionarios/', { params: { page: page, limit: 3, direction: 'asc' } });
                setFunc(response.data._embedded.funcionarioDTOList);
            }
        }
        ordenarMsg()
    }, [ordenarCategoria]);
    const deleteMsg = async (codigo: string) => {
        const admin = await api.get('/v1/ts/funcionarios/1');
        if (senhaExcluir == admin.data.senha_func) {
            const responseDelete = await api.delete('/v1/ts/funcionarios/' + codigo);
            window.location.reload()
        }
    }
    return (
        <>
            <div id='searchBarFunc'>
                <div id='search'>
                    <h3>Selecione a categoria que deseja pesquisar: </h3>
                    <input type="text" value={inputBuscar} onChange={e => setInputBuscar(e.target.value)} />
                    <Form.Group controlId="dropdownSearch" >
                        <Form.Control
                            as="select"
                            value={buscarCategoria}
                            onChange={e => { setBuscarCategoria(e.target.value) }} >
                            <option value="1" selected>Nome</option>
                            <option value="2">Cargo</option>
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
                        </Form.Control>
                    </Form.Group>
                </div>
            </div>
            <body>
                <thead>
                    {
                        Func.map(m => (
                            <ul id='funcBody'>
                                <li><strong>ID: {m.codigo_func}</strong></li>
                                <li><strong>Nome: {m.nome_func}</strong></li>
                                <li><strong>Cargo: {m.cargo_func}</strong></li>
                                <li><strong>Email: {m.email_func}</strong></li>
                                <li><strong>Telefone: {m.telefone_func}</strong></li>
                                <Popup trigger={<li className='deleteButton' ><strong >EXCLUIR FUNCIONARIO</strong></li>} position="bottom center">
                                    <h4>Digite a senha:</h4>
                                    <input type="text" value={senhaExcluir} onChange={e => { setSenhaExcluir(e.target.value) }} />
                                    <button className='confDelete' onClick={() => { deleteMsg(m.codigo_func.toString()) }}>Excluir</button>
                                </Popup>
                            </ul>
                        ))
                    }
                </thead>
            </body>
            <div id='carouselBar'>
                <FiArrowLeft id='carouselIcon' onClick={() => { if (page - 1 >= 0) setPage(page - 1) }} />
                <FiArrowRight id='carouselIcon' onClick={() => { if (Func.length == 3 && page + 1 < Limit.length / 3) { setPage(page + 1) } }} />
            </div>
        </>
    );
}
export default FuncBody;
