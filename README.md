## Project Title

Simple web crawler.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

## Prerequisites

*  IDE - Intellij
*  Java 8. This project will not compile with Java 7 or lower.
*  Maven for dependencies only.
*  commons-validator.jar. See pom.xml.
*  jsoup.jar. See pom.xml.

## Installing
* The easiest way to install and deploy is to open this project in Intellij IDEA.
* Create an Run Configuration. Provide main class as CrawlerMain. Supply one(1) program argument which is the domain name.
* Run the program.

## Future Work
* Add unit tests
* Use maven for build and deploy
* Solution works only for 1 domain. Expand to other domains
* Design changes. Example: Sitemap generator that can produce the sitemap in text, xml, html. Probably best to use a library
* Solution uses recursion. This can be slow
* Logging
* Better error handling. For example the resource URL=http://wiprodigital.com/cases/cisco-building-a-global-office returns a 404.
* Utility methods for handling URL's
* Project should handle http://www.wiprodigital.com and http://wiprodigital.com

## Authors

* **Vicky** - *Initial work*

