package Entidades;

public class Cliente extends Usuario {

	private Double saldo = 0.0;

	public Cliente() {
	}

	public Cliente(String nome, String documento) {
		super(nome, documento);
		this.saldo = 0.0;
	}

	public boolean depositar(Empresa empresa, double valor) {
		if (valor > 0) {
			this.saldo += valor;
			empresa.receberDeposito(valor);
			return true;
		}
		return false;
	}

	public boolean sacar(Empresa empresa, double valor) {
		if (valor > 0 && valor <= this.saldo && empresa.realizarSaque(valor)) {
			this.saldo -= valor;
			return true;
		}
		return false;
	}

	public double getSaldo() {
		return this.saldo;
	}

	@Override
    public boolean validarDocumento(String cpf) {
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

    private String removeFormatacao(String documento) {
        return documento.replaceAll("[^0-9]", "");
    }

    private boolean validaDigito(String cpfParcial, int[] multiplicadores, char digitoVerificador) {
        int soma = 0;
        for (int i = 0; i < cpfParcial.length(); i++) {
            soma += Character.getNumericValue(cpfParcial.charAt(i)) * multiplicadores[i];
        }
        int resto = soma % 11;
        int digito = (resto < 2) ? 0 : 11 - resto;
        return Character.getNumericValue(digitoVerificador) == digito;
    }
}
