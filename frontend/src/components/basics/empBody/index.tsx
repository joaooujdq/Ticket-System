
import {  useEffect, useState } from "react";
import api from "../../../services/api";
import { FiArrowLeft, FiArrowRight } from "react-icons/fi";
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
    const [deleteCodigo, setDeleteCodigo]= useState('');
    const [page, setPage]= useState(0);

    useEffect(()=>{
        const loadMsg = async() =>{
            const response = await api.get('/v1/ts/empresas/',{params:{page:page,limit:3}});
            const limit = await api.get('/v1/ts/empresas/');
            
            setEmp((response).data._embedded.empresaDTOList);
            setLimit((limit).data._embedded.empresaDTOList);
    
         
        }
        loadMsg()
    },[page]);
    

    const deleteMsg = async(codigo: string) => {
        //setDeleteCodigo(codigo);
        const responseDelete = await api.delete('/v1/ts/empresas/' + codigo);
       window.location.reload()
        
    }

   



    return (

        <>
          
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
                                <li id='deleteButton'  onClick={() => { deleteMsg(m.codigo_emp.toString())  }}><strong>EXCLUIR EMPRESA</strong></li>
                            </ul>
                        ))

                    }
               
               </thead>
              
               </body>

               <div id='carouselBar'>
                    <FiArrowLeft id='carouselIcon' onClick = { () => {if( page - 1 >= 0)setPage(page-1)   }  }/>
                    <FiArrowRight id='carouselIcon' onClick = {() =>{if(Emp.length == 3 && page + 1 < Limit.length/3){setPage(page+1)}}}/>

                </div>

        </>
            );

}

            export default EmpBody;

