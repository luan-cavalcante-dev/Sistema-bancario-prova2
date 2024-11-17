package contas;

public class Contaprincipal {
	
	// implementando metados da conta pai 
	
	private String TipoDeconta;
	private String CorrentistaTitular;
	private double saldo;
	private int NumerodaConta;

	// contruindo paramentros
	public Contaprincipal (String CorrentistaTitular, String TipoDeconta, int Numerodaconta) {
		this.CorrentistaTitular = CorrentistaTitular;
		this.TipoDeconta = TipoDeconta;
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
	
	
	
	
	
	
	
	
	
	
	public String getTipoDeconta() {
		return TipoDeconta;
	}
	public void setTipoDeconta(String tipoDeconta) {
		TipoDeconta = tipoDeconta;
	}
	public String getCorrentistaTitular() {
		return CorrentistaTitular;
	}
	public void setCorrentistaTitular(String correntistaTitular) {
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
