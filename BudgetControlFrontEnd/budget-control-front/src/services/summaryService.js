const API_URL = "http://localhost:8080";


export async function getSummary(year, month) {
    
    const token = localStorage.getItem("token");

    const completeUrl = (Boolean(month) ? `${API_URL}/summary?year=${year}&month=${month}` : `${API_URL}/summary?year=${year}`)

    const response = await fetch(
        completeUrl,
        {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`
            }
        }

    );


    if(!response.ok){
        throw new Error("Não foi possível concluir a pesquisa!");
    }

    return response.json();

}