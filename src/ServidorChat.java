/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aleja
 */
import java.io.*;
import java.net.*;

public class ServidorChat {
    public static void main(String[] args) {
        System.out.println("Servidor iniciado, esperando conexiones...");
        try (ServerSocket servidor = new ServerSocket(5000)) {
            while (true) {
                Socket clienteSocket = servidor.accept();
                System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());
                new Thread(new ManejadorCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}