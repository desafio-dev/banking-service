package br.com.bycoders.desafiodev.bankingservice.domains.enums;

import lombok.Getter;

@Getter
public enum TypeOperationEnum {

    DEBITO(1, "Débito", 1),
    BOLETO(2, "Boleto", 0),
    FINANCIAMENTO(3, "Financiamento", 0),
    CREDITO(4, "Crédito", 1),
    REEBIMENTO_EMPRESTIMO(5, "Recebimento empréstimo", 1),
    VENDAS(6, "Vendas", 1),
    REEBIMENTO_TED(7, "Recebimento TED", 1),
    REEBIMENTO_DOC(8, "Recebimento DOC", 1),
    ALUGUEL(9, "Aluguel", 0);

    private final int typeOperation;
    private final String description;
    private final int type;

    TypeOperationEnum(int type, String description, int operation) {
        this.typeOperation = type;
        this.description = description;
        this.type = operation;
    }

    public static TypeOperationEnum getOperationByTypeOperation(int typeOperation) {
        for (TypeOperationEnum transaction : values()) {
            if (transaction.getTypeOperation() == typeOperation) {
                return transaction;
            }
        }
        throw new IllegalArgumentException("Tipo de transação inválido");
    }
}
