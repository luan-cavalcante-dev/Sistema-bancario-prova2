package pessoas.enums;

public enum TipoUsuario {
	CORRENTISTA("CORRENTISTA"),
	BANCARIO("BANCARIO"),
	GERENTE("GERENTE");

	private String tipo;
	TipoUsuario(String tipo) {
		this.tipo=tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}

}
