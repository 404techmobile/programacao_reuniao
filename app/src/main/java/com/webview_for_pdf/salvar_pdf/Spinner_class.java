package com.webview_for_pdf.salvar_pdf;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spinner_class extends Activity {
Spinner spinner;
ArrayAdapter adapter;


    public void Spinner_class(View find_view, String[] lista, String participante,Reuniao[] reuniao, int indice, Context activity){
        this.spinner = (Spinner) find_view;
        this.spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) activity);
        this.adapter = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, lista);
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(this.adapter);
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (participante){
                    case "presidente":
                        reuniao[indice-1].setPresidente(parent.getAdapter().getItem(position).toString());
                        break;

                    case "oracao_inicial":
                        reuniao[indice-1].setOracao_inicial(parent.getAdapter().getItem(position).toString());
                        break;

                    case "joias":
                        reuniao[indice-1].setJoias(parent.getAdapter().getItem(position).toString());
                        break;

                    case "tesouros_discurso":
                        reuniao[indice-1].setTesouros(parent.getAdapter().getItem(position).toString());
                        break;

                    case "estudante_leitura":
                        reuniao[indice-1].setEstudante_leitura(parent.getAdapter().getItem(position).toString());
                        break;

                    case "melhor_ministerio_1":
                       reuniao[indice-1].setEtudante(0, parent.getAdapter().getItem(position).toString());
                        break;

                    case "melhor_ministerio_2":
                        reuniao[indice-1].setEtudante(1, parent.getAdapter().getItem(position).toString());
                        break;

                    case "melhor_ministerio_3":
                        reuniao[indice-1].setEtudante(2, parent.getAdapter().getItem(position).toString());
                        break;

                    case "ajudante_1":
                        reuniao[indice-1].setEtudante(3, parent.getAdapter().getItem(position).toString());
                          break;

                    case "ajudante_2":
                        reuniao[indice-1].setEtudante(4, parent.getAdapter().getItem(position).toString());
                        break;

                    case "ajudante_3":
                        reuniao[indice-1].setEtudante(5, parent.getAdapter().getItem(position).toString());
                        break;

                    case "vida_crista_1":
                        reuniao[indice-1].setVida_crista(0, parent.getAdapter().getItem(position).toString());
                        break;

                    case "vida_crista_2":
                        reuniao[indice-1].setVida_crista(1, parent.getAdapter().getItem(position).toString());
                        break;

                    case "estudo_biblico":
                        reuniao[indice-1].setVida_crista(2, parent.getAdapter().getItem(position).toString());
                        break;

                    case "oracao_final":
                        reuniao[indice-1].setVida_crista(3, parent.getAdapter().getItem(position).toString());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });

}

public void teste(){

}

}