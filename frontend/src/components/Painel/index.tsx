import axios from 'axios';
import { useEffect, useState } from 'react';
import { Password } from 'types/password';
import './styles.css';

type Props = {
  pass?: string | Password;
  reset?:Boolean;
};

function Painel({pass, reset} : Props) {
  const [password, setPassword] = useState<Password[]>();

  useEffect(() => {
    axios.get(`http://localhost:3000/password/list`).then((response) => {
      setPassword(response.data);
    });
  }, [pass, reset]);


  return (
    <div className="col">
      <div className="senha-atual">
        <h4>Senha Atual: </h4>
        <span className="span-senha">{pass}</span>
      </div>

      <div className="proximas-senhas">
        <h4>Próximas Senhas:</h4>
        {password?.map((password) => (
          <span className="span-senha">{password.pass}</span>
        ))}
      </div>
    </div>
  );
}

export default Painel;
