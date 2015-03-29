# UML #

This UML diagram shows the classes, but not all references (zoom in or download [here](http://www-stud.uni-due.de/~sfhedetj/sme/pics/SocialMediaExplorer.png)):

![http://www-stud.uni-due.de/~sfhedetj/sme/pics/SocialMediaExplorer.png](http://www-stud.uni-due.de/~sfhedetj/sme/pics/SocialMediaExplorer.png)

# JavaDocs #

can be found in svn (check out) or  [here](http://www-stud.uni-due.de/~sfhedetj/sme/sme_docs/)

# Program Main Flow #

An extension to [Overview - Process](https://code.google.com/p/twitterresearchproject/wiki/Overview)

The flow from a tweet to its visualization:

  1. The PostCollector (TwitterCrawler via [Twitter4J](http://twitter4j.org/en/index.html)) grabs tweets in **JSON** format in the rawdata folder with a timestamp via TimeStamp class (you can change that with the project\_config.xml): **files/rawdata/tweets/hashtag/yyyyMMddHHmmss\_hashtag.json**
  1. The JSON is read out by a FileReader and a PostConverter converts it into the demanded **Post** format to reduce overhead and ensure independence from api specific formats
  1. The **PostProvider** hands the Post over to the Analysis and its result is saved in the CAS (you can change that by project\_config.xml -> serializedCases): **files/hashtag/yyyyMMddHHmmss/yyyyMMddHHmmss\_i.ser**
  1. The serialized results become clustered into **ClusterElements** with a Sentiment and these are saved in: **files/serializedClusterElements/hashtag/yyyyMMdd.ser**
  1. The ClusterElements are called via the RMI interface but they have to be merged into a **JSON format** ([https://code.google.com/p/twitterresearchproject/wiki/Visualization details](.md)), which turned out to be very much faster on server-side with the org.JSON lib instead of the gwt.client.json lib
  1. The JSON string is passed to the Visualization and parsed into JavaScript objects so the outer js libs (Google Visualization API) can work with them
<br />
<br />
## read more ##

  * [Purpose of the project](Purpose.md)
  * [Data](Data.md)
  * [Architecture](Architecture.md)
  * [Setup](Setup.md)
  * [Crawler](Crawler.md)
  * [Analysis](Analysis.md)
  * [Visualization](Visualization.md)
  * [LimitationsOutlook](LimitationsOutlook.md)