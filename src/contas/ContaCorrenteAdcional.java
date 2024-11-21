package contas;

public class ContaCorrenteAdcional extends Contaprincipal{
	
	// declarando paramentros conta corrente adicional
		private double limite;
		private int numeroDaContaPrincipal;
		
		
		

		public ContaCorrenteAdcional (String CorrentistaTitular, int Numerodaconta, double limite, int numeroDaContaPrincipal) {
				super (TipoCorrentista.DEPENDENTE, "Conta corrente adicional", Numerodaconta);
				this.limite = limite;
				this.numeroDaContaPrincipal = numeroDaContaPrincipal;
			
		}
		
		@Override
		
		public void sacar (double valor) {
			if (valor <= 0) {
				throw new IllegalArgumentException ("Valor para saque deve ser possitivo");
		
			}
			
			if (saldo < valor && limite < valor) {
				throw new IllegalArgumentException ("Saldo ou limite insuficiente");

			}
		
		
		
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
