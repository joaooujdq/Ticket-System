import Footer from '../../components/basics/footer';
import Header from '../../components/basics/header';
import Title from  '../../components/basics/title';
import CriarFuncionarioBody from 'components/basics/criarFuncionarioBody';

const CriarFuncionario: React.FC = () => {
    return (

        <>
            <Title />
            <Header />
            <CriarFuncionarioBody />
            <Footer />
        </>
    );
}

export default CriarFuncionario;