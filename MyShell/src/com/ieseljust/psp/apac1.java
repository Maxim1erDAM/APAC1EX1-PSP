package com.ieseljust.psp;

import java.util.Scanner;

import java.util.ArrayList;

import java.lang.ProcessBuilder;

import java.io.FileReader;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.FileNotFoundException;

import java.io.InputStreamReader;

public class apac1 {

  private static Scanner sc = new Scanner(System. in );

  public static void LeerFichero(String archivo) throws FileNotFoundException,

  IOException {

    String cadena;

    FileReader f = new FileReader(archivo);

    BufferedReader b = new BufferedReader(f);

    while ((cadena = b.readLine()) != null) {

      System.out.println(cadena);

    }

    b.close();

  }

  private static void MyShell() {

    boolean repeat = true;

    while (repeat) {

      try {

        System.out.println("\u001B[34m#Introducir QUIT o CTRL+C para salir. ");

        System.out.println("\u001B[34m#APAC1 -PSP- Exercici 1. MyShell.");

        System.out.println("#By: Máxim Sánchez Porta. ");

        System.out.println("");

        System.out.print("\u001B[0m # MyShell>");

        ProcessBuilder processBuilder = new ProcessBuilder();

        ArrayList < String > script = new ArrayList < String > ();

        String comando = "";

        comando += sc.nextLine();

        System.out.print("\033[H\033[2J");

        System.out.flush();

        System.out.print("\u001B[0m");

        if (comando.equals("QUIT")) {

          System.out.println(" Anem a eixir del programa..");

          System.exit(1);

        }

        script.add(comando);

        String bashscript = "";

        for (String valor: script) {

          bashscript += valor + "";

        }

        System.out.println(" \u001B[0m  # Eixida : \u001B[0m \u001B[32m");

        processBuilder.command("bash", "-cl", bashscript.toString() + "  >>resultado.log  2> errores.log  && cat errores.log | cat resultado.log ");

        Process process = processBuilder.start();

        //Definiciones de metodos o comandos de java. Fuente: https://docs.oracle.com/javase/7/docs/api/java/io/
        //1.BufferedReader. Lee texto de una secuencia de entrada de caracteres, almacenando caracteres en búfer para proporcionar una lectura eficiente de caracteres, matrices y líneas.
        //Se puede especificar el tamaño del búfer o se puede usar el tamaño predeterminado. El valor predeterminado es lo suficientemente grande 
        //para la mayoría de los propósitos.
        //En general, cada solicitud de lectura realizada por un lector provoca que se realice una solicitud de lectura correspondiente del carácter subyacente 
        //o del flujo de bytes. Por lo tanto, es recomendable envolver un BufferedReader alrededor de cualquier lector cuyas operaciones read () puedan ser costosas, como FileReaders y InputStreamReaders. Por ejemplo,
        //2.InputStreamReader es un puente entre flujos de bytes y flujos de caracteres: lee bytes y los decodifica en caracteres usando un juego de caracteres específico.
        //El juego de caracteres que utiliza se puede especificar por nombre o se puede dar explícitamente, o se puede aceptar el juego de caracteres predeterminado de la plataforma.
        //3.El método java.lang.Process.getInputStream () obtiene el flujo de entrada del subproceso.
        //La secuencia obtiene datos canalizados desde la secuencia de salida estándar del proceso representado por este objeto de proceso.
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;

        while ((line = reader.readLine()) != null) {

          System.out.println(line);

        }

        int errorCode = process.waitFor();

        if ((errorCode == 127) || (errorCode == 2) || (errorCode != 0)) {

          System.out.println("\n \u001B[31m ");

          System.out.println("\n \u001B[31m Error. Exited with errorCode : " + errorCode);

          LeerFichero("errores.log");

        }

        if (errorCode == 0) {

          System.out.println("\n \u001B[32m No errors. Exited without error codes, result of errorCode: " + errorCode);

        }

      } catch(IOException e) {

        e.printStackTrace();

      } catch(InterruptedException e) {

        e.printStackTrace();

      } finally {

        System.out.println("\u001B[32m El programa a acabado de ejecutarse.");

      }

    }

  }

  public static void main(String[] args) {

    apac1.MyShell();

  }

}
