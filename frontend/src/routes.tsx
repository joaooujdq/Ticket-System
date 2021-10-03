import { Route, BrowserRouter } from  'react-router-dom';
import Home from 'pages/home';
import Empresas from 'pages/empresas';
import Funcionarios from 'pages/funcionarios';
import CriarRecado from 'pages/criarRecado';
import CriarFuncionario from 'pages/criarFuncionario';
import CriarEmpresa from 'pages/criarEmpresa';



const Routes = () => {
  return (
    <BrowserRouter>
      <Route component={Home} path="/" exact />
      <Route component={Empresas} path="/empresas" />
      <Route component={Funcionarios} path="/funcionarios" />
      <Route component={CriarRecado} path="/criarrecado" />
      <Route component={CriarEmpresa} path="/criarempresa" />
      <Route component={CriarFuncionario} path="/criarfuncionario" />
    </BrowserRouter>
  );
}
export default Routes;