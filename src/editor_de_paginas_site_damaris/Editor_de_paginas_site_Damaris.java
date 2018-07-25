/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor_de_paginas_site_damaris;

import java.io.IOException;

/**
 *
 * @author Win7
 */
public class Editor_de_paginas_site_Damaris {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ClasseParaAbrirEditarSalvarArquivos cp = new ClasseParaAbrirEditarSalvarArquivos();
        cp.realizarLeituraDaLinhaDoArquivo(cp.abrirArquivo());
        
    }
    
}
