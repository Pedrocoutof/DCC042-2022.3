import java.io.*;
import java.net.Socket;

public class AutorizaCliente extends Thread{
    Socket cliente;
    AutorizaCliente(Socket cliente){
        this.cliente = cliente;
    }

    public static void main(Socket cliente) {
        try{
            ObjectOutputStream output = new  ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());

            Mensagem mensagem = (Mensagem) input.readObject();
            System.err.println(mensagem.getNome() + mensagem.getMensagem());

            while(true){
                mensagem = (Mensagem) input.readObject();
                System.out.println("[" + mensagem.getNome() + "] - [" + mensagem.getComando() + "] - " + mensagem.getMensagem());

                if(mensagem.getComando().startsWith("quit"))
                {
                    System.err.println(mensagem.getNome() + " Saiu!");
                    Mensagem msg = new Mensagem("Servidor", "Voce foi desconectado!");
                    output.writeObject(msg);
                    cliente.close();
                    break;
                }

                if(mensagem.getComando().equals("echo")) {
                    Mensagem msg = new Mensagem("Servidor", mensagem.getMensagem());
                    output.writeObject(msg);
                }

                else{
                    Mensagem msg = new Mensagem("Servidor", "Comando nao reconhecido");
                    output.writeObject(msg);
                }

            }
        }catch (Exception erro){
            System.err.println("Alguem caiu rsrs, motivo:\n " + erro.getMessage());
        }
    }

    @Override
    public void run(){
        try{
            ObjectOutputStream output = new  ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());

            Mensagem mensagem = (Mensagem) input.readObject();
            System.err.println(mensagem.getNome() + mensagem.getMensagem());

            while(true){
                mensagem = (Mensagem) input.readObject();
                System.out.println("[" + mensagem.getNome() + "] - [" + mensagem.getComando() + "] - " + mensagem.getMensagem());

                if(mensagem.getComando().startsWith("quit"))
                {
                    System.err.println(mensagem.getNome() + " Saiu!");
                    Mensagem msg = new Mensagem("Servidor", "Voce foi desconectado!");
                    output.writeObject(msg);
                    cliente.close();
                    break;
                }

                if(mensagem.getComando().equals("echo")) {
                    Mensagem msg = new Mensagem("Servidor", mensagem.getMensagem());
                    output.writeObject(msg);
                }

                else{
                    Mensagem msg = new Mensagem("Servidor", "Comando nao reconhecido");
                    output.writeObject(msg);
                }

            }
        }catch (Exception erro){
            System.err.println("Alguem caiu rsrs, motivo:\n " + erro.getMessage());
        }
    }
}
