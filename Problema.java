import java.io.*;

public class Problema {

    public static void main(String[] args) {

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader("mensaje.txt"));
            bw = new BufferedWriter(new FileWriter("mensaje_cifrado.txt"));

            String linea = null;


            String clave = "IMOUTECHOY";

            while ((linea = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder(linea.length());
                bw.write(encriptarMensaje(br.readLine(),clave)); /* Escribe la cadena de caracteres en el fichero*/
                bw.newLine(); /* escribe nueva línea en el fichero */

            }
            System.out.println("El mensaje ha sido cifrado correctamente");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    //Hemos creado un metodo para encriptar el mensaje, en vez de hacerlo en medio del codigo por comodidad
    public static String encriptarMensaje(String mensaje, String clave) {
        //Utilizamos el StringBuilder el cual hemos encontrado en internet y sirve para modificar una cadena sin crear un objeto.
        StringBuilder msgEncr = new StringBuilder();
        int longitudClave = clave.length();
        //Hacemos un for con la longitud del mensaje para que nos lo ha entero
        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);

            if (Character.isLetter(caracter)) {
                char alfabeto = Character.isUpperCase(caracter) ? 'A' : 'a';
                int posicionOriginal = caracter - alfabeto;

                char claveActual = clave.charAt(i % longitudClave);
                int desplazamiento = claveActual - alfabeto;

                int nuevaPosicion = (posicionOriginal + desplazamiento) % 26;
                char nuevoCaracter = (char) (alfabeto + nuevaPosicion);

                msgEncr.append(nuevoCaracter);
            } else {
                msgEncr.append(caracter);
            }
        }

        return msgEncr.toString();
    }
}
