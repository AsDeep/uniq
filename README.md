# uniq
Объединение последовательностей одинаковых идущих подряд строк в файле в одну
## Использование
Командная строка: `uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]`

- `file` задаёт имя входного файла. Если параметр отсутствует, следует считывать текст с консоли.
	
- Флаг `-o ofile`  задаёт имя выходного файла. Если параметр отсутствует, следует выводить результаты на консоль.	
		
- Флаг `-i` означает, что при сравнении строк следует не учитывать регистр символов.
			
- Флаг `-s N` означает, что при сравнении строк следует игнорировать первые N символов каждой строки. Выводить нужно первую строку.	
	
- Флаг `-u` означает, что следует выводить в качестве результата только уникальные строки.
	
- Флаг `-с` означает, что перед каждой строкой вывода следует вывести количество строк, которые были заменены данной (т.е. если во входных данных было 2 одинаковые строки, в выходных данных должна быть одна с префиксом “2”).
