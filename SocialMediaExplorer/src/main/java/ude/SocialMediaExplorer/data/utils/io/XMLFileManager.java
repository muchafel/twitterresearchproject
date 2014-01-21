package ude.SocialMediaExplorer.data.utils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A class to read / write xml
 * 
 * @author henrikdetjen
 * 
 */
public class XMLFileManager {

	/**
	 * read in a Document the xml way
	 * 
	 * @param filepath
	 *            {@link String}
	 * @return {@link Document}
	 */
	private static Document getDoc( String filepath ) {
		Document doc = null;

		try {

			File fXmlFile = new File( filepath );
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse( fXmlFile );
			doc.getDocumentElement().normalize();

		}
		catch ( Exception e ) {
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * get the values of the nodes for a tag
	 * 
	 * @param filepath
	 *            {@link String}
	 * @param tagname
	 *            {@link String}
	 * @return - the found values as {@link String}
	 */
	public static ArrayList<String> getNodeValuesByTag( String filepath, String tagname ) {


		ArrayList<String> result = new ArrayList<String>();

		Document doc = getDoc( filepath );

		NodeList nodeList = doc.getElementsByTagName( tagname );


		for ( int i = 0; i < nodeList.getLength(); i++ ) {
			result.add( nodeList.item( i ).getTextContent() );
		}

		return result;
	}

	/**
	 * a method to exchange a single node value
	 * 
	 * @param filepath
	 *            {@link String} - the reltive path to the xml file
	 * @param tagname
	 *            {@link String} - the desired tag
	 * @param oldValue
	 *            {@link String} - (OPTIONAL) the old value (needed to find the right node)
	 * @param newValue
	 *            {@link String} - the new value (overwrites the old one);
	 * @throws Exception
	 */
	public static void updateNodeValue( String filepath, String tagname, String oldValue, String newValue )
			throws Exception {

		try {

			Document doc = getDoc( filepath );
			NodeList nodeList = doc.getElementsByTagName( tagname );

			for ( int i = 0; i < nodeList.getLength(); i++ ) {
				if ( nodeList.item( i ).getTextContent() == oldValue && oldValue != null ) {
					nodeList.item( i ).setTextContent( newValue );
					break;
				}
				else {
					if ( oldValue == null ) {
						nodeList.item( i ).setTextContent( newValue );
					}
				}
			}

			writeDoc( filepath, doc );

		}
		catch ( Exception e ) {
			throw e;
		}

	}

	/**
	 * appends a single value with specifed tag to a parent node (can be null -
	 * then firstchild is parent)
	 * 
	 * @param filepath
	 *            {@link String} - the reltive path to the xml file
	 * @param tagname
	 *            {@link String} - the desired tag
	 * @param parentNodesTagname
	 *            {@link String} - parent to append to (OPTIONAL)
	 * @param value
	 *            {@link String} - the value
	 * @throws Exception
	 */
	public static void appendValue( String filepath, String tagname, String parentNodesTagname, String value )
			throws Exception {

		try {

			Document doc = getDoc( filepath );
			Node parent = null;
			if ( parentNodesTagname != null ) {
				parent = doc.getElementsByTagName( parentNodesTagname ).item( 0 );
			}
			else {
				parent = doc.getFirstChild();
			}

			Element e = doc.createElement( tagname );
			e.setTextContent( value );
			parent.appendChild( e );

			writeDoc( filepath, doc );
		}
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * remove a certain tag (removes all nodes with given tag - specify value to
	 * remove a single node)
	 * 
	 * @param filepath
	 *            {@link String} - the reltive path to the xml file
	 * @param tagname
	 *            {@link String} - the desired tag
	 * @param value
	 *            {@link String} - the value (OPTIONAL)
	 * @throws Exception
	 */
	public static void removeNode( String filepath, String tagname, String value ) throws Exception {

		try {
			Document doc = getDoc( filepath );
			NodeList list = doc.getElementsByTagName( tagname );

			// node to delete, its parent
			Vector<Node> toDelete = new Vector<Node>();
			
			if ( value != null ) {
				for ( int i = 0; i < list.getLength(); i++ ) {
					if ( list.item( i ).getTextContent() == value ) {
						toDelete.add( list.item( i ) );
					}
				}
			}
			else {
				for ( int i = 0; i < list.getLength(); i++ ) {
					toDelete.add( list.item( i ) );
				}
			}

			for (Node node_to_delete : toDelete ) {
				Node parent = node_to_delete.getParentNode();
				parent.removeChild( node_to_delete );
			}
			
			writeDoc( filepath, doc );

		}
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * write Document the xml way
	 * 
	 * @param filepath
	 *            {@link String}
	 * @param doc
	 *            {@link Document}
	 * @throws Exception
	 */
	private static void writeDoc( String filepath, Document doc ) throws Exception {

		try {

			DOMSource domSource = new DOMSource( doc );
			File fileOutput = new File( filepath );
			StreamResult streamResult = new StreamResult( fileOutput );
			TransformerFactory tf = TransformerFactory.newInstance();

			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
			serializer.setOutputProperty( OutputKeys.INDENT, "yes" );
			serializer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "no" );
			serializer.setOutputProperty( OutputKeys.METHOD, "xml" );
			serializer.transform( domSource, streamResult );

		}
		catch ( Exception e ) {
			throw e;
		}

	}

}
