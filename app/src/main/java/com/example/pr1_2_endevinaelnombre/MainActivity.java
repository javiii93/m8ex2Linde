package com.example.pr1_2_endevinaelnombre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Jugador> players = new ArrayList<>();
    Button checkButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Button exit;
    Button rankingButton;
    TextView textView1;
    EditText usernumber;
    int numberToSolve = new Random().nextInt(100);
    String comentario, nickname;
    int contadorIntentos, nombre;
    ImageView imageView;
    Jugador j1 = new Jugador();
    File file,photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkButton = findViewById(R.id.button);
        rankingButton = findViewById(R.id.button2);
        exit = findViewById(R.id.button3);
        usernumber = findViewById(R.id.editText);
        file = new File(getFilesDir(), "players.xml");
        textView1 = findViewById(R.id.textView4);
        textView1.setText("Numero Intents: " + contadorIntentos + " Numero a adivinar: " + numberToSolve);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = Integer.parseInt(usernumber.getText().toString());
                contadorIntentos++;
                if (nombre < numberToSolve) {

                    comentario = "El numero que buscas es mayor";
                    textView1.setText("Numero Intents: " + contadorIntentos + " Numero a adivinar: " + numberToSolve);
                    Toast.makeText(getApplicationContext(), comentario, Toast.LENGTH_LONG).show();
                } else if (nombre > numberToSolve) {
                    comentario = "El numero que buscas es mas pequeño";
                    textView1.setText("Numero Intents: " + contadorIntentos + " Numero a adivinar: " + numberToSolve);
                    Toast.makeText(getApplicationContext(), comentario, Toast.LENGTH_LONG).show();
                } else {
                    textView1.setText("Congratulations, has adivinado el numero oculto, era el: " + numberToSolve + " Tu puntacion ha sido de " + contadorIntentos + " puntos, si quieres empezar de nuevo sigue escribiendo numeros");
                    comentario = "Lo has 'clavao' papi";
                    Toast.makeText(getApplicationContext(), comentario, Toast.LENGTH_LONG).show();
                    dialogo();
                }
                usernumber.setText("");
            }
        });

        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Ranking2Activity.class);
                startActivity(intent);
            }
        });
exit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Ranking2Activity ra=new Ranking2Activity();
        // ra.salida();

       salida();
    }
});
            }
public void salida(){
        finish();
}


public void eliminarRepetidos(){

    Set<Jugador> hs = new HashSet<>(players);
    players.clear();
    players.addAll(hs);

}
    public void dialogo() {
        final String menssage = "Escriba su apodo para guardar su puntuacion, si no quiere pulse cancelar";

        final AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        builder.setMessage(menssage);

        builder.setTitle("Hello makina, tu puntuacion es de " + contadorIntentos);

        builder.setCancelable(false);
        final EditText input = new EditText(this);
        builder.setView(input);


        builder
                .setPositiveButton(
                        "Añadir",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                nickname = input.getText().toString();
                                int numm = contadorIntentos;
                                j1 = new Jugador(nickname, numm);
                               // photo= createImageFile();
                                //System.out.println(j1.getCurrentPhotoPath());
                                contadorIntentos = 0;
                                numberToSolve = new Random().nextInt(100);
                                if(j1.getBitmap()==null){
                                    dispatchTakePictureIntent();
                                }



                               if(file.exists()){
                                   recuperarJugadores();
                                }

                                players.add(j1);

                                escrituraJugadoresXML();


                            }
                        });

        builder
                .setNegativeButton(
                        "Cancelar",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                finish();
                            }
                        });


        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
   /* private File createImageFile(){
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  *//* prefix *//*
                    ".jpg",         *//* suffix *//*
                    storageDir      *//* directory *//*
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        j1.setCurrentPhotoPath(image.getAbsolutePath());
        System.out.println(j1.getCurrentPhotoPath());
        return image;
    }*/
      @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            j1.setBitmap(imageBitmap);
         /*   ByteArrayOutputStream baos = new ByteArrayOutputStream();
           imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
           j1.setArray( baos.toByteArray());
           j1.foto=baos.toByteArray().toString();
            System.out.println(j1.getArray());
            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(photo);
                j1.getBitmap().compress(Bitmap.CompressFormat.JPEG, 99, fOut);
                fOut.flush();
                fOut.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/

        }
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
             /*  Element eFotoPath = doc.createElement("fotoPath");
                eFotoPath.appendChild(doc.createTextNode(players.get(i).getCurrentPhotoPath()));
                eJugador.appendChild(eFotoPath);*/
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

    public  void recuperarJugadores() {
        players.clear();
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
                   /* j1.array=eElement.getElementsByTagName("fotoPath").item(0).getTextContent().getBytes("UTF-8");
                    j1.bitmap= BitmapFactory.decodeByteArray(j1.array, 0, j1.array.length);*/
                    players.add(j1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


