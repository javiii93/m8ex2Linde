package com.example.pr1_2_endevinaelnombre;
import android.os.Bundle;
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

public class Ranking2Activity extends AppCompatActivity {
String jug;
int cont=0;
    MainActivity ma=new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        File file = new File(getFilesDir(), "players.xml");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ListView lista;
        ArrayAdapter<String> adaptador;
        lista = findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);
        if(file.exists()&&cont==0){
            recuperarJugadores();
            cont++;
        }
        Collections.sort(ma.players);


for(int i=0;i< ma.players.size();i++){
    jug=ma.players.get(i).nom+", "+ma.players.get(i).punts+" puntos";
    adaptador.add(jug);
}

}public void escrituraJugadoresXML(){
        File file = new File(getFilesDir(), "players.xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("jugadores");
            doc.appendChild(eRaiz);
for (int i=0;i<ma.players.size();i++){
    // definimos el nodo que contendrá los elementos
    Element eJugador = doc.createElement("jugador");
    eRaiz.appendChild(eJugador);
    // atributo para el nodo jugador
    Attr attr = doc.createAttribute("Numero");
    attr.setValue(String.valueOf(i+1));
    eJugador.setAttributeNode(attr);
    // definimos cada uno de los elementos y le asignamos un valor
    Element eNombre = doc.createElement("nombre");
    eNombre.appendChild(doc.createTextNode(ma.players.get(i).nom));
    eJugador.appendChild(eNombre);
    Element ePuntuacion = doc.createElement("puntuacion");
    ePuntuacion.appendChild(doc.createTextNode(String.valueOf(ma.players.get(i).punts)));
    eJugador.appendChild(ePuntuacion);

}
            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

public void recuperarJugadores(){
  File file = new File(getFilesDir(), "players.xml");
    try {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        NodeList nList = doc.getElementsByTagName("jugador");
        System.out.println("Número de jugadores: " + nList.getLength());
        for(int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Jugador j1=new Jugador();
                j1.nom=eElement.getElementsByTagName("nombre").item(0).getTextContent();
                j1.punts=Integer.parseInt(eElement.getElementsByTagName("puntuacion").item(0).getTextContent());
                ma.players.add(j1);
            }

        }


    } catch(Exception e) {
        e.printStackTrace();
    }










}


}

