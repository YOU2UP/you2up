import React from "react";
import Menu from "../components/Menu";
import api from "../api";
import { useState } from "react";
function Cadastro(){


const [nome, setNome] = useState("");
    const [senha, setSenha] = useState("");
    const [email, setEmail] = useState("");
    const [dataNascimento, setDataNascimento] = useState("");
    const [estagio, setEstagio] = useState("");



    function cadastrar(){
        const usuarioNovo = {
            nome: nome,
            senha: senha,
            email: email,
            dataNascimento: dataNascimento,
            estagio: estagio,
          
        }
        api.post("/usuarios",usuarioNovo).then((response) => {
            console.log(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }
    function mostrarCadastro2(){
        document.querySelector(".cadastro1").style.display = "none";
        document.querySelector(".cadastro2").style.display = "flex";
        document.querySelector(".cadastro3").style.display = "none";
        document.querySelector(".cadastro2").style.opacity = "1";
        document.querySelector(".cadastro2").style.visibility = "visible";

      }
        function mostrarCadastro3(){
            document.querySelector(".cadastro1").style.display = "none";
            document.querySelector(".cadastro2").style.display = "none";
            document.querySelector(".cadastro3").style.display = "flex";
            document.querySelector(".cadastro3").style.opacity = "1";
            document.querySelector(".cadastro3").style.visibility = "visible";
        }
    return(
    <>
    <Menu />
    <main id="cadastro">
    <div className="container">
        <div className="cadastro">
            <div className="cadastro1">
                <input type="text" placeholder="Nome" onInput={(e)=> setNome(e.target.value)}/>
                <input type="text" placeholder="Senha" onInput={(e) =>setSenha(e.target.value)}/>
                <input type="text" placeholder="Confirm. Senha"/>
                <input type="text" placeholder="E-mail" onInput={(e) => setEmail(e.target.value)}/>
                <input type="text" placeholder="Data Nascimento" onInput={(e)=>setDataNascimento(e.target.value)}/>

                <button onClick={()=>mostrarCadastro2()}> -- </button>
            </div>
            <div className="cadastro2">
                <button onClick={()=>setEstagio("Básico")}>Basico</button>
                <button onClick={()=>setEstagio("Intermediario")}>Intermediario</button>
                <button onClick={()=>setEstagio("Avançado")}>Avançado</button>
                <button onClick={()=> mostrarCadastro3()}> -- </button>
            </div>
            <div className="cadastro3">
                <input type="text" placeholder="Nome da academia" />
                <button onClick={()=>cadastrar()}>Cadastrar</button>
            </div>
            </div>
        </div>     
    </main>
    </>
    )
}

export default Cadastro;