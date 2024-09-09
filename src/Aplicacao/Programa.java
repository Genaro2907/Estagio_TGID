package Aplicacao;

import java.util.Locale;
import java.util.Scanner;

import Entidades.Cliente;
import Entidades.Empresa;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Cliente cliente = new Cliente();
		Empresa empresa = new Empresa();

		try {
			System.out.println("==========================");
			System.out.print("Digite seu nome: ");
			String nome = sc.nextLine();
			cliente.setNome(nome);

			System.out.print("Digite seu CPF: ");
			String cpf = sc.nextLine();
			if (!cliente.validarDocumento(cpf)) {
				throw new IllegalArgumentException("CPF inválido!");
			}
			cliente.setDocumento(cpf);

			System.out.print("Digite o nome da empresa: ");
			String nomeemp = sc.nextLine();
			empresa.setNome(nomeemp);

			System.out.print("Digite o CNPJ da empresa: ");
			String cnpj = sc.nextLine();
			if (!empresa.validarDocumento(cnpj)) {
				throw new IllegalArgumentException("CNPJ inválido!");
			}
			empresa.setDocumento(cnpj);
			empresa.setTaxa(0.25);

			boolean continuar = true;

			while (continuar) {
				System.out.println("=============================================");
				System.out.println("Digite o número da opção que deseja realizar");
				System.out.println("Opção 1 = Sacar");
				System.out.println("Opção 2 = Depositar");
				System.out.println("Opção 3 = Saldo Atual");
				System.out.println("Opção 4 = Sair");

				int operacoes;
				try {
					operacoes = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Opção inválida. Por favor, digite um número.");
					continue;
				}

				switch (operacoes) {
				case 1:
					System.out.print("Digite o valor para saque: R$");
					try {
						double valorSaque = Double.parseDouble(sc.nextLine());
						if (cliente.sacar(empresa, valorSaque)) {
							System.out.println("Saque realizado com sucesso!");
						} else {
							System.out.println("Saque não realizado. Verifique o saldo disponível.");
						}
						System.out.println(
								"Valor atual da conta da empresa: " + String.format("%.2f", empresa.getSaldo()));
					} catch (NumberFormatException e) {
						System.out.println("Valor inválido para saque. Por favor, digite um número válido.");
					}
					break;
				case 2:
					System.out.print("Digite o valor para depósito: R$");
					try {
						double valorDeposito = Double.parseDouble(sc.nextLine());
						if (cliente.depositar(empresa, valorDeposito)) {
							System.out.println("Depósito realizado com sucesso!");
						} else {
							System.out.println("Depósito não realizado. Verifique o valor informado.");
						}
						System.out.println(
								"Valor atual da conta da empresa: " + String.format("%.2f", empresa.getSaldo()));
					} catch (NumberFormatException e) {
						System.out.println("Valor inválido para depósito. Por favor, digite um número válido.");
					}
					break;
				case 3:
					System.out.println("O saldo atual da sua conta: " + String.format("%.2f", cliente.getSaldo()));
					break;
				case 4:
					continuar = false;
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}