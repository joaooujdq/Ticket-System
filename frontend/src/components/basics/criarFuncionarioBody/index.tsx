import { useEffect, useState } from "react";
import api from "../../../services/api";
import React from 'react'
import Alert from 'react-popup-alert'
import '../criarFuncionarioBody/index.css'
const CriarFuncionarioBody: React.FC = () => {
  const [descricao, setDescricao] = useState("");
  const [post, setPost] = useState(false);
  const [flag, setFlag] = useState(false);
  const [inputNomeFunc, setInputNomeFunc] = useState('');
  const [inputCargoFunc, setInputCargoFunc] = useState('');
  const [inputEmailFunc, setInputEmailFunc] = useState('');
  const [inputTelefoneFunc, setInputTelefoneFunc] = useState('');
  const [inputSenhaFunc, setInputSenhaFunc] = useState('');
  /* const [inputConfSenhaFunc, setInputConfSenhaFunc] = useState('');
     const [inputAdminFunc, setInputAdminFunc] = useState(false);
     const [adminFunc, setAdminFunc] = useState('0'); */
  const postMsg = async () => {
    const response = await api.post('/v1/ts/funcionarios', {
      "nome": inputNomeFunc,
      "cargo": inputCargoFunc,
      "email": inputEmailFunc,
      "telefone": inputTelefoneFunc,
      "senha": inputSenhaFunc
    }).then(response => response.data)
      .catch(async error => {
        if (error.response) {
          await setDescricao(error.response.data.descricao)
          setPost(!post)
        }
      });
      setFlag(true);
      
  }
  useEffect(() => {
    if (post) {
      onShowAlert('error')
    }else if(flag){
      window.location.reload();
    }
  }, [post])
  /* useEffect(() => {
      if(inputAdminFunc == true){
          setAdminFunc('0')
      }else{
          setAdminFunc('1')
      }
  }, [inputAdminFunc])
  useEffect(() => {
      if(inputSenhaFunc == inputConfSenhaFunc){
          console.log("igual")
      }else{
          console.log("diferente")
      }
  }, [inputConfSenhaFunc, inputSenhaFunc]) */
  const [alert, setAlert] = React.useState({
    type: 'error',
    text: descricao,
    show: false
  })
  function onCloseAlert() {
    setAlert({
      type: '',
      text: '',
      show: false
    })
    setPost(!post)
  }
  async function onShowAlert(type: string) {
    await setAlert({
      type: type,
      text: descricao,
      show: true
    })
  }
  return (
    <>
      <Alert
        header={''}
        btnText={'Fechar'}
        text={alert.text}
        type={alert.type}
        show={alert.show}
        onClosePress={onCloseAlert}
        pressCloseOnOutsideClick={true}
        showBorderBottom={true}
        alertStyles={{
          "background-color": "#f8f9fa",
          "width": "300px",
          "height": "100px",
          "display": "flex",
          "flex-direction": "column",
          "align-items": "center",
          "justify-content": "center",
          "left": "42%",
          "bottom": "30%",
          "border-radius": "8px",
          "border": "2px solid #C4C4C4",
          "position": "absolute"
        }}
        headerStyles={{}}
        textStyles={{}}
        buttonStyles={{
          "background-color": "#efefef",
          "border-radius": "8px",
          "margin-bottom": "10px",
          "text-decoration": "none",
          "button-decoration": "none",
          "align-text": "center",
          "width": "70px",
          "border": "2px solid #C4C4C4",
          "height": "30px",
          "color": "#000",
          "padding-left": "10px"
        }}
      />
      <body id='CriarFuncionarioBody'>
        <h2 id='TitleBar'>Cadastro de Funcionario</h2>
        <ul id='CriarFuncionarioUl'>
          <div id='CriarFuncionarioForm'>
            <div id='divH1'>
              <h1>Nome*: </h1>
              <h1>Cargo: </h1>
              <h1>Telefone: </h1>
              <h1>Email*: </h1>
              <h1>Senha*: </h1>
              {/* <h1>Confirmar senha*: </h1>
                        <h1>Admin: </h1> */}
            </div>
            <div id='divInput'>
              <input type="text" value={inputNomeFunc} onChange={e => setInputNomeFunc(e.target.value)} />
              <input type="text" value={inputCargoFunc} onChange={e => setInputCargoFunc(e.target.value)} />
              <input type="text" value={inputTelefoneFunc} onChange={e => setInputTelefoneFunc(e.target.value)} />
              <input type="text" value={inputEmailFunc} onChange={e => setInputEmailFunc(e.target.value)} />
              <input type="text" value={inputSenhaFunc} onChange={e => setInputSenhaFunc(e.target.value)} />
              {/* <input type="text" value={inputConfSenhaFunc} onChange={e => setInputConfSenhaFunc(e.target.value)} />
                        <div id='checkAdmin'>
                        <input type="checkbox" id="scales" name="scales"  onChange={e => setInputAdminFunc(!inputAdminFunc)} />
                        <h3>É administrador(a)?</h3>
                        </div> */}
            </div>
          </div>
          <button onClick={postMsg}>Cadastrar</button>
        </ul>
      </body>
    </>
  );
}
export default CriarFuncionarioBody;
