package com.portfolio.BudgetControl.converter;

import com.portfolio.BudgetControl.EnumClass.CategoryExpenses;
import com.portfolio.BudgetControl.entity.Dto.ExpenseCreationDto;
import com.portfolio.BudgetControl.exception.CategoryIsNotValidException;
import com.portfolio.BudgetControl.exception.MessageIsNotCompleteException;
import com.portfolio.BudgetControl.exception.ValueMustBeANumberException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Converter {


    public static ExpenseCreationDto receiveStringConverterToExpenseCreationDto(String receive){

        verifyIfMessageIsValid(receive);

        String[] words = receive.trim().split("\\s+", 4);

        return new ExpenseCreationDto(
                words[3],
                new BigDecimal(words[1].replace(",", ".")),
                CategoryExpenses.valueOf(words[2].trim().toUpperCase()),
                LocalDate.now()
        );

    }

    private static void verifyIfMessageIsValid(String message){

        if(message == null){
            throw new MessageIsNotCompleteException("Mensagem em branco!");
        }

        String[] words = message.trim().split("\\s+", 4);

        if (words.length != 4){
            throw new MessageIsNotCompleteException("Mensagem não está completa!");
        }

        if(!"gasto".equalsIgnoreCase(words[0])){
            throw new MessageIsNotCompleteException(
                    "Comando inválido! Use: gasto valor categoria descrição"
            );
        }

        try {
            BigDecimal valor = new BigDecimal(words[1].replace(",","."));
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new ValueMustBeANumberException(
                        "O valor precisa ser maior que zero!"
                );
            }
        } catch (NumberFormatException e){
            throw new ValueMustBeANumberException("O Valor precisa ser um valor válido!");
        }

        try {
            CategoryExpenses.valueOf(words[2].trim().toUpperCase());
        } catch (IllegalArgumentException e){
            throw new CategoryIsNotValidException("Categoria não existe!");
        }

    }

}
