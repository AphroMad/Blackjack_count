# -*- coding: utf-8 -*-
"""
Created on Wed May 13 10:20:38 2020

@author: pierr
"""

""" Probability
https://fr.blackjack.org/wp-content/uploads/2017/09/basic-blackjack-strategy-522x1030.png
"""

""" Learn how to count 
https://www.countingedge.com/fr/comptage-des-cartes/hi-opt-ii/
"""


# function that print a list with all the probability in it 
def print_line(tabl) : 
    for i in range(len(tabl)) : 
        print(tabl[i])


#sabot = int(input("Nombre de paquets de cartes : \t")) # if you want to enter the value of sabot each time 
sabot = 8 

cartes_couleur = ["heart","diamonds","spades","clubs"] # list of color 
cartes_valeur = ["1","2","3","4","5","6","7","8","9","10","11","12","13"] # list of card_value 
nbr_mm_carte = 4 * sabot # number of each card (each king, each queen, etc...)
nbr_tot = 52 * sabot # total number of cards

proba_carte_depart = round(nbr_mm_carte / nbr_tot *100,2) # Calculate first probability 

nombre_carte = [] # a list with all the card that'll be available 
tot_now = nbr_tot # number total of cards 

for i in range(len(cartes_valeur)) : # we go through each cards available 
    nombre_carte.append([cartes_valeur[i],nbr_mm_carte,proba_carte_depart]) # we add in a list the card, the number of the same, and the probability that it can be played 

    
print_line(nombre_carte) # print all the probability 

while True : 
    
    carte_now = 0 
    carte_now = (input("Enter a card : \n(V = 11, Q = 12, R = 13) \nPress l to leave \n\t")) # ask to the user to enter a card 

    try : carte_now = int(carte_now) # if the input can be converted to an int, we do it 
    except : # if it canno't,
        if carte_now == "l" : break # we check if the input is an 'l', if yes, we leave
        else :  # if it's not an 'l'
            print("Please enter a number") # we ask for an other number 
            continue # and we begin again the loop 
    
        
    if 1<=carte_now<=13 : 

        for i in range(len(nombre_carte)) : 
            
            if carte_now == i+1 : # we search the card played 
                
                nombre_carte[i][1] = nombre_carte[i][1] - 1 # reduce the number of this card available     
                tot_now = tot_now - 1 # reduce the number of total cards 
    
                
        for i in range(len(nombre_carte)) : 
           
            nombre_carte[i][2] = round(nombre_carte[i][1] / tot_now*100,2) # calculate the probability 
                
        print_line(nombre_carte) # print the new proba 
    
        # do it again ! 
    
    else : 
        print("There is no card for this number")
                



