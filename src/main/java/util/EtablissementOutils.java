/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import static console.Instructif.printlnConsoleIHM;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import metier.modele.Etablissement;

/**
 *
 * @author echaffraix
 */
public class EtablissementOutils {
    
    public static Etablissement getEtablissement(String codeUAI) {
        JsonObject infoEtablissement = null;
        Etablissement etablissement = new Etablissement(codeUAI);
        
        try {
            printlnConsoleIHM("Début API");
            // TODO: adapter l'URL de l'API et la liste des paramètres
            URI requestUri = URI.create(
                    "https://data.education.gouv.fr/api/explore/v2.1/catalog/datasets/fr-en-adresse-et-geolocalisation-etablissements-premier-et-second-degre/records"
                    + "?refine=numero_uai:"+ URLEncoder.encode(codeUAI, StandardCharsets.UTF_8)
                );

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
            HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                String body = (String) httpResponse.body();
                System.out.println(body);

                infoEtablissement = Json.createReader(new StringReader(body)).readObject();
            }
            else {
                throw new IOException("HTTP Error Status Code "+httpResponse.statusCode());
            }

        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
            infoEtablissement = null;
        }
        
        if (infoEtablissement != null) {
            JsonArray results = infoEtablissement.getJsonArray("results");
            if (results != null && !results.isEmpty()) {
                JsonObject premierResult = results.getJsonObject(0);
                String appellationOfficielle = premierResult.getString("appellation_officielle", "Nom inconnu");
                etablissement.setNom(appellationOfficielle);
            }
        }
        
        return etablissement;
    }
}
