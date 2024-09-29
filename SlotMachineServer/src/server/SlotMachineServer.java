package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import javax.rmi.ssl.SslRMIServerSocketFactory;
import service.ISlotMachineService;

public class SlotMachineServer extends UnicastRemoteObject implements ISlotMachineService {
    
    private Map<String, Double> clientBalances;
    private Map<String, LinkedList<String>> playHistory;
    
    public SlotMachineServer() throws RemoteException {
        super();
        clientBalances = new HashMap<>();
        playHistory = new HashMap<>();
    }

    @Override
    public boolean connectClient(String clientId) throws RemoteException {
        if (!clientBalances.containsKey(clientId)) {
            clientBalances.put(clientId, 100.0);
            playHistory.put(clientId, new LinkedList<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean placeBet(String clientId, double amount) throws RemoteException {
        if (clientBalances.containsKey(clientId) && clientBalances.get(clientId) >= amount) {
            clientBalances.put(clientId, clientBalances.get(clientId) - amount);
            
            String result = spinSlotMachine();
            updatePlayHistory(clientId, result);
            
            if (result.equals("Ganhou")) {
                clientBalances.put(clientId, clientBalances.get(clientId) + (amount * 2));
            }
            
            return true;
        }
        return false;
    }

    @Override
    public double getBalance(String clientId) throws RemoteException {
        return clientBalances.getOrDefault(clientId, 0.0);
    }

    @Override
    public List<String> getLastTenPlays(String clientId) throws RemoteException {
        return playHistory.getOrDefault(clientId, new LinkedList<>());
    }

    @Override
    public String getLastPlayResult(String clientId) throws RemoteException {
        List<String> history = playHistory.get(clientId);
        return history.isEmpty() ? "Sem jogadas ainda." : history.get(history.size() - 1);
    }

    private String spinSlotMachine() {
        return new Random().nextBoolean() ? "Ganhou" : "Perdeu";
    }

    private void updatePlayHistory(String clientId, String result) {
        LinkedList<String> history = playHistory.get(clientId);
        if (history.size() == 10) {
            history.removeFirst();
        }
        history.add(result);
    }

    public static void main(String[] args) {
        try {
            System.setProperty("javax.net.ssl.keyStore", "servidor.keystore");
            System.setProperty("javax.net.ssl.keyStorePassword", "senha123");

            SslRMIServerSocketFactory sslServerSocketFactory = new SslRMIServerSocketFactory();

            ISlotMachineService service = new SlotMachineServer();
            Registry registry = LocateRegistry.createRegistry(PORTA, null, sslServerSocketFactory);
            registry.bind(NOME, service);

            System.out.println("Servidor da Máquina de Caça-Níqueis com SSL/TLS iniciado.");
        } catch (Exception e) {
            System.err.println("Erro no Servidor: " + e.getMessage());
            e.printStackTrace(); // Adiciona a impressão do rastreamento de pilha para diagnóstico
        }
    }
}
