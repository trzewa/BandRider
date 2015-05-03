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


zrobione jest pobieranie danych z bazy o sprzęcie i instrumentach - pozostało zrobić adaptery dla list(można by skorzystać z kursów walut, które robiliśmy w tamtym semestrze)
okno dialogowe z wyborem kategorii zrobiłem jako odzielna aktywność dlatego, że gdy było ono w PlanRider na początku w oncreate od razu było w momencie uruchomienia aktywności inicjowane domyślnym wyborem czyli instrumentami
i nie można było zmienić już wartości zmiennej, która przechowywała numer kategorii
nie znalazłem na to innego wygodnego sposobu z tego powodu musiałem też zmienić typ funkcji z tym dialogiem z powrotem na void
po wyborze kategorii przekazywana jest informacja o kategorii do PlanRider i jest ta aktywnosć uruchamiana
po kliknięciu ok przez chwilkę widać biały ekran - dlatego, że nie ma w tej aktywności(showCategoryactivity) layoutu można by zrobić jakieś tło z obrazka żeby nie widać było tego białego ekranu tylko jakaś grafika przez chwilę albo np kręcące się kółeczko
 wygląd itemu na liście mógłby być podobny do tego z blablacar np.
 u góry textview z nazwą a pod spodem dwa textview obok siebie: właściciel i ocena czyli
							
						1	nazwa  instrumentu
							ocena  właścieciel
							
						2	nazwa  instrumentu
							ocena  właścieciel
							
						3	nazwa  instrumentu
							ocena  właścieciel
							
							itd...
po kliknięciu w item decyzja czy chce instrument wypożyczyć ewentualny kontakt z właścicielem
