# CurrencyConverter

Application Android de conversion de devises réalisée en **Kotlin** avec **Android Studio** dans le cadre d’un TP.

## Présentation

CurrencyConverter permet de convertir un montant d’une devise source vers une devise cible à l’aide de taux de change fixes fournis dans le sujet du TP.

L’interface comprend :
- deux listes déroulantes pour choisir les devises ;
- un affichage du montant et du résultat ;
- un bouton pour inverser les devises ;
- un bouton **Convertir** ;
- un clavier numérique intégré à l’application.

## Technologies utilisées

- Android Studio
- Kotlin
- XML pour l’interface
- View Binding
- `ConstraintLayout`, `TableLayout`, `Spinner` et `ArrayAdapter`

**SDK minimum :** API 24.

## Devises disponibles

- USD — Dollar américain
- EUR — Euro
- TND — Dinar tunisien
- GBP — Livre sterling
- JPY — Yen japonais
- CAD — Dollar canadien

## Fonctionnement

La conversion utilise l’euro comme devise pivot. Les taux sont fixes et servent uniquement à l’exercice ; ils ne représentent pas nécessairement les taux réels.

1. Montant en EUR = montant saisi ÷ taux de la devise source.
2. Résultat = montant en EUR × taux de la devise cible.

Le résultat est affiché avec deux chiffres après la virgule.

### Règles de saisie

- Le montant initial est `0`.
- Le premier chiffre saisi remplace le zéro initial.
- Un seul point décimal est autorisé.
- Le montant est limité à 10 caractères.
- Le bouton **C** remet le montant à `0` et efface le résultat.
- Si le montant vaut zéro, l’application affiche le message : « Veuillez saisir un montant ».
- Si les deux devises sont identiques, le résultat est égal au montant saisi.
- Le bouton d’inversion échange les devises sélectionnées et relance la conversion si un résultat est déjà affiché.

## Cas de test du TP

| Montant | Devise source | Devise cible | Résultat attendu |
|---:|:---:|:---:|---:|
| 120 | USD | EUR | 102.56 |
| 100 | EUR | TND | 340.00 |
| 50 | GBP | USD | 67.24 |
| 10 | EUR | EUR | 10.00 |

## Lancer le projet

1. Cloner ou télécharger le dépôt GitHub.
2. Ouvrir le projet dans Android Studio.
3. Attendre la synchronisation Gradle.
4. Lancer l’application sur un émulateur ou un appareil Android.

## Captures d’écran

Les captures d’écran de l’application peuvent être placées dans le dossier `screenshots/`, notamment l’écran initial et les quatre cas de test.


