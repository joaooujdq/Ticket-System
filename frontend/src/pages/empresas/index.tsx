import Footer from '../../components/basics/footer';
import Header from '../../components/basics/header';
import Title from  '../../components/basics/title';
import EmpBody from '../../components/basics/empBody/index'


const Empresas: React.FC = () => {
    return (

        <>
            <Title />
            <Header />
            <EmpBody/>
            <Footer />
        </>
    );
}

export default Empresas;