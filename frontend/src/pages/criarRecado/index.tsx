import Footer from '../../components/basics/footer';
import Header from '../../components/basics/header';
import Title from  '../../components/basics/title';
import CriarRecadoBody from '../../components/basics/criarRecadoBody';

const CriarRecado: React.FC = () => {
    return (

        <>
            <Title />
            <Header />
            <CriarRecadoBody />
            <Footer />
        </>
    );
}

export default CriarRecado;