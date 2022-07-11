package com.webview_for_pdf.salvar_pdf;

import java.io.Serializable;

public class Reuniao implements Serializable {
String presidente;
String oracao_inicial;
String tesouros;
String joias;
String estudante_leitura;
String estudo_biblico;
String oracao_final;

String[] estudante_ajudante = new String[6];
String[] vida_crista = new String[4];

    public void Reuniao(){
        for(int i=0;i<6;i++){
            this.estudante_ajudante[i] = new String();
        }

        for(int i=0;i<4;i++){
            this.vida_crista[i] = new String();
        }

    }

public void setPresidente(String presidente){
    this.presidente = presidente;
}
public String getPresidente(){
    return this.presidente;
}

public void setOracao_inicial(String oracao_inicial){
    this.oracao_inicial = oracao_inicial;
}
public String getOracao_inicial(){
    return this.oracao_inicial;
}

public void setTesouros(String tesouros){
    this.tesouros = tesouros;
}

public String getTesouros(){
    return  this.tesouros;
}

public void setJoias(String joias){
    this.joias = joias;
}

public String getJoias(){
    return joias;
}

public void setEstudante_leitura(String estudante_leitura){
    this.estudante_leitura = estudante_leitura;
}

public void setVida_crista(int indice, String vida_crista){
    this.vida_crista[indice] = vida_crista;
    }

public String[] getVida_Crista(){
        return this.vida_crista;
    }

public String getEstudo_biblico(){
        return this.estudo_biblico;
}

public void setEtudante(int indice, String estudante){
    this.estudante_ajudante[indice] = estudante;
}

public String[] getEstudante(){
    return this.estudante_ajudante;
}

public String getEstudante_leitura(){
        return this.estudante_leitura;
}

}
