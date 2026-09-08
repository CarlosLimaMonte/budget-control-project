const API_URL = "http://localhost:8080";

export async function getIncomes() {

    const token = localStorage.getItem("token");

    if(!token){
        throw new Error("Usuário não autenticado!");
    }

    const response = await fetch(
        `${API_URL}/income`,
        {
            method: "GET",

            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    if(!response.ok){
        throw new Error("Não foi possível carregar as receitas!");
    }

    return response.json();
    
}

export async function createIncome(income) {

    const token = localStorage.getItem("token");

    if(!token){
        throw new Error("Não você não esta autenticado!")
    }

    const response = await fetch(
        `${API_URL}/income`,
        {
            method: "POST",
            headers: {
                "Content-Type": "Application/Json",
                Authorization: `Bearer ${token}`
            },

            body: JSON.stringify(income)
        }
    );

    if(!response.ok){
        throw new Error("Não foi possível criar uma nova receita!")
    }

    return response.json();



    
}

export async function deleteIncome(incomeId) {

    const token = localStorage.getItem("token");

    const response = await fetch(
        `${API_URL}/income/delete/${incomeId}`,
        {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${token}`
            }

        }
    );

    if(!response.ok){
        throw new Error("Não foi deletar sua receitar!");
    }

    return response.json();
    
}