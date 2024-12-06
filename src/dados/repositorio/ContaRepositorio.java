package dados.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import contas.ContaCorrenteAdcional;
import contas.ContaCorrenteprincipal;
import contas.ContaPoupanca;
import contas.Contaprincipal;
import contas.ErroBanco;
import contas.TipoConta;
import dados.ArquivoTipo;
import dados.GerenciaArquivos;
import dados.estrutura.SuperDataInterface;
import pessoas.Bancario;
import pessoas.Correntista;
import pessoas.Gerente;
import pessoas.enums.TipoUsuario;

public class ContaRepositorio extends GerenciaArquivos implements SuperDataInterface<Contaprincipal>{

    public ContaRepositorio() {
        super(ArquivoTipo.CONTA);
    }

    @Override
    public List<Contaprincipal> findAll() {
        List<HashMap<String, String>> dadosCrus = pegaDados();
        ArrayList<Contaprincipal> retorno = new ArrayList<Contaprincipal>();
        for (HashMap<String,String> hashMap : dadosCrus) {
            retorno.add(converte(hashMap));
        }
        return retorno;
    }

    @Override
    public Contaprincipal findOne(int codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public void insert(Contaprincipal entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Contaprincipal entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Contaprincipal converte(Map<String, String> linha) {
        String tipo = linha.get("tipoConta");
        if(tipo.toLowerCase() == TipoConta.CORRENTE.getTipo().toLowerCase()){
            return new ContaCorrenteprincipal(
                Integer.parseInt( linha.get("numerodaConta")),
                Double.parseDouble(linha.get("saldo")),
                linha.get("cpfTitular"),
                Double.parseDouble(linha.get("limitechequeEspecial"))
            );
        }
        if(tipo.toLowerCase() == TipoConta.ADCIONAL.getTipo().toLowerCase()){
            return new ContaCorrenteAdcional(
                Integer.parseInt( linha.get("numerodaConta")),
                Double.parseDouble(linha.get("saldo")),
                linha.get("cpfTitular"),
                Double.parseDouble(linha.get("limite")),
                Integer.parseInt( linha.get("numeroDaContaPrincipal"))
            );
        }
        if(tipo.toLowerCase() == TipoConta.POUPANCA.getTipo().toLowerCase()){
            return new ContaPoupanca(
                Integer.parseInt( linha.get("numerodaConta")),
                Double.parseDouble(linha.get("saldo")),
                linha.get("cpfTitular")
            );
        }

        throw new ErroBanco("Tipo de Conta desconhecido");
    }
}
