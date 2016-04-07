log4slack-java (log4j & slack)
=======================================

![Coaxys logo](http://www.coaxys.com/public/images/coaxys-logo.svg)

Copyright (c) 2015 Coaxys <contact@coaxys.com>.
Licensed under the [Apache 2 licence](http://www.apache.org/licenses/LICENSE-2.0).

[Télécharger/Download](https://github.com/coaxys/play-routeChecker/blob/dev/target/RouteChecker-1.2.one-jar.jar?raw=true)

Description
-----------

Simple logger allows the sending of error logs into a slack's channel.

---

Un logger simple permettant l'envoi de log d'erreur sur un channel Slack. 

Utilisation
-----------

In the log4j.properties file add `Slack` to the property `log4j.rootLogger`

And theses lines into the file :
```
log4j.appender.Slack=com.coaxys.log4slack.SlackAppender
log4j.appender.Slack.url=XXX
```
Replace XXX by the webhook Slack URL.

---

Dans le fichier log4j.properties ajouter `Slack` à la propriété `log4j.rootLogger`

Et les lignes suivantes dans le fichier :
```
log4j.appender.Slack=com.coaxys.log4slack.SlackAppender
log4j.appender.Slack.url=XXX
```
Remplacer les XXX par l'URL webhook de slack.

### License
This software is licensed under the Apache 2 license, quoted below.

Copyright 2015 Coaxys (http://www.coaxys.com).

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
