package contas;

public class ContaCorrenteprincipal extends Contaprincipal {
	// criando metado  limite chuqe especial
	private double limitechequeEspecial;
	
	public ContaCorrenteprincipal(int numerodaConta, double saldo, String cpfTitular,
			double limitechequeEspecial) {
		super(numerodaConta, saldo, cpfTitular, TipoConta.CORRENTE);
		this.limitechequeEspecial = limitechequeEspecial;
	}

	@Override	
	public void sacar (double valor) {
		if (valor <= 0) {
			 throw new ErroBanco ("Valor para saque deve ser positivo");
		}
		if (saldo + limitechequeEspecial <= valor) {
			throw new ErroBanco ("Saldo do Cheque especial insulficiente");
		}
		saldo = saldo - valor;
	}
	
	public double getLimitechequeEspecial() {
		return limitechequeEspecial;
	}

	public void setLimitechequeEspecial(double limitechequeEspecial) {
		this.limitechequeEspecial = limitechequeEspecial;
	}
}
