package contas;

public class ContaPoupanca extends Contaprincipal {
	// criando paramentos conta poupança
	private double rendimentoMensal;
	private int periodoSemJuros; // numero do tempo de ultimo calculo de juros
	
	public ContaPoupanca(int numerodaConta, double saldo, String cpfTitular, TipoConta tipoDaconta) {
		super(numerodaConta, saldo, cpfTitular, tipoDaconta);
		this.periodoSemJuros = 0;
		this.rendimentoMensal = 0.05;
	}

	public void calculaJuros(){

	}

	public double getRendimentoMensal() {
		return rendimentoMensal;
	}
	public void setRendimentoMensal(double rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}
	public int getPeriodoSemJuros() {
		return periodoSemJuros;
	}
	public void setPeriodoSemJuros(int periodoSemJuros) {
		this.periodoSemJuros = periodoSemJuros;
	}
	
}
