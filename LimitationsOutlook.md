# Limitations & Outlook #

To gain insight in the huge amount of unstructured social media data this project uses a set of NLP-steps that were selected by theoretical (what should work best?) and practical issues (like license, availability in German, effort of implementation). In addition the unconventional use of language in the environment of social media causes some troubles to the adaption of best-practice approaches. This causes the fact, that the performance of the different steps could be improved further.

# Sentiment #
Our sentiment approach doesn’t take any context of words into account and relies just on the association of the words in the WebT1-corpus. It misses any syntactic dependencies in sentences that could effect the sentiment. Therefore it isn’t even capable of realizing that a “not” in front of an adjective could turn around the sentiment of the meaning. In the future there should be a mechanism that is capable of such dependencies by using syntactical parsing. Because Tweets often don’t use “correct” syntax, simple handcrafted rules (like “turn sentiment around after not”) should be added as fallback. In addition sentiment is highly related to the domain. To face this it is maybe worthy to compute the sentiment lexicon on a twitter-corpus (if a big one should be available). <br>
A further improvement could be an analysis that computes sentiment for aspects of the statements. Our approach sums up the sentiment of the whole Tweet and only finds the sentiment of the whole Tweet. A better approach would be to examine different aspects of the Tweet (e.g. the extracted key phrases) and assign sentiment to them. <br>
In addition our simple lookup-approach uses only a sentiment lexicon that was trained on German (according to our use case of German TV shows). Though the language in social media is often English or a mix of languages it refers to a German topic and is produced by German users. This lack of an English sentiment lexicon results partly in missidentification of the sentiment. E.g. in the sentence "wir fanden das verdammt nice" (that means "we found it damn nice") would be recognized as a negative statement because the word "nice" is out of vocabulary in the German lexicon.<br>
<br>
<h1>Key Phrase Extraction</h1>
Our approach is co-occurrence-graph based. Main advantage of this kind of approach is that they can take big context into account. Because Tweets consists of maximal 140 characters, the small context prevents the context advantage from generating real value. So it often brings nearly the same results as a simple noun selection would bring. <br>
An alternative that would work for small contexts would be a noun phrase selection by using a chunker. Unfortunately we didn’t find a chunker that is capable of German, under a free license and easy to integrate into our set-up. In the future this should be tackled.<br>
<br>
<h1>Spelling Correction</h1>
Our approach finds similar words based on Levenshtein-distance and replaces them with the most common form. Since in the context of Twitter there are often new and unusual words, the approach has the advantage that it does not recognize such words as incorrect. On the other hand it has the disadvantage that no knowledge is contained on spelling. This means that when a word is less often spelt then correct (according to conventions that may change), the wrong form will be assigned. Furthermore the approach can't distinguish between different words that are spelled very similar (E.g. water and walter (name). So in the future the approach could be evolved by using knowledge about spelling and common typos (e.g. by using noisy channel).<br>
<br>
<h1>Scope</h1>

At the moment we focus only on Tweets. To gain deeper insights in public, in the web expressed meanings it would be useful to examine other knowledge sources (e.g. blogs, facebook), too. Our approach is designed in a way that it can be enhanced for other sources (just have a look at our data interface <i>PostList</i>). Though it needs further concepts on how to crawl, preprocess and weight them in the analysis. In the cluster-process at the end of the analysis every post is weighted the same. If you try to include whole blogs you may think about adjust these weights.<br>
<br>
<h1>Semantic Information</h1>
Useful would be two applications of knowledge. For a disambiguation of words with the same surface forms (E.g. bank (money) versus bank (sitting)). Such a disambiguation could be useful to differentiate the analysis further. On the other hand for recognizing actually same concepts with different surface forms. For example, refer the words X and Y on a similar concept. This is not recognized by our approach, but would be very helpful for a good analysis.<br>
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