import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Mensagem implements Serializable {
    private String nome;
    private String mensagem;
    private String comando;

    private final List<String> listaComandos = Arrays.asList("quit", "echo");

    Mensagem(String nome, String mensagem){
        this.nome = nome;
        this.mensagem = mensagem;
        this.comando = "null";

        for(int i = 0; i < listaComandos.size() ; i++ ){
            if(mensagem.startsWith(listaComandos.get(i))){
                this.comando = listaComandos.get(i);
                this.mensagem = mensagem.replace(listaComandos.get(i),"");
            }
        }

        if(this.mensagem.isEmpty()){
            this.mensagem = "{null-message}";
        }
    }

    public String getComando() {
        return comando;
    }

    public String getMensagem() {
        if(mensagem.isBlank() || mensagem.isEmpty()){
            return " ";
        }
        return mensagem;
    }

    public String getNome() {
        return nome;
    }

}
