import { useEffect, useState } from "react";
import React from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import { Form } from "react-bootstrap";
import '../empBody/index.css'
interface iempresa {
    codigo_emp: number,
    nome_emp: string,
    razao_emp: string,
    cnpj_emp: string,
    email_emp: string,
    endereco_emp: string,
    telefone_emp: string
    links_: i_links
}
interface i_links {
    self: iself
}
interface iself {
    href: string
}
const EmpBody: React.FC = () => {
    const [Emp, setEmp] = useState<iempresa[]>([]);
    const [Limit, setLimit] = useState<iempresa[]>([]);
    const [direction, setDirection] = useState('desc');
    const [inputBuscar, setInputBuscar] = useState('');
    const [buscarCategoria, setBuscarCategoria] = useState('1');
    const [ordenarCategoria, setOrdenarCategoria] = useState('1');
    const [senhaExcluir, setSenhaExcluir] = useState('');
    const [page, setPage] = useState(0);
    useEffect(() => {
        const loadMsg = async () => {
            const response = await api.get('/v1/ts/empresas/', { params: { page: page, limit: 3, direction: direction } });
            const limit = await api.get('/v1/ts/empresas/');
            if (Object.keys(response.data).length) {
                setEmp((response).data._embedded.empresaDTOList);
            } else {
                setEmp([]);
            }
            setLimit((limit).data._embedded.empresaDTOList);
        }
        loadMsg()
    }, [page]);
    useEffect(() => {
        const buscarMsg = async () => {
            if (buscarCategoria == "1") {
                const response = await api.get('/v1/ts/empresas/nomes', { params: { page: page, limit: 3, nomes: inputBuscar} });
                if (Object.keys(response.data).length) {
                    setEmp(response.data._embedded.empresaDTOList);
                } else {
                    setEmp([]);
                }
            }
            if (buscarCategoria == "2") {
                const response = await api.get('/v1/ts/empresas/razao', { params: { page: page, limit: 3, razao: inputBuscar } });
                if (Object.keys(response.data).length) {
                    setEmp(response.data._embedded.empresaDTOList);
                } else {
                    setEmp([]);
                }
            }
            if (buscarCategoria == "3") {
                const response = await api.get('/v1/ts/empresas/endereco', { params: { page: page, limit: 3, endereco: inputBuscar} });
                if (Object.keys(response.data).length) {
                    setEmp(response.data._embedded.empresaDTOList);
                } else {
                    setEmp([]);
                }
            }
        }
        buscarMsg()
    }, [inputBuscar]);
    useEffect(() => {
        const ordenarMsg = async () => {
            if (ordenarCategoria == '1') {
                setDirection('desc');
                const response = await api.get('/v1/ts/empresas/', { params: { page: page, limit: 3, direction: 'desc' } });
                setEmp(response.data._embedded.empresaDTOList);
            }
            if (ordenarCategoria == '2') {
                setDirection('asc');
                const response = await api.get('/v1/ts/empresas/', { params: { page: page, limit: 3, direction: 'asc' } });
                setEmp(response.data._embedded.empresaDTOList);
            }
        }
        ordenarMsg()
    }, [ordenarCategoria]);
    const deleteMsg = async (codigo: string) => {
        const admin = await api.get('/v1/ts/funcionarios/1');
        if (senhaExcluir == admin.data.senha_func) {
            const responseDelete = await api.delete('/v1/ts/empresas/' + codigo);
            window.location.reload()
        }
    }
    return (
        <>
            <div id='searchBarEmp'>
                <div id='search'>
                    <h3>Selecione a categoria que deseja pesquisar: </h3>
                    <input type="text" value={inputBuscar} onChange={e => setInputBuscar(e.target.value)} />
                    <Form.Group controlId="dropdownSearch" >
                        <Form.Control
                            as="select"
                            value={buscarCategoria}
                            onChange={e => { setBuscarCategoria(e.target.value) }} >
                            <option value="1" selected>Nome</option>
                            <option value="2">Razão Social</option>
                            <option value="3">Endereço</option>
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
                        Emp.map(m => (
                            <ul id='empBody'>
                                <li>ID: {m.codigo_emp}</li>
                                <li>Nome: {m.nome_emp}</li>
                                <li>Razão Social: {m.razao_emp}</li>
                                <li>CNPJ: {m.cnpj_emp}</li>
                                <li >Email: {m.email_emp}</li>
                                <li>Endereço: {m.endereco_emp}</li>
                                <li >Telefone: {m.telefone_emp}</li>
                                <Popup trigger={<li className='deleteButton' ><strong >EXCLUIR EMPRESA</strong></li>} position="bottom center">
                                    <h4>Digite a senha:</h4>
                                    <input type="text" value={senhaExcluir} onChange={e => { setSenhaExcluir(e.target.value) }} />
                                    <button id='confDelete' onClick={() => { deleteMsg(m.codigo_emp.toString()) }}>Excluir</button>
                                </Popup>
                            </ul>
                        ))
                    }
                </thead>
            </body>
            <div id='carouselBar'>
                <FiArrowLeft id='carouselIcon' onClick={() => { if (page - 1 >= 0) setPage(page - 1) }} />
                <FiArrowRight id='carouselIcon' onClick={() => { if (Emp.length == 3 && page + 1 < Limit.length / 3) { setPage(page + 1) } }} />
            </div>
        </>
    );
}
export default EmpBody;
