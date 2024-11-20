package contas;

public class Contaprincipal {
	
	// implementando metados da conta pai 
	
	private String TipoDaconta;
	private TipoCorrentista CorrentistaTitular;
	protected double saldo;
	private int NumerodaConta;

	// contruindo paramentros
	public Contaprincipal (TipoCorrentista CorrentistaTitular, String TipoDeconta, int Numerodaconta) {
		this.CorrentistaTitular = CorrentistaTitular;
		this.TipoDaconta = TipoDeconta;
		this.NumerodaConta = Numerodaconta;
		this.saldo = 0;
			
	}
	
	public void sacar (double valor) {
		if (valor > 0 && saldo >= valor){
			saldo = saldo - valor;
			System.out.println("Saque realizado com sucesso");
		} else {
			System.out.println("Saldo insuficiente");
		
			
		}
		
	}
	
	public void depositar (double valor) {
		saldo = saldo + valor;
		
		System.out.println("Deposito realizado com sucesso");
		
	}
	
	public void transferencia (Contaprincipal contaDestino, double valorasertransferido ) {
		this.sacar(valorasertransferido);
		contaDestino.depositar(valorasertransferido);
	}
	
	
	
	
	
	
	
	
	
	
	public String getTipoDeconta() {
		return TipoDaconta;
	}
	public void setTipoDeconta(String tipoDeconta) {
		TipoDaconta = tipoDeconta;
	}
	public TipoCorrentista getCorrentistaTitular() {
		return CorrentistaTitular;
	}
	public void setCorrentistaTitular(TipoCorrentista correntistaTitular) {
		CorrentistaTitular = correntistaTitular;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public int getNumerodaConta() {
		return NumerodaConta;
	}
	public void setNumerodaConta(int numerodaConta) {
		NumerodaConta = numerodaConta;
	}
	
	

}
