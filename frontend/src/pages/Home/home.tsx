import { ReactComponent as ClientImage } from 'assets/images/client.svg';
import { ReactComponent as ManagerImage } from 'assets/images/manager.svg';
import './styles.css';
import Painel from 'components/Painel';
import axios from 'axios';
import { useState } from 'react';
import { Password } from 'types/password';
import Navbar from 'components/Navbar';



function Home() {

  
  const [password, setPassword] = useState<Password>();
  const [reset, setReset] = useState<Boolean>(true);

  
  function newNormalPassword(e : any){
    const data = {
      priority : false
    }
    axios.post('http://localhost:3000/password/new', data).then(() =>
    setReset(!reset)
    );
  }

  function newPrefPassword(e : any){
    const data = {
      priority : true
    }
    axios.post('http://localhost:3000/password/new', data).then(() => {
      setReset(!reset)
    });

  }

  function nextPassword(e : any){
    axios.get('http://localhost:3000/password/next')
    .then((response) => {
      setPassword(response.data);
    });
    
  }

  function resetPassword(e : any){
    axios.get('http://localhost:3000/password/reset')
    .then((response) => {
      setPassword({
        id: 0,
        number:0,
        priority: false,
        pass: ''
      });
    }) 
  }

  function recoverPassword(e : any){
    axios.get('http://localhost:3000/password/recover')
    .then(() => {
      setReset(!reset)
    }) 
  }

  return (
     <>
     <Navbar/>
      <div className="container">
        <div className="row">
          <div className="col">
            <h5>ATENDIMENTO AO CLIENTE</h5>
            <div className="div-client-img">
              <ClientImage />
            </div>
            <div className="d-grid gap-2 col-6 mx-auto" id="client-button">
              <input
                className="btn btn-primary"
                value="Atendimento Preferêncial"
                type="submit"
                name="prefPassword"
                onClick={(e) => {newPrefPassword(e)}}
              />
             
              <input
                className="btn btn-primary"
                value="Atendimento Normal"
                type="submit"
                name="normalPassword"
                onClick={(e) => {newNormalPassword(e)}}
              />
              
            </div>
          </div>
          <div className="col div-painel">
            
            <Painel pass={password?.pass} reset={reset}/>
            
          </div>
          <div className="col">
            <h5>Gerente</h5>
            <div className="div-manager-img">
              <ManagerImage />
            </div>
            <div className="d-grid gap-2 col-6 mx-auto" id="manager-button">
            <input
                className="btn btn-primary"
                value="Próxima Senha"
                type="submit"
                onClick={(e) => {nextPassword(e)}}
              />
              <input
                className="btn btn-primary"
                value="Resetar senhas"
                type="submit"
               onClick={(e) => {resetPassword(e)}}
              />
              <input
                className="btn btn-primary"
                value="Recuperar Senhas"
                type="submit"
                onClick={(e) => {recoverPassword(e)}}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Home;
