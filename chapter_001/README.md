<h2>Условие задачи</h2>
Требуется написать работающий код, решающий задачу, и приложить
инструкцию, как код собрать и запустить.<br>
Также надо написать unittest-ы.<br>
Задачу реализовать на Java (достаточно как консольное JAVA приложение).<br>
Код выложить на любой репозиторий (GitHub, GitLab, Butbucket)<br>
<br>
Имеется n пользователей, каждому из них соответствует список email-ов
(всего у всех пользователей m email-ов).<br>
Например:<br>
user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru<br>
user2 ->foo@gmail.com,ups@pisem.net<br>
user3 ->xyz@pisem.net,vasya@pupkin.com<br>
user4 ->ups@pisem.net,aaa@bbb.ru<br>
user5 ->xyz@pisem.net<br>
<br>
Считается, что если у двух пользователей есть общий email, значит это
один и тот же пользователь. Требуется построить
и реализовать алгоритм, выполняющий слияние пользователей. На выходе
должен быть список пользователей с их email-ами (такой же как на
входе).<br>
В качестве имени объединенного пользователя можно брать любое из
исходных имен. Список email-ов пользователя должен содержать только
уникальные email-ы.<br>
Параметры n и m произвольные, длина конкретного списка email-ов никак
не ограничена.<br>
Требуется, чтобы асимптотическое время работы полученного решения было
линейным, или близким к линейному.<br>
<br>
Возможный ответ на задачу в указанном примере:<br>
user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru,ups@pisem.net,aaa@bbb.ru<br>
user3 ->xyz@pisem.net,vasya@pupkin.com<br>

<h2>Использованные средства</h2>
<a href="https://www.oracle.com/java/technologies/javase-jdk15-downloads.html">Open JDK 14</a> - компилятор\интерпритатор<br>
<a href="http://maven.apache.org/index.html">Maven</a> - сборка и управление проектом

<h2>Компиляция</h2>
<pre>
<code>$ cd job4j_design_tests/chapter_001 
$ mvn package </code>
</pre>
Появится папка chapter_001/target, a в ней файл merge_users.jar

<h2>Запуск</h2>
<pre>
<code>$ java -jar merge_users.jar</code>
</pre>