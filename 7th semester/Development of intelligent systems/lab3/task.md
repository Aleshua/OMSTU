# Задача.

В зданиие два лифта. На каждом этаже, кроме вернего и первого, есть две кнопки
вызова лифта:

вызвать лифт для подъема на верхний этаж
вызвать лифт для спуска на нижний этаж. 

На первом соотвественно есть только одна кнопка для вызова лифта для подъема
на верхний этаж, а на последнем, соотвественно, только одна кнопка для вызова
лифта для спуска на нижний этаж. Лифт останавливается на этаже и открывает
двери, только если на этом этаже нажата кнопка вызова, совпадающая с
движением лифта. Каждый лифт обслуживает оба направления движения. 
Интерфейс лифта содержит команлы:

проехать этаж вверх
открыть двери
закртыть двери
проехать этаж вниз 

Лифт может двигаться с закртыми дверьми, при поптыке вполнить команду
проехать этаж вверх на посленем этаже и выполнить команду проехать этаж вниз
на первом этаже, команда выбрасывает исключение. 
Написать детерминированный конечный автомат для управления движения
лифтами. Стараемся оптимизировать время ожидания пассажиров.

На вход программа получает - этажность здания, этажи, на котором располагаются
лифты, список пар (номер этажа вызова, номер этажа. кужа нужно попасть).
На выходе - последовательность команд каждого из лифтов, для каждого вызова
необходимо вывести количество перемещений между этажами, которые совершили
лифты до отытия дверей на этаже вызова.