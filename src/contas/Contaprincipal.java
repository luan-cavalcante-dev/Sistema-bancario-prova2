package contas;

public class Contaprincipal {

	// implementando metados da conta pai

	private int numerodaConta;
	protected double saldo;
	private String cpfTitular;
	private TipoConta tipoDaconta;

	public Contaprincipal(int numerodaConta, double saldo, String cpfTitular, TipoConta tipoDaconta) {
		this.numerodaConta = numerodaConta;
		this.saldo = saldo;
		this.cpfTitular = cpfTitular;
		this.tipoDaconta = tipoDaconta;
	}

	public void sacar(double valor) {
		if (valor <= 0) {
			throw new ErroBanco("Valor para saque deve ser positivo");
		}
		if (saldo < valor) {
			throw new ErroBanco("Saldo insuficiente");
		}
		saldo = saldo - valor;
	}

	public void depositar(double valor) {
		saldo = saldo + valor;
	}

	public void transferencia(Contaprincipal contaDestino, double valorasertransferido) {
		this.sacar(valorasertransferido);
		contaDestino.depositar(valorasertransferido);
	}

	public TipoConta getTipoDeconta() {
		return tipoDaconta;
	}

	public void setTipoDeconta(TipoConta tipoDeconta) {
		this.tipoDaconta = tipoDeconta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getNumerodaConta() {
		return numerodaConta;
	}

	public void setNumerodaConta(int numerodaConta) {
		this.numerodaConta = numerodaConta;
	}

	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}

	public String getCpfTitular() {
		return cpfTitular;
	}
}
