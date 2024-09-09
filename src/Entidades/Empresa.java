package Entidades;

public class Empresa extends Usuario {
	private Double saldo;
	private Double taxa;

	public Empresa(String nome, String documento, Double taxa) throws IllegalArgumentException {
		super(nome, documento);
		if (!validarDocumento(documento)) {
			throw new IllegalArgumentException("CNPJ inválido");
		}
		if (taxa < 0 || taxa > 1) {
			throw new IllegalArgumentException("Taxa deve estar entre 0 e 1");
		}
		this.saldo = 1000.00;
		this.taxa = taxa;
	}

	public Empresa() {
		this.saldo = 1000.00;
		this.taxa = 0.0;
	}

	public void setTaxa(Double taxa) throws IllegalArgumentException {
		if (taxa < 0 || taxa > 1) {
			throw new IllegalArgumentException("Taxa deve estar entre 0 e 1");
		}
		this.taxa = taxa;
	}

	public Double getTaxa() {
		return this.taxa;
	}

	public void receberDeposito(double valor) throws IllegalArgumentException {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor do depósito deve ser positivo");
		}
		double valorComTaxa = valor * (1 - taxa);
		this.saldo += valorComTaxa;
	}

	public boolean realizarSaque(double valor) throws IllegalArgumentException {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor do saque deve ser positivo");
		}
		if (this.saldo >= valor) {
			this.saldo -= valor;
			return true;
		}
		return false;
	}

	public double getSaldo() {
		return this.saldo;
	}

	@Override
	public boolean validarDocumento(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty()) {
			return false;
		}
		if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
			return false;
		}
		cnpj = removeFormatacao(cnpj);

		if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
			return false;
		}

		int[] multiplicadores1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] multiplicadores2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

		String cnpjSemDigitos = cnpj.substring(0, 12);
		return validaDigito(cnpjSemDigitos, multiplicadores1, cnpj.charAt(12))
				&& validaDigito(cnpj.substring(0, 13), multiplicadores2, cnpj.charAt(13));
	}

	private static String removeFormatacao(String documento) {
		return documento.replaceAll("[^0-9]", "");
	}

	private static boolean validaDigito(String cnpjParcial, int[] multiplicadores, char digitoVerificador) {
		int soma = 0;
		for (int i = 0; i < cnpjParcial.length(); i++) {
			soma += Character.getNumericValue(cnpjParcial.charAt(i)) * multiplicadores[i];
		}
		int resto = soma % 11;
		int digito = (resto < 2) ? 0 : 11 - resto;
		return Character.getNumericValue(digitoVerificador) == digito;
	}
}