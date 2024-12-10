package contas;

public class ContaPoupanca extends Contaprincipal {
	// criando paramentos conta poupança
	private double rendimentoMensal;
	
	public ContaPoupanca(int numerodaConta, double saldo, String cpfTitular) {
		super(numerodaConta, saldo, cpfTitular, TipoConta.POUPANCA);
		this.rendimentoMensal = 0.05;
	}

	public double simulaRendimento(int meses){
		double valorRendimento = 1 + rendimentoMensal;
		double valorPrevisto = saldo * Math.pow(valorRendimento, meses);
		return valorPrevisto;
	}

	public void atualizaSaldoComRendimento(){
		saldo = saldo * (rendimentoMensal + 1);
	}

	public double getRendimentoMensal() {
		return rendimentoMensal;
	}
	public void setRendimentoMensal(double rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}
}
