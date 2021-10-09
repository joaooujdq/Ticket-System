
import {  useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
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
    const [deleteCodigo, setDeleteCodigo]= useState('');
    const [page, setPage]= useState(0);


    useEffect(()=>{
        const loadMsg = async () => {
            const response = api.get('/v1/ts/funcionarios/',{params:{page:page,limit:3}});
            const limit = api.get('/v1/ts/funcionarios/');
            
            setFunc((await response).data._embedded.funcionarioDTOList);
            setLimit((await limit).data._embedded.funcionarioDTOList);
    
          
        }

        loadMsg()
    },[page]);

    const deleteMsg = async(codigo: string) =>{
        //setDeleteCodigo(codigo);
        
        const responseDelete = await api.delete('/v1/ts/funcionarios/' + codigo);
        window.location.reload()
        
    }

    



    return (

        <>
          
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
                                <li id='deleteButton' onClick={() =>{deleteMsg(m.codigo_func.toString())}}><strong>EXCLUIR FUNCIONARIO</strong></li>
                            </ul>
                        ))

                    }
               
               </thead>
              
               </body>

               <div id='carouselBar'>
                
                    <FiArrowLeft id='carouselIcon' onClick = { () => {if( page - 1 >= 0)setPage(page-1)   }  }/>
                    <FiArrowRight id='carouselIcon' onClick = {() =>{if(Func.length == 3 && page + 1 < Limit.length/3){setPage(page+1)}}} />

                </div>

        </>
            );

}

            export default FuncBody;

