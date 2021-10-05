
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


    async function loadMsg() {
        const response = api.get('/v1/ts/funcionarios/',{params:{page:page,limit:3}});
        console.log(response)
        const limit = api.get('/v1/ts/funcionarios/');
        
        setFunc((await response).data._embedded.funcionarioDTOList);
        setLimit((await limit).data._embedded.funcionarioDTOList);

      
    }

    async function  deleteMsg(codigo: string) {
        setDeleteCodigo(codigo);
        
        const responseDelete = api.delete('/v1/ts/funcionarios/' + deleteCodigo);
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
                        Func.map(m => (
                            
                            <ul id='funcBody'>
                                <li>{m.codigo_func}</li>
                                <li>{m.nome_func}</li>
                                <li>{m.cargo_func}</li>
                                <li>{m.email_func}</li>
                                <li>{m.telefone_func}</li>
                                <li id='deleteButton' onClick={() =>{
                                    
                                    deleteMsg(m.codigo_func.toString())
                                }}>Excluir</li>
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

