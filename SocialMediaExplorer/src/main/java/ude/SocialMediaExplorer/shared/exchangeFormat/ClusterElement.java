package ude.SocialMediaExplorer.shared.exchangeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ude.SocialMediaExplorer.shared.IdGenerator;

public class ClusterElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2555484716641553292L;

	/////////////////////////////////

	private int id;

	private String name;
	private Sentiment sentiment;

//	private ArrayList<String> posts;
	private HashMap<String, Double> posts;

	private List<ClusterElement> subcluster;
	private boolean isRoot;
	private boolean isLeaf;
	private String parent;
	private double size = 0D;

	/////////////////////////////////

	public ClusterElement( String name, Sentiment sentiment, List<ClusterElement> subCluster ) {

		this.sentiment = sentiment;

		if ( subCluster == null ) {
			isLeaf = true;
		}
		else
			this.subcluster = subCluster;

		if ( name == null ) {
			isRoot = true;
		}
		else
			this.name = name;
				
		this.setId( IdGenerator.getId() );

	}

	/////////////////////////////////

	public ClusterElement() {

	}

	/////////////////////////////////

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment( Sentiment sentiment ) {
		this.sentiment = sentiment;
	}

	public List<ClusterElement> getSubcluster() {
		return subcluster;
	}

	public void setSubcluster( List<ClusterElement> subcluster ) {
		this.subcluster = subcluster;
		this.isLeaf = false;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot( boolean isRoot ) {
		this.isRoot = isRoot;
		this.isLeaf = false;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf( boolean isLeaf ) {
		this.isLeaf = isLeaf;
		this.isRoot = false;
	}

	public String getParent() {
		return parent;
	}

	public void setParent( String parent ) {
		this.parent = parent;
	}

	public double getSize() {
		return size;
	}

	public void setSize( double size ) {
		this.size = size;
	}

	public HashMap<String, Double> getPosts() {
		return posts;
	}

	public void setPosts( HashMap<String, Double> posts ) {
		this.posts = posts;
		this.size = posts.size();
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}
	
	///////////////////////////////////

	public static ClusterElement testCE() {

		Sentiment s1 = new Sentiment();
		s1.setNegative( 0.5 );
		s1.setPositive( 0 );
		Sentiment s2 = new Sentiment();
		s2.setNegative( 0 );
		s2.setPositive( 0.5 );
		Sentiment s3 = new Sentiment();
		s3.setNegative( 0.5 );
		s3.setPositive( 0.55 );


		ClusterElement ce1 = new ClusterElement();
		ce1.setRoot( false );
		ce1.setLeaf( true );
		ce1.setName( "cluster 1" );
		ce1.setSize( 0.3 );
		ce1.setSentiment( s1 );

		ClusterElement ce11 = new ClusterElement();
		ce11.setRoot( false );
		ce11.setLeaf( true );
		ce11.setName( "cluster 1.1" );
		ce11.setSize( 0.1 );
		ce11.setSentiment( s2 );

		ClusterElement ce12 = new ClusterElement();
		ce12.setRoot( false );
		ce12.setLeaf( true );
		ce12.setName( "cluster 1.2" );
		ce12.setSize( 0.4 );
		ce12.setSentiment( s3 );

		ArrayList<ClusterElement> l1 = new ArrayList<ClusterElement>();
		l1.add( ce11 );
		l1.add( ce12 );
		ce1.setSubcluster( l1 );


		ClusterElement ce2 = new ClusterElement();
		ce2.setRoot( false );
		ce2.setLeaf( true );
		ce2.setName( "cluster 2" );
		ce2.setSize( 0.4 );
		ce2.setSentiment( s2 );

		ClusterElement ce21 = new ClusterElement();
		ce21.setRoot( false );
		ce21.setLeaf( true );
		ce21.setName( "cluster 2.1" );
		ce21.setSize( 0.9 );
		ce21.setSentiment( s2 );

		ClusterElement ce22 = new ClusterElement();
		ce22.setRoot( false );
		ce22.setLeaf( true );
		ce22.setName( "cluster 2.2" );
		ce22.setSize( 0.1 );
		ce22.setSentiment( s3 );

		ArrayList<ClusterElement> l2 = new ArrayList<ClusterElement>();
		l2.add( ce21 );
		l2.add( ce22 );
		ce2.setSubcluster( l2 );


		ClusterElement ce3 = new ClusterElement();
		ce3.setRoot( false );
		ce3.setLeaf( true );
		ce3.setName( "cluster 3" );
		ce3.setSize( 0.9 );
		ce3.setSentiment( s3 );

		ClusterElement ce31 = new ClusterElement();
		ce31.setRoot( false );
		ce31.setLeaf( true );
		ce31.setName( "cluster 3.1" );
		ce31.setSize( 0.3 );
		ce31.setSentiment( s2 );

		ClusterElement ce32 = new ClusterElement();
		ce32.setRoot( false );
		ce32.setLeaf( true );
		ce32.setName( "cluster 3.2" );
		ce32.setSize( 0.3 );
		ce32.setSentiment( s1 );

		ArrayList<ClusterElement> l3 = new ArrayList<ClusterElement>();
		l3.add( ce31 );
		l3.add( ce32 );
		ce3.setSubcluster( l3 );


		ClusterElement ce = new ClusterElement();
		ce.setRoot( true );
		ce.setLeaf( false );
		ce.setName( "Root" );
		ce.setSize( 0.2 );
		ce.setSentiment( s3 );

		ArrayList<ClusterElement> l = new ArrayList<ClusterElement>();
		l.add( ce1 );
		l.add( ce2 );
		l.add( ce3 );
		ce.setSubcluster( l );


		return ce;
	}

	


}
