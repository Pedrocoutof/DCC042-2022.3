import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String screen_name = "ANÔNIMO";
        if(args.length == 0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o seu nome: ");
            screen_name = scanner.next();
        }
        else{
            screen_name = args[0];
        }
        try {
            //Declarar o socket cliente
            Socket cliente = new Socket("localhost", 4444);
            System.out.println("Conexao estabelecida - " + cliente.getInetAddress() + " - " + cliente.getPort());

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectInputStream inputStream = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream outputStream =  new ObjectOutputStream(cliente.getOutputStream());

            System.out.println("["+ screen_name + "] entrou.");
            Mensagem mensagem = new Mensagem(screen_name, " Entrou!");
            outputStream.writeObject(mensagem);

            while(true) {
                String msg = stdin.readLine();

                mensagem = new Mensagem(screen_name, msg);
                outputStream.writeObject(mensagem);

                Mensagem resposta = (Mensagem) inputStream.readObject();
                System.out.println("[" + resposta.getNome() + "] - " + resposta.getMensagem());

                if (mensagem.getComando().equals("quit"))
                {
                    outputStream.writeObject(mensagem);
                    break;
                }
            }

            cliente.close();

        } catch (Exception e) {
            System.err.println("\n\n[ERRO] - Ocorreu um erro durante a conexao!");
        }
    }
}