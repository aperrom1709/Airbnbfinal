package com.programacion.Tema7.Airbnbbien;



import com.programacion.Tema7.Airbnbbien.services.Alojamientoservice;
import com.programacion.Tema7.Airbnbbien.services.GestionFicheroAirbnb;


import java.util.Scanner;

public class AppAirbnb {

    public static void main(String[] args) {
        //Este main consiste en hacer un switch que con el do se queda en bucle hata elegir la opcion 0 y por tanto este menu
        //llama a los metodos corresapondientes con el alojamientoService clase donde estan todos estos metodos

        GestionFicheroAirbnb gestion = new GestionFicheroAirbnb();
        //Esta es la ruta del fichero de malaga.csv que contiene la informcaion de propietarios...
        String rutaLectura = "C:\\Users\\Usuario\\Desktop\\Airbnb\\airbnb\\services\\malaga.csv";
        String rutaEscritura = "C:\\Users\\Usuario\\Desktop\\Airbnb\\airbnb\\services\\malaga.csv";

        Alojamientoservice alojamientoService = new Alojamientoservice(rutaLectura, rutaEscritura, gestion);

        alojamientoService.leerDatos();
        alojamientoService.escribirDatos();

        Scanner scan = new Scanner(System.in);
        String opcion = "";


        do {
            System.out.print("""
                    Que paza picha los pro de verda usamo gillet pro 
                    bienvenido a airbnb
                                 
                    1. Mostrar 20 más baratos
                    2. Mostrar 20 más caros
                    3. Filtrar por presupuesto
                    4. Modificar precio alojamiento
                    5. Modificar propietario
                    0. Salir
                                    
                    Elige una opción:
                    """);

            opcion = scan.nextLine();
            switch (opcion) {
//Aqui tenemos cada opcion que depende del numero que mandes al string de opcion ya que si mandas el 1 el string lo pasara al switch
                //y ara que se vean los 20 mas baratos y asi con cada opcion
                case "1":
                    alojamientoService.get20MasBaratos();
                    break;
                case "2":
                    alojamientoService.get20MasCaros();
                    break;
                case "3":
                    alojamientoService.getByPresupuesto();
                    break;
                case "4":
                    alojamientoService.modifyPrecioById();
                    break;
                case "5":
                    alojamientoService.modifyPropietarioById();
                    break;
                case "0":
                    System.out.println("estas fuera sabesh...");
                    break;
                default:
                    System.out.println("Error terrible");
                    break;
            }

        } while (!opcion.equalsIgnoreCase("0"));
    }
}