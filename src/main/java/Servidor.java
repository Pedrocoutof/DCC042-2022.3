import java.net.ServerSocket;

public class Servidor {
        public static void main(String[] args) {
            try{
                ServerSocket servidor = new ServerSocket(4444);
                System. out.println("Servidor iniciado...");

                while(true) {
                    AutorizaCliente autorizaCliente = new AutorizaCliente(servidor.accept());
                    autorizaCliente.start();
                }
                }   catch(Exception e) {
                System.err.println("[ERRO] - Nao foi possivel inicializar servidor\n Motivo: " + e.getMessage());
            }
    }
}