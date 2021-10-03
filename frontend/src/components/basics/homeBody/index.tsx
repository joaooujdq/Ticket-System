
import {  SetStateAction, useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
import { FiSearch } from "react-icons/fi";
import { Link } from "react-router-dom";
import './index.css'
import { idText } from "typescript";

interface imensagem {
    codigo_rec: number,
    status_rec: boolean,
    prioridade_rec: number,
    setor_rec: string,
    mensagem_rec: string,
    data_rec: string,
    hora_rec: string,
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
    const [deleteCodigo, setDeleteCodigo]= useState('');
    const [page, setPage]= useState(0);


    async function loadMsg() {
        
            const response = api.get('/v1/ts/recados',{params:{page:page,limit:3}});
            const limit = api.get('/v1/ts/recados');
            console.log("get"+ (await response).data)
            setMsg((await response).data._embedded.recadoDTOList);
            setLimit((await limit).data._embedded.recadoDTOList);
            

    }

    async function  deleteMsg(codigo: string) {

        setDeleteCodigo(codigo);
        const responseDelete = api.delete('/v1/ts/recados/' + deleteCodigo);
        loadMsg()
        
    }


    useEffect(()=>{
        loadMsg()
        
    },[page]);

  


    return (

        <>
    
          <body>
              
                <thead>
                    {
                        Msg.map(m => (
                            <ul id='homeBody'>
                                <li>{m.codigo_rec}</li>
                                <li>{m.empresaDTO.nome_emp}</li>
                                <li>{m.funcionarioDTO.nome_func}</li>
                                <li>{m.status_rec}</li>
                                <li>{m.prioridade_rec}</li>
                                <li>{m.setor_rec}</li>
                                <li id='msgRecado'>{m.mensagem_rec}</li>
                                <li>{m.data_rec}</li>
                                <li>{m.hora_rec}</li>
                                <li id='deleteButton' onClick={() =>{
                                    deleteMsg(m.codigo_rec.toString())
                                }}> Excluir</li>
                          
                            </ul>
                        ))

                    }
               
               </thead>
              
               </body>

                <div id='carouselBar'>
                    <FiArrowLeft id='carouselIcon' onClick = { () => {if( page - 1 >= 0)setPage(page-1)   }  }/>
                    <FiArrowRight id='carouselIcon' onClick = {() =>{if(Msg.length == 3 && page + 1 < Limit.length/3){setPage(page+1)}}}/>

                </div>
        </>
            );

}

            export default HomeBody;

