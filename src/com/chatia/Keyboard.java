package com.chatia;

//Clase para la gestion de entrada por teclado
//Modificado 09/2004
/*
 *  This code is from the book:
 *
 *    Winder, R and Roberts, G (2000) Developing Java
 *    Software, second edition, John Wiley & Sons.
 *
 *  It is copyright (c) 2000 Russel Winder and Graham Roberts.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que permite leer valores simples de la l?nea de comandos.
 * Si ocurre un error durante la lectura las excepciones lanzadas
 * son capturadas y  se retorna el menor valor representable
 * del tipo de datos leido.
 * <p>
 * Nota: esta no es una muy buena opci?n de tratar los posibles errores. En futuras versiones
 * (cuendo se explique la gesti?n de excepciones) se mejorar? este tratamiento.
 *
 * @author PRG
 * @version 1.0
 */
public class Keyboard {

    /**
     * Stream para leer lineas de la entrada standar (por defecto el teclado)
     *
     * @seeInputStreamReader
     * @see BufferedReader
     * @see System.in
     */
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Atributo para almacenar la linea ledia por teclado
     *
     * @see String
     */
    private static String input;

    /**
     * Lee un <CODE>int</CODE> introducido por el teclado.
     *
     * @return Retorna el valor introducido por el teclado. Si se introduce un valor que no es correcto se retorna el minimo
     * valor representable  en int (Integer.MIN_VALUE)
     * @see Integer
     */
    public static final synchronized int readInteger() {
        input = "";
        int value = Integer.MIN_VALUE;
        try {
            input = in.readLine();
        } catch (IOException e) {
            error("standard input");
        }
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            value = Integer.MIN_VALUE;
        }
        return value;
    }

    /**
     * Lee un <CODE>short</CODE> introducido por el teclado.
     *
     * @return Retorna el valor introducido por el teclado. Si se introduce un valor que no es correcto se retorna el minimo
     * valor representable  en short (Short.MIN_VALUE)
     * @see Short
     */
    public static final synchronized short readShort() {
        input = "";
        short value = Short.MIN_VALUE;
        try {
            input = in.readLine();
        } catch (IOException e) {
            error("standard input");
        }
        try {
            value = Short.parseShort(input);
        } catch (NumberFormatException e) {
            value = Short.MIN_VALUE;
        }
        return value;

    }

    /**
     * Lee un <CODE>long</CODE> introducido por el teclado.
     *
     * @return Retorna el valor introducido por el teclado. Si se introduce un valor que no es correcto se retorna el minimo
     * valor representable  en long (Long.MIN_VALUE)
     * @see Long
     */

    public static final synchronized long readLong() {
        input = "";
        long value = Long.MIN_VALUE;
        try {
            input = in.readLine();
        } catch (IOException e) {
            error("standard input");
        }
        try {
            value = Long.parseLong(input);
        } catch (NumberFormatException e) {
            value = Long.MIN_VALUE;
        }
        return value;
    }


    /**
     * Lee un <CODE>float</CODE> introducido por el teclado.
     *
     * @return Retorna el valor introducido por el teclado. Si se introduce un valor que no es correcto se retorna el minimo
     * valor representable  en float (Float.MIN_VALUE)
     * @see Float
     */
    public static final synchronized float readFloat() {
        input = "";
        float value = Float.MIN_VALUE;
        try {
            input = in.readLine();
        } catch (IOException e) {
            error("standard input");
        }
        try {
            value = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            value = Float.MIN_VALUE;
        }
        return value;
    }


    /**
     * Lee un <CODE>long</CODE> introducido por el teclado.
     *
     * @return Retorna el valor introducido por el teclado. Si se introduce un valor que no es correcto se retorna el minimo
     * valor representable  en double (Double.MIN_VALUE)
     * @see Double
     */

    public static final synchronized double readDouble() {
        input = "";
        double value = Double.MIN_VALUE;
        try {
            input = in.readLine();
        } catch (IOException e) {
            error("standard input");
        }
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            value = Double.MIN_VALUE;
        }
        return value;
    }


    /**
     * Lee un <CODE>char</CODE> introducido por el teclado.
     *
     * @return Retorna el caracter introducido por el teclado.
     */
    public static final synchronized char readChar() {
        char c = ' ';
        try {
            c = (char) in.read();
        } catch (IOException e) {
            error("standard input");
        }
        return c;
    }

    /**
     * Lee un <CODE>String</CODE> introducido por el teclado.
     *
     * @return Retorna la cadena de caracteres introducida por el teclado.
     * @see String
     */
    public static final synchronized String readString() {
        String s = "";
        try {
            s = in.readLine();
        } catch (IOException e) {
            error("standard input");
        }
        if (s == null) {
            s = "";
        }
        return s;
    }

    /**
     * Lanzado cuando ocurre un error.Muestra un mensaje y termina
     */
    private static void error(String msg) {
        System.err.println("Error: " + msg);
        System.err.println("Imposible continuar la ejecucion del programa");
        System.exit(0);
    }
}