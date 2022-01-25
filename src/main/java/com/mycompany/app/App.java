package com.mycompany.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main(String[] argv) throws IOException {
    String host = "0.0.0.0";
    short port = 8080;

    ServerSocket server = null;

    try {
      server = new ServerSocket(port);

      System.err.println("Server listening on " + host + ":" + port + "\n");
      int read;
      byte[] buffer = new byte[8192];

      while(true) {
        Socket client = server.accept();
        System.out.println("Connection accepted from " + client.getRemoteSocketAddress());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        InputStream in = client.getInputStream();

        while((read = in.read(buffer)) > 0) {
          System.out.write(buffer, 0, read);
        }

        System.out.println("");

        out.write("HTTP/1.1 200 OK");
        out.close();
        in.close();
        client.close();
      }
    }
    finally {
      System.out.println("Closing");
      if(server != null)
        server.close();
    }
  }
}
