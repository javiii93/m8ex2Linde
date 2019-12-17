package com.example.pr1_2_endevinaelnombre;

import android.os.Bundle;

import java.time.Clock;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.pr1_2_endevinaelnombre.MainActivity.players;

public class Ranking2Activity extends AppCompatActivity {
    String jug;
    Jugador j5;
    boolean see;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  File f = new File("contador.txt");
        File file = new File(getFilesDir(), "players.xml");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        if (!players.isEmpty()) {
            j5 = players.get(players.size() - 1);
        }

        ListView lista;
        if (file.exists()) {
            players.clear();
            recuperarJugadores();
            if (j5 != null) {

                if (players.get(players.size() - 1).nom.equals(j5.nom) && players.get(players.size() - 1).punts == j5.punts) {

                } else {
                    players.add(j5);

                }
            }

        }
        Collections.sort(players);
        lista = findViewById(R.id.listView);
        ArrayAdapter<Jugador> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        lista.setAdapter(adaptador);

        /*
for(int i=0;i< ma.players.size();i++){
  jug=ma.players.get(i).nom+", "+ma.players.get(i).punts+" puntos";
  adaptador.add(jug);
    adaptador.add(ma.players.get(i));
}*/
        escrituraJugadoresXML();
//file.delete();

/*
for(int j=0;j<adaptador.getCount();j++){
    adaptador.remove();
}*/

    }



    public void escrituraJugadoresXML() {
        File file = new File(getFilesDir(), "players.xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("jugadores");
            doc.appendChild(eRaiz);
            for (int i = 0; i < players.size(); i++) {
                // definimos el nodo que contendrá los elementos
                Element eJugador = doc.createElement("jugador");
                eRaiz.appendChild(eJugador);
                // atributo para el nodo jugador
                Attr attr = doc.createAttribute("Numero");
                attr.setValue(String.valueOf(i + 1));
                eJugador.setAttributeNode(attr);
                // definimos cada uno de los elementos y le asignamos un valor
                Element eNombre = doc.createElement("nombre");
                eNombre.appendChild(doc.createTextNode(players.get(i).nom));
                eJugador.appendChild(eNombre);
                Element ePuntuacion = doc.createElement("puntuacion");
                ePuntuacion.appendChild(doc.createTextNode(String.valueOf(players.get(i).punts)));
                eJugador.appendChild(ePuntuacion);
            }
            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recuperarJugadores() {
        File file = new File(getFilesDir(), "players.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList nList = doc.getElementsByTagName("jugador");
            System.out.println("Número de jugadores: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Jugador j1 = new Jugador();
                    j1.nom = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    j1.punts = Integer.parseInt(eElement.getElementsByTagName("puntuacion").item(0).getTextContent());
                    players.add(j1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

