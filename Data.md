The Data we are working with are real Tweets that have been collected with our [Crawler](Crawler.md) during the period of the project. Each Tweet has been saved in a JSON file, containing information like the tweeted text or the date of the Tweet. We use this data for research [purpose](Purpose.md) only and the application uses the information in an anonymized way. For us, only the text and the date are of interest. <br>
Our internal data structure is a simple <i>Post</i> class containing these information. We set up our application in a modular way, so it's possible to extend our project with other sources of information (e.g. Facebook posts). All you need to do is to convert your raw data into that Post format.<br>
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