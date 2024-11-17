package dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GerenciaArquivos {
    private ArquivoTipo arquivo;

    public GerenciaArquivos(ArquivoTipo arquivo) {
        this.arquivo = arquivo;
    }

    public List<HashMap<String, String>> pegaDados() {
        ArrayList<HashMap<String, String>> dados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo.getArquivo()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                dados.add(linhaParaHashMap(linha));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return dados;
    }

    public void salvaDados(List<String> linhas){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo.getArquivo()))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public HashMap<String, String> linhaParaHashMap(String linha) {
        if (ArquivoTipo.CONTA == arquivo) {
            return deparaConta(linha);
        }
        if (ArquivoTipo.USUARIO == arquivo) {
            return deparaUsuario(linha);
        }
        throw new Error("Tipo de arquivo não implementado DEPARA");
    }

    

    private HashMap<String, String> deparaUsuario(String linha) {
        String[] dados = linha.split(";");
        HashMap<String, String> retorno = new HashMap<>();

        if (dados.length < 4) {
            throw new Error(String.format("A linha não possui os 4 dados: &s\n", linha));
        }

        retorno.put("nome", dados[0]);
        retorno.put("email", dados[1]);
        retorno.put("senha", dados[2]);
        retorno.put("cpf", dados[3]);

        return retorno;
    }



    private HashMap<String, String> deparaConta(String linha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deparaConta'");
    }
}