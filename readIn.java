
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class readIn{
  public readIn(){

  }
  public void callRead(){

    try{
      //getting files
      File board = new File("board.xml");
      File cards = new File("cards.xml");

      //creating document builder
      DocumentBuilderFactory boardfactory = DocumentBuilderFactory.newInstance();
      DocumentBuilderFactory cardsfactory = DocumentBuilderFactory.newInstance();

      DocumentBuilder boardbuilder = boardfactory.newDocumentBuilder();
      DocumentBuilder cardsbuilder = cardsfactory.newDocumentBuilder();

      Document boardDoc = boardbuilder.parse(board);
      Document cardsDoc = cardsbuilder.parse(cards);

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

    }catch (Exception e){
        e.printStackTrace();
    }
  }

  private void readInSets(NodeList sets){
    //loop through all of the sets
    for(int x = 0; x < sets.getLength(); x++){
      Node curr = sets.item(x);
      if(curr.getNodeType() == Node.ELEMENT_NODE){
        Element e = (Element) curr;
        String name = e.getAttribute("name");//name
        NodeList takes = e.getElementsByTagName("takes");
        int numTakes = takes.getLength();//number of takes

        //Setting ROOM --> associations
        NodeList neighbors = e.getElementsByTagName("neighbor");
        String[] neighborNames = new String[4];
        Arrays.fill(neighborNames, null);
        for(int i = 0; i < neighbors.getLength(); i++){
          Node nbr = neighbors.item(i);
          if(nbr.getNodeType() == nbr.ELEMENT_NODE){
            Element nbrname = (Element) nbr;
            neighborNames[i] = nbrname.getAttribute("name");
          }
        }
        String one = neighborNames[0];
        String two = neighborNames[1];
        String three = neighborNames[2];
        String four = neighborNames[3];
        Rooms add = new Rooms(one, two, three, four, name);

        //Making Roles Array
        NodeList roles = e.getElementsByTagName("part");
        Role[] rolesArray = new Role[4];
        Arrays.fill(rolesArray, null);
        for(int i = 0; i < roles.getLength(); i++){
          Node role = roles.item(i);
          if(role.getNodeType() == role.ELEMENT_NODE){
            Element roleName = (Element) role;
            String n = roleName.getAttribute("name");
            String level = roleName.getAttribute("level");
            int r = Integer.parseInt(level);
            String l = roleName.getElementsByTagName("line").item(0).getTextContent();
            Role newRole = new Role(n, r, l);
            rolesArray[i] = newRole;
          }
        }
        //Making set
        Set adding = new Set(name, numTakes, rolesArray);
      }
    }
  }

  private void readInTrailer(NodeList trailer){
    for(int x = 0; x < trailer.getLength(); x++){
      Node curr = trailer.item(x);
      if(curr.getNodeType() == Node.ELEMENT_NODE){
        Element e = (Element) curr;
        String name = "Trailer";

        //Setting ROOM --> associations
        NodeList neighbors = e.getElementsByTagName("neighbor");
        String[] neighborNames = new String[4];
        Arrays.fill(neighborNames, null);
        for(int i = 0; i < neighbors.getLength(); i++){
          Node nbr = neighbors.item(i);
          if(nbr.getNodeType() == nbr.ELEMENT_NODE){
            Element nbrname = (Element) nbr;
            neighborNames[i] = nbrname.getAttribute("name");
          }
        }
        String one = neighborNames[0];
        String two = neighborNames[1];
        String three = neighborNames[2];
        String four = neighborNames[3];
        Rooms add = new Rooms(one, two, three, four, name);

        trailerRoom adding = new trailerRoom(name);
      }
    }
  }

  private void readInOffice(NodeList office){
    for(int x = 0; x < office.getLength(); x++){
      Node curr = office.item(x);
      if(curr.getNodeType() == Node.ELEMENT_NODE){
        Element e = (Element) curr;
        String name = "Office";

        //Setting ROOM --> associations
        NodeList neighbors = e.getElementsByTagName("neighbor");
        String[] neighborNames = new String[4];
        Arrays.fill(neighborNames, null);
        for(int i = 0; i < neighbors.getLength(); i++){
          Node nbr = neighbors.item(i);
          if(nbr.getNodeType() == nbr.ELEMENT_NODE){
            Element nbrname = (Element) nbr;
            neighborNames[i] = nbrname.getAttribute("name");
          }
        }
        String one = neighborNames[0];
        String two = neighborNames[1];
        String three = neighborNames[2];
        String four = neighborNames[3];
        Rooms add = new Rooms(one, two, three, four, name);

        castingOffice adding = new castingOffice(name);
      }
    }
  }

  private void readInCards(NodeList cards){
    //loop through all of the sets
    for(int x = 0; x < cards.getLength(); x++){
      Node curr = cards.item(x);
      if(curr.getNodeType() == Node.ELEMENT_NODE){
        Element e = (Element) curr;
        String name = e.getAttribute("name");//name
        String budg = e.getAttribute("budget");
        int budget = Integer.parseInt(budg);

        //getting scene number and description
        NodeList scenes = e.getElementsByTagName("scene");
        String description = null;
        int sceneNumber = 0;
        for(int i = 0; i < scenes.getLength(); i++){
          Node scene = scenes.item(i);
          if(scene.getNodeType() == scene.ELEMENT_NODE){
            Element s = (Element) scene;
            description = s.getTextContent();
            String num = s.getAttribute("number");
            sceneNumber = Integer.parseInt(num);
          }
        }

        //Making Roles Array
        NodeList roles = e.getElementsByTagName("part");
        Role[] rolesArray = new Role[4];
        Arrays.fill(rolesArray, null);
        for(int i = 0; i < roles.getLength(); i++){
          Node role = roles.item(i);
          if(role.getNodeType() == role.ELEMENT_NODE){
            Element roleName = (Element) role;
            String n = roleName.getAttribute("name");
            String level = roleName.getAttribute("level");
            int r = Integer.parseInt(level);
            String l = roleName.getElementsByTagName("line").item(0).getTextContent();
            Role newRole = new Role(n, r, l);
            rolesArray[i] = newRole;
          }
        }
        //Making set
        Cards adding = new Cards(name, description, budget,  sceneNumber, rolesArray);
      }
    }
  }

}
