const API_URL = "http://localhost:8080";

export async function getExpenses() {

    const token = localStorage.getItem("token");

    if (!token){
        throw new Error(
            "Usuário não autenticado!"
        );
    }
    
    const response = await fetch(
        `${API_URL}/expense`,
        {
            method: "GET",

            headers: {
                Authorization: `Bearer ${token}`
            },

        }

        
    );

    if (!response.ok) {
        throw new Error("Não foi possível localizar as despesas!")
    }

    return response.json();

}

export async function createExpense(expense) {
    const token = localStorage.getItem("token");

    if (!token){
        throw new Error("Usuário não autenticado!")
    }

    const response = await fetch(
        `${API_URL}/expense`,
        {
            method: "POST",

            headers: {
                "Content-Type": "Application/json",
                Authorization: `Bearer ${token}`
            },

            body: JSON.stringify(expense)
        }
    );

    if(!response.ok){
        throw new Error("Não foi possível criar a despesa!")
    }

    return response.json();
    
}

export async function deleteExpense(expenseId) {

    const token = localStorage.getItem("token")

    const response = await fetch(
        `${API_URL}/expense/delete/${expenseId}`,
        {
        method: "DELETE",

        headers: {
            Authorization: `Bearer ${token}`
        }

        }
    );

    if(!response.ok){
        throw new Error("Não foi possível deletar!")
    }

    return response.json();
    
}