package neki.meteor.clans.client;


import com.google.gson.Gson;
import neki.meteor.clans.MeteorClans;
import neki.meteor.clans.clan.Clan;
import neki.meteor.clans.clan.User;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private DataInputStream inFromServer;

    private String server; private int port;

    public Client(String server, int port) {
        this.server = server; this.port = port;
        create();
    }

    private void create() {
        try
        {
            this.clientSocket = new Socket(server, port);
            this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
            this.inFromServer = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException ignored) { }
    }

    public boolean sendData(String message) {
        try {
            byte[] utf8bytes = message.getBytes("UTF-8");
            outToServer.write(utf8bytes, 0, utf8bytes.length);
            return true;
        }
        catch (IOException exception) { return false; }
    }
    public boolean sendData(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        return sendData(json);
    }
    public void getData() throws IOException {
        byte[] msg = new byte[1024];
        int count = inFromServer.read(msg, 0, msg.length);
        String message = new String(msg, 0, count);
        Gson gson = new Gson();
        Clan read = gson.fromJson(message, Clan.class);
        if (read != null && read.name.equals(MeteorClans.clan.name)) {
            MeteorClans.clan = read;
        }
    }
    public void reconecter() {
        if (clientSocket == null && clientSocket.isClosed()) {
            create();
        }
    }
}
