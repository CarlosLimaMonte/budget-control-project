import { useState } from "react";
import LoginPage from "./pages/LoginPage" 
import DashboardPage from "./pages/DashboardPage"

function App() {

  const [authenticated, setAuthenticated] = useState(Boolean(
    localStorage.getItem("token")
  ));

  const [hasAccount, setHasAccount] = useState(true);

  function handleRegister(){
    setHasAccount((previousValue) => !previousValue);;
}

  function handleLogin(){
    setAuthenticated(true);
  }

  function handleLogout(){

    localStorage.removeItem("token");

    setAuthenticated(false);
  }

  if (!authenticated) {
    return (
      <LoginPage onLogin={handleLogin} onRegisterClick={handleRegister} hasAccount = {hasAccount}/>
    );
  }


 
  return (
    <DashboardPage onLogout={handleLogout}/>
  );
}

export default App;