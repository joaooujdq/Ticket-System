
import { useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import { Form } from "react-bootstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { InputGroup, InputGroupText, Input } from 'reactstrap';
import { FaUserAlt } from 'react-icons/fa';
import '../funcBody/index.css'


interface ifuncionario {
    codigo_func: number;
    nome_func: string;
    cargo_func: string;
    email_func: string;
    telefone_func: string;
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
    const [deleteCodigo, setDeleteCodigo] = useState('');
    const [inputBuscar, setInputBuscar] = useState('');
    const [buscarCategoria, setBuscarCategoria] = useState('1');
    const [page, setPage] = useState(0);


    useEffect(() => {
        const loadMsg = async () => {
            const response = await api.get('/v1/ts/funcionarios/', { params: { page: page, limit: 3 } });
            const limit = await api.get('/v1/ts/funcionarios/');

            setFunc(response.data._embedded.funcionarioDTOList);
            setLimit(limit.data._embedded.funcionarioDTOList);


        }

        loadMsg()
    }, [page]);

    useEffect(() => {
        const buscarMsg = async () => {

            if(buscarCategoria == "1"){
                let response = await api.get('/v1/ts/funcionarios/nomes', { params: { page: page, limit: 3, direction: 'asc', nomes: inputBuscar } });
               
                if(Object.keys(response.data).length){
                    setFunc(response.data._embedded.funcionarioDTOList);
                }else{
                    setFunc([]);
                }

            } 

            if(buscarCategoria == "2"){
                const response = await api.get('/v1/ts/funcionarios/cargo', { params: { page: page, limit: 3, direction: 'asc', cargo: inputBuscar } });

                if(Object.keys(response.data).length){
                    setFunc(response.data._embedded.funcionarioDTOList);
                }else{
                    setFunc([]);
                }
               
            }

                
        }
        buscarMsg()
    }, [inputBuscar]);



    const deleteMsg = async (codigo: string) => {
        //setDeleteCodigo(codigo);

        const responseDelete = await api.delete('/v1/ts/funcionarios/' + codigo);
        window.location.reload()

    }





    return (

        <>


            <div id='searchBar'>
                <div id='search'>
                    <h3>Selecione a categoria que deseja pesquisar: </h3>
                    <input type="text" value={inputBuscar} onChange={e => setInputBuscar(e.target.value)} />
                    <Form.Group controlId="dropdownSearch" >
                    
                        <Form.Control
                            as="select"
                            value={buscarCategoria}
                            onChange={e => {setBuscarCategoria(e.target.value)}} >
                            <option value="1" selected>Nome</option>
                            <option value="2">Cargo</option>
                            <InputGroup>
        <Input placeholder="Username" />
    
          <FaUserAlt />
      
      </InputGroup>           </Form.Control>
                        
                    </Form.Group>
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
                        Func.map(m => (

                            <ul id='funcBody'>
                                <li>ID: {m.codigo_func}</li>
                                <li>Nome: {m.nome_func}</li>
                                <li>Cargo: {m.cargo_func}</li>
                                <li>Email: {m.email_func}</li>
                                <li>Telefone: {m.telefone_func}</li>
                                <li id='deleteButton' onClick={() => { deleteMsg(m.codigo_func.toString()) }}><strong>EXCLUIR FUNCIONARIO</strong></li>
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

