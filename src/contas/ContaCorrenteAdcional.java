package contas;

public class ContaCorrenteAdcional extends Contaprincipal{
	
	// declarando paramentros conta corrente adicional
		private double limite;
		private int numeroDaContaPrincipal;
		
		public ContaCorrenteAdcional(int numerodaConta, double saldo, String cpfTitular,
				double limite, int numeroDaContaPrincipal) {
			super(numerodaConta, saldo, cpfTitular, TipoConta.ADCIONAL);
			this.limite = limite;
			this.numeroDaContaPrincipal = numeroDaContaPrincipal;
		}



		@Override
		public void sacar (double valor) {
			if (valor <= 0) {
				throw new ErroBanco ("Valor para saque deve ser possitivo");
			}
			
			if (saldo < valor && limite < valor) {
				throw new ErroBanco ("Saldo ou limite insuficiente");
			}
			saldo -= valor;
		}
		


		public int getNumeroDaContaPrincipal() {
			return numeroDaContaPrincipal;
		}

		public void setNumeroDaContaPrincipal(int numeroDaContaPrincipal) {
			this.numeroDaContaPrincipal = numeroDaContaPrincipal;
		}

		public double getLimite() {
			return limite;
		}

		public void setLimite(double limite) {
			this.limite = limite;
		}
}
