import Footer from '../../components/basics/footer';
import Header from '../../components/basics/header';
import Title from  '../../components/basics/title';
import CriarEmpresaBody from 'components/basics/criarEmpresaBody';

const CriarEmpresa: React.FC = () => {
    return (

        <>
            <Title />
            <Header />
            <CriarEmpresaBody />
            <Footer />
        </>
    );
}

export default CriarEmpresa;