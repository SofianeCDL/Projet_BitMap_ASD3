# Projet_BitMap_ASD3

Projet de compression d'image de taille 2^n * 2^n de coté. Le principde est d'enregistrer recusirvement tout les pixels dans un arbre quadratique, pour cela nous decoupons l'image en 4 et pointons chaque branche un coin découpé, ainsi de suite. 

# DEMARRAGE

# mode non interactif
#windows : Ouvrire ou placer vous avec un fenètre PowerShell dans le dossier qui contient Projet_BitMap_ASD3.jar. Lancer la commande "java -jar .\Projet_BitMap_ASD3.jar 'chemin ou nom de l'image**' 'valeur delta' 'valeur phi'".

#linux : Ouvrire ou placer vous avec un terminal dans le dossier qui contient le Projet_BitMap_ASD3.jar. Lancer la commande "java -jar Projet_BitMap_ASD3.jar 'chemin ou nom de l'image**' 'valeur delta' 'valeur phi'".

**Pour charger une image, donnez le chemin ou mettez juste le nom (avec ou sans extension) si celle çi se situe dans le dossier "pngs" fournit avec.

# Mode interactif
#windows : Ouvrire ou placer vous avec un fenètre PowerShell dans le dossier qui contient Projet_BitMap_ASD3.jar. Lancer la commande "java -jar .\Projet_BitMap_ASD3.jar".

#linux : Ouvrire ou placer vous avec un terminal dans le dossier qui contient le Projet_BitMap_ASD3.jar. Lancer la commande "java -jar Projet_BitMap_ASD3.jar".

# EXECUTION 

# Mode non interactif
Il suffit juste de mettre le nom ou le chemin de l'image, la valeur du delta et la valeur du phi en argument lors de l'éxécution. deux nouvelles images apparaitrons alors dans "SavePNG" nomImage-delta"nombreDelta".png et nomImage-phi"nombrePhi".png qui sont les compression delta et phi de l'image et deux nouveaux fchier txt apparaitrons dans "SaveTXT" nomImage-delta"nombreDelta".txt et nomImage-phi"nombrePhi".txt qui corresponds aux fichiers textes des arbres compressés.

Un rapport de poid s'affichera dans le terminal ou le powerShell.

# mode intercatif 

Au début du programme, il sera demandé de charger une image avant d'acceder au menu. Pour charger une image, donnez le chemin ou mettez juste le nom (avec ou sans extension) si celle çi se situe dans le dossier "pngs" fournit avec.

Il vous sera possible ensuite de choisir de recharger une image (0), de faire une compression delta/phi(1/2) et afficher l'arbre en écriture (A). 

Une des options de compression permetra de sauvegarder votre image en .png (4) dans le dossier "SavePNG" fournit avec ou de sauvegarder dans un .txt (5) l'affichage écrit de votre arbre dans le dossier "SaveTXT" fournit avec.

Sauvegarder votre image en png permetra ensuite d'effectuer les mesures comparatives (6) de l'image d'origine avec l'image compresser.

# AUTEURS
Sofiane COUËDEL : étaudaint en licence 3 Informatique Nantes.
Lou-Anne SAUVETRE : étaudaint en licence 3 Informatique Nantes.
