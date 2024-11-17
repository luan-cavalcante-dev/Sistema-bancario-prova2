package dados;

public enum ArquivoTipo {
    USUARIO("usuarios.csv"),
    CONTA("contas.csv");

    ArquivoTipo(String file){
        this.arquivo = file;
    }

    private String arquivo;

    public String getArquivo(){
        return arquivo;
    }
}
