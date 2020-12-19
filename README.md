# advent-2020-clojure

This is my code for the 2020 [Advent of Code](https://adventofcode.com/2020), all solutions in [Clojure](https://clojure.org/).

All code is under the `src` directory. Each solution-file is named `dayNN.clj` and contains both puzzle solutions for that day. These are the publically-facing functions `part-1` and `part-2`. These files are the code *exactly as I used it to solve and submit the answers*. If I revisit any of the days and try to clean up or optimize the solutions, that work will be in a separate file that will be named `dayNNbis.clj`. (Except that I will likely go back and comment the code after the fact, when I'm not racing the clock.)

The `resources` directory contains the input data for each day. These files are named for the day (`dayNN.txt`), and files with the example input are named `dayNN-example.txt`.

The `test` directory contains unit tests. I'm experimenting with these for the first time this go-around, so I'll say more here once I have a better feel.

## Stats

Number of answers correct on first submission: 35/38 (92.11%)

Highest finish for first half: 1662 (day 19)

Highest finish for second half: 793 (day 19)

## Usage

This project is managed with [Leiningen](https://leiningen.org/). Running the following will download any dependencies and start a REPL:

```
lein repl
```

## Credits

This is based on my `advent-clojure-basis` repository, which itself is based almost entirely on Mitchell Hanburg's [Advent of Code Clojure Starter](https://github.com/mhanberg/advent-of-code-clojure-starter).

## License

Copyright © 2020 Randy J. Ray

Distributed under the Eclipse Public License either version 2.0 or (at your option) any later version.
