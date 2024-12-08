package contas;

import java.util.List;

import dados.repositorio.ContaRepositorio;

public class ContaCorrenteAdcional extends Contaprincipal{
	
	// declarando paramentros conta corrente adicional
		private double limite;
		private int numeroDaContaPrincipal;
		
		public ContaCorrenteAdcional(int numerodaConta, String cpfTitular,
				double limite, int numeroDaContaPrincipal) {
			super(numerodaConta, 0, cpfTitular, TipoConta.ADCIONAL);
			this.limite = limite;
			this.numeroDaContaPrincipal = numeroDaContaPrincipal;
		}



		@Override
		public void sacar (double valor) {
			if (valor <= 0) {
				throw new ErroBanco ("Valor para saque deve ser possitivo");
			}
			
			contaPrincipal = getContaPrincipal();

			if (contaPrincipal.getSaldo() < valor || limite < valor) {
				throw new ErroBanco ("Saldo ou limite insuficiente");
			}
			contaPrincipal.sacar(valor);
		}

		private ContaCorrenteprincipal contaPrincipal;

		public ContaCorrenteprincipal getContaPrincipal() {
			if(this.contaPrincipal != null){
				return this.contaPrincipal;
			}

			ContaRepositorio contaRepositorio = new ContaRepositorio();
			List<Contaprincipal> contas = contaRepositorio.findAll();
			Contaprincipal contaPrincipal = null;
			for (Contaprincipal conta : contas) {
				if (conta.getCpfTitular().equals(getCpfTitular()) && conta.getTipoDeconta().equals(TipoConta.CORRENTE)) {
					contaPrincipal = conta;
					break;
				}
			}
			if(contaPrincipal == null){
				throw new ErroBanco ("Conta principal nao encontrada");
			}
			this.contaPrincipal = (ContaCorrenteprincipal) contaPrincipal;
			return (ContaCorrenteprincipal) contaPrincipal;
		}


		@Override
		public void depositar(double valor) {
			throw new ErroBanco ("Conta adicional nao pode depositar");
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
