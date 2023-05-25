import React from "react";
import Logo from "../html/img/logo.png"

function Menu(){
return(
    <>
    <header id="Menu">
        <div className="logo">
           <img src={Logo} alt="" srcSet="" />
                
        </div>
        <nav id="NavBar">
            <ul>
                <li><a href="home.jsx">Home</a></li>
                <li><a href="#">Sobre</a></li>
                <li><a href="#">Ajuda</a></li>
                <li><a href="#">Contato</a></li>
                <li><a href="/login"><button>Login</button></a></li>
                <li><a href="/cadastro"><button>Cadastro</button></a></li>
            </ul>
        </nav>
    </header>
    </>
)
}

export default Menu