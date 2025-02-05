package com.programacion.Tema7.Airbnbbien.services;

import com.programacion.Tema7.Airbnbbien.model.Alojamiento;
import com.programacion.Tema7.Airbnbbien.model.Propietario;

 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
//Aqui la creacion de la clase Alojamienot service
public class Alojamientoservice {
    //Aqui creo las variables como el array el gestion....

    private ArrayList<Alojamiento> alojamientos;
    private GestionFicheroAirbnb gestionFichero;
    private String rutaLectura;
    private String rutaEscritura;

    public Alojamientoservice(String rutaDatosLectura, String rutaDatosEscritura, GestionFicheroAirbnb gestionFichero) {
        this.rutaLectura = rutaDatosLectura;
        this.rutaEscritura = rutaDatosEscritura;

        this.gestionFichero = gestionFichero;
        this.alojamientos = new ArrayList<>();
    }

    //Este metodo lee los datos del csv de malaga
    public boolean leerDatos() {

        //Lo que se hace aqui es simplemente llamar al metodo leerFicheroAlojamientos para llenar el ArrayList<Alojamiento> con todos los registros que contenga el fichero
        this.alojamientos = gestionFichero.leerFicheroAlojamientos(rutaLectura);

        // Devuelvo true o false para indicar si he podido llenar el ArrayList o no
        return this.alojamientos.isEmpty();

    }

    //Esto es para escribir los datos del fichero
    public void escribirDatos() {
        gestionFichero.escribirFicheroDatos(this.alojamientos, rutaEscritura);
    }

    //Metodo para mostrar los 20 lugares mas baratos
    public void get20MasBaratos() {
        Collections.sort(this.alojamientos);
        for (int i=0; i< Math.min(20, this.alojamientos.size()); i++) {
            System.out.println(this.alojamientos.get(i));
        }
    }

    //Metodo para mostrar los 20 lugares mas caros
    public void get20MasCaros() {
        Collections.sort(this.alojamientos);
        Collections.reverse(this.alojamientos);
        for (int i=0; i< Math.min(20, this.alojamientos.size()); i++) {
            System.out.println(this.alojamientos.get(i));
        }
    }


    // Metodo que se adapta al precio elegido
    public void getByPresupuesto() {

        Scanner scan = new Scanner(System.in);
        System.out.print("Introduzca  presupuesto : ");
        String presupuestoSt = scan.nextLine();

        try {
            double presupuesto = Double.parseDouble(presupuestoSt);

            for (Alojamiento aloja : this.alojamientos) {
                if(aloja.getPrecio() <= presupuesto) {
                    System.out.println(aloja);
                }
            }


        } catch (NumberFormatException e) {
            System.out.println("tas pasao pisha...");
        } catch (Exception e) {
            System.out.println("Fallo terrible...");
        }

    }

    //Tendria que modificar el precio sacnado el id del alojamiento
    public void modifyPrecioById() {
        System.out.print("Introduzca el ID del alojamiento deseado: ");


    }

    // Metodo que modifica un propietario usando el id
    public void modifyPropietarioById() {

        Scanner scan = new Scanner(System.in);
        System.out.print("Introduzca el ID del alojamiento deseado: ");
        String idAlojamiento = scan.nextLine();

        Alojamiento a = null;
        for (Alojamiento alojamiento : this.alojamientos) {
            if(alojamiento.getId().equalsIgnoreCase(idAlojamiento)) {
                a = alojamiento;
                break;
            }
        }


        if(a != null) {

            // Si existe el alojamiento dentro de mis datos, puedo proceder a cambiar el precio del mismo
            try {

                System.out.print("El propietario anterior es: "+a.getPropietario()+"\n");
                System.out.print("Introduzca el nombre del nuevo propietario: ");
                String nombreNuevoProp = scan.nextLine();
                System.out.print("Introduzca el id de "+nombreNuevoProp+": ");
                String idNuevoProp = scan.nextLine();

                if(!nombreNuevoProp.contains(",") && !idNuevoProp.contains(",")) {

                    Propietario nuevoPropietario = new Propietario(idNuevoProp, nombreNuevoProp);


                    // La forma más directa de hacer el cambio de precio es modificar el precio del objeto a que hemos buscado anteriormente.
                    // Este objeto está igualado al objeto del ArrayList... así que en realidad si modifico a, estoy modificando el objeto del ArrayList
                    a.setPropietario(nuevoPropietario);


                    escribirDatos();
                } else {
                    System.out.println("Datos del nuevo propietario incorrectos...");
                }

            } catch (Exception e) {
                System.out.println("fALLO TERRIBLE...");
            }

        } else {
            System.out.println("Id "+idAlojamiento+" no encontrado...");
        }
    }
}