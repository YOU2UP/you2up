import React, { useEffect } from "react";
import Menu from "../components/Menu";
function Login() {
  return (
    <>
    <Menu/>
   
    <div className="container">
      <div className="fundo">
        <div className="login">
          <h1>Login</h1>
       
            <input type="text" placeholder="Email" />
            <input type="password" placeholder="Senha" />
            <button type="submit">Entrar</button>
         
        </div>
      </div>
    </div>
    </>
  );
}

export default Login;
