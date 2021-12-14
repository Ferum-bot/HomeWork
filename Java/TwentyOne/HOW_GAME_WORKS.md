# How game works inside?

The game application hase some base entities:
1. [Player](./src/main/java/com/github/ferum_bot/twentyone/models/players/Player.java) - base player interface.
2. [ThreadPlayer](./src/main/java/com/github/ferum_bot/twentyone/models/players/thread/ThreadPlayer.java) - base thread player. See also [CommonThreadPlayer](./src/main/java/com/github/ferum_bot/twentyone/models/players/thread/CommonThreadPlayer.java) and [CheaterThreadPlayer](./src/main/java/com/github/ferum_bot/twentyone/models/players/thread/CheaterThreadPlayer.java)
3. [GameService](./src/main/java/com/github/ferum_bot/twentyone/service/GameService.java) - contains base game logic.
4. [GameTable](./src/main/java/com/github/ferum_bot/twentyone/models/game/GameTable.java) - represents game table with cards.

# More information about each entity:

## 1. Player:

Represents base interface of game player. 

Allows you to start the player or stop.
See more information at [java doc](./src/main/java/com/github/ferum_bot/twentyone/models/players/Player.java).

## 2. ThreadPlayer:

Represents abstract thread realization of thread player. [More information](./src/main/java/com/github/ferum_bot/twentyone/models/players/thread/ThreadPlayer.java).

Contains score, number and players lock object. Lock object used to synchronized collecting and changing score.

### Realizations:

#### 1. CommonThreadPlayer:
Represents common thread player that only can take cards from game table. [More information](./src/main/java/com/github/ferum_bot/twentyone/models/players/thread/CommonThreadPlayer.java).

Implements action block with synchronization on self lock object. It is necessary that in the process of receiving a new card, no other player can change the state of this player.

#### 2. CheaterThreadPlayer:
Represents cheater thread player that can also steal cards from common players. [More information](./src/main/java/com/github/ferum_bot/twentyone/models/players/thread/CheaterThreadPlayer.java).

Implements action block with two different types of action: get card from game table or steal from common player.
If steal action is chosen, cheater gets random common player and trying to decrement its score with synchronization on it lock object.

Thus, with the help of synchronization at the player's lock object, it is guaranteed that such situation when several cheaters simultaneously change the player's balance or the player will take a card while they are being stolen from him will not occur.

## 3. GameService:
Represents main game logic. [More information](./src/main/java/com/github/ferum_bot/twentyone/service/GameService.java).

Creates needed players, starts game, calculates winner and provides base player information.

## 4. GameTable:

Represents the game table entity. [More information](./src/main/java/com/github/ferum_bot/twentyone/models/game/GameTable.java).

Returns random card number. Important that method is synchronized on class instance, so only one thread(player) can get new game card.

## See also:
* [ReadMe](README.md)

#### Version 1.0.0
#### Created and powered by Matvey Popov