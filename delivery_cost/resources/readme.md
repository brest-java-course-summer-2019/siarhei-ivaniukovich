###price_per_**.csv format:
VALUE1, PRICE1
VALUE2, PRICE2
VALUE3, PRICE3
...

> Values less or equal than VALUE1 will return PRICE1
> Values more than VALUE1 and less or equal than VALUE2 will return PRICE2
> Values more than VALUE3 will return PRICE3

>  <-- <=VALUE1
>  .>VALUE1 <=VALUE2
>  .>VALUE2 <=VALUE3
>  .>VALUE3