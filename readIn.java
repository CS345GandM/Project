
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class readIn{
  public static void main(String[] args){

    try{
      //getting files
      File board = new File("board.xml");
      File cards = new File("cards.xml");

      //creating document builder
      DocumentBuilderFactory boardfactory = DocumentBuilderFactory.newInstance();
      DocumentBuilderFactory cardsfactory = DocumentBuilderFactory.newInstance();

      DocumentBuilder boardbuilder = boardfactory.newDocumentBuilder();
      DocumentBuilder cardsbuilder = cardsfactory.newDocumentBuilder();

      Document boardDoc = builder.parse(board);
      Document cardsDoc = builder.parse(cards);

      boardDoc.getDocumentElement().normalize();
      cardsDoc.getDocumentElement().normalize();

      //makes node lists of sets, tailer, office, and cards
      NodeList boardList = boardDoc.getElementsByTagName("set");
      readInSets(boardList);
      NodeList trailerList = boardDoc.getElementsByTagName("trailer");
      readInTrailer(trailerList);
      NodeList officeList = boardDoc.getElementsByTagName("office");
      readInOffice(officeList);
      NodeList cardsList = cardsDoc.getElementsByTagName("card");
      readInCards(cardsList);
////////////////////////////////////////////////////////////////////////////////////
      StringBuilder xmlStringBuilder = new StringBuilder();
      xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>");
      ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));


      Element root = document.getDocumentElement();

      //returns specific attributes
      getAttribute("someAttribute");
      //returns a Man (table) of names/values
      getAttributes();

      //returns a list of subelements of specific name
      getElementsByTagName("subelementName");
      //returns a list of all child nodes
      getChildNodes();
//////////////////////////////////////////////////////////////////////////////////////////
    }catch (Exception e){
        e.printStackTrace();
    }
  }

  private void readInSets(NodeList sets){

  }

  private void readInTrailer(NodeList trailer){

  }

  private void readInOffice(NodeList office){

  }

  private void readInCards(NodeList cards){

  }
}
