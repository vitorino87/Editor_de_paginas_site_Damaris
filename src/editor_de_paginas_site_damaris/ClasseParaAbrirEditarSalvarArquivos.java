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

    private static final int POSICAO_DO_CARACTERE_A_SER_PROCURADO = 2;
    private static final char CARACTERE_A_SER_PROCURADO = ')';
    private static final char ULTIMA_ALTERNATIVA_DE_CADA_QUESTAO = 'D';
    private static final int POSICAO_DA_LETRA_DA_ULTIMA_QUESTAO = 1;
    private static final int POSICAO_DA_LETRA_DA_ALTERNATIVA = 1;
    private int nome_do_input_radio;
    
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
            //Charset cs = Charset.forName("Cp1252");
            Charset cs = Charset.forName("UTF-8");
            InputStreamReader isr = new InputStreamReader(fis, cs);
            int ch;
            int contador = 0;
            String auxiliadorDeTexto = "";
            //String resposta = "";
            String text = "";
            File arquivoParaSalvar = selecionarLocalParaSalvar();
            nome_do_input_radio = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o nome do input radio inicial:"));
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
            if(!text.equals("")){                
                processarLinha(text, arquivoParaSalvar);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

    //edite o método processarLinha para o programa fazer o que você quer
    //Se necessário, edite o método realizarLeituraDaLinhaDoArquivo
    public void processarLinha(String text, File arquivo) {
        if (text.indexOf(CARACTERE_A_SER_PROCURADO) == POSICAO_DO_CARACTERE_A_SER_PROCURADO) {
            char variavel_capturadora_de_alternativa = text.charAt(POSICAO_DA_LETRA_DA_ALTERNATIVA);
            if(text.indexOf(ULTIMA_ALTERNATIVA_DE_CADA_QUESTAO)==POSICAO_DA_LETRA_DA_ULTIMA_QUESTAO){
                text+="\n<br><br>\n" +
"<button class=\"w3-button w3-green\" onclick=\"myFunction("+nome_do_input_radio+")\">Resolver</button>\n" +
"<br>\n" +
"<br>\n";                        
            }                  
            text = "\n<br><input type=\"radio\" name=\""+(nome_do_input_radio)+"\" value=\""+variavel_capturadora_de_alternativa+"\"/>" + text;            
            salvarLinhasProcessadas(arquivo.getAbsolutePath(), text);       
            if(variavel_capturadora_de_alternativa == ULTIMA_ALTERNATIVA_DE_CADA_QUESTAO)
                nome_do_input_radio++;
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
