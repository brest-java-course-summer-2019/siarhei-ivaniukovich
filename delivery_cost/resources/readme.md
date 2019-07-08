### price_per_**.csv format:
VALUE1, PRICE1
VALUE2, PRICE2
VALUE3, PRICE3
...

> Values less or equal than VALUE1 will return PRICE1
> Values more than VALUE1 and less or equal than VALUE2 will return PRICE2
> Values more than VALUE2 and less or equal than VALUE3 will return PRICE3
> Values more than VALUE3 will return PRICE3

EXMPL:
5,100   // <=5 = 100
10,75   // >5 && <=10 = 75
25,50   // >10 && <= 25 = 50
50,40   // >25 && <= 50 = 40
100,25  // >50 && <=100 = 25
        // >100 = 25
