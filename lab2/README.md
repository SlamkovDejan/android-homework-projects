## Homework no.2

Потребно е да изработите апликација која ќе oвозможи додавање, прелистување и приказ на детали за записи за филмови кои ќе се добиваат со користење на `FakeApi` сервис (како примерот во последната аудиториска вежба). Оваа апликација потребно е да ја изработите со користење на фрагменти и притоа за навигација помеѓу различните фрагменти потребно е да користите navigation component.

За секој филм потребно е да се чуваат следните информации:
- Идентификатор
- Име на филм
- Опис на филм
- Режисер
- Листа од актери

Главниот (првиот) фрагмент `MovieListFragment` треба да ги прикаже сите податоци добиени од страна на `FakeApi`  сервисот, притоа приказот на ваквите записи да се изработи со користење на `RecyclerView` компонента. Во рамките на `MovieListFragment` фрагментот за секој филм потребно е да се прикажуваат следните информации: идентификаторот на филмот, името на филмот и режисерот на филмот. При клик на било кој запис прикажан во рамките на `MovieListFragment`, потребно е корисникот да се пренесе на друг поглед (фрагмент) каде што ќе се прикажуваат деталите за селектираниот филм, односно ќе се прикажуваат сите информации кои се чуваат за еден филм (идентификатор, име, опис, режисер, листа од актери). Фрагментот кој што ќе ги прикажува деталите за еден филм именувајте го `MovieDetailsFragment`. 

Додавањето на нов филм во рамките на изработеното `FakeApi` можете да го изработите со користење на дијалог прозорец (како на аудиториски вежби) или со користење на нов фрагмент. Доколку се одлучите истото да го изработите со користење на фрагмент, фрагментот именувајтего `AddNewMovieFragment`. После успешно додавање на нов филм, на корисникот да се прикажуваат (прелистуваат) сите филмови кои се активни во рамките на `FakeApi` сервисот, односно корисникот да се пренесе на фратментот кој што е задолжен за оваа функционалност. 
