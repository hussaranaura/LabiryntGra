# Uczestnicy:
hussaranaura/somehussar - Bartosz Jóźwiak<br>
cyckimurzynki / Fiut Kutas - Mikołaj Gutowski<br>
MARIUSZMOCARZ4 - Oskar Śliziewski

# Jira: https://hussaranaura.atlassian.net/jira/software/projects/KAN/boards/1

# Dokumentacja https://hussaranaura.github.io/LabiryntGra/

# Kreator map!!!
Gra ma [własny kreator map!](https://hussaranaura.github.io/LabiryntGra/creator/). Kliknij na text obok aby go odwiedzić!

# Importowanie projektu!

## Eclipse:
1. `File > Import > Git (Smart Import) > Clone URI`
2. W polu URI wstaw `https://github.com/hussaranaura/LabiryntGra.git`
3. Ustaw protokół na HTTPS
4. `User`: twój login do Githuba
5. `Password`: [Token SSH do konta Github (zobacz jak)](https://github.com/settings/tokens)
6. ZAPISZ DANE LOGOWANIE
7. Po zaimportowaniu, kliknij prawym na projekt i na samym dole kliknij `Właściwości`/`Properties`
8. Po lewej, wybierz `Java Build Path`
9. Po środku, wybierz zakładkę `Source`
10. Po prawej `Add folder` i zaznacz folder `resources`
11. Możesz bezproblemu uruchomić aplikację!

## Intellij IDEA:
1. `Plik > Nowy > Z Systemu Kontroli Wersji`
2. Logowanie będzie oczywiste, ponieważ program prowadzi cię za ręke.
3. Kliknij prawym na folder `src` i na samym dole `Mark folder as > Source root`
4. To samo zrób z folderem `resources`, ale wybierz opcję `Resources root`
5. Uruchom aplikację.
