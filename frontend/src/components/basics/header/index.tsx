import './index.css'
import {Link} from 'react-router-dom'

const Header = () => {
    return (
        <>
         <header className="header mt-auto py-3 ">
     
  <div className="container" id="container-header">
  <Link to='/'>
    <button>Meus Recados</button>
    </Link>
    <Link to='/criarrecado'>
    <button>Criar Recado</button>
    </Link>
    <Link to='/funcionarios'>
    <button>Funcionários Cadastrados</button>
    </Link>
    <Link to='/criarfuncionario'>
    <button>Adicionar Funcionário</button>
    </Link>
    <Link to='/empresas'>
    <button>Empresas Cadastradas</button>
    </Link>
    <Link to='/criarempresa'>
    <button>Adicionar Empresa</button>
    </Link>
  </div>
     </header>
        </>
    );
}

export default Header;