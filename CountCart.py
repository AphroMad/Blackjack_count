# -*- coding: utf-8 -*-
"""
Created on Wed May 13 10:20:38 2020

@author: pierr
"""

""" Compteur de cartes blackjack"""

""" site BJ
https://lesmachinesasous.fr/blackjackenligne/
"""

""" Strat
https://fr.blackjack.org/wp-content/uploads/2017/09/basic-blackjack-strategy-522x1030.png
"""

""" Comptage
https://www.countingedge.com/fr/comptage-des-cartes/hi-opt-ii/
"""

"""7 ou 8 paquets je pense"""


def print_line(tabl) : 
    
    for i in range(len(tabl)) : 
        print(tabl[i])


sabot = int(input("Nombre de paquets de cartes : \t"))

cartes_couleur = ["coeur","carreau","pique","trèfle"] # la liste des symboles 
cartes_valeur = ["1","2","3","4","5","6","7","8","9","10","11","12","13"] # la liste des valerurs 
nbr_mm_carte = 4 * sabot # le nombre de cartes à mêmes valeurs totale
nbr_tot = 52 * sabot # le nombre total de cartes dans le sabot 

proba_carte_depart = round(nbr_mm_carte / nbr_tot *100,2) # calcul de la proba de départ que chaque carte tombe

nombre_carte = []
tot_now = nbr_tot 


for i in range(len(cartes_valeur)) : 
    nombre_carte.append([cartes_valeur[i],nbr_mm_carte,proba_carte_depart])

    
print_line(nombre_carte)


while True : 
    
    carte_now = int(input("Rentrer une carte : \n(V = 11, Q = 12, R = 13) \nEnter pour quitter \t"))

    for i in range(len(nombre_carte)) : 
        
        if carte_now == i+1 : # on cherche l'emplacement de la carte choisie
            
            nombre_carte[i][1] = nombre_carte[i][1] - 1 # on réduis le nombre de dispo cette carte de 1      
            tot_now = tot_now - 1 # on dis qu'on enlève une carte 
            #nombre_carte[i][2] = round(nombre_carte[i][1] / tot_now*100,2) # on recalcule la proba 
            
    for i in range(len(nombre_carte)) : 
       
        nombre_carte[i][2] = round(nombre_carte[i][1] / tot_now*100,2) # on recalcule les proba 
            
    print_line(nombre_carte)
                



