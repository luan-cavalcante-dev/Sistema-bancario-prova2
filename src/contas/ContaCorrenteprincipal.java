package contas;

public class ContaCorrenteprincipal extends Contaprincipal {
	// criando metado  limite chuqe especial
	
	private double limitechequeEspecial;
	
	public ContaCorrenteprincipal (int numeroDaconta, String CorrentistaTitular, double limitechequeEspecial) {
		super(TipoCorrentista.TITULAR, "Conta corrente principal", numeroDaconta);
		this.limitechequeEspecial = limitechequeEspecial;
	}
	
	@Override
	
	public void sacar (double valor) {
		if (valor <= 0) {
			 throw new IllegalArgumentException ("Valor para saque deve ser possitivo");
		}
		if (saldo + limitechequeEspecial <= valor) {
			throw new IllegalArgumentException ("Saldo do Cheque especial insulficiente");
		}
		
	}


	
	
	
	
	
	public double getLimitechequeEspecial() {
		return limitechequeEspecial;
	}

	public void setLimitechequeEspecial(double limitechequeEspecial) {
		this.limitechequeEspecial = limitechequeEspecial;
	}
	


}
