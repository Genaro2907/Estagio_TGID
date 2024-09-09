package Entidades;

public class Empresa extends Usuario {

	private Double saldo = 1000.00;
	private Double taxa;

	public Empresa(String nome, String documento, Double taxa) {
		super(nome, documento);
		this.saldo = 0.0;
		this.taxa = taxa;
	}

	public Empresa() {
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public void receberDeposito(double valor) {
		double valorComTaxa = valor * (1 - taxa);
		this.saldo += valorComTaxa;
	}

	public boolean realizarSaque(double valor) {
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

	private String removeFormatacao(String documento) {
		return documento.replaceAll("[^0-9]", "");
	}

	private boolean validaDigito(String cnpjParcial, int[] multiplicadores, char digitoVerificador) {
		int soma = 0;
		for (int i = 0; i < cnpjParcial.length(); i++) {
			soma += Character.getNumericValue(cnpjParcial.charAt(i)) * multiplicadores[i];
		}
		int resto = soma % 11;
		int digito = (resto < 2) ? 0 : 11 - resto;
		return Character.getNumericValue(digitoVerificador) == digito;
	}
}