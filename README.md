# Blackjack_count

Just a BlackJack software to help you follow the game. 


## What does it look like ?  
![GUI](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/GUI.png)

## How does it work ?  

### What does it do ? 
- Calculate probability of each card to come depending of the numbers of deck and all precedent games.
- Keep the Hi-Lo count (card counting technique) 
- Gives you the best move to make based on your cards and the dealer's card 

### How does it do that ? 
#### First thing first, how many decks ? 
![SABOT](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/Sabot.png)

The first thing you will need to do is to select the good number of decks there are. 
Usually, it's about 5-6 but this is not mandatory so pay attention. 
You can also change the number of decks during the use of the app, it will adapt, recalculate the probability but keeping in memory that card that have already been dealt. 

#### Probability display 
![Probability](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/probability.png)

At the right, you will see two rectangle with a lot of number in it. 
In the first rectangle, you can see how many card of each value is still in the shoe. (The number are changing each time you pick a card)
In the second rectangle, you can see the probability of each card to appear. (The number are also changing each time you pick a card)

#### Low-High count
![Lo-Hi](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/lo_hi.png)

At the bottom right you will find the Low-High score, it help you to know if there is more chance that the next card is high or low value. 
How does it work ? 
If a 2-6 card appear, it makes -1 to the score. 
If a 7-9 card appeear, it makes nothing to the score. 
If a 10 - King appear, it makes +1 to the score. 
So, if the score displayed is a number, more high value card have been played, so you have more chance to have low value card in the future. 
If the score displayed is a number and a "M" (mean minus), more low value card have been played, so you have more chance to have high value card in the future. 

#### Next Hand
![next_hand](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/next_hand.png)

At the top right there is a button that says "Next Hand", if you click on it, it will stop the round. Means that one of you has win and so we can throw the card and get new ones. Your score and the dealer score will be 0 again and you will have the possibilty to play another round and keep going with probability and counting. 

#### Recommended move
![recommended_move](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/Conseil.png)

BlackJack is all about probability and calcul. And so, there is a list of the best moves to make depending of all the situations that are possible. Here's where I found the one I used : http://casinomaths.free.fr/black2.html
So, depending of the dealer first card and you first card, a recommended play will appear at the left of the software, free to you to take it into account or not. 
Here is a traduction of the letter : 

R : Rester i.e. laisser le croupier jouer (STAY)
T : Tirer i.e. demander une carte (HIT)
D : Doubler i.e. doubler sa mise (DOUBLE)
P : Partager i.e. couper son jeu en deux et en faire sortir 2 jeux (SPLIT)
A/t : abandon si possible sinon tirer (Give Up or Hit)
A/r : abandon si possible sinon rester (Give Up or Stay)

#### Get a new card 
So, adding a new card to you, the dealer or other player is pretty easy. 
First, you have to select the value of the card in the list. 
![list cards](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/list_card.png)

Then, depending on who you want to give it to, you'll have to click on the right button. 
If you want to give it to the other player, then click "+ autre carte" bottom left. 
![+ other card](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/other_card.png)

If you want to give it to the dealer, then click "+ carte" just on top of the list. 
If you want to give it to you, then click "+ carte" just at the bottom of the list. 
![+ card](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/%2Bcarte.png)


#### Keep the score
![affichage score et cartes](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/affichage_score_cartes.png)

In the middle of the software, you will see your card, the card of the dealer, your score and the score of the dealer displayed. The dealer is on top and you are at the bottom. 

#### Split split split ! 
![Split button](https://github.com/AphroMad/Blackjack_count/blob/main/BlackJack_Count/images/Split.png)

Here is the split button, this is kind of special rule in blackjack, you can split your cards in two and then play like you are 2 player at the same time. 
You can only split when you have a pair of card, for example, two 9 or two Kings. 
Then, if you chose to split, there will be some graphic change for this hand, there will be 2 buttons "+", two scores, two hands with cards, and two recommended move to do. 


## Will it make me rich ? 
Absolutely not ! 

## Why is that ? 
Well, because it's forbidden to count cards in a casino and even if you do it, they shuffle several decks of cards together and play with only half of all these cards. 

