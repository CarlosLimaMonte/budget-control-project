import { useState, useEffect} from "react";
import { createExpense, getExpenses, deleteExpense } from "../services/expenseService";
import { createIncome, getIncomes, deleteIncome } from "../services/incomeService"
import { getSummary } from "../services/summaryService";
import "./DashboardPage.css";

function DashboardPage({onLogout}){
    const [expenses, setExpenses] = useState([]);
    const [incomes, setIncomes] = useState([]);
    const [monthlySummary, setMonthlySummary] = useState("");
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(true);

    const [name, setName] = useState("");
    const [value, setValue] = useState("");
    const [categoryExpenses, setCategoryExpenses] = useState("OUTROS");
    const [categoryIncomes, setCategoryIncomes] = useState("OUTROS");

    const [dateTime, setDateTime] = useState("");
    const [clicado, setClicado] = useState(true);

    const [year, setYear] = useState(new Date().getFullYear());
    const [month, setMonth] = useState("");

    const monthNames = [
    "Janeiro",
    "Fevereiro",
    "Março",
    "Abril",
    "Maio",
    "Junho",
    "Julho",
    "Agosto",
    "Setembro",
    "Outubro",
    "Novembro",
    "Dezembro"
    ];

    
    async function loadExpenses() {

        try {

            setLoading(true);
            
            const data = await getExpenses();

            setExpenses(data);

            setMessage("");


        } catch (error) {
            
            setMessage(error.message);

        } finally {
            setLoading(false);
        }
        
    }

    async function handleCreateExpense(e) {

        e.preventDefault();

        try {
            const newExpense = {
                name,
                value: Number(value),
                categoryExpenses,
                dateTime
            };

            await createExpense(newExpense);

            await loadExpenses();

            await loadSummary();

            setName("");
            setValue("");
            setCategoryExpenses("OUTROS");
            setDateTime("");


            setMessage("Despesa criada com sucesso!")
            
            
        } catch (error) {
            
            setMessage(error.message);

        }
        
    }

    async function deleteHandleExpense(e, expenseId) {

        e.preventDefault();

        try {
            
            await deleteExpense(expenseId);

            await loadExpenses();

            await loadSummary();

            setMessage("Despesa deletada!")

        } catch (error) {
            
            setMessage(error.message)

        }
        
    }
    
    async function loadIncomes() {
        try {
            setLoading(true)

            const data = await getIncomes();

            setIncomes(data);

            setMessage("");


        } catch (error) {
            setMessage(error.message)
        } finally {
            setLoading(false)
        }
    }

    async function deleteHandleIncome(e, incomeId) {

        e.preventDefault();

        try {
            
            await deleteIncome(incomeId);

            await loadIncomes();

            await loadSummary();

            setMessage("Receota deletada!")

        } catch (error) {
            
            setMessage(error.message)

        }
        
    }

    async function handleCreateIncome(e) {

        e.preventDefault();

        try {
            const newIncome = {
                name,
                value: Number(value),
                categoryIncomes,
                dateTime
            };

            await createIncome(newIncome);

            await loadIncomes();

            await loadSummary();

            setName("");
            setValue("");
            setCategoryIncomes("OUTROS");
            setDateTime("");


            setMessage("Ganho criado com sucesso!")
            
            
        } catch (error) {
            
            setMessage(error.message);

        }
        
    }

    async function loadSummary() {
        try {
            const data = await getSummary(year, month);

            console.log(data);

            setMonthlySummary(data);

            setMessage("");

        } catch (error) {
            setMessage(error.message);
        } 
        
    }


    useEffect(() => {
        loadExpenses();
        loadIncomes();
        loadSummary();
    }, []);

    useEffect(() => {
        loadSummary();
    }, [month, year]);


    return(

        <div className="DashboardPage">
            <div className="headerDiv">
                <header>
                    <h3>Controle<br />de Gastos</h3>
                    <h1>Dashboard</h1>
                    <button onClick={onLogout} className="buttonSair">Sair</button>
                </header>
            </div>    
            <div className="divGridPrincipal">
                <div className="dashboardResumeDiv">
                    <div className="monthlyForm">
                        <select value={month} onChange={(e) => setMonth(e.target.value)}>
                            <option value="">Mês</option>
                            <option value="1">Janeiro</option>
                            <option value="2">Fevereiro</option>
                            <option value="3">Março</option>
                            <option value="4">Abril</option>
                            <option value="5">Maio</option>
                            <option value="6">Junho</option>
                            <option value="7">Julho</option>
                            <option value="8">Agosto</option>
                            <option value="9">Setembro</option>
                            <option value="10">Outubro</option>
                            <option value="11">Novembro</option>
                            <option value="12">Dezembro</option>
                        </select>
                        <input type="number"min={2000} max={2100} value={year}
                        onChange={(e) => setYear(e.target.value)} />
                    </div>

                    <div className="titleDespesas">
                        {Boolean(month) ? <div><h3>Resumo de {monthNames[month-1]} de {year}</h3></div> : <div><h3>Resumo de {year}</h3></div>}
                    </div>

                    <div className="titleDespesas">
                        <div>
                            <h3>Total de despesas</h3>
                        {Boolean(monthlySummary) ? <strong>{monthlySummary.totalExpense.toLocaleString(
                            "pt-BR",
                                {
                                    style: "currency",
                                    currency: "BRL"
                                }
                            )}</strong> : <strong>R$ 00,00</strong>}
                        </div>
                    </div>
                    <div className="titleDespesas">
                        <div>
                            <h3>Total de Receitas</h3>
                            {Boolean(monthlySummary) ? <strong>{monthlySummary.totalIncome.toLocaleString(
                            "pt-BR",
                                {
                                    style: "currency",
                                    currency: "BRL"
                                }
                            )}</strong> : <strong>R$ 00,00</strong>}
                        </div>
                    </div>
                    <div className="titleDespesas">
                        <div>
                            <h3>Balanço</h3>
                            {Boolean(monthlySummary) ? <strong>{monthlySummary.balance.toLocaleString(
                            "pt-BR",
                                {
                                    style: "currency",
                                    currency: "BRL"
                                }
                            )}</strong> : <strong>R$ 00,00</strong>}
                        </div>
                    </div>      
                </div>
                {!clicado ?
                <div className="creationDiv">
                    <h2>Nova Despesa</h2>
                    <form onSubmit={handleCreateExpense} className="createForm">
                        <input required type="text" placeholder="Nome" value={name} onChange={((e) => setName(e.target.value))}/>
                        <input required type="number" step="0.01" placeholder="Valor" value={value} onChange={((e) => setValue(e.target.value))}/>
                        <select value={categoryExpenses} onChange={((e) => setCategoryExpenses(e.target.value))}>
                            <option value="FEIRA">Feira</option>
                            <option value="ESSENCIAL">Essencial</option>
                            <option value="LAZER">Lazer</option>
                            <option value="OUTROS">Outros</option>
                        </select>

                        <input required type="date" value={dateTime} onChange={((e) => setDateTime(e.target.value))} />

                        <button type="submit" className="creationDivButton">Adicionar despesa</button>
                        <p>{message}</p>
                    </form>
                </div> :
                <div className="creationDiv">
                    <h2>Nova Receita</h2>
                    <form onSubmit={handleCreateIncome} className="createForm">
                        <input required type="text" placeholder="Nome" value={name} onChange={((e) => setName(e.target.value))}/>
                        <input required type="number" step="0.01" placeholder="Valor" value={value} onChange={((e) => setValue(e.target.value))}/>
                        <select value={categoryIncomes} onChange={((e) => setCategoryIncomes(e.target.value))}>
                            <option value="SALARIO">Salário</option>
                            <option value="RENDA_EXTRA">Renda Extra</option>
                            <option value="OUTROS">Outros</option>
                        </select>

                        <input required type="date" value={dateTime} onChange={((e) => setDateTime(e.target.value))} />

                        <button type="submit" className="creationDivButton">Adicionar receita</button>
                        <p>{message}</p>
                    </form>
                </div>}

                {loading && (<p>Carregando despesas...</p>)}

                {clicado ? <div className="todasDespesas"> 
                    <div className="buttonsTabDiv">
                    <button className={!clicado ? "activeButtonTab" : "deactiveTabButton"} onClick={() => setClicado(!clicado)} disabled={!clicado ? true : false}>Despesas</button>
                    <button className={clicado ? "activeButtonTab" : "deactiveTabButton"} onClick={() => setClicado(!clicado)} disabled={clicado ? true : false}>Receita</button>
                    </div>
                        {incomes.map((income) => (
                        <div className="expenseCard">
                            <div key={income.id} className="expense">
                                <div className="informationExpense">
                                    <h4>{income.name}</h4>
                                    <p>{income.value.toLocaleString(
                                        "pt-BR", {
                                            style: "currency",
                                            currency: "BRL"
                                        }
                                    )}</p>
                                    <p>Categoria: {income.categoryIncomes}</p>
                                    <p>{income.dateTime.split("-").reverse().join("/")}</p>
                                </div>
                                <button className="buttonDelete" onClick={(e) => deleteHandleExpense(e, expense.id)}>Delete</button>
                            </div>
                        </div>
                        ))}
                </div> :
                <div className="todasDespesas">   
                    <div className="buttonsTabDiv">
                    <button className={!clicado ? "activeButtonTab" : "deactiveTabButton"} onClick={() => setClicado(!clicado)} disabled={!clicado ? true : false}>Despesas</button>
                    <button className={clicado ? "activeButtonTab" : "deactiveTabButton"} onClick={() => setClicado(!clicado)} disabled={clicado ? true : false}>Receita</button>
                    </div>
                    
                        {expenses.map((expense) => (
                        <div className="expenseCard">
                            <div key={expense.id} className="expense">
                                <div className="informationExpense">
                                    <h4>{expense.name}</h4>
                                    <p>{expense.value.toLocaleString(
                                        "pt-BR", {
                                            style: "currency",
                                            currency: "BRL"
                                        }
                                    )}</p>
                                    <p>Categoria: {expense.categoryExpenses}</p>
                                    <p>{expense.dateTime.split("-").reverse().join("/")}</p>
                                </div>
                                <button className="buttonDelete" onClick={(e) => deleteHandleIncome(e, income.id)}>Delete</button>
                            </div>
                        </div>
                        ))}
                </div>}
            </div>
        </div>
    )
}

export default DashboardPage;