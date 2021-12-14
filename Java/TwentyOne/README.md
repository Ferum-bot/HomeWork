# Twenty One game

The game rules are simple.

We have game players, which can be two types:
* Common player
* Cheater

Then we have game table with some infinity collection of cards.

Common player tries to get random card. Important that only one player can get card in one time.
Then player sleeps for 100-200 ms.

Cheater player can also steal cards from common players. Cheater can do it with 40% probability.
But after that action, cheater sleeps for 180-300 ms.

The game lasts for 10 seconds. 
After that, the scores of the players and the score of the winner will be displayed.


## See also:
* [How game works inside](HOW_GAME_WORKS.md)

#### Version 1.0.0
#### Created and powered by Matvey Popov
#### Test coverage 75%