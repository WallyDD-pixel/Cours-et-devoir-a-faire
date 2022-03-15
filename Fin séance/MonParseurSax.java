import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MonParseurSax extends DefaultHandler {
    public MonParseurSax(String nomFichierLivreXML) {
        this.nomFichierLivreXML = nomFichierLivreXML;
        listeLivres = new ArrayList<>() ;
        parseDocument();
        afficheLivres();
    }

    List<Livre> listeLivres ;
    String nomFichierLivreXML ;
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd") ;
    Livre unLivre ;
    String valeurLue ;

    private void afficheLivres(){
        for (Livre leLivre : listeLivres )
            System.out.println(leLivre);
    }

    private void parseDocument() {
        // parse
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            File leFichier = new File(nomFichierLivreXML) ;
            parser.parse(new InputSource(new FileInputStream(leFichier)), this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("livre")) {
            listeLivres.add(unLivre);
        }
        if (qName.equalsIgnoreCase("isbn")) {
            unLivre.setIsbn(valeurLue);
        }
        if (qName.equalsIgnoreCase("titre")) {
            unLivre.setTitre(valeurLue);
        }
        if(qName.equalsIgnoreCase("auteur")){
            unLivre.getAuteurs().add(valeurLue);
        }
        if(qName.equalsIgnoreCase("prix")){
            unLivre.setPrix(Integer.parseInt(valeurLue));
        }
        if(qName.equalsIgnoreCase("dateEnregistrement")){
            try {
                unLivre.setEnregistrement(sdf.parse(valeurLue));
            } catch (ParseException e) {
                System.out.println("date parsing error");
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        valeurLue = new String(ch, start, length) ;

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("livre")){
            unLivre = new Livre() ;
            unLivre.setId(attributes.getValue("id"));
            unLivre.setLangue(attributes.getValue("langage"));
        }

        if (qName.equalsIgnoreCase("editeur")) {
            unLivre.setPaysEdition(attributes.getValue("pays"));
        }


    }
    public static void main(String[] args) {
        new MonParseurSax("catalogue.xml");
    }
}
