import { useState } from "react";
import { login, register} from "../services/authService";
import "./LoginPage.css";

function LoginPage ({onLogin, onRegisterClick, hasAccount}){

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    async function handleLogin(e) {

        e.preventDefault();

        try{
            const data = await login(email, password);

            localStorage.setItem("token", data.token);

            setMessage("Login realizado com sucesso!");

            onLogin();

        }catch(error){

            setMessage(error.message)

        }
        
    }

    async function handleRegister(e) {
        e.preventDefault();

        try {
            await register(email, password);

            setPassword("");

            setMessage("Registro realizado! Faça login");

            onRegisterClick();
        } catch (error) {
            setMessage(error.message);
        }
    }

    function changeToRegister() {
        onRegisterClick();
    }


    if (hasAccount){
            return(
        <div className="principalDivLogin">
            <div className="loginPageItens">
                <h1>Controle de Gastos!</h1>
                <div className="divCentralLogin">
                    <h2>Login</h2>
                    <form onSubmit={handleLogin} className="form">
                        <input type="email" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)}/>

                        <input type="password" placeholder="senha" value={password} onChange={(e) => setPassword(e.target.value)} />
                        
                        <button type="submit">Entrar</button>
                        
                    </form>
                    <a href="#" onClick={changeToRegister}>Não tem uma conta? Clique aqui</a>
                </div>
                <p>{message}</p>
            </div>
        </div>
    )

    } else {
        return (
        <div className="principalDivLogin">
            <div className="loginPageItens">
            <h1>Controle de Gastos!</h1>
                <div className="divCentralLogin">
                    <h2>Register</h2>
                    <form onSubmit={handleRegister} className="form">
                        <input type="email" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)}/>

                        <input type="password" placeholder="senha" value={password} onChange={(e) => setPassword(e.target.value)} />

                        <button type="submit">Registrar</button>
                        
                    </form>
                    <a onClick={changeToRegister}>Já possui uma conta? Faça o login aqui</a>
                </div>
            </div>
            <p>{message}</p>

        </div>
        )
    }


    }



export default LoginPage;