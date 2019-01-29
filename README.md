# HTTP-CRAWLER

## Περιγραφή

Το HttpCrawler είναι ένα utility το οποιο μπορεί να χρησιμοποιηθεί για την εύρεση HTTP links σε webpages. Στην τωρινή version υποστηρίζεται ο εντοπισμός `HTML links`, `imports`, `image links` καθώς και `JavaScript links`.

Το πρόγραμμα διατίθεται σε μορφή JAR file το οποιο εκτελείται σε περιβάλλον κονσόλας. Για την βασική λειτουργία τής εύρεσης HTTP links χρησιμοποιεί την βιβλιοθήκη [Jsoup HTML Parser](https://jsoup.org/).

---

## Οδηγίες Χρήσης

Πλοηγηθείτε με την κονσόλα στο φάκελο με την τοποθεσία του JAR file και εκτελέστε την παρακάτω εντολή:

```shell
$ java -jar HttpCrawler.jar
```
Όταν ξεκινήσει το πρόγραμμα, θα σας προτρέψει να επισυνάψετε το path ενός valid sitemap.xml αρχείου με URL άρθρων. Προσθέστε το path και πατήστε το κουμπί `ENTER`.

π.χ. (path απο windows OS)
```shell
Enter the sitemap path you wish to crawl (press q to exit) : C:\Your\Path\sitemaps\sitemap.xml
```
Στο επόμενο βήμα θα σας ζητηθεί να επιλέξετε τον αριθμό των άρθρων που θέλετε να τσεκάρετε για HTTP links. Σε περίπτωση που δεν βάλετε κάποιο αριθμό και πατήσετε ENTER θα τσεκαριστούν όλα τα άρθρα του sitemap.

π.χ. (επιλέγουμε 5 άρθρα)
```shell
How many articles should be crawled ? (press q to exit) : 5
```
Εφόσον ολοκληρωθεί και η συγκεκριμένη διαδικασία, το utility ξεκινάει τον έλεγχο των URLs του sitemap και ταυτόχρονα εμφανίζει την πρόοδο στην κονσόλα. 

π.χ.
```shell
START CRAWLER
1 STARTING URL PARSING FOR http://www.example.com/article-title.5169033.html
2 STARTING URL PARSING FOR http://www.example.com/article-title.5169896.html
3 STARTING URL PARSING FOR http://www.example.com/article-title.5169897.html
4 STARTING URL PARSING FOR http://www.example.com/article-title.5169037.html
5 STARTING URL PARSING FOR http://www.example.com/article-title.5220563.html
Total Articles: 5
input : C:\Your\Path\sitemaps\sitemap.xml
-----------
```
Με την ολοκλήρωση πατάμε `q` για να κλείσουμε το utility

---

## Διαχείριση αποτελεσμάτων

Τα αποτελέσματα από τον έλεγχο του utility αποθηκεύονται στο αρχείο links.CSV το οποιο βρίσκετε στο directory:

Path σε Windows OS:

```shell
C:\httpcrawler\links.csv
```

Path σε Linux / MacOS:
```shell
/httpcrawler/links.csv
```

Το αρχείο CSV έχει την παρακάτω μορφή:
```shell
Type,URL,Article Id
Links,http://www.sportkit.gr/Ta-pio-fthhna-fouter-einai-edo_a-16854.aspx,5272997
Links,http://longreads.oneman.gr/tequila-story,5272997
Links,http://flix.gr/news/preview-january-31-2019.html,5272997
Js,http://m.blachsh.gr/static/images/sm/Logo.png"", ""width"": 149, ""height"": 20 } },,5272997
Media,http://www.youtube.com/embed/p-Lo_rdnNKI,5272802
Media,http://www.youtube.com/embed/Jzr9Jp6Z5W4,5272802
Media,http://www.youtube.com/embed/CuqV-mw0xdU,5272802
Media,http://www.youtube.com/embed/EG_VVoXWKtE,5272802
```





