import React from "react";
import Logo from "../html/img/logo.png"
function Menu(){
return(
    <>
    <header id="Menu">
        <div class="logo">
           <img src={Logo} alt="" srcset="" />
                
        </div>
        <nav id="NavBar">
            <ul>
                <li><a href="">Home</a></li>
                <li><a href="">Sobre</a></li>
                <li><a href="">Ajuda</a></li>
                <li><a href="#">Contato</a></li>
                <li><a href="#"><button>Login</button></a></li>
                <li><a href="#"><button>Cadastro</button></a></li>
            </ul>
        </nav>
    </header>
    </>
)
}

export default Menu