# BandRider
Wymagania

Zainstalowane:

Android Studio wraz z Android SDK(pobierz)
Java JDK w wersji 6 lub nowszej
Git

Jak zaimportować?

Zaczynamy od pobrania tego projektu do naszego katalogu roboczego. 
Aby to zrobić musimy w linii komend w Git wprowadzić polecenie:

git clone https://github.com/BandRider/BandRider.git

Po wykonaniu tej komendy otwieramy AndroidStudio i z menu wybieramy File -> Import Project...

Po z naszego katalogu roboczego katalog BandRider, klikamy OK i gotowe.

Nasz projekt zostanie zaimportowany, a my będziemy mogli rozpocząć kodowanie.

logowanie - trzeba zrobić wylogowywanie
 
Dodawanie/edycja instrumenów/sprzętu
Do zrobienia:
-dodanie większej liczby pól w formularzach dodawania
-pole dostępny/niedostępny np w formie radio buttona 
-zrobienie edycji/usuwania obiektu z bazy
alerty(okienka dialogowe) po naciśnięciu przycisku zapisz z informacją dla użytkownika i przeniesienie go do ekranu opcji

co dalej - zaplanować zarys ridera - jak ma wyglądać mechanizm działania (sugeruję uruchamianie go w tle jako usługa ale trzeba o tym pogadać)


szablon działania dla przykładowej listy
if selected.equals("Instrumenty") // albo może lepiej case
            {
              ładujemy widok dla listy instrumentów
              tworzymy adapter
              na liście głównej najlepiej jakby elementy wyglądały tak samo np.
              nazwa instrumentu właściciel ocena
              dla sprzętu
              nazwa instrumentu właściciel ocena
              a np różne dla item_selected
            }
użytkownik przed pokazaniem mu listy musi wybrać co go interesuje - widzę to tak:
1. wyświetlamy listę wyboru kategorii: np. taką instrumenty, sprzęt, akcesoria - można to zrobić jako okno dialogowe pojawiające się po naciśnięciu przycisku(może to być w tej samej aktywności co lista)
2.domyślnie lista jest niewidoczna - na początku widzimy tylko przycisk uruchamiający okno dialogowe z wyborem dopiero po wybraniu kategorii się pojawia
3. użytkownik wybrał kategorię instrumenty - w tym momencie z bazy parse z tabeli instrumenty wybieramy co tam jest i sobie ładujemy do listy oczywiście trzeba to ułożyć, żeby jakoś wyglądało
4. ustawiamy listę na widoczną
 a później to już z górki;-)
oczywiście powinno to być zrobione jeszcze w ten sposób, że jak wybierze instrumenty to później powinien wybrać kategorię instrumentu ale może aż tak nie róbmy ważne żeby pokazać, ze działa...
lista dla sprzętu na pewno będzie się różniła układem od instrumentów dlatego rozsądnie byłoby stworzyć dla każdego rodzaju listy layout i odpowiednio ładować wygląd zależnie od wyboru
