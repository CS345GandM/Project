  import java.io.FileInputStream;
  import java.util.ArrayList;
  import javax.xml.parsers.DocumentBuilder;
  import javax.xml.parsers.DocumentBuilderFactory;
  import org.w3c.dom.Document;
  import org.w3c.dom.Element;
  import org.w3c.dom.Node;
  import org.w3c.dom.NodeList;
  import java.util.*;
  import java.lang.*;

  public class readFiles {
    //first method is trying to add the cards to an arraylist
  	public void cards() {

      //AT THE END WE WANT TO CARDS TO HOLD ALL OF ITS INFO
  		//ArrayList<Cards> cards = new ArrayList<Cards>();

      try {
  			FileInputStream cardFile = new FileInputStream("cards.xml");
  			try {

  				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
  				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

  				Document doc = dBuilder.parse(cardFile);
  				doc.getDocumentElement().normalize();

  				NodeList nList = doc.getElementsByTagName("card");

  				//parse the cards
  				for (int i = 0; i < nList.getLength(); i++) {
            //card attributes
            String cardName;
  					int cardBudget;
  					String cardLine; //scene's description
  					int cardNumber;

  					String[] roleList = new String[4];
  					int[] roleRank = new int[4];
  					String[] roleLine = new String[4];

  					Node nNode = nList.item(i);

  					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  						Element set = (Element) nNode;
  						Element temp = (Element) set.getElementsByTagName("scene").item(0);

  						NodeList roles = set.getElementsByTagName("part"); //'part' in the xml file is defined as a role in our design

  						cardName = set.getAttribute("name");
  						cardBudget = Integer.parseInt(set.getAttribute("budget"));
  						cardNumber = Integer.parseInt(temp.getAttribute("number"));
  						cardLine = set.getElementsByTagName("scene").item(0).getTextContent();

  						//roles on a card
  						for(int j = 0; j < roles.getLength(); j++) {
  							Element n = (Element) roles.item(j);

  							roleList[j] = n.getAttribute("name");
  							roleRank[j] = Integer.parseInt(n.getAttribute("level"));
  							roleLine[j] = n.getElementsByTagName("line").item(0).getTextContent();
  						}
              //this arraylist hold the roles on the cards
  				ArrayList<Role> rolesAList = new ArrayList<Role>();
  				Role r1;  //roles 1-3
          Role r2;
          Role r3;

  					r1 = new Role(roleList[0], roleRank[0], roleLine[0]);
  					rolesAList.add(r1); //adding role 1 on the card to the roles arraylist
  				if(roleList[1] != null) {
  					r2 = new Role(roleList[1], roleRank[1], roleLine[1]);
  					rolesAList.add(r2);
  				}
  				if(roleList[2] != null) {
  					r3 = new Role(roleList[2], roleRank[2], roleLine[2]);
  					rolesAList.add(r3);
  				}

  				Cards card = new Cards(cardName, cardBudget, cardNumber, rolesAList, cardLine);
  				allCards.add(card); //add these new card objects into the card arraylist(from beginning)
  				}
        }
  			} finally {
  				cardFile.close(); //close the cards file
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}

  	return cards;  //return the arraylist of cards now holding roles, player rank, etc
  	}


    //second method is trying to seperate the componenets of board
    public void board() {
      //AT THE END WE WANT THE BOARD TO CONTAIN ALL ITS CONTENTS
      ArrayList<Rooms> boardContents = new ArrayList<Rooms>();
  		try {
  			FileInputStream boardFile = new FileInputStream("board.xml");
  			try {

  				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
  				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

  				Document doc = dBuilder.parse(boardFile);
  				doc.getDocumentElement().normalize();

  				NodeList trailerList = doc.getElementsByTagName("trailer");
  				NodeList officeList = doc.getElementsByTagName("office");
  				NodeList setList = doc.getElementsByTagName("set");

  				//first get the trailer info
  				Node tNode = trailerList.item(0);
  				String[] trailerList = new String[3];
  				ArrayList<String> trailerArray = new ArrayList<String>(); //******************************Trailer array (strings)
  				String trailerName = "Trailer";

  				if (tNode.getNodeType() == Node.ELEMENT_NODE) {
  					Element trailer = (Element) tNode;
  					NodeList neighbors = trailer.getElementsByTagName("neighbor");

  					for (int j = 0; j < neighbors.getLength(); j++) {
  						Element n = (Element) neighbors.item(j);

  						trailerList[j] = n.getAttribute("name");
  						trailerArray.add(trailerList[j]);
  					}

  				Rooms trailerRoom = new Rooms(trailerName, trailerArray, 0, null);
  				boardContents.add(trailerRoom);

          }

  				//Casting Office info
  				Node oNode = officeList.item(0);
  				String[] officeList = new String[3];
  				String castingOfficeName = "Office";

  				if (oNode.getNodeType() == Node.ELEMENT_NODE) {
  					Element trailer = (Element) oNode;
  					NodeList neighbors = trailer.getElementsByTagName("neighbor");

            //this ArrayList holds the office strings
            ArrayList<String> officeArray = new ArrayList<String>(); //********************************** office array(strings)

  					for (int j = 0; j < neighbors.getLength(); j++) {
  						Element adjRooms = (Element) neighbors.item(j);

  						officeList[j] = adjRooms.getAttribute("name");
  						officeArray.add(officeList[j]);
  					}

  				Rooms officeRoom = new Rooms(castingOfficeName, officeArray, 0, null);
  				boardContents.add(officeRoom);
  				}

  				//set info
  				for (int i = 0; i < setList.getLength(); i++) {
  					String[] adjList = new String[4];
  					int tvalue = 0;
  					String[] roleList = new String[4];
  					int[] roleRank = new int[4];
  					String[] roleLine = new String[4];

  					ArrayList<String> rArray = new ArrayList<String>();//*************************************************roles array
  					ArrayList<Role> rolesArray = new ArrayList<Role>();

  					Node nNode = setList.item(i);

  					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
  						Element set = (Element) nNode;
  						NodeList neighbors = set.getElementsByTagName("neighbor");
  						NodeList takes = set.getElementsByTagName("take");
  						NodeList rolesItems = set.getElementsByTagName("part");

  						//adjacent or neighbors
  						for (int j = 0; j < neighbors.getLength(); j++) {
  							Element n = (Element) neighbors.item(j);

  							setList[j] = n.getAttribute("name");
  							rArray.add(setList[j]);
  						}

  						//next is the takes info
  						tvalue = takes.getLength();

  						//the roles
  						for(int j = 0; j < rolesItems.getLength(); j++) {
  							Element n = (Element) rolesItems.item(j);

  							roleList[j] = n.getAttribute("name");
  							roleRank[j] = Integer.parseInt(n.getAttribute("level"));
  							roleLine[j] = n.getElementsByTagName("line").item(0).getTextContent();

  							Role role = new Role(roleList[j], roleRank[j], roleLine[j]);
  							rolesArray.add(role);
  						}

  						Rooms roleRoom = new Rooms(set.getAttribute("name"), roleList, tvalue, rolesArray);
  						allRooms.add(roleRoom);
              System.out.println(roleRoom); //*****************

  					}
  				}

  			} finally {
  				boardFile.close();
  			}

  		} catch (Exception e) {
  			e.printStackTrace();
  		}

  	return boardContents;
  	}
  }
