package contas;

public enum TipoConta {
    CORRENTE("CORRENTE"),
    ADCIONAL("ADCIONAL"),
	POUPANCA("POUPANCA");

	private String tipo;
	TipoConta(String tipo) {
		this.tipo=tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
}
