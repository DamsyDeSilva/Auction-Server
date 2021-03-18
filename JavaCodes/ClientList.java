/*
    This class is to keep track of current clients
*/

import java.util.ArrayList;


public class ClientList {
    
    private static ArrayList<String> ClientNameList;

    ClientList(){
       ClientNameList = new ArrayList<String> ();
    }

    // add client to list
    public static void addClient(String clientName){
        ClientNameList.add(clientName);
    }

    // remove client to list
    public static void removeClient(String clientName){
        ClientNameList.remove(clientName);
    }

    // remove a client from clientList
    public static boolean isClientActive(String clientName){
        return ClientNameList.contains(clientName);
    }
    
}