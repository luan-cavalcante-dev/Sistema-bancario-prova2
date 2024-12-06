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
import pessoas.Usuario;

public class ContaRepositorio extends GerenciaArquivos implements SuperDataInterface<Contaprincipal> {

    public ContaRepositorio() {
        super(ArquivoTipo.CONTA);
    }

    @Override
    public List<Contaprincipal> findAll() {
        List<HashMap<String, String>> dadosCrus = pegaDados();
        ArrayList<Contaprincipal> retorno = new ArrayList<Contaprincipal>();
        for (HashMap<String, String> hashMap : dadosCrus) {
            retorno.add(converteMapParaEntidade(hashMap));
        }
        return retorno;
    }

    @Override
    public Contaprincipal findOne(int codigo) {
        List<Contaprincipal> todos = this.findAll();
        for (Contaprincipal conta : todos) {
            if (conta.getNumerodaConta() == codigo) {
                return conta;
            }
        }
        return null;
    }

    @Override
    public void insert(Contaprincipal entity) {
        List<Contaprincipal> tudo = findAll();
        tudo.add(entity);
        salvaEntidades(tudo);
    }

    private void salvaEntidades(List<Contaprincipal> tudo) {
        List<String> lista = new ArrayList<>();
        for (Contaprincipal conta : tudo) {
            lista.add(entidadeParaString(conta));
        }
        salvaDados(lista);
    }

    @Override
    public void update(Contaprincipal entity) {
        List<Contaprincipal> tudo = findAll();
        for (Contaprincipal conta : tudo) {
            if(conta.getNumerodaConta() == entity.getNumerodaConta()){
                conta = entity;
            }
        }
        salvaEntidades(tudo);
    }

    @Override
    public Contaprincipal converteMapParaEntidade(Map<String, String> linha) {
        String tipo = linha.get("tipoConta");
        if (tipo.toLowerCase() == TipoConta.CORRENTE.getTipo().toLowerCase()) {
            return new ContaCorrenteprincipal(
                    Integer.parseInt(linha.get("numerodaConta")),
                    Double.parseDouble(linha.get("saldo")),
                    linha.get("cpfTitular"),
                    Double.parseDouble(linha.get("limitechequeEspecial")));
        }
        if (tipo.toLowerCase() == TipoConta.ADCIONAL.getTipo().toLowerCase()) {
            return new ContaCorrenteAdcional(
                    Integer.parseInt(linha.get("numerodaConta")),
                    Double.parseDouble(linha.get("saldo")),
                    linha.get("cpfTitular"),
                    Double.parseDouble(linha.get("limite")),
                    Integer.parseInt(linha.get("numeroDaContaPrincipal")));
        }
        if (tipo.toLowerCase() == TipoConta.POUPANCA.getTipo().toLowerCase()) {
            return new ContaPoupanca(
                    Integer.parseInt(linha.get("numerodaConta")),
                    Double.parseDouble(linha.get("saldo")),
                    linha.get("cpfTitular"));
        }

        throw new ErroBanco("Tipo de Conta desconhecido");
    }

    private String entidadeParaString(Contaprincipal conta) {
        if (conta instanceof ContaCorrenteprincipal) {
            return contaCorrenteParaString((ContaCorrenteprincipal) conta);
        }
        if (conta instanceof ContaCorrenteAdcional) {
            contaAdcionalParaString((ContaCorrenteAdcional) conta);

        }
        if (conta instanceof ContaPoupanca) {
            contaPoupancaParaString((ContaPoupanca) conta);
        }
        throw new ErroBanco("Tipo de Conta desconhecido");
    }

    private String contaCorrenteParaString(ContaCorrenteprincipal conta) {
        String linha = String.format("%s;%s;%s;%s;%s",
                conta.getTipoDeconta(),
                conta.getNumerodaConta(),
                conta.getSaldo(),
                conta.getCpfTitular(),
                conta.getLimitechequeEspecial());
        return linha;
    }

    private String contaAdcionalParaString(ContaCorrenteAdcional conta) {
        String linha = String.format("%s;%s;%s;%s;%s;%s",
                conta.getTipoDeconta(),
                conta.getNumerodaConta(),
                conta.getSaldo(),
                conta.getCpfTitular(),
                conta.getLimite(),
                conta.getNumeroDaContaPrincipal());
        return linha;
    }

    private String contaPoupancaParaString(ContaPoupanca conta) {
        String linha = String.format("%s;%s;%s;%s;%s",
                conta.getTipoDeconta(),
                conta.getNumerodaConta(),
                conta.getSaldo(),
                conta.getCpfTitular());

        return linha;
    }
}
