const API_URL = "http://localhost:8080";

export async function login(email, password) {
    
    const response = await fetch(
        `${API_URL}/auth/login`,
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email,
                password
            })
        }

        
    );

    if (!response.ok) {
        throw new Error("Email ou senha incorretos!")
    }

    return response.json();

}

export async function register(email, password) {

    const response = await fetch(
        `${API_URL}/auth/register`,
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email,
                password
            })
        }

    );

    if(!response.ok){
        throw new Error("Erro ao fazer o cadastro!");
    }

    return response.json();
    
}