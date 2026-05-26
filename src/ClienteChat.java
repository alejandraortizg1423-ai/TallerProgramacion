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
import java.util.Scanner;

public class ClienteChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa la dirección del servidor (ejemplo: localhost): ");
        String direccion = scanner.nextLine();

        System.out.print("Ingresa el puerto (ejemplo: 5000): ");
        int puerto = Integer.parseInt(scanner.nextLine());

        try (Socket socket = new Socket(direccion, puerto)) {
            System.out.println("Conectado al servidor!");

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                try {
                    String respuesta;
                    while ((respuesta = entrada.readLine()) != null) {
                        System.out.println(respuesta);
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada.");
                }
            }).start();

            // Enviar mensajes al servidor
            String mensaje;
            while (true) {
                mensaje = scanner.nextLine();
                salida.println(mensaje);
            }

        } catch (IOException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}