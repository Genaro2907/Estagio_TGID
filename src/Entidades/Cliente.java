package Entidades;

import Entities.exception.InsufficientFundsException;

public class Cliente extends Usuario {
    private Double saldo;

    public Cliente() {
        this.saldo = 0.0;
    }

    public Cliente(String nome, String documento) throws IllegalArgumentException {
        super(nome, documento);
        if (!validarDocumento(documento)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.saldo = 0.0;
    }

    public void depositar(Empresa empresa, double valor) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }
        this.saldo += valor;
        empresa.receberDeposito(valor);
    }

    public void sacar(Empresa empresa, double valor) throws IllegalArgumentException, InsufficientFundsException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo");
        }
        if (valor > this.saldo) {
            throw new InsufficientFundsException("Saldo insuficiente para realizar o saque");
        }
        if (empresa.realizarSaque(valor)) {
            this.saldo -= valor;
        } else {
            throw new RuntimeException("Não foi possível realizar o saque na empresa");
        }
    }

    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public boolean validarDocumento(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            return false;
        }
        cpf = removeFormatacao(cpf);

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] multiplicadores1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadores2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        String cpfSemDigitos = cpf.substring(0, 9);
        return validaDigito(cpfSemDigitos, multiplicadores1, cpf.charAt(9))
            && validaDigito(cpf.substring(0, 10), multiplicadores2, cpf.charAt(10));
    }

    private static String removeFormatacao(String documento) {
        return documento.replaceAll("[^0-9]", "");
    }

    private static boolean validaDigito(String cpfParcial, int[] multiplicadores, char digitoVerificador) {
        int soma = 0;
        for (int i = 0; i < cpfParcial.length(); i++) {
            soma += Character.getNumericValue(cpfParcial.charAt(i)) * multiplicadores[i];
        }
        int resto = soma % 11;
        int digito = (resto < 2) ? 0 : 11 - resto;
        return Character.getNumericValue(digitoVerificador) == digito;
    }
}

