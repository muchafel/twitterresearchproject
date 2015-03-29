# KeyphraseExtraction #

In order to compress the Tweets further we extract words and phrases that describe the sense of each Tweet. For this reason we use a co-occurence-graph based approach to detect and select keyphrases. First we create a co-occurence graph by using a sliding window approach (size 4). Our initial graph contains one node for every word, then we add an edge between two words that co-occur inside the sliding window. The effect of this procedure on the german example sentence "In einer Anti-Spam Klage wird behauptet, dass Utah Anti-Spam Regelungen verletzt. Das Vorgehen von Utah bezüglich der Anti-Spam Gesetze könnte die Bahn für neue Regelungen mit Telekommunikationsanbietern ebnen." is shown below:

![http://www-stud.uni-due.de/~sfmiwoja/BilderFoPro/CoOcc.png](http://www-stud.uni-due.de/~sfmiwoja/BilderFoPro/CoOcc.png)

Then the most connected nodes are selected as keywords. Because there could be also keyphrases consisting of more than one word, each keyword is checked for adjacent nodes that have a high rank, too.
If such a compound of two adjacent words that are both highly connected is found, the compound is chosen as a keyphrase. <br>

In the example sentence the keyphrases "Utah Anti-Spam Regelungen, Bahn, Telekommunikationsanbietern, Anti-Spam Gesetze" are chosen.<br>
<br />
<br />
<h2>read more</h2>

<ul><li><a href='Purpose.md'>Purpose of the project</a>
</li><li><a href='Data.md'>Data</a>
</li><li><a href='Architecture.md'>Architecture</a>
</li><li><a href='Setup.md'>Setup</a>
</li><li><a href='Crawler.md'>Crawler</a>
</li><li><a href='Analysis.md'>Analysis</a>
</li><li><a href='Visualization.md'>Visualization</a>
</li><li><a href='LimitationsOutlook.md'>LimitationsOutlook</a>