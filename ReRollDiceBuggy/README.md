# ReRollDice
A dice game with a user playing against a computer.  The user first specifies how many rounds they want to play the game.  Each round is won by whoever has the highest dice roll, and each game is won by the person who wins a majority of the rounds.  Each round, before the user and the computer reveal their rolls, they are also given a chance to reroll.  In every game, the user is only allowed to reroll the number of rounds specified.  So, if a game has 5 rounds, then each player has 5 re-rolls they can use.  If both players roll the same number, then the round is settled with a 50/50 coin flip

## Program rules:
1.	The number of rounds must be greater than 3
1.	The number of rounds must also be an odd number
1.  The number of rerolls must be the same number as the number of rounds in a game
1.	Using a reroll will deplete it
1.	The winner is whoever wins (totalRounds +1)/2 rounds first
1.	The game cannot go past the number of rounds specified
1.	If a player wins a round, then their win tally should go up by one
1.	The computer or user cannot reroll if they do not have any rerolls left
1.	The computer rerolls at the following frequencies for each roll
*	Roll = 1: 100% chance of reroll
*	Roll = 2: 80% chance of reroll
*	Roll = 3: 50% chance of reroll
*	Roll = 4: 20% chance of reroll
*	Roll = 5: 5% chance of reroll
*	Roll = 6: 0% chance of reroll


## Your job is to both write tests and debug issues in this solution.
