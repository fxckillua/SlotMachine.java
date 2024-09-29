package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISlotMachineService extends Remote {
    
    public static final String HOST = "127.0.0.1";
    public static final String NOME = "SlotMachineService";
    public static final int PORTA = 1099;
    
    boolean connectClient(String clientId) throws RemoteException;
    boolean placeBet(String clientId, double amount) throws RemoteException;
    double getBalance(String clientId) throws RemoteException;
    List<String> getLastTenPlays(String clientId) throws RemoteException;
    String getLastPlayResult(String clientId) throws RemoteException;
}
