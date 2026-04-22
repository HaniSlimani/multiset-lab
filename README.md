# MultiSet Lab

Implémentation en Java d'une structure de données **multi-ensemble** (`MultiSet<T>`) — une collection où chaque élément peut apparaître plusieurs fois et dont on retient le nombre d'occurrences.

Le projet suit les conventions de la **Java Collections Framework** et met en pratique plusieurs design patterns (Iterator, Decorator).

## Application concrète

Comptage de fréquence de mots dans un texte : trouver les mots les plus fréquents d'un fichier. Permet de comparer empiriquement les performances de différentes implémentations sur un corpus réel (War and Peace).

## Fonctionnalités

### Interface `MultiSet<T>`
- `add(e)` / `add(e, count)` — ajouter une ou plusieurs occurrences
- `remove(e)` / `remove(e, count)` — retirer une ou plusieurs occurrences
- `count(e)` — obtenir le nombre d'occurrences d'un élément
- `size()` — taille totale (somme des occurrences) en temps constant
- `clear()` — vider le multi-ensemble
- `elements()` — liste des éléments distincts
- Itérateur compatible `for-each`

### Conformité `Collection<T>`
`HashMultiSet<T>` hérite de `AbstractCollection<T>` et implémente `Collection<T>`, ce qui permet une interopérabilité totale avec l'écosystème des collections Java (tri, conversions, streams, etc.).

### Robustesse
- **Validation des arguments** — `IllegalArgumentException` pour tout usage incorrect (ex: `count < 0`)
- **Invariants vérifiés** via `isConsistent()` et assertions Java (`-ea`)
- **Exception personnalisée** `InvalidMultiSetFormat` pour le parsing de fichiers
- **Tests JUnit 5** couvrant les cas nominaux et les cas limites

## Architecture

### Deux implémentations pour comparaison

| Classe | Structure interne | Complexité `count` | Complexité `add` |
|--------|-------------------|---------------------|-------------------|
| `HashMultiSet<T>` | `HashMap<T, Integer>` | O(1) amorti | O(1) amorti |
| `NaiveMultiSet<T>` | `ArrayList<T>` avec doublons | O(n) | O(1) |

Sur un corpus comme *War and Peace* (~3 millions de mots), la différence de performance est spectaculaire — plusieurs ordres de grandeur.

### Pattern Iterator

Implémentation par **classe interne** `HashMultiSetIterator` qui délègue à l'itérateur d'entrées de la `HashMap` sous-jacente, tout en développant chaque paire `(élément, count)` en `count` appels successifs à `next()`.

Exemple : pour le multi-ensemble `{a:3, b:3, c:1}` (3 entrées dans la map), l'itérateur produit `a, a, a, b, b, b, c` (7 appels à `next()`).

### Pattern Decorator

`MultiSetDecorator<T>` permet d'ajouter la **vérification systématique de cohérence** à n'importe quelle implémentation de `MultiSet<T>` sans la modifier :

```java
MultiSet<String> unchecked = new HashMultiSet<>();
MultiSet<String> checked   = new MultiSetDecorator<>(new HashMultiSet<>());
```

Le décorateur peut envelopper aussi bien `HashMultiSet` que `NaiveMultiSet` — il ne dépend que de l'interface.

## Structure

```
src/pobj/
├── multiset/
│   ├── MultiSet.java                # Interface
│   ├── HashMultiSet.java            # Implémentation performante (+ itérateur interne)
│   ├── NaiveMultiSet.java           # Implémentation naïve (pour comparaison)
│   ├── MultiSetDecorator.java       # Décorateur de vérification
│   ├── MultiSetParser.java          # Parsing depuis un fichier texte
│   ├── InvalidMultiSetFormat.java   # Exception dédiée au parsing
│   ├── WordCount.java               # Application : top 10 des mots fréquents
│   └── test/                        # Tests JUnit 5
└── util/
    └── Chrono.java                  # Utilitaire de chronométrage

data/
└── WarAndPeace.txt                  # Corpus de test (~660 000 mots)
```

## Format de fichier pour `MultiSetParser`

```
chaine1:12
chaine2:49
chaine3:13
```

Chaque ligne : élément, deux-points, nombre d'occurrences.

## Exécution

### Prérequis
- Java 17+
- JUnit 5

### Lancer l'application de comptage
Importer le projet dans Eclipse et exécuter `WordCount` avec *Run As > Java Application*. Le programme affiche les 10 mots les plus fréquents du corpus.

### Activer les assertions d'invariants
Dans *Run As > Run Configurations > Arguments > VM arguments*, ajouter `-ea`.

### Tests
*Run As > JUnit Test* sur les classes de `pobj.multiset.test`.

## Auteurs

- Hani Slimani
- [@akkaboutaina](https://github.com/akkaboutaina)
