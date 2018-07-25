/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor_de_paginas_site_damaris;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Win7
 */
public class ClasseParaAbrirEditarSalvarArquivos {

    public File abrirArquivo() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);
        File f = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
        } else {
            System.exit(0);
        }
        return f;
    }

    @SuppressWarnings("empty-statement")
    public void realizarLeituraDaLinhaDoArquivo(File file) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            Charset cs = Charset.forName("Cp1252");
            InputStreamReader isr = new InputStreamReader(fis, cs);
            int ch;
            int contador = 0;
            String auxiliadorDeTexto = "";
            //String resposta = "";
            String text = "";
            File arquivoParaSalvar = selecionarLocalParaSalvar();
            while ((ch = isr.read()) != -1) {
                if (ch != 13) {
                    if (ch != 10) {
                        text += String.valueOf((char) ch);
                    }
                } else {
                    text += " ";
                    processarLinha(text, arquivoParaSalvar);
                    text = "";
                }
            }
            processarLinha(text, arquivoParaSalvar);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

    //edite o método processarLinha para o programa fazer o que você quer
    //Se necessário, edite o método realizarLeituraDaLinhaDoArquivo
    public void processarLinha(String text, File arquivo) {
        if (text.indexOf(")") == 1) {
            text = "\n" + text;
            salvarLinhasProcessadas(arquivo.getAbsolutePath(), text);
        } else {
            salvarLinhasProcessadas(arquivo.getAbsolutePath(), text);
        }
        //return auxiliarDeTexto;
    }

    public void salvarLinhasProcessadas(String file, String textoParaSalvar) {
        try {
            if (file != null) {
                try (FileWriter fw = new FileWriter(file, true)) {
                    fw.write(textoParaSalvar);
                }
            }
        } catch (Exception ex) {

        }

    }

    public File selecionarLocalParaSalvar() {
        JFileChooser fc = new JFileChooser();
        JOptionPane.showMessageDialog(null, "Escolha o local onde salvar");
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnval = fc.showSaveDialog(fc);
        File file = null;
        if (returnval == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "O programa será encerrado!");
            System.exit(0);
        }
        return file;
    }
}
