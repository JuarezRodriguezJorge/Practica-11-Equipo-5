import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Lee un archivo de texto plano y cuenta sus palabras.
 *
 * @author Equipo 5
 */
public class LectorArchivo {

    private File archivo;
    private String texto;
    private int totalPalabras;

    /** Crea un lector para el archivo indicado. */
    public LectorArchivo(File archivo) {
        this.archivo = archivo;
        this.texto = "";
        this.totalPalabras = 0;
    }

    /** Lee el archivo linea por linea, arma el texto completo y cuenta las palabras. */
    public void leer() throws IOException {
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        BufferedReader br = null;

        try {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(archivo), "UTF-8"));

            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");

                StringTokenizer st = new StringTokenizer(linea);
                contador += st.countTokens();
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        this.texto = sb.toString();
        this.totalPalabras = contador;
    }

    /** Devuelve el contenido completo del archivo. */
    public String getTexto() {
        return texto;
    }

    /** Devuelve el total de palabras contadas. */
    public int getTotalPalabras() {
        return totalPalabras;
    }

    /** Devuelve el nombre del archivo. */
    public String getNombreArchivo() {
        return archivo.getName();
    }
}
