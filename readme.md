# Capitally Game

## By: Nadeen Hussein ZZKUIL

## Task

### Simulate a simplified Capitally game. There are some players with different strategies, and a cyclical board with several fields.</br> Players can move around the board, by moving forward with the amount they rolled with a die. </br> A field can be a property, service, or lucky field. A property can be bought for 1000, and stepping on it the next time the player can build a house on it for 4000. </br> If a player steps on a property field which is owned by somebody else, the player should pay to the owner 500, if there is no house on the field, or 2000, if there is a house on it. </br>Stepping on a service field, the player should pay to the bank (the amount of money is a parameter of the field). Stepping on a lucky field, the player gets some money (the amount is defined as a parameter of the field). There are three different kind of strategies exist. Initially, every player has 10000.</br>1.Greedy player: If he steps on an unowned property, or his own property without a house, he starts buying it, if he has enough money for it.</br> Careful player: he buys in a round only for at most half the amount of his money. </br>Tactical player: he skips each second chance when he could buy. </br>If a player has to pay, but he runs out of money because of this, he loses. In this case, his properties are lost, and become free to buy.</br> Read the parameters of the game from a text file. This file defines the number of fields, and then defines them. We know about all fields: the type. </br>If a field is a service or lucky field, the cost of it is also defined. After the these parameters, the file tells the number of the players, and then enumerates the players with their names and strategies. In order to prepare the program for testing, make it possible to the program to read the roll dices from the file.</br> **Print out which player loses as a second loser**

</br></br></br>

## Implementation</br>

## Classes

## dice directory

1. Rolled Dice

    * Purpose : Add two values of dice thrown
    * Methods
        * RolledDice Constructor
        * getValue: return sum of values

## field directory

### A. action.step directory

1. StepAction Interface
    * Purpose: Performs step action depending on a player's condition
    * Methods:
        * doStep : Decides what should the player pay/earn/buy according to the field the player stepped on.
2. LuckyStepAction
    * Purpose: Gets lucky field value and adds it to the player's money
    * Methods
        * LuckyStepAction Constructor
        * doStep : overridden method that gets lucky value and adds it to the player's total money
3. PropertyStepAction
    * Purpose: Gets property field value and acts upon player's conditions
    * Methods
        * PropertyStepAction Constructor
        * doStep :Checks if the property has an owner and that it's not the player passed. Checks if there is a house built to charge the player to pay it's owner. Otherwise player buys property.
4. ServiceStepAction
    * Purpose: Gets service field value and subtracts it to the player's money
    * Methods
        * ServiceStepAction Constructor
        * doStep : overridden method that gets the service price and subtracts it from player's money

### A. Filed Interface

    *Purpose: Gets field player stepped on
    * Method:
        *playerStepped: Gets field the player stepped on and calls doStep method in StepAction to perform an action depending on the field.

### B. LuckyField

    *Purpose: Takes lucky value and uses doStep method from StepAction interface
    *Method:
        *LuckyField Constructor
        *playerStepped: Calls doStep to perform lucky step action from StepAction class

### C. PropertyField

    *Purpose: Takes lucky value and uses doStep method from StepAction interface
    *Method:
        *PropertyField Constructor
        *isOwner : takes a player and returns if the player is the owner of property
        *playerStepped: Calls doStep to perform
        *isHouseBuilt : returns if there is a house built on field
        *playerStepped: Calls doStep method from StepAction class to perform property step action.Calls player strategy to buy/noy buy property according to player's strategy.

### D. ServiceField

    *Purpose: Takes service value and uses doStep method from StepAction interface
    *Method:
        *ServiceField Constructor
        *playerStepped: Calls doStep to perform service step action from StepAction class

## player directory

### A. actions directory

1. PlayerActions Interface
    * Purpose: performs player's actions
    * Method:
        * buildHouse : takes a field and build a house according to the player's owned property and total money.
        * buyProperty: takes a property field and buys a property according to the player's conditions
        *getPlayer : returns player
        * removeOwner : removes owner of the property if he/she loses
        * isOwned: checks if property field is owned by a player.
2. PlayerActionsImpl
    * Purpose: Implementations of all the Player Actions method
    * Methods:
        * PlayerActionsImpl Constructor
        * getPlayer : returns player
        * buildHouse :  Checks is player is the owner and allows him/her to buy a house by subtracting house price from his money and setting the field to have a house.
        * buyProperty:  If property has no owner and player steps in field then the field is added to player's ownership list and subtract 1000 from his total money
        * removeOwner: removes owner from the field.
        * isOwned: checks if property is owned.

### B. strategy directory

1. Strategy Interface
    * Methods:
        * playStrategy: performs action according to the player's strategy.
        * getPlayerAction : returns playerAction type

2. CarefulStrategy
    * Purpose: performs player's actions according to the rule : player buys in a round only for at most half the amount of player's money
    * Methods:
        * CarefulStrategy Constructor : sets the amount of money the player has per round by getting the full money the player has and divide it by 2.
        * hasEnoughMoney: returns if player has enough money according to the player's strategy
        * playStrategy: Careful player builds a house or buys a property in a round up to half oh his money
        * getPlayerAction: return the playerActions.
3. GreedyStrategy
    * Purpose: If the player steps on an unowned property, or the player's own property without a house, player starts buying it, if player has enough money for it
    * Methods:
        * GreedyStrategy Constructor
        * playStrategy:  Greedy player builds a house or buys a property
        * getPlayerAction: return the playerActions.
4. TacticalStrategy
    * Purpose:player skips each second chance when player could buy.
    * Methods:
        * TacticalStrategy Constructor
        * playStrategy: Tactical player builds a house or buys a property every second chance
        * getPlayerAction: return the playerActions.
5. StrategyType Enum: 
    * Fields
        * TACTICAL
        * GREEDY
        * CAREFUL
6. StrategyFactory
    * Purpose : creates a strategy according to the StrategyType for each player
    * Methods:
        * createStrategy : gets player and strategy type from StrategyType Enum and calls create methods according to the field.
        * createGreedy : returns greedy player type
        * createCareful : returns careful player type
        * createTactical : returns tactical player type

### C. NotEnoughMoney Class

* Purpose : if player doesn't have enough money then player loses
* Methods
  * NotEnoughMoney: displays error message

### D. Player Class

* Purpose : Identifies each player
* Methods:
  * Player Constructor
  * subtractMoney : Checks if player has the money then subtracts value from a player's total amount.
  * addMoney : add value to the total money of the player.
  * playStrategy: If player loses then all his properties should be freed.
  * increasePosition :  Keeps track of the round, and the last position the player was on. Then increases it and saves the new position

## PlayGround Class

* Purpose : reads the file input, gets all the players and fields and start the game.
* Methods:
  * play : Gives back the second loser from the losers list.
  * getStrategyType : Goes through the players' strategies. If the strategy is between Greedy, Careful, or Tactical -> Calls the strategy type from Enum StrategyType
  * playTheGame : Reads the dice numbers then checks if player loses
  * init : Gets the number passed as numbers of fields and pass the players in the players list and the fields in fields list
  * findPlayer : Makes sure that player playing is the same player in the index of the players list.
  * fillPlayersList : Reads in the player's name and strategy line by line depending on the number of players given.
  * fillFieldsList : Read lines of fields according to a given number and defines them as property, service, or lucky fields
  * playOneTurnWithPlayer : Keep track of the player's position after rolling the dice and performs action according to which field the player stepped on
