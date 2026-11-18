import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ventana que permite elegir un archivo .txt, mostrar su contenido
 * y el total de palabras.
 *
 * @author Equipo 5
 */
public class VentanaConteo extends JFrame {

    private JTextArea areaTexto;
    private JLabel etiquetaTotal;
    private JLabel etiquetaArchivo;

    /** Crea la ventana y construye su interfaz. */
    public VentanaConteo() {
        super("Contador de palabras");
        construirInterfaz();
    }

    private void construirInterfaz() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonAbrir = new JButton("Abrir archivo .txt");
        etiquetaArchivo = new JLabel("Ningun archivo seleccionado");
        panelSuperior.add(botonAbrir);
        panelSuperior.add(etiquetaArchivo);
        add(panelSuperior, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(BorderFactory.createTitledBorder("Contenido del archivo"));
        add(scroll, BorderLayout.CENTER);

        etiquetaTotal = new JLabel("Total de palabras: 0");
        etiquetaTotal.setFont(new Font("Times New Roman", Font.BOLD, 16));
        etiquetaTotal.setBorder(BorderFactory.createEmptyBorder(5, 10, 8, 10));
        add(etiquetaTotal, BorderLayout.SOUTH);

        botonAbrir.addActionListener(e -> abrirArchivo());
    }

    private void abrirArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int opcion = selector.showOpenDialog(this);
        if (opcion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = selector.getSelectedFile();
        LectorArchivo lector = new LectorArchivo(archivo);

        try {
            lector.leer();
            areaTexto.setText(lector.getTexto());
            areaTexto.setCaretPosition(0);
            etiquetaArchivo.setText(lector.getNombreArchivo());
            etiquetaTotal.setText("Total de palabras: " + lector.getTotalPalabras());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo leer el archivo:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaConteo().setVisible(true));
    }
}
