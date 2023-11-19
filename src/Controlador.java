import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    Scanner scn = new Scanner(System.in);
    public void creacionCSV(){ // Creación de un archivo CSV que permita almacenar los datos de los usuarios
    
        File datosUsuarios = new File("datosUsuarios.csv");
        if (!datosUsuarios.exists()){
            try {
                datosUsuarios.createNewFile();
                System.out.println("Archivo de datosUsuarios.csv creado");
            } catch (IOException e) {
                System.out.println("Error encontrado" + e.getMessage());
            }
        }
    }

    public void creacionCSVprestamos(){ // Creación de un archivo CSV que permita almacenar los datos de los préstamos
    File datosUsuarios = new File("prestamosCSV.csv");
    if (!datosUsuarios.exists()){
        try {
            datosUsuarios.createNewFile();
            System.out.println("Archivo de prestamosCSV.csv creado");
        } catch (IOException e) {
            System.out.println("Error encontrado" + e.getMessage());
        }
    
    }
    }

    public void registroUsuario(String nombreUsuarioN, String contraseñaUsuarioN, String tipoPlan){ // El método para ir agregando a los usuarios
        String archivoCSV = "datosUsuarios.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCSV, true))) {
            writer.write(nombreUsuarioN + "," + contraseñaUsuarioN + "," + tipoPlan);
            writer.newLine();
            System.out.println("\nTe hemos registrado con éxito, " + nombreUsuarioN);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    public void iniciarSesion(String nombreUsuario, String contraseña){
        String archivoCSV = "datosUsuarios.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");

                if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseña)) {
                    System.out.println("INICIO DE SESIÓN CORRECTO.\nTe damos la bienvenida de vuelta, " + nombreUsuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarMaterial(String nombreUsuario, String contraseñaUsuario, String tipoMaterial){
        if (tipoMaterial.equalsIgnoreCase("libro")){
            String archivoCSV = "datosUsuarios.csv";
            try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] columnas = linea.split(",");
                    if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Premium")) {
                        System.out.println("Puedes prestar 5 libros");
                    } else if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Base")){
                        System.out.println("Puedes prestar 3 libros");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tipoMaterial.equalsIgnoreCase("revista")){
            System.out.print("Puedes prestar de manera ilimitada este material");
        }
    }

    public void modoPrestamo(String nombreUsuario, String contraseñaUsuario){
        String archivoCSV = "datosUsuarios.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");

                if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Premium")) {
                    scn = new Scanner(System.in);
                    System.out.println("Tienes 50 días para devolver el material");
                    System.out.print("Define el horario de entrega (AM/PM): ");
                    String horarioEntrega = scn.nextLine();
                    System.out.println("Seleccionar dirección de envío: ");
                    String direccionEnvio = scn.nextLine();
                } else if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Base")){
                    System.out.println("Tienes 30 días para devolver el material");
                    System.out.print("En qué sucursal puedes recoger el material [inserte sucursales]: ");
                    String elegirSucursal = scn.nextLine();
                    System.out.print("Pasará por el material dentro de 12 horas o 24 horas.");
                    String horaRecogida = scn.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modoPerfil(String nombreUsuario, String contraseñaUsuario){
        while (true){
            System.out.println("1. Cambiar tipo de plan.\n2. Cambiar contraseña.");
            System.out.print("Ingresa el número de opción que deseas: ");
            int opcion = scn.nextInt();
            switch (opcion) {
                case 1:
                String archivoCSV = "datosUsuarios.csv";
                List<String> lineas = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] columnas = linea.split(",");
                        if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Premium")) {
                            System.out.println("Gracias por ser nuestro cliente premium, te daremos un cupón.");
                        } else if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario) && columnas[2].equalsIgnoreCase("Base")){
                            System.out.println("Puedes cambiar de plan");
                            columnas[2] = "premium";
                        }
                        lineas.add(String.join(",", columnas));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
                    for (String linea : lineas) {
                        bw.write(linea);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                String archivoCSV2 = "datosUsuarios.csv";
                List<String> lineas2 = new ArrayList<>();
                
                try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV2))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] columnas = linea.split(",");
                        if (columnas.length >= 2 && columnas[0].equals(nombreUsuario) && columnas[1].equals(contraseñaUsuario)) {
                            scn = new Scanner(System.in);
                            System.out.print("Ingresa la nueva contraseña: ");
                            String nuevaContraseña = scn.nextLine();
                            // Cambiar la contraseña del usuario
                            columnas[1] = nuevaContraseña;
                            System.out.println("Contraseña cambiada con éxito.");
                        }
                        lineas2.add(String.join(",", columnas));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                // Escribir las líneas modificadas en el archivo
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV2))) {
                    for (String linea : lineas2) {
                        bw.write(linea);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        }
    }
