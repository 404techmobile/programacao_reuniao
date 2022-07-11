package com.webview_for_pdf.salvar_pdf;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriarLista extends AppCompatActivity {

    // creating object of WebView
    WebView printWeb;
    Elements faca_seu_melhor_ministerio = null;
    Elements discurso_tesouro = null;
    Elements vida_crista = null;
    Elements data = null;
    Elements leitura_da_semana = null;
    Elements cantico_inicial = null;
    Elements cantico_do_meio_e_final = null;
    String[] paginas = new String[4];
    Reuniao[] reuniao = new Reuniao[4];
    Calendar cal = new GregorianCalendar();
    String tempo="";
    int tempo_int=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        for(int i=0; i<paginas.length; i++){
            paginas[i] = new String();
            buscar_texto(i);
            //paginas(i);
        }

        //configura_webView();

        // Initializing the Button
        Button savePdfBtn = (Button) findViewById(R.id.savePdfBtn);

        //configura_webView();

        // loading the URL
       // webView.loadUrl("https://www.google.com");

        // setting clickListener for Save Pdf Button
        savePdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (printWeb != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Calling createWebPrintJob()
                        PrintTheWebPage(printWeb);
                    } else {
                        // Showing Toast message to user
                        Toast.makeText(CriarLista.this, "Not available for device below Android LOLLIPOP", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Showing Toast message to user
                    Toast.makeText(CriarLista.this, "WebPage not fully loaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
        for(int i=0;i<4;i++)
        {
            reuniao[i] = new Reuniao();
        }
    }


    public void buscar_texto(int i){
        String[] links_semanas = {"https://www.jw.org/pt/biblioteca/jw-apostila-do-mes/julho-agosto-2022-mwb/Programa%C3%A7%C3%A3o-da-semana-de-4-10-de-julho-de-2022-na-Apostila-da-Reuni%C3%A3o-Vida-e-Minist%C3%A9rio/",
                "https://www.jw.org/pt/biblioteca/jw-apostila-do-mes/julho-agosto-2022-mwb/Programa%C3%A7%C3%A3o-da-semana-de-11-17-de-julho-de-2022-na-Apostila-da-Reuni%C3%A3o-Vida-e-Minist%C3%A9rio/",
                "https://www.jw.org/pt/biblioteca/jw-apostila-do-mes/julho-agosto-2022-mwb/Programa%C3%A7%C3%A3o-da-semana-de-18-24-de-julho-de-2022-na-Apostila-da-Reuni%C3%A3o-Vida-e-Minist%C3%A9rio/",
                "https://www.jw.org/pt/biblioteca/jw-apostila-do-mes/julho-agosto-2022-mwb/Programa%C3%A7%C3%A3o-da-semana-de-25-31-de-julho-de-2022-na-Apostila-da-Reuni%C3%A3o-Vida-e-Minist%C3%A9rio/"
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, links_semanas[i],

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Document doc = Jsoup.parse(response);
                        data = doc.select("#p1");
                        leitura_da_semana = doc.select("#p2");
                        cantico_inicial = doc.select("#p3");
                        discurso_tesouro = doc.select("#section2 > div > ul > li");
                        faca_seu_melhor_ministerio = doc.select("#section3 > div > ul > li");
                        vida_crista = doc.select("#section4 > div > ul > li");
                        cantico_do_meio_e_final = doc.select("#section4 > div > ul > li");
                        try {
                            paginas(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //configura_webView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        //Tempo de espera e número de tentativas, repectivamente. Deu certo com os parâmetros 25000 e 5 também.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public String linha_para_tabela(String linha, String nome, String tempo){

        String linha_preenchida;
        linha_preenchida =
                "<tr>" +
                "     <td>" +
                "\t\t<span id=\"tempo_vida_crista_2\">" + tempo + "</span>\n" +
                "\t\t<span id=\"tema_vida_crista_2\">" + linha +"</span>\n" +
                "\t    </td>\n" +
                "\t    <td><span id=\"designacao_vida_crista_2\">" + nome + "</span></td>\n" +
                "        </tr>";
        return linha_preenchida;
    }

    public String linha_para_tabela_melhor_ministerio(String linha, String[] estudante, int indice, String tempo){
        String linha_preenchida;
        linha_preenchida =
                "\t<tr>\n" +
                        "\t    <td>\n" +
                        "\t\t<span id=\"tempo_parte_ministerio_3\">" + tempo + "</span>\n" +
                        "\t\t<span id=\"parte_ministerio_3\">" + linha +"</span>\n" +
                        "\t    </td>\n" +
                        "\t    <td><span id=\"estudante_label\"> Estudante/ajudante:</span></td>\n" +
                        "\t    <td><span id=\"designacao_estudante_3\">" + estudante[indice] + "/" +estudante[indice+3] + "</span></td>\n" +
                        "        </tr>\n";
        return linha_preenchida;
    }


    public String calcula_tempo_duracao_parte(String texto){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

        //cal.add(Calendar.MINUTE,30);
        //System.out.println(sdf.format(cal.getTime()));

        String tempo="";
        Pattern pattern = Pattern.compile("\\(\\d+\\s+min\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(texto);

        while (matcher.find()) {
            tempo = matcher.group();
            tempo = tempo.replaceAll("\\s+min\\)", "");
            tempo = tempo.replaceAll("\\(", "");
        }

        cal.add(Calendar.MINUTE,tempo_int);

        if(!tempo.equals("")){
            tempo_int = Integer.parseInt(tempo);
        }

        return sdf.format(cal.getTime());
    }

    public void paginas(int indice) throws JSONException {
        reuniao = (Reuniao[]) getIntent().getSerializableExtra("reuniao");

        String presidente = reuniao[indice].getPresidente();
        String oracao_inicial = reuniao[indice].getOracao_inicial();
        String joias = reuniao[indice].getJoias();
        String tesouros_orador = reuniao[indice].getTesouros();
        String tesouros_estudante = reuniao[indice].estudante_leitura;
        String[] estudante_melhor_ministerio = reuniao[indice].getEstudante();
        String[] vida_crista_nomes = reuniao[indice].getVida_Crista();
        String estudo_biblico = reuniao[indice].getEstudo_biblico();
        String oracao_final = reuniao[indice].oracao_final;

        String nome_congregacao = "GEORGE AMÉRICO";
        String data_texto = data.get(0).text();
        String leitura_string = leitura_da_semana.get(0).text();
        String cantico_inicial_string = cantico_inicial.get(0).text();
        String tesouros = discurso_tesouro.get(0).text();

        String partes_melhor_ministerio = "";

        cal.set(Calendar.HOUR, 20);
        cal.set(Calendar.MINUTE, 00);

        for(int fmm=0;fmm < faca_seu_melhor_ministerio.size(); fmm++){
            String texto_limpo;
            texto_limpo = faca_seu_melhor_ministerio.get(fmm).text();
            tempo = calcula_tempo_duracao_parte(texto_limpo);
            texto_limpo = texto_limpo.replaceAll("\\)\\s+(.*)", ")");
            partes_melhor_ministerio += linha_para_tabela_melhor_ministerio(texto_limpo, estudante_melhor_ministerio, fmm, tempo);
        }


    /*    Calendar cal = new GregorianCalendar();
        int primeiro_dia_mes = 1;
        cal.set(2022, 6, primeiro_dia_mes);
        int dia = cal.get(Calendar.DAY_OF_WEEK);

        if(cal.get(Calendar.DAY_OF_WEEK)!=2) {
            while (dia != 2) {
                primeiro_dia_mes++;
                cal.set(2022, 5, primeiro_dia_mes);
                dia = cal.get(Calendar.DAY_OF_WEEK);
                //Toast.makeText(this, String.valueOf(dia), Toast.LENGTH_SHORT).show();
            }
        }

        cal.setTime(new Date());

        //Toast.makeText(this, "Primeira segunda do mês: " + String.valueOf(primeiro_dia_mes), Toast.LENGTH_SHORT).show();

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    System.out.println(sdf.format(cal.getTime()));

    cal.add(Calendar.MINUTE,30);
    System.out.println("Hora atual:" + sdf.format(cal.getTime()));*/



        String partes_vida_crista = "";

        if(vida_crista.size() == 5) {
            for (int vc = 0; vc < vida_crista.size(); vc++) {
                String texto_limpo;
                texto_limpo = vida_crista.get(vc).text();
                tempo = calcula_tempo_duracao_parte(texto_limpo);
                texto_limpo = texto_limpo.replaceAll("\\)\\s+(.*)", ")");

                switch (vc){
                    case 0:
                        partes_vida_crista += linha_para_tabela(texto_limpo, "", tempo);
                        break;
                    case 1:
                        partes_vida_crista += linha_para_tabela(texto_limpo, vida_crista_nomes[0], tempo);
                        break;
                    case 2:
                        partes_vida_crista += linha_para_tabela(texto_limpo, vida_crista_nomes[2], tempo);
                        break;
                    case 3:
                        partes_vida_crista += linha_para_tabela(texto_limpo, "", tempo);
                        break;
                    case 4:
                        partes_vida_crista += linha_para_tabela(texto_limpo, vida_crista_nomes[3], tempo);
                        break;

                }
            }
        }

        if(vida_crista.size() == 6) {
            for (int vc = 0; vc < vida_crista.size(); vc++) {
                String texto_limpo;
                texto_limpo = vida_crista.get(vc).text();
                tempo = calcula_tempo_duracao_parte(texto_limpo);
                texto_limpo = texto_limpo.replaceAll("\\)\\s+(.*)", ")");
                if (vc > 0 && vc < vida_crista.size() - 2) {
                    partes_vida_crista += linha_para_tabela(texto_limpo, vida_crista_nomes[vc-1], tempo);
                } else {
                    if(vc == vida_crista.size()-1){
                        partes_vida_crista += linha_para_tabela(texto_limpo, vida_crista_nomes[vc-2], tempo);
                        break;
                    }
                    partes_vida_crista += linha_para_tabela(texto_limpo, "", tempo);
                }
            }
        }

        if(indice > 0){
            nome_congregacao = "\n";
        }

        String pagina = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "<style>\n" +
                "#tesouros{\n" +
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
                "<table summary=\"\" cellspacing=\"9\" cellpadding=\"0\" align=\"left\" height=\"134\" border=\"0\" width=\"700\">\n" +
                "    <tbody>\n" +
                "        <tr>\n" +
                "            <td><span id=\"nome_congregacao\">" + nome_congregacao +"</span></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><span id=\"data_leitura\"> <strong>" + data_texto + "|" + leitura_string + "</strong></span></td>\n" +
                "            <td><span id=\"presidente\"> Presidente:\t" + presidente + "</span></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "\t    <td><span id=\"tempo_cantico_1\"> 19:30</span>\n" +
                "\t\t<span id=\"cantico_1\">" + cantico_inicial_string + "</span></td>\n" +
                "\t    <td><span id=\"oracao_inicial\"> Oração:\t" + oracao_inicial + "</span></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "\t\t<span id=\"tempo_comentarios_iniciais\"> 19:35</span>\n" +
                "\t\t<span id=\"comentarios_iniciais\"> Comentários iniciais (1 min)</span></td>\n" +
                "        </tr>\n" +
                "\t<tr>\n" +
                "            <td id=\"tesouros\"><span class=\"cor_letra\" > TESOUROS DA PALAVRA DE DEUS</span></td>\n" +
                "        </tr>\n" +
                "\t<tr>\n" +
                "            <td>\n" +
                "\t\t<span id=\"tempo_discurso_tesouros\"> 19:36</span>\n" +
                "\t\t<span id=\"discurso_tesouros\">" + tesouros + "</span>\n" +
                "\t    </td>\n" +
                "\t      <td><span id=\"orador_tesouros\">" + tesouros_orador + "</span></td>\n" +
                "        </tr>\n" +
                "\t<tr>\n" +
                "            <td>\n" +
                "\t\t<span id=\"tempo_joias\"> 19:46</span>\n" +
                "\t\t<span id=\"joias_espirituais\"> • Joias espirituais</span>\n" +
                "\t        <span class=\"tempo_lado_direito\"> (10 min)</span>\n" +
                "\t    </td>\n" +
                "\t    <td><span id=\"orador_joias\">" + joias + "</span></td>\n" +
                "        </tr>\n" +
                "\t<tr>\n" +
                "            <td>\n" +
                "\t\t<span id=\"tempo_leitura_da_biblia\"> 19:56</span>\n" +
                "\t\t<span id=\"leitura_da_biblia\"> • Leitura da Bíblia</span>\n" +
                "\t        <span class=\"tempo_lado_direito\"> (4 min ou menos)</span>\n" +
                "\t    </td>\n" +
                "\n" +
                "\t    <td><span id=\"estudante_label\"> Estudante:</span></td>\n" +
                "\t    <td><span id=\"designacao_estudante_leitura_1\">" + tesouros_estudante + "</span></td>\n" +
                "        </tr>\n" +
                "\t<tr>\n" +
                "            <td id=\"melhor_no_ministerio\"><span class=\"cor_letra\">FAÇA SEU MELHOR NO MINISTÉRIO<span></td>\n" +
                "\t</tr>\n" +
                partes_melhor_ministerio +
                "\n" +
                "\n" +
                "\t<tr>\n" +
                "            <td id=\"vida_crista\"><span class=\"cor_letra\"> NOSSA VIDA CRISTÃ</span></td>\n" +
                "        </tr>\n" +
                partes_vida_crista
                +
                "    </tbody>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>";

        paginas[indice] = pagina;
        if(indice == paginas.length-1){
            configura_webView();
        }
    }

    public void configura_webView(){

        String todas_paginas = "";

        for(int i=0; i<paginas.length; i++) {
            todas_paginas += paginas[i];
        }

        // Initializing the WebView
        final WebView webView = (WebView) findViewById(R.id.webViewMain);

        //WebView webView = findViewById(R.id.webView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(100);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", todas_paginas, "text/html", "UTF-8", "");

        // Setting we View Client
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // initializing the printWeb Object
                printWeb = webView;
            }
        });

    }


    // object of print job
    PrintJob printJob;

    // a boolean to check the status of printing
    boolean printBtnPressed = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void PrintTheWebPage(WebView webView) {

        // set printBtnPressed true
        printBtnPressed = true;

        // Creating PrintManager instance
        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        // setting the name of job
        String jobName = getString(R.string.app_name) + " webpage" + webView.getUrl();

        // Creating PrintDocumentAdapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
        assert printManager != null;
        printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (printJob != null && printBtnPressed) {
            if (printJob.isCompleted()) {
                // Showing Toast Message
                Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();
            } else if (printJob.isStarted()) {
                // Showing Toast Message
                Toast.makeText(this, "isStarted", Toast.LENGTH_SHORT).show();

            } else if (printJob.isBlocked()) {
                // Showing Toast Message
                Toast.makeText(this, "isBlocked", Toast.LENGTH_SHORT).show();

            } else if (printJob.isCancelled()) {
                // Showing Toast Message
                Toast.makeText(this, "isCancelled", Toast.LENGTH_SHORT).show();
                configura_webView();

            } else if (printJob.isFailed()) {
                // Showing Toast Message
                Toast.makeText(this, "isFailed", Toast.LENGTH_SHORT).show();

            } else if (printJob.isQueued()) {
                // Showing Toast Message
                Toast.makeText(this, "isQueued", Toast.LENGTH_SHORT).show();

            }
            // set printBtnPressed false
            printBtnPressed = false;
        }
    }
}
