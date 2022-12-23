package com.webview_for_pdf.salvar_pdf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String[] presidentes = { "Antônio", "José", "João","Pedro","Abias"};
    String[] oracao = { "Rogério", "Renato", "Edilson", "José",  "Zedequias", "Judá", "Moisés", "Paulo", "Marcos", "Abel", "Jonas", "Joel"};
    String[] estudantes_e_ajudantes = {"Presidente", "Elias", "José", "Benjamin", "Febe", "", "Júnias", "Paulo", "Cláudia", "Pedro", "Mateus", "Lucas", "Adão", "Eva", "Cecília", "Juliana", "Gilda", "Andréia Oliveira", "Renilda", "Silvana", "Josenilda","Cristiane", "Sonildes", "Elienai","Raiana"};
    String[] estudantes_leitura_lista = {"Daniel", "Bruno", "Ronaldo José", "Lucas Cunha", "Jamilton", "Zezito", "José Ramos", "Renato"};
    String[] servos_e_anciaos = {"Rogério", "Rafael", "Luan", "Marcos Vinícius", "Rozenilton",  "Florival", "Joelson", "Moisés", "Paulo César", "Marcos Erbson", "Ezequiel", "José Souza"};
    String[] numero_semana = {"1", "2", "3", "4", "5"};
    String[] lista_participante = {"Selecione","PRESIDENTE", "ORACAO_INICIAL", "TESOUROS_DISCURSO", "JOIAS", "ESTUDANTE_LEITURA", "ESTUDANTE_1", "ESTUDANTE_2", "ESTUDANTE_3", "AJUDANTE_1", "AJUDANTE_2", "AJUDANTE_3", "VIDA_CRISTA_1", "VIDA_CRISTA_2", "ESTUDO_BIBLICO", "ORACAO_FINAL"};
    Reuniao[] reuniao = new Reuniao[4];

    int indice = 1;
    ArrayAdapter numero_da_semana_adapter;
    ArrayAdapter participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<4;i++) {
            reuniao[i] = new Reuniao();
            reuniao[i].Reuniao();
        }

        Spinner spinner_numero_da_semana = findViewById(R.id.semana);
        spinner_numero_da_semana.setOnItemSelectedListener(this);
        numero_da_semana_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, numero_semana);
        numero_da_semana_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_numero_da_semana.setAdapter(numero_da_semana_adapter);

        Spinner spinner_participante = findViewById(R.id.spinner_participante);
        spinner_participante.setOnItemSelectedListener(this);
        participante = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista_participante);
        participante.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_participante.setAdapter(participante);

        define_valores_spinner("teste");
    }

    public void define_valores_spinner(String spinner){
        View view_presidente = findViewById(R.id.presidente_spinner);
        view_presidente.setVisibility(View.GONE);
        Spinner_class presidente = new Spinner_class();
        presidente.Spinner_class(view_presidente, presidentes, "presidente",reuniao, indice, this);

        View view_oracao_inicial = findViewById(R.id.oracao_inicial);
        view_oracao_inicial.setVisibility(View.GONE);
        Spinner_class oracao_inicial = new Spinner_class();
        oracao_inicial.Spinner_class(view_oracao_inicial, oracao, "oracao_inicial",reuniao, indice, this);

        View view_joias = findViewById(R.id.joias);
        view_joias.setVisibility(View.GONE);
        Spinner_class joias = new Spinner_class();
        joias.Spinner_class(view_joias, servos_e_anciaos, "joias",reuniao, indice, this);

        View view_tesouros_discurso = findViewById(R.id.tesouros_discurso);
        view_tesouros_discurso.setVisibility(View.GONE);
        Spinner_class tesouros_discurso = new Spinner_class();
        tesouros_discurso.Spinner_class(view_tesouros_discurso, servos_e_anciaos, "tesouros_discurso", reuniao, indice, this);

        View view_estudante_leitura = findViewById(R.id.estudante_leitura);
        view_estudante_leitura.setVisibility(View.GONE);
        Spinner_class estudante_leitura = new Spinner_class();
        estudante_leitura.Spinner_class(view_estudante_leitura, estudantes_leitura_lista, "estudante_leitura", reuniao, indice, this);

        View view_melhor_ministerio_1 = findViewById(R.id.Estudante_melhor_ministerio_1);
        view_melhor_ministerio_1.setVisibility(View.GONE);
        Spinner_class melhor_ministerio_1 = new Spinner_class();
        melhor_ministerio_1.Spinner_class(view_melhor_ministerio_1, estudantes_e_ajudantes, "melhor_ministerio_1", reuniao, indice, this);

        View view_melhor_ministerio_2 = findViewById(R.id.Estudante_melhor_ministerio_2);
        view_melhor_ministerio_2.setVisibility(View.GONE);
        Spinner_class melhor_ministerio_2 = new Spinner_class();
        melhor_ministerio_2.Spinner_class(view_melhor_ministerio_2, estudantes_e_ajudantes, "melhor_ministerio_2", reuniao, indice, this);

        View view_melhor_ministerio_3 = findViewById(R.id.Estudante_melhor_ministerio_3);
        view_melhor_ministerio_3.setVisibility(View.GONE);
        Spinner_class melhor_ministerio_3 = new Spinner_class();
        melhor_ministerio_3.Spinner_class(view_melhor_ministerio_3, estudantes_e_ajudantes, "melhor_ministerio_3", reuniao, indice, this);

        View view_ajudante_1 = findViewById(R.id.Ajudante_1);
        view_ajudante_1.setVisibility(View.GONE);
        Spinner_class ajudante_1 = new Spinner_class();
        ajudante_1.Spinner_class(view_ajudante_1, estudantes_e_ajudantes, "ajudante_1", reuniao, indice, this);

        View view_ajudante_2 = findViewById(R.id.Ajudante_2);
        view_ajudante_2.setVisibility(View.GONE);
        Spinner_class ajudante_2 = new Spinner_class();
        ajudante_2.Spinner_class(view_ajudante_2, estudantes_e_ajudantes, "ajudante_2", reuniao, indice, this);

        View view_ajudante_3 = findViewById(R.id.Ajudante_3);
        view_ajudante_3.setVisibility(View.GONE);
        Spinner_class ajudante_3 = new Spinner_class();
        ajudante_3.Spinner_class(view_ajudante_3, estudantes_e_ajudantes, "ajudante_3", reuniao, indice, this);

        View view_vida_crista_1 = findViewById(R.id.Vida_crista_1);
        view_vida_crista_1.setVisibility(View.GONE);
        Spinner_class vida_crista_1 = new Spinner_class();
        vida_crista_1.Spinner_class(view_vida_crista_1, servos_e_anciaos, "vida_crista_1", reuniao, indice, this);

        View view_vida_crista_2 = findViewById(R.id.Vida_crista_2);
        view_vida_crista_2.setVisibility(View.GONE);
        Spinner_class vida_crista_2 = new Spinner_class();
        vida_crista_2.Spinner_class(view_vida_crista_2, servos_e_anciaos, "vida_crista_2", reuniao, indice, this);

        View view_estudo_biblico = findViewById(R.id.Estudo_biblico);
        view_estudo_biblico.setVisibility(View.GONE);
        Spinner_class estudo_biblico = new Spinner_class();
        estudo_biblico.Spinner_class(view_estudo_biblico, servos_e_anciaos, "estudo_biblico", reuniao, indice, this);

        View view_oracao_final = findViewById(R.id.Oracao_final);
        view_oracao_final.setVisibility(View.GONE);
        Spinner_class oracao_final = new Spinner_class();
        oracao_final.Spinner_class(view_oracao_final, servos_e_anciaos, "oracao_final", reuniao, indice, this);



        switch (spinner){
            case "presidente":
                view_presidente.setVisibility(View.VISIBLE);
                break;

            case "oracao_inicial":
                view_oracao_inicial.setVisibility(View.VISIBLE);
                break;

            case "tesouros_discurso":
                view_tesouros_discurso.setVisibility(View.VISIBLE);
                break;

            case "joias":
                view_joias.setVisibility(View.VISIBLE);
                break;

            case "estudante_leitura":
                view_estudante_leitura.setVisibility(View.VISIBLE);
                break;

            case "estudante_1":
                view_melhor_ministerio_1.setVisibility(View.VISIBLE);
                break;

            case "estudante_2":
                view_melhor_ministerio_2.setVisibility(View.VISIBLE);
                break;

            case "estudante_3":
                view_melhor_ministerio_3.setVisibility(View.VISIBLE);
                break;

            case "ajudante_1":
                view_ajudante_1.setVisibility(View.VISIBLE);
                break;

            case "ajudante_2":
                view_ajudante_2.setVisibility(View.VISIBLE);
                break;

            case "ajudante_3":
                view_ajudante_3.setVisibility(View.VISIBLE);
                break;

            case "vida_crista_1":
                view_vida_crista_1.setVisibility(View.VISIBLE);
                break;

            case "vida_crista_2":
                view_vida_crista_2.setVisibility(View.VISIBLE);
                break;

            case "estudo_biblico":
                view_estudo_biblico.setVisibility(View.VISIBLE);
                break;

            case "oracao_final":
                view_oracao_final.setVisibility(View.VISIBLE);
                break;

        }

    }

    public void mostra_webView(){
        for(int i=0; i<6;i++) {

        }
        String linha = "";
        for(int i=0; i<reuniao.length;i++) {

            linha += "\t<tr>\n" +
                    "\t\t<td><span> 8-14 </span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getPresidente() + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getOracao_inicial() + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getTesouros() + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getJoias() + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getEstudante_leitura() + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getEstudante()[0] + "/" + reuniao[i].getEstudante()[3] + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getEstudante()[1] + "/" + reuniao[i].getEstudante()[4] + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getEstudante()[2] + "/" + reuniao[i].getEstudante()[5] + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getVida_Crista()[0] + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getVida_Crista()[1] + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getVida_Crista()[2]  + "</span></td>\n" +
                    "\t\t<td><span>" + reuniao[i].getVida_Crista()[3]  + "</span></td>\n" +
                    "\t</tr>\n";
        }

        String programacao = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "<style>\n" +
                "#tesouros1{\n" +
                "\twidth: 360px;\n" +
                "\tbackground-color: #575a5d;\n" +
                "}\n" +
                "\n" +
                "#melhor_no_ministerio{\n" +
                "\twidth: 360px;\n" +
                "\tbackground-color: #be8900;\n" +
                "}\n" +
                "\n" +
                "#vida_crista{\n" +
                "\twidth: 360px;\n" +
                "\tbackground-color: #7e0024;\n" +
                "}\n" +
                "\n" +
                "\n" +
                ".cor_letra{\n" +
                "color: #ffffff;\n" +
                "font-family: Calibri;\n" +
                "font-size: 12pt;\n" +
                "font-weight: bold;\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "<table summary=\"\" cellspacing=\"1\" cellpadding=\"1\" align=\"left\" height=\"134\" border=\"1\" width=\"566\">\n" +
                "    <tbody>\n" +
                "        <tr>\n" +
                "            <td><span id=\"mes\"> MAIO </span></td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t<tr>\n" +
                "\t\t<td><span> </span></td>\n" +
                "\t\t<td><span> PRESIDENTE </span></td>\n" +
                "\t\t<td><span> ORAÇÃO INICIAL </span></td>\n" +
                "\t\t<td><span> TESOUROS </span></td>\n" +
                "\t\t<td><span> JOIAS </span></td>\n" +
                "\t\t<td><span> ESTUDANTE </span></td>\n" +
                "\t\t<td><span> ESTUDANTE 1 / AJUDANTE 1 </span></td>\n" +
                "\t\t<td><span> ESTUDANTE 2 / AJUDANTE 2 </span></td>\n" +
                "\t\t<td><span> ESTUDANTE 3 / AJUDANTE 3 </span></td>\n" +
                "\t\t<td><span> VIDA CRISTÃ 1 </span></td>\n" +
                "\t\t<td><span> VIDA CRISTÃ 2 </span></td>\n" +
                "\t\t<td><span> ESTUDO BÍBLICO</span></td>\n" +
                "\t\t<td><span> ORAÇÃO FINAL</span></td>\n" +
                "\t</tr>\n"+
                linha+
                "\n" +
                "\n" +
                "\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>";

        final WebView webView = (WebView) findViewById(R.id.webViewProgramacao);
        webView.clearCache(true);
        webView.setInitialScale(100);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", programacao, "text/html", "UTF-8", "");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter().toString().equals(numero_da_semana_adapter.toString())) {
            indice = Integer.parseInt(parent.getAdapter().getItem(position).toString());
            define_valores_spinner("teste");
        }

        if (parent.getAdapter().toString().equals(participante.toString())) {

            switch (parent.getAdapter().getItem(position).toString())
            {
                case "PRESIDENTE":
                    define_valores_spinner("presidente");
                    break;

                case "ORACAO_INICIAL":
                    define_valores_spinner("oracao_inicial");
                    break;

                case "TESOUROS_DISCURSO":
                    define_valores_spinner("tesouros_discurso");
                    break;

                case "JOIAS":
                    define_valores_spinner("joias");
                    break;

                case "ESTUDANTE_LEITURA":
                    define_valores_spinner("estudante_leitura");
                    break;

                case "ESTUDANTE_1":
                    define_valores_spinner("estudante_1");
                    break;

                case "ESTUDANTE_2":
                    define_valores_spinner("estudante_2");
                    break;

                case "ESTUDANTE_3":
                    define_valores_spinner("estudante_3");
                    break;

                case "AJUDANTE_1":
                    define_valores_spinner("ajudante_1");
                    break;

                case "AJUDANTE_2":
                    define_valores_spinner("ajudante_2");
                    break;

                case "AJUDANTE_3":
                    define_valores_spinner("ajudante_3");
                    break;

                case "VIDA_CRISTA_1":
                    define_valores_spinner("vida_crista_1");
                    break;

                case "VIDA_CRISTA_2":
                    define_valores_spinner("vida_crista_2");
                    break;

                case "ESTUDO_BIBLICO":
                    define_valores_spinner("estudo_biblico");
                    break;

                case "ORACAO_FINAL":
                    define_valores_spinner("oracao_final");
                    break;

            }

        }
        mostra_webView();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(this, parent.getAdapter().toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
/*
        MenuItem searchItem = menu.findItem(id.Busca);
        SearchView searchView = (SearchView) searchItem.getActionView();
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
  */

        return true;
    }


    public boolean OnQueryTextSubmit(String query){

        return false;
    }

    public boolean OnQueryTextChange(String newText){

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Configurar:
                //configurar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    public void configurar(View view){
        Intent intent = new Intent(getApplicationContext(), CriarLista.class);
        intent.putExtra("reuniao", reuniao);
        startActivity(intent);
    }

}
